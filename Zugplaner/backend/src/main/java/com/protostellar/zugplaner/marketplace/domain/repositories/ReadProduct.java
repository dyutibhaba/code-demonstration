package com.protostellar.zugplaner.marketplace.domain.repositories;

import com.protostellar.zugplaner.common.errors.ProtostellarError;
import com.protostellar.zugplaner.marketplace.domain.model.Product;
import io.vavr.control.Either;

public interface ReadProduct {

  Either<ProtostellarError, Product> findByNameAndModel(String name, String model);
}
