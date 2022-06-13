package com.protostellar.zugplaner.marketplace.infra.spi.dao;

import com.protostellar.zugplaner.common.model.id.Identifier;
import com.protostellar.zugplaner.marketplace.domain.model.Customer;
import com.protostellar.zugplaner.marketplace.domain.model.Order;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
public class OrderDAO {
  UUID id;
  String customerId;
  String customerName;
  String creatorId;
  String orderNumber;
  BigDecimal totalPrice;
  LocalDateTime sysInsertTS;
  LocalDateTime sysUpdateTS;

  public Order toOrderContent() {
    return Order.builder()
      .id(Identifier.from(id))
      .orderNumber(orderNumber)
      .customer(Customer.builder().id(Identifier.from(customerId)).build())
      .totalPrice(totalPrice)
      .orderDateTime(sysInsertTS)
      .build();
  }

}
