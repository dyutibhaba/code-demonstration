package com.protostellar.zugplaner.trackandpredict.domain.usecases.maintenance;

import org.junit.jupiter.api.Test;

class FindOrCreateMaintenanceTypeTests {

  private FindOrCreateMaintenanceTypeDSL given() { return new FindOrCreateMaintenanceTypeDSL(); }

  @Test
  void given_a_user_with_premium_access_with_an_asset_when_user_creates_maintenance_type_then_it_is_added() {
    given()
      .a_user()
      .part_of_a_userGroup_with_premium_access()
    .when()
      .user_creates_a_maintenance_type()
    .then()
      .request_succeeded()
      .and_maintenance_type_has_the_proper_type();
  }

  @Test
  void given_a_user_with_premium_access_with_an_asset_with_existing_maintenance_type_when_user_creates_maintenance_type_then_maintenance_type_is_reused() {
    given()
      .a_user()
      .part_of_a_userGroup_with_premium_access()
      .with_an_existing_maintenance_maintenance_type()
    .when()
      .user_creates_a_maintenance_type()
    .then()
      .request_succeeded()
      .and_maintenance_type_has_the_proper_type()
      .and_maintenance_type_has_the_proper_id();
  }

  @Test
  void given_a_user_with_a_free_plan_when_he_creates_a_new_maintenance_type_then_its_not_added() {
    given()
      .a_user()
      .part_of_a_userGroup_with_free_access()
    .when()
      .user_creates_a_maintenance_type()
    .then()
      .request_failed();
  }

}
