package com.protostellar.zugplaner.trackandpredict.domain.usecases.maintenance.plan;

import com.protostellar.zugplaner.common.domain.usecases.user.services.PayingHandler;
import com.protostellar.zugplaner.common.errors.InvalidError;
import com.protostellar.zugplaner.common.errors.ProtostellarError;
import com.protostellar.zugplaner.common.model.id.Identifier;
import com.protostellar.zugplaner.common.model.payment.PayingFeature;
import com.protostellar.zugplaner.trackandpredict.domain.repositories.ReadAssetIdentity;
import com.protostellar.zugplaner.trackandpredict.domain.repositories.ReadMaintenancePlan;
import com.protostellar.zugplaner.trackandpredict.domain.repositories.WriteMaintenancePlan;
import com.protostellar.zugplaner.trackandpredict.domain.usecases.maintenance.plan.requests.MaintenancePlanChangeRequest;
import com.protostellar.zugplaner.trackandpredict.domain.usecases.maintenance.type.FindOrCreateMaintenanceType;
import com.protostellar.zugplaner.trackandpredict.model.maintenance.MaintenancePlan;
import com.protostellar.zugplaner.trackandpredict.model.maintenance.MaintenanceType;
import io.vavr.control.Either;

import java.util.function.Function;

public class UpdateMaintenancePlan {
  private final ReadMaintenancePlan readMaintenancePlan;
  private final WriteMaintenancePlan maintenancePlanWriter;
  private final ReadAssetIdentity assetIdentityReader;
  private final PayingHandler payingHandler;
  private final FindOrCreateMaintenanceType findOrCreateMaintenanceType;

  public UpdateMaintenancePlan(ReadMaintenancePlan readMaintenancePlan,
                               WriteMaintenancePlan maintenancePlanWriter,
                               ReadAssetIdentity assetIdentityReader,
                               PayingHandler payingHandler,
                               FindOrCreateMaintenanceType findOrCreateMaintenanceType) {
    this.readMaintenancePlan = readMaintenancePlan;
    this.maintenancePlanWriter = maintenancePlanWriter;
    this.assetIdentityReader = assetIdentityReader;
    this.payingHandler = payingHandler;
    this.findOrCreateMaintenanceType = findOrCreateMaintenanceType;
  }

  public Either<ProtostellarError, MaintenancePlan> perform(
    Identifier userId,
    Identifier maintenancePlanId,
    MaintenancePlanChangeRequest maintenancePlanChangeRequest) {
    return payingHandler.hasProperPayingAccessFor(userId, PayingFeature.MAINTENANCE_FEATURE)
      .flatMap(validateMaintenancePlanChangeRequest(userId, maintenancePlanChangeRequest))
      .flatMap(maintenancePlan -> updateMaintenancePlan(userId, maintenancePlanId, maintenancePlanChangeRequest));
  }

  private Function<Void, Either<ProtostellarError, Void>>
  validateMaintenancePlanChangeRequest(Identifier userId, MaintenancePlanChangeRequest maintenancePlanChangeRequest) {
    if (maintenancePlanChangeRequest.getAssetId() != null) {
      return assetIdentityReader.isResourceInScope(userId, maintenancePlanChangeRequest.getAssetId());
    }
    return v -> Either.right(null);
  }

  private Either<ProtostellarError, MaintenancePlan> validateMaintenancePlan(MaintenancePlan maintenancePlan) {
    if (maintenancePlan.getMaintenanceType() == null
      || maintenancePlan.getMaintenanceType().getType() == null
      || maintenancePlan.getMaintenanceType().getType().isEmpty()) {
      return Either.left(new InvalidError());
    }
    if (maintenancePlan.getFrequency() == null
      || maintenancePlan.getFrequency().getValue() <= 0) {
      return Either.left(new InvalidError());
    }
    if (maintenancePlan.getAlarm() == null) {
      return Either.left(new InvalidError());
    }
    return Either.right(maintenancePlan);
  }

  private Either<ProtostellarError, MaintenancePlan>
  updateMaintenancePlan(Identifier userId, Identifier maintenancePlanId,
                        MaintenancePlanChangeRequest maintenancePlanChangeRequest) {
    Either<ProtostellarError, MaintenancePlan> updatedMaintenancePlan = readMaintenancePlan.findById(maintenancePlanId)
      .map(maintenancePlan -> getUpdatedMaintenancePlan(userId, maintenancePlanChangeRequest, maintenancePlan));
    return updatedMaintenancePlan
      .flatMap(this::validateMaintenancePlan)
      .flatMap(maintenancePlanWriter::update);
  }

  private MaintenancePlan getUpdatedMaintenancePlan(Identifier userId,
                                                    MaintenancePlanChangeRequest maintenancePlanChangeRequest,
                                                    MaintenancePlan maintenancePlan) {
    if (maintenancePlanChangeRequest.getMaintenanceType() != null) {
      MaintenanceType relatedMaintenanceType = findOrCreateMaintenanceType.perform(userId,
        maintenancePlanChangeRequest.getMaintenanceType().getType()).get();
      maintenancePlan = maintenancePlan.withMaintenanceType(relatedMaintenanceType);
    }
    if ((maintenancePlanChangeRequest.getAlarm() != null)) {
      maintenancePlan = maintenancePlan.withAlarm(maintenancePlanChangeRequest.getAlarm());
    }
    if ((maintenancePlanChangeRequest.getFrequency() != null)
      && (maintenancePlanChangeRequest.getFrequency().getValue() != null)
      && (maintenancePlanChangeRequest.getFrequency().getType() != null)) {
      maintenancePlan = maintenancePlan.withFrequency(maintenancePlanChangeRequest.getFrequency());
    }
    if (maintenancePlanChangeRequest.getAssetId() != null) {
      maintenancePlan = maintenancePlan.withAssetId(maintenancePlanChangeRequest.getAssetId());
    }
    if (maintenancePlanChangeRequest.getComment() != null) {
      maintenancePlan = maintenancePlan.withComment(maintenancePlanChangeRequest.getComment());
    }
    return maintenancePlan;
  }
}
