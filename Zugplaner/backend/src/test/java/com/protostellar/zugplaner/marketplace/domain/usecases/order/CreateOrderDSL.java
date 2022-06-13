package com.protostellar.zugplaner.marketplace.domain.usecases.order;

import com.protostellar.zugplaner.common.domain.usecases.user.services.PayingHandler;
import com.protostellar.zugplaner.common.errors.ProtostellarError;
import com.protostellar.zugplaner.common.infra.spi.memory.UserMemoryRepository;
import com.protostellar.zugplaner.common.model.UserGroup;
import com.protostellar.zugplaner.common.model.id.Identifier;
import com.protostellar.zugplaner.common.model.payment.PayingFeature;
import com.protostellar.zugplaner.common.model.payment.PayingPlan;
import com.protostellar.zugplaner.common.model.payment.PayingPlans;
import com.protostellar.zugplaner.marketplace.domain.model.Customer;
import com.protostellar.zugplaner.marketplace.domain.model.Order;
import com.protostellar.zugplaner.marketplace.domain.model.OrderLine;
import com.protostellar.zugplaner.marketplace.domain.model.Product;
import com.protostellar.zugplaner.marketplace.domain.model.ProductArticleWithPrice;
import com.protostellar.zugplaner.marketplace.domain.usecases.CreateOrder;
import com.protostellar.zugplaner.marketplace.domain.usecases.CreateOrderLine;
import com.protostellar.zugplaner.marketplace.infra.spi.memory.CustomerMemoryRepository;
import com.protostellar.zugplaner.marketplace.infra.spi.memory.OrderLineMemoryRepository;
import com.protostellar.zugplaner.marketplace.infra.spi.memory.OrderMemoryRepository;
import com.protostellar.zugplaner.marketplace.infra.spi.memory.ProductMemoryRepository;
import com.protostellar.zugplaner.trackandpredict.domain.repositories.WriteUser;
import com.protostellar.zugplaner.trackandpredict.domain.repositories.WriteUserGroup;
import com.protostellar.zugplaner.trackandpredict.domain.user.User;
import com.protostellar.zugplaner.trackandpredict.infra.spi.memory.UserGroupMemoryRepository;
import com.protostellar.zugplaner.trackandpredict.models.UserProfiles;
import com.protostellar.zugplaner.utils.PicoFactoryTestConfiguration;
import io.vavr.control.Either;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

public class CreateOrderDSL extends PicoFactoryTestConfiguration {

  private User user;
  private Product product;
  private UserGroup savedUserGroup;

  private final WriteUser userWriter = getComponentInstance(UserMemoryRepository.class);
  private final WriteUserGroup userGroupWriter = getComponentInstance(UserGroupMemoryRepository.class);
  private final OrderLineMemoryRepository orderLineMemoryRepository = getComponentInstance(OrderLineMemoryRepository.class);
  private final OrderMemoryRepository orderMemoryRepository = getComponentInstance(OrderMemoryRepository.class);
  private final ProductMemoryRepository productMemoryRepository = getComponentInstance(ProductMemoryRepository.class);
  private final CustomerMemoryRepository customerMemoryRepository = getComponentInstance(CustomerMemoryRepository.class);

  private final CreateOrder createOrder;

  public CreateOrderDSL() {
    PayingHandler payingHandler = getComponentInstance(PayingHandler.class);
    CreateOrderLine createOrderLine = new CreateOrderLine(payingHandler, orderLineMemoryRepository);
    this.createOrder = new CreateOrder(payingHandler, createOrderLine, orderMemoryRepository, productMemoryRepository,
      customerMemoryRepository);
  }


  public CreateOrderDSL a_user() {
    user = UserProfiles.standardUserInWebshop();
    userWriter.save(user, user.getEmail());
    return this;
  }

  public CreateOrderDSL part_of_a_userGroup_with_premium_access() {
    randomUserExists();
    UserGroup userGroupToAdd = UserGroup.from("userGroup", List.of(user), new ArrayList<>())
      .withPayingPlans(PayingPlans.empty().add(PayingFeature.MARKETPLACE_FEATURE, PayingPlan.PREMIUM_PLAN));
    savedUserGroup = userGroupWriter.save(userGroupToAdd).get();
    return this;
  }

  /* Sanity checks */
  private void randomUserExists() {
    if (user == null) {
      throw new IllegalStateException("Given clause incomplete: User was not set");
    }
  }

  private When whenClause = new When();
  private Then thenClause = new Then(whenClause);

  public When when() {
    return whenClause;
  }

  public class When {
    private Either<ProtostellarError, Order> result;

    private OrderLine an_order_line() {
      ProductArticleWithPrice productArticleWithPrice = ProductArticleWithPrice
        .builder()
        .articleId(Identifier.from(UUID.randomUUID()))
        .pricePerArticle(BigDecimal.ONE)
        .build();
      return OrderLine.builder()
        .productArticleWithPrice(productArticleWithPrice)
        .articleQuantity(1)
        .articleTotalPrice(BigDecimal.TEN)
        .build();
    }

    private Order an_order() {
      return Order.builder()
        .customer(Customer.builder().id(Identifier.from("55cebff1-7fe6-48c7-b974-b31aca6dea22")).build())
        .orderNumber("PSWST123DERS")
        .productName("point machine")
        .modelName("l700h")
        .orderLines(List.of(an_order_line()))
        .totalPrice(BigDecimal.TEN)
        .build();
    }

    public When user_creates_an_order() {
      randomUserExists();
      userGroupExists();
      result = createOrder.perform(user.getId(),
        an_order());
      return this;
    }
    public Then then() {
      return thenClause;
    }
  }

  public class Then {
    private final When when;

    private Then(When when) {
      this.when = when;
    }

    public Then request_succeeded() {
      assertThat(when.result.isRight()).isTrue();
      return this;
    }

    public Then has_an_order_id() {
      assertThat(when.result.get().getId()).isNotNull();
      return this;
    }
  }

  private void userGroupExists() {
    if (savedUserGroup == null) {
      throw new IllegalStateException("Given clause incomplete: Group was not set");
    }
  }

}
