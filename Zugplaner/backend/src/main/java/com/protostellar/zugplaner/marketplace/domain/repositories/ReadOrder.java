package com.protostellar.zugplaner.marketplace.domain.repositories;

import com.protostellar.zugplaner.common.errors.ProtostellarError;
import com.protostellar.zugplaner.common.model.id.Identifier;
import com.protostellar.zugplaner.marketplace.domain.model.Order;
import io.vavr.control.Either;

public interface ReadOrder {

  Either<ProtostellarError, Order> findById(Identifier id);

  Either<ProtostellarError, Identifier> findCustomerByUserId(Identifier userId);
}
