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
import com.protostellar.zugplaner.trackandpredict.domain.usecases.maintenance.type.DeleteMaintenanceType;
import com.protostellar.zugplaner.trackandpredict.domain.user.User;
import com.protostellar.zugplaner.trackandpredict.infra.spi.memory.MaintenanceTypeMemoryRepository;
import com.protostellar.zugplaner.trackandpredict.infra.spi.memory.UserGroupMemoryRepository;
import com.protostellar.zugplaner.trackandpredict.model.maintenance.MaintenanceType;
import com.protostellar.zugplaner.trackandpredict.models.UserProfiles;
import com.protostellar.zugplaner.utils.PicoFactoryTestConfiguration;
import io.vavr.control.Either;

import java.util.ArrayList;
import java.util.Collections;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

public class DeleteMaintenanceTypeDSL extends PicoFactoryTestConfiguration {
  public static final String WHEELS_INSPECTION = "wheels inspection";
  private User user;
  private UserGroup savedUserGroup;
  private MaintenanceType maintenanceType;

  private final WriteUser userWriter = getComponentInstance(UserMemoryRepository.class);
  private final WriteUserGroup userGroupWriter = getComponentInstance(UserGroupMemoryRepository.class);
  private final MaintenanceTypeMemoryRepository maintenanceTypeMemoryRepository = getComponentInstance(MaintenanceTypeMemoryRepository.class);

  private final DeleteMaintenanceType deleteMaintenanceType;

  public DeleteMaintenanceTypeDSL() {
    PayingHandler payingHandler = getComponentInstance(PayingHandler.class);
    AuthorizationHandler authorizationHandler = getComponentInstance(AuthorizationHandler.class);
    this.deleteMaintenanceType = new DeleteMaintenanceType(authorizationHandler, payingHandler, maintenanceTypeMemoryRepository, maintenanceTypeMemoryRepository);
  }

  private When whenClause = new When();
  private Then thenClause = new Then(whenClause);

  public When when() { return whenClause; }

  public DeleteMaintenanceTypeDSL a_user() {
    user = UserProfiles.standardUserInTrackAndPredict();
    userWriter.save(user, user.getEmail());
    return this;
  }

  public DeleteMaintenanceTypeDSL an_admin() {
    user = UserProfiles.adminInTrackAndPredict();
    userWriter.save(user, user.getEmail());
    return this;
  }

  public DeleteMaintenanceTypeDSL part_of_a_userGroup_with_premium_access() {
    randomUserExists();
    UserGroup userGroupToAdd = UserGroup.from("userGroup", Collections.singletonList(user), new ArrayList<>())
      .withPayingPlans(PayingPlans.empty().add(PayingFeature.MAINTENANCE_FEATURE, PayingPlan.PREMIUM_PLAN));
    savedUserGroup = userGroupWriter.save(userGroupToAdd).get();
    return this;
  }

  public DeleteMaintenanceTypeDSL part_of_a_userGroup_with_free_access() {
    randomUserExists();
    UserGroup userGroupToAdd = UserGroup.from("userGroup", Collections.singletonList(user), new ArrayList<>())
      .withPayingPlans(PayingPlans.empty().add(PayingFeature.MAINTENANCE_FEATURE, PayingPlan.FREE_PLAN));
    savedUserGroup = userGroupWriter.save(userGroupToAdd).get();
    return this;
  }

  public DeleteMaintenanceTypeDSL with_a_maintenance_type() {
    randomUserExists();
    userGroupExists();

    MaintenanceType toAdd = MaintenanceType.empty()
      .withId(Identifier.from(UUID.randomUUID()))
      .withType(WHEELS_INSPECTION);
    maintenanceType = maintenanceTypeMemoryRepository.save(toAdd).get();
    return this;
  }

  public class When {
    private Either<ProtostellarError, Boolean> result;

    public When user_deletes_the_maintenance_type() {
      randomUserExists();
      userGroupExists();
      maintenancePlanExists();
      result = deleteMaintenanceType.perform(user.getId(), maintenanceType.getId());
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

    public Then the_maintenance_type_is_deleted() {
      assertThat(maintenanceTypeMemoryRepository.findById(maintenanceType.getId()).isLeft()).isTrue();
      return this;
    }

    public Then the_maintenance_type_is_not_deleted() {
      assertThat(maintenanceTypeMemoryRepository.findById(maintenanceType.getId()).get()).isEqualTo(maintenanceType);
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

  private void maintenancePlanExists() {
    if (maintenanceType == null) {
      throw new IllegalStateException("Given clause incomplete: Maintenance plan was not set");
    }
  }
}
