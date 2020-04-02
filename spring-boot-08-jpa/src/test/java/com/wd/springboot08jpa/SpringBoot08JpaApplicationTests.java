package com.wd.springboot08jpa;
import com.wd.springboot08jpa.dao.UserDao;
import com.wd.springboot08jpa.dao2.UserDao2;
import com.wd.springboot08jpa.entity.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class SpringBoot08JpaApplicationTests {

    @Autowired
    private UserDao mUserDao;
    @Autowired
    private UserDao2 mUserDao2;

    @Test
    void contextLoads() {

        User user = new User();
        user.setUsername("carba").setAddress("baohequ");
        //mUserDao.save(user);
        //System.out.println(user);
        //user.setId(4L);
        //mUserDao.delete(user);
        User maxUser = mUserDao2.maxIdUser();
        System.out.println(maxUser);
    }

}
