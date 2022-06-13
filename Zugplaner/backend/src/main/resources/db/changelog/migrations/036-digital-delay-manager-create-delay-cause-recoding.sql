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

-- Table: digital_delay_manager.DelayCauseRecoding

-- DROP TABLE digital_delay_manager."DelayCauseRecoding";

CREATE TABLE IF NOT EXISTS digital_delay_manager."DelayCauseRecoding"
(
    "Id" text COLLATE pg_catalog."default" PRIMARY KEY NOT NULL,
    "Comment" text COLLATE pg_catalog."default",
    "ReasonCode" text COLLATE pg_catalog."default" NOT NULL,
    "Status" text COLLATE pg_catalog."default" NOT NULL
);
