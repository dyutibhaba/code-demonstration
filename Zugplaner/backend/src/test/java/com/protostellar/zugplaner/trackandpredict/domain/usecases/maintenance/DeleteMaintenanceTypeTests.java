package com.protostellar.zugplaner.trackandpredict.domain.usecases.maintenance;

import org.junit.jupiter.api.Test;

class DeleteMaintenanceTypeTests {

  private DeleteMaintenanceTypeDSL given() { return new DeleteMaintenanceTypeDSL(); }

  @Test
  void should_delete_a_maintenance_type_if_user_has_access() {
    given()
      .an_admin()
      .part_of_a_userGroup_with_premium_access()
      .with_a_maintenance_type()
    .when()
      .user_deletes_the_maintenance_type()
    .then()
      .request_succeeded()
      .the_maintenance_type_is_deleted();
  }

  @Test
  void should_not_delete_a_maintenance_type_if_user_doesnt_have_access() {
    given()
      .a_user()
      .part_of_a_userGroup_with_free_access()
      .with_a_maintenance_type()
    .when()
      .user_deletes_the_maintenance_type()
    .then()
      .request_failed()
      .the_maintenance_type_is_not_deleted();
  }
}
