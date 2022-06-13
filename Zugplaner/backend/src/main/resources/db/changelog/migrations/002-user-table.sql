DROP TABLE IF EXISTS protostellar."1LUser";

CREATE EXTENSION IF NOT EXISTS "uuid-ossp";

CREATE TABLE protostellar."1LUser" (
       "ID" UUID DEFAULT public.uuid_generate_v4() NOT NULL UNIQUE,
       "Email" character varying(250) PRIMARY KEY,
       "AzureAccountId" character varying,
       "DisplayedName" character varying(100),
       "SysInsertTS" timestamp without time zone NOT NULL,
       "SysInsertPrg" character varying(50),
       "SysInsertUserID" character varying(255),
       "SysUpdateTS" timestamp without time zone,
       "SysUpdatePrg" character varying(50),
       "SysUpdateUserID" character varying(255)
);
