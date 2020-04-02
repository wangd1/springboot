package com.wd.springboot12rabbitmqprovider.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.rabbitmq.client.Channel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.listener.api.ChannelAwareMessageListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author dwang
 *
 * 消息处理确认返回需要实现ChannelAwareMessageListener，重写方法
 *
 * @create 2019-12-21 17:37
 */
@Component
public class UserOrderListener implements ChannelAwareMessageListener {

    private static final Logger log= LoggerFactory.getLogger(UserOrderListener.class);

    @Autowired
    private ObjectMapper objectMapper;

    //@Autowired
    //private ConcurrencyService concurrencyService;

    @Override
    public void onMessage(Message message, Channel channel) throws Exception {
        long tag=message.getMessageProperties().getDeliveryTag();
        try {
            byte[] body=message.getBody();
            /*UserOrderDto entity=objectMapper.readValue(body, UserOrderDto.class);
            log.info("用户商城抢单监听到消息： {} ",entity);

            UserOrder userOrder=new UserOrder();
            BeanUtils.copyProperties(entity,userOrder);
            userOrder.setStatus(1);
            userOrderMapper.insertSelective(userOrder);*/

            String mobile=new String(body,"UTF-8");
            log.info("监听到抢单手机号： {} ",mobile);
            //concurrencyService.manageRobbing(String.valueOf(mobile));
            //消息消费成功
            channel.basicAck(tag,true);
        }catch (Exception e){
            log.error("用户商城下单 发生异常：",e.fillInStackTrace());
            //消息消费失败
            channel.basicReject(tag,false);
        }
    }
}
