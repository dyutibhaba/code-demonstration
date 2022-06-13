CREATE TABLE track_predict."TrainLastPosition" (
      "TrainNumber" character varying(255),
      "CountryCode" character varying(255),
      "State" character varying(255),
      "RailstationNumber" character varying(255),
      "RailstationCode" character varying(255),
      "CountryName" character varying(255),
      "OperatingRegionName" character varying(255),
      "RailstationName" character varying(255),
      "Street" character varying(255),
      "ZIPCode" character varying(255),
      "GPSLongitude" character varying(255),
      "GPSLatitude" character varying(255),
      "SYS_InsertTS" timestamp without time zone,
      "SYS_InsertPrg" character varying(255),
      "SYS_UpdateTS" timestamp without time zone,
      "SYS_UpdatePrg" character varying(255),
      PRIMARY KEY ("TrainNumber")
);

CREATE TABLE track_predict."TrainETADelay" (
      "TrainNumber" character varying(255),
      "DeviationInMinutes" double precision,
      "ETA" timestamp without time zone,
      "SYS_InsertPrg" character varying(255),
      "SYS_InsertTS" timestamp without time zone,
      "SYS_UpdatePrg" character varying(255),
      "SYS_UpdateTS" timestamp without time zone,
      PRIMARY KEY ("TrainNumber")
);
