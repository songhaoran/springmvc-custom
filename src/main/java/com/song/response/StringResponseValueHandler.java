package com.song.response;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Song on 2020/10/05.
 */
public class StringResponseValueHandler implements ResponseValueHandler{

    @Override
    public void handleResp(HttpServletResponse resp, Object value) throws IOException {
        resp.setContentType("text/plain;charset=utf8");
        resp.getWriter().write(value.toString());
    }
}
