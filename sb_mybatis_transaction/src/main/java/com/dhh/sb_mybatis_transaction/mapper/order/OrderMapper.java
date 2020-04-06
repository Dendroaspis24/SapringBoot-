package com.dhh.sb_mybatis_transaction.mapper.order;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.math.BigDecimal;

@Mapper
public interface OrderMapper {
    @Insert("insert into main_order (id,order_name,amount_total) values(null,#{orderName},#{amountTotal})")
    public int insertOrder(@Param("orderName") String orderName, @Param("amountTotal") BigDecimal amountTotal);
}
