ALTER TABLE track_predict."UserGroup"
    ADD COLUMN "Recipient" character varying;

ALTER TABLE track_predict."TrainLastPosition"
    ADD COLUMN "Recipient" character varying;

ALTER TABLE track_predict."TrainETADelay"
    ADD COLUMN "Recipient" character varying;

ALTER TABLE track_predict."Checklist"
     ALTER COLUMN "SysInsertTS" SET DATA TYPE timestamp with time zone,
     ALTER COLUMN "SysUpdateTS" SET DATA TYPE timestamp with time zone;
