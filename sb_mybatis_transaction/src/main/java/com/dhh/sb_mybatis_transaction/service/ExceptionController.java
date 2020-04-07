package com.dhh.sb_mybatis_transaction.service;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

/**
 * springBoot整合全局捕获异常
 */
@ControllerAdvice//该注解实际上就是一个异常的切面类
//只要请求出现异常都会进入该类，执行该类的方法
public class ExceptionController {

    //拦截指定的异常注解
    @ExceptionHandler(RuntimeException.class)
    //返回json字符串
    @ResponseBody
    public Map<String, String> exceptionHandler() {
        Map<String, String> restMap = new HashMap<>();
        restMap.put("code", "500");
        restMap.put("message", "系统错误");
        return restMap;
    }


}
