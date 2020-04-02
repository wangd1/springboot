package com.wangd.springboot;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;


@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class SpringBoot03LoggingApplicationTests {

    @Test
    public void contextLoads() {

        //日记级别：
        //从低到高：trace<debug<info<warn>error
        //可以调整输出的日志级别；日志就只在这个级别和之后的高级别生效
        log.trace("这是trace日志");
        log.debug("这是debug日志");  //调试日志
        //springboot默认的日志级别是info,所以运行时只会打印info和级别高于info的日志信息
        log.info("这是info日志");  //
        log.warn("这是warn日志");  //警告日志
        log.error("这是error日志"); //错误日志
    }

}
