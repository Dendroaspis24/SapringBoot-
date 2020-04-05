package com.dhh.sb_mybatis_package;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan(basePackages = "com.dhh.sb_mybatis_package.mapper")
public class SbMybatisPackageApplication {

    public static void main(String[] args) {
        SpringApplication.run(SbMybatisPackageApplication.class, args);
    }

}
