package com.dhh.sb_mybatis_package.mapper.user;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;


@Mapper
public interface UserMapper {

    @Insert("insert into user (id,user_name,age) values(null,#{userName},#{age})")
    public int insertUser(@Param("userName") String userName, @Param("age") int age);
}
