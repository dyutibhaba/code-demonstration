package com.protostellar.zugplaner.trackandpredict.infra.api.rest.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.protostellar.zugplaner.common.errors.ProtostellarError;
import io.vavr.control.Either;

import java.util.HashMap;
import java.util.Map;

public class JsonParser {
  private ObjectMapper om = new ObjectMapper();

  public Either<ProtostellarError, Map<String, Object>> parseString(String string) {
    try {
      if (string == null || string.length() == 0) {
        return Either.right(new HashMap<>());
      }
      return Either.right(om.readValue(string, new TypeReference<>() {}));

    } catch (JsonProcessingException e) {
      return Either.left(new ProtostellarError());
    }
  }

  public Either<ProtostellarError, String> parseToString(Map<String, Object> map) {
    try {
      return Either.right(om.writeValueAsString(map));
    } catch (JsonProcessingException e) {
      return Either.left(new ProtostellarError());
    }
  }
}
