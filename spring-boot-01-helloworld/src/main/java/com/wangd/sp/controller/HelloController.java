package com.wangd.sp.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author dwang
 * @create 2019-08-14 21:45
 */
@RestController
public class HelloController {

    //@GetMapping("/hello")
    @RequestMapping(method = RequestMethod.GET)
    public String hello(){
        return "hello world";
    }
}
