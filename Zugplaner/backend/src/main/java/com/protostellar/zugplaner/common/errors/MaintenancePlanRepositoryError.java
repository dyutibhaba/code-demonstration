package com.protostellar.zugplaner.common.errors;

public class MaintenancePlanRepositoryError extends ProtostellarError {
  public MaintenancePlanRepositoryError() {
    super();
  }

  public static class NotFound extends MaintenancePlanRepositoryError { }
}
