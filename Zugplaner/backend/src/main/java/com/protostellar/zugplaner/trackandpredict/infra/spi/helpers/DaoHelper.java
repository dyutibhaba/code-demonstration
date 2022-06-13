package com.protostellar.zugplaner.trackandpredict.infra.spi.helpers;

public class DaoHelper {

  private DaoHelper() {
    throw new UnsupportedOperationException();
  }

  public static String concatenateAddress(String street, String zipCode, String operatingRegionName, String countryName) {
    String address = "";
    if (street != null) {
      address += street + ", " + zipCode + " " + operatingRegionName + ", " + countryName;
    } else {
      address = null;
    }
    return address;
  }
}
