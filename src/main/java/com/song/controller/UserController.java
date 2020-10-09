package com.song.controller;

import com.song.anotation.Controller;
import com.song.anotation.RequestMapping;
import com.song.anotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

/**
 *  @Controller 标记的类中的被@RequestMapping(或其他Mapping)标记的方法才是处理器
 *  HandlerMethod 才是注解方式开发的真正的处理器
 */
@Controller
@RequestMapping("/user")
public class UserController {

    @RequestMapping("/query")
    @ResponseBody
    public Map<String, Object> query(Integer id, String name) {
        Map<String, Object> map = new HashMap<>();
        map.put("id", id);
        map.put("name", name);

        return map;
    }

    @RequestMapping("save")
    @ResponseBody
    public String save(){
        return "添加成功";
    }
}
