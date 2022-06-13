package com.protostellar.zugplaner.marketplace.infra.spi.postgres;

import com.protostellar.zugplaner.common.errors.ProtostellarError;
import com.protostellar.zugplaner.common.infra.spi.postgres.UserPgRepository;
import com.protostellar.zugplaner.common.model.id.Identifier;
import com.protostellar.zugplaner.common.utils.StringUtilities;
import com.protostellar.zugplaner.marketplace.domain.model.Customer;
import com.protostellar.zugplaner.marketplace.domain.model.Order;
import com.protostellar.zugplaner.marketplace.domain.model.OrderLine;
import com.protostellar.zugplaner.marketplace.domain.model.ProductArticleWithPrice;
import com.protostellar.zugplaner.trackandpredict.domain.user.User;
import com.protostellar.zugplaner.utils.TestSecurityConfiguration;
import io.vavr.control.Either;
import io.zonky.test.db.AutoConfigureEmbeddedDatabase;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(classes = {TestSecurityConfiguration.class})
@AutoConfigureEmbeddedDatabase(beanName = "dataSource", provider = AutoConfigureEmbeddedDatabase.DatabaseProvider.DOCKER)
@ActiveProfiles("it")
class OrderPgRepositoryTest {

  private static final String customerId = "55cebff1-7fe6-48c7-b974-b31aca6dea22";
  private static final String productId = "49cebff1-7fe6-48c7-b974-b31aca6dea10";
  public static final String articleId = "0e5dd834-ec99-49a4-af70-23a46e80c782";


  @Autowired
  UserPgRepository userPgRepository;
  @Autowired
  OrderPgRepository orderPgRepository;
  @Autowired
  OrderLinePgRepository orderLinePgRepository;
  @Autowired
  ArticlePgRepository articlePgRepository;

  private User createUser(String email) {
    return userPgRepository.save(email).get();
  }

  @Test
  void saveOrder() {
    User user = createUser("user@thalesdigital.io");

    Order order = Order.builder()
      .orderNumber(StringUtilities.randomAlphanumeric(7))
      .productName("point machine")
      .modelName("l700h")
      .customer(Customer.builder().id(Identifier.from(customerId)).build())
      .creatorId(user.getId())
      .totalPrice(BigDecimal.TEN)
      .build();
    Either<ProtostellarError, Order> anOrder = orderPgRepository.save(order);
    ProductArticleWithPrice productArticleWithPrice = ProductArticleWithPrice
      .builder()
      .productId(Identifier.from(productId))
      .articleId(Identifier.from(articleId))
      .pricePerArticle(BigDecimal.ONE)
      .build();
    Either<ProtostellarError, OrderLine> anOrderLine = orderLinePgRepository.save(
      OrderLine.builder()
        .orderId(anOrder.get().getId())
        .productArticleWithPrice(productArticleWithPrice)
        .articleQuantity(1)
        .articleTotalPrice(BigDecimal.TEN)
        .build()
    );
    assertThat(anOrder.isRight()).isTrue();
    assertThat(anOrder.get().getId()).isNotNull();
    assertThat(anOrder.get().getOrderNumber().length()).isEqualTo(12);
    assertThat(anOrder.get().getOrderNumber().contains("PSWST")).isTrue();
    assertThat(anOrderLine.isRight()).isTrue();
    assertThat(anOrderLine.get().getId()).isNotNull();
  }
}
