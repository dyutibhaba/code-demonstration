package com.protostellar.zugplaner.common.utils;

import com.protostellar.zugplaner.common.errors.ProtostellarError;
import io.vavr.control.Either;

import java.util.List;
import java.util.stream.Collectors;

public class EitherUtilities {

  private EitherUtilities() {
    throw new UnsupportedOperationException();
  }

  public static <U> Either<ProtostellarError, U> flatPass(Either<ProtostellarError, U> either) {
    return either;
  }

  public static <U> Either<ProtostellarError, U> right(U value) {
    return Either.right(value);
  }

  public static <U> Either<ProtostellarError, List<U>> listOfEitherToEitherOfList(List<Either<ProtostellarError, U>> list) {
    List<Either<ProtostellarError, U>> lefters = list.stream().filter(Either::isLeft).collect(Collectors.toList());
    if (lefters.isEmpty()) {
      return Either.right(list.stream().map(Either::get).collect(Collectors.toList()));
    } else {
      return Either.left(lefters.get(0).getLeft());
    }
  }
}
