package com.example.demo.user.email;


import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;


@Service
@AllArgsConstructor
public class EmailService implements EmailSender {

    private final static Logger logger = LoggerFactory
            .getLogger(EmailService.class);

    private final JavaMailSender mailSender;

    @Override
    @Async
    public void send(String to, String email,String subject) {
        try {
            MimeMessage message = generateMimeMessage(to, email,subject);
            mailSender.send(message);
        } catch (MessagingException e) {
            logger.error("failed to send email", e);
            throw new IllegalStateException("failed to send email");
        }
    }


    private MimeMessage generateMimeMessage(String to, String email, String subject) throws MessagingException {
        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, "utf-8");
        helper.setText(email, true);
        helper.setTo(to);
        helper.setSubject(subject);
        helper.setFrom("reservations@britenet.gmail.com");
        return message;
    }
}
