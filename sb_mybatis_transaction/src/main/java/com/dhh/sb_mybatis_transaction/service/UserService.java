package com.dhh.sb_mybatis_transaction.service;

import com.dhh.sb_mybatis_transaction.mapper.order.OrderMapper;
import com.dhh.sb_mybatis_transaction.mapper.user.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
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

    @RequestMapping("/orderAndUser")
    @Transactional
    public String orderAndUser(String name,int age){
        //操作用户数据库
        userMapper.insertUser(name, age);
        //操作订单数据库
        orderMapper.insertOrder(name, new BigDecimal(19));
        int a = 1/0;
        return "success";
    }
}
