package com.protostellar.zugplaner.common.errors;

public class MaintenanceTypeRepositoryError extends ProtostellarError {
  public MaintenanceTypeRepositoryError() {
    super();
  }

  public static class NotFound extends MaintenanceTypeRepositoryError { }
}
