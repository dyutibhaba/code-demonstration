package com.protostellar.zugplaner.marketplace.domain.usecases.article;

import com.protostellar.zugplaner.common.domain.usecases.user.services.PayingHandler;
import com.protostellar.zugplaner.common.errors.ProtostellarError;
import com.protostellar.zugplaner.common.infra.spi.memory.UserMemoryRepository;
import com.protostellar.zugplaner.common.model.UserGroup;
import com.protostellar.zugplaner.common.model.payment.PayingFeature;
import com.protostellar.zugplaner.common.model.payment.PayingPlan;
import com.protostellar.zugplaner.common.model.payment.PayingPlans;
import com.protostellar.zugplaner.marketplace.domain.model.Article;
import com.protostellar.zugplaner.marketplace.domain.repositories.ReadArticle;
import com.protostellar.zugplaner.trackandpredict.domain.repositories.ReadUser;
import com.protostellar.zugplaner.trackandpredict.domain.repositories.WriteUser;
import com.protostellar.zugplaner.trackandpredict.domain.repositories.WriteUserGroup;
import com.protostellar.zugplaner.trackandpredict.domain.user.User;
import com.protostellar.zugplaner.trackandpredict.infra.spi.memory.UserGroupMemoryRepository;
import com.protostellar.zugplaner.trackandpredict.models.UserProfiles;
import com.protostellar.zugplaner.utils.PicoFactoryTestConfiguration;
import io.vavr.control.Either;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class GetAllArticlesDSL extends PicoFactoryTestConfiguration {
  private User user;
  private UserGroup savedUserGroup;

  private final GetAllArticles getAllArticles;
  private final WriteUser userWriter = getComponentInstance(UserMemoryRepository.class);
  private final ReadUser userReader = getComponentInstance(UserMemoryRepository.class);
  private final WriteUserGroup userGroupWriter = getComponentInstance(UserGroupMemoryRepository.class);
  private final ReadArticle articleReader = getComponentInstance(ReadArticle.class);

  public GetAllArticlesDSL() {
    PayingHandler payingHandler = getComponentInstance(PayingHandler.class);
    this.getAllArticles = new GetAllArticles(payingHandler, articleReader);
  }

  private GetAllArticlesDSL.When whenClause = new GetAllArticlesDSL.When();
  private GetAllArticlesDSL.Then thenClause = new GetAllArticlesDSL.Then(whenClause);

  public GetAllArticlesDSL.When when() { return whenClause; }

  public GetAllArticlesDSL a_user() {
    user = UserProfiles.standardUserInTrackAndPredict();
    userWriter.save(user, user.getEmail());
    return this;
  }

  public GetAllArticlesDSL part_of_a_userGroup_with_premium_access() {
    randomUserExists();
    UserGroup userGroupToAdd = UserGroup.from("userGroup", Collections.singletonList(user), new ArrayList<>())
      .withPayingPlans(PayingPlans.empty().add(PayingFeature.MAINTENANCE_FEATURE, PayingPlan.PREMIUM_PLAN));
    savedUserGroup = userGroupWriter.save(userGroupToAdd).get();
    return this;
  }

  public GetAllArticlesDSL part_of_a_userGroup_with_free_access() {
    randomUserExists();
    UserGroup userGroupToAdd = UserGroup.from("userGroup", Collections.singletonList(user), new ArrayList<>())
      .withPayingPlans(PayingPlans.empty().add(PayingFeature.MAINTENANCE_FEATURE, PayingPlan.FREE_PLAN));
    savedUserGroup = userGroupWriter.save(userGroupToAdd).get();
    return this;
  }

  public class When {
    private Either<ProtostellarError, List<Article>> result;

    public GetAllArticlesDSL.When user_fetches_all_articles() {
      randomUserExists();
      userGroupExists();
      result = getAllArticles.perform(user.getId());
      return this;
    }
    public GetAllArticlesDSL.Then then() {
      return thenClause;
    }
  }

  public class Then {
    private final GetAllArticlesDSL.When when;

    private Then(GetAllArticlesDSL.When when) {
      this.when = when;
    }

    public GetAllArticlesDSL.Then request_succeeded() {
      assertThat(when.result.isRight()).isTrue();
      return this;
    }

    public GetAllArticlesDSL.Then request_failed() {
      assertThat(when.result.isLeft()).isTrue();
      return this;
    }
  }

  private void randomUserExists() {
    if (user == null) {
      throw new IllegalStateException("Given clause incomplete: User was not set");
    }
  }

  private void userGroupExists() {
    if (savedUserGroup == null) {
      throw new IllegalStateException("Given clause incomplete: Group was not set");
    }
  }

}
