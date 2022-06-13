package com.protostellar.zugplaner.trackandpredict.domain.usecases.maintenance;

import org.junit.jupiter.api.Test;

class GetMaintenancePlanTest {

  private GetAllMaintenancePlansDSL given() { return new GetAllMaintenancePlansDSL(); }

  @Test
  void should_get_requested_latest_added_maintenance_plan_if_exists_and_user_has_access() {
    given()
        .a_user()
        .part_of_a_userGroup_with_premium_access()
        .with_an_asset()
        .with_maintenance_plan()
        .when()
        .user_fetches_latest_added_maintenance_plan()
        .then()
        .request_unique_maintenance_plan_succeeded()
        .unique_maintenancePlan_is_returned();
  }

  @Test
  void should_get_requested_maintenance_plan_if_exists_and_user_has_access() {
    given()
        .a_user()
        .part_of_a_userGroup_with_premium_access()
        .with_an_asset()
        .with_many_maintenance_plans()
        .when()
        .user_fetches_an_existing_maintenance_plan()
        .then()
        .request_unique_maintenance_plan_succeeded()
        .requested_existing_returned();
  }

  @Test
  void should_not_get_requested_unique_maintenance_plan_if_not_exists_and_user_has_access() {
    given()
        .a_user()
        .part_of_a_userGroup_with_premium_access()
        .with_an_asset()
        .with_maintenance_plan()
        .when()
        .user_fetches_non_existing_maintenance_plan()
        .then()
        .request_unique_maintenance_plan_failed();
  }

  @Test
  void should_not_get_requested_unique_maintenance_plan_if_is_not_part_of_authorized_user_group() {
    given()
        .a_user()
        .part_of_a_userGroup_with_premium_access()
        .with_an_asset()
        .with_maintenance_plan()
        .when()
        .user_not_part_of_authorized_user_group_fetches_maintenance_plan()
        .then()
        .request_unique_maintenance_plan_failed();
  }
}
