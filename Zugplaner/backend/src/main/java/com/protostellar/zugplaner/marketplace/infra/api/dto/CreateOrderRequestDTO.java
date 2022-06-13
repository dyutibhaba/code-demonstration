package com.protostellar.zugplaner.marketplace.infra.api.dto;

import com.protostellar.zugplaner.common.model.id.Identifier;
import com.protostellar.zugplaner.marketplace.domain.model.Customer;
import com.protostellar.zugplaner.marketplace.domain.model.Order;
import com.protostellar.zugplaner.marketplace.domain.model.OrderLine;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Data
public class CreateOrderRequestDTO {
  String customerId;
  String productName;
  String productModel;
  List<CreateOrderLineRequestDTO> orderLines;
  BigDecimal totalPrice;

  public Order toOrder() {
    List<OrderLine> orderLineContents = this.orderLines
      .stream()
      .map(CreateOrderLineRequestDTO::toOrderLine)
      .collect(Collectors.toList());
    return Order.builder()
      .customer(Customer.builder().id(Identifier.from(customerId)).build())
      .productName(productName)
      .modelName(productModel)
      .orderLines(orderLineContents)
      .totalPrice(totalPrice)
      .build();
  }
}
