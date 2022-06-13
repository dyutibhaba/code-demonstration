package com.protostellar.zugplaner.marketplace.infra.spi.dao;

import com.protostellar.zugplaner.common.model.id.Identifier;
import com.protostellar.zugplaner.marketplace.domain.model.Customer;
import lombok.Data;

@Data
public class CustomerDAO {

  String id;
  String name;

  public Customer toCustomer() {
    return Customer.builder()
      .id(Identifier.from(id))
      .name(name)
      .build();
  }
}
