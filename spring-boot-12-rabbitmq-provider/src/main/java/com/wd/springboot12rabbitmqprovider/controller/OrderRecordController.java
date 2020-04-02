package com.wd.springboot12rabbitmqprovider.controller;

import com.wd.springboot12rabbitmqprovider.listener.event.PushOrderRecordEvent;
import com.wd.springboot12rabbitmqprovider.response.BaseResponse;
import com.wd.springboot12rabbitmqprovider.response.StatusCode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author dwang
 * @create 2019-12-19 19:13
 */
@RestController
public class OrderRecordController {

    private final static Logger logger = LoggerFactory.getLogger(OrderRecordController.class);

    private final static String PREFEX = "order";

    /**
     * Spring基于event的事件驱动模型其实就是RabbitMQ：“ 生产者->发送消息->队列->消费者监听消费处理 ” 这种简单的消息模型
     * 这个类似于RabbitMQ中的RabbitTemplate发送消息的
     */
    @Autowired
    private ApplicationEventPublisher mPublisher;

    @GetMapping(value=PREFEX+"/push")
    public BaseResponse pushOrder(){
        BaseResponse baseResponse = new BaseResponse(StatusCode.Success);
        try{
            PushOrderRecordEvent event = new PushOrderRecordEvent(this,"orderNo_1004", "sf2");
            mPublisher.publishEvent(event);
        }catch (Exception e){
            logger.info("下单发生异常，{}",e.fillInStackTrace());
        }
        return baseResponse;
    }

}
