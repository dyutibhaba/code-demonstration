package com.protostellar.zugplaner.marketplace.domain.model;

import com.protostellar.zugplaner.common.model.id.Identifier;
import lombok.Builder;
import lombok.Data;
import lombok.With;

import java.math.BigDecimal;

@Data
@Builder
public class OrderLine {

  @With Identifier id;
  @With Identifier orderId;
  @With ProductArticleWithPrice productArticleWithPrice;
  @With Integer articleQuantity;
  @With BigDecimal articleTotalPrice;

  public static OrderLine empty() {
    return OrderLine.builder().build();
  }
}
