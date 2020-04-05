package com.dhh.sb_mybatis_aop;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan(basePackages = "com.dhh.sb_mybatis_aop.mapper")
public class SbMybatisAopApplication {

    public static void main(String[] args) {
        SpringApplication.run(SbMybatisAopApplication.class, args);
    }

}
