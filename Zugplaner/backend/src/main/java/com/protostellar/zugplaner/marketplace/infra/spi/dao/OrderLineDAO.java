package com.protostellar.zugplaner.marketplace.infra.spi.dao;

import com.protostellar.zugplaner.common.model.id.Identifier;
import com.protostellar.zugplaner.marketplace.domain.model.OrderLine;
import com.protostellar.zugplaner.marketplace.domain.model.ProductArticleWithPrice;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class OrderLineDAO {

  Identifier id;
  Identifier orderId;
  Identifier productId;
  Identifier articleId;
  BigDecimal pricePerArticle;
  Integer quantity;
  BigDecimal totalPrice;
  LocalDateTime sysInsertTS;
  LocalDateTime sysUpdateTS;

  public OrderLine toOrderLine() {
    ProductArticleWithPrice productArticleWithPrice = ProductArticleWithPrice
      .builder()
      .productId(productId)
      .articleId(articleId)
      .pricePerArticle(pricePerArticle)
      .build();
    return OrderLine.builder()
      .id(id)
      .orderId(orderId)
      .productArticleWithPrice(productArticleWithPrice)
      .articleQuantity(quantity)
      .articleTotalPrice(totalPrice)
      .build();
  }

}
