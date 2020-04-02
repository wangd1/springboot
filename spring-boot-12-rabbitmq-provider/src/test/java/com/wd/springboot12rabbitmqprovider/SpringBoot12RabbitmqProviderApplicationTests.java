package com.wd.springboot12rabbitmqprovider;

import com.wd.springboot12rabbitmqprovider.mapper.OrderRecordMapper;
import com.wd.springboot12rabbitmqprovider.service.MailServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class SpringBoot12RabbitmqProviderApplicationTests {


    @Autowired
    private OrderRecordMapper mRecordMapper;

    @Autowired
    private MailServiceImpl mMailService;

    @Test
    void contextLoads() {

        //OrderRecord orderRecord = new OrderRecord();
        //orderRecord.setOrderNo("1003");
        //orderRecord.setOrderType("bs");
        //orderRecord.setCreateTime(new Date());
        //orderRecord.setUpdateTime(new Date());
        //mRecordMapper.insert(orderRecord);

        mMailService.sendHtmlMail("wangdi1208i@gmail.com", "hello","<h1>1122你好啊</h1>");
    }

}
