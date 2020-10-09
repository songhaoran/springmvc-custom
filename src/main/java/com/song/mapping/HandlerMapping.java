package com.song.mapping;

import javax.servlet.http.HttpServletRequest;

/**
 * 处理器映射器标准接口
 */
public interface HandlerMapping {

    Object getHandler(HttpServletRequest request);
}
