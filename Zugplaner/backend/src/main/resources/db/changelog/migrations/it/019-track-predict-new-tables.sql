INSERT INTO track_predict."Asset" ("ID", "Name") VALUES ('7f32ef3d-3a96-4b0b-810a-a8bc82678497'::uuid, 'Big Red Container') ON CONFLICT("ID") DO NOTHING;;
INSERT INTO track_predict."Asset" ("ID", "Name") VALUES ('66e52d90-fab0-4dfd-bda9-037218c3c199'::uuid, 'Big Red Container') ON CONFLICT("ID") DO NOTHING;
INSERT INTO track_predict."Asset" ("ID", "Name") VALUES ('ce83e7f2-718c-4af0-880b-edea3585d693'::uuid, 'Big Red Container') ON CONFLICT("ID") DO NOTHING;
INSERT INTO track_predict."Asset" ("ID", "Name") VALUES ('ce83e7f2-718c-4af0-880b-edea3585d6fa'::uuid, 'Big Red Container') ON CONFLICT("ID") DO NOTHING;
INSERT INTO track_predict."Asset" ("ID", "Name") VALUES ('a8081e1f-0f85-450d-97f4-c64174a56741'::uuid, 'Big Red Container') ON CONFLICT("ID") DO NOTHING;
INSERT INTO track_predict."Asset" ("ID", "Name") VALUES ('82b33988-63d1-4b64-a1b0-fdd0d1b4e863'::uuid, 'Big Red Container') ON CONFLICT("ID") DO NOTHING;
INSERT INTO track_predict."Asset" ("ID", "Name") VALUES ('3e0cf1ee-b906-4b71-ba3d-cf0d8c7e3380'::uuid, 'Big Red Container') ON CONFLICT("ID") DO NOTHING;


INSERT INTO track_predict."F_AssetStats_Day"
("AssetId", "Date", "DistanceKM")
VALUES('7f32ef3d-3a96-4b0b-810a-a8bc82678497','2020-02-20',0.19780193098548024),
      ('7f32ef3d-3a96-4b0b-810a-a8bc82678497','2021-05-17',2.244087776452983),
      ('7f32ef3d-3a96-4b0b-810a-a8bc82678497', '2020-11-22', 55.73540784032639) ON CONFLICT("AssetId", "Date") DO UPDATE SET "Date"=excluded."Date";

INSERT INTO track_predict."F_AssetStats_Month"
("AssetId", "Year", "Month", "DistanceKM")
VALUES('ce83e7f2-718c-4af0-880b-edea3585d6fa', 2020.0, 9.0, 0.19780193098548024),
      ('ce83e7f2-718c-4af0-880b-edea3585d6fa', 2020.0, 6.0, 0.19780193098548024),
      ('ce83e7f2-718c-4af0-880b-edea3585d6fa', 2021.0, 2.0, 223.455434252117),
      ('ce83e7f2-718c-4af0-880b-edea3585d6fa', 2022.0, 1.0, 0.9448030935338056) ON CONFLICT("AssetId", "Year", "Month") DO UPDATE SET "Month"=excluded."Month", "Year"=excluded."Year";
