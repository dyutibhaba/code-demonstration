package com.protostellar.zugplaner.trackandpredict.domain.usecases.maintenance.plan;

import com.protostellar.zugplaner.common.domain.usecases.user.services.PayingHandler;
import com.protostellar.zugplaner.common.errors.ProtostellarError;
import com.protostellar.zugplaner.common.model.id.Identifier;
import com.protostellar.zugplaner.common.model.payment.PayingFeature;
import com.protostellar.zugplaner.trackandpredict.domain.repositories.ReadAssetIdentity;
import com.protostellar.zugplaner.trackandpredict.domain.repositories.ReadMaintenancePlan;
import com.protostellar.zugplaner.trackandpredict.model.maintenance.MaintenancePlan;
import io.vavr.control.Either;

import java.util.List;

public class GetAllMaintenancePlans {
  private final ReadMaintenancePlan readMaintenancePlan;
  private final ReadAssetIdentity assetIdentityReader;
  private final PayingHandler payingHandler;

  public GetAllMaintenancePlans(ReadMaintenancePlan readMaintenancePlan,
                                ReadAssetIdentity assetIdentityReader,
                                PayingHandler payingHandler) {
    this.readMaintenancePlan = readMaintenancePlan;
    this.assetIdentityReader = assetIdentityReader;
    this.payingHandler = payingHandler;
  }

  public Either<ProtostellarError, List<MaintenancePlan>> perform(
    Identifier assetId,
    Identifier userId
  ) {
    return payingHandler.hasProperPayingAccessFor(userId, PayingFeature.MAINTENANCE_FEATURE)
      .flatMap(assetIdentityReader.isResourceInScope(userId, assetId))
      .flatMap(v -> readMaintenancePlan.getAllFor(assetId));
  }
}
