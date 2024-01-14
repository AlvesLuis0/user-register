package com.alves.authenticationwithjwtapi.services;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class MailService {

  private final JavaMailSender javaMailSender;

  public void sendEmail(String to, String subject, String text) {
    var simpleMailMessage = new SimpleMailMessage();
    simpleMailMessage.setTo(to);
    simpleMailMessage.setSubject(subject);
    simpleMailMessage.setText(text);
    javaMailSender.send(simpleMailMessage);
  }

}