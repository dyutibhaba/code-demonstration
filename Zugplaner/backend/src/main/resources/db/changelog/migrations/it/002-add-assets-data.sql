-- Big Red Container asset (one Train Number, zero trackers)
INSERT INTO track_predict."Asset" (
    "ID", "Name"
) VALUES (
    '938ed385-6f1d-4a18-8982-35dc4f4ebb45'::uuid, 'Big Red Container'
);

INSERT INTO track_predict."TrainNumber" (
    "Id", "Value"
) VALUES (
    '80220529-f4e2-44f4-82f6-e2cf8b62acb8'::uuid, 'big-red-train-000'
);

INSERT INTO track_predict."TrainNumberToAsset" (
    "TrainNumberId", "AssetId"
) VALUES (
    '80220529-f4e2-44f4-82f6-e2cf8b62acb8'::uuid, '938ed385-6f1d-4a18-8982-35dc4f4ebb45'::uuid
);

INSERT INTO track_predict."AssetToUserGroup" (
    "AssetId", "UserGroupId"
) VALUES (
    '938ed385-6f1d-4a18-8982-35dc4f4ebb45'::uuid, '443a64c2-a284-401b-a777-65500eaf38b8'::uuid
);


-- Big Blue Container asset (two Train Numbers, zero trackers, belongs to user group recipient 000)
INSERT INTO track_predict."Asset" (
    "ID", "Name"
) VALUES (
    'b7e26eab-f13f-419e-b046-80d7cdf2c7f9'::uuid, 'Big Blue Container'
);

INSERT INTO track_predict."TrainNumber" (
    "Id", "Value"
) VALUES (
    '07134d17-e2a9-4cdb-92be-184aefe40754'::uuid, 'big-blue-train-000'
);

INSERT INTO track_predict."TrainNumber" (
    "Id", "Value"
) VALUES (
    '8a6b3cda-9274-4435-a978-dbf21da18404'::uuid, 'big-blue-train-001'
);

INSERT INTO track_predict."TrainNumberToAsset" (
    "TrainNumberId", "AssetId"
) VALUES (
    '07134d17-e2a9-4cdb-92be-184aefe40754'::uuid, 'b7e26eab-f13f-419e-b046-80d7cdf2c7f9'::uuid
);

INSERT INTO track_predict."TrainNumberToAsset" (
    "TrainNumberId", "AssetId"
) VALUES (
    '8a6b3cda-9274-4435-a978-dbf21da18404'::uuid, 'b7e26eab-f13f-419e-b046-80d7cdf2c7f9'::uuid
);

INSERT INTO track_predict."AssetToUserGroup" (
    "AssetId", "UserGroupId"
) VALUES (
    'b7e26eab-f13f-419e-b046-80d7cdf2c7f9'::uuid, '443a64c2-a284-401b-a777-65500eaf38b8'::uuid
);
