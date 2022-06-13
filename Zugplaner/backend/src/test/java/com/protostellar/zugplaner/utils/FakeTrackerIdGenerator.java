package com.protostellar.zugplaner.utils;

public class FakeTrackerIdGenerator {

  private static final int SIZE = 7;

  private static final String NUMERIC_STRING = "0123456789";
  public static String generate() {
    StringBuilder builder = new StringBuilder();
    int count = SIZE;
    while (count -- != 0) {
      int character = (int)(Math.random()*NUMERIC_STRING.length());
      builder.append(NUMERIC_STRING.charAt(character));
    }
    return builder.toString();
  }
}
