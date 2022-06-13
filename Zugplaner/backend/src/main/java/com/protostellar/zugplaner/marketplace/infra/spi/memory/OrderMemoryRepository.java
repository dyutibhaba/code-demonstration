package com.protostellar.zugplaner.marketplace.infra.spi.memory;

import com.protostellar.zugplaner.common.errors.OrderRepositoryError;
import com.protostellar.zugplaner.common.errors.ProtostellarError;
import com.protostellar.zugplaner.common.model.id.Identifier;
import com.protostellar.zugplaner.marketplace.domain.model.Order;
import com.protostellar.zugplaner.marketplace.domain.repositories.ReadOrder;
import com.protostellar.zugplaner.marketplace.domain.repositories.WriteOrder;
import io.vavr.control.Either;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.UUID;

public class OrderMemoryRepository implements WriteOrder, ReadOrder {
  private final HashMap<Identifier, Order> orders;

  public OrderMemoryRepository() {
    this.orders = new HashMap<>();
  }

  @Override
  public Either<ProtostellarError, Order> save(Order order) {
    Order anOrder = order;
    if(order.getId() == null) {
      Identifier randomId = Identifier.from(UUID.randomUUID());
      anOrder = order.withId(randomId);
    }

    anOrder = anOrder.withOrderDateTime(LocalDateTime.now());
    orders.put(anOrder.getId(), anOrder);
    return Either.right(anOrder);
  }

  @Override
  public Either<ProtostellarError, Order> findById(Identifier id) {
    Order order = orders.get(id);
    return order != null ? Either.right(order) : Either.left(new OrderRepositoryError());
  }

  @Override
  public Either<ProtostellarError, Identifier> findCustomerByUserId(Identifier userId) {
    return Either.right(Identifier.from("55cebff1-7fe6-48c7-b974-b31aca6dea22"));
  }
}
