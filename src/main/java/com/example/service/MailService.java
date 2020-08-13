package com.example.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class MailService {

//    private static String[] PRODUCT_SEND_TO_EMAIL = {"785613839@qq.com"};
//
//    private static String[] TEXT_SEND_TO_EMAIL = {"", "", "", ""};
//
//    @Autowired
//    private JavaMailSender mailSender;
//
//    @Value("${spring.mail.username}")
//    private String from;

    /**
     * 发送普通邮件
     *
     * @param product 是否为生产环境
     * @param subject 标题
     * @param content 内容
     */
    public void sendSimpleMail(boolean product, String subject, String content) {
//        SimpleMailMessage message = new SimpleMailMessage();
//        message.setFrom(from);
//        message.setTo(product ? PRODUCT_SEND_TO_EMAIL : TEXT_SEND_TO_EMAIL);
//        message.setSubject(subject);
//        message.setText(content);
//        try {
//            mailSender.send(message);
//        } catch (Exception e) {
//            log.info("异常邮件提醒发送异常:" + e);
//        }
    }
}
