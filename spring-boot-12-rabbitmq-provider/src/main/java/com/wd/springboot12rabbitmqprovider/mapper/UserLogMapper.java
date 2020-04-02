package com.wd.springboot12rabbitmqprovider.mapper;

import com.wd.springboot12rabbitmqprovider.entity.UserLog;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

public interface UserLogMapper {


    String sql = "id, user_name userName, module, operation, data, create_time createTime, update_time updateTime ";

    @Delete("delete from user_log where id=#{id}")
    int deleteByPrimaryKey(Integer id);

    @Insert({
            "<script>",
            "insert into user_log(user_name, module,operation, data, create_time,update_time) ",
            "values(#{userName}, #{module},#{operation}, #{data}, #{createTime},#{updateTime})",
            "</script>"
    })
    int insert(UserLog record);

    //int insertSelective(UserLog record);

    @Select({
            "<script>",
            "select ",
            sql,
            "from user_log ",
            "where id=#{id}",
            "</script>"
    })
    UserLog selectByPrimaryKey(Long id);

    @Update({
            "<script>",
            "update user_log ",
            "<set>",
            " <if test=\"userName != null and userName != ''\" > user_name = #{userName},</if>",
            " <if test=\"module != null and module != ''\" > module = #{module},</if>",
            " <if test=\"operation != null and operation != ''\" > operation = #{operation},</if>",
            " <if test=\"data != null and data != ''\" > data = #{data},</if>",
            " <if test=\"createTime != null createTime != ''\" > create_time = #{createTime},</if>",
            " <if test=\"updateTime != null updateTime != ''\" > update_time = #{updateTime},</if>",
            "</set>",
            "where id = #{id}",
            "</script>"
    })
    int updateByPrimaryKeySelective(UserLog record);
}
