package com.protostellar.zugplaner.marketplace.infra.api.dto;

import com.protostellar.zugplaner.marketplace.domain.model.Article;
import lombok.Data;
import lombok.Value;

import java.math.BigDecimal;

@Data
@Value
public class ArticleDTO {
  String id;
  String customerId;
  String thalesNr;
  Boolean detectorBar;
  Boolean trailable;
  Boolean connectorPointDetector;
  Integer throwingForce;
  Integer throwingStroke;
  BigDecimal maxThrowingTime;
  Boolean phasePlug;
  Boolean additionalContact;
  String standardApplication;
  BigDecimal pricePerArticle;

public static ArticleDTO from(Article article) {
  return new ArticleDTO(
    article.getId().getRepresentation(),
    article.getCustomerId().getRepresentation(),
    article.getThalesNr(),
    article.getDetectorBar(),
    article.getTrailable(),
    article.getConnectorPointDetector(),
    article.getThrowingForce(),
    article.getThrowingStroke(),
    article.getMaxThrowingTime(),
    article.getPhasePlug(),
    article.getAdditionalContact(),
    article.getStandardApplication(),
    article.getPrice()
  );
 }
}
