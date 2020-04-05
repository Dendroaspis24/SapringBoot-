package com.dhh.sb_mybatis_aop.config;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class DataSourceAop {
    @Before("execution(* com.dhh.sb_mybatis_aop.service.UserService.insertUser(..)))")
    public void setDataSource2test01() {
        System.err.println("user业务");
        DataSourceType.setDataBaseType(DataSourceType.DataBaseType.user);
    }

    @Before("execution(* com.dhh.sb_mybatis_aop.service.UserService.insertOrder(..))")
    public void setDataSource2test02() {
        System.err.println("order业务");
        DataSourceType.setDataBaseType(DataSourceType.DataBaseType.order);
    }

}
