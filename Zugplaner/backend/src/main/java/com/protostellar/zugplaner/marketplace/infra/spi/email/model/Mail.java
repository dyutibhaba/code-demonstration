package com.protostellar.zugplaner.marketplace.infra.spi.email.model;

import lombok.Builder;
import lombok.Data;
import lombok.Value;

@Data
@Value
@Builder
public class Mail {
  String Id;
  String from;
  String to;
  String message;

  public Mail(String id, String from, String to, String message) {
    Id = id;
    this.from = from;
    this.to = to;
    this.message = message;
  }

  public String getId() {
    return Id;
  }

  public String getFrom() {
    return from;
  }

  public String getTo() {
    return to;
  }

  public String getMessage() {
    return message;
  }
}
