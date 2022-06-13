CREATE TABLE IF NOT EXISTS track_predict."Asset" (
     "ID" UUID DEFAULT public.uuid_generate_v4() PRIMARY KEY,
     "Name" character varying(250)
);

CREATE TABLE IF NOT EXISTS track_predict."TrainNumberToAsset" (
      "TrainNumberId" UUID NOT NULL,
      "AssetId" UUID NOT NULL,
      CONSTRAINT train_number_fkey FOREIGN KEY ("TrainNumberId") REFERENCES track_predict."TrainNumber"("Id"),
      CONSTRAINT asset_fkey FOREIGN KEY ("AssetId") REFERENCES track_predict."Asset"("ID"),
      PRIMARY KEY ("TrainNumberId", "AssetId")
);

CREATE TABLE IF NOT EXISTS track_predict."TrackerToAsset" (
      "TrackerId" character varying(250) NOT NULL,
      "AssetId" UUID NOT NULL,
      CONSTRAINT tracker_fkey FOREIGN KEY ("TrackerId") REFERENCES track_predict."Tracker"("ID"),
      CONSTRAINT asset_fkey FOREIGN KEY ("AssetId") REFERENCES track_predict."Asset"("ID"),
      PRIMARY KEY ("TrackerId", "AssetId")
);
