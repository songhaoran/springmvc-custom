package com.song.response;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Song on 2020/10/05.
 */
public interface ResponseValueHandler {

    void handleResp(HttpServletResponse resp, Object value) throws IOException;
}
