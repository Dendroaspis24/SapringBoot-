package com.dhh.sb_mail_server.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.mail.internet.MimeMessage;
import java.io.File;

@RestController
public class EmailController {

    @Value("${spring.mail.username}")
    private String mailUserName;

    @Autowired
    private JavaMailSender javaMailSender;

    @RequestMapping("/sendEmail/{address}")
    public String sendEmail(@PathVariable("address") String address) {

        try {
            //建立邮件消息
            SimpleMailMessage mainMessage = new SimpleMailMessage();
            //发送者邮箱
            mainMessage.setFrom(mailUserName);
            //接收者邮箱
            mainMessage.setTo(address);
            //发送的邮件标题
            mainMessage.setSubject("Springboot发送邮件");
            //发送的内容
            mainMessage.setText("邮件内容");
            System.out.println("123");
            //发送
            javaMailSender.send(mainMessage);
            return "发送成功";
        } catch (Exception e) {
            return "发送失败";

        }
    }

    //添加附件发送
    @RequestMapping("/sendEmailAttachment/{address}")
    public String sends(@PathVariable("address") String address) {
        try {
            //建立邮件消息
            MimeMessage mainMessage = javaMailSender.createMimeMessage();
            //用MimeMessageHelper组装复杂邮件，第二个参数为true，可以发送附件
            MimeMessageHelper helper = new MimeMessageHelper(mainMessage, true);
            //发送者邮箱
            helper.setFrom(mailUserName);
            //接收者邮箱
            helper.setTo(address);
            //发送的邮件标题
            helper.setSubject("Springboot发送邮件");
            //发送的内容
            helper.setText("邮件内容");
            //添加附件
            helper.addAttachment("ATM.pptx", new File("E:\\HTML\\ATM.pptx"));
            //发送
            javaMailSender.send(mainMessage);
            return "发送成功";
        } catch (Exception e) {
            return "发送失败";
        }

    }
}
