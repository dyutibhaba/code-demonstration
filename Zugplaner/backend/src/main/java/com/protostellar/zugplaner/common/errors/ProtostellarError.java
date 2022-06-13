package com.protostellar.zugplaner.common.errors;

public class ProtostellarError extends Exception {
  public ProtostellarError() {
    super();
  }

  public static class UserGroupRemovalError extends ProtostellarError {
    public UserGroupRemovalError(Exception ex) {
      super(ex);
    }
  }

  public ProtostellarError(Exception ex) {
    super(ex);
  }
}
