package com.protostellar.zugplaner.marketplace.infra.spi.email.service;

import com.protostellar.zugplaner.common.infra.spi.postgres.UserPgRepository;
import com.protostellar.zugplaner.common.model.id.Identifier;
import com.protostellar.zugplaner.marketplace.infra.api.dto.OrderDTO;
import com.protostellar.zugplaner.trackandpredict.domain.user.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import javax.mail.MessagingException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

@Slf4j
@Service
public class EmailService {

  @Value("${webshop.mail.username}")
  String username;

  @Value("${webshop.mail.internal}")
  String internalEmail;

  private final JavaMailSender javaMailSender;
  private final UserPgRepository userPgRepository;
  private final TemplateEngine templateEngine;

  public EmailService(UserPgRepository userPgRepository,
                      TemplateEngine templateEngine,
                      JavaMailSender javaMailSender) {
    this.userPgRepository = userPgRepository;
    this.templateEngine = templateEngine;
    this.javaMailSender = javaMailSender;
  }

  @Async
  public void sendMail(OrderDTO orderDto, Identifier userId) {
    User user = userPgRepository.findById(userId);

    Context context = new Context();
    context.setVariable("user", user);
    context.setVariable("orderDto", orderDto);

    String userTemplate = templateEngine.process("email/order-confirmation-user", context);
    sendMailToUser(orderDto, user, userTemplate);

    String internalTemplate = templateEngine.process("email/order-confirmation-internal", context);
    sendMailToInternal(orderDto, internalTemplate);
  }

  private void sendMailToUser(OrderDTO orderDto, User user, String userTemplate) {
    try{
      MimeMessage message = javaMailSender.createMimeMessage();
      MimeMessageHelper helper = new MimeMessageHelper(message);
      message.setFrom(new InternetAddress(username));
      helper.setSubject(String.format("Your order information - %s", orderDto.getOrderNumber()));
      helper.setText(userTemplate, true);
      helper.setTo(user.getEmail());
      javaMailSender.send(message);
      log.info("Email sent successfully!!");
    } catch (MessagingException e) {
      log.error("Error occurred while trying to send mail!!");
      e.printStackTrace();
    }
  }

  private void sendMailToInternal(OrderDTO orderDto, String internalTemplate) {
    try{
      MimeMessage message = javaMailSender.createMimeMessage();
      MimeMessageHelper helper = new MimeMessageHelper(message);
      message.setFrom(new InternetAddress(username));
      helper.setSubject(String.format("New order information - %s", orderDto.getOrderNumber()));
      helper.setText(internalTemplate, true);
      helper.setTo(internalEmail);
      javaMailSender.send(message);
      log.info("Email sent successfully!!");
    } catch (MessagingException e) {
      log.error("Error occurred while trying to send mail!!");
      e.printStackTrace();
    }
  }

}
