ALTER TABLE track_predict."UserGroup"
    ADD COLUMN IF NOT EXISTS "InfraOp_CustomerNo" varchar(30) NULL,
    ADD COLUMN IF NOT EXISTS "TSI_RecipientCode" varchar(10) NULL;

ALTER TABLE track_predict."UserGroup"
    DROP COLUMN "Recipient";
