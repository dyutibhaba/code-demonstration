package com.protostellar.zugplaner.trackandpredict.domain.usecases.maintenance.plan;

import com.protostellar.zugplaner.common.domain.usecases.user.services.AuthorizationHandler;
import com.protostellar.zugplaner.common.domain.usecases.user.services.PayingHandler;
import com.protostellar.zugplaner.common.domain.usecases.user.services.Permission;
import com.protostellar.zugplaner.common.errors.ProtostellarError;
import com.protostellar.zugplaner.common.model.authorization.Module;
import com.protostellar.zugplaner.common.model.id.Identifier;
import com.protostellar.zugplaner.common.model.payment.PayingFeature;
import com.protostellar.zugplaner.trackandpredict.domain.repositories.*;
import io.vavr.control.Either;

public class DeleteMaintenancePlan {
  private final AuthorizationHandler authorizationHandler;
  private final PayingHandler payingHandler;
  private final WriteMaintenancePlan writeMaintenancePlan;
  private final ReadMaintenancePlan readMaintenancePlan;
  private final ReadAssetIdentity assetIdentityReader;

  public DeleteMaintenancePlan(
    AuthorizationHandler authorizationHandler, PayingHandler payingHandler,
    WriteMaintenancePlan writeMaintenancePlan,
    ReadMaintenancePlan readMaintenancePlan,
    ReadAssetIdentity assetIdentityReader
  ) {
    this.authorizationHandler = authorizationHandler;
    this.payingHandler = payingHandler;
    this.writeMaintenancePlan = writeMaintenancePlan;
    this.readMaintenancePlan = readMaintenancePlan;
    this.assetIdentityReader = assetIdentityReader;
  }

  public Either<ProtostellarError, Boolean> perform(Identifier userId, Identifier maintenancePlanId) {
    return payingHandler.hasProperPayingAccessFor(userId, PayingFeature.MAINTENANCE_FEATURE)
      .flatMap(v -> authorizationHandler.checkPermission(userId, Permission.DELETE_MAINTENANCE_PLAN, Module.TRACK_AND_PREDICT))
      .flatMap(v -> readMaintenancePlan.findById(maintenancePlanId))
      .map(mp -> assetIdentityReader.isResourceInScope(userId, mp.getAssetId()))
      .flatMap(c -> writeMaintenancePlan.delete(maintenancePlanId));
  }
}
