package com.dhh.sb_mybatis_transaction;

import com.dhh.sb_mybatis_transaction.config.OrderDataConfig;
import com.dhh.sb_mybatis_transaction.config.UserDataConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties({OrderDataConfig.class, UserDataConfig.class})
public class SbMybatisTransactionApplication {

    public static void main(String[] args) {
        SpringApplication.run(SbMybatisTransactionApplication.class, args);
    }

}
