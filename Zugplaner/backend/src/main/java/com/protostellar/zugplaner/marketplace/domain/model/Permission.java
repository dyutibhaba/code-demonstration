package com.protostellar.zugplaner.marketplace.domain.model;

import com.protostellar.zugplaner.common.model.id.Identifier;
import lombok.Data;
import lombok.Value;

@Data
@Value
public class Permission {
  private final Identifier id;
  private final String name;
  private final String description;
}
