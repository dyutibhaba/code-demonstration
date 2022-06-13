package com.protostellar.zugplaner.trackandpredict.domain.usecases.maintenance;

import org.junit.jupiter.api.Test;

class CreateMaintenancePlanTests {

  private CreateMaintenancePlanDSL given() { return new CreateMaintenancePlanDSL(); }

  @Test
  void given_a_user_with_premium_access_with_an_asset_when_user_creates_maintenance_plan_then_it_is_added() {
    given()
      .a_user()
      .part_of_a_userGroup_with_premium_access()
      .with_an_asset()
    .when()
      .user_creates_a_maintenance_plan()
    .then()
      .request_succeeded()
      .and_maintenance_plan_has_the_proper_asset_id()
      .and_maintenance_plan_has_the_proper_creator_id();
  }

  @Test
  void given_a_user_with_premium_access_with_an_asset_with_existing_maintenance_type_when_user_creates_maintenance_plan_of_existing_maintenance_type_then_it_is_added_and_maintenance_type_is_reused() {
    given()
      .a_user()
      .part_of_a_userGroup_with_premium_access()
      .with_an_asset()
      .with_an_existing_maintenance_maintenance_type()
    .when()
      .user_creates_a_maintenance_plan()
    .then()
      .request_succeeded()
      .and_maintenance_plan_has_the_proper_asset_id()
      .and_maintenance_plan_has_the_proper_creator_id()
      .and_maintenance_plan_has_the_proper_maintenance_id();
  }

  @Test
  void given_a_user_with_a_free_plan_when_he_creates_a_new_maintenance_plan_then_its_not_added() {
    given()
      .a_user()
      .part_of_a_userGroup_with_free_access()
      .with_an_asset()
    .when()
      .user_creates_a_maintenance_plan()
    .then()
      .request_failed();
  }

  @Test
  void given_a_user_with_premium_access_with_an_asset_not_linked_to_user_group_when_user_creates_maintenance_plan_then_it_is_not_added() {
    given()
      .a_user()
      .part_of_a_userGroup_with_premium_access()
      .with_an_asset_not_linked_to_user_group()
    .when()
      .user_creates_a_maintenance_plan()
    .then()
      .request_failed();
  }


}
