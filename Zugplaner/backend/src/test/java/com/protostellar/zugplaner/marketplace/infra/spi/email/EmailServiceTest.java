package com.protostellar.zugplaner.marketplace.infra.spi.email;

import com.protostellar.zugplaner.common.model.id.Identifier;
import com.protostellar.zugplaner.marketplace.infra.api.dto.OrderDTO;
import com.protostellar.zugplaner.marketplace.infra.spi.email.service.EmailService;
import com.protostellar.zugplaner.utils.TestSecurityConfiguration;
import io.zonky.test.db.AutoConfigureEmbeddedDatabase;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;


@SpringBootTest(classes = {TestSecurityConfiguration.class})
@AutoConfigureEmbeddedDatabase(beanName = "dataSource", provider = AutoConfigureEmbeddedDatabase.DatabaseProvider.DOCKER)
@ActiveProfiles("it")
public class EmailServiceTest {

  public static final String WEBSHOP_TEST_USER_ID = "17f96c0f-6ce2-475a-b806-aff7564d6473";

  @Autowired
  private EmailService emailService;

  @Test
  void sendEmail() {
    OrderDTO order = OrderDTO.builder()
      .customerName("TestCustomer")
      .userName("Jack")
      .build();

    emailService.sendMail(order, Identifier.from(WEBSHOP_TEST_USER_ID));
  }

}
