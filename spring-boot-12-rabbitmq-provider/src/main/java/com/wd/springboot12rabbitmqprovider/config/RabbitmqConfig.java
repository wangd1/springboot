package com.wd.springboot12rabbitmqprovider.config;

import com.wd.springboot12rabbitmqprovider.controller.UserOrderListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.config.SimpleRabbitListenerContainerFactory;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.amqp.SimpleRabbitListenerContainerFactoryConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;


/**
 * @author dwang
 * @create 2019-12-19 19:37
 */
@Configuration
public class RabbitmqConfig {

    private static final Logger log= LoggerFactory.getLogger(RabbitmqConfig.class);

    @Autowired
    private Environment env;

    @Autowired
    private CachingConnectionFactory connectionFactory;

    @Autowired
    private SimpleRabbitListenerContainerFactoryConfigurer factoryConfigurer;

    /**
     * 单一消费者
     * @return
     */
    @Bean(name = "singleListenerContainer")
    public SimpleRabbitListenerContainerFactory listenerContainer(){
        SimpleRabbitListenerContainerFactory factory = new SimpleRabbitListenerContainerFactory();
        System.out.println(connectionFactory);
        factory.setConnectionFactory(connectionFactory);
        //factory.setMessageConverter(new Jackson2JsonMessageConverter());
        factory.setConcurrentConsumers(1);
        factory.setMaxConcurrentConsumers(1);
        factory.setPrefetchCount(1);
        factory.setTxSize(1);
        factory.setAcknowledgeMode(AcknowledgeMode.AUTO);
        return factory;
    }

    /**
     * 多个消费者
     * @return
     */
    @Bean(name = "multiListenerContainer")
    public SimpleRabbitListenerContainerFactory multiListenerContainer(){
        SimpleRabbitListenerContainerFactory factory = new SimpleRabbitListenerContainerFactory();
        factoryConfigurer.configure(factory,connectionFactory);
        //factory.setMessageConverter(new Jackson2JsonMessageConverter());
        factory.setAcknowledgeMode(AcknowledgeMode.NONE);
        factory.setConcurrentConsumers(env.getProperty("spring.rabbitmq.listener.concurrency",int.class));
        factory.setMaxConcurrentConsumers(env.getProperty("spring.rabbitmq.listener.max-concurrency",int.class));
        factory.setPrefetchCount(env.getProperty("spring.rabbitmq.listener.prefetch",int.class));
        return factory;
    }

    @Bean
    public RabbitTemplate rabbitTemplate(){
        connectionFactory.setPublisherConfirms(true);
        connectionFactory.setPublisherReturns(true);
        RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMandatory(true);
        rabbitTemplate.setConfirmCallback(new RabbitTemplate.ConfirmCallback() {
            @Override
            public void confirm(CorrelationData correlationData, boolean ack, String cause) {
                log.info("消息发送成功:correlationData({}),ack({}),cause({})",correlationData,ack,cause);
            }
        });
        rabbitTemplate.setReturnCallback(new RabbitTemplate.ReturnCallback() {
            @Override
            public void returnedMessage(Message message, int replyCode, String replyText, String exchange, String routingKey) {
                log.info("消息丢失:exchange({}),route({}),replyCode({}),replyText({}),message:{}",exchange,routingKey,replyCode,replyText,message);
            }
        });
        return rabbitTemplate;
    }


    //TODO：基本消息模型构建

    @Bean
    public Queue basicQueue(){
        return new Queue(env.getProperty("basic.info.mq.queue.name"),true);
    }

    @Bean
    public DirectExchange basicExchange(){
        return new DirectExchange(env.getProperty("basic.info.mq.exchange.name"),true,false);
    }

    @Bean
    public Binding basicBinding(){
        return BindingBuilder.bind(basicQueue()).to(basicExchange()).with(env.getProperty("basic.info.mq.routing.key.name"));
    }

    //TODO：用户操作日志消息模型

    @Bean(name = "logUserQueue")
    public Queue logUserQueue(){
        return new Queue(env.getProperty("log.user.queue.name"),true);
    }

    @Bean
    public DirectExchange logUserExchange(){
        return new DirectExchange(env.getProperty("log.user.exchange.name"),true,false);
    }

    @Bean
    public Binding logUserBinding(){
        return BindingBuilder.bind(logUserQueue()).to(logUserExchange()).with(env.getProperty("log.user.routing.key.name"));
    }

    //TODO：发送邮件消息模型

    @Bean
    public Queue mailQueue(){
        return new Queue(env.getProperty("mail.queue.name"),true);
    }

    @Bean
    public DirectExchange mailExchange(){
        return new DirectExchange(env.getProperty("mail.exchange.name"),true,false);
    }

    @Bean
    public Binding mailBinding(){
        return BindingBuilder.bind(mailQueue()).to(mailExchange()).with(env.getProperty("mail.routing.key.name"));
    }


    //TODO：用户商城抢单消息模型

    @Bean("userOrderQueue")
    public Queue userOrderQueue(){
        return new Queue(env.getProperty("user.order.queue.name"),true);
    }

    @Bean
    public DirectExchange userOrderExchange(){
        return new DirectExchange(env.getProperty("user.order.exchange.name"),true,false);
    }

    @Bean
    public Binding userOrderBinding(){
        return BindingBuilder.bind(userOrderQueue()).to(userOrderExchange()).with(env.getProperty("user.order.routing.key.name"));
    }

    @Autowired
    private UserOrderListener mUserOrderListener;

    @Bean
    public SimpleMessageListenerContainer listenerContainerUserOrder(@Qualifier("userOrderQueue") Queue userOrderQueue){
        SimpleMessageListenerContainer container = new SimpleMessageListenerContainer();
        container.setConnectionFactory(connectionFactory);
        //container.setMessageConverter(new Jackson2JsonMessageConverter());

        // TODO 并发的配置
        container.setConcurrentConsumers(env.getProperty("spring.rabbitmq.listener.concurrency",Integer.class));
        container.setMaxConcurrentConsumers(env.getProperty("spring.rabbitmq.listener.max-concurrency",Integer.class));
        container.setPrefetchCount(env.getProperty("spring.rabbitmq.listener.prefetch",Integer.class));

        //TODO 消息确认机制
        container.setQueueNames(userOrderQueue.getName());
        container.setMessageListener(mUserOrderListener);
        container.setAcknowledgeMode(AcknowledgeMode.MANUAL);

        return container;
    }
}
