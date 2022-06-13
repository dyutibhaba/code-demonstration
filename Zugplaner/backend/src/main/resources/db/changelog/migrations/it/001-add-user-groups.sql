-- One User Group with no plan, no recipient ID
INSERT INTO protostellar."UserGroup"(
	"ID", "Name")
	VALUES ('4cc074f6-3fdc-401b-9ab2-83222bcebed1'::uuid, 'User group with no plans');

-- One User Group with Premium for Maintenance, no recipient ID
INSERT INTO protostellar."UserGroup"(
	"ID", "Name")
	VALUES ('d360007f-3f79-43ee-8c19-70345432bbab'::uuid, 'User group with maintenance as premium');

-- One User Group with Premium for Delay Manager, no recipient ID
INSERT INTO protostellar."UserGroup"(
	"ID", "Name")
	VALUES ('75b485fc-c007-4377-9223-bb6aad3c20ae'::uuid, 'User group with delay manager as premium');

INSERT INTO protostellar."PayingPlan"(
    "UserGroupId", "FeatureRef", "PlanRef")
VALUES ('d360007f-3f79-43ee-8c19-70345432bbab'::uuid, 1, 2);

INSERT INTO protostellar."PayingPlan"(
    "UserGroupId", "FeatureRef", "PlanRef")
VALUES ('75b485fc-c007-4377-9223-bb6aad3c20ae'::uuid, 2, 2);

-- One User Group with Premium for Maintenance, has a recipient ID
INSERT INTO protostellar."UserGroup"(
    "ID", "Name", "TSI_RecipientCode")
    VALUES ('443a64c2-a284-401b-a777-65500eaf38b8'::uuid, 'User group with recipient id', 'rec-id-000');
