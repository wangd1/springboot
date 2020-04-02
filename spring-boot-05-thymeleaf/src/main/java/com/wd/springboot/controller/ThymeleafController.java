package com.wd.springboot.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Map;

/**
 * @author dwang
 * @create 2019-08-20 22:34
 */

@Controller
public class ThymeleafController {

    @GetMapping("/hello")
    public String hello(Map<String,Object> map){

        map.put("hello","hello thymeleaf");
        map.put("id","aaa");

        return "welcome";
    }

}
