package org.hdemia.hdemia.be.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class MailService {

    @Autowired
    JavaMailSender javaMailSender;

    @Value("${spring.mail.from:hdemia@hdemia.com}")
    String from;

    public boolean send(String recipient, String subject, String text) {
        return send(Collections.singletonList(recipient), subject, text);
    }

    public boolean send(List<String> recipients, String subject, String text) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(from);
        message.setTo(recipients.toArray(new String[0]));
        message.setSubject(subject);
        message.setText(text);
        javaMailSender.send(message);
        return true;
    }
}
