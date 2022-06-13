package com.protostellar.zugplaner.marketplace.infra.spi.postgres;

import com.protostellar.zugplaner.common.errors.ProtostellarError;
import com.protostellar.zugplaner.common.model.id.Identifier;
import com.protostellar.zugplaner.marketplace.domain.errors.ArticleRepositoryError;
import com.protostellar.zugplaner.marketplace.domain.model.Article;
import com.protostellar.zugplaner.marketplace.domain.repositories.ReadArticle;
import com.protostellar.zugplaner.marketplace.domain.repositories.WriteArticle;
import com.protostellar.zugplaner.marketplace.infra.spi.postgres.dao.ArticleDAO;
import io.vavr.control.Either;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Collectors;

@Repository
public class ArticlePgRepository implements ReadArticle, WriteArticle {
  private static final Logger LOGGER = LoggerFactory.getLogger(ArticlePgRepository.class);
  private final JdbcTemplate template;

  public ArticlePgRepository(JdbcTemplate template) {
    this.template = template;
  }

  private static final String SELECT_ALL_ARTICLES_BY_USER_ID = "SELECT a.\"ID\", ctpap.\"CustomerId\", a.\"ThalesNr\", a.\"DetectorBar\", a.\"Trailable\", " +
    "a.\"ConnectorPointDetector\", a.\"ThrowingForce\", a.\"ThrowingStroke\", a.\"MaxThrowingTime\", a.\"PhasePlug\", a.\"AdditionalContact\", " +
    "a.\"StandardApplication\", ctpap.\"Price\" FROM protostellar.\"Article\" a INNER JOIN  protostellar.\"CustomerToArticlePrice\" ctpap \n" +
    "ON a.\"ID\" = ctpap.\"ArticleId\" WHERE ctpap.\"CustomerId\" IN (SELECT DISTINCT uuid(ug.\"CustomerID\") FROM protostellar.\"UserToUserGroup\" utug \n" +
    "INNER JOIN protostellar.\"UserGroup\" ug on utug.\"UserGroupId\" = ug.\"ID\" WHERE utug.\"UserId\" = ?::uuid AND ug.\"CustomerID\" IS NOT NULL)";

  private static final String FIND_ARTICLES_BY_PRODUCT_ID = "SELECT * " +
    "FROM protostellar.\"Article\" AS a  " +
    "INNER_JOIN protostellar.\"ProductToArticle\" AS pa " +
    "ON a.\"ID\" = pa.\"ArticleId\" " +
    "WHERE pa.\"ProductId\" = ?::uuid; ";

  @Override
  public Either<ProtostellarError, List<Article>> getProductArticles(Identifier productId) {
    try {
      List<Article> productArticles = template.query(
        FIND_ARTICLES_BY_PRODUCT_ID,
        new BeanPropertyRowMapper<>(ArticleDAO.class),
        productId.getRepresentation()).stream()
        .map(ArticleDAO::toArticle)
        .collect(Collectors.toList());

      return Either.right(productArticles);
    } catch (DataAccessException e) {
      if (LOGGER.isErrorEnabled()) {
        LOGGER.error(String.format("getting articles for product id {%s} thrown an exception {%s}",
          productId.getRepresentation(), e.getMessage()));
      }
      return Either.left(new ArticleRepositoryError.NotFound());
    }
  }

  @Override
  public Either<ProtostellarError, List<Article>> findAllArticlesByUser(Identifier userId) {
    try {
      List<Article> allArticles = template.query(
        SELECT_ALL_ARTICLES_BY_USER_ID,
        new BeanPropertyRowMapper<>(ArticleDAO.class),
        userId.getRepresentation()).stream()
        .map(ArticleDAO::toArticle)
        .collect(Collectors.toList());

      return Either.right(allArticles);
    } catch (DataAccessException e) {
      if (LOGGER.isErrorEnabled()) {
        LOGGER.error("getting articles thrown an exception", e.getCause());
      }
      return Either.left((new ArticleRepositoryError.NotFound()));
    }
  }
}
