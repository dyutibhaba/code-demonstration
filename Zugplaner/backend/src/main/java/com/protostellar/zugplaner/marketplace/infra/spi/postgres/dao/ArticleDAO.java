package com.protostellar.zugplaner.marketplace.infra.spi.postgres.dao;

import com.protostellar.zugplaner.common.model.id.Identifier;
import com.protostellar.zugplaner.marketplace.domain.model.Article;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.math.BigDecimal;

@Data(staticConstructor = "from")
@NoArgsConstructor
@Entity
public class ArticleDAO {
  @Id
  private String id;
  private String customerId;
  private String thalesNr;
  private Boolean detectorBar;
  private Boolean trailable;
  private Boolean connectorPointDetector;
  private Integer throwingForce;
  private Integer throwingStroke;
  private BigDecimal maxThrowingTime;
  private Boolean phasePlug;
  private Boolean additionalContact;
  private String standardApplication;
  private BigDecimal price;

  public Article toArticle() {

    return Article.from(
      Identifier.from(id),
      Identifier.from(customerId),
      thalesNr,
      detectorBar,
      trailable,
      connectorPointDetector,
      throwingForce,
      throwingStroke,
      maxThrowingTime,
      phasePlug,
      additionalContact,
      standardApplication,
      price
    );

  }
}
