package com.song.mapping;

import javax.servlet.http.HttpServletRequest;


public interface HandlerMapping {

    Object getHandler(HttpServletRequest request);
}