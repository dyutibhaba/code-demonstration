package com.protostellar.zugplaner.marketplace.domain.repositories;

import com.protostellar.zugplaner.common.errors.ProtostellarError;
import com.protostellar.zugplaner.common.model.id.Identifier;
import com.protostellar.zugplaner.marketplace.domain.model.Article;
import io.vavr.control.Either;

import java.util.List;

public interface ReadArticle {
  Either<ProtostellarError, List<Article>> getProductArticles(Identifier productId);

  Either<ProtostellarError, List<Article>> findAllArticlesByUser(Identifier userId);
}
