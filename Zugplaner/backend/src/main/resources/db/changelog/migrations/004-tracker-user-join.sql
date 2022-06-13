CREATE TABLE track_predict."TrackerToUserGroup" (
    "TrackerId" character varying NOT NULL,
    "UserGroupId" UUID NOT NULL,
    CONSTRAINT tracker_fkey FOREIGN KEY ("TrackerId") REFERENCES track_predict."Tracker"("ID"),
    CONSTRAINT user_group_fkey FOREIGN KEY ("UserGroupId") REFERENCES track_predict."UserGroup"("ID")
);

CREATE TABLE track_predict."UserToUserGroup" (
    "UserId" UUID NOT NULL,
    "UserGroupId" UUID NOT NULL,
    CONSTRAINT user_fkey FOREIGN KEY ("UserId") REFERENCES protostellar."1LUser"("ID"),
    CONSTRAINT user_group_fkey FOREIGN KEY ("UserGroupId") REFERENCES track_predict."UserGroup"("ID")
);
