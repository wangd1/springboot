package com.wd.springboot12rabbitmqprovider.entity;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

/**
 * @author dwang
 * @create 2019-12-19 18:18
 */
public class OrderRecord {

    private Long id;

    private String orderNo;

    private String orderType;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date createTime;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date updateTime;

    public Long getId() {
        return id;
    }

    public OrderRecord setId(Long id) {
        this.id = id;
        return this;
    }

    public String getOrderNo() {
        return orderNo;
    }

    public OrderRecord setOrderNo(String orderNo) {
        this.orderNo = orderNo;
        return this;
    }

    public String getOrderType() {
        return orderType;
    }

    public OrderRecord setOrderType(String orderType) {
        this.orderType = orderType;
        return this;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public OrderRecord setCreateTime(Date createTime) {
        this.createTime = createTime;
        return this;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public OrderRecord setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
        return this;
    }

    @Override
    public String toString() {
        return "OrderRecord{" +
                "id=" + id +
                ", orderNo='" + orderNo + '\'' +
                ", orderType='" + orderType + '\'' +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                '}';
    }
}
