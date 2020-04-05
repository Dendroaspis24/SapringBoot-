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
@MapperScan(basePackages = "com.dhh.sb_mybatis_package.mapper.user",sqlSessionTemplateRef = "userSqlSessionTemplate")
public class UserDataSourceConfig {

    /**
     * 将user数据库注册容器中
     * @return
     */
    @Bean("userDataSource")
    @ConfigurationProperties("spring.datasource.user")
    public DataSource getUserDataSource(){
        return DataSourceBuilder.create().build();
    }

    /**
     * 获取对应的sqlsessionfactory对象
     * @param dataSource
     * @return
     * @throws Exception
     */
    @Bean("userSqlSessionFactory")
    public SqlSessionFactory getUserSqlSessionFactory(@Qualifier("userDataSource") DataSource dataSource) throws Exception {
        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        sqlSessionFactoryBean.setDataSource(dataSource);
        return sqlSessionFactoryBean.getObject();
    }

    /**
     * 创建用户管理器
     * @param dataSource
     * @return
     */

    @Bean("userDataSourceTransactionManager")
    public DataSourceTransactionManager getUserDataSourceTransactionManager(@Qualifier("userDataSource") DataSource dataSource){
        return new DataSourceTransactionManager(dataSource);
    }

    /**
     * 创建用户session模板
     * @param sqlSessionFactory
     * @return
     */
    @Bean("userSqlSessionTemplate")
    public SqlSessionTemplate getUserSqlSessionTemplate(@Qualifier("userSqlSessionFactory")SqlSessionFactory sqlSessionFactory){
        return new SqlSessionTemplate(sqlSessionFactory);
    }
}
