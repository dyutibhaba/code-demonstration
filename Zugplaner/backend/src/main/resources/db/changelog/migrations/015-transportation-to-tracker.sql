--changeset reda.jaifar:015.1

ALTER TABLE track_predict."Tracker"
    DROP COLUMN IF EXISTS "Transportation";

CREATE TABLE IF NOT EXISTS track_predict."Transportation" (
    "Id" UUID default public.uuid_generate_v4() NOT NULL UNIQUE PRIMARY KEY,
    "Label" character varying
);

CREATE TABLE IF NOT EXISTS track_predict."TransportationToTracker" (
    "TransportationId" UUID,
    "TrackerId" character varying,
    CONSTRAINT transportation_key FOREIGN KEY ("TransportationId") REFERENCES track_predict."Transportation"("Id"),
    CONSTRAINT tracker_key FOREIGN KEY ("TrackerId") REFERENCES track_predict."Tracker"("ID"),
    CONSTRAINT unique_transportation_tracker UNIQUE ("TransportationId", "TrackerId")
);


