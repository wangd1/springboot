package com.wd.springboot12rabbitmqprovider.mapper;

import com.wd.springboot12rabbitmqprovider.entity.User;
import org.apache.ibatis.annotations.*;

public interface UserMapper {
    @Delete("delete from user where id=#{id}")
    int deleteByPrimaryKey(Integer id);

    @Insert({
            "<script>",
            "insert into user(user_name, password,sex, is_active, create_time,update_time) ",
            "values(#{userName}, #{password},{sex}, #{isActive}, #{createTime},#{updateTime})",
            "</script>"
    })
    int insert(User record);

    //@Insert({
    //        "<script>",
    //        "insert into user(id, user_name, password,sex, is_active, create_time,update_time) ",
    //        "values(#{id}, #{userName}, #{password},{sex}, #{isActive}, #{createTime},#{updateTime})",
    //        "</script>"
    //})
    //int insertSelective(User record);

    @Select({
            "<script>",
            "select ",
            "id, user_name userName, password, sex, is_active isAcive, create_time createTime, update_time updateTime ",
            "from user ",
            "where id=#{id}",
            "</script>"
    })
    User selectByPrimaryKey(Long id);


    @Update({
            "<script>",
            "update user ",
            "<set>",
            " <if test=\"userName != null and userName != ''\" > user_name = #{userName},</if>",
            " <if test=\"password != null and password != ''\" > password = #{password},</if>",
            " <if test=\"sex != null and sex != ''\" > sex = #{sex},</if>",
            " <if test=\"isActive != null and isActive != ''\" > is_active = #{isActive},</if>",
            " <if test=\"createTime != null createTime != ''\" > create_time = #{createTime},</if>",
            " <if test=\"updateTime != null updateTime != ''\" > update_time = #{updateTime},</if>",
            "</set>",
            "where id = #{id}",
            "</script>"
    })
    int updateByPrimaryKeySelective(User record);

    @Update({
            "<script>",
            "update user ",
            "set user_name = #{userName},password = #{password},sex = #{sex},is_active = #{isActive},",
            "create_time = #{createTime},update_time = #{updateTime} ",
            "where id = #{id}",
            "</script>"
    })
    int updateByPrimaryKey(User record);

    @Select({
            "<script>",
            "select ",
            "id, user_name userName, password, sex, is_active isAcive, create_time createTime, update_time updateTime ",
            "from user ",
            "where user_name=#{userName} and password=#{password} and is_active=1",
            "</script>"
    })
    User selectByUserNamePassword(@Param("userName") String userName, @Param("password") String password);
}
