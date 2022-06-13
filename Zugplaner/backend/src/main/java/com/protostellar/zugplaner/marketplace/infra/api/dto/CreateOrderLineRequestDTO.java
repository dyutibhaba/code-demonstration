package com.protostellar.zugplaner.marketplace.infra.api.dto;

import com.protostellar.zugplaner.common.model.id.Identifier;
import com.protostellar.zugplaner.marketplace.domain.model.OrderLine;
import com.protostellar.zugplaner.marketplace.domain.model.ProductArticleWithPrice;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class CreateOrderLineRequestDTO {

  String articleId;
  String thalesNr;
  BigDecimal articlePrice;
  Integer articleQuantity;
  BigDecimal articleTotalPrice;

  public OrderLine toOrderLine() {
    ProductArticleWithPrice productArticleWithPrice = ProductArticleWithPrice
      .builder()
      .articleId(Identifier.from(articleId))
      .thalesNr(thalesNr)
      .pricePerArticle(articlePrice)
      .build();
    return OrderLine.builder()
      .productArticleWithPrice(productArticleWithPrice)
      .articleQuantity(articleQuantity)
      .articleTotalPrice(articleTotalPrice)
      .build();
  }
}
