package com.wd.springboot12rabbitmqprovider;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author dwang
 */
@SpringBootApplication
@MapperScan(basePackages = "com.wd.springboot12rabbitmqprovider.mapper")
public class SpringBoot12RabbitmqProviderApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringBoot12RabbitmqProviderApplication.class, args);
    }

}
