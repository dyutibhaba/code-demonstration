package com.protostellar.zugplaner.marketplace.infra.api.rest;

import com.protostellar.zugplaner.common.domain.usecases.user.services.PayingHandler;
import com.protostellar.zugplaner.common.errors.ProtostellarError;
import com.protostellar.zugplaner.common.infra.authorization.UserPrincipal;
import com.protostellar.zugplaner.common.model.id.Identifier;
import com.protostellar.zugplaner.marketplace.domain.repositories.ReadCustomer;
import com.protostellar.zugplaner.marketplace.domain.repositories.ReadProduct;
import com.protostellar.zugplaner.marketplace.domain.repositories.WriteOrder;
import com.protostellar.zugplaner.marketplace.domain.repositories.WriteOrderLine;
import com.protostellar.zugplaner.marketplace.domain.usecases.CreateOrder;
import com.protostellar.zugplaner.marketplace.domain.usecases.CreateOrderLine;
import com.protostellar.zugplaner.marketplace.infra.api.dto.CreateOrderRequestDTO;
import com.protostellar.zugplaner.marketplace.infra.api.dto.OrderDTO;
import com.protostellar.zugplaner.marketplace.infra.spi.email.service.EmailService;
import com.protostellar.zugplaner.trackandpredict.infra.api.rest.util.UserUtility;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/orders")
public class OrderController {

  private final WriteOrder orderWriter;
  private final WriteOrderLine orderLineWriter;
  private final ReadProduct productReader;
  private final PayingHandler payingHandler;
  private final EmailService emailService;
  private final ReadCustomer customerReader;

  public OrderController(WriteOrder writeOrder,
                         WriteOrderLine orderLineWriter,
                         PayingHandler payingHandler,
                         ReadProduct productReader,
                         EmailService emailService,
                         ReadCustomer customerReader) {
    this.orderWriter = writeOrder;
    this.orderLineWriter = orderLineWriter;
    this.payingHandler = payingHandler;
    this.productReader = productReader;
    this.emailService = emailService;
    this.customerReader = customerReader;
  }

@PostMapping
public ResponseEntity<OrderDTO> createOrder(
  @AuthenticationPrincipal Jwt jwt,
  @RequestBody CreateOrderRequestDTO orderContent
) throws ProtostellarError {
  CreateOrderLine createOrderLine = new CreateOrderLine(payingHandler, orderLineWriter);
  CreateOrder createOrder = new CreateOrder(payingHandler, createOrderLine, orderWriter, productReader, customerReader);
  UserPrincipal userPrincipal = UserPrincipal.from(jwt);
  Identifier userId = UserUtility.getIdentifier(userPrincipal);
  OrderDTO orderDto = createOrder.perform(userId, orderContent.toOrder())
    .map(order -> OrderDTO.from(order).withUserName(userPrincipal.getUsername()))
    .getOrElseThrow(error -> error);
  emailService.sendMail(orderDto, userId);
  return ResponseEntity.ok(orderDto);
  }
}
