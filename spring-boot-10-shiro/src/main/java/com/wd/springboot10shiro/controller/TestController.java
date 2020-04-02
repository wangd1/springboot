package com.wd.springboot10shiro.controller;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.config.IniSecurityManagerFactory;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.Factory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author dwang
 * @create 2019-12-25 14:33
 */
@RestController
public class TestController {

    @GetMapping("/testHelloWorld")
    public String testHelloWorld(){

        //1、获取SecurityManager工厂，此处使用Ini配置文件初始化SecurityManager
        Factory<SecurityManager> factory = new IniSecurityManagerFactory("classpath:shiro.ini");
        //2、得到SecurityManager实例 并绑定给SecurityUtils
        SecurityManager securityManager = factory.getInstance();
        //3、得到Subject及创建用户名/密码身份验证Token（即用户身份/凭证）
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken("wang", "123");
        try {
            //4、登录，即身份验证
            subject.login(token);
        }catch (AuthenticationException e){
            //5、身份验证失败
        }
        //断言用户已经登录
        System.out.println(subject.isAuthenticated());
        //6、退出
        subject.logout();
        return null;
    }

}
