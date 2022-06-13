package com.protostellar.zugplaner.trackandpredict.model.maintenance;

import com.protostellar.zugplaner.common.model.id.Identifier;
import lombok.Data;
import lombok.Value;
import lombok.With;

import java.time.LocalDateTime;

@Data(staticConstructor = "from")
@Value
public class MaintenancePlan {
  @With Identifier id;
  @With MaintenanceType maintenanceType;
  @With Alarm alarm;
  @With Frequency frequency;
  @With Identifier assetId;
  @With Identifier creatorId;
  @With String comment;
  @With LocalDateTime creationDate;
  @With LocalDateTime lastUpdateDateTime;

  public static MaintenancePlan empty() {
    return new MaintenancePlan(
      Identifier.empty(),
      MaintenanceType.empty(),
      Alarm.empty(),
      Frequency.unknown(),
      Identifier.empty(),
      Identifier.empty(),
      "",
      null,
      null
    );
  }
}
