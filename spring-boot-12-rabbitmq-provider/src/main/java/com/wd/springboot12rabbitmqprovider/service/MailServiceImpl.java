package com.wd.springboot12rabbitmqprovider.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.File;

/**
 * @author dwang
 * @create 2019-12-20 10:07
 */
@Service
public class MailServiceImpl implements MailService {

    private final Logger logger = LoggerFactory.getLogger(MailServiceImpl.class);
    /**
     * 使用@Value注入application.properties中指定的用户名
     */
    @Value("${spring.mail.username}")
    private String from;

    /**
     * 用于发送文件
     */
    @Autowired
    private JavaMailSender mailSender;

    @Override
    public void sendSimpleMail(String to, String subject, String content) {
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setFrom(from);
        mailMessage.setTo(to);
        mailMessage.setSubject(subject);
        mailMessage.setText(content);
        mailSender.send(mailMessage);
    }

    @Override
    public void sendHtmlMail(String to, String subject, String content) {
        logger.info("发送HTML邮件开始：{},{},{}", to, subject, content);
        //使用MimeMessage，MIME协议
        MimeMessage message = mailSender.createMimeMessage();

        MimeMessageHelper helper;
        //MimeMessageHelper帮助我们设置更丰富的内容
        try {
            helper = new MimeMessageHelper(message, true);
            helper.setFrom(from);
            helper.setTo(to);
            helper.setSubject(subject);
            /**
             * //true代表支持html
             */
            helper.setText(content, true);
            mailSender.send(message);
            logger.info("发送HTML邮件成功");
        } catch (MessagingException e) {
            logger.error("发送HTML邮件失败：{}", e.fillInStackTrace());
        }
    }

    @Override
    public void sendAttachmentMail(String to, String subject, String content, String filePath) {
        logger.info("开始发送附件邮件");
        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper;
        try{
            helper = new MimeMessageHelper(message, true);
            helper.setFrom(from);
            helper.setTo(to);
            helper.setSubject(subject);
            /**
             * //true代表支持html
             */
            helper.setText(content, true);
            FileSystemResource file = new FileSystemResource(new File(filePath));
            String fileName = file.getFilename();
            //添加附件，可添加多个
            helper.addAttachment(fileName,file);
            mailSender.send(message);
            logger.info("发送附件邮件成功");
        }catch (Exception e){
            logger.error("发送附件邮件失败：{}", e.fillInStackTrace());
        }
    }

    @Override
    public void sendInlineResourceMail(String to, String subject, String content, String rscPath, String rscId) {
        logger.info("开始发送图片邮件");
        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper;
        try{
            helper = new MimeMessageHelper(message, true);
            helper.setFrom(from);
            helper.setTo(to);
            helper.setSubject(subject);
            /**
             * //true代表支持html
             */
            helper.setText(content, true);
            FileSystemResource res = new FileSystemResource(new File(rscPath));
            //可重复使用添加多个图片
            helper.addInline(rscId, res);
            mailSender.send(message);
            logger.info("发送图片邮件成功");
        }catch (Exception e){
            logger.error("发送图片邮件失败：{}", e.fillInStackTrace());
        }
    }
}
