CREATE TABLE IF NOT EXISTS track_predict."TrackerDataHistory" (
    "TrackerId" character varying,
    "PositionGpsLatitude" real,
    "PositionGpsLongitude" real,
    "PositionAddress" character varying(255),
    "BatteryPercentage" integer,
    "BatteryVoltage" real,
    "SpeedKph" real,
    "LastLocationDateTime" timestamp without time zone NOT NULL,
    "SystemInsertTimestamp" timestamp without time zone,
    "SystemInsertProgram" character varying(50),
    CONSTRAINT tracker_fkey FOREIGN KEY ("TrackerId") REFERENCES track_predict."Tracker"("ID")
);
