    -- track_predict."AssetLocationHistory" definition
    -- Drop table
    -- DROP TABLE track_predict."AssetLocationHistory";
    CREATE TABLE  IF NOT EXISTS track_predict."AssetLocationHistory" (
           "ID" uuid NOT NULL DEFAULT uuid_generate_v4(),
           "AssetId" uuid NOT NULL,
           "LocationDateTime" timestamp NOT NULL,
           "PositionGpsLatitude" float4 NULL,
           "PositionGpsLongitude" float4 NULL,
           "SpeedKph" float4 NULL,
           CONSTRAINT "AssetLocationHistory_pkey" PRIMARY KEY ("ID"),
           CONSTRAINT assetlocationhistory_fk FOREIGN KEY ("AssetId") REFERENCES track_predict."Asset"("ID")
    );

    -- track_predict."F_AssetStats_Day" definition
    -- Drop table
    -- DROP TABLE track_predict."F_AssetStats_Day";

    CREATE TABLE  IF NOT EXISTS track_predict."F_AssetStats_Day" (
           "AssetId" uuid NOT NULL,
           "Date" DATE,
           "DistanceKM" float8 NULL,
           CONSTRAINT f_assetstats_day_pk PRIMARY KEY ("AssetId", "Date"),
           CONSTRAINT f_assetstats_day_fk FOREIGN KEY ("AssetId") REFERENCES track_predict."Asset"("ID")
    );

    CREATE INDEX  IF NOT EXISTS "idx_F_AssetStats_Day_lookup" ON track_predict."F_AssetStats_Day" USING btree ("AssetId", "Date");

    -- track_predict."F_AssetStats_Month" definition
    -- Drop table
    -- DROP TABLE track_predict."F_AssetStats_Month";

    CREATE TABLE  IF NOT EXISTS track_predict."F_AssetStats_Month" (
           "AssetId" uuid NOT NULL,
           "Year" float8 NOT NULL,
           "Month" float8 NOT NULL,
           "DistanceKM" float8 NULL,
           CONSTRAINT f_assetstats_month_pk PRIMARY KEY ("AssetId", "Year", "Month"),
           CONSTRAINT f_assetstats_month_fk FOREIGN KEY ("AssetId") REFERENCES track_predict."Asset"("ID")
    );
    CREATE INDEX  IF NOT EXISTS "idx_F_AssetStats_Month_lookup" ON track_predict."F_AssetStats_Month" USING btree ("AssetId", "Year", "Month");

    -- track_predict."F_TrackerStats_Day" definition
    -- Drop table
    -- DROP TABLE track_predict."F_TrackerStats_Day";

    CREATE TABLE  IF NOT EXISTS track_predict."F_TrackerStats_Day" (
           "TrackerId" text NOT NULL,
           "Year" float8 NOT NULL,
           "Month" float8 NOT NULL,
           "DayOfMonth" float8 NOT NULL,
           "DayDate" timestamp NOT NULL,
           "DayID" float8 NULL,
           "MonthID" float8 NULL,
           "DistanceKilometers" float8 NULL,
           "DistanceMeters" float8 NULL,
           "DeltaBatteryPercentage" float8 NULL,
           "DeltaBatteryVoltage" float8 NULL,
           "DeltaTimestampsMinutes" float8 NULL,
           "DeltaTimestampsHours" float8 NULL,
           "AverageSpeedKpH" float8 NULL,
           CONSTRAINT f_trackerstats_day_pk PRIMARY KEY ("TrackerId", "Year", "Month", "DayOfMonth", "DayDate"),
           CONSTRAINT f_trackerstats_day_fk FOREIGN KEY ("TrackerId") REFERENCES track_predict."Tracker"("ID")
    );

    CREATE INDEX  IF NOT EXISTS "idx_F_TrackerStats_Day_lookup" ON track_predict."F_TrackerStats_Day" USING btree ("TrackerId", "Year", "Month", "DayOfMonth");

    -- track_predict."F_TrackerStats_Month" definition
    -- Drop table
    -- DROP TABLE track_predict."F_TrackerStats_Month";

    CREATE TABLE  IF NOT EXISTS track_predict."F_TrackerStats_Month" (
           "TrackerId" text NOT NULL,
           "Year" float8 NOT NULL,
           "Month" float8 NOT NULL,
           "MonthID" float8 NOT NULL,
           "DistanceKilometers" float8 NULL,
           "DistanceMeters" float8 NULL,
           "DeltaBatteryPercentage" float8 NULL,
           "DeltaBatteryVoltage" float8 NULL,
           "DeltaTimestampsMinutes" float8 NULL,
           "DeltaTimestampsHours" float8 NULL,
           "AverageSpeedKpH" float8 NULL,

           CONSTRAINT f_trackerstats_month_pk PRIMARY KEY ("TrackerId", "Year", "Month", "MonthID"),
           CONSTRAINT f_trackerstats_month_fk FOREIGN KEY ("TrackerId") REFERENCES track_predict."Tracker"("ID")
    );

    CREATE INDEX  IF NOT EXISTS "idx_F_TrackerStats_Month_lookup" ON track_predict."F_TrackerStats_Month" USING btree ("TrackerId", "Year", "Month");


    -- track_predict."F_TrackerStats_Year" definition
    -- Drop table
    -- DROP TABLE track_predict."F_TrackerStats_Year";

    CREATE TABLE  IF NOT EXISTS track_predict."F_TrackerStats_Year" (
           "TrackerId" text NOT NULL,
           "Year" float8 NOT NULL,
           "DistanceKilometers" float8 NULL,
           "DistanceMeters" float8 NULL,
           "DeltaBatteryPercentage" float8 NULL,
           "DeltaBatteryVoltage" float8 NULL,
           "DeltaTimestampsMinutes" float8 NULL,
           "DeltaTimestampsHours" float8 NULL,
           "AverageSpeedKpH" float8 NULL,
           CONSTRAINT f_trackerstats_year_pk PRIMARY KEY ("TrackerId", "Year"),
           CONSTRAINT f_trackerstats_year_fk FOREIGN KEY ("TrackerId") REFERENCES track_predict."Tracker"("ID")
    );
    CREATE INDEX  IF NOT EXISTS "idx_F_TrackerStats_Year_lookup" ON track_predict."F_TrackerStats_Year" USING btree ("TrackerId", "Year");