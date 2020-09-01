package com.xml.publications.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

@Service
public class EmailService {
    private JavaMailSender javaMailSender;

    @Autowired
    public EmailService(JavaMailSender javaMailSender) {this.javaMailSender = javaMailSender;}

    public void sendEmail(String subject, String message, String emailTo) throws MailException{

        MimeMessage msg = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = null;

        try{
            helper = new MimeMessageHelper(msg, true);
            helper.setTo(emailTo);
            helper.setFrom(System.getenv("GMAIL_USERNAME"));

            helper.setSubject(subject);
            helper.setText("<h1>"+message+"</h1>", true);
        }catch (MessagingException e){
            e.printStackTrace();
        }
        javaMailSender.send(msg);

    }
}
