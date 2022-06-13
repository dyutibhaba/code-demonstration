--
-- PostgreSQL database dump
--

-- Dumped from database version 9.6.13
-- Dumped by pg_dump version 11.1

-- Started on 2020-06-04 15:45:00 CEST

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET client_min_messages = warning;
SET row_security = off;



--
-- TOC entry 8 (class 2615 OID 25527)
-- Name: track_predict; Type: SCHEMA; Schema: -; Owner: postgres
--

CREATE SCHEMA IF NOT EXISTS track_predict;


-- ALTER SCHEMA track_predict OWNER TO postgres;

SET default_tablespace = '';

SET default_with_oids = false;

--
-- TOC entry 189 (class 1259 OID 25531)
-- Name: tracker; Type: TABLE; Schema: track_predict; Owner: postgres
--

CREATE TABLE IF NOT EXISTS track_predict.tracker (
    id character varying(255) NOT NULL,
    address_id character varying
);


-- ALTER TABLE track_predict.tracker OWNER TO postgres;

--
-- TOC entry 284 (class 1259 OID 42047)
-- Name: tracker_unit_data_history; Type: TABLE; Schema: track_predict; Owner: postgres
--

CREATE TABLE IF NOT EXISTS track_predict."tracker_unit_data_history" (
    tracker_unit_id bigint,
    position_gps_latitude real,
    position_gps_longitude real,
    position_address character varying(255),
    battery_percentage integer,
    battery_voltage real,
    speed_kph real,
    last_location_date_time timestamp without time zone NOT NULL,
    system_insert_timestamp timestamp without time zone,
    system_insert_program character varying(50)
);


-- ALTER TABLE track_predict."tracker_unit_data_history" OWNER TO postgres;

--
-- TOC entry 191 (class 1259 OID 25551)
-- Name: User; Type: TABLE; Schema: track_predict; Owner: postgres
--

CREATE TABLE IF NOT EXISTS track_predict.user (
    email character varying NOT NULL PRIMARY KEY,
    account_id character varying ,
    displayed_name character varying
);

--
-- TOC entry 277 (class 1259 OID 29781)
-- Name: tracker_user; Type: TABLE; Schema: track_predict; Owner: postgres
--

CREATE TABLE IF NOT EXISTS track_predict.tracker_user (
    tracker_id character varying NOT NULL,
    user_email character varying NOT NULL
);

--
-- TOC entry 2354 (class 2606 OID 42158)
-- Name: tracker_unit_data_history tracker_unit_id; Type: FK CONSTRAINT; Schema: track_predict; Owner: postgres
--

CREATE TABLE IF NOT EXISTS track_predict."TrackerUnit"(
    id bigint UNIQUE
);

--
-- TOC entry 4 (class 2615 OID 26698)
-- Name: protostellar; Type: SCHEMA; Schema: -; Owner: postgres
--

CREATE SCHEMA IF NOT EXISTS protostellar;


-- ALTER SCHEMA protostellar OWNER TO postgres;

SET default_tablespace = '';

SET default_with_oids = false;
