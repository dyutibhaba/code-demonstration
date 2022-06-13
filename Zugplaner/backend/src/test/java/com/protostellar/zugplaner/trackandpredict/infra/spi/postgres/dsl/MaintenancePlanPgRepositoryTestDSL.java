package com.protostellar.zugplaner.trackandpredict.infra.spi.postgres.dsl;

import com.protostellar.zugplaner.common.errors.ProtostellarError;
import com.protostellar.zugplaner.common.infra.spi.postgres.UserPgRepository;
import com.protostellar.zugplaner.common.model.id.Identifier;
import com.protostellar.zugplaner.trackandpredict.domain.user.User;
import com.protostellar.zugplaner.trackandpredict.infra.spi.postgres.AssetIdentityPgRepository;
import com.protostellar.zugplaner.trackandpredict.infra.spi.postgres.MaintenancePlanPgRepository;
import com.protostellar.zugplaner.trackandpredict.infra.spi.postgres.MaintenanceTypePgRepository;
import com.protostellar.zugplaner.trackandpredict.model.AssetIdentity;
import com.protostellar.zugplaner.trackandpredict.model.maintenance.*;
import io.vavr.control.Either;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class MaintenancePlanPgRepositoryTestDSL {

  private final static String USER_EMAIL = "user@thalesdigital.io";

  private static List<MaintenancePlan> a_list_of_maintenance_plans(Identifier assetId, Identifier creatorId) {
    List<MaintenancePlan> maintenancePlanList = new ArrayList<>();
    Alarm alarm = Alarm.from(false, false);
    Frequency frequency = Frequency.from(10, FrequencyType.CALENDAR_DAYS);
    for (int i = 0; i < 4; i++) {
      MaintenancePlan toAdd = MaintenancePlan.empty()
        .withMaintenanceType(MaintenanceType.from(Identifier.empty(),"check type " + i))
        .withAlarm(alarm)
        .withFrequency(frequency)
        .withAssetId(assetId)
        .withCreatorId(creatorId)
        .withComment("no comment");
      maintenancePlanList.add(toAdd);
    }
    return maintenancePlanList;
  }

  public static class Given {
    private final UserPgRepository userPgRepository;
    private final MaintenancePlanPgRepository maintenancePlanPgRepository;
    private final AssetIdentityPgRepository assetIdentityPgRepository;
    private final MaintenanceTypePgRepository maintenanceTypePgRepository;
    private List<MaintenancePlan> maintenancePlanList;
    private User user;
    private AssetIdentity asset;

    public Given(MaintenancePlanPgRepository maintenancePlanPgRepository, UserPgRepository userPgRepository, AssetIdentityPgRepository assetIdentityPgRepository, MaintenanceTypePgRepository maintenanceTypePgRepository) {
      this.maintenancePlanPgRepository = maintenancePlanPgRepository;
      this.userPgRepository = userPgRepository;
      this.assetIdentityPgRepository = assetIdentityPgRepository;
      this.maintenanceTypePgRepository = maintenanceTypePgRepository;
    }

    public Given a_list_of_maintenance_plan_for_same_asset_saved() {
      this.maintenancePlanList = a_list_of_maintenance_plans(asset.getId(), user.getId());
      for (MaintenancePlan maintenancePlan: this.maintenancePlanList) {
        String maintenanceTypeToBeCreated = maintenancePlan.getMaintenanceType().getType();
        Either<ProtostellarError, MaintenanceType> maintenanceTypeCreated = maintenanceTypePgRepository.findByType(maintenanceTypeToBeCreated);
        if (maintenanceTypeCreated.isLeft()) maintenanceTypeCreated = maintenanceTypePgRepository.save(MaintenanceType.from(Identifier.empty(), maintenanceTypeToBeCreated));
        maintenancePlan = maintenancePlan.withMaintenanceType(maintenanceTypeCreated.get());
        Either<ProtostellarError, MaintenancePlan> maintenancePlanCreated = maintenancePlanPgRepository.save(maintenancePlan);
      }
      return this;
    }

    public When when() {
      return new When(this);
    }

    public Given a_user_connected() {
      this.user = userPgRepository.save(USER_EMAIL).get();
      return this;
    }

    public Given an_asset_created() {
       this.asset = assetIdentityPgRepository.createAsset("Asset Maintenance Plan").get();
      return this;
    }
  }

  public static class When {
    private final Given given;
    private Map<Identifier, Integer> result;
    private Either<ProtostellarError, List<MaintenancePlan>> maintenancePlanByAssetId;

    public When(Given given) {
      this.given = given;
    }

    public Then then() {
      return new Then(this);
    }

    public When we_get_all_by_assetId() {
      this.maintenancePlanByAssetId = this.given.maintenancePlanPgRepository.getAllFor(this.given.asset.getId());
      return this;
    }
  }
  public static class Then {
    private final When when;

    public Then(When when) {
      this.when = when;
    }


    public Then the_result_is_valid() {
      assertThat(this.when.maintenancePlanByAssetId.isRight()).isTrue();
      return this;
    }

    public void the_result_contains_several_maintenance_plans() {
      assertThat(this.when.maintenancePlanByAssetId.get().size() > 0).isTrue();
    }
  }
}
