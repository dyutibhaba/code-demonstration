package com.protostellar.zugplaner.trackandpredict.domain.repositories;

import com.protostellar.zugplaner.common.errors.ProtostellarError;
import com.protostellar.zugplaner.common.model.id.Identifier;
import com.protostellar.zugplaner.trackandpredict.model.maintenance.MaintenanceType;
import io.vavr.control.Either;

public interface WriteMaintenanceType {

  Either<ProtostellarError, MaintenanceType> save(MaintenanceType maintenanceType);

  Either<ProtostellarError, MaintenanceType> update(MaintenanceType updatedMaintenanceType);

  Either<ProtostellarError, Boolean> delete(Identifier maintenanceTypeId);

}
