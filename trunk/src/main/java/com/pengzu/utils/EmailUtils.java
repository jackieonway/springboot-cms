package com.pengzu.utils;

import org.apache.commons.lang3.StringUtils;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import javax.mail.internet.MimeMessage;
import java.io.File;

@Component
public class EmailUtils {

    /**
     * 发送单个用户简单邮件
     *
     * @param sender     发送者
     * @param receiver   接收者
     * @param carbonCopy 抄送者
     * @param subject    邮件主题
     * @param content    邮件内容
     */
    private static void sendSingleSimpleEmail(JavaMailSender jms, String sender, String receiver,
                                              String carbonCopy, String subject, String content) {
        //建立邮件消息
        SimpleMailMessage mainMessage = new SimpleMailMessage();
        //发送者
        mainMessage.setFrom(sender);
        //接收者
        mainMessage.setTo(receiver);
        //抄送者
        if (StringUtils.isNotEmpty(carbonCopy)) {
            mainMessage.setCc(carbonCopy);
        }
        //发送的标题
        mainMessage.setSubject(subject);
        //发送的内容
        mainMessage.setText(content);
        jms.send(mainMessage);
    }

    /**
     * 发送多个用户简单邮件
     *
     * @param sender       发送者
     * @param receivers    接收者列表
     * @param carbonCopies 抄送者列表
     * @param subject      邮件主题
     * @param content      邮件内容
     */
    private static void sendSimpleEmail(JavaMailSender jms, String sender, String[] receivers,
                                        String[] carbonCopies, String subject, String content) {
        try {
            //建立邮件消息
            SimpleMailMessage mainMessage = new SimpleMailMessage();
            //发送者
            mainMessage.setFrom(sender);
            //接收者
            mainMessage.setTo(receivers);
            //抄送者
            mainMessage.setCc(carbonCopies);
            //发送的标题
            mainMessage.setSubject(subject);
            //发送的内容
            mainMessage.setText(content);
            jms.send(mainMessage);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 发送单个用户HTML邮件
     *
     * @param sender     发送者
     * @param receiver   接收者
     * @param carbonCopy 抄送者
     * @param subject    邮件主题
     * @param content    邮件内容
     * @param isHtml     是否是HTML
     */
    public static void sendSingleHtmlEmail(JavaMailSender jms, String sender, String receiver, String carbonCopy,
                                           String subject, String content, boolean isHtml) {
        try {
            MimeMessage message = jms.createMimeMessage();
            MimeMessageHelper messageHelper = new MimeMessageHelper(message);

            messageHelper.setFrom(sender);
            messageHelper.setTo(receiver);
            if (StringUtils.isNotEmpty(carbonCopy)) {
                messageHelper.setCc(carbonCopy);
            }
            messageHelper.setSubject(subject);
            messageHelper.setText(content, isHtml);
            jms.send(message);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 发送单个用户HTML邮件
     *
     * @param sender       发送者
     * @param receiver     接收者列表
     * @param carbonCopies 抄送者列表
     * @param subject      邮件主题
     * @param content      邮件内容
     * @param isHtml       是否是HTML
     */
    public static void sendHtmlEmail(JavaMailSender jms, String sender, String[] receiver, String[] carbonCopies,
                                     String subject, String content, boolean isHtml) {
        try {
            MimeMessage message = jms.createMimeMessage();
            MimeMessageHelper messageHelper = new MimeMessageHelper(message);

            messageHelper.setFrom(sender);
            messageHelper.setTo(receiver);
            messageHelper.setCc(carbonCopies);
            messageHelper.setSubject(subject);
            messageHelper.setText(content, isHtml);
            jms.send(message);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 发送单个用户带附件邮件
     *
     * @param sender     发送者
     * @param receiver   接收者
     * @param carbonCopy 抄送者
     * @param subject    邮件主题
     * @param content    邮件内容
     * @param isHtml     是否是HTML
     * @param fileName   附件文件名
     * @param file       附件文件
     */
    public static void sendSingleAttachmentFileEmail(JavaMailSender jms, String sender,
                                                     String receiver, String carbonCopy,
                                                     String subject, String content,
                                                     boolean isHtml, String fileName, File file) {
        try {
            MimeMessage message = jms.createMimeMessage();
            MimeMessageHelper messageHelper = new MimeMessageHelper(message, true);
            messageHelper.setFrom(sender);
            messageHelper.setTo(receiver);
            if (StringUtils.isNotEmpty(carbonCopy)) {
                messageHelper.setCc(carbonCopy);
            }
            messageHelper.setSubject(subject);
            messageHelper.setText(content, isHtml);
            messageHelper.addAttachment(fileName, file);
            jms.send(message);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 发送带附件邮件
     *
     * @param sender       发送者
     * @param receiver     接收者列表
     * @param carbonCopies 抄送者列表
     * @param subject      邮件主题
     * @param content      邮件内容
     * @param isHtml       是否是HTML
     * @param fileName     附件文件名
     * @param file         附件文件
     */
    public static void sendAttachmentFileEmail(JavaMailSender jms, String sender, String[] receiver, String[] carbonCopies,
                                               String subject, String content,
                                               boolean isHtml, String fileName, File file) {
        try {
            MimeMessage message = jms.createMimeMessage();
            MimeMessageHelper messageHelper = new MimeMessageHelper(message, true);
            messageHelper.setFrom(sender);
            messageHelper.setTo(receiver);
            messageHelper.setCc(carbonCopies);
            messageHelper.setSubject(subject);
            messageHelper.setText(content, isHtml);
            messageHelper.addAttachment(fileName, file);
            jms.send(message);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
