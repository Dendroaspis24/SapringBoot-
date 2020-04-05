package com.dhh.sb_mybatis_package.config;


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

@Configuration
// 配置mybatis的接口类放的地方
@MapperScan(basePackages = "com.dhh.sb_mybatis_package.mapper.order",sqlSessionTemplateRef = "OrderSqlSessionTemplate")
public class OrderDataSourceConfig {

    /**
     * 将Order数据库注册容器中
     * @return
     */
    @Bean("OrderDataSource")
    @ConfigurationProperties("spring.datasource.order")
    public DataSource getOrderDataSource(){
        return DataSourceBuilder.create().build();
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

    /**
     * 创建用户管理器
     * @param dataSource
     * @return
     */

    @Bean("OrderDataSourceTransactionManager")
    public DataSourceTransactionManager getOrderDataSourceTransactionManager(@Qualifier("OrderDataSource") DataSource dataSource){
        return new DataSourceTransactionManager(dataSource);
    }

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
