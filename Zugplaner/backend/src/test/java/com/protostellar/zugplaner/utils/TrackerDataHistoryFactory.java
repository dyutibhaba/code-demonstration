package com.protostellar.zugplaner.utils;

import com.protostellar.zugplaner.common.model.FakeStringIdentifier;
import com.protostellar.zugplaner.trackandpredict.infra.spi.dao.TrackerDataHistoryDAO;
import com.protostellar.zugplaner.trackandpredict.model.Tracker;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class TrackerDataHistoryFactory {
  private static DateTimeFormatter locationTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
  private static DateTimeFormatter systemTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSSSSS");

  private static LocalDateTime parseTime(String time, DateTimeFormatter formatter) {
    LocalDateTime formattedTime;
    try {
      formattedTime = LocalDateTime.parse(time, formatter);
    } catch (DateTimeParseException e) {
      throw new IllegalArgumentException("The lastLocationDateTime must match following pattern: yyyy-MM-dd HH:mm:ss \n"+
        "The systemInsertTimestamp must match following pattern: yyyy-MM-dd HH:mm:ss.SSSSSS");
    }

    return formattedTime;
  }

  static public TrackerDataHistoryDAO createFrom(
    String trackerId,
    double positionGpsLatitude,
    double positionGpsLongitude,
    String positionAddress,
    int batteryLevelPercentage,
    double voltage,
    float speedKph,
    String lastLocationDateTime,
    String sysInsertTS,
    String description
    ) {

    LocalDateTime lastLocationTime = parseTime(lastLocationDateTime, locationTimeFormatter);
    LocalDateTime sysInsertTime = parseTime(sysInsertTS, systemTimeFormatter);

    return new TrackerDataHistoryDAO(trackerId,
      positionGpsLatitude,
      positionGpsLongitude,
      positionAddress,
      batteryLevelPercentage,
      (float) voltage,
      speedKph,
      lastLocationTime,
      sysInsertTime,
      "Track&Predict UI",
      null,
      null,
      null,
      null,
      description);
  }

  public static Tracker createFrom(String trackerId) {
    LocalDateTime lastLocationTime = parseTime("2019-10-28 21:33:33", locationTimeFormatter);
    LocalDateTime systemInsertTime = parseTime("2020-08-26 15:41:20.490763", systemTimeFormatter);
    return Tracker.from(
      FakeStringIdentifier.from(trackerId),
      0.0,
      0.0,
      "",
      100,
      5F,
      100F,
      lastLocationTime,
      "",
      "");
  }

  static public TrackerDataHistoryDAO createFrom(
    String trackerId,
    double positionGpsLatitude,
    double positionGpsLongitude,
    String positionAddress,
    int batteryLevelPercentage,
    double voltage,
    float speedKph,
    String lastLocationDateTime,
    String sysInsertTS,
    String systemInsertProgram,
    String description
  ) {

    LocalDateTime lastLocationTime = parseTime(lastLocationDateTime, locationTimeFormatter);
    LocalDateTime systemInsertTime = parseTime(sysInsertTS, systemTimeFormatter);

    return new TrackerDataHistoryDAO(trackerId,
      positionGpsLatitude,
      positionGpsLongitude,
      positionAddress,
      batteryLevelPercentage,
      (float) voltage,
      speedKph,
      lastLocationTime,
      systemInsertTime,
      systemInsertProgram,
      null,
      null,
      null,
      null,
      description
    );
  }
}
