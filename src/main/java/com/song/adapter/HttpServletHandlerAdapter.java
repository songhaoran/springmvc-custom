package com.song.adapter;

import com.song.handler.HttpServletHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


public class HttpServletHandlerAdapter implements HandlerAdapter {

    @Override
    public Object handleRequest(Object handler, HttpServletRequest req, HttpServletResponse resp) throws IOException {
        ((HttpServletHandler) handler).handleRequest(req, resp);
        return null;
    }

    @Override
    public boolean supports(Object handler) {
        return handler instanceof HttpServletHandler;
    }
}
