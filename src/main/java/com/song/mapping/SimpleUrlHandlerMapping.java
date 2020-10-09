package com.song.mapping;

import com.song.handler.QueryUserHandler;
import com.song.spring.factory.support.DefaultListableBeanFactory;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Song on 2020/09/27.
 */
public class SimpleUrlHandlerMapping implements HandlerMapping{

    private Map<String, Object> urlHandlers = new HashMap<>();

    public SimpleUrlHandlerMapping() {
//        this.urlHandlers.put("/queryUser", new QueryUserHandler());
    }

    @Override
    public Object getHandler(HttpServletRequest request) {
        String requestURI = request.getRequestURI();
        if (requestURI == null || requestURI == "") {
            return null;
        }
        return this.urlHandlers.get(requestURI);
    }
}
