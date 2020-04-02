package com.wd.springboot12rabbitmqprovider.listener;

import com.wd.springboot12rabbitmqprovider.entity.OrderRecord;
import com.wd.springboot12rabbitmqprovider.listener.event.PushOrderRecordEvent;
import com.wd.springboot12rabbitmqprovider.mapper.OrderRecordMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * 类似于RabbitMQ中的监听器
 * @author dwang
 * @create 2019-12-19 19:10
 */
@Component
public class PushOrderRecordListener implements ApplicationListener<PushOrderRecordEvent> {

    private final static Logger logger = LoggerFactory.getLogger(PushOrderRecordListener.class);

    @Autowired
    private OrderRecordMapper mRecordMapper;

    @Override
    public void onApplicationEvent(PushOrderRecordEvent pushOrderRecordEvent) {
        logger.info("监听到的下单记录: {}",pushOrderRecordEvent);
        try{
            OrderRecord record = new OrderRecord();
            BeanUtils.copyProperties(pushOrderRecordEvent, record);
            record.setUpdateTime(new Date());
            record.setCreateTime(new Date());
            mRecordMapper.insert(record);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
