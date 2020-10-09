package com.song.handler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 处理器标准
 */
public interface HttpServletHandler {

    void handleRequest(HttpServletRequest req, HttpServletResponse response) throws IOException;
}
