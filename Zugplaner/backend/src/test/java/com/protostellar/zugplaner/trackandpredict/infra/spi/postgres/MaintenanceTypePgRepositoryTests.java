package com.protostellar.zugplaner.trackandpredict.infra.spi.postgres;

import com.protostellar.zugplaner.common.errors.ProtostellarError;
import com.protostellar.zugplaner.common.model.id.Identifier;
import com.protostellar.zugplaner.trackandpredict.infra.spi.postgres.dsl.MaintenanceTypePgRepositoryTestDSL;
import com.protostellar.zugplaner.trackandpredict.model.maintenance.MaintenanceType;
import com.protostellar.zugplaner.utils.TestSecurityConfiguration;
import com.protostellar.zugplaner.utils.WithAuthenticatedUser;
import io.vavr.control.Either;
import io.zonky.test.db.AutoConfigureEmbeddedDatabase;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import static com.protostellar.zugplaner.trackandpredict.infra.spi.postgres.dsl.MaintenanceTypePgRepositoryTestDSL.brakes;
import static com.protostellar.zugplaner.trackandpredict.infra.spi.postgres.dsl.MaintenanceTypePgRepositoryTestDSL.engine;
import static com.protostellar.zugplaner.trackandpredict.infra.spi.postgres.dsl.MaintenanceTypePgRepositoryTestDSL.oil;
import static com.protostellar.zugplaner.trackandpredict.infra.spi.postgres.dsl.MaintenanceTypePgRepositoryTestDSL.water;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(classes = {TestSecurityConfiguration.class})
@AutoConfigureEmbeddedDatabase(beanName = "dataSource", provider = AutoConfigureEmbeddedDatabase.DatabaseProvider.DOCKER)
@ActiveProfiles("it")
class MaintenanceTypePgRepositoryTests {

  @Autowired
  MaintenanceTypePgRepository maintenanceTypePgRepository;

  private MaintenanceTypePgRepositoryTestDSL.Given given() {
    return new MaintenanceTypePgRepositoryTestDSL.Given(this.maintenanceTypePgRepository);
  }

  @Test
  @WithAuthenticatedUser
  void create() {
    given()
      .the_standard_database()
    .when()
      .a_maintenance_type_is_saved(engine())
    .then()
      .it_succeeds()
      .maintenance_type_has_type(engine().getType());
  }

  @Test
  @WithAuthenticatedUser
  void delete() {
    given()
      .the_standard_database()
      .a_maintenance_type_is_saved(brakes())
    .when()
      .we_delete_a_maintenance_type(brakes().getId())
    .then()
      .maintenance_type_is_removed(brakes().getId());
  }

  @Test
  @WithAuthenticatedUser
  void findByType() {
    given()
      .the_standard_database()
      .a_maintenance_type_is_saved(oil())
    .when()
      .we_find_maintenance_type_by_type(oil().getType())
    .then()
      .it_succeeds()
      .maintenance_type_has_type(oil().getType());
  }

  @Test
  @WithAuthenticatedUser
  void findById() {
    given()
      .the_standard_database()
      .a_maintenance_type_is_saved(water())
    .when()
      .we_find_maintenance_type_by_id()
    .then()
      .it_succeeds()
      .maintenance_type_has_type(water().getType());
  }

  @Test
  @WithAuthenticatedUser
  void update() {
    MaintenanceType initial = maintenanceTypePgRepository.save(MaintenanceType.from(Identifier.empty(),"initial")).get();
    Either<ProtostellarError, MaintenanceType> updatedMaintenanceType = maintenanceTypePgRepository.update(initial.withType("updated"));
    assertThat(updatedMaintenanceType.isRight()).isTrue();
    assertThat(updatedMaintenanceType.get().getType()).isEqualTo("updated");
  }
}
