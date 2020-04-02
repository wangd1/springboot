package com.wd.springboot10shiro.service;

import com.wd.springboot10shiro.dao.UserInfoDao;
import com.wd.springboot10shiro.entity.UserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author dwang
 * @create 2019-12-25 15:18
 */
@Service
public class UserInfoService {

    @Autowired
    private UserInfoDao mUserInfoDao;

    public UserInfo findByUserName(String userName){
        System.out.println("UserInfoServiceImpl.findByUsername()");
        return mUserInfoDao.findByUserName(userName);
    }

}
