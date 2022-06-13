package com.protostellar.zugplaner.trackandpredict.model.maintenance;

import com.protostellar.zugplaner.common.model.id.Identifier;
import lombok.Data;
import lombok.Value;
import lombok.With;

@Data(staticConstructor = "from")
@Value
public class MaintenanceType {
  @With Identifier id;
  @With String type;

  public static MaintenanceType empty() {
    return new MaintenanceType(Identifier.empty(), "");
  }
}
