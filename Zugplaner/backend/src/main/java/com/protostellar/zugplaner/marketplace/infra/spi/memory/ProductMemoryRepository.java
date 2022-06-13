package com.protostellar.zugplaner.marketplace.infra.spi.memory;

import com.protostellar.zugplaner.common.errors.ProtostellarError;
import com.protostellar.zugplaner.common.model.id.Identifier;
import com.protostellar.zugplaner.marketplace.domain.model.Product;
import com.protostellar.zugplaner.marketplace.domain.repositories.ReadProduct;
import io.vavr.control.Either;

public class ProductMemoryRepository implements ReadProduct {
  public static final String POINT_MACHINE_L700H_PRODUCT_ID = "49cebff1-7fe6-48c7-b974-b31aca6dea10";

  @Override
  public Either<ProtostellarError, Product> findByNameAndModel(String name, String model) {
    return Either.right(Product.builder().id(Identifier.from(POINT_MACHINE_L700H_PRODUCT_ID)).build());
  }
}
