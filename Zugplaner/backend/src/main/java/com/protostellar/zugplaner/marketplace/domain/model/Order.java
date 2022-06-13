package com.protostellar.zugplaner.marketplace.domain.model;

import com.protostellar.zugplaner.common.model.id.Identifier;
import lombok.Builder;
import lombok.Data;
import lombok.With;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
public class Order {

  @With Identifier id;
  @With String productName;
  @With String modelName;
  @With Customer customer;
  @With Identifier creatorId;
  @With String creatorName;
  @With String orderNumber;
  @With List<OrderLine> orderLines;
  @With BigDecimal totalPrice;
  @With LocalDateTime orderDateTime;

  public static Order empty() {
    return Order.builder().build();
  }
}
