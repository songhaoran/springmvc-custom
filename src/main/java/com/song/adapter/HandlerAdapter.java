package com.song.adapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

/**
 * 用于将各种处理器适配成统一的
 */
public interface HandlerAdapter {
    Object handleRequest(Object handler, HttpServletRequest req, HttpServletResponse resp) throws IOException, InvocationTargetException, IllegalAccessException;

    boolean supports(Object handler);
}
