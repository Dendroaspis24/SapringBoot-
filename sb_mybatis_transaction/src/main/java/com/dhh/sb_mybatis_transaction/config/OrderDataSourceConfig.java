package com.dhh.sb_mybatis_transaction.config;


import com.atomikos.jdbc.AtomikosDataSourceBean;
import com.mysql.cj.jdbc.MysqlXADataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;
import java.sql.SQLException;

@Configuration
// 配置mybatis的接口类放的地方
@MapperScan(basePackages = "com.dhh.sb_mybatis_transaction.mapper.order",sqlSessionTemplateRef = "OrderSqlSessionTemplate")
public class OrderDataSourceConfig {

    /**
     * 将Order数据库注册容器中
     * @return
     */
    @Bean("OrderDataSource")
    //@ConfigurationProperties("spring.datasource.order") 此处不需要读取配置数据了 直接添加配置类orderDataConfig读取数据
    public DataSource getOrderDataSource(OrderDataConfig orderDataConfig) throws SQLException {
        //1.创建我们的xaDataSource
        MysqlXADataSource mysqlXADataSource = new MysqlXADataSource();
        mysqlXADataSource.setUrl(orderDataConfig.getUrl());
        mysqlXADataSource.setPinGlobalTxToPhysicalConnection(true);
        mysqlXADataSource.setPassword(orderDataConfig.getPassWord());
        mysqlXADataSource.setUser(orderDataConfig.getUserName());

        //2.注册到全局事务中
        AtomikosDataSourceBean xaDataSource = new AtomikosDataSourceBean();
        xaDataSource.setXaDataSource(mysqlXADataSource);
        xaDataSource.setUniqueResourceName("orderDataBase");

        xaDataSource.setMinPoolSize(orderDataConfig.getMinPoolSize());
        xaDataSource.setMaxPoolSize(orderDataConfig.getMaxPoolSize());
        xaDataSource.setMaxLifetime(orderDataConfig.getMaxLifetime());
        xaDataSource.setBorrowConnectionTimeout(orderDataConfig.getBorrowConnectionTimeout());
        xaDataSource.setLoginTimeout(orderDataConfig.getLoginTimeout());
        xaDataSource.setMaintenanceInterval(orderDataConfig.getMaintenanceInterval());
        xaDataSource.setMaxIdleTime(orderDataConfig.getMaxIdleTime());
        xaDataSource.setTestQuery(orderDataConfig.getTestQuery());

        return xaDataSource;

    }

    /**
     *    表示这个数据源是默认数据源（设置默认数据源）
     * @param dataSource
     * @return
     * @throws Exception
     */
    @Bean("OrderSqlSessionFactory")
    public SqlSessionFactory getOrderSqlSessionFactory(@Qualifier("OrderDataSource") DataSource dataSource) throws Exception {
        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        sqlSessionFactoryBean.setDataSource(dataSource);
        //若mybatis使用的xml开发，不是注解开发，需要定位mybatis的xml文件所在地址
        //sqlSessionFactoryBean.setMapperLocations(new PathMatchingResourcePatternResolver().getResource("classpath*:mapping/test01/*.xml"));
        return sqlSessionFactoryBean.getObject();
    }

/*
    *//**
     * 创建用户管理器  多数据源分布式事务 不需要每个数据源创建自己的用户管理器，所以这段代码不需要
     * @param dataSource
     * @return
     *//*

    @Bean("OrderDataSourceTransactionManager")
    public DataSourceTransactionManager getOrderDataSourceTransactionManager(@Qualifier("OrderDataSource") DataSource dataSource){
        return new DataSourceTransactionManager(dataSource);
    }*/

    /**
     * 创建用户session模板
     * @param sqlSessionFactory
     * @return
     */
    @Bean("OrderSqlSessionTemplate")
    public SqlSessionTemplate getOrderSqlSessionTemplate(@Qualifier("OrderSqlSessionFactory")SqlSessionFactory sqlSessionFactory){
        return new SqlSessionTemplate(sqlSessionFactory);
    }
}
