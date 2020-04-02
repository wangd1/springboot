package com.wd.springboot12rabbitmqprovider.controller;

import com.wd.springboot12rabbitmqprovider.response.BaseResponse;
import com.wd.springboot12rabbitmqprovider.response.StatusCode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.MessageBuilder;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author dwang
 * @create 2019-12-20 16:21
 */
@RestController
public class MailController {

    private final static Logger logger = LoggerFactory.getLogger(MailController.class);
    private final static String PREFEX = "mail";

    @Autowired
    private Environment env;

    @Autowired
    private RabbitTemplate mRabbitTemplate;

    @PostMapping(value=PREFEX+"/send")
    public BaseResponse sendMail(){
        BaseResponse baseResponse = new BaseResponse(StatusCode.Success);
        try{
            mRabbitTemplate.setExchange(env.getProperty("mail.exchange.name"));
            mRabbitTemplate.setRoutingKey(env.getProperty("mail.routing.key.name"));
            String msg = "收藏 <a href='http://www.zzkkjx.com/video/?92110-3-31.html'>看庆余年全集</a> 可观看庆余年全集哦！！！";
            mRabbitTemplate.convertAndSend(MessageBuilder.withBody(msg.getBytes("UTF-8")).build());
        }catch (Exception e){
            e.printStackTrace();
        }
        return baseResponse;
    }

}
