package com.song.mapping;

import com.song.handler.QueryUserHandler;
import com.song.spring.aware.BeanFactoryAware;
import com.song.spring.factory.BeanFactory;
import com.song.spring.factory.support.DefaultListableBeanFactory;
import com.song.spring.init.InitializingBean;
import com.song.spring.ioc.BeanDefinition;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class BeanNameUrlHandlerMapping implements HandlerMapping, BeanFactoryAware, InitializingBean {

    private Map<String, Object> urlHandlers = new HashMap<>();

    private DefaultListableBeanFactory beanFactory;

    public BeanNameUrlHandlerMapping() {
        this.urlHandlers.put("/queryUser", new QueryUserHandler());
        // 初始化映射关系
    }

    @Override
    public Object getHandler(HttpServletRequest request) {
        String requestURI = request.getRequestURI();
        if (requestURI == null || requestURI == "") {
            return null;
        }
        return this.urlHandlers.get(requestURI);
    }

    @Override
    public void setBeanFactory(BeanFactory beanFactory) {
        this.beanFactory = (DefaultListableBeanFactory) beanFactory;
    }

    @Override
    public void afterProperties() {
        // 建立映射关系
        List<BeanDefinition> beanDefinitions = beanFactory.getBeanDefinitions();
        for (BeanDefinition bd : beanDefinitions) {
            String beanName = bd.getBeanName();
            if (beanName.startsWith("/")) {
                this.urlHandlers.put(beanName, beanFactory.getBean(beanName));
            }
        }
    }
}
