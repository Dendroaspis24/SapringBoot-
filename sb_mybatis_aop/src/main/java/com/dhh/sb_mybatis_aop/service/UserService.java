package com.dhh.sb_mybatis_aop.service;

import com.dhh.sb_mybatis_aop.mapper.order.OrderMapper;
import com.dhh.sb_mybatis_aop.mapper.user.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

@RestController
public class UserService {
    @Autowired
    private UserMapper userMapper;

    @Autowired
    private OrderMapper orderMapper;

    @RequestMapping("/insertUser")
    public String insertUser(String userName, int age) {
        return userMapper.insertUser(userName, age) > 0 ? "success" : "false";
    }

    @RequestMapping("/insertOrder")
    public String insertOrder(String orderName, BigDecimal amount) {
        return orderMapper.insertOrder(orderName, amount) > 0 ? "success" : "false";
    }
}
