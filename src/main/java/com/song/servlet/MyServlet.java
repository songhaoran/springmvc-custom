package com.song.servlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 一个Servlet对应一个请求
 * doGet:get请求的处理逻辑
 * doPost:post请求的处理逻辑
 */
public class MyServlet extends HttpServlet {

    @Override
    public void init() throws ServletException {
        System.out.println("MyServlet init!");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
       resp.setContentType("text/plain;charset=utf-8");
       resp.getWriter().write("加油!");
    }

    @Override
    public void destroy() {
        System.out.println("MyServlet destroy!");
    }
}
