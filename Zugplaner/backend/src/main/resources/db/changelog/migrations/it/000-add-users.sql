-- User with no roles at all: john.doe@thalesdigital.io aka John Doe
INSERT INTO protostellar."1LUser"
    VALUES (
        '17f96c0f-6ce2-475a-b806-aff7564d6473'::uuid,
        'john.doe@thalesdigital.io',
        'JohnDoeAzureAccountId',
        'John Doe',
        DATE '1975-04-02',
        'Integration Tests',
        'System user',
        DATE '1975-04-02',
        'Integration Tests',
        'System user'
    );

-- User with admin role for T&P: jane.doe@thalesdigital.io aka Jane Doe
INSERT INTO protostellar."1LUser"
    VALUES (
        '2eec38c7-d10b-4df8-b984-4d92f6cef250'::uuid,
        'jane.doe@thalesdigital.io',
        'JaneDoeAzureAccountId',
        'Jane Doe',
        DATE '1980-10-30',
        'Integration Tests',
        'System user',
        DATE '1980-10-30',
        'Integration Tests',
        'System user'
    );

INSERT INTO protostellar."UserToModuleRole"
    VALUES (
        '2eec38c7-d10b-4df8-b984-4d92f6cef250'::uuid,
        1,
        1
    );
