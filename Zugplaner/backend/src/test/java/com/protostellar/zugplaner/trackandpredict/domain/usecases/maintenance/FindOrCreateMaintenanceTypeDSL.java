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
import com.protostellar.zugplaner.trackandpredict.domain.usecases.maintenance.type.FindOrCreateMaintenanceType;
import com.protostellar.zugplaner.trackandpredict.domain.user.User;
import com.protostellar.zugplaner.trackandpredict.infra.spi.memory.MaintenanceTypeMemoryRepository;
import com.protostellar.zugplaner.trackandpredict.infra.spi.memory.UserGroupMemoryRepository;
import com.protostellar.zugplaner.trackandpredict.model.maintenance.MaintenanceType;
import com.protostellar.zugplaner.trackandpredict.models.UserProfiles;
import com.protostellar.zugplaner.utils.PicoFactoryTestConfiguration;
import io.vavr.control.Either;

import java.util.ArrayList;
import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;

public class FindOrCreateMaintenanceTypeDSL extends PicoFactoryTestConfiguration {
  public static final String MAINTENANCE_TYPE_CHECK_ENGINE_OIL = "check engine oil";
  private User user;
  private UserGroup savedUserGroup;
  private MaintenanceType maintenanceType;

  private final WriteUser userWriter = getComponentInstance(UserMemoryRepository.class);
  private final WriteUserGroup userGroupWriter = getComponentInstance(UserGroupMemoryRepository.class);
  private final MaintenanceTypeMemoryRepository maintenanceTypeMemoryRepository = getComponentInstance(MaintenanceTypeMemoryRepository.class);

  private final FindOrCreateMaintenanceType findOrCreateMaintenanceType;

  public FindOrCreateMaintenanceTypeDSL() {
    PayingHandler payingHandler = getComponentInstance(PayingHandler.class);
    this.findOrCreateMaintenanceType = new FindOrCreateMaintenanceType(maintenanceTypeMemoryRepository, maintenanceTypeMemoryRepository, payingHandler);
  }

  private When whenClause = new When();
  private Then thenClause = new Then(whenClause);

  public When when() {
    return whenClause;
  }

  public FindOrCreateMaintenanceTypeDSL a_user() {
    user = UserProfiles.standardUserInTrackAndPredict();
    userWriter.save(user, user.getEmail());
    return this;
  }

  public FindOrCreateMaintenanceTypeDSL part_of_a_userGroup_with_premium_access() {
    randomUserExists();
    UserGroup userGroupToAdd = UserGroup.from("userGroup", Arrays.asList(user), new ArrayList<>())
      .withPayingPlans(PayingPlans.empty().add(PayingFeature.MAINTENANCE_FEATURE, PayingPlan.PREMIUM_PLAN));
    savedUserGroup = userGroupWriter.save(userGroupToAdd).get();
    return this;
  }

  public FindOrCreateMaintenanceTypeDSL with_an_existing_maintenance_maintenance_type() {
    maintenanceType = maintenanceTypeMemoryRepository.save(MaintenanceType.from(Identifier.empty(), MAINTENANCE_TYPE_CHECK_ENGINE_OIL)).get();
    return this;
  }

  public FindOrCreateMaintenanceTypeDSL part_of_a_userGroup_with_free_access() {
    randomUserExists();
    UserGroup userGroupToAdd = UserGroup.from("userGroup", Arrays.asList(user), new ArrayList<>())
      .withPayingPlans(PayingPlans.empty().add(PayingFeature.MAINTENANCE_FEATURE, PayingPlan.FREE_PLAN));
    savedUserGroup = userGroupWriter.save(userGroupToAdd).get();
    return this;
  }

  public class When {
    private Either<ProtostellarError, MaintenanceType> result;

    public When user_creates_a_maintenance_type() {
      randomUserExists();
      userGroupExists();
      result = findOrCreateMaintenanceType.perform(
        user.getId(),
        MAINTENANCE_TYPE_CHECK_ENGINE_OIL);
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

    public Then and_maintenance_type_has_the_proper_type() {
      assertThat(when.result.get().getType()).isEqualTo(MAINTENANCE_TYPE_CHECK_ENGINE_OIL);
      return this;
    }

    public Then and_maintenance_type_has_the_proper_id() {
      assertThat(when.result.get().getId()).isEqualTo(maintenanceType.getId());
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
}
