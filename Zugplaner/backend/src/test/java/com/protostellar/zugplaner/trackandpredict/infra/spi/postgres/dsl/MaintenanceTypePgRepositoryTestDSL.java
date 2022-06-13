package com.protostellar.zugplaner.trackandpredict.infra.spi.postgres.dsl;

import com.protostellar.zugplaner.common.errors.ProtostellarError;
import com.protostellar.zugplaner.common.model.id.Identifier;
import com.protostellar.zugplaner.trackandpredict.infra.spi.postgres.MaintenanceTypePgRepository;
import com.protostellar.zugplaner.trackandpredict.model.maintenance.MaintenanceType;
import io.vavr.control.Either;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class MaintenanceTypePgRepositoryTestDSL {

  public static MaintenanceType oil() {
    return MaintenanceType.from(
      Identifier.empty(),
      "oil"
    );
  }

  public static MaintenanceType water() {
    return MaintenanceType.from(
      Identifier.empty(),
      "water"
    );
  }

  public static MaintenanceType engine() {
    return MaintenanceType.from(
      Identifier.empty(),
      "engine"
    );
  }
  public static MaintenanceType brakes() {
    return MaintenanceType.from(
      Identifier.empty(),
      "brakes"
    );
  }

  public static class Given {
    private final MaintenanceTypePgRepository maintenanceTypePgRepository;
    private MaintenanceType target;

    public Given(MaintenanceTypePgRepository maintenanceTypePgRepository) {
      this.maintenanceTypePgRepository = maintenanceTypePgRepository;
    }

    public Given the_standard_database() {
      return this;
    }

    public Given a_maintenance_type_is_saved(MaintenanceType maintenanceType) {
      target = maintenanceTypePgRepository.save(maintenanceType).get();
      return this;
    }

    public When when() {
      return new When(this);
    }
  }

  public static class When {
    private final Given given;
    private Either<ProtostellarError, MaintenanceType> assertRoot;

    public When(Given given) {
      this.given = given;
    }

    public Then then() {
      return new Then(this);
    }

    public When a_maintenance_type_is_saved(MaintenanceType maintenanceType) {
      this.assertRoot = this.given.maintenanceTypePgRepository.save(maintenanceType);
      return this;
    }
    public When we_delete_a_maintenance_type(Identifier id) {
      this.given.maintenanceTypePgRepository.delete(id);
      return this;
    }
    public When we_find_maintenance_type_by_type(String type) {
      this.assertRoot = this.given.maintenanceTypePgRepository.findByType(type);
      return this;
    }

    public When we_find_maintenance_type_by_id() {
      this.assertRoot = this.given.maintenanceTypePgRepository.findById(this.given.target.getId());
      return this;
    }
  }
  public static class Then {
    private final When when;

    public Then(When when) {
      this.when = when;
    }

    public Then it_succeeds() {
      assertThat(this.when.assertRoot.isRight()).isTrue();
      return this;
    }

    public Then maintenance_type_is_removed(Identifier id) {
      assertThat(this.when.given.maintenanceTypePgRepository.findById(id).isLeft()).isTrue();
      return this;
    }

    public void maintenance_type_has_type(String type) {
      assertThat(this.when.assertRoot.get().getType()).isEqualTo(type);
    }
  }
}
