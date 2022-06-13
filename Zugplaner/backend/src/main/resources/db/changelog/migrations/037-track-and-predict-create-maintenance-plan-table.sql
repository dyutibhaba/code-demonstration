DROP TABLE IF EXISTS track_predict."MaintenanceType" CASCADE;;
CREATE TABLE track_predict."MaintenanceType"
(
    "Id" uuid default uuid_generate_v4() not null,
    "Type" character varying(250) not null unique,
    constraint "MaintenanceType_pkey"
        primary key ("Id")
);

DROP TABLE IF EXISTS track_predict."MaintenancePlan" CASCADE;
CREATE TABLE track_predict."MaintenancePlan"
(
    "Id"              uuid default uuid_generate_v4() not null,
    "MaintenanceTypeId" uuid          not null
        constraint maintenancetypeid_fkey
            references track_predict."MaintenanceType" ("Id"),
    "AlarmSet"        boolean                         not null,
    "AlarmDue"        boolean,
    "FrequencyType"   integer                         not null,
    "FrequencyValue"  integer                         not null,
    "AssetId"         uuid                            not null
        constraint assetid_fkey
            references track_predict."Asset",
    "CreatorId"       uuid                            not null
        constraint creatorid_fkey
            references protostellar."1LUser" ("ID"),
    "Comment"         text,
    "SysInsertTS"     timestamp with time zone        not null,
    "SysUpdateTS"     timestamp with time zone,
    constraint "MaintenancePlan_pkey"
        primary key ("Id")
);

ALTER TABLE ONLY track_predict."Checklist"
    DROP COLUMN IF EXISTS "MaintenancePlanId",
    DROP CONSTRAINT IF EXISTS MaintenancePlanId_fkey,
    ADD COLUMN "MaintenancePlanId" uuid,
        ADD CONSTRAINT MaintenancePlanId_fkey
            FOREIGN KEY ("MaintenancePlanId")
                REFERENCES track_predict."MaintenancePlan"("Id");
