package com.protostellar.zugplaner.marketplace.domain.model;

import com.protostellar.zugplaner.common.model.id.Identifier;
import lombok.Data;
import lombok.Value;

import java.math.BigDecimal;

@Data(staticConstructor = "from")
@Value
public class Article {
  Identifier id;
  Identifier customerId;
  String thalesNr;
  Boolean detectorBar;
  Boolean trailable;
  Boolean connectorPointDetector;
  Integer throwingForce;
  Integer throwingStroke;
  BigDecimal maxThrowingTime;
  Boolean phasePlug;
  Boolean additionalContact;
  String standardApplication; // perhaps an enum
  BigDecimal price;

}
