package com.wd.springboot07mybatis.mapper2;

import com.wd.springboot07mybatis.entity.User;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface UserMapper2 {

    @Select("select * from user")
    List<User> getAll();

    @Results({
            @Result(property = "id", column = "id"),
            @Result(property = "username" , column = "u"),
            @Result(property = "address" , column = "a")
    })

    @Select("select username as u,address as a, id as id from user where id=#{id}")
    User get(Long id);

    @Select("select * from user where username like concat('%',#{name},'%')")
    List<User> getByName(String name);

    @Insert({"insert into user(username,address) values(#{username},#{address})"})
    @SelectKey(statement = "select last_insert_id()", keyProperty = "id", before = false, resultType = Long.class)
    Integer add(User user);

    @Update("update user set username=#{username},address=#{address} where id=#{id}")
    Integer updateById(User user);

    @Delete("delete from user where id=#{id}")
    Integer deleteById(Long id);

}
