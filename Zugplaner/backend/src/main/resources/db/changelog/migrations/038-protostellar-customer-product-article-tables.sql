DROP TABLE IF EXISTS protostellar."Product" CASCADE;

CREATE TABLE protostellar."Product" (
       "ID" UUID DEFAULT public.uuid_generate_v4() NOT NULL UNIQUE PRIMARY KEY,
       "Name" character varying(250),
       "Model" character varying(100),
       "Description" character varying(100),
       "SysInsertTS" timestamp without time zone NOT NULL,
       "SysInsertPrg" character varying(50),
       "SysInsertUserID" character varying(255),
       "SysUpdateTS" timestamp without time zone,
       "SysUpdatePrg" character varying(50),
       "SysUpdateUserID" character varying(255)
);

CREATE TABLE IF NOT EXISTS protostellar."Customer" (
  "ID" uuid PRIMARY KEY,
  "Name" varchar(250) NOT NULL,
  "SysInsertTS" timestamp NULL,
  "SysInsertPrg" varchar(50) NULL,
  "SysInsertUserID" varchar(50) NULL,
  "SysUpdateTS" timestamp NULL,
  "SysUpdatePrg" varchar(50) NULL,
  "SysUpdateUserID" varchar(50) NULL
  );

ALTER TABLE ONLY protostellar."Customer"
    DROP COLUMN IF EXISTS "Email",
    DROP COLUMN IF EXISTS "Description",
    ADD COLUMN "Email" character varying(250),
    ADD COLUMN "Description" character varying(100);

ALTER TABLE ONLY track_predict."UserGroup"
    DROP COLUMN IF EXISTS "CustomerId",
    ADD COLUMN "CustomerId" UUID NULL,
    ADD CONSTRAINT customer_ug_fkey FOREIGN KEY ("CustomerId") REFERENCES protostellar."Customer"("ID");

DROP TABLE IF EXISTS protostellar."Article" CASCADE;

CREATE TABLE protostellar."Article" (
       "ID" UUID DEFAULT public.uuid_generate_v4() NOT NULL UNIQUE PRIMARY KEY,
       "ThalesNr" character varying(250),
       "DetectorBar" boolean,
       "Trailable" boolean,
       "ConnectorPointDetector" boolean,
       "ThrowingForce" integer not null,
       "ThrowingStroke" integer not null,
       "MaxThrowingTime" decimal not null,
       "PhasePlug" boolean,
       "AdditionalContact" boolean,
       "StandardApplication" character varying(250),
       "SysInsertTS" timestamp without time zone NOT NULL,
       "SysInsertPrg" character varying(50),
       "SysInsertUserID" character varying(255),
       "SysUpdateTS" timestamp without time zone,
       "SysUpdatePrg" character varying(50),
       "SysUpdateUserID" character varying(255)
);

DROP TABLE IF EXISTS protostellar."ProductToArticle" CASCADE;

CREATE TABLE protostellar."ProductToArticle"
(
    "ProductId" UUID NOT NULL,
    "ArticleId" UUID NOT NULL,
    "SysInsertTS" timestamp without time zone NOT NULL,
    "SysInsertPrg" character varying(50),
    "SysInsertUserID" character varying(255),
    "SysUpdateTS" timestamp without time zone,
    "SysUpdatePrg" character varying(50),
    "SysUpdateUserID" character varying(255),
    CONSTRAINT product_fkey FOREIGN KEY ("ProductId") REFERENCES protostellar."Product"("ID"),
    CONSTRAINT article_fkey FOREIGN KEY ("ArticleId") REFERENCES protostellar."Article"("ID"),
    PRIMARY KEY ("ProductId", "ArticleId")
  );

DROP TABLE IF EXISTS protostellar."CustomerToArticlePrice" CASCADE;

CREATE TABLE protostellar."CustomerToArticlePrice"
(
    "CustomerId" UUID NOT NULL,
    "ArticleId"  UUID NOT NULL,
    "Price" DECIMAL,
    "SysInsertTS" timestamp without time zone NOT NULL,
    "SysInsertPrg" character varying(50),
    "SysInsertUserID" character varying(255),
    "SysUpdateTS" timestamp without time zone,
    "SysUpdatePrg" character varying(50),
    "SysUpdateUserID" character varying(255),
    CONSTRAINT customer_fkey FOREIGN KEY ("CustomerId") REFERENCES protostellar."Customer"("ID"),
    CONSTRAINT article_fkey FOREIGN KEY ("ArticleId") REFERENCES protostellar."Article"("ID"),
    PRIMARY KEY ("CustomerId", "ArticleId")
);
