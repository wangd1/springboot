package com.spring;

import com.spring.service.MailService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MailServiceTest {

    @Autowired
    private MailService mailService;

    @Autowired
    private TemplateEngine templateEngine;

    //测试普通邮件
    @Test
    public void sendSimpleMail() throws Exception {
        //mailService.sendSimpleMail("1814380567@qq.com","这是一个测试邮件","https://www.baidu.com");11111111
    }

    //html邮件
    @Test
    public void sendHtmlMail() throws Exception{
        //mailService.sendHtmlMail("1814380567@qq.com","这是一个测试HTML邮件","<h3>https://www.baidu.com</h3>");
    }

    //附件邮件
    @Test
    public void sendAttachmentsMail() {
        //String filePath="f:\\13117000158B.xlsx";
        //mailService.sendAttachmentsMail("1814380567@qq.com", "主题：带附件的邮件", "有附件，请查收！", filePath);
    }

    //静态资源邮件
    @Test
    public void sendInlineResourceMail() {
        String rscId = "neo006";
        String content="<html><body>这是有图片的邮件：<img src=\'cid:" + rscId + "\' width=200 height=300 ></body></html>";
        String imgPath = "f:\\20170831.jpg";

       // mailService.sendInlineResourceMail("1814380567@qq.com", "主题：这是有图片的邮件", content, imgPath, rscId);
    }

    //模板邮件
    @Test
    public void sendTemplateMail() {
        //创建邮件正文
        Context context = new Context();
        context.setVariable("id", "006");
        String emailContent = templateEngine.process("emailTemplate", context);

        mailService.sendHtmlMail("1814380567@qq.com","账号激活",emailContent);
    }
}
