CREATE OR REPLACE FUNCTION migrate_asset() RETURNS SETOF track_predict."Tracker" AS
$BODY$
DECLARE
    r       track_predict."Tracker"%rowtype;
    assetId uuid;
BEGIN
    FOR r IN
        SELECT * FROM track_predict."Tracker"
        LOOP
            INSERT INTO track_predict."Asset" ("Name") VALUES (r."Asset") RETURNING "ID" INTO assetId;
            INSERT INTO track_predict."TrackerToAsset"("TrackerId", "AssetId") VALUES (r."ID", assetId);
            RETURN NEXT r; -- return current row of SELECT
        END LOOP;
    RETURN;
END;
$BODY$
    LANGUAGE plpgsql;

SELECT *
FROM migrate_asset();


