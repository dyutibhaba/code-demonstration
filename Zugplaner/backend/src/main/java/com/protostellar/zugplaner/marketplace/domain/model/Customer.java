package com.protostellar.zugplaner.marketplace.domain.model;

import com.protostellar.zugplaner.common.model.id.Identifier;
import lombok.Builder;
import lombok.Data;
import lombok.Value;

@Data
@Value
@Builder
public class Customer {
  Identifier id; // usergroup id
  String name;
}
