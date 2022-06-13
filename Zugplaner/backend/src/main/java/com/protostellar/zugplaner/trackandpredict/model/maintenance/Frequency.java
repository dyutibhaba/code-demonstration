package com.protostellar.zugplaner.trackandpredict.model.maintenance;

import lombok.Data;
import lombok.Value;
import lombok.With;


@Data(staticConstructor = "from")
@Value
public class Frequency {
  @With
  Integer value;
  @With
  FrequencyType type;

  public static Frequency unknown() {
    return new Frequency(
      -1,
      FrequencyType.UNKNOWN
    );
  }
}
