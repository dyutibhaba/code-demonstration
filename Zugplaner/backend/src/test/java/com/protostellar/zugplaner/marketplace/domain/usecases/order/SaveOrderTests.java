package com.protostellar.zugplaner.marketplace.domain.usecases.order;

import org.junit.jupiter.api.Test;

class SaveOrderTests {

  private CreateOrderDSL given() {
    return new CreateOrderDSL();
  }

  @Test
  void given_a_user_with_access_to_shop_when_user_creates_an_order_then_succeeded() {
    given()
      .a_user()
      .part_of_a_userGroup_with_premium_access()
    .when()
      .user_creates_an_order()
    .then()
      .request_succeeded()
      .has_an_order_id();
  }
}
