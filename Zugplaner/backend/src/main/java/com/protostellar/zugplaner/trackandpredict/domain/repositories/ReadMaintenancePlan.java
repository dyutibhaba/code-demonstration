package com.protostellar.zugplaner.trackandpredict.domain.repositories;

import com.protostellar.zugplaner.common.errors.ProtostellarError;
import com.protostellar.zugplaner.common.model.id.Identifier;
import com.protostellar.zugplaner.trackandpredict.model.maintenance.FrequencyType;
import com.protostellar.zugplaner.trackandpredict.model.maintenance.MaintenancePlan;
import io.vavr.control.Either;

import java.util.List;
import java.util.function.Function;

public interface ReadMaintenancePlan {
  Either<ProtostellarError, MaintenancePlan> findById(Identifier id);

  Either<ProtostellarError, List<MaintenancePlan>> getAllFor(Identifier assetId);

  Function<Void, Either<ProtostellarError, Void>> isInScope(Identifier maintenancePlanId, Identifier assetId);

  Either<ProtostellarError, List<MaintenancePlan>> findAllByFrequencyTypeAndAlarmIsSet(FrequencyType frequencyType, Boolean alarmIsSet);

}
