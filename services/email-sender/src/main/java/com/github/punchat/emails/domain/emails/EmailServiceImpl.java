package com.github.punchat.emails.domain.emails;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailServiceImpl implements EmailService {
    private final JavaMailSender sender;

    public EmailServiceImpl(JavaMailSender sender) {
        this.sender = sender;
    }

    @Override
    public void send(EmailMessage msg) {
        SimpleMailMessage mail = new SimpleMailMessage();
        mail.setTo(msg.getEmail());
        mail.setSubject(msg.getSubject());
        mail.setText(msg.getText());
        sender.send(mail);
    }
}
