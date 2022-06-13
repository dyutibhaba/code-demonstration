SET search_path TO public;
DROP EXTENSION IF EXISTS "uuid-ossp" CASCADE;
CREATE EXTENSION "uuid-ossp";

-- File here for historical reasons, not to be used in migrations
CREATE SCHEMA IF NOT EXISTS protostellar;
--
-- TOC entry 186 (class 1259 OID 26699)
-- Name: 1LEMailAccounts; Type: TABLE; Schema: protostellar; Owner: postgres
--

CREATE TABLE IF NOT EXISTS protostellar."1LEMailAccounts" (
    "EMailAdress" character varying(250) NOT NULL,
    "1LUserID" character varying(250),
    "CustomerAddressID" bigint NOT NULL,
    "FlagTOSendPermitted" boolean NOT NULL,
    "Name" character varying(2000) NOT NULL,
    "SYS_InsertTS" date NOT NULL,
    "SYS_InsertPGM" character varying(250) NOT NULL,
    "SYS_UpdateTS" date,
    "SYS_UpdatePGM" character varying(250)
);


ALTER TABLE protostellar."1LEMailAccounts" OWNER TO postgres;

--
-- TOC entry 187 (class 1259 OID 26705)
-- Name: 1LEMailAttachmentDetails; Type: TABLE; Schema: protostellar; Owner: postgres
--

CREATE TABLE IF NOT EXISTS protostellar."1LEMailAttachmentDetails" (
    "AttachmentFilename" character varying(2000),
    "MailFile_MessageID" character varying(500),
    "MailFile_filename" character varying(2000),
    "MailFile_rownumber" bigint,
    "MailFile_ShortFilename" character varying(500),
    "MailFile_Extension" character varying(20),
    "MailFile_Path" character varying(2000),
    "MailFile_Size" character varying(20),
    "MailFile_IsHidden" boolean,
    "MailFile_LastMod" timestamp without time zone,
    "MailFile_Uri" character varying(2000),
    "MailFile_RootUri" character varying(2000),
    "SYSInfo_SystemDate" timestamp without time zone,
    "SYSInfo_TransformationName" character varying(250),
    "SYSInfo_Hostname" character varying(250),
    "SYSInfo_HostIP" character varying(20),
    "SYSInfo_HostMAC" character varying(250),
    "SYSInfo_PDIversion" character varying(100),
    "SYSInfo_PDIbuild" character varying(100),
    "SYSInfo_PDIbuildDate" timestamp without time zone,
    "SYSInfo_ProcessID" bigint,
    "SYSInfo_CPUcount" bigint,
    "SYSInfo_MemJVMmax" bigint,
    "SYSInfo_MemJVMtotal" bigint,
    "SYSInfo_MemJVMfree" bigint,
    "SYSInfo_MemJVMavailable" bigint,
    "SYSInfo_MemPhysicalTotal" bigint,
    "SYSInfo_MemPhysicalFree" bigint,
    "SYSInfo_MemSwap" bigint,
    "SYSInfo_JVMCPUtime" bigint,
    filename character varying(2000),
    short_filename character varying(500),
    path character varying(2000),
    type character varying(500),
    "exists" boolean,
    ishidden boolean,
    isreadable boolean,
    iswriteable boolean,
    lastmodifiedtime timestamp without time zone,
    size double precision,
    extension character varying,
    uri character varying(500),
    rooturi character varying(500),
    "FilenumberFound" bigint,
    "SysTimestamp" timestamp without time zone,
    "Message number" bigint,
    "Subject" character varying(2000),
    "Sender" character varying(2000),
    "Reply to" character varying(2000),
    "Recipients" character varying(32000),
    "Description" character varying(2000),
    "Body" character varying,
    "Received date" timestamp without time zone,
    "MailSentDate" character varying(500),
    "Content type" character varying(500),
    "Folder name" character varying(500),
    "Flag new" boolean,
    "Flag read" boolean,
    "Flag flagged" boolean,
    "Flag draft" boolean,
    "Flag deleted" boolean,
    "Attached files count" bigint,
    "Mail Header(Name)" character varying(500),
    "Content Type (Body)" character varying(500),
    "Message-ID" character varying(500),
    "X-Envelope-From" character varying(2000),
    "X-Envelope-To" character varying(500),
    "X-Delivery-Time" character varying(250),
    "X-UID" character varying(500),
    "From_field" character varying(500),
    "To_field" character varying(500),
    "Date_field" character varying(250),
    "AssignedToOrderID" bigint
);


ALTER TABLE protostellar."1LEMailAttachmentDetails" OWNER TO postgres;

--
-- TOC entry 2718 (class 0 OID 0)
-- Dependencies: 187
-- Name: COLUMN "1LEMailAttachmentDetails"."AssignedToOrderID"; Type: COMMENT; Schema: protostellar; Owner: postgres
--

COMMENT ON COLUMN protostellar."1LEMailAttachmentDetails"."AssignedToOrderID" IS 'the order-ID that will be used for this attachment if it will be registered';


--
-- TOC entry 188 (class 1259 OID 26711)
-- Name: 1LEMailReceiveAllDetails; Type: TABLE; Schema: protostellar; Owner: postgres
--

CREATE TABLE IF NOT EXISTS protostellar."1LEMailReceiveAllDetails" (
    "Message-ID" character varying NOT NULL,
    "X-Delivery-Time" character varying,
    "X-Envelope-From" character varying,
    "X-Envelope-To" character varying,
    "Date_field" character varying,
    "From_field" character varying,
    "To_field" character varying,
    "Return-Path" character varying,
    "Subject" character varying,
    "X-UID" character varying,
    "Authentication-Results" character varying,
    "X-Strato-MessageType" character varying,
    "X-RZG-CLASS-ID" character varying,
    "Received-SPF" character varying,
    "Received" character varying,
    "DKIM-Signature" character varying,
    "X-RZG-AUTH" character varying,
    "X-RZG-CLASS-ID_1" character varying,
    "References" character varying,
    "X-Forwarded-Message-Id" character varying,
    "User-Agent" character varying,
    "MIME-Version" character varying,
    "In-Reply-To" character varying,
    "Content-Type" character varying,
    "MailFile_filename" character varying,
    "MailFile_rownumber" bigint,
    "MailFile_ShortFilename" character varying,
    "MailFile_Extension" character varying,
    "MailFile_Path" character varying,
    "MailFile_Size" character varying,
    "MailFile_IsHidden" boolean,
    "MailFile_LastMod" timestamp without time zone,
    "MailFile_Uri" character varying,
    "MailFile_RootUri" character varying,
    "Mailfile_MessageID" character varying
);


ALTER TABLE protostellar."1LEMailReceiveAllDetails" OWNER TO postgres;

--
-- TOC entry 189 (class 1259 OID 26717)
-- Name: 1LEMailReceiveDetails; Type: TABLE; Schema: protostellar; Owner: postgres
--

CREATE TABLE IF NOT EXISTS protostellar."1LEMailReceiveDetails" (
    "SysTimestamp" timestamp without time zone,
    "Message number" bigint,
    "Subject" character varying,
    "Sender" character varying,
    "Reply to" character varying,
    "Recipients" character varying,
    "Description" character varying,
    "Body" character varying,
    "Received date" timestamp without time zone,
    "Sent date" timestamp without time zone,
    "Content type" character varying,
    "Folder name" character varying,
    "Size" bigint,
    "Flag new" boolean,
    "Flag read" boolean,
    "Flag flagged" boolean,
    "Flag draft" boolean,
    "Flag deleted" boolean,
    "Attached files count" bigint,
    "Mail Header(Name)" character varying,
    "Content Type (Body)" character varying,
    "SYSInfo_SystemDate" timestamp without time zone,
    "SYSInfo_TransformationName" character varying,
    "SYSInfo_Hostname" character varying,
    "SYSInfo_HostIP" character varying,
    "SYSInfo_HostMAC" character varying,
    "SYSInfo_PDIversion" character varying,
    "SYSInfo_PDIbuild" character varying,
    "SYSInfo_PDIbuildDate" timestamp without time zone,
    "SYSInfo_ProcessID" bigint,
    "SYSInfo_CPUcount" bigint,
    "SYSInfo_MemJVMmax" bigint,
    "SYSInfo_MemJVMtotal" bigint,
    "SYSInfo_MemJVMfree" bigint,
    "SYSInfo_MemJVMavailable" bigint,
    "SYSInfo_MemPhysicalTotal" bigint,
    "SYSInfo_MemPhysicalFree" bigint,
    "SYSInfo_MemSwap" bigint,
    "SYSInfo_JVMCPUtime" bigint
);


ALTER TABLE protostellar."1LEMailReceiveDetails" OWNER TO postgres;

--
-- TOC entry 190 (class 1259 OID 26723)
-- Name: 1LOrderMaintFilesProcessed; Type: TABLE; Schema: protostellar; Owner: postgres
--

CREATE TABLE IF NOT EXISTS protostellar."1LOrderMaintFilesProcessed" (
    "ShortFilename" character varying(2000) NOT NULL,
    "InsertTS" timestamp without time zone,
    "UpdateTS" timestamp without time zone,
    "UpdatePrg" character varying(500),
    "InsetPrg" character varying(500)
);


ALTER TABLE protostellar."1LOrderMaintFilesProcessed" OWNER TO postgres;

--
-- TOC entry 191 (class 1259 OID 26729)
-- Name: 1LPartnerSystem; Type: TABLE; Schema: protostellar; Owner: postgres
--

CREATE TABLE IF NOT EXISTS protostellar."1LPartnerSystem" (
    "SystemShortDesc" character varying(100) NOT NULL,
    "SystemDesc" character varying(500) NOT NULL,
    "1LPartnerSystemCode" character varying(50) NOT NULL,
    "PartnerSystemCountryCode" character varying(5),
    "ProtostellarCodeAtPartner" character varying(20),
    "ProtostellarNameAtPartner" character varying(250),
    "PartnerSystemCodelist" character varying(20),
    "ProtostellarOperatorcodeAtPartner" character varying(20),
    "ID" bigint NOT NULL,
    "CustomerNumberAtPartner" character varying(30)
);


ALTER TABLE protostellar."1LPartnerSystem" OWNER TO postgres;

--
-- TOC entry 192 (class 1259 OID 26735)
-- Name: 1LPartnersMessageProtocol; Type: TABLE; Schema: protostellar; Owner: postgres
--

CREATE TABLE IF NOT EXISTS protostellar."1LPartnersMessageProtocol" (
    "ContainerNumber" character varying(12),
    "1LPartnerSystem_ID" bigint,
    "OrderItemVoyageSegmentID" bigint,
    "OrderVoyageSegmentID" bigint,
    "OrderVoyageSegmentTrainID" bigint,
    "OrderItemID" bigint,
    "OrderID" bigint,
    "ItemID" bigint,
    "ScheduledTrainID" bigint,
    "GoodsID" bigint,
    "OVSTrain_ID" bigint,
    "OVSTrain_TrainID" bigint,
    "SeqNo" integer,
    "WagonSeqNo" integer,
    "Item_OnWagonSeq" bigint,
    "Goods_InItemSeq" bigint,
    "1LPartnerSystem_1LPartnerSystemCode" character varying(50),
    "1LPartnerSystem_CustomerNumberAtPartner" character varying(30),
    "1LRecordVersion" bigint,
    "1LReferenceCode" character varying(100),
    "CommunicationCreationTime" timestamp without time zone,
    "ContainerEmptyPickupDepot" character varying(50),
    "ContainerEmptyPickupReference" character varying(50),
    "ContainerEmptyReturnDepot" character varying(50),
    "ContainerEmptyReturnDropoffReference" character varying(50),
    "ContainerFillingDegreeID" bigint,
    "ContainerReleasePIN" character varying(50),
    "ContainerReleasePassword" character varying(250),
    "ContainerSizeID" bigint,
    "CustomerAddressID" bigint,
    "CustomsDocumentsATBNo" character varying(50),
    "CustomsDocumentsNo" character varying(50),
    "CustomsDocumentsTypeID" bigint,
    "CustomsHandlingTypeID" bigint,
    "CustomsProcedureID" bigint,
    "CustomsTariffID" bigint,
    "CustomsTradeAreaStatusID" bigint,
    "DeliveryDateTime" timestamp without time zone,
    "DestinationLocationID" character varying(100),
    "DestinationPortCountryID" bigint,
    "DestinationPortInterfacePartnerSystemID" bigint,
    "DestinationPortLocationID" character varying(100),
    "DestinationRailstationInterfacePartnerSystemID" bigint,
    "DestinationRailstationLocationID" character varying(100),
    "DischargeDate" timestamp without time zone,
    "DispatchDate" timestamp without time zone,
    "ExchangeTestMessageFlag" boolean,
    "ForwardingAgentAddressID" bigint,
    "InboundPartnerSystemExchangeNumber" integer,
    "InboundPartnerSystemID" bigint,
    "InboundPartnerSystemTransportOrderNumber" character varying(100),
    "KLVIndicator" character varying(1),
    "LoadingRequestID" bigint,
    "MessageReferenceNumber" text,
    "NoOfPackages" integer,
    "OVSTrain_DestinationSlotNumber" text,
    "OVSTrain_TrainDate" timestamp without time zone,
    "OceanCarrierAddressID" bigint,
    "OceanCarrierBLNo" character varying(50),
    "OceanCarrierBookingNo" character varying(15),
    "OrderDateTime" timestamp without time zone,
    "OrderNumberPS" character varying(20),
    "Order_DestinationLocationID" character varying(100),
    "Order_StartLocationID" character varying(100),
    "OutboundPartnerSystemExchangeNumber" integer,
    "OutboundPartnerSystemID" bigint,
    "OutboundPartnerSystemTransportOrderNumber" character varying(100),
    "PackageCodeID" bigint,
    "SealLocationID" bigint,
    "SealTypeID" bigint,
    "ShipCallingCode" character varying(20),
    "ShipClearanceDateTime" timestamp without time zone,
    "ShipID" bigint,
    "ShipIMONumber" character varying(20),
    "ShipName" character varying(200),
    "ShipmentNumber" character varying(50),
    "ShippingCompanyAddressID" bigint,
    "ShippingCompanyReferenceNumber" character varying(50),
    "ShippingUnitDestinationCountryCode" character varying(5),
    "ShippingUnitOriginCountryCode" character varying(5),
    "ShippingUnitPositionNo" smallint,
    "ShippingUnitRailwayClass" character varying(20),
    "SourcePortLocationID" character varying(100),
    "SourceRailstationInterfacePartnerSystemID" bigint,
    "SourceRailstationLocationID" character varying(100),
    "StartLocationID" character varying(100),
    "TO_Filename" text,
    "TO_FilenameFull" text,
    "Train_TrainNumber" character varying(10),
    "TransportCompanyAddressID" bigint,
    "TransportationMeansID" bigint,
    "VoyageTypeID" bigint,
    "WRITE-SEQ" double precision,
    "Wagon_ID" bigint,
    "TimeStamp1" timestamp without time zone,
    "TS_DATN8" text,
    "TS_TIMN8" text
);


ALTER TABLE protostellar."1LPartnersMessageProtocol" OWNER TO postgres;

--
-- TOC entry 193 (class 1259 OID 26741)
-- Name: 1LRole; Type: TABLE; Schema: protostellar; Owner: postgres
--

CREATE TABLE IF NOT EXISTS protostellar."1LRole" (
    "ID" bigint NOT NULL,
    "Desc" character varying(100) NOT NULL,
    "Remarks" character varying(2000),
    "FlagActive" boolean,
    "FlagDeleted" boolean,
    "SysInsertTS" timestamp without time zone,
    "SysInsertPrg" character varying(50),
    "SysInsertUserID" character varying(50),
    "SysUpdateTS" timestamp without time zone,
    "SysUpdatePrg" character varying(50),
    "SysUpdateUserID" character varying(50)
);


ALTER TABLE protostellar."1LRole" OWNER TO postgres;

--
-- TOC entry 194 (class 1259 OID 26747)
-- Name: 1LStatusCode; Type: TABLE; Schema: protostellar; Owner: postgres
--

CREATE TABLE IF NOT EXISTS protostellar."1LStatusCode" (
    "ID" bigint NOT NULL,
    "Warning_Flag" boolean,
    "Error_Flag" boolean,
    "1LStatusCode" integer NOT NULL,
    "1LStatusMessage" character varying(2000),
    "SysInsertTS" timestamp without time zone,
    "SysInsertPrg" character varying(50),
    "SysInsertUserID" character varying(50),
    "SysUpdateTS" timestamp without time zone,
    "SysUpdatePrg" character varying(50),
    "SysUpdateUserID" character varying(50)
);


ALTER TABLE protostellar."1LStatusCode" OWNER TO postgres;

--
-- TOC entry 195 (class 1259 OID 26753)
-- Name: 1LStatusMapping; Type: TABLE; Schema: protostellar; Owner: postgres
--

CREATE TABLE IF NOT EXISTS protostellar."1LStatusMapping" (
    "1LStatusGroup" character varying(5) NOT NULL,
    "PartnerReplyQualifier" character varying(10) NOT NULL,
    "PartnerStatusCode" character varying(50) NOT NULL,
    "PartnerStatusMessage" character varying(4000),
    "1LStatusCodeID" integer NOT NULL,
    "PartnerSystemID" bigint NOT NULL,
    "PartnerTransportDirectionID" bigint NOT NULL,
    "1LStatusObjectID" bigint NOT NULL,
    "1LStatusMessage" character varying(1000)
);


ALTER TABLE protostellar."1LStatusMapping" OWNER TO postgres;

--
-- TOC entry 196 (class 1259 OID 26759)
-- Name: 1LStatusObject; Type: TABLE; Schema: protostellar; Owner: postgres
--

CREATE TABLE IF NOT EXISTS protostellar."1LStatusObject" (
    "ID" bigint NOT NULL,
    "Code" character varying(5) NOT NULL,
    "Desc" character varying(100) NOT NULL
);


ALTER TABLE protostellar."1LStatusObject" OWNER TO postgres;

--
-- TOC entry 197 (class 1259 OID 26762)
-- Name: 1LSystemExecResultsJobs; Type: TABLE; Schema: protostellar; Owner: postgres
--

CREATE TABLE IF NOT EXISTS protostellar."1LSystemExecResultsJobs" (
    "StartDate" timestamp without time zone NOT NULL,
    "StartTime" timestamp without time zone NOT NULL,
    "MachineName" text NOT NULL,
    "Process" character varying(389) NOT NULL,
    "StatusTime" timestamp without time zone,
    "StatusDate" timestamp without time zone,
    "Status" text,
    "MachineIP" text NOT NULL,
    "PDIversion" text,
    "MemoryJVMmax" bigint,
    "MemoryJVMtotal" bigint,
    "MemoryJVMfree" bigint,
    "MemoryPhysicalFree" bigint,
    "MemoryPhysicalTotal" bigint,
    "CPUcount" bigint,
    "MachineMAC" text,
    "OSName" character varying(389),
    "OSVersion" character varying(389),
    "OSUserID" character varying(389),
    "JVMversion" character varying(389),
    "JavaSpecificationVersion" character varying(389),
    "ExecutionTime" bigint,
    "ExecutionResult" boolean,
    "ExecutionNrErrors" integer,
    "ExecutionLinesRead" integer,
    "ExecutionLinesWritten" integer,
    "ExecutionLinesInput" integer,
    "ExecutionLinesOutput" integer,
    "ExecutionLinesRejected" integer,
    "ExecutionLinesUpdated" integer,
    "ExecutionLinesDeleted" integer,
    "ExecutionFilesRetrieved" integer,
    "ExecutionExitStatus" smallint,
    "ExecutionLogText" text,
    "ExecutionLogChannelID" character varying(50)
);


ALTER TABLE protostellar."1LSystemExecResultsJobs" OWNER TO postgres;

--
-- TOC entry 198 (class 1259 OID 26768)
-- Name: 1LTabColHelp; Type: TABLE; Schema: protostellar; Owner: postgres
--

CREATE TABLE IF NOT EXISTS protostellar."1LTabColHelp" (
    "TableName" character varying(2000) NOT NULL,
    "ColName" character varying(2000) NOT NULL,
    "LanguageID" bigint NOT NULL,
    "HelpTextShort" character varying NOT NULL,
    "HelpTextLong" character varying NOT NULL
);


ALTER TABLE protostellar."1LTabColHelp" OWNER TO postgres;

--
-- TOC entry 199 (class 1259 OID 26774)
-- Name: 1LTabColRules; Type: TABLE; Schema: protostellar; Owner: postgres
--

CREATE TABLE IF NOT EXISTS protostellar."1LTabColRules" (
    "TableName" character varying(2000) NOT NULL,
    "ColName" character varying(2000) NOT NULL,
    "NullOnly" boolean NOT NULL,
    "NullForbidden" boolean NOT NULL,
    "MinVal" character varying,
    "MaxVal" character varying,
    "MinLength" integer,
    "MaxLength" integer,
    "ValuesOnly" character varying,
    "ValuesForbidden" character varying,
    "StartOnly" character varying,
    "StartForbidden" character varying,
    "EndOnly" character varying,
    "EndForbidden" character varying,
    "MaskOnly" character varying,
    "MaskForbidden" character varying,
    "RegExOnly" character varying,
    "RegExForbidden" character varying,
    "Message" character varying NOT NULL
);


ALTER TABLE protostellar."1LTabColRules" OWNER TO postgres;

--
-- TOC entry 200 (class 1259 OID 26780)
-- Name: 1LTabColValidationResults; Type: TABLE; Schema: protostellar; Owner: postgres
--

CREATE TABLE IF NOT EXISTS protostellar."1LTabColValidationResults" (
    "TableName" character varying(500) NOT NULL,
    "ColName" character varying(250) NOT NULL,
    "RecID" character varying(500) NOT NULL,
    "ResultCode" character varying(10) NOT NULL,
    "Desc" character varying(2000) NOT NULL,
    "ValidationTime" timestamp without time zone,
    "ValidationPrg" character varying
);


ALTER TABLE protostellar."1LTabColValidationResults" OWNER TO postgres;

--
-- TOC entry 201 (class 1259 OID 26786)
-- Name: 1LTabColValResOrder; Type: VIEW; Schema: protostellar; Owner: postgres
--

CREATE VIEW protostellar."1LTabColValResOrder" AS
 SELECT "1LTabColValidationResults"."TableName",
    "1LTabColValidationResults"."ColName",
    "1LTabColValidationResults"."RecID",
    "1LTabColValidationResults"."ResultCode",
    "1LTabColValidationResults"."Desc",
    "1LTabColValidationResults"."ValidationTime",
    "1LTabColValidationResults"."ValidationPrg"
   FROM protostellar."1LTabColValidationResults"
  WHERE (("1LTabColValidationResults"."TableName")::text = 'Order'::text);


ALTER TABLE protostellar."1LTabColValResOrder" OWNER TO postgres;

--
-- TOC entry 202 (class 1259 OID 26790)
-- Name: 1LTabColValResOrderItem; Type: VIEW; Schema: protostellar; Owner: postgres
--

CREATE VIEW protostellar."1LTabColValResOrderItem" AS
 SELECT "1LTabColValidationResults"."TableName",
    "1LTabColValidationResults"."ColName",
    "1LTabColValidationResults"."RecID",
    "1LTabColValidationResults"."ResultCode",
    "1LTabColValidationResults"."Desc",
    "1LTabColValidationResults"."ValidationTime",
    "1LTabColValidationResults"."ValidationPrg"
   FROM protostellar."1LTabColValidationResults"
  WHERE (("1LTabColValidationResults"."TableName")::text = 'OrderItem'::text);


ALTER TABLE protostellar."1LTabColValResOrderItem" OWNER TO postgres;

--
-- TOC entry 203 (class 1259 OID 26794)
-- Name: 1LTabColValResOrderItemGoods; Type: VIEW; Schema: protostellar; Owner: postgres
--

CREATE VIEW protostellar."1LTabColValResOrderItemGoods" AS
 SELECT "1LTabColValidationResults"."TableName",
    "1LTabColValidationResults"."ColName",
    "1LTabColValidationResults"."RecID",
    "1LTabColValidationResults"."ResultCode",
    "1LTabColValidationResults"."Desc",
    "1LTabColValidationResults"."ValidationTime",
    "1LTabColValidationResults"."ValidationPrg"
   FROM protostellar."1LTabColValidationResults"
  WHERE (("1LTabColValidationResults"."TableName")::text = 'OrderItemGoods'::text);


ALTER TABLE protostellar."1LTabColValResOrderItemGoods" OWNER TO postgres;

--
-- TOC entry 204 (class 1259 OID 26798)
-- Name: 1LTabColValResOrderItemSeals; Type: VIEW; Schema: protostellar; Owner: postgres
--

CREATE VIEW protostellar."1LTabColValResOrderItemSeals" AS
 SELECT "1LTabColValidationResults"."TableName",
    "1LTabColValidationResults"."ColName",
    "1LTabColValidationResults"."RecID",
    "1LTabColValidationResults"."ResultCode",
    "1LTabColValidationResults"."Desc",
    "1LTabColValidationResults"."ValidationTime",
    "1LTabColValidationResults"."ValidationPrg"
   FROM protostellar."1LTabColValidationResults"
  WHERE (("1LTabColValidationResults"."TableName")::text = 'OrderItemSeals'::text);


ALTER TABLE protostellar."1LTabColValResOrderItemSeals" OWNER TO postgres;

--
-- TOC entry 205 (class 1259 OID 26802)
-- Name: 1LTabColValResOrderItemVoyageSegment; Type: VIEW; Schema: protostellar; Owner: postgres
--

CREATE VIEW protostellar."1LTabColValResOrderItemVoyageSegment" AS
 SELECT "1LTabColValidationResults"."TableName",
    "1LTabColValidationResults"."ColName",
    "1LTabColValidationResults"."RecID",
    "1LTabColValidationResults"."ResultCode",
    "1LTabColValidationResults"."Desc",
    "1LTabColValidationResults"."ValidationTime",
    "1LTabColValidationResults"."ValidationPrg"
   FROM protostellar."1LTabColValidationResults"
  WHERE (("1LTabColValidationResults"."TableName")::text = 'OrderItemVoyageSegment'::text);


ALTER TABLE protostellar."1LTabColValResOrderItemVoyageSegment" OWNER TO postgres;

--
-- TOC entry 206 (class 1259 OID 26806)
-- Name: 1LTabColValResOrderVoyageSegment; Type: VIEW; Schema: protostellar; Owner: postgres
--

CREATE VIEW protostellar."1LTabColValResOrderVoyageSegment" AS
 SELECT "1LTabColValidationResults"."TableName",
    "1LTabColValidationResults"."ColName",
    "1LTabColValidationResults"."RecID",
    "1LTabColValidationResults"."ResultCode",
    "1LTabColValidationResults"."Desc",
    "1LTabColValidationResults"."ValidationTime",
    "1LTabColValidationResults"."ValidationPrg"
   FROM protostellar."1LTabColValidationResults"
  WHERE (("1LTabColValidationResults"."TableName")::text = 'OrderVoyageSegment'::text);


ALTER TABLE protostellar."1LTabColValResOrderVoyageSegment" OWNER TO postgres;

--
-- TOC entry 207 (class 1259 OID 26810)
-- Name: 1LUser; Type: TABLE; Schema: protostellar; Owner: postgres
--

CREATE TABLE IF NOT EXISTS protostellar."1LUser" (
    "ID" character varying(50) NOT NULL,
    "LastName" character varying(100) NOT NULL,
    "FirstName" character varying(100) NOT NULL,
    "EMail1" character varying(250),
    "CompanyName" character varying(250) NOT NULL,
    "FlagActive" boolean NOT NULL,
    "FlagSecurityBlocked" boolean NOT NULL,
    "SysInsertTS" timestamp without time zone NOT NULL,
    "SysInsertPrg" character varying(100) NOT NULL,
    "SysInsertUserID" character varying(50) NOT NULL,
    "SysUpdateTS" timestamp without time zone,
    "SysUpdatePrg" character varying(50),
    "SysUpdateUserID" character varying(50)
);


ALTER TABLE protostellar."1LUser" OWNER TO postgres;

--
-- TOC entry 208 (class 1259 OID 26816)
-- Name: 1LUserRole; Type: TABLE; Schema: protostellar; Owner: postgres
--

CREATE TABLE IF NOT EXISTS protostellar."1LUserRole" (
    "1LUserID" character varying(50) NOT NULL,
    "1LRoleID" bigint NOT NULL,
    "SysInsertTS" timestamp without time zone,
    "SysInsertPrg" character varying(50),
    "SysInsertUserID" character varying(50),
    "SysUpdateTS" timestamp without time zone,
    "SysUpdatePrg" character varying(50),
    "SysUpdateUserID" character varying(50)
);


ALTER TABLE protostellar."1LUserRole" OWNER TO postgres;

--
-- TOC entry 209 (class 1259 OID 26819)
-- Name: Address; Type: TABLE; Schema: protostellar; Owner: postgres
--

CREATE TABLE IF NOT EXISTS protostellar."Address" (
    "ID" bigint NOT NULL,
    "Role" character varying,
    "Code" character varying,
    "CodeAlias" character varying,
    "Name" character varying,
    "StreetNo" character varying,
    "City" character varying,
    "PostCode" character varying,
    "CountryCode" character varying,
    "Contact01Name" character varying,
    "Contact01Email" character varying,
    "Contact01Phone1" character varying,
    "Contact01Phone2" character varying,
    "Contact02Name" character varying,
    "Contact02Email" character varying,
    "Contact02Phone1" character varying,
    "Contact02Phone2" character varying,
    "PSCustomerNumber" character varying(20),
    "PSSupplierNumber" character varying(20),
    "ProvidesTransportModeSea" boolean,
    "ProvidesTransportModeRail" boolean,
    "ProvidesTransportModeRoad" boolean,
    "ProvidesTransportModeAir" boolean,
    "FlagOceanCarrier" boolean,
    "FlagTransportCompany" boolean,
    "Remarks" text,
    "AddressGroupID" bigint,
    "SysInsertTS" timestamp without time zone,
    "SysInsertPrg" character varying(50),
    "SysInsertUserID" character varying(50),
    "SysUpdateTS" timestamp without time zone,
    "SysUpdatePrg" character varying(50),
    "SysUpdateUserID" character varying(50)
);


ALTER TABLE protostellar."Address" OWNER TO postgres;

--
-- TOC entry 210 (class 1259 OID 26825)
-- Name: AddressGroup; Type: TABLE; Schema: protostellar; Owner: postgres
--

CREATE TABLE IF NOT EXISTS protostellar."AddressGroup" (
    "ID" bigint NOT NULL,
    "Name" character varying(200) NOT NULL,
    "SysInsertTS" timestamp without time zone,
    "SysInsertPrg" character varying(50),
    "SysInsertUserID" character varying(50),
    "SysUpdateTS" timestamp without time zone,
    "SysUpdatePrg" character varying(50),
    "SysUpdateUserID" character varying(50)
);


ALTER TABLE protostellar."AddressGroup" OWNER TO postgres;

--
-- TOC entry 211 (class 1259 OID 26828)
-- Name: Address_Matchinglist; Type: TABLE; Schema: protostellar; Owner: postgres
--

CREATE TABLE IF NOT EXISTS protostellar."Address_Matchinglist" (
    "SearchText" text,
    "AddressCode" text
);


ALTER TABLE protostellar."Address_Matchinglist" OWNER TO postgres;

--
-- TOC entry 212 (class 1259 OID 26834)
-- Name: Address_PartnerSystem; Type: TABLE; Schema: protostellar; Owner: postgres
--

CREATE TABLE IF NOT EXISTS protostellar."Address_PartnerSystem" (
    "AddressID" bigint NOT NULL,
    "PartnerSystemID" bigint NOT NULL,
    "Code" character varying(200)
);


ALTER TABLE protostellar."Address_PartnerSystem" OWNER TO postgres;

--
-- TOC entry 213 (class 1259 OID 26837)
-- Name: ContainerFillingDegree; Type: TABLE; Schema: protostellar; Owner: postgres
--

CREATE TABLE IF NOT EXISTS protostellar."ContainerFillingDegree" (
    "ID" bigint NOT NULL,
    "Code" character varying(10) NOT NULL,
    "Desc" character varying(100) NOT NULL,
    "SysInsertTS" timestamp without time zone,
    "SysInsertPrg" character varying(50),
    "SysInsertUserID" character varying(50),
    "SysUpdateTS" timestamp without time zone,
    "SysUpdatePrg" character varying(50),
    "SysUpdateUserID" character varying(50)
);


ALTER TABLE protostellar."ContainerFillingDegree" OWNER TO postgres;

--
-- TOC entry 214 (class 1259 OID 26840)
-- Name: ContainerFillingDegree_PartnerSystem; Type: TABLE; Schema: protostellar; Owner: postgres
--

CREATE TABLE IF NOT EXISTS protostellar."ContainerFillingDegree_PartnerSystem" (
    "ContainerFillingDegreeID" bigint NOT NULL,
    "PartnerSystemID" bigint NOT NULL,
    "Code" character varying(20) NOT NULL
);


ALTER TABLE protostellar."ContainerFillingDegree_PartnerSystem" OWNER TO postgres;

--
-- TOC entry 215 (class 1259 OID 26843)
-- Name: ContainerSize; Type: TABLE; Schema: protostellar; Owner: postgres
--

CREATE TABLE IF NOT EXISTS protostellar."ContainerSize" (
    "ContainerTypeCode" character varying NOT NULL,
    "Remarks" character varying,
    "FlagActive" boolean,
    "FlagDeleted" boolean,
    "ContainerSizeCode" character varying NOT NULL,
    "LengthFt" numeric(7,3) NOT NULL,
    "HeightFt" numeric NOT NULL,
    "ISOCode" character varying,
    "TEU" numeric(7,3),
    "ID" bigint NOT NULL,
    "RefrigerationFlag" boolean,
    "SysInsertTS" timestamp without time zone,
    "SysInsertPrg" character varying(50),
    "SysInsertUserID" character varying(50),
    "SysUpdateTS" timestamp without time zone,
    "SysUpdatePrg" character varying(50),
    "SysUpdateUserID" character varying(50)
);


ALTER TABLE protostellar."ContainerSize" OWNER TO postgres;

--
-- TOC entry 216 (class 1259 OID 26849)
-- Name: Country; Type: TABLE; Schema: protostellar; Owner: postgres
--

CREATE TABLE IF NOT EXISTS protostellar."Country" (
    "Code" character varying NOT NULL,
    "Desc" character varying NOT NULL,
    "Remarks" character varying,
    "InternationalPhoneCode" character varying,
    "FlagActive" boolean,
    "FlagDeleted" boolean,
    "ID" bigint NOT NULL,
    "SysInsertTS" timestamp without time zone,
    "SysInsertPrg" character varying(50),
    "SysInsertUserID" character varying(50),
    "SysUpdateTS" timestamp without time zone,
    "SysUpdatePrg" character varying(50),
    "SysUpdateUserID" character varying(50)
);


ALTER TABLE protostellar."Country" OWNER TO postgres;

--
-- TOC entry 217 (class 1259 OID 26855)
-- Name: CustomsDocumentsType; Type: TABLE; Schema: protostellar; Owner: postgres
--

CREATE TABLE IF NOT EXISTS protostellar."CustomsDocumentsType" (
    "ID" bigint NOT NULL,
    "Desc" character varying(250) NOT NULL,
    "SysInsertTS" timestamp without time zone,
    "SysInsertPrg" character varying(50),
    "SysInsertUserID" character varying(50),
    "SysUpdateTS" timestamp without time zone,
    "SysUpdatePrg" character varying(50),
    "SysUpdateUserID" character varying(50)
);


ALTER TABLE protostellar."CustomsDocumentsType" OWNER TO postgres;

--
-- TOC entry 218 (class 1259 OID 26858)
-- Name: CustomsDocumentsType_PartnerSystem; Type: TABLE; Schema: protostellar; Owner: postgres
--

CREATE TABLE IF NOT EXISTS protostellar."CustomsDocumentsType_PartnerSystem" (
    "CustomsDocumentsTypeID" bigint NOT NULL,
    "PartnerSystemID" bigint NOT NULL,
    "Code" character varying(20),
    "Desc" character varying(250)
);


ALTER TABLE protostellar."CustomsDocumentsType_PartnerSystem" OWNER TO postgres;

--
-- TOC entry 219 (class 1259 OID 26861)
-- Name: CustomsHandlingType; Type: TABLE; Schema: protostellar; Owner: postgres
--

CREATE TABLE IF NOT EXISTS protostellar."CustomsHandlingType" (
    "ID" bigint NOT NULL,
    "Code" character varying(20) NOT NULL,
    "Desc" character varying(2000) NOT NULL,
    "DescUC" character varying(20),
    "CodeUC" character varying(20),
    "SysInsertTS" timestamp without time zone,
    "SysInsertPrg" character varying(50),
    "SysInsertUserID" character varying(50),
    "SysUpdateTS" timestamp without time zone,
    "SysUpdatePrg" character varying(50),
    "SysUpdateUserID" character varying(50)
);


ALTER TABLE protostellar."CustomsHandlingType" OWNER TO postgres;

--
-- TOC entry 220 (class 1259 OID 26867)
-- Name: CustomsProcedure; Type: TABLE; Schema: protostellar; Owner: postgres
--

CREATE TABLE IF NOT EXISTS protostellar."CustomsProcedure" (
    "ID" bigint NOT NULL,
    "ShortDesc" character varying(100) NOT NULL,
    "Desc" character varying(2000) NOT NULL,
    "Remarks" character varying(2000),
    "FlagActive" boolean,
    "FlagDeleted" boolean,
    "SysInsertTS" timestamp without time zone,
    "SysInsertPrg" character varying(50),
    "SysInsertUserID" character varying(50),
    "SysUpdateTS" timestamp without time zone,
    "SysUpdatePrg" character varying(50),
    "SysUpdateUserID" character varying(50)
);


ALTER TABLE protostellar."CustomsProcedure" OWNER TO postgres;

--
-- TOC entry 221 (class 1259 OID 26873)
-- Name: CustomsTariff; Type: TABLE; Schema: protostellar; Owner: postgres
--

CREATE TABLE IF NOT EXISTS protostellar."CustomsTariff" (
    "ID" bigint NOT NULL,
    "TariffNumber" character varying(20) NOT NULL,
    "Desc" character varying(1000) NOT NULL,
    "SysInsertTS" timestamp without time zone,
    "SysInsertPrg" character varying(50),
    "SysInsertUserID" character varying(50),
    "SysUpdateTS" timestamp without time zone,
    "SysUpdatePrg" character varying(50),
    "SysUpdateUserID" character varying(50)
);


ALTER TABLE protostellar."CustomsTariff" OWNER TO postgres;

--
-- TOC entry 222 (class 1259 OID 26879)
-- Name: CustomsTradeAreaStatus; Type: TABLE; Schema: protostellar; Owner: postgres
--

CREATE TABLE IF NOT EXISTS protostellar."CustomsTradeAreaStatus" (
    "ID" bigint NOT NULL,
    "Desc" character varying(250) NOT NULL,
    "Code" character varying(4) NOT NULL
);


ALTER TABLE protostellar."CustomsTradeAreaStatus" OWNER TO postgres;

--
-- TOC entry 223 (class 1259 OID 26882)
-- Name: CustomsTradeAreaStatus_PartnerSystem; Type: TABLE; Schema: protostellar; Owner: postgres
--

CREATE TABLE IF NOT EXISTS protostellar."CustomsTradeAreaStatus_PartnerSystem" (
    "CustomsTradeAreaStatusID" bigint NOT NULL,
    "PartnerSystemID" bigint NOT NULL,
    "Code" character varying(20) NOT NULL
);


ALTER TABLE protostellar."CustomsTradeAreaStatus_PartnerSystem" OWNER TO postgres;

--
-- TOC entry 224 (class 1259 OID 26885)
-- Name: CustomsTradeAreaStatus_PartnerSystem_PartnerSystemID_seq; Type: SEQUENCE; Schema: protostellar; Owner: postgres
--

CREATE SEQUENCE protostellar."CustomsTradeAreaStatus_PartnerSystem_PartnerSystemID_seq"
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE protostellar."CustomsTradeAreaStatus_PartnerSystem_PartnerSystemID_seq" OWNER TO postgres;

--
-- TOC entry 2719 (class 0 OID 0)
-- Dependencies: 224
-- Name: CustomsTradeAreaStatus_PartnerSystem_PartnerSystemID_seq; Type: SEQUENCE OWNED BY; Schema: protostellar; Owner: postgres
--

ALTER SEQUENCE protostellar."CustomsTradeAreaStatus_PartnerSystem_PartnerSystemID_seq" OWNED BY protostellar."CustomsTradeAreaStatus_PartnerSystem"."PartnerSystemID";


--
-- TOC entry 225 (class 1259 OID 26887)
-- Name: DangerousGoods; Type: TABLE; Schema: protostellar; Owner: postgres
--

CREATE TABLE IF NOT EXISTS protostellar."DangerousGoods" (
    "Code" character varying(8),
    "NumberUN" bigint,
    "CodeIMDG" character varying(4),
    "Description" character varying(1000),
    "SeaWaterHazard" boolean,
    "MFAGTableNo1" bigint,
    "MFAGTableNo2" bigint,
    "PrimaryLabel" character varying(50),
    "SecondaryLabel1" character varying(50),
    "SecondaryLabel2" character varying(50),
    "EMSNo1" character varying(50),
    "EMSNo2" character varying(50),
    "PackagingGroupNo" character varying(50),
    "SeaWaterHazardClassNo" bigint,
    "HazardPageNo" bigint,
    "PackagingGroupCode" character varying(10),
    "ID" bigint NOT NULL,
    "SysInsertTS" timestamp without time zone,
    "SysInsertPrg" character varying(50),
    "SysInsertUserID" character varying(50),
    "SysUpdateTS" timestamp without time zone,
    "SysUpdatePrg" character varying(50),
    "SysUpdateUserID" character varying(50)
);


ALTER TABLE protostellar."DangerousGoods" OWNER TO postgres;

--
-- TOC entry 226 (class 1259 OID 26893)
-- Name: Goods; Type: TABLE; Schema: protostellar; Owner: postgres
--

CREATE TABLE IF NOT EXISTS protostellar."Goods" (
    "ID" bigint NOT NULL,
    "Desc" character varying(200) NOT NULL,
    "DescUC" character varying(200),
    "SysInsertTS" timestamp without time zone,
    "SysInsertPrg" character varying(50),
    "SysInsertUserID" character varying(50),
    "SysUpdateTS" timestamp without time zone,
    "SysUpdatePrg" character varying(50),
    "SysUpdateUserID" character varying(50),
    "CommodityNumber" character varying(50)
);


ALTER TABLE protostellar."Goods" OWNER TO postgres;

--
-- TOC entry 227 (class 1259 OID 26899)
-- Name: Item; Type: TABLE; Schema: protostellar; Owner: postgres
--

CREATE TABLE IF NOT EXISTS protostellar."Item" (
    "ID" bigint NOT NULL,
    "ContainerNumber" character varying(12),
    "1LInsertTS" timestamp without time zone NOT NULL,
    "1LUpdateTS" timestamp without time zone,
    "1LInsertPgm" character varying(250) NOT NULL,
    "1LUpdatePgm" character varying(250),
    "PackageNumber" character varying(50),
    "ContainerSizeID" bigint,
    "SysInsertTS" timestamp without time zone,
    "SysInsertPrg" character varying(50),
    "SysInsertUserID" character varying(50),
    "SysUpdateTS" timestamp without time zone,
    "SysUpdatePrg" character varying(50),
    "SysUpdateUserID" character varying(50)
);


ALTER TABLE protostellar."Item" OWNER TO postgres;

--
-- TOC entry 228 (class 1259 OID 26905)
-- Name: LoadingRequest; Type: TABLE; Schema: protostellar; Owner: postgres
--

CREATE TABLE IF NOT EXISTS protostellar."LoadingRequest" (
    "ID" bigint NOT NULL,
    "Code" character varying(20) NOT NULL,
    "Desc" character varying(250) NOT NULL,
    "SysInsertTS" timestamp without time zone,
    "SysInsertPrg" character varying(50),
    "SysInsertUserID" character varying(50),
    "SysUpdateTS" timestamp without time zone,
    "SysUpdatePrg" character varying(50),
    "SysUpdateUserID" character varying(50)
);


ALTER TABLE protostellar."LoadingRequest" OWNER TO postgres;

--
-- TOC entry 229 (class 1259 OID 26908)
-- Name: LoadingRequest_PartnerSystem; Type: TABLE; Schema: protostellar; Owner: postgres
--

CREATE TABLE IF NOT EXISTS protostellar."LoadingRequest_PartnerSystem" (
    "LoadingRequestID" bigint NOT NULL,
    "PartnerSystemID" bigint NOT NULL,
    "Code" character varying(50) NOT NULL
);


ALTER TABLE protostellar."LoadingRequest_PartnerSystem" OWNER TO postgres;

--
-- TOC entry 230 (class 1259 OID 26911)
-- Name: Location; Type: TABLE; Schema: protostellar; Owner: postgres
--

CREATE TABLE IF NOT EXISTS protostellar."Location" (
    "ID" character varying(100) NOT NULL,
    "LocationType" character varying(30),
    "LocationFunctionUnknown" integer,
    "LocationFunctionPort" integer,
    "LocationFunctionRailTerminal" integer,
    "LocationFunctionRoadTerminal" integer,
    "LocationFunctionAirport" integer,
    "LocationFunctionPostalExchangeOffice" integer,
    "LocationFunctionMultimodal" integer,
    "LocationFunctionFixedPlatform" integer,
    "LocationFunctionBorderCrossing" integer,
    "CountryCode" character varying(5),
    "LocationName" character varying(250),
    "LocationNameShort" character varying(100),
    "LocationNameLong" character varying(2000),
    "LocationCity" character varying(250),
    "LocationZIP" character varying(30),
    "LocationStreet" character varying(250),
    "LocationStreetNo" character varying(30),
    "RailStationCode" character varying(20),
    "PortCode" character varying(5),
    "PortAreaCode" character varying(5),
    "PortQuayCode" character varying(5),
    "PortLocationCode" character varying(5),
    "AirportCode" character varying(5),
    "KLVIndicator" character(1),
    "GeoGPSNorth" character varying(20),
    "GeoGPSEast" character varying(20),
    "IATA" character varying(20),
    "UNLOCFunctionCode" character varying(20),
    "UNLOCDate" character varying(20),
    "UNLOCStatus" character varying(10),
    "InterfacePartnerSystemID" bigint,
    "CountryID" bigint,
    "SysInsertTS" timestamp without time zone,
    "SysInsertPrg" character varying(50),
    "SysInsertUserID" character varying(50),
    "SysUpdateTS" timestamp without time zone,
    "SysUpdatePrg" character varying(50),
    "SysUpdateUserID" character varying(50)
);


ALTER TABLE protostellar."Location" OWNER TO postgres;

--
-- TOC entry 231 (class 1259 OID 26917)
-- Name: Location_Matchinglist; Type: TABLE; Schema: protostellar; Owner: postgres
--

CREATE TABLE IF NOT EXISTS protostellar."Location_Matchinglist" (
    "LocationName" character varying(250) NOT NULL,
    "LocationID" character varying(100) NOT NULL
);


ALTER TABLE protostellar."Location_Matchinglist" OWNER TO postgres;

--
-- TOC entry 232 (class 1259 OID 26920)
-- Name: Order; Type: TABLE; Schema: protostellar; Owner: postgres
--

CREATE TABLE IF NOT EXISTS protostellar."Order" (
    "ID" bigint NOT NULL,
    "StartLocationID" character varying(100) NOT NULL,
    "DestinationLocationID" character varying(100) NOT NULL,
    "CustomerAddressID" bigint NOT NULL,
    "1LEMailMessageID" character varying(500),
    "1LErrorMessage" character varying,
    "1LInsertPrg" character varying(250) NOT NULL,
    "1LInsertTS" timestamp without time zone NOT NULL,
    "1LRecordVersion" bigint NOT NULL,
    "1LStatusCodeID" integer NOT NULL,
    "1LUpdatePrg" character varying(250),
    "1LUpdateRequiredFlag" boolean,
    "1LUpdateTS" timestamp without time zone,
    "ExchangeTestMessageFlag" boolean,
    "OrderDateTime" timestamp without time zone NOT NULL,
    "OrderNumberCustomer" character varying(100),
    "OrderNumberPS" character varying(20) NOT NULL,
    "Remarks" character varying(2000)
);


ALTER TABLE protostellar."Order" OWNER TO postgres;

--
-- TOC entry 2720 (class 0 OID 0)
-- Dependencies: 232
-- Name: TABLE "Order"; Type: COMMENT; Schema: protostellar; Owner: postgres
--

COMMENT ON TABLE protostellar."Order" IS 'future use!';


--
-- TOC entry 233 (class 1259 OID 26926)
-- Name: OrderItem; Type: TABLE; Schema: protostellar; Owner: postgres
--

CREATE TABLE IF NOT EXISTS protostellar."OrderItem" (
    "ID" bigint NOT NULL,
    "1LErrorMessage" character varying(2000),
    "1LInsertPrg" character varying(250) NOT NULL,
    "1LInsertTS" timestamp without time zone NOT NULL,
    "1LRecordVersion" bigint NOT NULL,
    "1LStatusCodeID" bigint NOT NULL,
    "1LUpdatePrg" character varying(250),
    "1LUpdateRequiredFlag" boolean NOT NULL,
    "1LUpdateTS" timestamp without time zone,
    "ConsigneeAddressID" bigint NOT NULL,
    "ConsignorAddressID" bigint NOT NULL,
    "ContainerNumber" character varying(12),
    "ContainerSizeID" bigint NOT NULL,
    "OrderID" bigint NOT NULL,
    "Remarks" character varying(2000),
    "SupplierAddressID" bigint,
    "ItemID" bigint NOT NULL
);


ALTER TABLE protostellar."OrderItem" OWNER TO postgres;

--
-- TOC entry 234 (class 1259 OID 26932)
-- Name: OrderItemDangerousGoods; Type: TABLE; Schema: protostellar; Owner: postgres
--

CREATE TABLE IF NOT EXISTS protostellar."OrderItemDangerousGoods" (
    "OrderItemID" bigint NOT NULL,
    "DangerousGoodsID" bigint NOT NULL,
    "1LInsertPrg" character varying(250),
    "1LInsertTS" timestamp without time zone,
    "1LRecordVersion" integer,
    "1LUpdatePrg" character varying(250),
    "1LUpdateTS" timestamp without time zone,
    "1LInsertUser" character varying(250),
    "1LUpdateUser" character varying(250)
);


ALTER TABLE protostellar."OrderItemDangerousGoods" OWNER TO postgres;

--
-- TOC entry 235 (class 1259 OID 26938)
-- Name: OrderItemGoods; Type: TABLE; Schema: protostellar; Owner: postgres
--

CREATE TABLE IF NOT EXISTS protostellar."OrderItemGoods" (
    "OrderItemID" bigint NOT NULL,
    "1LErrorMessage" character varying(2000),
    "1LInsertPrg" character varying(250) NOT NULL,
    "1LInsertTS" timestamp without time zone NOT NULL,
    "1LRecordVersion" bigint NOT NULL,
    "1LUpdatePrg" character varying(250),
    "1LUpdateRequiredFlag" boolean NOT NULL,
    "1LUpdateTS" timestamp without time zone,
    "GoodsID" bigint NOT NULL,
    "Remarks" character varying(2000)
);


ALTER TABLE protostellar."OrderItemGoods" OWNER TO postgres;

--
-- TOC entry 2721 (class 0 OID 0)
-- Dependencies: 235
-- Name: TABLE "OrderItemGoods"; Type: COMMENT; Schema: protostellar; Owner: postgres
--

COMMENT ON TABLE protostellar."OrderItemGoods" IS 'future use!';


--
-- TOC entry 236 (class 1259 OID 26944)
-- Name: OrderItemSeals; Type: TABLE; Schema: protostellar; Owner: postgres
--

CREATE TABLE IF NOT EXISTS protostellar."OrderItemSeals" (
    "OrderItemID" bigint NOT NULL,
    "SealLocationID" bigint NOT NULL,
    "SealTypeID" bigint NOT NULL,
    "SeqNo" integer NOT NULL,
    "SealNumber" character varying(30) NOT NULL,
    "1LErrorMessage" character varying(2000),
    "1LInsertPrg" character varying(250) NOT NULL,
    "1LInsertTS" timestamp without time zone NOT NULL,
    "1LRecordVersion" bigint NOT NULL,
    "1LUpdatePrg" character varying(250),
    "1LUpdateRequiredFlag" boolean NOT NULL,
    "1LUpdateTS" timestamp without time zone,
    "Remarks" character varying(2000)
);


ALTER TABLE protostellar."OrderItemSeals" OWNER TO postgres;

--
-- TOC entry 237 (class 1259 OID 26950)
-- Name: OrderItemVoyageSegment; Type: TABLE; Schema: protostellar; Owner: postgres
--

CREATE TABLE IF NOT EXISTS protostellar."OrderItemVoyageSegment" (
    "ID" bigint NOT NULL,
    "CommunicationCreationTime" timestamp without time zone,
    "MessageReferenceNumber" character varying,
    "1LReferenceCode" character varying(100),
    "BusinessDocumentIssueDate" timestamp without time zone,
    "WagonNumber" character varying,
    "Remarks" character varying,
    "1LEMailMessageID" character varying(500),
    "1LInsertTS" timestamp without time zone NOT NULL,
    "1LUpdateTS" timestamp without time zone,
    "EMailInfileSheetRowNr" bigint,
    "1LStatusCodeID" bigint NOT NULL,
    "1LRecordVersion" bigint NOT NULL,
    "1LUpdateRequired_Flag" boolean NOT NULL,
    "InboundPartnerSystemResponseGeneralCode" character varying(50),
    "InboundPartnerSystemResponseCode" character varying(50),
    "InboundPartnerSystemResponseText" character varying(2000),
    "InboundPartnerSystemResponseReference" character varying(250),
    "InboundPartnerSystemResponseSourceFilename" character varying(2000),
    "InboundPartnerSystemStatusQualifier" character varying(50),
    "InboundPartnerSystemStatusCode" character varying(50),
    "InboundPartnerSystemStatusText" character varying(250),
    "InboundPartnerSystemStatusReference" character varying(50),
    "1LUpdatePrg" character varying,
    "InboundPartnerSystemTransportOrderNumber" character varying(100),
    "LoadingRequestRemarks" character varying(250),
    "ContainerHandlingStoppedFlag" boolean,
    "LoadingRequestID" bigint,
    "ShippingCompanyAddressID" bigint,
    "CustomsProcedureID" bigint,
    "CustomsHandlingTypeID" bigint,
    "ForwardingAgentAddressID" bigint,
    "OutboundPartnerSystem1LStatusCode" bigint,
    "InboundPartnerSystem1LStatusCode" bigint,
    "OutboundPartnerSystemStatusText" character varying(250),
    "OutboundPartnerSystemStatusReference" character varying(50),
    "OutboundPartnerSystemStatusCode" character varying(50),
    "OutboundPartnerSystemResponseText" character varying(2000),
    "OutboundPartnerSystemResponseSourceFilename" character varying(2000),
    "OutboundPartnerSystemResponseGeneralCode" character varying(50),
    "OutboundPartnerSystemResponseCode" character varying(50),
    "OutboundPartnerSystemResponseReference" character varying(250),
    "OutboundPartnerSystemStatusQualifier" character varying(50),
    "OutboundPartnerSystemTransportOrderNumber" character varying(100),
    "1LErrorMessage" character varying(2000),
    "1LInsertPrg" character varying(250) NOT NULL,
    "OceanCarrierBookingNo" character varying(50),
    "OceanCarrierPickupReference" character varying(50),
    "OrderID" bigint NOT NULL,
    "OrderItemID" bigint NOT NULL,
    "OrderVoyageSegmentID" bigint NOT NULL,
    "ShipmentNumber" character varying(50),
    "Code" character varying(100),
    "ContainerReleasePIN" character varying(50),
    "ContainerReleasePassword" character varying(250),
    "ContainerReleaseStatusFlag" boolean,
    "WagonNumberAutoAssigned" character varying(20),
    "InboundPartnerSystemExchangeNumber" integer,
    "OutboundPartnerSystemExchangeNumber" integer,
    "DeliveryDateTime" timestamp without time zone,
    "PickupDateTime" timestamp without time zone,
    "ContainerFillingDegreeID" bigint,
    "NoOfPackages" integer,
    "WeightNet" numeric,
    "WeightGross" numeric,
    "WeightTare" numeric,
    "CustomsDocumentsATBNo" character varying(50),
    "CustomsDocumentsNo" character varying(50),
    "CustomsDocumentsTypeID" bigint,
    "CustomsTariffID" bigint,
    "OceanCarrierBLNo" character varying(50),
    "ContainerRefrigerationFlag" boolean,
    "PackageCodeID" bigint,
    "CustomsTradeAreaStatusID" bigint NOT NULL,
    "WagonID" bigint
);


ALTER TABLE protostellar."OrderItemVoyageSegment" OWNER TO postgres;

--
-- TOC entry 2722 (class 0 OID 0)
-- Dependencies: 237
-- Name: COLUMN "OrderItemVoyageSegment"."WagonNumber"; Type: COMMENT; Schema: protostellar; Owner: postgres
--

COMMENT ON COLUMN protostellar."OrderItemVoyageSegment"."WagonNumber" IS 'wagon number for this item';


--
-- TOC entry 2723 (class 0 OID 0)
-- Dependencies: 237
-- Name: COLUMN "OrderItemVoyageSegment"."Remarks"; Type: COMMENT; Schema: protostellar; Owner: postgres
--

COMMENT ON COLUMN protostellar."OrderItemVoyageSegment"."Remarks" IS 'remarks to this VoyageOrderSegment';


--
-- TOC entry 2724 (class 0 OID 0)
-- Dependencies: 237
-- Name: COLUMN "OrderItemVoyageSegment"."1LInsertTS"; Type: COMMENT; Schema: protostellar; Owner: postgres
--

COMMENT ON COLUMN protostellar."OrderItemVoyageSegment"."1LInsertTS" IS 'insert timestamp';


--
-- TOC entry 2725 (class 0 OID 0)
-- Dependencies: 237
-- Name: COLUMN "OrderItemVoyageSegment"."1LUpdateTS"; Type: COMMENT; Schema: protostellar; Owner: postgres
--

COMMENT ON COLUMN protostellar."OrderItemVoyageSegment"."1LUpdateTS" IS 'last update timestamp';


--
-- TOC entry 2726 (class 0 OID 0)
-- Dependencies: 237
-- Name: COLUMN "OrderItemVoyageSegment"."1LUpdatePrg"; Type: COMMENT; Schema: protostellar; Owner: postgres
--

COMMENT ON COLUMN protostellar."OrderItemVoyageSegment"."1LUpdatePrg" IS 'last update program';


--
-- TOC entry 2727 (class 0 OID 0)
-- Dependencies: 237
-- Name: COLUMN "OrderItemVoyageSegment"."ShippingCompanyAddressID"; Type: COMMENT; Schema: protostellar; Owner: postgres
--

COMMENT ON COLUMN protostellar."OrderItemVoyageSegment"."ShippingCompanyAddressID" IS 'FK - shipping company';


--
-- TOC entry 238 (class 1259 OID 26956)
-- Name: OrderVoyageSegment; Type: TABLE; Schema: protostellar; Owner: postgres
--

CREATE TABLE IF NOT EXISTS protostellar."OrderVoyageSegment" (
    "OrderID" bigint NOT NULL,
    "ID" bigint NOT NULL,
    "StartLocationID" character varying(100) NOT NULL,
    "DestinationLocationID" character varying(100) NOT NULL,
    "VoyageTypeID" bigint NOT NULL,
    "1LErrorMessage" character varying(2000),
    "1LInsertPrg" character varying(250) NOT NULL,
    "1LInsertTS" timestamp without time zone NOT NULL,
    "1LRecordVersion" bigint NOT NULL,
    "1LStatusCodeID" integer NOT NULL,
    "1LUpdatePrg" character varying(250),
    "1LUpdateRequiredFlag" boolean NOT NULL,
    "1LUpdateTS" timestamp without time zone,
    "ArrivalDate" date,
    "ContainerEmptyPickupDepot" character varying(50),
    "ContainerEmptyPickupReference" character varying(50),
    "ContainerEmptyReturnDepot" character varying(50),
    "ContainerEmptyReturnDropoffReference" character varying(50),
    "DischargeDate" date,
    "DispatchDate" date,
    "FeederShipID" bigint,
    "KLVIndicator" character(1),
    "OceanCarrierAddressID" bigint,
    "OceanCarrierClosingForCargoDateTime" timestamp without time zone,
    "OceanVoyageETAFinal" timestamp without time zone,
    "OceanVoyageETAInitial" timestamp without time zone,
    "OceanVoyageETD" timestamp without time zone,
    "RailwayBillTypeCode" character varying(5),
    "RailwayClassID" bigint,
    "Remarks" character varying(2000),
    "ShipClearanceDateTime" timestamp without time zone,
    "ShipID" bigint,
    "ShippingCompanyReferenceNumber" character varying(50),
    "TrainDate" date,
    "TransportationMeansID" bigint NOT NULL,
    "TransportCompanyAddressID" bigint NOT NULL,
    "InboundPartnerSystemID" bigint,
    "OutboundPartnerSystemID" bigint,
    "DestinationPortInterfacePartnerSystemID" bigint,
    "DestinationPortLocationID" character varying(100),
    "DestinationRailstationInterfacePartnerSystemID" bigint,
    "DestinationRailstationLocationID" character varying(100),
    "SourcePortInterfacePartnerSystemID" bigint,
    "SourceRailstationInterfacePartnerSystemID" bigint,
    "SourceRailstationLocationID" character varying(100),
    "SourcePortLocationID" character varying(100),
    "TrainNumber" character varying(20),
    "ScheduledTrainID" bigint,
    "IsFinalFlag" boolean
);


ALTER TABLE protostellar."OrderVoyageSegment" OWNER TO postgres;

--
-- TOC entry 239 (class 1259 OID 26962)
-- Name: OrderVoyageSegmentTrain; Type: TABLE; Schema: protostellar; Owner: postgres
--

CREATE TABLE IF NOT EXISTS protostellar."OrderVoyageSegmentTrain" (
    "TrainID" bigint NOT NULL,
    "DestinationSlotNumber" character varying,
    "TrainDate" date NOT NULL,
    "ID" bigint NOT NULL,
    "Desc" character varying NOT NULL
);


ALTER TABLE protostellar."OrderVoyageSegmentTrain" OWNER TO postgres;

--
-- TOC entry 240 (class 1259 OID 26968)
-- Name: OrderVoyageSegmentTrainWagons; Type: TABLE; Schema: protostellar; Owner: postgres
--

CREATE TABLE IF NOT EXISTS protostellar."OrderVoyageSegmentTrainWagons" (
    "OrderVoyageSegmentTrainID" bigint NOT NULL,
    "WagonID" bigint NOT NULL,
    "WagonSeqNo" integer NOT NULL
);


ALTER TABLE protostellar."OrderVoyageSegmentTrainWagons" OWNER TO postgres;

--
-- TOC entry 241 (class 1259 OID 26971)
-- Name: PackageCode; Type: TABLE; Schema: protostellar; Owner: postgres
--

CREATE TABLE IF NOT EXISTS protostellar."PackageCode" (
    "ID" bigint NOT NULL,
    "Code" character varying(10) NOT NULL,
    "Desc" character varying(250) NOT NULL,
    "Remarks" character varying(2000),
    "FlagActive" boolean,
    "FlagDeleted" boolean,
    "1LInsertTS" timestamp without time zone,
    "1LInsertPrg" character varying(50),
    "1LInsertUserID" character varying(50),
    "1LUpdateTS" timestamp without time zone,
    "1LUpdatePrg" character varying(50),
    "1LUpdateUserID" character varying(50)
);


ALTER TABLE protostellar."PackageCode" OWNER TO postgres;

--
-- TOC entry 242 (class 1259 OID 26977)
-- Name: RailwayClass; Type: TABLE; Schema: protostellar; Owner: postgres
--

CREATE TABLE IF NOT EXISTS protostellar."RailwayClass" (
    "ID" bigint NOT NULL,
    "Code" character varying(20) NOT NULL,
    "Desc" character varying(200) NOT NULL,
    "1LInsertTS" timestamp without time zone,
    "1LInsertPrg" character varying(50),
    "1LInsertUserID" character varying(50),
    "1LUpdateTS" timestamp without time zone,
    "1LUpdatePrg" character varying(50),
    "1LUpdateUserID" character varying(50)
);


ALTER TABLE protostellar."RailwayClass" OWNER TO postgres;

--
-- TOC entry 243 (class 1259 OID 26980)
-- Name: SEQ_EmptyContainer; Type: SEQUENCE; Schema: protostellar; Owner: postgres
--

CREATE SEQUENCE protostellar."SEQ_EmptyContainer"
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE protostellar."SEQ_EmptyContainer" OWNER TO postgres;

--
-- TOC entry 244 (class 1259 OID 26982)
-- Name: SEQ_ExchangeNumber; Type: SEQUENCE; Schema: protostellar; Owner: postgres
--

CREATE SEQUENCE protostellar."SEQ_ExchangeNumber"
    START WITH 0
    INCREMENT BY 1
    MINVALUE 0
    NO MAXVALUE
    CACHE 50;


ALTER TABLE protostellar."SEQ_ExchangeNumber" OWNER TO postgres;

--
-- TOC entry 245 (class 1259 OID 26984)
-- Name: SEQ_ItemID; Type: SEQUENCE; Schema: protostellar; Owner: postgres
--

CREATE SEQUENCE protostellar."SEQ_ItemID"
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE protostellar."SEQ_ItemID" OWNER TO postgres;

--
-- TOC entry 246 (class 1259 OID 26986)
-- Name: SEQ_OrderID; Type: SEQUENCE; Schema: protostellar; Owner: postgres
--

CREATE SEQUENCE protostellar."SEQ_OrderID"
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE protostellar."SEQ_OrderID" OWNER TO postgres;

--
-- TOC entry 247 (class 1259 OID 26988)
-- Name: SEQ_OrderItemID; Type: SEQUENCE; Schema: protostellar; Owner: postgres
--

CREATE SEQUENCE protostellar."SEQ_OrderItemID"
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE protostellar."SEQ_OrderItemID" OWNER TO postgres;

--
-- TOC entry 248 (class 1259 OID 26990)
-- Name: SEQ_OrderItemVoyageSegmentID; Type: SEQUENCE; Schema: protostellar; Owner: postgres
--

CREATE SEQUENCE protostellar."SEQ_OrderItemVoyageSegmentID"
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE protostellar."SEQ_OrderItemVoyageSegmentID" OWNER TO postgres;

--
-- TOC entry 249 (class 1259 OID 26992)
-- Name: SEQ_OrderVoyageSegmentID; Type: SEQUENCE; Schema: protostellar; Owner: postgres
--

CREATE SEQUENCE protostellar."SEQ_OrderVoyageSegmentID"
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE protostellar."SEQ_OrderVoyageSegmentID" OWNER TO postgres;

--
-- TOC entry 250 (class 1259 OID 26994)
-- Name: SEQ_OrderVoyageSegmentTrainID; Type: SEQUENCE; Schema: protostellar; Owner: postgres
--

CREATE SEQUENCE protostellar."SEQ_OrderVoyageSegmentTrainID"
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE protostellar."SEQ_OrderVoyageSegmentTrainID" OWNER TO postgres;

--
-- TOC entry 251 (class 1259 OID 26996)
-- Name: SEQ_TrainID; Type: SEQUENCE; Schema: protostellar; Owner: postgres
--

CREATE SEQUENCE protostellar."SEQ_TrainID"
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE protostellar."SEQ_TrainID" OWNER TO postgres;

--
-- TOC entry 252 (class 1259 OID 26998)
-- Name: SEQ_WagonID; Type: SEQUENCE; Schema: protostellar; Owner: postgres
--

CREATE SEQUENCE protostellar."SEQ_WagonID"
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE protostellar."SEQ_WagonID" OWNER TO postgres;

--
-- TOC entry 253 (class 1259 OID 27000)
-- Name: SealLocation; Type: TABLE; Schema: protostellar; Owner: postgres
--

CREATE TABLE IF NOT EXISTS protostellar."SealLocation" (
    "ID" bigint NOT NULL,
    "Code" character varying(20),
    "Desc" character varying(200) NOT NULL,
    "1LInsertTS" timestamp without time zone,
    "1LInsertPrg" character varying(50),
    "1LInsertUserID" character varying(50),
    "1LUpdateTS" timestamp without time zone,
    "1LUpdatePrg" character varying(50),
    "1LUpdateUserID" character varying(50)
);


ALTER TABLE protostellar."SealLocation" OWNER TO postgres;

--
-- TOC entry 254 (class 1259 OID 27003)
-- Name: SealType; Type: TABLE; Schema: protostellar; Owner: postgres
--

CREATE TABLE IF NOT EXISTS protostellar."SealType" (
    "ID" bigint NOT NULL,
    "Code" character varying(20),
    "Desc" character varying(200) NOT NULL,
    "1LInsertTS" timestamp without time zone,
    "1LInsertPrg" character varying(50),
    "1LInsertUserID" character varying(50),
    "1LUpdateTS" timestamp without time zone,
    "1LUpdatePrg" character varying(50),
    "1LUpdateUserID" character varying(50)
);


ALTER TABLE protostellar."SealType" OWNER TO postgres;

--
-- TOC entry 255 (class 1259 OID 27006)
-- Name: Ship; Type: TABLE; Schema: protostellar; Owner: postgres
--

CREATE TABLE IF NOT EXISTS protostellar."Ship" (
    "ID" bigint NOT NULL,
    "Name" character varying(200) NOT NULL,
    "NameUC" character varying(100),
    "1LInsertTS" timestamp without time zone,
    "1LInsertPrg" character varying(50),
    "1LInsertUserID" character varying(50),
    "1LUpdateTS" timestamp without time zone,
    "1LUpdatePrg" character varying(50),
    "1LUpdateUserID" character varying(50),
    "IMONumber" character varying(20),
    "CallingCode" character varying(20)
);


ALTER TABLE protostellar."Ship" OWNER TO postgres;

--
-- TOC entry 256 (class 1259 OID 27012)
-- Name: StatisticsContainersPerMonth; Type: VIEW; Schema: protostellar; Owner: postgres
--

CREATE VIEW protostellar."StatisticsContainersPerMonth" AS
 SELECT DISTINCT to_char("OrderItem"."1LInsertTS", 'YYYY-MM'::text) AS "Month",
    count(*) AS count
   FROM protostellar."OrderItem"
  GROUP BY (to_char("OrderItem"."1LInsertTS", 'YYYY-MM'::text));


ALTER TABLE protostellar."StatisticsContainersPerMonth" OWNER TO postgres;

--
-- TOC entry 257 (class 1259 OID 27016)
-- Name: Train; Type: TABLE; Schema: protostellar; Owner: postgres
--

CREATE TABLE IF NOT EXISTS protostellar."Train" (
    "ID" bigint NOT NULL,
    "TrainNumber" character varying(10) NOT NULL,
    "Desc" character varying(1000) NOT NULL
);


ALTER TABLE protostellar."Train" OWNER TO postgres;

--
-- TOC entry 258 (class 1259 OID 27022)
-- Name: TrainAL; Type: TABLE; Schema: protostellar; Owner: postgres
--

CREATE TABLE IF NOT EXISTS protostellar."TrainAL" (
    "ID" character varying(50) NOT NULL,
    "TimeID" numeric(15,0),
    "Partner" character varying(20),
    "LocationID" character varying(100),
    "TrainNo" character varying(20),
    "RailwayClass" character varying(10),
    "TrainOperatorDesc" character varying(50),
    "RailstationName" character varying(50),
    "TerminalName" character varying(50),
    "Area" character varying(50),
    "Quay" character varying(50),
    "Location" character varying(50),
    "RailNo" character varying(50),
    "XLSXShortFilename" character varying(1000),
    "SYS_InsertTS" timestamp without time zone,
    "SYS_UpdateTS" timestamp without time zone,
    "SYS_UpdatePrg" character varying(250),
    "1LInsertTS" timestamp without time zone,
    "1LInsertPrg" character varying(50),
    "1LInsertUserID" character varying(50),
    "1LUpdateTS" timestamp without time zone,
    "1LUpdatePrg" character varying(50),
    "1LUpdateUserID" character varying(50)
);


ALTER TABLE protostellar."TrainAL" OWNER TO postgres;

--
-- TOC entry 259 (class 1259 OID 27028)
-- Name: TrainALConfigurations; Type: TABLE; Schema: protostellar; Owner: postgres
--

CREATE TABLE IF NOT EXISTS protostellar."TrainALConfigurations" (
    "TrainID" character varying(50) NOT NULL,
    "SeqNo" real,
    "WagonNo" text,
    "Position" character varying(2),
    "ContainerNo" text,
    "CombinationNo" bigint,
    "UseWagon" text,
    "WagonClass" text,
    "1LPositionOccupied" character(1),
    "1LLengthPattern" character varying(50),
    "1LPositionPattern" character varying(50),
    "1LLoadWeightPattern" character varying(50),
    "Remarks" text,
    "Bauart" text,
    "LgCtons" real,
    "MaxLoadLenFeet" real,
    "Stellbereich" text,
    "VmaxLoaded" real,
    "ContainerLength" integer,
    "MaxLoadTons" real,
    "WagonPosAvailable" text,
    "SYS_InsertTS" timestamp without time zone,
    "SYS_UpdateTS" timestamp without time zone,
    "SYS_UpdatePrg" character varying(250)
);


ALTER TABLE protostellar."TrainALConfigurations" OWNER TO postgres;

--
-- TOC entry 260 (class 1259 OID 27034)
-- Name: TrainETA; Type: TABLE; Schema: protostellar; Owner: postgres
--

CREATE TABLE IF NOT EXISTS protostellar."TrainETA" (
    "TrainNumber" character varying(20),
    "TrainDate" date NOT NULL,
    "ETA" timestamp without time zone,
    "LocationID" character varying(100) NOT NULL,
    "LocationNameUIC" character varying(45),
    "OrderVoyageSegmentTrainID" bigint NOT NULL,
    "1LInsertTS" timestamp without time zone NOT NULL,
    "1LInsertPrg" character varying(250) NOT NULL,
    "1LUpdateTS" timestamp without time zone,
    "1LUpdatePrg" character varying(250)
);


ALTER TABLE protostellar."TrainETA" OWNER TO postgres;

--
-- TOC entry 261 (class 1259 OID 27040)
-- Name: TransportDirection; Type: TABLE; Schema: protostellar; Owner: postgres
--

CREATE TABLE IF NOT EXISTS protostellar."TransportDirection" (
    "ID" bigint NOT NULL,
    "TransportDirectionDesc" character varying(100) NOT NULL,
    "Remarks" character varying(2000),
    "FlagActive" boolean,
    "FlagDeleted" boolean,
    "1LInsertTS" timestamp without time zone,
    "1LInsertPrg" character varying(50),
    "1LInsertUserID" character varying(50),
    "1LUpdateTS" timestamp without time zone,
    "1LUpdatePrg" character varying(50),
    "1LUpdateUserID" character varying(50)
);


ALTER TABLE protostellar."TransportDirection" OWNER TO postgres;

--
-- TOC entry 262 (class 1259 OID 27046)
-- Name: TransportDirection_PartnerSystem; Type: TABLE; Schema: protostellar; Owner: postgres
--

CREATE TABLE IF NOT EXISTS protostellar."TransportDirection_PartnerSystem" (
    "TransportDirectionID" bigint NOT NULL,
    "PartnerSystemID" bigint NOT NULL,
    "Code" character varying(20) NOT NULL
);


ALTER TABLE protostellar."TransportDirection_PartnerSystem" OWNER TO postgres;

--
-- TOC entry 263 (class 1259 OID 27049)
-- Name: TransportationMeans; Type: TABLE; Schema: protostellar; Owner: postgres
--

CREATE TABLE IF NOT EXISTS protostellar."TransportationMeans" (
    "ID" bigint NOT NULL,
    "Desc" character varying(100) NOT NULL,
    "IsSea" boolean,
    "IsRiver" boolean,
    "IsLand" boolean,
    "IsRoad" boolean,
    "IsRail" boolean,
    "IsAir" boolean,
    "TransportModeDesc" character varying(50),
    "1LInsertTS" timestamp without time zone,
    "1LInsertPrg" character varying(50),
    "1LInsertUserID" character varying(50),
    "1LUpdateTS" timestamp without time zone,
    "1LUpdatePrg" character varying(50),
    "1LUpdateUserID" character varying(50)
);


ALTER TABLE protostellar."TransportationMeans" OWNER TO postgres;

--
-- TOC entry 264 (class 1259 OID 27052)
-- Name: VoyageOrderSegmentRejects; Type: TABLE; Schema: protostellar; Owner: postgres
--

CREATE TABLE IF NOT EXISTS protostellar."VoyageOrderSegmentRejects" (
    "ID" text,
    "Message-ID" text,
    "InfileSheetRowNr" text,
    "Remarks" text,
    "ContainerNumber" text,
    "ContainerTEU" text,
    "ContainerOceanCarrierBLNo" text,
    "WagonNumber" text,
    "ContainerEmptyReturnDropoffReference" text,
    "ContainerOceanVoyageETAFinal" text,
    "ContainerOceanVoyageETAInitial" text,
    "ContainerOceanVoyageETD" text,
    "ContainerEmptyReturnDepot" text,
    "DeliveryDate" text,
    "DeliveryTime" text,
    "ContainerContent" text,
    "ContainerOceanVoyageSourcePortCode" text,
    "ContainerOceanVoyageDestinationPortName" text,
    "ContainerOceanVoyageDestinationPortTerminal" text,
    "ContainerOceanVoyageFeederShipName" text,
    "ShippingUnitWeightGross" text,
    "ShippingUnitWeightNet" text,
    "SupplierName" text,
    "ShippingUnitNoOfPackages" text,
    "ContainerOceanVoyageShipClearanceDateTime" text,
    "ContainerTypeCode" text,
    "ContainerOceanVoyageSourcePortName" text,
    "ShippingDirectorName" text,
    "ContainerNO" text,
    "Attached files count" bigint,
    "BLNo" text,
    "BoxesCount" text,
    "ContainerClearanceDateTime" text,
    "ContainerSize" text,
    "DeliveryIn" text,
    "DropoffReference" text,
    "ETAFinalArrivalDate" text,
    "ETAInitialArrivalDate" text,
    "ETDDeliveryDate" text,
    "EmptyContainerDestination" text,
    "FilenumberFound" text,
    "ForwardingAgent" text,
    "GoodsDesc" text,
    "InfileExtension" text,
    "InfileFullFilename" text,
    "InfileIsHidden" text,
    "InfileLastModification" text,
    "InfilePath" text,
    "InfileRootURI" text,
    "InfileRowNrWritten" text,
    "InfileSheetname" text,
    "InfileShortFilename" text,
    "InfileSize" text,
    "InfileURI" text,
    "InvoiceNo" text,
    "Date_field" text,
    "From_field" text,
    "To_field" text,
    "SYS_InsertTS" timestamp without time zone,
    "SYS_UpdateTS" timestamp without time zone,
    "MailFile_MessageID" text,
    "ContainerNumber_RC" text,
    "ContainerNumber_RCText" text,
    "OrderID" text,
    "OrderID_RC" text,
    "OrderID_RCText" text,
    "RC" text,
    "RC_Message" text,
    "PUReference" text,
    "PlannedArrivalGiengenKwh" text,
    "PlannedLoadingPort" text,
    "ShipDestinationPort" text,
    "ShipDestinationPortTerminal" text,
    "ShipName" text,
    "ShipSourceCountry" text,
    "ShipSourcePort" text,
    "ShipperVerlader" text,
    "ShippingCompany" text,
    "SupplierDesc" text,
    "SysinfoHostIP" text,
    "SysinfoHostMAC" text,
    "SysinfoHostname" text,
    "SysinfoSystemDate" text,
    "SysinfoTransformationName" text,
    "WagonNo" text,
    "WeightGross" text,
    "WeightNet" text,
    "RejectedCount" text,
    "RejectedRCSum" text,
    "SysStatusCode" bigint,
    "ContainerOceanVoyageShipName" text
);


ALTER TABLE protostellar."VoyageOrderSegmentRejects" OWNER TO postgres;

--
-- TOC entry 265 (class 1259 OID 27058)
-- Name: VoyageOrderSegment_LoadFromMail; Type: TABLE; Schema: protostellar; Owner: postgres
--

CREATE TABLE IF NOT EXISTS protostellar."VoyageOrderSegment_LoadFromMail" (
    "ContainerNO" text,
    "ContainerSize" text,
    "ETDDeliveryDate" text,
    "ETAInitialArrivalDate" text,
    "ETAFinalArrivalDate" text,
    "ContainerClearanceDateTime" text,
    "ShipName" text,
    "ShipSourcePort" text,
    "ShipSourceCountry" text,
    "ShipDestinationPort" text,
    "ShipDestinationPortTerminal" text,
    "PUReference" text,
    "ForwardingAgent" text,
    "ShippingCompany" text,
    "BLNo" text,
    "WeightNet" text,
    "WeightGross" text,
    "GoodsDesc" text,
    "SupplierDesc" text,
    "DeliveryIn" text,
    "EmptyContainerDestination" text,
    "DropoffReference" text,
    "DeliveryDate" text,
    "DeliveryTime" text,
    "Remarks" text,
    "WagonNo" text,
    "BoxesCount" text,
    "InvoiceNo" text,
    "InfileFullFilename" text,
    "InfileSheetname" text,
    "InfileSheetRowNr" bigint,
    "InfileRowNrWritten" bigint,
    "InfileShortFilename" text,
    "InfileExtension" text,
    "InfilePath" text,
    "InfileSize" integer,
    "InfileIsHidden" boolean,
    "InfileLastModification" timestamp without time zone,
    "InfileURI" text,
    "InfileRootURI" text,
    "SysinfoSystemDate" timestamp without time zone,
    "SysinfoTransformationName" text,
    "SysinfoHostname" text,
    "SysinfoHostIP" text,
    "SysinfoHostMAC" text,
    "Attached files count" bigint,
    "MailFile_MessageID" text,
    "FilenumberFound" bigint,
    "ScheduledTrainNumber" text,
    "ContainerOceanCarrierBookingNo" text,
    "ContainerOceanVoyageSourcePortTerminal" text,
    "DispatchRailStationName" text,
    "DischargeRailStationName" text,
    "Voyage-Type" character varying(50),
    "ContainerEmptyPickupReference" character varying(50),
    "DangerousGoodsFlag" boolean,
    "TransportCompany" text,
    "CustomsHandlingTypeCode" text,
    "CustomsRelevantGoods" character(1),
    "CustomsDocumentNumber" text,
    "ScheduledTrainDate" date,
    "ScheduledTrainSlotNumber" character varying(20),
    "WagonSequenceNumber" integer,
    "PackageCode" text,
    "CustomerAddressID" double precision
);


ALTER TABLE protostellar."VoyageOrderSegment_LoadFromMail" OWNER TO postgres;

--
-- TOC entry 266 (class 1259 OID 27064)
-- Name: VoyageOrderSegment_LoadHistory; Type: TABLE; Schema: protostellar; Owner: postgres
--

CREATE TABLE IF NOT EXISTS protostellar."VoyageOrderSegment_LoadHistory" (
    "ContainerNO" text,
    "ContainerSize" text,
    "ETDDeliveryDate" text,
    "ETAInitialArrivalDate" text,
    "ETAFinalArrivalDate" text,
    "ContainerClearanceDateTime" text,
    "ShipName" text,
    "ShipSourcePort" text,
    "ShipSourceCountry" text,
    "ShipDestinationPort" text,
    "ShipDestinationPortTerminal" text,
    "PUReference" text,
    "ForwardingAgent" text,
    "ShippingCompany" text,
    "BLNo" text,
    "WeightNet" text,
    "WeightGross" text,
    "GoodsDesc" text,
    "SupplierDesc" text,
    "DeliveryIn" text,
    "EmptyContainerDestination" text,
    "DropoffReference" text,
    "DeliveryDate" text,
    "DeliveryTime" text,
    "Remarks" text,
    "WagonNo" text,
    "BoxesCount" text,
    "InvoiceNo" text,
    "InfileFullFilename" text,
    "InfileSheetname" text,
    "InfileSheetRowNr" bigint,
    "InfileRowNrWritten" bigint,
    "InfileShortFilename" text,
    "InfileExtension" text,
    "InfilePath" text,
    "InfileSize" integer,
    "InfileIsHidden" boolean,
    "InfileLastModification" timestamp without time zone,
    "InfileURI" text,
    "InfileRootURI" text,
    "SysinfoSystemDate" timestamp without time zone,
    "SysinfoTransformationName" text,
    "SysinfoHostname" text,
    "SysinfoHostIP" text,
    "SysinfoHostMAC" text,
    "Attached files count" bigint,
    "MailFile_MessageID" text,
    "FilenumberFound" bigint,
    "ScheduledTrainNumber" text,
    "ContainerOceanCarrierBookingNo" text,
    "ContainerOceanVoyageSourcePortTerminal" text,
    "DispatchRailStationName" text,
    "DischargeRailStationName" text,
    "Voyage-Type" character varying(50),
    "ContainerEmptyPickupReference" character varying(50),
    "DangerousGoodsFlag" boolean,
    "TransportCompany" text,
    "CustomsHandlingTypeCode" text,
    "CustomsRelevantGoods" character(1),
    "CustomsDocumentNumber" text
);


ALTER TABLE protostellar."VoyageOrderSegment_LoadHistory" OWNER TO postgres;

--
-- TOC entry 267 (class 1259 OID 27070)
-- Name: VoyageOrder_Hist; Type: TABLE; Schema: protostellar; Owner: postgres
--

CREATE TABLE IF NOT EXISTS protostellar."VoyageOrder_Hist" (
    "ID" bigint NOT NULL
);


ALTER TABLE protostellar."VoyageOrder_Hist" OWNER TO postgres;

--
-- TOC entry 268 (class 1259 OID 27073)
-- Name: VoyageType; Type: TABLE; Schema: protostellar; Owner: postgres
--

CREATE TABLE IF NOT EXISTS protostellar."VoyageType" (
    "ID" bigint NOT NULL,
    "Desc" character varying(100) NOT NULL,
    "TransportationMeansFromID" bigint NOT NULL,
    "TransportationMeansToID" bigint NOT NULL,
    "TransportModeCode" character varying(30),
    "1LInsertTS" timestamp without time zone,
    "1LInsertPrg" character varying(50),
    "1LInsertUserID" character varying(50),
    "1LUpdateTS" timestamp without time zone,
    "1LUpdatePrg" character varying(50),
    "1LUpdateUserID" character varying(50)
);


ALTER TABLE protostellar."VoyageType" OWNER TO postgres;

--
-- TOC entry 269 (class 1259 OID 27076)
-- Name: Wagon; Type: TABLE; Schema: protostellar; Owner: postgres
--

CREATE TABLE IF NOT EXISTS protostellar."Wagon" (
    "SegmentID" character varying(20),
    "SegmentVersion" character varying(20),
    "AxleCount" integer NOT NULL,
    "WeightNetKG" numeric NOT NULL,
    "LengthMM" numeric NOT NULL,
    "VMaxKpH" numeric NOT NULL,
    "MaxLoadKG" numeric NOT NULL,
    "HighCubeFlag" character(1),
    "CanHold45FeetFlag" character(1),
    "CompoundBreakFlag" character(1),
    "LoadingAreaTarget" character varying(20),
    "ClassUC" character varying(20),
    "SegmentIDUC" character varying(20),
    "SYS_InsertTS" timestamp without time zone,
    "SYS_UpdateTS" timestamp without time zone,
    "SYS_UpdatePrg" character varying(250),
    "WagonNo" character varying(20) NOT NULL,
    "DesignNo" character varying(10) NOT NULL,
    "Class" character varying(20) NOT NULL,
    "1LInsertTS" timestamp without time zone,
    "1LInsertPrg" character varying(50),
    "1LInsertUserID" character varying(50),
    "1LUpdateTS" timestamp without time zone,
    "1LUpdatePrg" character varying(50),
    "1LUpdateUserID" character varying(50),
    "ID" bigint NOT NULL
);


ALTER TABLE protostellar."Wagon" OWNER TO postgres;

--
-- TOC entry 270 (class 1259 OID 27082)
-- Name: WagonClass; Type: TABLE; Schema: protostellar; Owner: postgres
--

CREATE TABLE IF NOT EXISTS protostellar."WagonClass" (
    "WagonType" character varying(10) NOT NULL,
    "CombinationNo" bigint NOT NULL,
    "RailwayClass" character varying(5),
    "ContainerLength" integer NOT NULL,
    "Position" character(2) NOT NULL,
    "MaxLoadTons" numeric,
    "TechFilename" character varying(2000),
    "TechSheetname" character varying(250),
    "TechSheetRowNr" bigint,
    "TechRowNum" bigint,
    "TechShortFilename" character varying(1000),
    "SYS_InsertTS" timestamp without time zone,
    "SYS_UpdateTS" timestamp without time zone,
    "SYS_UpdatePrg" character varying(250),
    "1LInsertTS" timestamp without time zone,
    "1LInsertPrg" character varying(50),
    "1LInsertUserID" character varying(50),
    "1LUpdateTS" timestamp without time zone,
    "1LUpdatePrg" character varying(50),
    "1LUpdateUserID" character varying(50)
);


ALTER TABLE protostellar."WagonClass" OWNER TO postgres;

--
-- TOC entry 271 (class 1259 OID 27088)
-- Name: WagonClassLoadingCombinations; Type: TABLE; Schema: protostellar; Owner: postgres
--

CREATE TABLE IF NOT EXISTS protostellar."WagonClassLoadingCombinations" (
    "WagonClass" character varying(30) NOT NULL,
    "CombinationNo" integer NOT NULL,
    "RailwayClass" character varying(10),
    "ContainerLength" integer NOT NULL,
    "Position" character varying(10) NOT NULL,
    "MaxLoadTons" double precision NOT NULL,
    "TechFilename" character varying(2000),
    "TechSheetname" character varying(250),
    "TechSheetRowNr" bigint,
    "TechRowNum" bigint,
    "TechShortFilename" character varying(250),
    "SYS_InsertTS" timestamp without time zone,
    "SYS_UpdateTS" timestamp without time zone,
    "SYS_UpdatePrg" character varying(250)
);


ALTER TABLE protostellar."WagonClassLoadingCombinations" OWNER TO postgres;

--
-- TOC entry 272 (class 1259 OID 27094)
-- Name: new_view; Type: VIEW; Schema: protostellar; Owner: postgres
--

CREATE VIEW protostellar.new_view AS
 SELECT "Wagon"."SegmentID",
    "Wagon"."SegmentVersion",
    "Wagon"."AxleCount",
    "Wagon"."WeightNetKG",
    "Wagon"."LengthMM",
    "Wagon"."VMaxKpH",
    "Wagon"."MaxLoadKG",
    "Wagon"."HighCubeFlag",
    "Wagon"."CanHold45FeetFlag",
    "Wagon"."CompoundBreakFlag",
    "Wagon"."LoadingAreaTarget",
    "Wagon"."ClassUC",
    "Wagon"."SegmentIDUC",
    "Wagon"."SYS_InsertTS",
    "Wagon"."SYS_UpdateTS",
    "Wagon"."SYS_UpdatePrg",
    "Wagon"."WagonNo",
    "Wagon"."DesignNo",
    "Wagon"."Class"
   FROM protostellar."Wagon";


ALTER TABLE protostellar.new_view OWNER TO postgres;

--
-- TOC entry 2373 (class 2604 OID 27098)
-- Name: CustomsTradeAreaStatus_PartnerSystem PartnerSystemID; Type: DEFAULT; Schema: protostellar; Owner: postgres
--

ALTER TABLE ONLY protostellar."CustomsTradeAreaStatus_PartnerSystem" ALTER COLUMN "PartnerSystemID" SET DEFAULT nextval('protostellar."CustomsTradeAreaStatus_PartnerSystem_PartnerSystemID_seq"'::regclass);


--
-- TOC entry 2375 (class 2606 OID 27110)
-- Name: 1LEMailAccounts 1LEMailAccounts_pk; Type: CONSTRAINT; Schema: protostellar; Owner: postgres
--

ALTER TABLE ONLY protostellar."1LEMailAccounts"
    ADD CONSTRAINT "1LEMailAccounts_pk" PRIMARY KEY ("EMailAdress");


--
-- TOC entry 2378 (class 2606 OID 27112)
-- Name: 1LEMailReceiveAllDetails 1LEMailReceiveAllDetails_pk; Type: CONSTRAINT; Schema: protostellar; Owner: postgres
--

ALTER TABLE ONLY protostellar."1LEMailReceiveAllDetails"
    ADD CONSTRAINT "1LEMailReceiveAllDetails_pk" PRIMARY KEY ("Message-ID");


--
-- TOC entry 2381 (class 2606 OID 27114)
-- Name: 1LOrderMaintFilesProcessed 1LOrderMaintFilesProcessed_pk; Type: CONSTRAINT; Schema: protostellar; Owner: postgres
--

ALTER TABLE ONLY protostellar."1LOrderMaintFilesProcessed"
    ADD CONSTRAINT "1LOrderMaintFilesProcessed_pk" PRIMARY KEY ("ShortFilename");


--
-- TOC entry 2384 (class 2606 OID 27116)
-- Name: 1LPartnerSystem 1LPartnerSystem_pk; Type: CONSTRAINT; Schema: protostellar; Owner: postgres
--

ALTER TABLE ONLY protostellar."1LPartnerSystem"
    ADD CONSTRAINT "1LPartnerSystem_pk" PRIMARY KEY ("ID");


--
-- TOC entry 2395 (class 2606 OID 27118)
-- Name: 1LStatusMapping 1LStatusMapping_pk; Type: CONSTRAINT; Schema: protostellar; Owner: postgres
--

ALTER TABLE ONLY protostellar."1LStatusMapping"
    ADD CONSTRAINT "1LStatusMapping_pk" PRIMARY KEY ("1LStatusCodeID", "PartnerSystemID", "PartnerTransportDirectionID", "1LStatusObjectID", "PartnerStatusCode", "PartnerReplyQualifier", "1LStatusGroup");


--
-- TOC entry 2397 (class 2606 OID 27120)
-- Name: 1LStatusObject 1LStatusObject_pk; Type: CONSTRAINT; Schema: protostellar; Owner: postgres
--

ALTER TABLE ONLY protostellar."1LStatusObject"
    ADD CONSTRAINT "1LStatusObject_pk" PRIMARY KEY ("ID");


--
-- TOC entry 2407 (class 2606 OID 27122)
-- Name: 1LUser 1LUser_pk; Type: CONSTRAINT; Schema: protostellar; Owner: postgres
--

ALTER TABLE ONLY protostellar."1LUser"
    ADD CONSTRAINT "1LUser_pk" PRIMARY KEY ("ID");


--
-- TOC entry 2405 (class 2606 OID 27124)
-- Name: 1LTabColValidationResults 1LValidationResults_pk; Type: CONSTRAINT; Schema: protostellar; Owner: postgres
--

ALTER TABLE ONLY protostellar."1LTabColValidationResults"
    ADD CONSTRAINT "1LValidationResults_pk" PRIMARY KEY ("TableName", "ColName", "RecID");


--
-- TOC entry 2409 (class 2606 OID 27126)
-- Name: Address Address_PK; Type: CONSTRAINT; Schema: protostellar; Owner: postgres
--

ALTER TABLE ONLY protostellar."Address"
    ADD CONSTRAINT "Address_PK" PRIMARY KEY ("ID");


--
-- TOC entry 2400 (class 2606 OID 27128)
-- Name: 1LSystemExecResultsJobs AppSystemExecStatusResultsJobsPK; Type: CONSTRAINT; Schema: protostellar; Owner: postgres
--

ALTER TABLE ONLY protostellar."1LSystemExecResultsJobs"
    ADD CONSTRAINT "AppSystemExecStatusResultsJobsPK" PRIMARY KEY ("StartDate", "StartTime", "MachineName", "Process", "MachineIP");


--
-- TOC entry 2433 (class 2606 OID 27130)
-- Name: CustomsProcedure CustomsProcedurePK; Type: CONSTRAINT; Schema: protostellar; Owner: postgres
--

ALTER TABLE ONLY protostellar."CustomsProcedure"
    ADD CONSTRAINT "CustomsProcedurePK" PRIMARY KEY ("ID");


--
-- TOC entry 2391 (class 2606 OID 27132)
-- Name: 1LStatusCode Key4; Type: CONSTRAINT; Schema: protostellar; Owner: postgres
--

ALTER TABLE ONLY protostellar."1LStatusCode"
    ADD CONSTRAINT "Key4" PRIMARY KEY ("ID");


--
-- TOC entry 2508 (class 2606 OID 27134)
-- Name: TransportationMeans PK_TRANSPORTATIONMEANS; Type: CONSTRAINT; Schema: protostellar; Owner: postgres
--

ALTER TABLE ONLY protostellar."TransportationMeans"
    ADD CONSTRAINT "PK_TRANSPORTATIONMEANS" PRIMARY KEY ("ID");


--
-- TOC entry 2484 (class 2606 OID 27136)
-- Name: PackageCode PackageCodePK; Type: CONSTRAINT; Schema: protostellar; Owner: postgres
--

ALTER TABLE ONLY protostellar."PackageCode"
    ADD CONSTRAINT "PackageCodePK" PRIMARY KEY ("ID");


--
-- TOC entry 2389 (class 2606 OID 27138)
-- Name: 1LRole RolePK; Type: CONSTRAINT; Schema: protostellar; Owner: postgres
--

ALTER TABLE ONLY protostellar."1LRole"
    ADD CONSTRAINT "RolePK" PRIMARY KEY ("ID");


--
-- TOC entry 2504 (class 2606 OID 27140)
-- Name: TransportDirection TransportDirectionPK; Type: CONSTRAINT; Schema: protostellar; Owner: postgres
--

ALTER TABLE ONLY protostellar."TransportDirection"
    ADD CONSTRAINT "TransportDirectionPK" PRIMARY KEY ("ID");


--
-- TOC entry 2471 (class 2606 OID 27142)
-- Name: OrderItemVoyageSegment TransportOrderDetails_pkey; Type: CONSTRAINT; Schema: protostellar; Owner: postgres
--

ALTER TABLE ONLY protostellar."OrderItemVoyageSegment"
    ADD CONSTRAINT "TransportOrderDetails_pkey" PRIMARY KEY ("ID");


--
-- TOC entry 2414 (class 2606 OID 27144)
-- Name: Address_PartnerSystem address_partnersystem_pk; Type: CONSTRAINT; Schema: protostellar; Owner: postgres
--

ALTER TABLE ONLY protostellar."Address_PartnerSystem"
    ADD CONSTRAINT address_partnersystem_pk PRIMARY KEY ("AddressID", "PartnerSystemID");


--
-- TOC entry 2411 (class 2606 OID 27146)
-- Name: AddressGroup addressgroup_pk; Type: CONSTRAINT; Schema: protostellar; Owner: postgres
--

ALTER TABLE ONLY protostellar."AddressGroup"
    ADD CONSTRAINT addressgroup_pk PRIMARY KEY ("ID");


--
-- TOC entry 2418 (class 2606 OID 27148)
-- Name: ContainerFillingDegree_PartnerSystem containerfillingdegree_partnersystem_pk; Type: CONSTRAINT; Schema: protostellar; Owner: postgres
--

ALTER TABLE ONLY protostellar."ContainerFillingDegree_PartnerSystem"
    ADD CONSTRAINT containerfillingdegree_partnersystem_pk PRIMARY KEY ("PartnerSystemID", "ContainerFillingDegreeID");


--
-- TOC entry 2416 (class 2606 OID 27150)
-- Name: ContainerFillingDegree containerfillingdegree_pk; Type: CONSTRAINT; Schema: protostellar; Owner: postgres
--

ALTER TABLE ONLY protostellar."ContainerFillingDegree"
    ADD CONSTRAINT containerfillingdegree_pk PRIMARY KEY ("ID");


--
-- TOC entry 2421 (class 2606 OID 27152)
-- Name: ContainerSize containersize_pk; Type: CONSTRAINT; Schema: protostellar; Owner: postgres
--

ALTER TABLE ONLY protostellar."ContainerSize"
    ADD CONSTRAINT containersize_pk PRIMARY KEY ("ID");


--
-- TOC entry 2424 (class 2606 OID 27154)
-- Name: Country country_pk; Type: CONSTRAINT; Schema: protostellar; Owner: postgres
--

ALTER TABLE ONLY protostellar."Country"
    ADD CONSTRAINT country_pk PRIMARY KEY ("ID");


--
-- TOC entry 2428 (class 2606 OID 27156)
-- Name: CustomsDocumentsType_PartnerSystem customsdocumentstype_partnersystem_pk; Type: CONSTRAINT; Schema: protostellar; Owner: postgres
--

ALTER TABLE ONLY protostellar."CustomsDocumentsType_PartnerSystem"
    ADD CONSTRAINT customsdocumentstype_partnersystem_pk PRIMARY KEY ("CustomsDocumentsTypeID", "PartnerSystemID");


--
-- TOC entry 2426 (class 2606 OID 27158)
-- Name: CustomsDocumentsType customsdocumentstype_pk; Type: CONSTRAINT; Schema: protostellar; Owner: postgres
--

ALTER TABLE ONLY protostellar."CustomsDocumentsType"
    ADD CONSTRAINT customsdocumentstype_pk PRIMARY KEY ("ID");


--
-- TOC entry 2430 (class 2606 OID 27160)
-- Name: CustomsHandlingType customshandlingtype_pk; Type: CONSTRAINT; Schema: protostellar; Owner: postgres
--

ALTER TABLE ONLY protostellar."CustomsHandlingType"
    ADD CONSTRAINT customshandlingtype_pk PRIMARY KEY ("ID");


--
-- TOC entry 2435 (class 2606 OID 27162)
-- Name: CustomsTariff customstariff_pk; Type: CONSTRAINT; Schema: protostellar; Owner: postgres
--

ALTER TABLE ONLY protostellar."CustomsTariff"
    ADD CONSTRAINT customstariff_pk PRIMARY KEY ("ID");


--
-- TOC entry 2439 (class 2606 OID 27164)
-- Name: CustomsTradeAreaStatus_PartnerSystem customstradeareastatus_partnersystem_pk; Type: CONSTRAINT; Schema: protostellar; Owner: postgres
--

ALTER TABLE ONLY protostellar."CustomsTradeAreaStatus_PartnerSystem"
    ADD CONSTRAINT customstradeareastatus_partnersystem_pk PRIMARY KEY ("PartnerSystemID", "CustomsTradeAreaStatusID");


--
-- TOC entry 2437 (class 2606 OID 27166)
-- Name: CustomsTradeAreaStatus customstradeareastatus_pk; Type: CONSTRAINT; Schema: protostellar; Owner: postgres
--

ALTER TABLE ONLY protostellar."CustomsTradeAreaStatus"
    ADD CONSTRAINT customstradeareastatus_pk PRIMARY KEY ("ID");


--
-- TOC entry 2441 (class 2606 OID 27168)
-- Name: DangerousGoods dangerousgoods_pk; Type: CONSTRAINT; Schema: protostellar; Owner: postgres
--

ALTER TABLE ONLY protostellar."DangerousGoods"
    ADD CONSTRAINT dangerousgoods_pk PRIMARY KEY ("ID");


--
-- TOC entry 2444 (class 2606 OID 27170)
-- Name: Goods goods_pk; Type: CONSTRAINT; Schema: protostellar; Owner: postgres
--

ALTER TABLE ONLY protostellar."Goods"
    ADD CONSTRAINT goods_pk PRIMARY KEY ("ID");


--
-- TOC entry 2448 (class 2606 OID 27172)
-- Name: Item item_pk; Type: CONSTRAINT; Schema: protostellar; Owner: postgres
--

ALTER TABLE ONLY protostellar."Item"
    ADD CONSTRAINT item_pk PRIMARY KEY ("ID");


--
-- TOC entry 2452 (class 2606 OID 27174)
-- Name: LoadingRequest_PartnerSystem loadingrequest_partnersystem_pk; Type: CONSTRAINT; Schema: protostellar; Owner: postgres
--

ALTER TABLE ONLY protostellar."LoadingRequest_PartnerSystem"
    ADD CONSTRAINT loadingrequest_partnersystem_pk PRIMARY KEY ("LoadingRequestID", "PartnerSystemID");


--
-- TOC entry 2450 (class 2606 OID 27176)
-- Name: LoadingRequest loadingrequest_pk; Type: CONSTRAINT; Schema: protostellar; Owner: postgres
--

ALTER TABLE ONLY protostellar."LoadingRequest"
    ADD CONSTRAINT loadingrequest_pk PRIMARY KEY ("ID");


--
-- TOC entry 2455 (class 2606 OID 27178)
-- Name: Location location_pk; Type: CONSTRAINT; Schema: protostellar; Owner: postgres
--

ALTER TABLE ONLY protostellar."Location"
    ADD CONSTRAINT location_pk PRIMARY KEY ("ID");


--
-- TOC entry 2494 (class 2606 OID 27180)
-- Name: Train newtable_pk; Type: CONSTRAINT; Schema: protostellar; Owner: postgres
--

ALTER TABLE ONLY protostellar."Train"
    ADD CONSTRAINT newtable_pk PRIMARY KEY ("ID");


--
-- TOC entry 2458 (class 2606 OID 27182)
-- Name: Order order_pk; Type: CONSTRAINT; Schema: protostellar; Owner: postgres
--

ALTER TABLE ONLY protostellar."Order"
    ADD CONSTRAINT order_pk PRIMARY KEY ("ID");


--
-- TOC entry 2460 (class 2606 OID 27184)
-- Name: OrderItem orderitem_pk; Type: CONSTRAINT; Schema: protostellar; Owner: postgres
--

ALTER TABLE ONLY protostellar."OrderItem"
    ADD CONSTRAINT orderitem_pk PRIMARY KEY ("ID");


--
-- TOC entry 2462 (class 2606 OID 27186)
-- Name: OrderItem orderitem_un; Type: CONSTRAINT; Schema: protostellar; Owner: postgres
--

ALTER TABLE ONLY protostellar."OrderItem"
    ADD CONSTRAINT orderitem_un UNIQUE ("OrderID", "ItemID");


--
-- TOC entry 2464 (class 2606 OID 27188)
-- Name: OrderItemDangerousGoods orderitemdangerousgoods_pk; Type: CONSTRAINT; Schema: protostellar; Owner: postgres
--

ALTER TABLE ONLY protostellar."OrderItemDangerousGoods"
    ADD CONSTRAINT orderitemdangerousgoods_pk PRIMARY KEY ("OrderItemID", "DangerousGoodsID");


--
-- TOC entry 2466 (class 2606 OID 27190)
-- Name: OrderItemGoods orderitemgoods_pk; Type: CONSTRAINT; Schema: protostellar; Owner: postgres
--

ALTER TABLE ONLY protostellar."OrderItemGoods"
    ADD CONSTRAINT orderitemgoods_pk PRIMARY KEY ("OrderItemID", "GoodsID");


--
-- TOC entry 2468 (class 2606 OID 27192)
-- Name: OrderItemSeals orderitemseals_pk; Type: CONSTRAINT; Schema: protostellar; Owner: postgres
--

ALTER TABLE ONLY protostellar."OrderItemSeals"
    ADD CONSTRAINT orderitemseals_pk PRIMARY KEY ("OrderItemID", "SealLocationID", "SealTypeID");


--
-- TOC entry 2473 (class 2606 OID 27194)
-- Name: OrderItemVoyageSegment orderitemvoyagesegment_un; Type: CONSTRAINT; Schema: protostellar; Owner: postgres
--

ALTER TABLE ONLY protostellar."OrderItemVoyageSegment"
    ADD CONSTRAINT orderitemvoyagesegment_un UNIQUE ("OrderID", "OrderItemID", "OrderVoyageSegmentID");


--
-- TOC entry 2475 (class 2606 OID 27196)
-- Name: OrderVoyageSegment ordervoyagesegment_pk; Type: CONSTRAINT; Schema: protostellar; Owner: postgres
--

ALTER TABLE ONLY protostellar."OrderVoyageSegment"
    ADD CONSTRAINT ordervoyagesegment_pk PRIMARY KEY ("ID");


--
-- TOC entry 2477 (class 2606 OID 27198)
-- Name: OrderVoyageSegmentTrain ordervoyagesegmenttrain_pk; Type: CONSTRAINT; Schema: protostellar; Owner: postgres
--

ALTER TABLE ONLY protostellar."OrderVoyageSegmentTrain"
    ADD CONSTRAINT ordervoyagesegmenttrain_pk PRIMARY KEY ("ID");


--
-- TOC entry 2479 (class 2606 OID 27200)
-- Name: OrderVoyageSegmentTrain ordervoyagesegmenttrain_un; Type: CONSTRAINT; Schema: protostellar; Owner: postgres
--

ALTER TABLE ONLY protostellar."OrderVoyageSegmentTrain"
    ADD CONSTRAINT ordervoyagesegmenttrain_un UNIQUE ("TrainID", "TrainDate");


--
-- TOC entry 2486 (class 2606 OID 27202)
-- Name: RailwayClass railwayclass_pk; Type: CONSTRAINT; Schema: protostellar; Owner: postgres
--

ALTER TABLE ONLY protostellar."RailwayClass"
    ADD CONSTRAINT railwayclass_pk PRIMARY KEY ("ID");


--
-- TOC entry 2488 (class 2606 OID 27204)
-- Name: SealLocation seallocation_pk; Type: CONSTRAINT; Schema: protostellar; Owner: postgres
--

ALTER TABLE ONLY protostellar."SealLocation"
    ADD CONSTRAINT seallocation_pk PRIMARY KEY ("ID");


--
-- TOC entry 2490 (class 2606 OID 27206)
-- Name: SealType sealtype_pk; Type: CONSTRAINT; Schema: protostellar; Owner: postgres
--

ALTER TABLE ONLY protostellar."SealType"
    ADD CONSTRAINT sealtype_pk PRIMARY KEY ("ID");


--
-- TOC entry 2492 (class 2606 OID 27208)
-- Name: Ship ship_pk; Type: CONSTRAINT; Schema: protostellar; Owner: postgres
--

ALTER TABLE ONLY protostellar."Ship"
    ADD CONSTRAINT ship_pk PRIMARY KEY ("ID");


--
-- TOC entry 2497 (class 2606 OID 27210)
-- Name: TrainAL train_pk; Type: CONSTRAINT; Schema: protostellar; Owner: postgres
--

ALTER TABLE ONLY protostellar."TrainAL"
    ADD CONSTRAINT train_pk PRIMARY KEY ("ID");


--
-- TOC entry 2501 (class 2606 OID 27212)
-- Name: TrainETA traineta_pk; Type: CONSTRAINT; Schema: protostellar; Owner: postgres
--

ALTER TABLE ONLY protostellar."TrainETA"
    ADD CONSTRAINT traineta_pk PRIMARY KEY ("OrderVoyageSegmentTrainID", "LocationID", "TrainDate");


--
-- TOC entry 2481 (class 2606 OID 27214)
-- Name: OrderVoyageSegmentTrainWagons trainwagons_pk; Type: CONSTRAINT; Schema: protostellar; Owner: postgres
--

ALTER TABLE ONLY protostellar."OrderVoyageSegmentTrainWagons"
    ADD CONSTRAINT trainwagons_pk PRIMARY KEY ("OrderVoyageSegmentTrainID", "WagonID", "WagonSeqNo");


--
-- TOC entry 2506 (class 2606 OID 27216)
-- Name: TransportDirection_PartnerSystem transportdirection_partnersystem_pk; Type: CONSTRAINT; Schema: protostellar; Owner: postgres
--

ALTER TABLE ONLY protostellar."TransportDirection_PartnerSystem"
    ADD CONSTRAINT transportdirection_partnersystem_pk PRIMARY KEY ("TransportDirectionID", "PartnerSystemID");


--
-- TOC entry 2514 (class 2606 OID 27218)
-- Name: VoyageType voyagetype_pk; Type: CONSTRAINT; Schema: protostellar; Owner: postgres
--

ALTER TABLE ONLY protostellar."VoyageType"
    ADD CONSTRAINT voyagetype_pk PRIMARY KEY ("ID");


--
-- TOC entry 2516 (class 2606 OID 27220)
-- Name: Wagon wagon_pk; Type: CONSTRAINT; Schema: protostellar; Owner: postgres
--

ALTER TABLE ONLY protostellar."Wagon"
    ADD CONSTRAINT wagon_pk PRIMARY KEY ("ID");


--
-- TOC entry 2518 (class 2606 OID 27222)
-- Name: Wagon wagon_un; Type: CONSTRAINT; Schema: protostellar; Owner: postgres
--

ALTER TABLE ONLY protostellar."Wagon"
    ADD CONSTRAINT wagon_un UNIQUE ("WagonNo");


--
-- TOC entry 2401 (class 1259 OID 27223)
-- Name: 1LValidationResults_ResultCode_IDX3; Type: INDEX; Schema: protostellar; Owner: postgres
--

CREATE INDEX "1LValidationResults_ResultCode_IDX3" ON protostellar."1LTabColValidationResults" USING btree ("ResultCode");


--
-- TOC entry 2402 (class 1259 OID 27224)
-- Name: 1LValidationResults_TableName_IDX1; Type: INDEX; Schema: protostellar; Owner: postgres
--

CREATE INDEX "1LValidationResults_TableName_IDX1" ON protostellar."1LTabColValidationResults" USING btree ("TableName", "ColName", "RecID");


--
-- TOC entry 2403 (class 1259 OID 27225)
-- Name: 1LValidationResults_TableName_IDX2; Type: INDEX; Schema: protostellar; Owner: postgres
--

CREATE INDEX "1LValidationResults_TableName_IDX2" ON protostellar."1LTabColValidationResults" USING btree ("TableName");


--
-- TOC entry 2398 (class 1259 OID 27226)
-- Name: AppSystemExecResultsJobsI01; Type: INDEX; Schema: protostellar; Owner: postgres
--

CREATE INDEX "AppSystemExecResultsJobsI01" ON protostellar."1LSystemExecResultsJobs" USING btree ("StartTime", "Process", "MachineName", "StartDate");


--
-- TOC entry 2385 (class 1259 OID 27227)
-- Name: AppSystemExternalSystemI01; Type: INDEX; Schema: protostellar; Owner: postgres
--

CREATE INDEX "AppSystemExternalSystemI01" ON protostellar."1LPartnerSystem" USING btree ("SystemShortDesc");


--
-- TOC entry 2419 (class 1259 OID 27228)
-- Name: ContainerTypeI01; Type: INDEX; Schema: protostellar; Owner: postgres
--

CREATE INDEX "ContainerTypeI01" ON protostellar."ContainerSize" USING btree ("ContainerTypeCode");


--
-- TOC entry 2422 (class 1259 OID 27229)
-- Name: CountryI01; Type: INDEX; Schema: protostellar; Owner: postgres
--

CREATE INDEX "CountryI01" ON protostellar."Country" USING btree ("Code");


--
-- TOC entry 2431 (class 1259 OID 27230)
-- Name: CustomsProcedureI01; Type: INDEX; Schema: protostellar; Owner: postgres
--

CREATE INDEX "CustomsProcedureI01" ON protostellar."CustomsProcedure" USING btree ("ShortDesc");


--
-- TOC entry 2469 (class 1259 OID 27231)
-- Name: ID_I; Type: INDEX; Schema: protostellar; Owner: postgres
--

CREATE INDEX "ID_I" ON protostellar."OrderItemVoyageSegment" USING btree ("ID");


--
-- TOC entry 2482 (class 1259 OID 27232)
-- Name: PackageCodeI01; Type: INDEX; Schema: protostellar; Owner: postgres
--

CREATE INDEX "PackageCodeI01" ON protostellar."PackageCode" USING btree ("Code");


--
-- TOC entry 2387 (class 1259 OID 27233)
-- Name: RoleI01; Type: INDEX; Schema: protostellar; Owner: postgres
--

CREATE INDEX "RoleI01" ON protostellar."1LRole" USING btree ("Desc");


--
-- TOC entry 2502 (class 1259 OID 27234)
-- Name: TransportDirectionI01; Type: INDEX; Schema: protostellar; Owner: postgres
--

CREATE INDEX "TransportDirectionI01" ON protostellar."TransportDirection" USING btree ("TransportDirectionDesc");


--
-- TOC entry 2511 (class 1259 OID 27235)
-- Name: fki_; Type: INDEX; Schema: protostellar; Owner: postgres
--

CREATE INDEX fki_ ON protostellar."VoyageType" USING btree ("TransportationMeansFromID");


--
-- TOC entry 2498 (class 1259 OID 27236)
-- Name: i_trainconfigurations_1; Type: INDEX; Schema: protostellar; Owner: postgres
--

CREATE INDEX i_trainconfigurations_1 ON protostellar."TrainALConfigurations" USING btree ("TrainID", "WagonNo", "CombinationNo", "Position");


--
-- TOC entry 2382 (class 1259 OID 27237)
-- Name: idx_1LOrderMaintFilesProcessed_lookup; Type: INDEX; Schema: protostellar; Owner: postgres
--

CREATE INDEX "idx_1LOrderMaintFilesProcessed_lookup" ON protostellar."1LOrderMaintFilesProcessed" USING btree ("ShortFilename");


--
-- TOC entry 2386 (class 1259 OID 27238)
-- Name: idx_1LPartnerSystem_lookup; Type: INDEX; Schema: protostellar; Owner: postgres
--

CREATE INDEX "idx_1LPartnerSystem_lookup" ON protostellar."1LPartnerSystem" USING btree ("1LPartnerSystemCode");


--
-- TOC entry 2392 (class 1259 OID 27239)
-- Name: idx_1LStatusCode_1; Type: INDEX; Schema: protostellar; Owner: postgres
--

CREATE INDEX "idx_1LStatusCode_1" ON protostellar."1LStatusCode" USING btree ("ID");


--
-- TOC entry 2393 (class 1259 OID 27240)
-- Name: idx_1LStatusCode_2; Type: INDEX; Schema: protostellar; Owner: postgres
--

CREATE INDEX "idx_1LStatusCode_2" ON protostellar."1LStatusCode" USING btree ("1LStatusCode");


--
-- TOC entry 2412 (class 1259 OID 27241)
-- Name: idx_Address_Matchinglist_lookup; Type: INDEX; Schema: protostellar; Owner: postgres
--

CREATE INDEX "idx_Address_Matchinglist_lookup" ON protostellar."Address_Matchinglist" USING btree ("SearchText");


--
-- TOC entry 2442 (class 1259 OID 27242)
-- Name: idx_DangerousGoods_lookup; Type: INDEX; Schema: protostellar; Owner: postgres
--

CREATE INDEX "idx_DangerousGoods_lookup" ON protostellar."DangerousGoods" USING btree ("Code");


--
-- TOC entry 2453 (class 1259 OID 27243)
-- Name: idx_Location_lookup; Type: INDEX; Schema: protostellar; Owner: postgres
--

CREATE INDEX "idx_Location_lookup" ON protostellar."Location" USING btree ("ID");


--
-- TOC entry 2376 (class 1259 OID 27244)
-- Name: idx_MailAttachmentDetails_lookup; Type: INDEX; Schema: protostellar; Owner: postgres
--

CREATE INDEX "idx_MailAttachmentDetails_lookup" ON protostellar."1LEMailAttachmentDetails" USING btree ("AttachmentFilename");


--
-- TOC entry 2379 (class 1259 OID 27245)
-- Name: idx_MailReceiveAllDetails_lookup; Type: INDEX; Schema: protostellar; Owner: postgres
--

CREATE INDEX "idx_MailReceiveAllDetails_lookup" ON protostellar."1LEMailReceiveAllDetails" USING btree ("Message-ID");


--
-- TOC entry 2499 (class 1259 OID 27246)
-- Name: idx_TrainETA_lookup; Type: INDEX; Schema: protostellar; Owner: postgres
--

CREATE INDEX "idx_TrainETA_lookup" ON protostellar."TrainETA" USING btree ("TrainNumber", "TrainDate", "OrderVoyageSegmentTrainID");


--
-- TOC entry 2495 (class 1259 OID 27247)
-- Name: idx_Train_lookup; Type: INDEX; Schema: protostellar; Owner: postgres
--

CREATE INDEX "idx_Train_lookup" ON protostellar."TrainAL" USING btree ("ID");


--
-- TOC entry 2510 (class 1259 OID 27248)
-- Name: idx_TransportOrderRejects_lookup; Type: INDEX; Schema: protostellar; Owner: postgres
--

CREATE INDEX "idx_TransportOrderRejects_lookup" ON protostellar."VoyageOrderSegmentRejects" USING btree ("ID", "Message-ID", "InfileSheetRowNr");


--
-- TOC entry 2509 (class 1259 OID 27249)
-- Name: idx_TransportationMeans_lookup; Type: INDEX; Schema: protostellar; Owner: postgres
--

CREATE INDEX "idx_TransportationMeans_lookup" ON protostellar."TransportationMeans" USING btree ("ID");


--
-- TOC entry 2512 (class 1259 OID 27250)
-- Name: idx_VoyageType_lookup; Type: INDEX; Schema: protostellar; Owner: postgres
--

CREATE INDEX "idx_VoyageType_lookup" ON protostellar."VoyageType" USING btree ("ID");


--
-- TOC entry 2445 (class 1259 OID 27251)
-- Name: item_containernumber_idx; Type: INDEX; Schema: protostellar; Owner: postgres
--

CREATE INDEX item_containernumber_idx ON protostellar."Item" USING btree ("ContainerNumber");


--
-- TOC entry 2446 (class 1259 OID 27252)
-- Name: item_containersizeid_idx; Type: INDEX; Schema: protostellar; Owner: postgres
--

CREATE INDEX item_containersizeid_idx ON protostellar."Item" USING btree ("ContainerSizeID");


--
-- TOC entry 2456 (class 1259 OID 27253)
-- Name: location_matchinglist_locationname_idx; Type: INDEX; Schema: protostellar; Owner: postgres
--

CREATE INDEX location_matchinglist_locationname_idx ON protostellar."Location_Matchinglist" USING btree ("LocationName");


--
-- TOC entry 2519 (class 2606 OID 27254)
-- Name: 1LEMailAccounts 1LEMailAccounts_Address_FK; Type: FK CONSTRAINT; Schema: protostellar; Owner: postgres
--

ALTER TABLE ONLY protostellar."1LEMailAccounts"
    ADD CONSTRAINT "1LEMailAccounts_Address_FK" FOREIGN KEY ("CustomerAddressID") REFERENCES protostellar."Address"("ID");


--
-- TOC entry 2520 (class 2606 OID 27259)
-- Name: 1LStatusMapping 1LStatusMapping_1LPartnerSystem_FK; Type: FK CONSTRAINT; Schema: protostellar; Owner: postgres
--

ALTER TABLE ONLY protostellar."1LStatusMapping"
    ADD CONSTRAINT "1LStatusMapping_1LPartnerSystem_FK" FOREIGN KEY ("PartnerSystemID") REFERENCES protostellar."1LPartnerSystem"("ID");


--
-- TOC entry 2521 (class 2606 OID 27264)
-- Name: 1LStatusMapping 1LStatusMapping_1LStatusCode_FK; Type: FK CONSTRAINT; Schema: protostellar; Owner: postgres
--

ALTER TABLE ONLY protostellar."1LStatusMapping"
    ADD CONSTRAINT "1LStatusMapping_1LStatusCode_FK" FOREIGN KEY ("1LStatusCodeID") REFERENCES protostellar."1LStatusCode"("ID");


--
-- TOC entry 2522 (class 2606 OID 27269)
-- Name: 1LStatusMapping 1LStatusMapping_1LStatusObject_FK; Type: FK CONSTRAINT; Schema: protostellar; Owner: postgres
--

ALTER TABLE ONLY protostellar."1LStatusMapping"
    ADD CONSTRAINT "1LStatusMapping_1LStatusObject_FK" FOREIGN KEY ("1LStatusObjectID") REFERENCES protostellar."1LStatusObject"("ID");


--
-- TOC entry 2523 (class 2606 OID 27274)
-- Name: 1LStatusMapping 1LStatusMapping_TransportDirection_FK; Type: FK CONSTRAINT; Schema: protostellar; Owner: postgres
--

ALTER TABLE ONLY protostellar."1LStatusMapping"
    ADD CONSTRAINT "1LStatusMapping_TransportDirection_FK" FOREIGN KEY ("PartnerTransportDirectionID") REFERENCES protostellar."TransportDirection"("ID");


--
-- TOC entry 2586 (class 2606 OID 27279)
-- Name: VoyageType TranspMeansFrom; Type: FK CONSTRAINT; Schema: protostellar; Owner: postgres
--

ALTER TABLE ONLY protostellar."VoyageType"
    ADD CONSTRAINT "TranspMeansFrom" FOREIGN KEY ("TransportationMeansFromID") REFERENCES protostellar."TransportationMeans"("ID");


--
-- TOC entry 2587 (class 2606 OID 27284)
-- Name: VoyageType TranspMeansTo; Type: FK CONSTRAINT; Schema: protostellar; Owner: postgres
--

ALTER TABLE ONLY protostellar."VoyageType"
    ADD CONSTRAINT "TranspMeansTo" FOREIGN KEY ("TransportationMeansToID") REFERENCES protostellar."TransportationMeans"("ID");


--
-- TOC entry 2524 (class 2606 OID 27289)
-- Name: 1LUserRole _1LUserRole___1LRole__FK; Type: FK CONSTRAINT; Schema: protostellar; Owner: postgres
--

ALTER TABLE ONLY protostellar."1LUserRole"
    ADD CONSTRAINT "_1LUserRole___1LRole__FK" FOREIGN KEY ("1LRoleID") REFERENCES protostellar."1LRole"("ID");


--
-- TOC entry 2582 (class 2606 OID 27294)
-- Name: OrderVoyageSegmentTrainWagons _TrainWagons__Wagon_FK; Type: FK CONSTRAINT; Schema: protostellar; Owner: postgres
--

ALTER TABLE ONLY protostellar."OrderVoyageSegmentTrainWagons"
    ADD CONSTRAINT "_TrainWagons__Wagon_FK" FOREIGN KEY ("WagonID") REFERENCES protostellar."Wagon"("ID");


--
-- TOC entry 2525 (class 2606 OID 27299)
-- Name: Address address_addressgroup_fk; Type: FK CONSTRAINT; Schema: protostellar; Owner: postgres
--

ALTER TABLE ONLY protostellar."Address"
    ADD CONSTRAINT address_addressgroup_fk FOREIGN KEY ("AddressGroupID") REFERENCES protostellar."AddressGroup"("ID");


--
-- TOC entry 2526 (class 2606 OID 27304)
-- Name: Address_PartnerSystem address_partnersystem_1lpartnersystem_fk; Type: FK CONSTRAINT; Schema: protostellar; Owner: postgres
--

ALTER TABLE ONLY protostellar."Address_PartnerSystem"
    ADD CONSTRAINT address_partnersystem_1lpartnersystem_fk FOREIGN KEY ("PartnerSystemID") REFERENCES protostellar."1LPartnerSystem"("ID");


--
-- TOC entry 2527 (class 2606 OID 27309)
-- Name: Address_PartnerSystem address_partnersystem_address_fk; Type: FK CONSTRAINT; Schema: protostellar; Owner: postgres
--

ALTER TABLE ONLY protostellar."Address_PartnerSystem"
    ADD CONSTRAINT address_partnersystem_address_fk FOREIGN KEY ("AddressID") REFERENCES protostellar."Address"("ID");


--
-- TOC entry 2528 (class 2606 OID 27314)
-- Name: ContainerFillingDegree_PartnerSystem containerfillingdegree_partnersystem_1lpartnersystem_fk; Type: FK CONSTRAINT; Schema: protostellar; Owner: postgres
--

ALTER TABLE ONLY protostellar."ContainerFillingDegree_PartnerSystem"
    ADD CONSTRAINT containerfillingdegree_partnersystem_1lpartnersystem_fk FOREIGN KEY ("PartnerSystemID") REFERENCES protostellar."1LPartnerSystem"("ID");


--
-- TOC entry 2529 (class 2606 OID 27319)
-- Name: ContainerFillingDegree_PartnerSystem containerfillingdegree_partnersystem_containerfillingdegree_fk; Type: FK CONSTRAINT; Schema: protostellar; Owner: postgres
--

ALTER TABLE ONLY protostellar."ContainerFillingDegree_PartnerSystem"
    ADD CONSTRAINT containerfillingdegree_partnersystem_containerfillingdegree_fk FOREIGN KEY ("ContainerFillingDegreeID") REFERENCES protostellar."ContainerFillingDegree"("ID");


--
-- TOC entry 2530 (class 2606 OID 27324)
-- Name: CustomsDocumentsType_PartnerSystem customsdocumentstype_partnersystem_1lpartnersystem_fk; Type: FK CONSTRAINT; Schema: protostellar; Owner: postgres
--

ALTER TABLE ONLY protostellar."CustomsDocumentsType_PartnerSystem"
    ADD CONSTRAINT customsdocumentstype_partnersystem_1lpartnersystem_fk FOREIGN KEY ("PartnerSystemID") REFERENCES protostellar."1LPartnerSystem"("ID");


--
-- TOC entry 2531 (class 2606 OID 27329)
-- Name: CustomsDocumentsType_PartnerSystem customsdocumentstype_partnersystem_customsdocumentstype_fk; Type: FK CONSTRAINT; Schema: protostellar; Owner: postgres
--

ALTER TABLE ONLY protostellar."CustomsDocumentsType_PartnerSystem"
    ADD CONSTRAINT customsdocumentstype_partnersystem_customsdocumentstype_fk FOREIGN KEY ("CustomsDocumentsTypeID") REFERENCES protostellar."CustomsDocumentsType"("ID");


--
-- TOC entry 2532 (class 2606 OID 27334)
-- Name: CustomsTradeAreaStatus_PartnerSystem customstradeareastatus_partnersystem_1lpartnersystem_fk; Type: FK CONSTRAINT; Schema: protostellar; Owner: postgres
--

ALTER TABLE ONLY protostellar."CustomsTradeAreaStatus_PartnerSystem"
    ADD CONSTRAINT customstradeareastatus_partnersystem_1lpartnersystem_fk FOREIGN KEY ("PartnerSystemID") REFERENCES protostellar."1LPartnerSystem"("ID");


--
-- TOC entry 2533 (class 2606 OID 27339)
-- Name: CustomsTradeAreaStatus_PartnerSystem customstradeareastatus_partnersystem_customstradeareastatus_fk; Type: FK CONSTRAINT; Schema: protostellar; Owner: postgres
--

ALTER TABLE ONLY protostellar."CustomsTradeAreaStatus_PartnerSystem"
    ADD CONSTRAINT customstradeareastatus_partnersystem_customstradeareastatus_fk FOREIGN KEY ("CustomsTradeAreaStatusID") REFERENCES protostellar."CustomsTradeAreaStatus"("ID");


--
-- TOC entry 2534 (class 2606 OID 27344)
-- Name: LoadingRequest_PartnerSystem loadingrequest_partnersystem_1lpartnersystem_fk; Type: FK CONSTRAINT; Schema: protostellar; Owner: postgres
--

ALTER TABLE ONLY protostellar."LoadingRequest_PartnerSystem"
    ADD CONSTRAINT loadingrequest_partnersystem_1lpartnersystem_fk FOREIGN KEY ("PartnerSystemID") REFERENCES protostellar."1LPartnerSystem"("ID");


--
-- TOC entry 2535 (class 2606 OID 27349)
-- Name: LoadingRequest_PartnerSystem loadingrequest_partnersystem_loadingrequest_fk; Type: FK CONSTRAINT; Schema: protostellar; Owner: postgres
--

ALTER TABLE ONLY protostellar."LoadingRequest_PartnerSystem"
    ADD CONSTRAINT loadingrequest_partnersystem_loadingrequest_fk FOREIGN KEY ("LoadingRequestID") REFERENCES protostellar."LoadingRequest"("ID");


--
-- TOC entry 2536 (class 2606 OID 27354)
-- Name: Location location_1lpartnersystem_fk; Type: FK CONSTRAINT; Schema: protostellar; Owner: postgres
--

ALTER TABLE ONLY protostellar."Location"
    ADD CONSTRAINT location_1lpartnersystem_fk FOREIGN KEY ("InterfacePartnerSystemID") REFERENCES protostellar."1LPartnerSystem"("ID");


--
-- TOC entry 2537 (class 2606 OID 27359)
-- Name: Location location_country_fk; Type: FK CONSTRAINT; Schema: protostellar; Owner: postgres
--

ALTER TABLE ONLY protostellar."Location"
    ADD CONSTRAINT location_country_fk FOREIGN KEY ("CountryID") REFERENCES protostellar."Country"("ID");


--
-- TOC entry 2538 (class 2606 OID 27364)
-- Name: Order order_1lstatuscode_fk; Type: FK CONSTRAINT; Schema: protostellar; Owner: postgres
--

ALTER TABLE ONLY protostellar."Order"
    ADD CONSTRAINT order_1lstatuscode_fk FOREIGN KEY ("1LStatusCodeID") REFERENCES protostellar."1LStatusCode"("ID");


--
-- TOC entry 2539 (class 2606 OID 27369)
-- Name: Order order_address_fk; Type: FK CONSTRAINT; Schema: protostellar; Owner: postgres
--

ALTER TABLE ONLY protostellar."Order"
    ADD CONSTRAINT order_address_fk FOREIGN KEY ("CustomerAddressID") REFERENCES protostellar."Address"("ID");


--
-- TOC entry 2540 (class 2606 OID 27374)
-- Name: Order order_location_fk; Type: FK CONSTRAINT; Schema: protostellar; Owner: postgres
--

ALTER TABLE ONLY protostellar."Order"
    ADD CONSTRAINT order_location_fk FOREIGN KEY ("StartLocationID") REFERENCES protostellar."Location"("ID");


--
-- TOC entry 2541 (class 2606 OID 27379)
-- Name: Order order_location_fk_1; Type: FK CONSTRAINT; Schema: protostellar; Owner: postgres
--

ALTER TABLE ONLY protostellar."Order"
    ADD CONSTRAINT order_location_fk_1 FOREIGN KEY ("DestinationLocationID") REFERENCES protostellar."Location"("ID");


--
-- TOC entry 2542 (class 2606 OID 27384)
-- Name: OrderItem orderitem_1lstatuscode_fk; Type: FK CONSTRAINT; Schema: protostellar; Owner: postgres
--

ALTER TABLE ONLY protostellar."OrderItem"
    ADD CONSTRAINT orderitem_1lstatuscode_fk FOREIGN KEY ("1LStatusCodeID") REFERENCES protostellar."1LStatusCode"("ID");


--
-- TOC entry 2543 (class 2606 OID 27389)
-- Name: OrderItem orderitem_address_fk; Type: FK CONSTRAINT; Schema: protostellar; Owner: postgres
--

ALTER TABLE ONLY protostellar."OrderItem"
    ADD CONSTRAINT orderitem_address_fk FOREIGN KEY ("ConsigneeAddressID") REFERENCES protostellar."Address"("ID");


--
-- TOC entry 2544 (class 2606 OID 27394)
-- Name: OrderItem orderitem_address_fk_1; Type: FK CONSTRAINT; Schema: protostellar; Owner: postgres
--

ALTER TABLE ONLY protostellar."OrderItem"
    ADD CONSTRAINT orderitem_address_fk_1 FOREIGN KEY ("ConsignorAddressID") REFERENCES protostellar."Address"("ID");


--
-- TOC entry 2545 (class 2606 OID 27399)
-- Name: OrderItem orderitem_address_fk_2; Type: FK CONSTRAINT; Schema: protostellar; Owner: postgres
--

ALTER TABLE ONLY protostellar."OrderItem"
    ADD CONSTRAINT orderitem_address_fk_2 FOREIGN KEY ("SupplierAddressID") REFERENCES protostellar."Address"("ID");


--
-- TOC entry 2546 (class 2606 OID 27404)
-- Name: OrderItem orderitem_containersize_fk; Type: FK CONSTRAINT; Schema: protostellar; Owner: postgres
--

ALTER TABLE ONLY protostellar."OrderItem"
    ADD CONSTRAINT orderitem_containersize_fk FOREIGN KEY ("ContainerSizeID") REFERENCES protostellar."ContainerSize"("ID");


--
-- TOC entry 2547 (class 2606 OID 27409)
-- Name: OrderItem orderitem_item_fk; Type: FK CONSTRAINT; Schema: protostellar; Owner: postgres
--

ALTER TABLE ONLY protostellar."OrderItem"
    ADD CONSTRAINT orderitem_item_fk FOREIGN KEY ("ItemID") REFERENCES protostellar."Item"("ID");


--
-- TOC entry 2548 (class 2606 OID 27414)
-- Name: OrderItem orderitem_order_fk; Type: FK CONSTRAINT; Schema: protostellar; Owner: postgres
--

ALTER TABLE ONLY protostellar."OrderItem"
    ADD CONSTRAINT orderitem_order_fk FOREIGN KEY ("OrderID") REFERENCES protostellar."Order"("ID");


--
-- TOC entry 2549 (class 2606 OID 27419)
-- Name: OrderItemDangerousGoods orderitemdangerousgoods_dangerousgoods_fk; Type: FK CONSTRAINT; Schema: protostellar; Owner: postgres
--

ALTER TABLE ONLY protostellar."OrderItemDangerousGoods"
    ADD CONSTRAINT orderitemdangerousgoods_dangerousgoods_fk FOREIGN KEY ("DangerousGoodsID") REFERENCES protostellar."DangerousGoods"("ID");


--
-- TOC entry 2550 (class 2606 OID 27424)
-- Name: OrderItemDangerousGoods orderitemdangerousgoods_orderitem_fk; Type: FK CONSTRAINT; Schema: protostellar; Owner: postgres
--

ALTER TABLE ONLY protostellar."OrderItemDangerousGoods"
    ADD CONSTRAINT orderitemdangerousgoods_orderitem_fk FOREIGN KEY ("OrderItemID") REFERENCES protostellar."OrderItem"("ID");


--
-- TOC entry 2551 (class 2606 OID 27429)
-- Name: OrderItemGoods orderitemgoods_goods_fk; Type: FK CONSTRAINT; Schema: protostellar; Owner: postgres
--

ALTER TABLE ONLY protostellar."OrderItemGoods"
    ADD CONSTRAINT orderitemgoods_goods_fk FOREIGN KEY ("GoodsID") REFERENCES protostellar."Goods"("ID");


--
-- TOC entry 2552 (class 2606 OID 27434)
-- Name: OrderItemGoods orderitemgoods_orderitem_fk; Type: FK CONSTRAINT; Schema: protostellar; Owner: postgres
--

ALTER TABLE ONLY protostellar."OrderItemGoods"
    ADD CONSTRAINT orderitemgoods_orderitem_fk FOREIGN KEY ("OrderItemID") REFERENCES protostellar."OrderItem"("ID");


--
-- TOC entry 2553 (class 2606 OID 27439)
-- Name: OrderItemSeals orderitemseals_orderitem_fk; Type: FK CONSTRAINT; Schema: protostellar; Owner: postgres
--

ALTER TABLE ONLY protostellar."OrderItemSeals"
    ADD CONSTRAINT orderitemseals_orderitem_fk FOREIGN KEY ("OrderItemID") REFERENCES protostellar."OrderItem"("ID");


--
-- TOC entry 2554 (class 2606 OID 27444)
-- Name: OrderItemSeals orderitemseals_seallocation_fk; Type: FK CONSTRAINT; Schema: protostellar; Owner: postgres
--

ALTER TABLE ONLY protostellar."OrderItemSeals"
    ADD CONSTRAINT orderitemseals_seallocation_fk FOREIGN KEY ("SealLocationID") REFERENCES protostellar."SealLocation"("ID");


--
-- TOC entry 2555 (class 2606 OID 27449)
-- Name: OrderItemSeals orderitemseals_sealtype_fk; Type: FK CONSTRAINT; Schema: protostellar; Owner: postgres
--

ALTER TABLE ONLY protostellar."OrderItemSeals"
    ADD CONSTRAINT orderitemseals_sealtype_fk FOREIGN KEY ("SealTypeID") REFERENCES protostellar."SealType"("ID");


--
-- TOC entry 2556 (class 2606 OID 27454)
-- Name: OrderItemVoyageSegment orderitemvoyagesegment_1lemailreceivealldetails_fk; Type: FK CONSTRAINT; Schema: protostellar; Owner: postgres
--

ALTER TABLE ONLY protostellar."OrderItemVoyageSegment"
    ADD CONSTRAINT orderitemvoyagesegment_1lemailreceivealldetails_fk FOREIGN KEY ("1LEMailMessageID") REFERENCES protostellar."1LEMailReceiveAllDetails"("Message-ID");


--
-- TOC entry 2557 (class 2606 OID 27459)
-- Name: OrderItemVoyageSegment orderitemvoyagesegment_1lstatuscode_fk; Type: FK CONSTRAINT; Schema: protostellar; Owner: postgres
--

ALTER TABLE ONLY protostellar."OrderItemVoyageSegment"
    ADD CONSTRAINT orderitemvoyagesegment_1lstatuscode_fk FOREIGN KEY ("1LStatusCodeID") REFERENCES protostellar."1LStatusCode"("ID");


--
-- TOC entry 2558 (class 2606 OID 27464)
-- Name: OrderItemVoyageSegment orderitemvoyagesegment_address_fk; Type: FK CONSTRAINT; Schema: protostellar; Owner: postgres
--

ALTER TABLE ONLY protostellar."OrderItemVoyageSegment"
    ADD CONSTRAINT orderitemvoyagesegment_address_fk FOREIGN KEY ("ForwardingAgentAddressID") REFERENCES protostellar."Address"("ID");


--
-- TOC entry 2559 (class 2606 OID 27469)
-- Name: OrderItemVoyageSegment orderitemvoyagesegment_address_fk_1; Type: FK CONSTRAINT; Schema: protostellar; Owner: postgres
--

ALTER TABLE ONLY protostellar."OrderItemVoyageSegment"
    ADD CONSTRAINT orderitemvoyagesegment_address_fk_1 FOREIGN KEY ("ShippingCompanyAddressID") REFERENCES protostellar."Address"("ID");


--
-- TOC entry 2560 (class 2606 OID 27474)
-- Name: OrderItemVoyageSegment orderitemvoyagesegment_customsdocumentstype_fk; Type: FK CONSTRAINT; Schema: protostellar; Owner: postgres
--

ALTER TABLE ONLY protostellar."OrderItemVoyageSegment"
    ADD CONSTRAINT orderitemvoyagesegment_customsdocumentstype_fk FOREIGN KEY ("CustomsDocumentsTypeID") REFERENCES protostellar."CustomsDocumentsType"("ID");


--
-- TOC entry 2561 (class 2606 OID 27479)
-- Name: OrderItemVoyageSegment orderitemvoyagesegment_customshandlingtype_fk; Type: FK CONSTRAINT; Schema: protostellar; Owner: postgres
--

ALTER TABLE ONLY protostellar."OrderItemVoyageSegment"
    ADD CONSTRAINT orderitemvoyagesegment_customshandlingtype_fk FOREIGN KEY ("CustomsHandlingTypeID") REFERENCES protostellar."CustomsHandlingType"("ID");


--
-- TOC entry 2562 (class 2606 OID 27484)
-- Name: OrderItemVoyageSegment orderitemvoyagesegment_customsprocedure_fk; Type: FK CONSTRAINT; Schema: protostellar; Owner: postgres
--

ALTER TABLE ONLY protostellar."OrderItemVoyageSegment"
    ADD CONSTRAINT orderitemvoyagesegment_customsprocedure_fk FOREIGN KEY ("CustomsProcedureID") REFERENCES protostellar."CustomsProcedure"("ID");


--
-- TOC entry 2563 (class 2606 OID 27489)
-- Name: OrderItemVoyageSegment orderitemvoyagesegment_customstariff_fk; Type: FK CONSTRAINT; Schema: protostellar; Owner: postgres
--

ALTER TABLE ONLY protostellar."OrderItemVoyageSegment"
    ADD CONSTRAINT orderitemvoyagesegment_customstariff_fk FOREIGN KEY ("CustomsTariffID") REFERENCES protostellar."CustomsTariff"("ID");


--
-- TOC entry 2564 (class 2606 OID 27494)
-- Name: OrderItemVoyageSegment orderitemvoyagesegment_loadingrequest_fk; Type: FK CONSTRAINT; Schema: protostellar; Owner: postgres
--

ALTER TABLE ONLY protostellar."OrderItemVoyageSegment"
    ADD CONSTRAINT orderitemvoyagesegment_loadingrequest_fk FOREIGN KEY ("LoadingRequestID") REFERENCES protostellar."LoadingRequest"("ID");


--
-- TOC entry 2565 (class 2606 OID 27499)
-- Name: OrderItemVoyageSegment orderitemvoyagesegment_order_fk; Type: FK CONSTRAINT; Schema: protostellar; Owner: postgres
--

ALTER TABLE ONLY protostellar."OrderItemVoyageSegment"
    ADD CONSTRAINT orderitemvoyagesegment_order_fk FOREIGN KEY ("OrderID") REFERENCES protostellar."Order"("ID");


--
-- TOC entry 2566 (class 2606 OID 27504)
-- Name: OrderItemVoyageSegment orderitemvoyagesegment_orderitem_fk; Type: FK CONSTRAINT; Schema: protostellar; Owner: postgres
--

ALTER TABLE ONLY protostellar."OrderItemVoyageSegment"
    ADD CONSTRAINT orderitemvoyagesegment_orderitem_fk FOREIGN KEY ("OrderItemID") REFERENCES protostellar."OrderItem"("ID");


--
-- TOC entry 2567 (class 2606 OID 27509)
-- Name: OrderItemVoyageSegment orderitemvoyagesegment_ordervoyagesegment_fk; Type: FK CONSTRAINT; Schema: protostellar; Owner: postgres
--

ALTER TABLE ONLY protostellar."OrderItemVoyageSegment"
    ADD CONSTRAINT orderitemvoyagesegment_ordervoyagesegment_fk FOREIGN KEY ("OrderVoyageSegmentID") REFERENCES protostellar."OrderVoyageSegment"("ID");


--
-- TOC entry 2568 (class 2606 OID 27514)
-- Name: OrderItemVoyageSegment orderitemvoyagesegment_packagecode_fk; Type: FK CONSTRAINT; Schema: protostellar; Owner: postgres
--

ALTER TABLE ONLY protostellar."OrderItemVoyageSegment"
    ADD CONSTRAINT orderitemvoyagesegment_packagecode_fk FOREIGN KEY ("PackageCodeID") REFERENCES protostellar."PackageCode"("ID");


--
-- TOC entry 2569 (class 2606 OID 27519)
-- Name: OrderItemVoyageSegment orderitemvoyagesegment_wagon_fk; Type: FK CONSTRAINT; Schema: protostellar; Owner: postgres
--

ALTER TABLE ONLY protostellar."OrderItemVoyageSegment"
    ADD CONSTRAINT orderitemvoyagesegment_wagon_fk FOREIGN KEY ("WagonID") REFERENCES protostellar."Wagon"("ID");


--
-- TOC entry 2570 (class 2606 OID 27524)
-- Name: OrderVoyageSegment ordervoyagesegment_1lstatuscode_fk; Type: FK CONSTRAINT; Schema: protostellar; Owner: postgres
--

ALTER TABLE ONLY protostellar."OrderVoyageSegment"
    ADD CONSTRAINT ordervoyagesegment_1lstatuscode_fk FOREIGN KEY ("1LStatusCodeID") REFERENCES protostellar."1LStatusCode"("ID");


--
-- TOC entry 2571 (class 2606 OID 27529)
-- Name: OrderVoyageSegment ordervoyagesegment_address_fk; Type: FK CONSTRAINT; Schema: protostellar; Owner: postgres
--

ALTER TABLE ONLY protostellar."OrderVoyageSegment"
    ADD CONSTRAINT ordervoyagesegment_address_fk FOREIGN KEY ("OceanCarrierAddressID") REFERENCES protostellar."Address"("ID");


--
-- TOC entry 2572 (class 2606 OID 27534)
-- Name: OrderVoyageSegment ordervoyagesegment_address_fk_1; Type: FK CONSTRAINT; Schema: protostellar; Owner: postgres
--

ALTER TABLE ONLY protostellar."OrderVoyageSegment"
    ADD CONSTRAINT ordervoyagesegment_address_fk_1 FOREIGN KEY ("TransportCompanyAddressID") REFERENCES protostellar."Address"("ID");


--
-- TOC entry 2573 (class 2606 OID 27539)
-- Name: OrderVoyageSegment ordervoyagesegment_location_fk; Type: FK CONSTRAINT; Schema: protostellar; Owner: postgres
--

ALTER TABLE ONLY protostellar."OrderVoyageSegment"
    ADD CONSTRAINT ordervoyagesegment_location_fk FOREIGN KEY ("StartLocationID") REFERENCES protostellar."Location"("ID");


--
-- TOC entry 2574 (class 2606 OID 27544)
-- Name: OrderVoyageSegment ordervoyagesegment_location_fk_1; Type: FK CONSTRAINT; Schema: protostellar; Owner: postgres
--

ALTER TABLE ONLY protostellar."OrderVoyageSegment"
    ADD CONSTRAINT ordervoyagesegment_location_fk_1 FOREIGN KEY ("DestinationLocationID") REFERENCES protostellar."Location"("ID");


--
-- TOC entry 2575 (class 2606 OID 27549)
-- Name: OrderVoyageSegment ordervoyagesegment_order_fk; Type: FK CONSTRAINT; Schema: protostellar; Owner: postgres
--

ALTER TABLE ONLY protostellar."OrderVoyageSegment"
    ADD CONSTRAINT ordervoyagesegment_order_fk FOREIGN KEY ("OrderID") REFERENCES protostellar."Order"("ID");


--
-- TOC entry 2576 (class 2606 OID 27554)
-- Name: OrderVoyageSegment ordervoyagesegment_railwayclass_fk; Type: FK CONSTRAINT; Schema: protostellar; Owner: postgres
--

ALTER TABLE ONLY protostellar."OrderVoyageSegment"
    ADD CONSTRAINT ordervoyagesegment_railwayclass_fk FOREIGN KEY ("RailwayClassID") REFERENCES protostellar."RailwayClass"("ID");


--
-- TOC entry 2577 (class 2606 OID 27559)
-- Name: OrderVoyageSegment ordervoyagesegment_ship_fk; Type: FK CONSTRAINT; Schema: protostellar; Owner: postgres
--

ALTER TABLE ONLY protostellar."OrderVoyageSegment"
    ADD CONSTRAINT ordervoyagesegment_ship_fk FOREIGN KEY ("FeederShipID") REFERENCES protostellar."Ship"("ID");


--
-- TOC entry 2578 (class 2606 OID 27564)
-- Name: OrderVoyageSegment ordervoyagesegment_ship_fk_1; Type: FK CONSTRAINT; Schema: protostellar; Owner: postgres
--

ALTER TABLE ONLY protostellar."OrderVoyageSegment"
    ADD CONSTRAINT ordervoyagesegment_ship_fk_1 FOREIGN KEY ("ShipID") REFERENCES protostellar."Ship"("ID");


--
-- TOC entry 2579 (class 2606 OID 27569)
-- Name: OrderVoyageSegment ordervoyagesegment_transportationmeans_fk; Type: FK CONSTRAINT; Schema: protostellar; Owner: postgres
--

ALTER TABLE ONLY protostellar."OrderVoyageSegment"
    ADD CONSTRAINT ordervoyagesegment_transportationmeans_fk FOREIGN KEY ("TransportationMeansID") REFERENCES protostellar."TransportationMeans"("ID");


--
-- TOC entry 2580 (class 2606 OID 27574)
-- Name: OrderVoyageSegment ordervoyagesegment_voyagetype_fk; Type: FK CONSTRAINT; Schema: protostellar; Owner: postgres
--

ALTER TABLE ONLY protostellar."OrderVoyageSegment"
    ADD CONSTRAINT ordervoyagesegment_voyagetype_fk FOREIGN KEY ("VoyageTypeID") REFERENCES protostellar."VoyageType"("ID");


--
-- TOC entry 2581 (class 2606 OID 27579)
-- Name: OrderVoyageSegmentTrain ordervoyagesegmenttrain__train__fk; Type: FK CONSTRAINT; Schema: protostellar; Owner: postgres
--

ALTER TABLE ONLY protostellar."OrderVoyageSegmentTrain"
    ADD CONSTRAINT ordervoyagesegmenttrain__train__fk FOREIGN KEY ("TrainID") REFERENCES protostellar."Train"("ID");


--
-- TOC entry 2583 (class 2606 OID 27584)
-- Name: TrainALConfigurations trainconfigurations_train_fk; Type: FK CONSTRAINT; Schema: protostellar; Owner: postgres
--

ALTER TABLE ONLY protostellar."TrainALConfigurations"
    ADD CONSTRAINT trainconfigurations_train_fk FOREIGN KEY ("TrainID") REFERENCES protostellar."TrainAL"("ID");


--
-- TOC entry 2584 (class 2606 OID 27589)
-- Name: TransportDirection_PartnerSystem transportdirection_partnersystem_1lpartnersystem_fk; Type: FK CONSTRAINT; Schema: protostellar; Owner: postgres
--

ALTER TABLE ONLY protostellar."TransportDirection_PartnerSystem"
    ADD CONSTRAINT transportdirection_partnersystem_1lpartnersystem_fk FOREIGN KEY ("PartnerSystemID") REFERENCES protostellar."1LPartnerSystem"("ID");


--
-- TOC entry 2585 (class 2606 OID 27594)
-- Name: TransportDirection_PartnerSystem transportdirection_partnersystem_transportdirection_fk; Type: FK CONSTRAINT; Schema: protostellar; Owner: postgres
--

ALTER TABLE ONLY protostellar."TransportDirection_PartnerSystem"
    ADD CONSTRAINT transportdirection_partnersystem_transportdirection_fk FOREIGN KEY ("TransportDirectionID") REFERENCES protostellar."TransportDirection"("ID");


-- Completed on 2020-06-04 15:45:02 CEST

--
-- PostgreSQL database dump complete
--
