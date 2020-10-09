package com.song.handler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class QueryUserHandler implements HttpServletHandler {

    @Override
    public void handleRequest(HttpServletRequest req, HttpServletResponse response) throws IOException {
        response.setContentType("text/plain;charset=utf-8");
        response.getWriter().write("时间不多了,加油!");
    }
}
