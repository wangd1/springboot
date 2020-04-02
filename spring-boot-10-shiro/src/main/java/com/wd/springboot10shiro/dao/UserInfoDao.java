package com.wd.springboot10shiro.dao;

import com.wd.springboot10shiro.entity.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.annotation.Resource;

/**
 * @author dwang
 * @create 2019-12-25 15:18
 */
@Resource
public interface UserInfoDao extends JpaRepository<UserInfo,Long> {

    UserInfo findByUserName(String userName);

}
