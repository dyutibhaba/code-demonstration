package com.protostellar.zugplaner.trackandpredict.domain.usecases.maintenance;

import org.junit.jupiter.api.Test;

class DeleteMaintenancePlanTests {

  private DeleteMaintenancePlanDSL given() { return new DeleteMaintenancePlanDSL(); }

  @Test
  void should_delete_a_maintenance_plan_if_user_has_access() {
    given()
      .an_admin()
      .part_of_a_userGroup_with_premium_access()
      .with_a_maintenance_plan()
      .and_we_check_the_number_of_maintenance_plans()
    .when()
      .user_deletes_the_maintenance_plan()
    .then()
      .request_succeeded()
      .the_maintenance_plan_is_deleted()
      .and_the_number_of_maintenance_plans_is_down_from_1();
  }

  @Test
  void should_not_delete_a_maintenance_plan_if_user_doesnt_have_access() {
    given()
      .a_user()
      .part_of_a_userGroup_with_free_access()
      .with_a_maintenance_plan()
      .and_we_check_the_number_of_maintenance_plans()
      .when()
      .user_deletes_the_maintenance_plan()
      .then()
      .request_failed()
      .the_maintenance_plan_is_not_deleted()
      .and_the_number_of_maintenance_plans_is_the_same();
  }
}
