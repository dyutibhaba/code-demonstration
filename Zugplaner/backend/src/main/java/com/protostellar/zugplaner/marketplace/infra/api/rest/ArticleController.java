package com.protostellar.zugplaner.marketplace.infra.api.rest;


import com.protostellar.zugplaner.common.domain.usecases.user.services.PayingHandler;
import com.protostellar.zugplaner.common.errors.ProtostellarError;
import com.protostellar.zugplaner.common.infra.authorization.UserPrincipal;
import com.protostellar.zugplaner.common.model.id.Identifier;
import com.protostellar.zugplaner.marketplace.domain.repositories.ReadArticle;
import com.protostellar.zugplaner.marketplace.domain.usecases.article.GetAllArticles;
import com.protostellar.zugplaner.marketplace.domain.usecases.article.GetProductArticles;
import com.protostellar.zugplaner.marketplace.infra.api.dto.ArticleDTO;
import com.protostellar.zugplaner.trackandpredict.infra.api.rest.util.UserUtility;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/marketplace/products")
public class ArticleController {

  private final PayingHandler payingHandler;
  private final ReadArticle articleReader;

  public ArticleController(PayingHandler payingHandler, ReadArticle articleReader) {
    this.articleReader = articleReader;
    this.payingHandler = payingHandler;
  }

  @GetMapping("/{productName}/articles")
  public ResponseEntity<List<ArticleDTO>> getArticlesFor(
    @AuthenticationPrincipal Jwt jwt,
    @PathVariable String productName,
    @RequestParam String productId
  ) throws ProtostellarError {
    GetProductArticles getProductArticles = new GetProductArticles(payingHandler, articleReader);
    UserPrincipal userPrincipal = UserPrincipal.from(jwt);
    Identifier userId = UserUtility.getIdentifier(userPrincipal);
    return getProductArticles.perform(userId, Identifier.from(productId))
      .map(articles -> ResponseEntity.ok(
        articles.stream()
          .map(ArticleDTO::from)
          .collect(Collectors.toList()))).getOrElseThrow(error -> error);
  }

  @GetMapping("/l700h/articles")
  public ResponseEntity<List<ArticleDTO>> getAllArticles(
    @AuthenticationPrincipal Jwt jwt
  ) throws ProtostellarError {
    GetAllArticles getAllArticles = new GetAllArticles(payingHandler, articleReader);
    UserPrincipal userPrincipal = UserPrincipal.from(jwt);
    Identifier userId = UserUtility.getIdentifier(userPrincipal);
    return getAllArticles.perform(userId)
      .map(articles -> ResponseEntity.ok(
        articles.stream()
          .map(ArticleDTO::from)
          .collect(Collectors.toList()))).getOrElseThrow(error -> error);
  }
}
