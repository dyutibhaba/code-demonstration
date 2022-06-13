package com.protostellar.zugplaner.marketplace.domain.model;

import com.protostellar.zugplaner.common.model.id.Identifier;
import lombok.Builder;
import lombok.Data;
import lombok.Value;
import lombok.With;

import java.math.BigDecimal;

@Data
@Value
@Builder
public class ProductArticleWithPrice {
  @With Identifier productId;
  @With Identifier articleId;
  @With String thalesNr;
  @With BigDecimal pricePerArticle;
}
