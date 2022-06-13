package com.protostellar.zugplaner.marketplace.domain.errors;

import com.protostellar.zugplaner.common.errors.ProtostellarError;

public class ArticleRepositoryError extends ProtostellarError {
  public ArticleRepositoryError() { super();}

  public static class NotFound extends ArticleRepositoryError {

  }

}
