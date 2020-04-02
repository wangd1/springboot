package com.wd.springboot12rabbitmqprovider.listener;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.wd.springboot12rabbitmqprovider.entity.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

/**
 * @author dwang
 * @create 2019-12-19 19:58
 */
@Component
public class CommonListener {

    private static final Logger log= LoggerFactory.getLogger(CommonListener.class);
    @Autowired
    private ObjectMapper objectMapper;


    @RabbitListener(queues = "${basic.info.mq.queue.name}",containerFactory = "singleListenerContainer")
    public void comsumeMessage(@Payload byte[] message){
        try {
            // 接收消息
            //String result = new String(message,"UTF-8");
            //log.info("接收String消息：",result);
            User user=objectMapper.readValue(message, User.class);
            log.info("接收对象消息： {} ",user.toString());
        } catch (Exception e) {
            log.info("监听消费消息异常：{}",e.fillInStackTrace());
        }

    }

}
