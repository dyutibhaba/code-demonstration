package com.protostellar.zugplaner.marketplace.infra.spi.postgres.dsl;

import com.protostellar.zugplaner.common.errors.ProtostellarError;
import com.protostellar.zugplaner.common.infra.spi.postgres.UserPgRepository;
import com.protostellar.zugplaner.common.model.id.Identifier;
import com.protostellar.zugplaner.marketplace.domain.model.Article;
import com.protostellar.zugplaner.marketplace.infra.spi.postgres.ArticlePgRepository;
import com.protostellar.zugplaner.trackandpredict.domain.user.User;
import io.vavr.control.Either;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class ArticlePgRepositoryDSL {
  private final static String USER_EMAIL = "creator@thalesdigital.io";
  public static final String USER_ID_FOR_MARKETPLACE_CUSTOMER = "fd90e76b-b0e7-4bbb-9559-110b83e3ec87";

  public static class Given {
    private final UserPgRepository userPgRepository;
    private final ArticlePgRepository articlePgRepository;
    private User user;
    private List<Article> articles;

    public Given(ArticlePgRepository articlePgRepository, UserPgRepository userPgRepository) {
      this.articlePgRepository = articlePgRepository;
      this.userPgRepository = userPgRepository;
    }

    public When when() {
      return new When(this, userPgRepository);
    }

    public Given a_user_connected() {
      user = userPgRepository.findById(Identifier.from(USER_ID_FOR_MARKETPLACE_CUSTOMER));
      return this;
    }
  }

  public static class When {
    private final Given given;
    private Either<ProtostellarError, List<Article>> allArticles;

    public When(Given given, UserPgRepository userPgRepository) {
      this.given = given;
    }

    public Then then() {
      return new Then(this);
    }

    public When user_fetches_all_articles() {
      this.allArticles = this.given.articlePgRepository.findAllArticlesByUser(
        Identifier.from(USER_ID_FOR_MARKETPLACE_CUSTOMER));
      return this;
    }
  }

  public static class Then {
    private final When when;

    public Then(When when) {
      this.when = when;
    }

    public Then the_result_is_valid() {
      assertThat(this.when.allArticles.isRight()).isTrue();
      return this;
    }

    public void the_result_contains_several_articles() {
      assertThat(this.when.allArticles.get().size() > 0).isTrue();
    }
  }
}
