package com.protostellar.zugplaner.marketplace.infra.spi.postgres;

import com.protostellar.zugplaner.common.infra.spi.postgres.UserPgRepository;
import com.protostellar.zugplaner.marketplace.infra.spi.postgres.dsl.ArticlePgRepositoryDSL;
import com.protostellar.zugplaner.utils.TestSecurityConfiguration;
import com.protostellar.zugplaner.utils.WithAuthenticatedUser;
import io.zonky.test.db.AutoConfigureEmbeddedDatabase;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest(classes = {TestSecurityConfiguration.class})
@AutoConfigureEmbeddedDatabase(beanName = "dataSource", provider = AutoConfigureEmbeddedDatabase.DatabaseProvider.DOCKER)
@ActiveProfiles("it")
class ArticlePgRepositoryTests {

  @Autowired
  ArticlePgRepository articlePgRepository;
  @Autowired
  UserPgRepository userPgRepository;

  private ArticlePgRepositoryDSL.Given given() {
    return new ArticlePgRepositoryDSL.Given(this.articlePgRepository, userPgRepository);
  }

  @Test
  @WithAuthenticatedUser
  void testGetAllArticles() {
    given()
      .a_user_connected()
      .when()
      .user_fetches_all_articles()
      .then()
      .the_result_is_valid()
      .the_result_contains_several_articles();
  }
}
