CREATE TABLE IF NOT EXISTS track_predict."Checklist"
(
    "Id" UUID default public.uuid_generate_v4() NOT NULL UNIQUE PRIMARY KEY,
    "Content" JSONB,
    "AssetId" UUID NOT NULL,
    "CreatorId" UUID NOT NULL,
    "Type" int NOT NULL,
    "SysInsertTS" timestamp without time zone NOT NULL,
    "SysUpdateTS" timestamp without time zone,
    CONSTRAINT assetid_fkey FOREIGN KEY ("AssetId") REFERENCES track_predict."Asset"("ID"),
    CONSTRAINT creatorid_fkey FOREIGN KEY ("CreatorId") REFERENCES protostellar."1LUser"("ID")
);
