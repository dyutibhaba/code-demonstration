CREATE OR REPLACE FUNCTION migrate_asset_to_usergroup() RETURNS SETOF track_predict."TrackerToUserGroup" AS
$BODY$
DECLARE
    r track_predict."TrackerToUserGroup"%rowtype;
    assetId uuid;
BEGIN
    FOR r IN
        SELECT * FROM track_predict."TrackerToUserGroup"
        LOOP
		  INSERT INTO track_predict."AssetToUserGroup"("UserGroupId", "AssetId") VALUES (r."UserGroupId", (
				SELECT "AssetId" as assetId FROM track_predict."TrackerToAsset" WHERE "TrackerId" = r."TrackerId"
			));
            RETURN NEXT r;
        END LOOP;
    RETURN;
END;
$BODY$
    LANGUAGE plpgsql;

SELECT * FROM migrate_asset_to_usergroup();
