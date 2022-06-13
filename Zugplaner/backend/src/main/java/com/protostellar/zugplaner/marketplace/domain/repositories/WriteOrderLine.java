package com.protostellar.zugplaner.marketplace.domain.repositories;

import com.protostellar.zugplaner.common.errors.ProtostellarError;
import com.protostellar.zugplaner.marketplace.domain.model.OrderLine;
import io.vavr.control.Either;

public interface WriteOrderLine {

  Either<ProtostellarError, OrderLine> save(OrderLine orderLineContent);
}
