package com.song.handler;

import com.song.model.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by Song on 2020/09/27.
 */
public interface SimpleControllerHandler {

    ModelAndView handleRequest(HttpServletRequest req, HttpServletResponse response);
}
