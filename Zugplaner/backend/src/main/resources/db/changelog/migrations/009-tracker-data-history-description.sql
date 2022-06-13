ALTER TABLE ONLY track_predict."TrackerDataHistory"
    ADD COLUMN "SysInsertUserID" character varying(255),
    ADD COLUMN "SysUpdateTS" timestamp without time zone,
    ADD COLUMN "SysUpdatePrg" character varying(50),
    ADD COLUMN "SysUpdateUserID" character varying(255),
    ADD COLUMN "Description" character varying(255);

ALTER TABLE ONLY track_predict."TrackerDataHistory"
  RENAME COLUMN "SystemInsertTimestamp" TO "SysInsertTS";

  ALTER TABLE ONLY track_predict."TrackerDataHistory"
  RENAME COLUMN "SystemInsertProgram" TO "SysInsertPrg";
