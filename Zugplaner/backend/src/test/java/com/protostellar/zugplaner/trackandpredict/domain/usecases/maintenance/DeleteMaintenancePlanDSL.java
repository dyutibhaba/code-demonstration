package com.protostellar.zugplaner.trackandpredict.domain.usecases.maintenance;

import com.protostellar.zugplaner.common.domain.usecases.user.services.AuthorizationHandler;
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
import com.protostellar.zugplaner.trackandpredict.domain.usecases.maintenance.plan.DeleteMaintenancePlan;
import com.protostellar.zugplaner.trackandpredict.domain.user.User;
import com.protostellar.zugplaner.trackandpredict.infra.spi.memory.AssetIdentityMemoryRepository;
import com.protostellar.zugplaner.trackandpredict.infra.spi.memory.MaintenancePlanMemoryRepository;
import com.protostellar.zugplaner.trackandpredict.infra.spi.memory.UserGroupMemoryRepository;
import com.protostellar.zugplaner.trackandpredict.model.AssetIdentity;
import com.protostellar.zugplaner.trackandpredict.model.maintenance.*;
import com.protostellar.zugplaner.trackandpredict.models.UserProfiles;
import com.protostellar.zugplaner.utils.PicoFactoryTestConfiguration;
import io.vavr.control.Either;

import java.util.ArrayList;
import java.util.Collections;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

public class DeleteMaintenancePlanDSL extends PicoFactoryTestConfiguration {
  private User user;
  private UserGroup savedUserGroup;
  private AssetIdentity asset;
  private MaintenancePlan maintenancePlan;
  private int initialNumber;

  private final WriteUser userWriter = getComponentInstance(UserMemoryRepository.class);
  private final WriteUserGroup userGroupWriter = getComponentInstance(UserGroupMemoryRepository.class);
  private final AssetIdentityMemoryRepository assetIdentityRepo = getComponentInstance(AssetIdentityMemoryRepository.class);
  private final MaintenancePlanMemoryRepository maintenancePlanMemoryRepository = getComponentInstance(MaintenancePlanMemoryRepository.class);

  private final DeleteMaintenancePlan deleteMaintenancePlan;

  public DeleteMaintenancePlanDSL() {
    PayingHandler payingHandler = getComponentInstance(PayingHandler.class);
    AuthorizationHandler authorizationHandler = getComponentInstance(AuthorizationHandler.class);
    this.deleteMaintenancePlan = new DeleteMaintenancePlan(authorizationHandler, payingHandler, maintenancePlanMemoryRepository, maintenancePlanMemoryRepository, assetIdentityRepo);
  }

  private When whenClause = new When();
  private Then thenClause = new Then(whenClause);

  public When when() { return whenClause; }

  public DeleteMaintenancePlanDSL a_user() {
    user = UserProfiles.standardUserInTrackAndPredict();
    userWriter.save(user, user.getEmail());
    return this;
  }

  public DeleteMaintenancePlanDSL an_admin() {
    user = UserProfiles.adminInTrackAndPredict();
    userWriter.save(user, user.getEmail());
    return this;
  }

  public DeleteMaintenancePlanDSL part_of_a_userGroup_with_premium_access() {
    randomUserExists();
    UserGroup userGroupToAdd = UserGroup.from("userGroup", Collections.singletonList(user), new ArrayList<>())
      .withPayingPlans(PayingPlans.empty().add(PayingFeature.MAINTENANCE_FEATURE, PayingPlan.PREMIUM_PLAN));
    savedUserGroup = userGroupWriter.save(userGroupToAdd).get();
    return this;
  }

  public DeleteMaintenancePlanDSL part_of_a_userGroup_with_free_access() {
    randomUserExists();
    UserGroup userGroupToAdd = UserGroup.from("userGroup", Collections.singletonList(user), new ArrayList<>())
      .withPayingPlans(PayingPlans.empty().add(PayingFeature.MAINTENANCE_FEATURE, PayingPlan.FREE_PLAN));
    savedUserGroup = userGroupWriter.save(userGroupToAdd).get();
    return this;
  }

  public DeleteMaintenancePlanDSL with_a_maintenance_plan() {
    randomUserExists();
    userGroupExists();
    asset = assetIdentityRepo.createAsset("new asset").get();
    assetIdentityRepo.associateAssetToUserGroup(asset, savedUserGroup);

    MaintenancePlan toAdd = MaintenancePlan.empty()
      .withId(Identifier.from(UUID.randomUUID()))
      .withMaintenanceType(MaintenanceType.from(Identifier.empty(), "wheels inspection"))
      .withAlarm(Alarm.from(false, false))
      .withFrequency(Frequency.from(100, FrequencyType.CALENDAR_DAYS))
      .withAssetId(asset.getId())
      .withCreatorId(user.getId())
      .withComment("no comment");
    maintenancePlan = maintenancePlanMemoryRepository.save(toAdd).get();
    return this;
  }

  public DeleteMaintenancePlanDSL and_we_check_the_number_of_maintenance_plans() {
    randomUserExists();
    userGroupExists();
    assetExists();
    maintenancePlanExists();
    initialNumber = maintenancePlanMemoryRepository.getAllFor(asset.getId()).get().size();
    return this;
  }

  public class When {
    private Either<ProtostellarError, Boolean> result;

    public When user_deletes_the_maintenance_plan() {
      randomUserExists();
      userGroupExists();
      assetExists();
      maintenancePlanExists();
      result = deleteMaintenancePlan.perform(user.getId(), maintenancePlan.getId());
      return this;
    }

    public Then then() {
      return thenClause;
    }
  }

  public class Then {
    private final When when;

    private Then(When when) { this.when = when; }

    public Then request_succeeded() {
      assertThat(when.result.isRight()).isTrue();
      return this;
    }

    public Then request_failed() {
      assertThat(when.result.isLeft()).isTrue();
      return this;
    }

    public Then the_maintenance_plan_is_deleted() {
      assertThat(maintenancePlanMemoryRepository.findById(maintenancePlan.getId()).isLeft()).isTrue();
      return this;
    }

    public Then the_maintenance_plan_is_not_deleted() {
      assertThat(maintenancePlanMemoryRepository.findById(maintenancePlan.getId()).get()).isEqualTo(maintenancePlan);
      return this;
    }

    public Then and_the_number_of_maintenance_plans_is_down_from_1() {
      assertThat(maintenancePlanMemoryRepository.getAllFor(asset.getId()).get().size()).isEqualTo(initialNumber - 1);
      return this;
    }

    public Then and_the_number_of_maintenance_plans_is_the_same() {
      assertThat(maintenancePlanMemoryRepository.getAllFor(asset.getId()).get().size()).isEqualTo(initialNumber);
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

  private void maintenancePlanExists() {
    if (maintenancePlan == null) {
      throw new IllegalStateException("Given clause incomplete: Maintenance plan was not set");
    }
  }
}
