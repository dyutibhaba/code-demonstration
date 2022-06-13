DROP TABLE IF EXISTS track_predict.tracker_unit_data_history;

DROP TABLE track_predict.tracker_user;

DROP TABLE track_predict.tracker;

--
-- TOC entry 277 (class 1259 OID 29781)
-- Name: tracker_user; Type: TABLE; Schema: track_predict; Owner: postgres
--

CREATE TABLE IF NOT EXISTS track_predict.tracker_user (
    tracker_id character varying NOT NULL,
    user_email character varying NOT NULL
);

CREATE TABLE IF NOT EXISTS track_predict.tracker(
    id character varying(255) NOT NULL UNIQUE PRIMARY KEY,
    address_id bigint NOT NULL
);

--
-- TOC entry 2331 (class 2606 OID 29788)
-- Name: tracker_user tracker_user_pkey; Type: CONSTRAINT; Schema: track_predict; Owner: postgres
--

ALTER TABLE ONLY track_predict.tracker_user
    ADD CONSTRAINT tracker_user_pkey PRIMARY KEY (tracker_id, user_email);


ALTER TABLE ONLY track_predict.tracker
    ADD CONSTRAINT address_fkey FOREIGN KEY (address_id) REFERENCES protostellar."Address"("ID");

CREATE TABLE IF NOT EXISTS track_predict.tracker_unit_data_history (
    tracker_unit_id character varying (255),
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

-- ALTER TABLE track_predict.tracker_unit_data_history OWNER TO postgres;

ALTER TABLE ONLY track_predict.tracker_unit_data_history
    ADD CONSTRAINT tracker_unit_id_fk FOREIGN KEY (tracker_unit_id) REFERENCES track_predict.tracker(id);

ALTER TABLE ONLY track_predict.tracker_user
    DROP CONSTRAINT IF EXISTS traker_fkey;

ALTER TABLE ONLY track_predict.tracker_user
    ADD CONSTRAINT tracker_fk FOREIGN KEY (tracker_id) REFERENCES track_predict.tracker(id);
