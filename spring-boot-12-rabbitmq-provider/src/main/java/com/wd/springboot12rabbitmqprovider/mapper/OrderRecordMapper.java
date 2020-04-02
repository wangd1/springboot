package com.wd.springboot12rabbitmqprovider.mapper;

import com.wd.springboot12rabbitmqprovider.entity.OrderRecord;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

/**
 * @author dwang
 * @create 2019-12-19 18:19
 */
public interface OrderRecordMapper {

    @Delete("delete from order_record where id = #{id}")
    int deleteByPrimaryKey(Long id);

    @Insert("insert into order_record(order_no,order_type,create_time,update_time) values(#{orderNo},#{orderType},#{createTime},#{updateTime})")
    int insert(OrderRecord record);

    //@
    //int insertSelective(OrderRecord record);

    @Select("select id, order_no as orderNo, order_type as orderType, create_time as createTime, update_time as updateTime from order_record where id=#{id}")
    OrderRecord selectByPrimaryKey(Long id);

    @Update({"<script>",
            "update order_record ",
            "<if test=\"orderNo != null and orderNo != ''\">order_no = #{orderNo},</if>",
            "<if test=\"orderType != null and orderType != ''\">order_type = #{orderType},</if>",
            "<if test=\"createTime != null and createTime != ''\">createTime = #{createTime},</if>",
            "<if test=\"updateTime != null and updateTime != ''\">updateTime = #{updateTime},</if>",
          "</script>"})
    int updateByPrimaryKeySelective(OrderRecord record);

    @Update("update order_record " +
            "    set order_no = #{orderNo}, " +
            "      order_type = #{orderType}, " +
            "      create_time = #{createTime}, " +
            "      update_time = #{updateTime} " +
            "    where id = #{id}")
    int updateByPrimaryKey(OrderRecord record);

    @Select("select id, order_no as orderNo, order_type as orderType, create_time as createTime, update_time as updateTime from order_record")
    List<OrderRecord> selectAll();

}
