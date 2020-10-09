package com.song.adapter;

import com.song.handler.SimpleControllerHandler;
import com.song.model.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Song on 2020/09/27.
 */
public class SimpleControllerHandlerAdapter implements HandlerAdapter {

    @Override
    public Object handleRequest(Object handler, HttpServletRequest req, HttpServletResponse resp) throws IOException {
        ModelAndView modelAndView = ((SimpleControllerHandler) handler).handleRequest(req, resp);
        return modelAndView;
    }

    @Override
    public boolean supports(Object handler) {
        return handler instanceof SimpleControllerHandler;
    }
}
