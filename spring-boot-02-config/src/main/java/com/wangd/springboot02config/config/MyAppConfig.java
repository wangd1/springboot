package com.wangd.springboot02config.config;

import com.wangd.springboot02config.service.HelloService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Configuration 注解表明当前类是一个配置类，来代替之前的Spring的配置文件xml
 */
@Configuration
public class MyAppConfig {

    //@Bean：将方法的返回值添加到容器中，id就是方法名
    @Bean
    public HelloService helloService(){
        return new HelloService();
    }
}
