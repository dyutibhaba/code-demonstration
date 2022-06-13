CREATE OR REPLACE FUNCTION migrate_asset_to_train_numbers() RETURNS SETOF track_predict."TrainNumberToTracker" AS
$BODY$
DECLARE
    r track_predict."TrainNumberToTracker"%rowtype;
    assetId uuid;
BEGIN
    FOR r IN
        SELECT * FROM track_predict."TrainNumberToTracker"
        LOOP
		  INSERT INTO track_predict."TrainNumberToAsset"("TrainNumberId", "AssetId") VALUES (r."TrainNumberId", (
				SELECT "AssetId" as assetId FROM track_predict."TrackerToAsset" WHERE "TrackerId" = r."TrackerId"
			));
            RETURN NEXT r;
        END LOOP;
    RETURN;
END;
$BODY$
    LANGUAGE plpgsql;

SELECT * FROM migrate_asset_to_train_numbers();
