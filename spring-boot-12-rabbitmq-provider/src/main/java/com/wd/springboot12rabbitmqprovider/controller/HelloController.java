package com.wd.springboot12rabbitmqprovider.controller;

import com.wd.springboot12rabbitmqprovider.mapper.OrderRecordMapper;
import com.wd.springboot12rabbitmqprovider.response.BaseResponse;
import com.wd.springboot12rabbitmqprovider.response.StatusCode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author dwang
 * @create 2019-12-19 18:31
 */
@RestController
public class HelloController {

    private final static Logger log = LoggerFactory.getLogger(HelloController.class);

    private final static String PREFIX = "hello";

    @Autowired
    private OrderRecordMapper mOrderRecordMapper;

    @GetMapping(value=PREFIX+"/rabbitmq")
    public BaseResponse rabbitmq(){
        BaseResponse baseResponse = new BaseResponse(StatusCode.Success);
        String msg = "rabbitmq整合springboot";
        baseResponse.setData(msg);
        return baseResponse;
    }

    @GetMapping(value = PREFIX+"/list")
    public BaseResponse getList(){
        BaseResponse baseResponse = new BaseResponse(StatusCode.Success);
        baseResponse.setData(mOrderRecordMapper.selectAll());
        return baseResponse;
    }

    //@GetMapping(value = PREFIX+ "/save")
    //public BaseResponse save(){
    //
    //}

}
