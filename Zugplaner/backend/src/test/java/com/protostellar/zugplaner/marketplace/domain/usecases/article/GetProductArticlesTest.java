package com.protostellar.zugplaner.marketplace.domain.usecases.article;

import org.junit.jupiter.api.Test;

class GetProductArticlesTest {

  private GetProductArticlesDSL given() { return new GetProductArticlesDSL();}

  @Test
  void should_get_articles_for_product_if_user_has_access() {
    given()
      .a_user()
      .part_of_a_userGroup_with_premium_access()
    .when()
      .user_fetches_the_articles_for_the_product()
    .then()
      .request_succeeded();
  }

  @Test
  void shouldnt_get_articles_for_product_if_user_doesnt_have_access() {
    given()
      .a_user()
      .part_of_a_userGroup_with_free_access()
    .when()
      .user_fetches_the_articles_for_the_product()
    .then()
      .request_failed();
  }
}
