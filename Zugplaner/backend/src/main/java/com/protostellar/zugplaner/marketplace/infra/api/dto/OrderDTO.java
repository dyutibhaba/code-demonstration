package com.protostellar.zugplaner.marketplace.infra.api.dto;

import com.protostellar.zugplaner.common.model.id.Identifier;
import com.protostellar.zugplaner.marketplace.domain.model.Order;
import com.protostellar.zugplaner.marketplace.domain.model.OrderLine;
import lombok.Builder;
import lombok.Data;
import lombok.With;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Data
@Builder
public class OrderDTO {

  @With Identifier id;
  @With String orderNumber;
  @With Identifier customerId;
  @With String customerName;
  @With BigDecimal totalPrice;
  @With String userName;
  @With LocalDate orderDate;
  @With List<OrderLine> orderLines;

 public static OrderDTO from(Order orderContent){
    return OrderDTO.builder()
      .id(orderContent.getId())
      .orderNumber(orderContent.getOrderNumber())
      .customerId(orderContent.getCustomer().getId())
      .customerName(orderContent.getCustomer().getName())
      .totalPrice(orderContent.getTotalPrice())
      .orderDate(orderContent.getOrderDateTime().toLocalDate())
      .orderLines(orderContent.getOrderLines())
      .build();
  }
}
