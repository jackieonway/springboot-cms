package com.pengzu;

import com.pengzu.utils.EmailUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.File;

@RunWith(SpringJUnit4ClassRunner. class)
@SpringBootTest(classes=CmsApplication. class)
public class EmailTest {
    @Autowired
    private JavaMailSender javaMailSender;

//    @Autowired
//    private TemplateEngine templateEngine;
    @Test
    public void testSend(){
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("jackiehe2016@outlook.com");//发送者.
        message.setTo("1254825871@qq.com");//接收者.
        message.setSubject("测试邮件（邮件主题）");//邮件主题.
        message.setText("这是邮件内容111");//邮件内容.
        javaMailSender.send(message);//发送邮件
    }

    @Test
    public void testE(){
        MimeMessage message = null;
        try {
            message = javaMailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setFrom(new InternetAddress("jackiehe2016@outlook.com", "asd", "UTF-8"));
            helper.setTo("1254825871@qq.com");
            helper.setSubject("用户注册激活");
            StringBuffer sb = new StringBuffer();
            sb.append("<h1>XX用户注册激活</h1>")
                    .append("<p>您好！: </p>")
                    .append("<p style='text-align:left'>感谢您注册XX,请在30分钟内输入进行激活</p>");
            helper.setText(sb.toString(), true);
        } catch (Exception e) {
            e.printStackTrace();
        }
        javaMailSender.send(message);
    }

    @Test
    public void testTemp(){
        EmailUtils.sendSingleAttachmentFileEmail(javaMailSender,"jackiehe2016@outlook.com",
                "1254825871@qq.com",null,"测试邮件: 带附件",
                "<h2>这是一个测试发送附件的邮件<h2>",true,"baiduQRCode.jpg",new File("E://test.jpg"));
    }

    @Test
    public void testHtml(){
        EmailUtils.sendSingleHtmlEmail(javaMailSender,"jackiehe2016@outlook.com",
                "1254825871@qq.com",null,"测试邮件: 发送图片",
                "<h2>这是一个测试发送图片的邮件<h2>" +
                        "<img src='cid:E://test.jpg'/>",true);
    }
}