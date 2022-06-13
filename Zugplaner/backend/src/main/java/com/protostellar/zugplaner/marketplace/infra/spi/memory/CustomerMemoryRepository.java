package com.protostellar.zugplaner.marketplace.infra.spi.memory;

import com.protostellar.zugplaner.common.errors.ProtostellarError;
import com.protostellar.zugplaner.common.model.id.Identifier;
import com.protostellar.zugplaner.marketplace.domain.model.Customer;
import com.protostellar.zugplaner.marketplace.domain.repositories.ReadCustomer;
import io.vavr.control.Either;

public class CustomerMemoryRepository implements ReadCustomer {
  @Override
  public Either<ProtostellarError, Customer> findById(Identifier id) {
    Customer customer = Customer.builder()
      .id(Identifier.from("55cebff1-7fe6-48c7-b974-b31aca6dea22"))
      .build();
    return Either.right(customer);
  }
}
