package com.protostellar.zugplaner.trackandpredict.domain.usecases.maintenance;

import com.protostellar.zugplaner.common.errors.ProtostellarError;
import com.protostellar.zugplaner.common.infra.spi.memory.UserMemoryRepository;
import com.protostellar.zugplaner.common.model.id.Identifier;
import com.protostellar.zugplaner.trackandpredict.domain.repositories.ReadChecklist;
import com.protostellar.zugplaner.trackandpredict.domain.repositories.ReadMaintenancePlan;
import com.protostellar.zugplaner.trackandpredict.domain.repositories.WriteMaintenancePlan;
import com.protostellar.zugplaner.trackandpredict.domain.repositories.WriteUser;
import com.protostellar.zugplaner.trackandpredict.domain.usecases.maintenance.plan.CalculateAlarmDueTypeCalenderDays;
import com.protostellar.zugplaner.trackandpredict.domain.user.User;
import com.protostellar.zugplaner.trackandpredict.infra.spi.memory.AssetIdentityMemoryRepository;
import com.protostellar.zugplaner.trackandpredict.infra.spi.memory.ChecklistMemoryRepository;
import com.protostellar.zugplaner.trackandpredict.infra.spi.memory.MaintenancePlanMemoryRepository;
import com.protostellar.zugplaner.trackandpredict.model.AssetIdentity;
import com.protostellar.zugplaner.trackandpredict.model.maintenance.Alarm;
import com.protostellar.zugplaner.trackandpredict.model.maintenance.Frequency;
import com.protostellar.zugplaner.trackandpredict.model.maintenance.FrequencyType;
import com.protostellar.zugplaner.trackandpredict.model.maintenance.MaintenancePlan;
import com.protostellar.zugplaner.trackandpredict.model.maintenance.MaintenanceType;
import com.protostellar.zugplaner.trackandpredict.models.UserProfiles;
import com.protostellar.zugplaner.utils.PicoFactoryTestConfiguration;
import io.vavr.control.Either;

import static org.assertj.core.api.Assertions.assertThat;

public class CalculateAlarmDueDSL extends PicoFactoryTestConfiguration {

  private MaintenancePlan maintenancePlan;
  private AssetIdentity asset;
  private User user;

  private final WriteUser userWriter = getComponentInstance(UserMemoryRepository.class);
  private final ReadChecklist readChecklist = getComponentInstance(ChecklistMemoryRepository.class);
  private final MaintenancePlanMemoryRepository maintenancePlanMemoryRepository =
    getComponentInstance(MaintenancePlanMemoryRepository.class);
  private final ReadMaintenancePlan maintenancePlanReader = getComponentInstance(MaintenancePlanMemoryRepository.class);
  private final WriteMaintenancePlan maintenancePlanWrite = getComponentInstance(MaintenancePlanMemoryRepository.class);
  private final AssetIdentityMemoryRepository assetIdentityRepo = getComponentInstance(AssetIdentityMemoryRepository.class);
  private final CalculateAlarmDueTypeCalenderDays calculateAlarmDueTypeCalenderDays;

  public CalculateAlarmDueDSL() {
    this.calculateAlarmDueTypeCalenderDays = CalculateAlarmDueTypeCalenderDays
      .with(readChecklist, maintenancePlanReader, maintenancePlanWrite);
  }

  private CalculateAlarmDueDSL.When whenClause = new CalculateAlarmDueDSL.When();
  private CalculateAlarmDueDSL.Then thenClause = new CalculateAlarmDueDSL.Then(whenClause);

  public CalculateAlarmDueDSL.When when() {
    return whenClause;
  }


  public CalculateAlarmDueDSL a_user() {
    user = UserProfiles.standardUserInTrackAndPredict();
    userWriter.save(user, user.getEmail());
    return this;
  }

  public CalculateAlarmDueDSL with_an_asset() {
    //randomUserExists();
    //userGroupExists();
    asset = assetIdentityRepo.createAsset("new asset").get();
    //assetIdentityRepo.associateAssetToUserGroup(asset, savedUserGroup);
    return this;
  }

  public CalculateAlarmDueDSL with_a_frequency_type_calender_days() {
    maintenancePlan = maintenancePlanMemoryRepository.save(MaintenancePlan.empty()
      .withMaintenanceType(MaintenanceType.from(Identifier.empty(), "test 1"))
      .withAssetId(asset.getId())
      .withCreatorId(user.getId())
      .withAlarm(Alarm.from(true, false))
      .withFrequency(Frequency.from(1, FrequencyType.CALENDAR_DAYS))
      .withComment("comment test 1")).get();
    return this;
  }

  public CalculateAlarmDueDSL with_a_frequency_type_days_in_operation() {
    maintenancePlan = maintenancePlanMemoryRepository.save(MaintenancePlan.empty()
      .withMaintenanceType(MaintenanceType.from(Identifier.empty(), "test 1"))
      .withAssetId(asset.getId())
      .withCreatorId(user.getId())
      .withAlarm(Alarm.from(true, false))
      .withFrequency(Frequency.from(1, FrequencyType.DAYS_IN_OPERATION))
      .withComment("comment test 1")).get();
    return this;
  }

  public CalculateAlarmDueDSL with_a_frequency_type_kilometers() {
    maintenancePlan = maintenancePlanMemoryRepository.save(MaintenancePlan.empty()
      .withMaintenanceType(MaintenanceType.from(Identifier.empty(), "test 1"))
      .withAssetId(asset.getId())
      .withCreatorId(user.getId())
      .withAlarm(Alarm.from(true, false))
      .withFrequency(Frequency.from(1, FrequencyType.KILOMETERS))
      .withComment("comment test 1")).get();
    return this;
  }

  public class When {
    private Either<ProtostellarError, Void> result;

    public CalculateAlarmDueDSL.When system_calculates_alarm_due() {
      result = calculateAlarmDueTypeCalenderDays.perform(FrequencyType.CALENDAR_DAYS);
      return this;
    }

    public CalculateAlarmDueDSL.Then then() {
      return thenClause;
    }
  }

  public class Then {
    private final CalculateAlarmDueDSL.When when;

    private Then(CalculateAlarmDueDSL.When when) {
      this.when = when;
    }

    public CalculateAlarmDueDSL.Then calculation_succeeded() {
      assertThat(when.result.isRight()).isTrue();
      return this;
    }

    public CalculateAlarmDueDSL.Then calculation_failed() {
      assertThat(when.result.isLeft()).isTrue();
      return this;
    }
  }
}
