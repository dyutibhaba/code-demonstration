package com.protostellar.zugplaner.trackandpredict.domain.usecases.maintenance;

import com.protostellar.zugplaner.common.domain.usecases.user.services.PayingHandler;
import com.protostellar.zugplaner.common.errors.ProtostellarError;
import com.protostellar.zugplaner.common.infra.spi.memory.UserMemoryRepository;
import com.protostellar.zugplaner.common.model.UserGroup;
import com.protostellar.zugplaner.common.model.id.Identifier;
import com.protostellar.zugplaner.common.model.payment.PayingFeature;
import com.protostellar.zugplaner.common.model.payment.PayingPlan;
import com.protostellar.zugplaner.common.model.payment.PayingPlans;
import com.protostellar.zugplaner.trackandpredict.domain.repositories.WriteUser;
import com.protostellar.zugplaner.trackandpredict.domain.repositories.WriteUserGroup;
import com.protostellar.zugplaner.trackandpredict.domain.usecases.maintenance.plan.UpdateMaintenancePlan;
import com.protostellar.zugplaner.trackandpredict.domain.usecases.maintenance.plan.requests.MaintenancePlanChangeRequest;
import com.protostellar.zugplaner.trackandpredict.domain.usecases.maintenance.type.FindOrCreateMaintenanceType;
import com.protostellar.zugplaner.trackandpredict.domain.user.User;
import com.protostellar.zugplaner.trackandpredict.infra.spi.memory.AssetIdentityMemoryRepository;
import com.protostellar.zugplaner.trackandpredict.infra.spi.memory.MaintenancePlanMemoryRepository;
import com.protostellar.zugplaner.trackandpredict.infra.spi.memory.MaintenanceTypeMemoryRepository;
import com.protostellar.zugplaner.trackandpredict.infra.spi.memory.UserGroupMemoryRepository;
import com.protostellar.zugplaner.trackandpredict.model.AssetIdentity;
import com.protostellar.zugplaner.trackandpredict.model.maintenance.*;
import com.protostellar.zugplaner.trackandpredict.models.UserProfiles;
import com.protostellar.zugplaner.utils.PicoFactoryTestConfiguration;
import io.vavr.control.Either;

import java.util.ArrayList;
import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;

public class UpdateMaintenancePlanDSL extends PicoFactoryTestConfiguration {
  private User user;
  private UserGroup savedUserGroup;
  private AssetIdentity asset;
  private MaintenancePlan maintenancePlan;
  private MaintenancePlanChangeRequest maintenancePlanChangeRequest;

  private final WriteUser userWriter = getComponentInstance(UserMemoryRepository.class);
  private final WriteUserGroup userGroupWriter = getComponentInstance(UserGroupMemoryRepository.class);
  private final AssetIdentityMemoryRepository assetIdentityRepo = getComponentInstance(AssetIdentityMemoryRepository.class);
  private final MaintenancePlanMemoryRepository maintenancePlanMemoryRepository = getComponentInstance(MaintenancePlanMemoryRepository.class);
  private final MaintenanceTypeMemoryRepository maintenanceTypeMemoryRepository = getComponentInstance(MaintenanceTypeMemoryRepository.class);

  private final UpdateMaintenancePlan updateMaintenancePlan;

  public UpdateMaintenancePlanDSL() {
    PayingHandler payingHandler = getComponentInstance(PayingHandler.class);
    FindOrCreateMaintenanceType findOrCreateMaintenanceType = new FindOrCreateMaintenanceType(maintenanceTypeMemoryRepository, maintenanceTypeMemoryRepository, payingHandler);
    this.updateMaintenancePlan = new UpdateMaintenancePlan(maintenancePlanMemoryRepository, maintenancePlanMemoryRepository, assetIdentityRepo, payingHandler, findOrCreateMaintenanceType);
  }

  private When whenClause = new When();
  private Then thenClause = new Then(whenClause);

  public When when() {
    return whenClause;
  }

  public UpdateMaintenancePlanDSL a_user() {
    user = UserProfiles.standardUserInTrackAndPredict();
    userWriter.save(user, user.getEmail());
    return this;
  }

  public UpdateMaintenancePlanDSL part_of_a_userGroup_with_premium_access() {
    randomUserExists();
    UserGroup userGroupToAdd = UserGroup.from("userGroup", Arrays.asList(user), new ArrayList<>())
      .withPayingPlans(PayingPlans.empty().add(PayingFeature.MAINTENANCE_FEATURE, PayingPlan.PREMIUM_PLAN));
    savedUserGroup = userGroupWriter.save(userGroupToAdd).get();
    return this;
  }

  public UpdateMaintenancePlanDSL with_an_asset() {
    randomUserExists();
    userGroupExists();
    asset = assetIdentityRepo.createAsset("new asset").get();
    assetIdentityRepo.associateAssetToUserGroup(asset, savedUserGroup);
    return this;
  }

  public UpdateMaintenancePlanDSL with_a_maintenance_plan() {
    randomUserExists();
    userGroupExists();
    maintenancePlan = maintenancePlanMemoryRepository.save(MaintenancePlan.empty()
      .withMaintenanceType(MaintenanceType.from(Identifier.empty(), "test 1"))
      .withAssetId(asset.getId())
      .withCreatorId(user.getId())
      .withAlarm(Alarm.from(false,false))
      .withFrequency(Frequency.from(1,FrequencyType.CALENDAR_DAYS))
      .withComment("comment test 1")).get();
    return this;
  }

  public UpdateMaintenancePlanDSL with_an_asset_not_linked_to_user_group() {
    randomUserExists();
    userGroupExists();
    asset = assetIdentityRepo.createAsset("new asset").get();
    return this;
  }

  public UpdateMaintenancePlanDSL part_of_a_userGroup_with_free_access() {
    randomUserExists();
    UserGroup userGroupToAdd = UserGroup.from("userGroup", Arrays.asList(user), new ArrayList<>())
      .withPayingPlans(PayingPlans.empty().add(PayingFeature.MAINTENANCE_FEATURE, PayingPlan.FREE_PLAN));
    savedUserGroup = userGroupWriter.save(userGroupToAdd).get();
    return this;
  }

  public class When {
    private Either<ProtostellarError, MaintenancePlan> result;

    public When user_updates_a_maintenance_plan() {
      randomUserExists();
      userGroupExists();
      assetExists();
      maintenancePlanChangeRequest = MaintenancePlanChangeRequest.builder()
        .assetId(asset.getId())
        .alarm(Alarm.from(true, false))
        .frequency(Frequency.from(2,FrequencyType.CALENDAR_DAYS))
        .maintenanceType(MaintenanceType.from(Identifier.empty(), "test 2"))
        .comment("test 2")
        .build();
      result = updateMaintenancePlan.perform(
        user.getId(),
        maintenancePlan.getId(),
        maintenancePlanChangeRequest
        );
      return this;
    }

    public Then then() {
      return thenClause;
    }
  }

  public class Then {
    private final When when;

    private Then(When when) {
      this.when = when;
    }

    public Then request_succeeded() {
      assertThat(when.result.isRight()).isTrue();
      return this;
    }

    public Then request_failed() {
      assertThat(when.result.isLeft()).isTrue();
      return this;
    }

    public Then and_maintenance_plan_has_the_proper_asset_id() {
      assertThat(when.result.get().getAssetId()).isEqualTo(asset.getId());
      return this;
    }

    public Then and_maintenance_plan_has_the_proper_creator_id() {
      assertThat(when.result.get().getCreatorId()).isEqualTo(user.getId());
      return this;
    }

    public Then and_maintenance_plan_has_the_proper_comment() {
      assertThat(when.result.get().getComment()).isEqualTo(maintenancePlanChangeRequest.getComment());
      return this;
    }

    public Then and_maintenance_plan_has_the_proper_maintenance_type() {
      assertThat(when.result.get().getMaintenanceType()).isEqualTo(maintenancePlanChangeRequest.getMaintenanceType());
      return this;
    }

    public Then and_maintenance_plan_has_the_proper_frequency() {
      assertThat(when.result.get().getFrequency()).isEqualTo(maintenancePlanChangeRequest.getFrequency());
      return this;
    }

    public Then and_maintenance_plan_has_the_proper_alarm() {
      assertThat(when.result.get().getAlarm()).isEqualTo(maintenancePlanChangeRequest.getAlarm());
      return this;
    }
  }

  /* Sanity checks */
  private void randomUserExists() {
    if (user == null) {
      throw new IllegalStateException("Given clause incomplete: User was not set");
    }
  }

  private void userGroupExists() {
    if (savedUserGroup == null) {
      throw new IllegalStateException("Given clause incomplete: Group was not set");
    }
  }

  private void assetExists() {
    if (asset == null) {
      throw new IllegalStateException("Given clause incomplete: Asset was not set");
    }
  }
}
