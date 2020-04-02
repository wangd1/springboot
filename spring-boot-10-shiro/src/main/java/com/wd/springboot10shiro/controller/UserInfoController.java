package com.wd.springboot10shiro.controller;

import com.wd.springboot10shiro.service.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author dwang
 * @create 2019-12-25 15:27
 */
@Controller
@RequestMapping("/userInfo")
public class UserInfoController {

    @Autowired
    private UserInfoService mUserInfoService;

    @RequestMapping("/userList")
    public String list(){
        return "list";
    }

    @RequestMapping("/userAdd")
    public String add(){
        return "add";
    }

    @RequestMapping("/userDel")
    public String del(){
        return "del";
    }

}
