package com.protostellar.zugplaner.marketplace.domain.repositories;

import com.protostellar.zugplaner.common.errors.ProtostellarError;
import com.protostellar.zugplaner.common.model.id.Identifier;
import com.protostellar.zugplaner.marketplace.domain.model.Customer;
import io.vavr.control.Either;

public interface ReadCustomer {

  Either<ProtostellarError, Customer> findById(Identifier id);
}
