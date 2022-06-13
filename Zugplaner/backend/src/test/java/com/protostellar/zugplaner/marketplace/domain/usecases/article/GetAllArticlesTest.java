package com.protostellar.zugplaner.marketplace.domain.usecases.article;

import org.junit.jupiter.api.Test;

 class GetAllArticlesTest {

  private GetAllArticlesDSL given() { return new GetAllArticlesDSL(); }

  @Test
  void should_get_all_articles_if_user_has_access() {
    given()
      .a_user()
      .part_of_a_userGroup_with_premium_access()
      .when()
      .user_fetches_all_articles()
      .then()
      .request_succeeded();
  }

  @Test
  void shouldnt_get_all_articles_if_user_doesnt_have_access() {
    given()
      .a_user()
      .part_of_a_userGroup_with_free_access()
      .when()
      .user_fetches_all_articles()
      .then()
      .request_failed();
  }
}
