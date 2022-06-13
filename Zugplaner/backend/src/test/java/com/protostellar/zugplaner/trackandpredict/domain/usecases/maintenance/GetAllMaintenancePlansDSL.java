package com.protostellar.zugplaner.trackandpredict.domain.usecases.maintenance;

import com.protostellar.zugplaner.common.domain.usecases.user.services.PayingHandler;
import com.protostellar.zugplaner.common.errors.ProtostellarError;
import com.protostellar.zugplaner.common.infra.spi.memory.UserMemoryRepository;
import com.protostellar.zugplaner.common.model.UserGroup;
import com.protostellar.zugplaner.common.model.id.Identifier;
import com.protostellar.zugplaner.common.model.payment.PayingFeature;
import com.protostellar.zugplaner.common.model.payment.PayingPlan;
import com.protostellar.zugplaner.common.model.payment.PayingPlans;
import com.protostellar.zugplaner.common.utils.RandomUtilities;
import com.protostellar.zugplaner.trackandpredict.domain.repositories.WriteUser;
import com.protostellar.zugplaner.trackandpredict.domain.repositories.WriteUserGroup;
import com.protostellar.zugplaner.trackandpredict.domain.usecases.maintenance.plan.GetAllMaintenancePlans;
import com.protostellar.zugplaner.trackandpredict.domain.usecases.maintenance.plan.GetMaintenancePlan;
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
import java.util.List;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

public class GetAllMaintenancePlansDSL extends PicoFactoryTestConfiguration {

  private final static int MAX_MAITENANCE_PLANS = 10;

  private User user;
  private UserGroup savedUserGroup;
  private AssetIdentity asset;
  private List<MaintenancePlan> maintenancePlans = new ArrayList<>();
  private Identifier latestAddedMaintenancePlanId = Identifier.empty();

  private final WriteUser userWriter = getComponentInstance(UserMemoryRepository.class);
  private final WriteUserGroup userGroupWriter = getComponentInstance(UserGroupMemoryRepository.class);
  private final AssetIdentityMemoryRepository assetIdentityRepo = getComponentInstance(AssetIdentityMemoryRepository.class);
  private final MaintenancePlanMemoryRepository maintenancePlanMemoryRepository = getComponentInstance(MaintenancePlanMemoryRepository.class);

  private final GetAllMaintenancePlans getAllMaintenancePlans;
  private final GetMaintenancePlan getMaintenancePlan;

  public GetAllMaintenancePlansDSL() {
    PayingHandler payingHandler = getComponentInstance(PayingHandler.class);
    this.getAllMaintenancePlans = new GetAllMaintenancePlans(maintenancePlanMemoryRepository, assetIdentityRepo, payingHandler);
    this.getMaintenancePlan = new GetMaintenancePlan(maintenancePlanMemoryRepository, assetIdentityRepo, payingHandler);
  }

  private When whenClause = new When();
  private Then thenClause = new Then(whenClause);

  public When when() { return whenClause; }

  public GetAllMaintenancePlansDSL a_user() {
    user = UserProfiles.standardUserInTrackAndPredict();
    userWriter.save(user, user.getEmail());
    return this;
  }

  public GetAllMaintenancePlansDSL part_of_a_userGroup_with_premium_access() {
    randomUserExists();
    UserGroup userGroupToAdd = UserGroup.from("userGroup", Collections.singletonList(user), new ArrayList<>())
      .withPayingPlans(PayingPlans.empty().add(PayingFeature.MAINTENANCE_FEATURE, PayingPlan.PREMIUM_PLAN));
    savedUserGroup = userGroupWriter.save(userGroupToAdd).get();
    return this;
  }

  public GetAllMaintenancePlansDSL part_of_a_userGroup_with_free_access() {
    randomUserExists();
    UserGroup userGroupToAdd = UserGroup.from("userGroup", Collections.singletonList(user), new ArrayList<>())
      .withPayingPlans(PayingPlans.empty().add(PayingFeature.MAINTENANCE_FEATURE, PayingPlan.FREE_PLAN));
    savedUserGroup = userGroupWriter.save(userGroupToAdd).get();
    return this;
  }

  public GetAllMaintenancePlansDSL with_an_asset() {
    randomUserExists();
    userGroupExists();
    asset = assetIdentityRepo.createAsset("new asset").get();
    assetIdentityRepo.associateAssetToUserGroup(asset, savedUserGroup);
    return this;
  }

  public GetAllMaintenancePlansDSL with_maintenance_plan() {
    randomUserExists();
    userGroupExists();
    assetExists();
    MaintenancePlan maintenancePlanToAdd = MaintenancePlan.empty()
      .withId(Identifier.from(UUID.randomUUID()))
      .withMaintenanceType(MaintenanceType.from(Identifier.empty(), "type of maintenance"))
      .withAssetId(asset.getId())
      .withCreatorId(user.getId())
      .withAlarm(Alarm.from(false, false))
      .withFrequency(Frequency.from(1, FrequencyType.CALENDAR_DAYS))
      .withComment("no comment");

    MaintenancePlan createdMaintenancePlan = maintenancePlanMemoryRepository.save(maintenancePlanToAdd).get();
    latestAddedMaintenancePlanId = createdMaintenancePlan.getId();
    refreshMaintenancePlan();
    return this;
  }

  private void refreshMaintenancePlan() {
    maintenancePlans = maintenancePlanMemoryRepository.getAllFor(asset.getId()).get();
  }

  public Identifier get_latest_added_maintenance_plan_id() {
    randomUserExists();
    userGroupExists();
    assetExists();
    return latestAddedMaintenancePlanId;
  }

  public GetAllMaintenancePlansDSL with_many_maintenance_plans() {
    for (int i = 0; i < MAX_MAITENANCE_PLANS; i++) {
      MaintenancePlan maintenancePlanToAdd = MaintenancePlan.empty()
        .withId(Identifier.from(UUID.randomUUID()))
        .withMaintenanceType(MaintenanceType.from(Identifier.empty(), "type of maintenance " + i))
        .withAssetId(asset.getId())
        .withCreatorId(user.getId())
        .withAlarm(Alarm.from(false, false))
        .withFrequency(Frequency.from(1, FrequencyType.CALENDAR_DAYS))
        .withComment("no comment");
      maintenancePlanMemoryRepository.save(maintenancePlanToAdd).get();
      refreshMaintenancePlan();
    }
    return this;
  }

  public class When {
    private Either<ProtostellarError, List<MaintenancePlan>> result;
    private Either<ProtostellarError, MaintenancePlan> unique;
    private Identifier requestedMaintenancePlanId = Identifier.empty();

    public When user_fetches_the_maintenance_plans_of_this_asset() {
      randomUserExists();
      userGroupExists();
      assetExists();
      maintenancePlanExists();
      result = getAllMaintenancePlans.perform(asset.getId(), user.getId());
      return this;
    }

    public When user_fetches_latest_added_maintenance_plan() {
      randomUserExists();
      userGroupExists();
      assetExists();
      maintenancePlanExists();
      latestAddedMaintenancePlanExists();
      unique = getMaintenancePlan.perform(user.getId(), asset.getId(), latestAddedMaintenancePlanId);
      return this;
    }

    public Then then() {
      return thenClause;
    }

    public When user_fetches_an_existing_maintenance_plan() {
      randomUserExists();
      userGroupExists();
      assetExists();
      maintenancePlanExists();
      requestedMaintenancePlanId = maintenancePlans.get(RandomUtilities.randomInt(0, maintenancePlans.size() - 1)).getId();
      unique = getMaintenancePlan.perform(
        user.getId(),
        asset.getId(),
        requestedMaintenancePlanId
      );
      return this;
    }

    public When user_fetches_non_existing_maintenance_plan() {
      randomUserExists();
      userGroupExists();
      assetExists();
      maintenancePlanExists();
      unique = getMaintenancePlan.perform(
        user.getId(),
        asset.getId(),
        Identifier.from(UUID.randomUUID())
      );
      return this;
    }

    public When user_not_part_of_authorized_user_group_fetches_maintenance_plan() {
      randomUserExists();
      userGroupExists();
      assetExists();
      maintenancePlanExists();
      requestedMaintenancePlanId = maintenancePlans.get(RandomUtilities.randomInt(0, maintenancePlans.size() - 1)).getId();
      unique = getMaintenancePlan.perform(
        Identifier.from(UUID.randomUUID()),
        asset.getId(),
        requestedMaintenancePlanId
      );
      return this;
    }
  }

  public class Then {
    private final When when;

    private Then(When when) { this.when = when; }

    public Then request_unique_maintenance_plan_succeeded() {
      assertThat(when.unique.isRight()).isTrue();
      return this;
    }

    public Then request_succeeded() {
      assertThat(when.result.isRight()).isTrue();
      return this;
    }

    public Then request_failed() {
      assertThat(when.result.isLeft()).isTrue();
      return this;
    }

    public Then and_2_maintenance_plans_are_returned() {
      assertThat(when.result.get().size()).isEqualTo(maintenancePlans.size());
      return this;
    }
    public Then unique_maintenancePlan_is_returned() {
      assertThat(when.unique.get().getId()).isEqualTo(latestAddedMaintenancePlanId);
      return this;
    }
    public Then requested_existing_returned() {
      assertThat(when.unique.get().getId()).isEqualTo(when.requestedMaintenancePlanId);
      return this;
    }
    public Then request_unique_maintenance_plan_failed() {
      assertThat(when.unique.isLeft()).isTrue();
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
    if (maintenancePlans.isEmpty()) {
      throw new IllegalStateException("Given clause incomplete: Checklist was not set");
    }
  }

  private void latestAddedMaintenancePlanExists() {
    if (latestAddedMaintenancePlanId.isEmpty()) {
      throw new IllegalStateException("Given clause incomplete: latest checklist was not set");
    }
  }
}
