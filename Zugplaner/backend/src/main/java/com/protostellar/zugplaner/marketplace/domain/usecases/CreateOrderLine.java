package com.protostellar.zugplaner.marketplace.domain.usecases;

import com.protostellar.zugplaner.common.domain.usecases.user.services.PayingHandler;
import com.protostellar.zugplaner.common.errors.OrderRepositoryError;
import com.protostellar.zugplaner.common.errors.ProtostellarError;
import com.protostellar.zugplaner.common.model.id.Identifier;
import com.protostellar.zugplaner.common.model.payment.PayingFeature;
import com.protostellar.zugplaner.marketplace.domain.model.OrderLine;
import com.protostellar.zugplaner.marketplace.domain.repositories.WriteOrderLine;
import io.vavr.control.Either;

import java.util.function.Function;

public class CreateOrderLine {
  private final WriteOrderLine orderLineWriter;
  private final PayingHandler payingHandler;

  public CreateOrderLine(PayingHandler payingHandler,
                         WriteOrderLine orderLineWriter) {
    this.orderLineWriter = orderLineWriter;
    this.payingHandler = payingHandler;
  }

  public Either<ProtostellarError, OrderLine> perform(Identifier userId,
                                                      OrderLine orderLineContent) {
    return payingHandler.hasProperPayingAccessFor(userId, PayingFeature.MARKETPLACE_FEATURE)
      .flatMap(validateOrderLineContent(orderLineContent))
      .flatMap(v -> orderLineWriter.save(orderLineContent));
  }

  private Function<Void, Either<ProtostellarError, Void>> validateOrderLineContent(OrderLine orderLineContent) {
    return v -> orderLineContent == null ? Either.left(new OrderRepositoryError()) : Either.right(null);
  }
}
