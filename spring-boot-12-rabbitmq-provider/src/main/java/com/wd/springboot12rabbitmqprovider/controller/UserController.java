package com.wd.springboot12rabbitmqprovider.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.wd.springboot12rabbitmqprovider.entity.User;
import com.wd.springboot12rabbitmqprovider.entity.UserLog;
import com.wd.springboot12rabbitmqprovider.mapper.UserLogMapper;
import com.wd.springboot12rabbitmqprovider.mapper.UserMapper;
import com.wd.springboot12rabbitmqprovider.response.BaseResponse;
import com.wd.springboot12rabbitmqprovider.response.StatusCode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageBuilder;
import org.springframework.amqp.core.MessageDeliveryMode;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.AbstractJavaTypeMapper;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

/**
 * @author dwang
 * @create 2019-12-19 20:32
 */
@RestController
public class UserController {

    private static final Logger log= LoggerFactory.getLogger(UserController.class);

    private static final String Prefix="user";

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private UserLogMapper userLogMapper;

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Autowired
    private Environment env;

    @PostMapping(value = Prefix+"/login",consumes = "application/json")
    public BaseResponse login(@RequestBody User loginUser){
        BaseResponse response=new BaseResponse(StatusCode.Success);
        try {
            String userName = loginUser.getUserName();
            String password = loginUser.getPassword();
            //TODO：执行登录逻辑
            User user=userMapper.selectByUserNamePassword(userName,password);
            if (user!=null){
                //TODO：异步写用户日志
                try {
                    UserLog userLog=new UserLog(userName,"Login","login",objectMapper.writeValueAsString(user));
                    userLog.setCreateTime(new Date());
                    rabbitTemplate.setMessageConverter(new Jackson2JsonMessageConverter());
                    rabbitTemplate.setExchange(env.getProperty("log.user.exchange.name"));
                    rabbitTemplate.setRoutingKey(env.getProperty("log.user.routing.key.name"));

                    Message message= MessageBuilder.withBody(objectMapper.writeValueAsBytes(userLog)).setDeliveryMode(MessageDeliveryMode.NON_PERSISTENT).build();
                    message.getMessageProperties().setHeader(AbstractJavaTypeMapper.DEFAULT_CONTENT_CLASSID_FIELD_NAME, MessageProperties.CONTENT_TYPE_JSON);
                    rabbitTemplate.convertAndSend(message);
                }catch (Exception e){
                    e.printStackTrace();
                }

                //TODO：塞权限数据-资源数据-视野数据
            }else{
                response=new BaseResponse(StatusCode.Fail);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return response;
    }

}
