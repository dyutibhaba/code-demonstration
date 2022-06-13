INSERT INTO track_predict."UserGroup"("Name") VALUES ('Track and Predict Demo User Group');

INSERT INTO track_predict."TrackerToUserGroup" ("TrackerId", "UserGroupId")
SELECT '285890770' AS "TrackerId", "ID" AS "UserGroupId" FROM track_predict."UserGroup"
WHERE "UserGroup"."Name" = 'Track and Predict Demo User Group';

INSERT INTO track_predict."TrackerToUserGroup" ("TrackerId", "UserGroupId")
SELECT '285890970' AS "TrackerId", "ID" AS "UserGroupId" FROM track_predict."UserGroup"
WHERE "UserGroup"."Name" = 'Track and Predict Demo User Group';
