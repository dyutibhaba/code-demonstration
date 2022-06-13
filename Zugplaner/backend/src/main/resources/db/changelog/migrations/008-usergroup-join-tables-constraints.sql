ALTER TABLE ONLY track_predict."UserToUserGroup"
    ADD CONSTRAINT unique_user_usergroup UNIQUE ("UserId", "UserGroupId");

ALTER TABLE ONLY track_predict."TrackerToUserGroup"
    ADD CONSTRAINT unique_tracker_usergroup UNIQUE ("TrackerId", "UserGroupId");

ALTER TABLE ONLY track_predict."UserGroup"
    ALTER COLUMN "ID" SET DEFAULT public.uuid_generate_v4();

ALTER TABLE ONLY track_predict."UserGroup"
    ADD COLUMN "Name" character varying(255);
