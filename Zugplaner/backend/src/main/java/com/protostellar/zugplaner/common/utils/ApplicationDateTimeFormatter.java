package com.protostellar.zugplaner.common.utils;

import lombok.Data;

import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.temporal.ChronoField;

@Data
public class ApplicationDateTimeFormatter {
  private static final String DATETIME_PATTERN = "yyyy-MM-dd";
  private static final String ZONE_ID = "Europe/Berlin";
  private static DateTimeFormatter formatterInstance = null;

  private ApplicationDateTimeFormatter() {
    throw new IllegalStateException("Utility class");
  }

  public static DateTimeFormatter getFormatter() {
    if (formatterInstance == null) {
      return
          new DateTimeFormatterBuilder().appendPattern(DATETIME_PATTERN)
              .parseDefaulting(ChronoField.NANO_OF_DAY, 0)
              .toFormatter()
              .withZone(ZoneId.of(ZONE_ID));
    }
    return formatterInstance;
  }
}
