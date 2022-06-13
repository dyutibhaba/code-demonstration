package com.protostellar.zugplaner.trackandpredict.infra.spi.dao;

import com.protostellar.zugplaner.common.model.id.Identifier;
import com.protostellar.zugplaner.trackandpredict.model.maintenance.MaintenanceType;
import lombok.Data;

import java.util.UUID;

@Data
public class MaintenanceTypeDAO {
  UUID id;
  String type;

  public MaintenanceType toMaintenanceType() {
    return MaintenanceType.empty()
      .withId(Identifier.from(id))
      .withType(type);
  }
}
