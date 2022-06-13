package com.protostellar.zugplaner.common.utils;

import com.protostellar.zugplaner.common.errors.ProtostellarError;
import io.vavr.control.Either;

import java.util.function.Function;

public class Fallback<R> {

  private Either<ProtostellarError, R> either;

  public Fallback(Either<ProtostellarError, R> either) {
    this.either = either;
  }

  public Either<ProtostellarError, R> fallback(Function<ProtostellarError, Either<ProtostellarError, R>> method) {
    return either
        .mapLeft(method)
        .fold(EitherUtilities::flatPass, EitherUtilities::right);
  }
}
