package com.protostellar.zugplaner.trackandpredict.domain.usecases.maintenance.type;

import com.protostellar.zugplaner.common.domain.usecases.user.services.PayingHandler;
import com.protostellar.zugplaner.common.errors.InvalidError;
import com.protostellar.zugplaner.common.errors.ProtostellarError;
import com.protostellar.zugplaner.common.model.id.Identifier;
import com.protostellar.zugplaner.common.model.payment.PayingFeature;
import com.protostellar.zugplaner.trackandpredict.domain.repositories.ReadMaintenanceType;
import com.protostellar.zugplaner.trackandpredict.domain.repositories.WriteMaintenanceType;
import com.protostellar.zugplaner.trackandpredict.model.maintenance.MaintenanceType;
import io.vavr.control.Either;

import java.util.function.Function;

public class FindOrCreateMaintenanceType {
  private final WriteMaintenanceType maintenanceTypeWriter;
  private final ReadMaintenanceType maintenanceTypeReader;
  private final PayingHandler payingHandler;

  public FindOrCreateMaintenanceType(WriteMaintenanceType maintenanceTypeWriter,
                                     ReadMaintenanceType maintenanceTypeReader,
                                     PayingHandler payingHandler) {
    this.maintenanceTypeWriter = maintenanceTypeWriter;
    this.maintenanceTypeReader = maintenanceTypeReader;
    this.payingHandler = payingHandler;
  }

  public Either<ProtostellarError, MaintenanceType> perform(
    Identifier userId,
    String maintenanceType) {
    return payingHandler.hasProperPayingAccessFor(userId, PayingFeature.MAINTENANCE_FEATURE)
      .flatMap(validateMaintenancePlan(maintenanceType))
      .flatMap(createOrReuseMaintenanceType(maintenanceType));
  }

  private Function<Void, Either<ProtostellarError, Void>> validateMaintenancePlan(String maintenanceType) {
   return v -> {
     if (maintenanceType == null || maintenanceType.isEmpty()) return Either.left(new InvalidError());
     return Either.right(null);
   };
  }

  private Function<Void, Either<ProtostellarError, MaintenanceType>> createOrReuseMaintenanceType(String type) {
    return v -> {
      Either<ProtostellarError, MaintenanceType> existingMaintenanceType = maintenanceTypeReader.findByType(type);
      if (existingMaintenanceType.isLeft()) {
        return maintenanceTypeWriter.save(MaintenanceType.empty().withType(type));
      }
      return existingMaintenanceType;
    };
  }
}
