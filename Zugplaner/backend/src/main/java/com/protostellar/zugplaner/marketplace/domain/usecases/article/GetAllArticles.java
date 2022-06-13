package com.protostellar.zugplaner.marketplace.domain.usecases.article;

import com.protostellar.zugplaner.common.domain.usecases.user.services.PayingHandler;
import com.protostellar.zugplaner.common.errors.ProtostellarError;
import com.protostellar.zugplaner.common.model.id.Identifier;
import com.protostellar.zugplaner.common.model.payment.PayingFeature;
import com.protostellar.zugplaner.marketplace.domain.model.Article;
import com.protostellar.zugplaner.marketplace.domain.repositories.ReadArticle;
import io.vavr.control.Either;

import java.util.List;

public class GetAllArticles {
  private final ReadArticle articleReader;
  private final PayingHandler payingHandler;

  public GetAllArticles(PayingHandler payingHandler, ReadArticle articleReader) {
    this.payingHandler = payingHandler;
    this.articleReader = articleReader;
  }

  public final Either<ProtostellarError, List<Article>> perform(Identifier userId) {
    return payingHandler.hasProperPayingAccessFor(userId, PayingFeature.MAINTENANCE_FEATURE)
      .flatMap(v -> articleReader.findAllArticlesByUser(userId));
  }
}
