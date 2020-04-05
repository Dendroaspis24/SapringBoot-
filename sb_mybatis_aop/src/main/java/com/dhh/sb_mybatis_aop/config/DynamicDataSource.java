package com.dhh.sb_mybatis_aop.config;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

/**
 * 1、定义一个动态数据源：继承AbstractRoutingDataSource 抽象类，并重写determineCurrentLookupKey（）方法
 * AbstractRoutingDataSource：这个类是实现多数据源的关键，他的作用就是动态切换数据源
 */
public class DynamicDataSource extends AbstractRoutingDataSource {
    @Override
    protected Object determineCurrentLookupKey() {
        return  DataSourceType.getDataBaseType();
    }
}
