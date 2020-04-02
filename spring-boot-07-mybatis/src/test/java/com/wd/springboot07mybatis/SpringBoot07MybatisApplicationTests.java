package com.wd.springboot07mybatis;

import com.wd.springboot07mybatis.entity.User;
import com.wd.springboot07mybatis.mapper2.UserMapper2;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class SpringBoot07MybatisApplicationTests {

    @Autowired
    private UserMapper2 userMapper2;

    @Test
    void contextLoads() {
        User user = new User();
        user.setUsername("wangd1").setAddress("安徽合肥");
        Integer isInsert = userMapper2.add(user);
        System.out.println(isInsert);
        System.out.println(user);


        user.setId(1L);
        //userMapper.updateById(user);
        //userMapper.deleteById(2L);
        //User currUser = userMapper.get(1L);
        //System.out.println(currUser);
        //List<User> userList = userMapper.getAll();
        //System.out.println(userList.size());
    }

}
