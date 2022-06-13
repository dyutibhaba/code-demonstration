package com.protostellar.zugplaner.trackandpredict.domain.usecases.maintenance.plan;

import com.protostellar.zugplaner.common.domain.usecases.user.services.PayingHandler;
import com.protostellar.zugplaner.common.errors.InvalidError;
import com.protostellar.zugplaner.common.errors.ProtostellarError;
import com.protostellar.zugplaner.common.model.id.Identifier;
import com.protostellar.zugplaner.common.model.payment.PayingFeature;
import com.protostellar.zugplaner.trackandpredict.domain.repositories.ReadAssetIdentity;
import com.protostellar.zugplaner.trackandpredict.domain.repositories.WriteMaintenancePlan;
import com.protostellar.zugplaner.trackandpredict.domain.usecases.maintenance.type.FindOrCreateMaintenanceType;
import com.protostellar.zugplaner.trackandpredict.model.maintenance.Alarm;
import com.protostellar.zugplaner.trackandpredict.model.maintenance.Frequency;
import com.protostellar.zugplaner.trackandpredict.model.maintenance.MaintenancePlan;
import com.protostellar.zugplaner.trackandpredict.model.maintenance.MaintenanceType;
import io.vavr.control.Either;

import java.util.function.Function;

public class CreateMaintenancePlan {
  private final WriteMaintenancePlan maintenancePlanWriter;
  private final ReadAssetIdentity assetIdentityReader;
  private final PayingHandler payingHandler;
  private final FindOrCreateMaintenanceType findOrCreateMaintenanceType;

  public CreateMaintenancePlan(WriteMaintenancePlan maintenancePlanWriter,
                               ReadAssetIdentity assetIdentityReader,
                               PayingHandler payingHandler,
                               FindOrCreateMaintenanceType findOrCreateMaintenanceType) {
    this.maintenancePlanWriter = maintenancePlanWriter;
    this.assetIdentityReader = assetIdentityReader;
    this.payingHandler = payingHandler;
    this.findOrCreateMaintenanceType = findOrCreateMaintenanceType;
  }

  public Either<ProtostellarError, MaintenancePlan> perform(
    Identifier userId,
    Identifier assetId,
    String maintenanceType,
    Frequency frequency,
    Alarm alarm,
    String comment) {
    return payingHandler.hasProperPayingAccessFor(userId, PayingFeature.MAINTENANCE_FEATURE)
      .flatMap(assetIdentityReader.isResourceInScope(userId, assetId))
      .flatMap(validateMaintenancePlan(frequency, alarm))
      .map(v->createMaintenancePlan(userId, assetId, maintenanceType, frequency,alarm, comment))
      .flatMap(maintenancePlanWriter::save);
  }

  private Function<Void, Either<ProtostellarError, Void>> validateMaintenancePlan(Frequency frequency,
                                                                                  Alarm alarm) {
   return v -> {
     if (frequency == null || frequency.getValue() <= 0) return Either.left(new InvalidError());
     if (alarm == null) return Either.left(new InvalidError());
     return Either.right(null);
   };
  }

  private MaintenancePlan createMaintenancePlan(Identifier userId,
                                                Identifier assetId,
                                                String maintenanceType,
                                                Frequency frequency,
                                                Alarm alarm, String comment) {

    MaintenanceType relatedMaintenanceType = findOrCreateMaintenanceType.perform(userId, maintenanceType).get();

    return MaintenancePlan
      .empty()
      .withMaintenanceType(relatedMaintenanceType)
      .withAlarm(alarm)
      .withFrequency(frequency)
      .withAssetId(assetId)
      .withCreatorId(userId)
      .withComment(comment);
  }
}
