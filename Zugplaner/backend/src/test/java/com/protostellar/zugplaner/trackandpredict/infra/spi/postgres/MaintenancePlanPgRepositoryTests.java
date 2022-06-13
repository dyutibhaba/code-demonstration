package com.protostellar.zugplaner.trackandpredict.infra.spi.postgres;

import com.protostellar.zugplaner.common.errors.ProtostellarError;
import com.protostellar.zugplaner.common.infra.spi.postgres.UserPgRepository;
import com.protostellar.zugplaner.common.model.id.Identifier;
import com.protostellar.zugplaner.trackandpredict.domain.user.User;
import com.protostellar.zugplaner.trackandpredict.infra.spi.postgres.dsl.MaintenancePlanPgRepositoryTestDSL;
import com.protostellar.zugplaner.trackandpredict.model.AssetIdentity;
import com.protostellar.zugplaner.trackandpredict.model.maintenance.*;
import com.protostellar.zugplaner.utils.TestSecurityConfiguration;
import com.protostellar.zugplaner.utils.WithAuthenticatedUser;
import io.vavr.control.Either;
import io.zonky.test.db.AutoConfigureEmbeddedDatabase;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(classes = {TestSecurityConfiguration.class})
@AutoConfigureEmbeddedDatabase(beanName = "dataSource", provider = AutoConfigureEmbeddedDatabase.DatabaseProvider.DOCKER)
@ActiveProfiles("it")
class MaintenancePlanPgRepositoryTests {

  @Autowired
  MaintenancePlanPgRepository maintenancePlanPgRepository;
  @Autowired
  AssetIdentityPgRepository assetIdentityPgRepository;
  @Autowired
  UserPgRepository userPgRepository;
  @Autowired
  MaintenanceTypePgRepository maintenanceTypePgRepository;

  private MaintenancePlanPgRepositoryTestDSL.Given given() {
    return new MaintenancePlanPgRepositoryTestDSL.Given(this.maintenancePlanPgRepository, userPgRepository, assetIdentityPgRepository, maintenanceTypePgRepository);
  }

  private User createUser(String email) {
    return userPgRepository.save(email).get();
  }

  private AssetIdentity createAsset(String name) {
    return assetIdentityPgRepository.createAsset(name).get();
  }

  private MaintenanceType findOrSaveMaintenanceType(String type){
    Either<ProtostellarError, MaintenanceType> existing = maintenanceTypePgRepository.findByType(type);
    if (existing.isRight()) return existing.get();
    return maintenanceTypePgRepository.save(MaintenanceType.from(Identifier.empty(), type)).get();
  }

  private MaintenancePlan saveMaintenancePlan(User user, AssetIdentity asset, String type, Alarm alarm, Frequency frequency){
    MaintenanceType maintenanceType = findOrSaveMaintenanceType(type);
    MaintenancePlan toAdd = MaintenancePlan.empty()
      .withMaintenanceType(maintenanceType)
      .withAlarm(alarm)
      .withFrequency(frequency)
      .withAssetId(asset.getId())
      .withCreatorId(user.getId())
      .withComment("no comment");
    return maintenancePlanPgRepository.save(toAdd).get();
  }

  @Test
  @WithAuthenticatedUser
  void createMaintenancePlan() {
    User user = createUser("user@thalesdigital.io");
    AssetIdentity asset = createAsset("asset for it");
    MaintenanceType maintenanceType = findOrSaveMaintenanceType( "check engine oil");
    Alarm alarm = Alarm.from(false, false);
    Frequency frequency = Frequency.from(10, FrequencyType.CALENDAR_DAYS);
    assertThat(maintenancePlanPgRepository
      .save(MaintenancePlan.empty()
        .withMaintenanceType(maintenanceType)
        .withAlarm(alarm)
        .withFrequency(frequency)
        .withAssetId(asset.getId())
        .withCreatorId(user.getId())
        .withComment("no comment")
      ).isRight()).isTrue();
  }

  @Test
  @WithAuthenticatedUser
  void deleteMaintenancePlan() {
    User user = createUser("user@thalesdigital.io");
    AssetIdentity asset = createAsset("asset for it");
    Alarm alarm = Alarm.from(false, false);
    Frequency frequency = Frequency.from(10, FrequencyType.CALENDAR_DAYS);
    MaintenancePlan maintenancePlan = saveMaintenancePlan(user,asset, "check water", alarm, frequency);
    Either<ProtostellarError, Boolean> delete = maintenancePlanPgRepository.delete(maintenancePlan.getId());
    assertThat(delete.isRight()).isTrue();
  }

  @Test
  @WithAuthenticatedUser
  void getAllMaintenancePlansFor() {
    User user = createUser("user@thalesdigital.io");
    AssetIdentity asset = createAsset("asset for it");
    Alarm alarm = Alarm.from(false, false);
    Frequency frequency = Frequency.from(10, FrequencyType.CALENDAR_DAYS);
    MaintenancePlan oil = saveMaintenancePlan(user, asset, "oil", alarm, frequency);
    MaintenancePlan water = saveMaintenancePlan(user, asset, "water", alarm, frequency);
    MaintenancePlan engine = saveMaintenancePlan(user, asset, "engine", alarm, frequency);
    Either<ProtostellarError, List<MaintenancePlan>> get = maintenancePlanPgRepository.getAllFor(asset.getId());
    assertThat(get.isRight()).isTrue();
    assertThat(get.get().contains(oil)).isTrue();
    assertThat(get.get().contains(water)).isTrue();
    assertThat(get.get().contains(engine)).isTrue();
  }

  @Test
  @WithAuthenticatedUser
  void getMaintenancePlan() {
    User user = createUser("user@thalesdigital.io");
    AssetIdentity asset = createAsset("asset for it");
    Alarm alarm = Alarm.from(false, false);
    Frequency frequency = Frequency.from(10, FrequencyType.CALENDAR_DAYS);
    MaintenancePlan maintenancePlan = saveMaintenancePlan(user, asset, "check wheels", alarm, frequency);
    Either<ProtostellarError, MaintenancePlan> get = maintenancePlanPgRepository.findById(maintenancePlan.getId());
    assertThat(get.isRight()).isTrue();
    assertThat(get.get()).isEqualTo(maintenancePlan);
  }

  @Test
  @WithAuthenticatedUser
  void testGetAllByAssetId() {
    given()
      .a_user_connected()
      .an_asset_created()
      .a_list_of_maintenance_plan_for_same_asset_saved()
    .when()
      .we_get_all_by_assetId()
    .then()
      .the_result_is_valid()
      .the_result_contains_several_maintenance_plans();
  }

  @Test
  @WithAuthenticatedUser
  void testUpdateMaintenancePlanSubsetOfAttributes() {
    User user = createUser("user@thalesdigital.io");
    AssetIdentity asset = createAsset("asset for it");
    Alarm alarm = Alarm.from(false, false);
    Frequency frequency = Frequency.from(10, FrequencyType.CALENDAR_DAYS);
    MaintenancePlan maintenancePlan = saveMaintenancePlan(user, asset, "check wheels", alarm, frequency);
    MaintenanceType maintenanceType = findOrSaveMaintenanceType("updated type");
    MaintenancePlan updatedMaintenancePlan = maintenancePlan
      .withMaintenanceType(maintenanceType)
      .withComment("updated");

    Either<ProtostellarError, MaintenancePlan> update = maintenancePlanPgRepository.update(updatedMaintenancePlan);

    assertThat(update.isRight()).isTrue();

    assertThat(update.get().getMaintenanceType()).isEqualTo(updatedMaintenancePlan.getMaintenanceType());
    assertThat(update.get().getComment()).isEqualTo(updatedMaintenancePlan.getComment());

    assertThat(update.get().getCreatorId()).isEqualTo(maintenancePlan.getCreatorId());
    assertThat(update.get().getCreationDate()).isEqualTo(maintenancePlan.getCreationDate());
    assertThat(update.get().getLastUpdateDateTime()).isNotEqualTo(maintenancePlan.getLastUpdateDateTime());
  }
}
