package com.song.mapping;

import com.song.anotation.Controller;
import com.song.anotation.RequestMapping;
import com.song.model.HandlerMethod;
import com.song.spring.aware.BeanFactoryAware;
import com.song.spring.factory.BeanFactory;
import com.song.spring.factory.support.DefaultListableBeanFactory;
import com.song.spring.init.InitializingBean;
import com.song.spring.ioc.BeanDefinition;
import org.apache.commons.lang3.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Song on 2020/09/30.
 */
public class RequestMappingHandlerMapping implements HandlerMapping, BeanFactoryAware, InitializingBean {

    private Map<String, Object> urlHandlers = new HashMap<>();

    private DefaultListableBeanFactory beanFactory;

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
        List<BeanDefinition> beanDefinitions = beanFactory.getBeanDefinitions();
        for (BeanDefinition bd : beanDefinitions) {
            Class<?> clazz = bd.getClazzType();
            if (isHandler(clazz)) {
                RequestMapping beanRequestMapping = clazz.getAnnotation(RequestMapping.class);
                String beanUrl = beanRequestMapping != null ? beanRequestMapping.value() : "";

                Method[] methods = clazz.getMethods();
                for (Method method : methods) {
                    if (method.isAnnotationPresent(RequestMapping.class)) {
                        HandlerMethod handlerMethod = new HandlerMethod(beanFactory.getBean(bd.getBeanName()), method);
                        RequestMapping methodRequestMapping = method.getAnnotation(RequestMapping.class);
                        String methodUrl = methodRequestMapping.value();
                        String url;
                        if (beanUrl != null && "".equals(beanUrl) == false) {
                            url = beanUrl + methodUrl;
                        } else {
                            url = methodUrl;
                        }

                        urlHandlers.put(url, handlerMethod);
                    }
                }
            }
        }
    }

    private boolean isHandler(Class clazz) {
        return clazz.isAnnotationPresent(Controller.class)
                || clazz.isAnnotationPresent(RequestMapping.class);
    }
}
