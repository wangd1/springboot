package com.wd.springboot12rabbitmqprovider.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.wd.springboot12rabbitmqprovider.entity.User;
import com.wd.springboot12rabbitmqprovider.response.BaseResponse;
import com.wd.springboot12rabbitmqprovider.response.StatusCode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageBuilder;
import org.springframework.amqp.core.MessageDeliveryMode;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.*;

/**
 * @author dwang
 * @create 2019-12-19 19:46
 */
@RestController
public class RabbitController {

    private static final Logger log= LoggerFactory.getLogger(RabbitController.class);
    private static final String PREFEX="rabbit";

    @Autowired
    private Environment env;

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Autowired
    private ObjectMapper objectMapper;

    /**
     * 发送简单消息
     * @param msg
     * @return
     */
    @GetMapping(value=PREFEX+"/simple/msg/send")
    public BaseResponse sendSimpleMessage(@RequestParam String msg){
        BaseResponse baseResponse = new BaseResponse(StatusCode.Success);
        log.info("要发送的消息：{}",msg);
        try{
            rabbitTemplate.setMessageConverter(new Jackson2JsonMessageConverter());
            rabbitTemplate.setExchange(env.getProperty("basic.info.mq.exchange.name"));
            rabbitTemplate.setRoutingKey(env.getProperty("basic.info.mq.routing.key.name"));

            Message message=MessageBuilder.withBody(objectMapper.writeValueAsBytes(msg)).build();
            rabbitTemplate.convertAndSend(message);
        }catch (Exception e){
            log.error("发送简单消息发生异常： ",e.fillInStackTrace());
        }
        return baseResponse;
    }

    @PostMapping(value=PREFEX+"/object/msg/send",consumes = "application/json")
    public BaseResponse sendObjectMessage(@RequestBody User user){
        BaseResponse baseResponse = new BaseResponse(StatusCode.Success);
        log.info("要发送的消息：{}",user.toString());
        try{
            rabbitTemplate.setMessageConverter(new Jackson2JsonMessageConverter());
            rabbitTemplate.setExchange(env.getProperty("basic.info.mq.exchange.name"));
            rabbitTemplate.setRoutingKey(env.getProperty("basic.info.mq.routing.key.name"));

            Message msg = MessageBuilder.withBody(objectMapper.writeValueAsBytes(user)).setDeliveryMode(MessageDeliveryMode.NON_PERSISTENT)
                    .build();
            rabbitTemplate.convertAndSend(msg);
        }catch (Exception e){
            log.error("发送对象消息发生异常： ",e.fillInStackTrace());
        }
        return baseResponse;
    }



}
