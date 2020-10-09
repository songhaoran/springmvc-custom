package com.song.conversion;

/**
 * Created by Song on 2020/10/05.
 */
public class StringTypeHandler implements TypeHandler {
    @Override
    public Object handleValue(String[] value) {
        if (value == null || value.length == 0) {
            return null;
        }
        return value[0];
    }
}
