package com.protostellar.zugplaner.trackandpredict.model.maintenance;

public enum FrequencyType {
  UNKNOWN(0),
  CALENDAR_DAYS(1),
  DAYS_IN_OPERATION(2),
  KILOMETERS(3);

  private final int value;

  FrequencyType(int value) {
    this.value = value;
  }

  public int getValue() {
    return value;
  }

  public static FrequencyType from(int type) {
    if (type == 1) {
      return CALENDAR_DAYS;
    } else if (type == 2) {
      return DAYS_IN_OPERATION;
    } else if (type == 3) {
      return KILOMETERS;
    }
    throw new NullPointerException();
  }
}
