package com.protostellar.zugplaner.trackandpredict.domain.repositories;

import com.protostellar.zugplaner.common.errors.ProtostellarError;
import com.protostellar.zugplaner.common.model.id.Identifier;
import com.protostellar.zugplaner.trackandpredict.model.maintenance.MaintenancePlan;
import io.vavr.control.Either;

public interface WriteMaintenancePlan {

  Either<ProtostellarError, MaintenancePlan> save(MaintenancePlan maintenancePlan);

  Either<ProtostellarError, MaintenancePlan> update(MaintenancePlan updatedMaintenancePlan);

  Either<ProtostellarError, Boolean> delete(Identifier maintenancePlanId);

  Either<ProtostellarError, Boolean> updateAlarmDue(Identifier maintenancePlanId, Boolean alarmDue);

}
