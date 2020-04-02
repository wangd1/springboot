package com.wangd.springboot02config;

import com.wangd.springboot02config.bean.Person;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringBoot02ConfigApplicationTests {

    @Autowired
    Person mPerson;

    @Autowired
    ApplicationContext ioc;


    @Test
    public void testHelloService(){
        System.out.println(ioc.containsBean("helloService"));
    }

    @Test
    public void contextLoads() {
        System.out.println(mPerson);
    }

}
