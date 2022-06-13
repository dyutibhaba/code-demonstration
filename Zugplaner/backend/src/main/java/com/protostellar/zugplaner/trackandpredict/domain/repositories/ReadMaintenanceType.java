package com.protostellar.zugplaner.trackandpredict.domain.repositories;

import com.protostellar.zugplaner.common.errors.ProtostellarError;
import com.protostellar.zugplaner.common.model.id.Identifier;
import com.protostellar.zugplaner.trackandpredict.model.maintenance.MaintenanceType;
import io.vavr.control.Either;

public interface ReadMaintenanceType {
  Either<ProtostellarError, MaintenanceType> findById(Identifier id);
  Either<ProtostellarError, MaintenanceType> findByType(String type);
}
