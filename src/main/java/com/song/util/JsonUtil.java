package com.song.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Created by Song on 2020/10/05.
 */
public class JsonUtil {

    public static String toJsonString(Object object) {
        try {
            String value = new ObjectMapper().writeValueAsString(object);
            return value;
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return "";
        }
    }


}
