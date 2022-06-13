package com.protostellar.zugplaner.trackandpredict.domain.usecases.maintenance;

import org.junit.jupiter.api.Test;

class UpdateMaintenancePlanTests {

  private UpdateMaintenancePlanDSL given() { return new UpdateMaintenancePlanDSL(); }

  @Test
  void given_a_user_with_premium_access_with_an_asset_when_user_updates_a_maintenance_plan_then_it_succeeds() {
    given()
      .a_user()
      .part_of_a_userGroup_with_premium_access()
      .with_an_asset()
      .with_a_maintenance_plan()
    .when()
      .user_updates_a_maintenance_plan()
    .then()
      .request_succeeded()
      .and_maintenance_plan_has_the_proper_asset_id()
      .and_maintenance_plan_has_the_proper_creator_id()
      .and_maintenance_plan_has_the_proper_alarm()
      .and_maintenance_plan_has_the_proper_frequency()
      .and_maintenance_plan_has_the_proper_maintenance_type()
      .and_maintenance_plan_has_the_proper_comment();
  }


  @Test
  void given_a_user_with_a_free_plan_when_he_updates_a_new_maintenance_plan_then_it_fails() {
    given()
      .a_user()
      .part_of_a_userGroup_with_free_access()
      .with_an_asset()
      .with_a_maintenance_plan()
    .when()
      .user_updates_a_maintenance_plan()
    .then()
      .request_failed();
  }

  @Test
  void given_a_user_with_premium_access_with_an_asset_not_linked_to_user_group_when_user_updates_maintenance_plan_then_it_fails() {
    given()
      .a_user()
      .part_of_a_userGroup_with_premium_access()
      .with_an_asset_not_linked_to_user_group()
      .with_a_maintenance_plan()
    .when()
      .user_updates_a_maintenance_plan()
    .then()
      .request_failed();
  }


}
