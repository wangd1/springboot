package com.wd.springboot08jpa.dao;

import com.wd.springboot08jpa.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * @author dwang
 * @create 2019-12-16 14:40
 */
public interface UserDao extends JpaRepository<User,Long> {

    List<User> getUserByAddressEqualsAndIdLessThanEqual(String address, Integer id);

    @Query(value = "select * from user where id=(select max(id) from user)",nativeQuery = true)
    User maxIdUser();
}
