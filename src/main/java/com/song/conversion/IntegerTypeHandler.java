package com.song.conversion;

/**
 * Created by Song on 2020/10/05.
 */
public class IntegerTypeHandler implements TypeHandler {
    @Override
    public Object handleValue(String[] value) {
        if (value == null || value.length == 0) {
            return null;
        }
        return Integer.parseInt(value[0].toString());
    }
}
