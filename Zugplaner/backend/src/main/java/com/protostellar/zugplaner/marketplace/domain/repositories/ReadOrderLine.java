package com.protostellar.zugplaner.marketplace.domain.repositories;

import com.protostellar.zugplaner.common.errors.ProtostellarError;
import com.protostellar.zugplaner.common.model.id.Identifier;
import com.protostellar.zugplaner.marketplace.domain.model.OrderLine;
import io.vavr.control.Either;

public interface ReadOrderLine {

  Either<ProtostellarError, OrderLine> findById(Identifier id);
}
