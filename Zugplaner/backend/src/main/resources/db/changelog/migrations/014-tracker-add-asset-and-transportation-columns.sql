--changeset reda.jaifar:014.1

ALTER TABLE track_predict."Tracker"
    ADD COLUMN IF NOT EXISTS "Asset"          character varying,
    ADD COLUMN IF NOT EXISTS "Transportation" character varying;

