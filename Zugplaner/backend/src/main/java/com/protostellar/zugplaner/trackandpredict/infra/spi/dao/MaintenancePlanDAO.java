package com.protostellar.zugplaner.trackandpredict.infra.spi.dao;

import com.protostellar.zugplaner.common.model.id.Identifier;
import com.protostellar.zugplaner.trackandpredict.model.maintenance.*;
import com.protostellar.zugplaner.trackandpredict.model.maintenance.Alarm;
import com.protostellar.zugplaner.trackandpredict.model.maintenance.Frequency;
import com.protostellar.zugplaner.trackandpredict.model.maintenance.FrequencyType;
import com.protostellar.zugplaner.trackandpredict.model.maintenance.MaintenancePlan;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
public class MaintenancePlanDAO {
  UUID id;
  UUID maintenanceTypeId;
  String type; // todo remove this unnecesary field
  Boolean alarmSet;
  Boolean alarmDue;
  Integer frequencyValue;
  Integer frequencyType;
  UUID assetId;
  UUID creatorId;
  String comment;
  LocalDateTime sysInsertTS;
  LocalDateTime sysUpdateTS;

  public MaintenancePlan toMaintenancePlan() {
    return MaintenancePlan.from(
      Identifier.from(id),
      MaintenanceType.from(Identifier.from(maintenanceTypeId), type),
      Alarm.from(alarmSet,alarmDue),
      Frequency.from(frequencyValue, FrequencyType.from(frequencyType)),
      Identifier.from(assetId),
      Identifier.from(creatorId),
      comment,
      sysInsertTS,
      sysUpdateTS
    );
  }
}
