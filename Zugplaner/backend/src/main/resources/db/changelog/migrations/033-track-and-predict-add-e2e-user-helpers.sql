INSERT INTO protostellar."1LUser"
    VALUES (
        'be527f12-0a80-4f72-a5ce-9320f7220176'::uuid,
        'svc-protostellar-user1@tdfmvpqual.onmicrosoft.com',
        '7ccba34c-4949-4f4d-b488-f80f251cbda3',
        'SVC PROTOSTELLAR USER1',
        CURRENT_TIMESTAMP,
        'e2e',
        'svc-protostellar-user1@tdfmvpqual.onmicrosoft.com',
        CURRENT_TIMESTAMP,
        'e2e',
        'svc-protostellar-user1@tdfmvpqual.onmicrosoft.com'
    ) ON CONFLICT("ID") DO NOTHING;

INSERT INTO protostellar."UserToModuleRole"
    VALUES (
        'be527f12-0a80-4f72-a5ce-9320f7220176'::uuid,
        1,
        1
    );

INSERT INTO track_predict."UserToUserGroup"
   VALUES (
     'be527f12-0a80-4f72-a5ce-9320f7220176'::uuid,
     (SELECT "ID" FROM track_predict."UserGroup" WHERE "Name" = 'Track and Predict Demo User Group')::uuid
   );

