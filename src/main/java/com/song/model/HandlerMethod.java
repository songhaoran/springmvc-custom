package com.song.model;

import java.lang.reflect.Method;

/**
 * Created by Song on 2020/09/30.
 */
public class HandlerMethod {

    private Object controller;

    private Method method;

    public HandlerMethod(Object controller, Method method) {
        this.controller = controller;
        this.method = method;
    }

    public Object getController() {
        return controller;
    }

    public void setController(Object controller) {
        this.controller = controller;
    }

    public Method getMethod() {
        return method;
    }

    public void setMethod(Method method) {
        this.method = method;
    }
}
