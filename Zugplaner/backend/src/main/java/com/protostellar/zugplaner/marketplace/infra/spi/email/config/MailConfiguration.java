package com.protostellar.zugplaner.marketplace.infra.spi.email.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;

import java.util.Locale;
import java.util.Properties;

@Configuration
public class MailConfiguration {

  @Value("${webshop.mail.smtp.host}")
  String host;
  @Value("${webshop.mail.smtp.port}")
  int port;
  @Value("${webshop.mail.smtp.auth}")
  String auth;
  @Value("${webshop.mail.smtp.socketFactoryClass}")
  String sslSocketFactoryClass;
  @Value("${webshop.mail.username}")
  String username;
  @Value("${webshop.mail.password}")
  String password;

  @Bean
  public JavaMailSender getJavaMailSender() {
    JavaMailSenderImpl javaMailSender = new JavaMailSenderImpl();
    javaMailSender.setHost(host);
    javaMailSender.setPort(port);
    javaMailSender.setUsername(username);
    javaMailSender.setPassword(password);

    Properties props = javaMailSender.getJavaMailProperties();
    props.put("mail.smtp.auth", auth);
    props.put("mail.smtp.socketFactory.class", sslSocketFactoryClass);
    return javaMailSender;
  }

  @Bean
  public SessionLocaleResolver localeResolver() {
    SessionLocaleResolver slr = new SessionLocaleResolver();
    slr.setDefaultLocale(new Locale("de", "DE"));
    return slr;
  }
}
