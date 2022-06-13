package com.protostellar.zugplaner.trackandpredict.infra.api.dto;

import com.protostellar.zugplaner.common.model.id.Identifier;
import com.protostellar.zugplaner.trackandpredict.domain.usecases.maintenance.plan.requests.MaintenancePlanChangeRequest;
import com.protostellar.zugplaner.trackandpredict.model.maintenance.Alarm;
import com.protostellar.zugplaner.trackandpredict.model.maintenance.Frequency;
import com.protostellar.zugplaner.trackandpredict.model.maintenance.MaintenanceType;
import lombok.Data;

import java.util.UUID;

@Data
public class PatchMaintenancePlanDTO {
  public final String maintenanceType;
  public final Alarm alarm;
  public final Frequency frequency;
  public final UUID assetId;
  public final String comment;

  public MaintenancePlanChangeRequest toMaintenancePlanChangeRequest() {
    return MaintenancePlanChangeRequest.builder()
      .maintenanceType(MaintenanceType.empty().withType(maintenanceType))
      .alarm(alarm)
      .frequency(frequency)
      .assetId(Identifier.from(assetId))
      .comment(comment)
      .build();
  }
}
