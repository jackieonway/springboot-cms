package com.pengzu.service.impl;

import com.pengzu.service.ThreadService;
import com.pengzu.utils.EmailUtils;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Component;
import org.springframework.util.concurrent.ListenableFuture;

@Component
@Async
public class THreadServiceImpl implements ThreadService {

    public ListenableFuture<String> sendHtmlEmail(String name) {
        String res = name + ":Hello World!";
        return new AsyncResult<>(res);
    }

    @Override
    public ListenableFuture<String> sendSimpleHtmlEmail(JavaMailSender javaMailSender, String sender, String receiver, String carbonCopy, String subject, String content, boolean isHtml) {
        EmailUtils.sendSingleHtmlEmail(javaMailSender, sender, receiver, carbonCopy, subject, content, isHtml);
        return new AsyncResult<>(receiver);
    }
}
