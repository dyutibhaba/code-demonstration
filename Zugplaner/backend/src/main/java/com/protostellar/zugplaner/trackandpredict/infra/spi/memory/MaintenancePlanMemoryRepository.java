package com.protostellar.zugplaner.trackandpredict.infra.spi.memory;

import com.protostellar.zugplaner.common.errors.AssociationError;
import com.protostellar.zugplaner.common.errors.MaintenancePlanRepositoryError;
import com.protostellar.zugplaner.common.errors.ProtostellarError;
import com.protostellar.zugplaner.common.model.id.Identifier;
import com.protostellar.zugplaner.trackandpredict.domain.repositories.ReadMaintenancePlan;
import com.protostellar.zugplaner.trackandpredict.domain.repositories.WriteMaintenancePlan;
import com.protostellar.zugplaner.trackandpredict.model.maintenance.Alarm;
import com.protostellar.zugplaner.trackandpredict.model.maintenance.FrequencyType;
import com.protostellar.zugplaner.trackandpredict.model.maintenance.MaintenancePlan;
import io.vavr.control.Either;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.function.Function;
import java.util.stream.Collectors;

public class MaintenancePlanMemoryRepository implements WriteMaintenancePlan, ReadMaintenancePlan {
  private final Map<Identifier, MaintenancePlan> maintenancePlans;

  public MaintenancePlanMemoryRepository() {
    this.maintenancePlans = new HashMap<>();
  }

  @Override
  public Either<ProtostellarError, MaintenancePlan> findById(Identifier id) {

    MaintenancePlan maintenancePlan = maintenancePlans.get(id);
    return maintenancePlan != null
      ? Either.right(maintenancePlan)
      : Either.left(new MaintenancePlanRepositoryError.NotFound());
  }

  @Override
  public Either<ProtostellarError, List<MaintenancePlan>> getAllFor(Identifier assetId) {
    return Either.right(maintenancePlans.values().stream()
      .filter(it -> it.getAssetId().equals(assetId))
      .collect(Collectors.toList())
    );
  }

  @Override
  public Function<Void, Either<ProtostellarError, Void>> isInScope(Identifier maintenancePlanId, Identifier assetId) {
    return v -> this.getAllFor(assetId)
      .map(m -> m.stream().anyMatch(it -> it.getId().equals(maintenancePlanId)))
      .flatMap(b -> Boolean.TRUE.equals(b) ? Either.right(null) : Either.left(new AssociationError()));
  }

  @Override
  public Either<ProtostellarError, MaintenancePlan> save(MaintenancePlan maintenancePlan) {
    MaintenancePlan maintenancePlanToAdd = maintenancePlan;

    if (maintenancePlan.getId().isEmpty()) {
      Identifier id = Identifier.from(UUID.randomUUID());
      maintenancePlanToAdd = maintenancePlan.withId(id);
    }
    maintenancePlanToAdd = maintenancePlanToAdd.withCreationDate(LocalDateTime.now());
    maintenancePlans.put(maintenancePlanToAdd.getId(), maintenancePlanToAdd);
    return Either.right(maintenancePlanToAdd);
  }

  @Override
  public Either<ProtostellarError, MaintenancePlan> update(MaintenancePlan updatedMaintenancePlan) {
    MaintenancePlan maintenancePlan = maintenancePlans.get(updatedMaintenancePlan.getId());
    if (maintenancePlan != null) {
      maintenancePlans.put(maintenancePlan.getId(), updatedMaintenancePlan);
      return Either.right(updatedMaintenancePlan);
    } else {
      return Either.left(new MaintenancePlanRepositoryError.NotFound());
    }
  }

  @Override
  public Either<ProtostellarError, Boolean> delete(Identifier id) {
    MaintenancePlan maintenancePlan = maintenancePlans.get(id);
    return maintenancePlans.remove(id, maintenancePlan)
      ? Either.right(true)
      : Either.left(new MaintenancePlanRepositoryError.NotFound());
  }

  @Override
  public Either<ProtostellarError, Boolean> updateAlarmDue(Identifier maintenancePlanId, Boolean alarmDue) {
    MaintenancePlan maintenancePlan = maintenancePlans
      .computeIfPresent(maintenancePlanId, (k, v) -> v.withAlarm(Alarm.from(Boolean.TRUE, alarmDue)));
    maintenancePlans.put(maintenancePlanId, maintenancePlan);
    return Either.right(Boolean.TRUE);
  }

  @Override
  public Either<ProtostellarError, List<MaintenancePlan>> findAllByFrequencyTypeAndAlarmIsSet(
    FrequencyType frequencyType, Boolean alarmIsSet) {
    return Either.right(maintenancePlans.values().stream()
      .filter(mp -> mp.getFrequency().getType().equals(frequencyType) && mp.getAlarm().getIsSet().equals(alarmIsSet))
      .collect(Collectors.toList())
    );
  }

}
