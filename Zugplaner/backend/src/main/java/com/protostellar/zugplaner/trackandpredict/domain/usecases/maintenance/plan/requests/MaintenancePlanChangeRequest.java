package com.protostellar.zugplaner.trackandpredict.domain.usecases.maintenance.plan.requests;

import com.protostellar.zugplaner.common.model.id.Identifier;
import com.protostellar.zugplaner.trackandpredict.model.maintenance.Alarm;
import com.protostellar.zugplaner.trackandpredict.model.maintenance.Frequency;
import com.protostellar.zugplaner.trackandpredict.model.maintenance.MaintenanceType;
import lombok.Builder;
import lombok.Data;
import lombok.With;

@Data
@Builder
public class MaintenancePlanChangeRequest {
  @With MaintenanceType maintenanceType;
  @With Alarm alarm;
  @With Frequency frequency;
  @With Identifier assetId;
  @With String comment;
}
