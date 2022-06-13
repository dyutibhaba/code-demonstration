package com.protostellar.zugplaner.trackandpredict.domain.usecases.maintenance.type;

import com.protostellar.zugplaner.common.domain.usecases.user.services.AuthorizationHandler;
import com.protostellar.zugplaner.common.domain.usecases.user.services.PayingHandler;
import com.protostellar.zugplaner.common.domain.usecases.user.services.Permission;
import com.protostellar.zugplaner.common.errors.ProtostellarError;
import com.protostellar.zugplaner.common.model.authorization.Module;
import com.protostellar.zugplaner.common.model.id.Identifier;
import com.protostellar.zugplaner.common.model.payment.PayingFeature;
import com.protostellar.zugplaner.trackandpredict.domain.repositories.*;
import io.vavr.control.Either;

public class DeleteMaintenanceType {
  private final AuthorizationHandler authorizationHandler;
  private final PayingHandler payingHandler;
  private final WriteMaintenanceType writeMaintenanceType;
  private final ReadMaintenanceType readMaintenanceType;

  public DeleteMaintenanceType(
    AuthorizationHandler authorizationHandler, PayingHandler payingHandler,
    WriteMaintenanceType writeMaintenanceType,
    ReadMaintenanceType readMaintenanceType
  ) {
    this.authorizationHandler = authorizationHandler;
    this.payingHandler = payingHandler;
    this.writeMaintenanceType = writeMaintenanceType;
    this.readMaintenanceType = readMaintenanceType;
  }

  public Either<ProtostellarError, Boolean> perform(Identifier userId, Identifier maintenanceTypeId) {
    return payingHandler.hasProperPayingAccessFor(userId, PayingFeature.MAINTENANCE_FEATURE)
      .flatMap(v -> authorizationHandler.checkPermission(userId, Permission.DELETE_MAINTENANCE_PLAN, Module.TRACK_AND_PREDICT))
      .flatMap(v -> readMaintenanceType.findById(maintenanceTypeId))
      .flatMap(c -> writeMaintenanceType.delete(maintenanceTypeId));
  }
}
