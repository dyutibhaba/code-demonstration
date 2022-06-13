package com.protostellar.zugplaner.trackandpredict.infra.spi.memory;

import com.protostellar.zugplaner.common.errors.MaintenancePlanRepositoryError;
import com.protostellar.zugplaner.common.errors.MaintenanceTypeRepositoryError;
import com.protostellar.zugplaner.common.errors.ProtostellarError;
import com.protostellar.zugplaner.common.model.id.Identifier;
import com.protostellar.zugplaner.trackandpredict.domain.repositories.ReadMaintenanceType;
import com.protostellar.zugplaner.trackandpredict.domain.repositories.WriteMaintenanceType;
import com.protostellar.zugplaner.trackandpredict.model.maintenance.MaintenanceType;
import io.vavr.control.Either;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class MaintenanceTypeMemoryRepository implements WriteMaintenanceType, ReadMaintenanceType {
  private final Map<Identifier, MaintenanceType> maintenanceTypes;

  public MaintenanceTypeMemoryRepository() {
    this.maintenanceTypes = new HashMap<>();
  }

  @Override
  public Either<ProtostellarError, MaintenanceType> findById(Identifier id) {

    MaintenanceType maintenanceType = maintenanceTypes.get(id);
    return maintenanceType != null
      ? Either.right(maintenanceType)
      : Either.left(new MaintenancePlanRepositoryError.NotFound());
  }

  @Override
  public Either<ProtostellarError, MaintenanceType> findByType(String type) {
    return maintenanceTypes.values().stream()
      .filter(maintenanceType -> maintenanceType.getType().equals(type))
      .findFirst()
      .<Either<ProtostellarError, MaintenanceType>> map(Either::right)
      .orElse(Either.left(new MaintenanceTypeRepositoryError.NotFound()));
  }

  @Override
  public Either<ProtostellarError, MaintenanceType> save(MaintenanceType maintenanceType) {
    MaintenanceType toAdd = maintenanceType;
    if (toAdd.getId().isEmpty()) {
      toAdd.withId(Identifier.from(UUID.randomUUID()));
    }
    maintenanceTypes.put(toAdd.getId(), toAdd);
    return Either.right(toAdd);
  }

  @Override
  public Either<ProtostellarError, MaintenanceType> update(MaintenanceType updatedMaintenanceType) {
    MaintenanceType maintenanceType = maintenanceTypes.get(updatedMaintenanceType.getId());
    if (maintenanceType == null) {
      return Either.left(new MaintenanceTypeRepositoryError.NotFound());
    }
    maintenanceTypes.put(maintenanceType.getId(), updatedMaintenanceType);
    return Either.right(updatedMaintenanceType);
  }

  @Override
  public Either<ProtostellarError, Boolean> delete(Identifier maintenanceTypeId) {
    MaintenanceType maintenanceType = maintenanceTypes.get(maintenanceTypeId);
    return maintenanceTypes.remove(maintenanceTypeId, maintenanceType)
      ? Either.right(true)
      : Either.left(new MaintenanceTypeRepositoryError.NotFound());
  }
}
