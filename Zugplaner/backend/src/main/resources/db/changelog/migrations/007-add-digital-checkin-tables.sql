CREATE SCHEMA digital_checkin;

CREATE TABLE digital_checkin."Terminal" (
    "ID" UUID default public.uuid_generate_v4() NOT NULL UNIQUE PRIMARY KEY,
    "Name" character varying(250),
    "SysInsertTS" timestamp without time zone NOT NULL,
    "SysInsertPrg" character varying(50),
    "SysInsertUserID" character varying(255),
    "SysUpdateTS" timestamp without time zone,
    "SysUpdatePrg" character varying(50),
    "SysUpdateUserID" character varying(255)
);

CREATE TABLE digital_checkin."UserToTerminal" (
    "UserID" UUID,
    "TerminalID" UUID,
    CONSTRAINT user_fkey FOREIGN KEY ("UserID") REFERENCES protostellar."1LUser"("ID"),
    CONSTRAINT terminal_fkey FOREIGN KEY ("TerminalID") REFERENCES digital_checkin."Terminal"("ID")
);

CREATE TABLE digital_checkin."ContainerCheckinForm" (
    "ID" UUID default public.uuid_generate_v4() PRIMARY KEY ,
    "ContainerNumber" character varying,
    "VehiclePlateNumber" character varying,
    "CheckinDateTime" timestamp without time zone,
    "TerminalID" UUID,
    "Status" integer,
    "SysInsertTS" timestamp without time zone NOT NULL,
    "SysInsertPrg" character varying(50),
    "SysInsertUserID" character varying(255),
    "SysUpdateTS" timestamp without time zone,
    "SysUpdatePrg" character varying(50),
    "SysUpdateUserID" character varying(255),
    CONSTRAINT terminal_fkey FOREIGN KEY ("TerminalID") REFERENCES digital_checkin."Terminal"("ID")
);

CREATE TABLE digital_checkin."ContainerCheckinFormPicture" (
    "ID" UUID PRIMARY KEY,
    "StorageUrl" character varying,
    "RecognizedNumber" character varying,
    "ContainerCheckinFormID" UUID,
    "Status" integer,
    "Type" integer,
    "SysInsertTS" timestamp without time zone NOT NULL,
    "SysInsertPrg" character varying(50),
    "SysInsertUserID" character varying(255),
    "SysUpdateTS" timestamp without time zone,
    "SysUpdatePrg" character varying(50),
    "SysUpdateUserID" character varying(255),
    CONSTRAINT form_fkey FOREIGN KEY ("ContainerCheckinFormID") REFERENCES digital_checkin."ContainerCheckinForm"("ID")
);
