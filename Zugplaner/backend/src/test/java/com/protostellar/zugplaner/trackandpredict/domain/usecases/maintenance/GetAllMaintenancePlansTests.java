package com.protostellar.zugplaner.trackandpredict.domain.usecases.maintenance;

import org.junit.jupiter.api.Test;

class GetAllMaintenancePlansTests {

  private GetAllMaintenancePlansDSL given() { return new GetAllMaintenancePlansDSL(); }

  @Test
  void should_get_maintenance_plans_of_assets_if_user_has_access() {
    given()
      .a_user()
      .part_of_a_userGroup_with_premium_access()
      .with_an_asset()
      .with_maintenance_plan()
      .with_maintenance_plan()
    .when()
      .user_fetches_the_maintenance_plans_of_this_asset()
    .then()
      .request_succeeded()
      .and_2_maintenance_plans_are_returned();
  }

  @Test
  void shouldnt_get_maintenance_plans_of_assets_if_user_doesnt_have_access() {
    given()
      .a_user()
      .part_of_a_userGroup_with_free_access()
      .with_an_asset()
      .with_maintenance_plan()
    .when()
      .user_fetches_the_maintenance_plans_of_this_asset()
    .then()
      .request_failed();
  }
}
