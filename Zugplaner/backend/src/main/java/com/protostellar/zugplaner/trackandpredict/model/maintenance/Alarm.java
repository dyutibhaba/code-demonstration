package com.protostellar.zugplaner.trackandpredict.model.maintenance;

import lombok.Data;
import lombok.Value;
import lombok.With;

@Data(staticConstructor = "from")
@Value
public class Alarm {
  @With Boolean isSet;
  @With Boolean isDue;

  public static Alarm empty() {
    return new Alarm(
      Boolean.FALSE,
      Boolean.FALSE);
  }
}
