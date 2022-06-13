package com.protostellar.zugplaner.marketplace.infra.spi.memory;

import com.protostellar.zugplaner.common.errors.ProtostellarError;
import com.protostellar.zugplaner.common.model.id.Identifier;
import com.protostellar.zugplaner.marketplace.domain.model.Article;
import com.protostellar.zugplaner.marketplace.domain.repositories.ReadArticle;
import com.protostellar.zugplaner.marketplace.domain.repositories.WriteArticle;
import io.vavr.control.Either;

import java.util.ArrayList;
import java.util.List;

public class ArticleMemoryRepository implements ReadArticle, WriteArticle {
  private final List<Article> articles;

  public ArticleMemoryRepository() {
    this.articles = new ArrayList<>();
  }

  @Override
  public Either<ProtostellarError, List<Article>> getProductArticles(Identifier productId) {
    return Either.right(articles);
  }

  @Override
  public Either<ProtostellarError, List<Article>> findAllArticlesByUser(Identifier userId) {
    return Either.right(articles);
  }
}
