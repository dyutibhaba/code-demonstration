package com.protostellar.zugplaner.marketplace.domain.model;

import com.protostellar.zugplaner.common.model.id.Identifier;
import lombok.Builder;
import lombok.Data;
import lombok.Value;

@Data
@Value
@Builder
public class Product {
  private final Identifier id;
  private final String name;
  private final String model;
  private final String description;
}
