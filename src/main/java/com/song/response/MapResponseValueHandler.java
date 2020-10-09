package com.song.response;

import com.song.util.JsonUtil;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Song on 2020/10/05.
 */
public class MapResponseValueHandler implements ResponseValueHandler{

    @Override
    public void handleResp(HttpServletResponse resp, Object value) throws IOException {
        resp.setContentType("application/json;charset=utf8");
        resp.getWriter().write(JsonUtil.toJsonString(value));
    }
}
