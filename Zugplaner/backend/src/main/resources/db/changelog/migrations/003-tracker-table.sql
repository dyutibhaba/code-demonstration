DROP TABLE IF EXISTS track_predict."TrackerUnit";
DROP TABLE IF EXISTS track_predict.tracker_user;
DROP TABLE IF EXISTS track_predict.user;
DROP TABLE IF EXISTS track_predict."tracker_unit_data_history";
DROP TABLE IF EXISTS track_predict."tracker";

CREATE TABLE track_predict."Tracker" (
    "ID" character varying PRIMARY KEY
);

CREATE TABLE track_predict."UserGroup" (
    "ID" UUID PRIMARY KEY
);

INSERT INTO track_predict."Tracker" VALUES
('285890570'),
('285890670'),
('285890770'),
('285890870'),
('285890970');
