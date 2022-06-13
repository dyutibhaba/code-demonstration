package com.protostellar.zugplaner.common.utils;

import lombok.extern.slf4j.Slf4j;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Random;

@Slf4j
public class StringUtilities {
  public static final String ORDER_NUMBER_PART_PROTOSTELLAR = "PS";
  public static final String ORDER_NUMBER_PART_WEBSHOP_THALES = "WST";
  private static Random random;

  static {
    try {
      random = SecureRandom.getInstanceStrong();
    } catch (NoSuchAlgorithmException e) {
      e.printStackTrace();
      log.error("Error occurred in creating secure random instance");
    }
  }

  private StringUtilities() {}

  public static String randomAlphanumeric(int size) {
    int leftLimit = 48; // numeral '0'
    int rightLimit = 122; // letter 'z'
    String generatedString = random.ints(leftLimit, rightLimit + 1)
      .filter(i -> (i <= 57 || i >= 65) && (i <= 90 || i >= 97))
      .limit(size)
      .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
      .toString();
    return ORDER_NUMBER_PART_PROTOSTELLAR +
      ORDER_NUMBER_PART_WEBSHOP_THALES + generatedString.toUpperCase();
  }
}
