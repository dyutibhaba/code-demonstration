package com.protostellar.zugplaner.marketplace.domain.repositories;

import com.protostellar.zugplaner.common.errors.ProtostellarError;
import com.protostellar.zugplaner.marketplace.domain.model.Order;
import io.vavr.control.Either;

public interface WriteOrder {
  Either<ProtostellarError, Order> save(Order order);
}
