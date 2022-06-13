ALTER TABLE track_predict."Tracker"
    ADD COLUMN "EtaVariation" integer,
    ADD COLUMN "Eta" timestamp without time zone;

ALTER TABLE track_predict."Transportation"
    RENAME TO "TrainNumber";

ALTER TABLE track_predict."TrainNumber"
    RENAME COLUMN "Label" TO "Value";

ALTER TABLE track_predict."TransportationToTracker"
    RENAME TO "TrainNumberToTracker";

ALTER TABLE track_predict."TrainNumberToTracker"
    RENAME COLUMN "TransportationId" TO "TrainNumberId";
