--
-- PostgreSQL database dump
--

-- Dumped from database version 11.6
-- Dumped by pg_dump version 13.2

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET xmloption = content;
SET client_min_messages = warning;
SET row_security = off;

CREATE SCHEMA IF NOT EXISTS digital_delay_manager;

SET default_tablespace = '';

SET default_with_oids = false;

-- Table: digital_delay_manager.DelayCauseTrainRunsHist

-- DROP TABLE digital_delay_manager."DelayCauseTrainRunsHist";

CREATE TABLE IF NOT EXISTS digital_delay_manager."DelayCauseTrainRunsHist"
(
    "MessageDateTime" timestamp without time zone,
    "Sender" text COLLATE pg_catalog."default",
    "Recipient" text COLLATE pg_catalog."default",
    "OperationalTrainNumber" text COLLATE pg_catalog."default",
    "ScheduledTimeAtHandover" timestamp without time zone,
    "CustomerID" uuid,
    "MessageStatus" text COLLATE pg_catalog."default",
    "TrainLocationReportCountryCodeISO" text COLLATE pg_catalog."default",
    "TrainLocationReportLocationPrimaryCode" text COLLATE pg_catalog."default",
    "TrainLocationReportPrimaryLocationName" text COLLATE pg_catalog."default",
    "TrainLocationReportLocationSubsidiaryCode" text COLLATE pg_catalog."default",
    "TrainLocationReportAllocationCompany" text COLLATE pg_catalog."default",
    "TrainLocationReportLocationDateTime" timestamp without time zone,
    "TrainLocationReportTrainLocationStatus" text COLLATE pg_catalog."default",
    "TrainLocationReportAgainstBooked" text COLLATE pg_catalog."default",
    "TransferPointCountryCodeISO" text COLLATE pg_catalog."default",
    "TransferPointLocationPrimaryCode" text COLLATE pg_catalog."default",
    "TransferPointPrimaryLocationName" text COLLATE pg_catalog."default",
    "TransferPointLocationSubsidiaryCode" text COLLATE pg_catalog."default",
    "TransferPointAllocationCompany" text COLLATE pg_catalog."default",
    "TrainNumber" text COLLATE pg_catalog."default",
    "Network_Operator_DelayCause " text COLLATE pg_catalog."default",
    "Network_Operator_Additional_Delay_Minutes" numeric(12,2),
    "PenaltyTotal" double precision,
    "TrainLocationStatusDesc" text COLLATE pg_catalog."default",
    "XLSFilename" text COLLATE pg_catalog."default",
    "XLSSheetname" text COLLATE pg_catalog."default",
    "Seq" double precision,
    "SYS_InsertPrg" text COLLATE pg_catalog."default",
    "SYS_InsertTS" timestamp without time zone,
    "SYS_UpdatePrg" text COLLATE pg_catalog."default",
    "SYS_UpdateTS" timestamp without time zone,
    "ReportDate" timestamp without time zone,
    "IncidentID2" text COLLATE pg_catalog."default"
);

-- ALTER TABLE digital_delay_manager."DelayCauseTrainRunsHist" OWNER to postgres;
-- Index: idx_DelayCauseOverviewHist_lookup

-- DROP INDEX digital_delay_manager."idx_DelayCauseOverviewHist_lookup";

CREATE INDEX IF NOT EXISTS "idx_DelayCauseOverviewHist_lookup"
    ON digital_delay_manager."DelayCauseTrainRunsHist" USING btree
        ("MessageDateTime" ASC NULLS LAST, "Sender" COLLATE pg_catalog."default" ASC NULLS LAST, "Recipient" COLLATE pg_catalog."default" ASC NULLS LAST, "OperationalTrainNumber" COLLATE pg_catalog."default" ASC NULLS LAST, "ScheduledTimeAtHandover" ASC NULLS LAST, "CustomerID" ASC NULLS LAST)
    TABLESPACE pg_default;
--
-- Name: DelayCauseTrainRunsHist; Type: TABLE; Schema: digital_delay_manager; Owner: postgres
--

-- Table: digital_delay_manager.DelayCauseOverviewHist

-- DROP TABLE digital_delay_manager."DelayCauseOverviewHist";

CREATE TABLE IF NOT EXISTS digital_delay_manager."DelayCauseOverviewHist"
(
    "Region" text COLLATE pg_catalog."default",
    "EventTime" timestamp without time zone,
    "TrainNumber" text COLLATE pg_catalog."default",
    "WayPointCode" text COLLATE pg_catalog."default",
    "InfraOpReportType" text COLLATE pg_catalog."default",
    "InfraOpCustomerCode" text COLLATE pg_catalog."default",
    "InfraOpAnalysisDate" text COLLATE pg_catalog."default",
    "TSI_RecipientCode" text COLLATE pg_catalog."default",
    "CustomerID" text COLLATE pg_catalog."default",
    "NetworkOperatorCode" text COLLATE pg_catalog."default",
    "Comment" text COLLATE pg_catalog."default",
    "PenaltyChargeTo" text COLLATE pg_catalog."default",
    "PenaltyTotal" double precision,
    "XLSFilename" text COLLATE pg_catalog."default",
    "XLSSheetname" text COLLATE pg_catalog."default",
    "Seq" double precision,
    "SYS_InsertPrg" text COLLATE pg_catalog."default",
    "SYS_InsertTS" timestamp without time zone,
    "SYS_UpdatePrg" text COLLATE pg_catalog."default",
    "SYS_UpdateTS" timestamp without time zone,
    "ReportDate" timestamp without time zone,
    "IncidentID2" text COLLATE pg_catalog."default",
    "ReasonCode" text COLLATE pg_catalog."default",
    "ReasonCategoryCode" text COLLATE pg_catalog."default",
    "PunctualityRelevant" text COLLATE pg_catalog."default",
    "InfraOpReportName" text COLLATE pg_catalog."default",
    "InfraOpReference" text COLLATE pg_catalog."default",
    "InfraOpThreshold" text COLLATE pg_catalog."default",
    "WayPointName" text COLLATE pg_catalog."default",
    "ReasonDescription" text COLLATE pg_catalog."default",
    "InfraOpAdditionallyCausedDelayMinutes" numeric(12,2),
    "PenaltyAmountPunctualitySensitive" numeric(12,2),
    "PenaltyAmountNotPunctualitySensitive" numeric(12,2)
);

-- ALTER TABLE digital_delay_manager."DelayCauseOverviewHist" OWNER to postgres;

-- Index: idx_DelayCauseTrainRunsHist_lookup

-- DROP INDEX digital_delay_manager."idx_DelayCauseTrainRunsHist_lookup";

CREATE INDEX IF NOT EXISTS "idx_DelayCauseTrainRunsHist_lookup"
    ON digital_delay_manager."DelayCauseOverviewHist" USING btree
        ("Region" COLLATE pg_catalog."default" ASC NULLS LAST, "EventTime" ASC NULLS LAST, "TrainNumber" COLLATE pg_catalog."default" ASC NULLS LAST, "WayPointCode" COLLATE pg_catalog."default" ASC NULLS LAST, "InfraOpReportType" COLLATE pg_catalog."default" ASC NULLS LAST, "InfraOpCustomerCode" COLLATE pg_catalog."default" ASC NULLS LAST, "InfraOpAnalysisDate" COLLATE pg_catalog."default" ASC NULLS LAST, "TSI_RecipientCode" COLLATE pg_catalog."default" ASC NULLS LAST, "CustomerID" COLLATE pg_catalog."default" ASC NULLS LAST, "NetworkOperatorCode" COLLATE pg_catalog."default" ASC NULLS LAST)
    TABLESPACE pg_default;
