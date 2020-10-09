package com.song.adapter;

import com.song.anotation.ResponseBody;
import com.song.conversion.IntegerTypeHandler;
import com.song.conversion.StringTypeHandler;
import com.song.conversion.TypeHandler;
import com.song.model.HandlerMethod;
import com.song.response.MapResponseValueHandler;
import com.song.response.ResponseValueHandler;
import com.song.response.StringResponseValueHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * HandlerMethod对应的处理器适配器,执行HandlerMethod处理器
 */
public class RequestMappingHandlerAdapter implements HandlerAdapter {

    private Map<Class, TypeHandler> typeHandlers = new HashMap<>();
    private Map<Class, ResponseValueHandler> responseValueHandlers = new HashMap<>();

    public RequestMappingHandlerAdapter() {
        this.typeHandlers.put(String.class, new StringTypeHandler());
        this.typeHandlers.put(Integer.class, new IntegerTypeHandler());

        this.responseValueHandlers.put(String.class, new StringResponseValueHandler());
        this.responseValueHandlers.put(HashMap.class, new MapResponseValueHandler());
    }

    @Override
    public Object handleRequest(Object handler, HttpServletRequest req, HttpServletResponse resp) throws IOException, InvocationTargetException, IllegalAccessException {
        HandlerMethod handlerMethod = (HandlerMethod) handler;
        Object controller = handlerMethod.getController();
        Method method = handlerMethod.getMethod();

        // 获取请求参数
        Object[] args = getParameter(method, req);
        // 执行handler,得到返回数据
        Object returnValue = method.invoke(controller, args);
        // 处理写回返回数据
        handleReturnValue(returnValue, method, resp);

        return null;
    }

    private void handleReturnValue(Object returnValue, Method method, HttpServletResponse resp) throws IOException {
        if (method.isAnnotationPresent(ResponseBody.class)) {
            // 返回数据
            Class<?> returnValueClass = returnValue.getClass();
            ResponseValueHandler valueHandler = getResponseValueHandler(returnValueClass);
            if (valueHandler == null) {
                throw new RuntimeException("can not find response handler for " + returnValueClass.getName() + "!");
            }

            valueHandler.handleResp(resp, returnValue);
        } else {
            // 返回视图
        }
    }

    private ResponseValueHandler getResponseValueHandler(Class<?> clazz) {
        return responseValueHandlers.get(clazz);
    }

    private Object[] getParameter(Method method, HttpServletRequest req) {
        Map<String, String[]> parameterMap = req.getParameterMap();
        Parameter[] parameters = method.getParameters();

        List<Object> results = new ArrayList<>();
        for (Parameter parameter : parameters) {
            String name = parameter.getName();
            String[] valueArr = parameterMap.get(name);

            // 使用策略模式获取参数处理器
            TypeHandler typeHandler = getTypeHandler(parameter.getType());
            if (typeHandler == null) {
                continue;
            }
            // 获取参数值
            Object value = typeHandler.handleValue(valueArr);

            results.add(value);
        }
        return results.toArray();
    }

    private TypeHandler getTypeHandler(Class<?> type) {
        return typeHandlers.get(type);
    }

    @Override
    public boolean supports(Object handler) {
        return handler instanceof HandlerMethod;
    }
}
