package com.wd.springboot12rabbitmqprovider.listener.event;

import org.springframework.context.ApplicationEvent;

/**
 * 类似于RabbitMQ中message
 * @author dwang
 * @create 2019-12-19 19:08
 */
public class PushOrderRecordEvent extends ApplicationEvent {

    private String orderNo;
    private String orderType;

    public PushOrderRecordEvent(Object source,String orderNo,String orderType) {
        super(source);
        this.orderNo=orderNo;
        this.orderType=orderType;
    }

    public String getOrderNo() {
        return orderNo;
    }

    public PushOrderRecordEvent setOrderNo(String orderNo) {
        this.orderNo = orderNo;
        return this;
    }

    public String getOrderType() {
        return orderType;
    }

    public PushOrderRecordEvent setOrderType(String orderType) {
        this.orderType = orderType;
        return this;
    }

    @Override
    public String toString() {
        return "PushOrderRecordEvent{" +
                "orderNo='" + orderNo + '\'' +
                ", orderType='" + orderType + '\'' +
                '}';
    }
}
