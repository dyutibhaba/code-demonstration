CREATE TABLE IF NOT EXISTS protostellar."PayingPlan"
(
    "UserGroupId" UUID NOT NULL,
    "FeatureRef" INT NOT NULL,
    "PlanRef" INT NOT NULL,
    CONSTRAINT usergroupid_fkey FOREIGN KEY ("UserGroupId") REFERENCES track_predict."UserGroup"("ID"),
    PRIMARY KEY ("UserGroupId", "FeatureRef")
);
