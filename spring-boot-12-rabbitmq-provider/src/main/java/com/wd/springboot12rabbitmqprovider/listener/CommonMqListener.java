package com.wd.springboot12rabbitmqprovider.listener;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.wd.springboot12rabbitmqprovider.entity.UserLog;
import com.wd.springboot12rabbitmqprovider.mapper.UserLogMapper;
import com.wd.springboot12rabbitmqprovider.service.MailServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * @author dwang
 * @create 2019-12-19 21:16
 */
@Component
public class CommonMqListener {

    private static final Logger log= LoggerFactory.getLogger(CommonMqListener.class);

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private UserLogMapper userLogMapper;
    @Autowired
    private MailServiceImpl mMailService;

    /**
     * 监听消费用户日志
     * @param message
     */
    @RabbitListener(queues = "${log.user.queue.name}",containerFactory = "singleListenerContainer")
    public void consumeUserLogQueue(@Payload byte[] message){
        try {
            UserLog userLog=objectMapper.readValue(message, UserLog.class);
            log.info("监听消费用户日志 监听到消息： {} ",userLog);
            userLog.setCreateTime(new Date());
            userLog.setUpdateTime(new Date());
            //TODO：记录日志入数据表
            userLogMapper.insert(userLog);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @RabbitListener(queues = "${mail.queue.name}",containerFactory = "singleListenerContainer")
    public void consumerMailQueue(@Payload byte[] message){
        try{
            String msg = new String(message,"UTF-8");
            log.info("监听消费邮件发送 监听到消息： {} ",msg);
            //TODO：记录日志入数据表
            //1640611853@qq.com
            mMailService.sendHtmlMail("wangdi1208i@gmail.com", "监听邮件发送(测试)", msg);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
