package com.ftl.springboot06freemark.controller;

import com.ftl.springboot06freemark.entity.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;

/**
 * @author dwang
 * @create 2019-10-18 15:47
 */
@Controller
@RequestMapping(value = "/f")
public class UserController {

    @GetMapping("/index")
    public String index(Model model){

        List<User> users = new ArrayList<>();
        for(int i=0;i<10;i++){
            User user = new User();
            user.setId((long)i);
            user.setUsername("wangdi>>>>>"+i);
            user.setAddress("wangd1.top>>>>"+i);
            users.add(user);
        }
        model.addAttribute("users", users);
        return "idx";
    }

}
