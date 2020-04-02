package com.wangd.springboot02config.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author dwang
 * @create 2019-08-16 21:42
 */
@RestController
public class HelloController {

    @GetMapping(value="/hello")
    public String hello(){
        return "hello";
    }
}
