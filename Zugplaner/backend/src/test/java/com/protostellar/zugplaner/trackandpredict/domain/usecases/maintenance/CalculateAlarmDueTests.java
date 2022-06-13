package com.protostellar.zugplaner.trackandpredict.domain.usecases.maintenance;

import org.junit.jupiter.api.Test;

public class CalculateAlarmDueTests {

  private CalculateAlarmDueDSL given() {
    return new CalculateAlarmDueDSL();
  }

  @Test
  void given_frequency_type_calender_days_when_system_calculates_alarm_due_then_it_succeeds() {
    given()
      .a_user()
      .with_an_asset()
      .with_a_frequency_type_calender_days()
      .when()
      .system_calculates_alarm_due()
      .then()
      .calculation_succeeded();
  }

  @Test
  void given_frequency_type_days_in_operation_when_system_calculates_alarm_due_then_it_succeeds() {
    given()
      .a_user()
      .with_an_asset()
      .with_a_frequency_type_days_in_operation()
      .when()
      .system_calculates_alarm_due()
      .then()
      .calculation_succeeded();
  }

  @Test
  void given_frequency_type_kilometers_when_system_calculates_alarm_due_then_it_succeeds() {
    given()
      .a_user()
      .with_an_asset()
      .with_a_frequency_type_kilometers()
      .when()
      .system_calculates_alarm_due()
      .then()
      .calculation_succeeded();
  }

}
