INSERT INTO track_predict."Asset"(
    "ID", "Name")
VALUES ('a8081e1f-0f85-450d-97f4-c64174a5676f', 'Asset for test');

INSERT INTO track_predict."Tracker"(
    "ID", "Asset", "EtaVariation", "Eta")
VALUES ('296697270', 'Test asset 1', 0, '2020-11-13 20:43:00+00');

INSERT INTO track_predict."TrackerToAsset"(
    "TrackerId", "AssetId")
VALUES ('296697270', 'a8081e1f-0f85-450d-97f4-c64174a5676f');

INSERT INTO track_predict."TrackerDataHistory"(
    "TrackerId", "PositionGpsLatitude", "PositionGpsLongitude", "PositionAddress", "BatteryPercentage", "BatteryVoltage", "SpeedKph", "LastLocationDateTime", "SysInsertTS", "SysInsertPrg", "SysInsertUserID", "SysUpdateTS", "SysUpdatePrg", "SysUpdateUserID", "Description")
VALUES ('296697270', 50.081163, 8.992625, 'Address', 100, 4.18, 0, '2019-10-28 21:39:22', '2020-09-01 10:00:03.787529', 'Track&Predict UI', null, null, null, null, null);

INSERT INTO track_predict."TrainLastPosition"(
    "TrainNumber", "CountryCode", "State", "RailstationNumber", "RailstationCode", "CountryName", "OperatingRegionName", "RailstationName", "Street", "ZIPCode", "GPSLongitude", "GPSLatitude", "SYS_InsertTS", "SYS_InsertPrg", "SYS_UpdateTS", "SYS_UpdatePrg", "Recipient")
VALUES (84606, 'DE', 'Bayern', 5813, 'NSLZ', 'Germany', 'Bamberg', 'Selbitz', 'Bahnhofstr. 36', 95152, 11.74812, 50.31628, '2021-03-22 12:45:17.561', '31812_INTFTSI_StoreTrainLastPositionInTrackPredict', '2021-03-22 12:45:17.561', '31812_INTFTSI_StoreTrainLastPositionInTrackPredict', 3164);

INSERT INTO track_predict."TrainETADelay"(
    "TrainNumber", "DeviationInMinutes", "ETA", "SYS_InsertPrg", "SYS_InsertTS", "SYS_UpdatePrg", "SYS_UpdateTS", "Recipient")
VALUES (84606, 10, '2021-03-22 11:00:00', '31811_INTFTSI_StoreTrainETADelayCurrInTrackPredict', '2021-03-22 12:49:08.782', '31811_INTFTSI_StoreTrainETADelayCurrInTrackPredict', '2021-03-22 16:57:30.423', 3164);

INSERT INTO track_predict."TrainNumber"(
    "Id", "Value")
VALUES ('b200122c-9fa7-46ca-9a0d-00ceef357aed', 84606);

INSERT INTO track_predict."TrainNumberToAsset"(
    "TrainNumberId", "AssetId")
VALUES ('b200122c-9fa7-46ca-9a0d-00ceef357aed', 'a8081e1f-0f85-450d-97f4-c64174a5676f');

INSERT INTO protostellar."UserGroup"(
    "ID", "Name", "InfraOp_CustomerNo", "TSI_RecipientCode")
VALUES ('d931815b-f83f-468c-a8e3-634bb2acf5cb', 'User Group 1', 'InfraOp', 'Recipient');

INSERT INTO track_predict."AssetToUserGroup"(
    "AssetId", "UserGroupId")
VALUES ('a8081e1f-0f85-450d-97f4-c64174a5676f', 'd931815b-f83f-468c-a8e3-634bb2acf5cb');
