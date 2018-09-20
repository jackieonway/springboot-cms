package com.pengzu.service;

import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.util.concurrent.ListenableFuture;

public interface ThreadService {

    ListenableFuture<String> sendSimpleHtmlEmail(JavaMailSender jms, String sender, String receiver, String carbonCopy,
                                                 String subject, String content, boolean isHtml);
}
