package com.protostellar.zugplaner.marketplace.domain.usecases;

import com.protostellar.zugplaner.common.domain.usecases.user.services.PayingHandler;
import com.protostellar.zugplaner.common.errors.OrderRepositoryError;
import com.protostellar.zugplaner.common.errors.ProtostellarError;
import com.protostellar.zugplaner.common.model.id.Identifier;
import com.protostellar.zugplaner.common.model.payment.PayingFeature;
import com.protostellar.zugplaner.common.utils.StringUtilities;
import com.protostellar.zugplaner.marketplace.domain.model.Customer;
import com.protostellar.zugplaner.marketplace.domain.model.Order;
import com.protostellar.zugplaner.marketplace.domain.model.OrderLine;
import com.protostellar.zugplaner.marketplace.domain.model.Product;
import com.protostellar.zugplaner.marketplace.domain.model.ProductArticleWithPrice;
import com.protostellar.zugplaner.marketplace.domain.repositories.ReadCustomer;
import com.protostellar.zugplaner.marketplace.domain.repositories.ReadProduct;
import com.protostellar.zugplaner.marketplace.domain.repositories.WriteOrder;
import io.vavr.control.Either;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

public class CreateOrder {

  public static final int SIZE_OF_ORDER_NUMBER_CHUNK = 7;
  private final PayingHandler payingHandler;
  private final CreateOrderLine createOrderLine;
  private final WriteOrder orderWriter;
  private final ReadProduct productReader;
  private final ReadCustomer customerReader;

  public CreateOrder(PayingHandler payingHandler,
                     CreateOrderLine createOrderLine,
                     WriteOrder orderWriter,
                     ReadProduct productReader,
                     ReadCustomer customerReader) {
    this.payingHandler = payingHandler;
    this.createOrderLine = createOrderLine;
    this.orderWriter = orderWriter;
    this.productReader = productReader;
    this.customerReader = customerReader;
  }

  public Either<ProtostellarError, Order> perform(
    Identifier userId,
    Order orderContent) {
    Either<ProtostellarError, Order> savedOrder = payingHandler
      .hasProperPayingAccessFor(userId, PayingFeature.MARKETPLACE_FEATURE)
      .flatMap(validateOrder(orderContent))
      .flatMap(createOrder(orderContent.withCreatorId(userId)));
    return savedOrder
      .flatMap(order -> persistOrderLines(userId, order.getId(), orderContent))
      .map(prepareOrder(savedOrder.get()));
  }

  private Function<List<OrderLine>, Order> prepareOrder(Order order) {
    return order::withOrderLines;
  }

  private Either<ProtostellarError, List<OrderLine>> persistOrderLines(Identifier userId,
                                                                      Identifier orderId,
                                                                      Order order) {
    Either<ProtostellarError, Product> productEither = productReader
      .findByNameAndModel(order.getProductName(), order.getModelName());
    List<OrderLine> savedOrderLines = order.getOrderLines()
      .stream()
      .map(orderLine -> orderLine.withOrderId(orderId)
        .withProductArticleWithPrice(orderLine
          .getProductArticleWithPrice()
          .withProductId(productEither.get().getId())))
      .map(orderLine -> createOrderLine.perform(userId, orderLine))
      .map(Either::get)
      .collect(Collectors.toList());
    setThalesNr(order, savedOrderLines);
    return Either.right(savedOrderLines);
  }

  private void setThalesNr(Order order, List<OrderLine> savedOrderLines) {
    savedOrderLines.stream().forEach(e -> {
      order.getOrderLines().stream().forEach(o -> {
        ProductArticleWithPrice productArticleWithPrice = e.getProductArticleWithPrice();
        if(productArticleWithPrice.getArticleId().equals(o.getProductArticleWithPrice().getArticleId())) {
          ProductArticleWithPrice newProductArticleWithPrice = productArticleWithPrice.withThalesNr(
            o.getProductArticleWithPrice().getThalesNr());
          e.setProductArticleWithPrice(newProductArticleWithPrice);
        }
      });
    });
  }

  private Function<Void, Either<ProtostellarError, Order>> createOrder(Order order) {
    return v -> {
      order.setCustomer(order.getCustomer());
      order.setOrderNumber(StringUtilities.randomAlphanumeric(SIZE_OF_ORDER_NUMBER_CHUNK));
      Order savedOrder = orderWriter.save(order).get();
      Customer customer = customerReader.findById(savedOrder.getCustomer().getId()).get();
      savedOrder.setCustomer(customer);
      return Either.right(savedOrder);
    };
  }

  private Function<Void, Either<ProtostellarError, Void>> validateOrder(Order orderContent) {
    return v -> orderContent == null ? Either.left(new OrderRepositoryError()) : Either.right(null);
  }
}
