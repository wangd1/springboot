package com.wd.springboot11session.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

/**
 * @author dwang
 * @create 2019-12-16 16:39
 */
@RestController
public class HelloController {

    @Value("${server.port}")
    Integer port;

    @GetMapping("/set")
    public String set(HttpSession session){
        session.setAttribute("username", "wangdi");
        return String.valueOf(port);
    }

    @GetMapping("/get")
    public String get(HttpSession session){
        return session.getAttribute("username")+","+port;
    }

}
