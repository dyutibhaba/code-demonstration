CREATE TABLE track_predict."AssetToUserGroup"
(
    "AssetId"     UUID NOT NULL,
    "UserGroupId" UUID NOT NULL,
    CONSTRAINT asset_fkey FOREIGN KEY ("AssetId") REFERENCES track_predict."Asset"("ID"),
    CONSTRAINT user_group_fkey FOREIGN KEY ("UserGroupId") REFERENCES track_predict."UserGroup"("ID"),
    PRIMARY KEY ("AssetId", "UserGroupId")
);
