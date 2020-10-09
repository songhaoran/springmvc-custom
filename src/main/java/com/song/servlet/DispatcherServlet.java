package com.song.servlet;

import com.song.adapter.HandlerAdapter;
import com.song.mapping.HandlerMapping;
import com.song.spring.factory.support.DefaultListableBeanFactory;
import com.song.spring.reader.XmlBeanDefinitionReader;
import com.song.spring.resource.ClasspathResource;
import com.song.spring.resource.Resource;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

/**
 * 使用该Servlet处理所有的请求
 */
public class DispatcherServlet extends AbstractServlet {

    private List<HandlerAdapter> handlerAdapters = new ArrayList<>();

    private List<HandlerMapping> handlerMappings = new ArrayList<>();

    private DefaultListableBeanFactory beanFactory;

    private static String contextConfigLocation = "contextConfigLocation";

    @Override
    public void init(ServletConfig config) throws ServletException {

        String location = config.getInitParameter(contextConfigLocation);

        // 初始化spring
        initSpringContainer(location);

        // 初始化策略集合
        initStrategies();
    }

    private void initStrategies() {
        initAdapter();
        initHandlerMapping();
    }

    private void initSpringContainer(String location) {
        beanFactory = new DefaultListableBeanFactory();
        XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(beanFactory);
        Resource resource = new ClasspathResource(location);
        reader.loadBeanDefinitions(resource);
    }

    private void initHandlerMapping() {
        handlerMappings = beanFactory.getBeansByType(HandlerMapping.class);

//        handlerMappings.add(new BeanNameUrlHandlerMapping());
//        handlerMappings.add(new SimpleUrlHandlerMapping());
    }

    private void initAdapter() {
        handlerAdapters = beanFactory.getBeansByType(HandlerAdapter.class);

//        handlerAdapters.add(new HttpServletHandlerAdapter());
//        handlerAdapters.add(new SimpleControllerHandlerAdapter());
    }

    @Override
    public void doDispatch(HttpServletRequest req, HttpServletResponse resp) {
        try {
            // 1. 根据请求查找对应的处理器
            Object handler = getHandler(req);
            if (handler == null) {
                return;
            }

            // 2. 执行处理器逻辑,获取处理结果

            // 适配器(DispatcherServlet --> 适配器 --> handler)
            HandlerAdapter ha = getHandlerAdapter(handler);
            if (ha == null) {
                return;
            }

            // 3. 返回处理结果给客户端
            ha.handleRequest(handler, req, resp);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private HandlerAdapter getHandlerAdapter(Object handler) {
        // 这里就是策略模式,可以省略臃肿的if else判断
        for (HandlerAdapter adapter : handlerAdapters) {
            if (adapter.supports(handler)) {
                return adapter;
            }
        }
        return null;
    }

    private Object getHandler(HttpServletRequest req) {

        // 根据处理器和请求的映射关系查找(映射可能存储在xml,或者map)

        // 方式一:BeanNameUrlHandlerMapping
        //<bean name="/queryUser" class="处理器类的全路径"/>

        // 方式二:SimpleUrlHandlerMapping
        //<bean class="专门用来建立映射关系的类">
        //  <props>
        //      <prop key="/queryUser">处理器类的全路径</prop>
        //  </props>
        //</bean>

        // 先从方法一中查找,再从方法二中查找
        for (HandlerMapping hm : handlerMappings) {
            Object handler = hm.getHandler(req);
            if (handler != null) {
                return handler;
            }
        }

        return null;
    }
}
