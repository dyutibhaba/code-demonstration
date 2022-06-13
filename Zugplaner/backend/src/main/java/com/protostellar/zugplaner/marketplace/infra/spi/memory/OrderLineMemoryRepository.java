package com.protostellar.zugplaner.marketplace.infra.spi.memory;

import com.protostellar.zugplaner.common.errors.OrderRepositoryError;
import com.protostellar.zugplaner.common.errors.ProtostellarError;
import com.protostellar.zugplaner.common.model.id.Identifier;
import com.protostellar.zugplaner.marketplace.domain.model.OrderLine;
import com.protostellar.zugplaner.marketplace.domain.repositories.ReadOrderLine;
import com.protostellar.zugplaner.marketplace.domain.repositories.WriteOrderLine;
import io.vavr.control.Either;

import java.util.HashMap;
import java.util.UUID;

public class OrderLineMemoryRepository implements WriteOrderLine, ReadOrderLine {

  private final HashMap<Identifier, OrderLine> orderLines;

  public OrderLineMemoryRepository() {
    this.orderLines = new HashMap<>();
  }

  @Override
  public Either<ProtostellarError, OrderLine> save(OrderLine orderLine) {
    OrderLine anOrderLine = orderLine;
    if(orderLine.getId() == null) {
      Identifier randomId = Identifier.from(UUID.randomUUID());
      anOrderLine = orderLine.withId(randomId).withOrderId(orderLine.getOrderId());
    }

    orderLines.put(anOrderLine.getId(), anOrderLine);
    return Either.right(anOrderLine);
  }

  @Override
  public Either<ProtostellarError, OrderLine> findById(Identifier id) {
    OrderLine orderLine = orderLines.get(id);
    return orderLine != null ? Either.right(orderLine) : Either.left(new OrderRepositoryError());
  }
}
