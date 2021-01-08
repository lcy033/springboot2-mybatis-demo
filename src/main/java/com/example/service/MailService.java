package com.example.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;

@Slf4j
@Service
public class MailService {

    private static String[] PRODUCT_SEND_TO_EMAIL = {"lichunyue@aixuexi.com"};

    private static String[] TEXT_SEND_TO_EMAIL = {"", "", "", ""};

    @Autowired
    private JavaMailSender mailSender;

    @Value("${spring.mail.address}")
    private String from;

    /**
     * 发送普通邮件
     *
     * @param product 是否为生产环境
     * @param subject 标题
     * @param content 内容
     */
    public void sendSimpleMail(boolean product, String subject, String content) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(from);
        message.setTo(PRODUCT_SEND_TO_EMAIL);
        message.setSubject(subject);
        message.setText(content);
        try {
            mailSender.send(message);
        } catch (Exception e) {
            log.info("异常邮件提醒发送异常:" + e);
        }
    }

    public static void main(String[] args) {
        ClassLoader classLoader = ClassLoader.getSystemClassLoader();
        System.out.println(classLoader);//AppClassLoader
        ClassLoader classLoader1 = classLoader.getParent();
        System.out.println(classLoader1);//ExtClassLoader
        ClassLoader classLoader2 = classLoader1.getParent();
        System.out.println(classLoader2);//null
    }
}
