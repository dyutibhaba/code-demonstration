CREATE TABLE IF NOT EXISTS protostellar."UserToModuleRole"
(
    "userId"   UUID NOT NULL,
    "moduleId" INT  NOT NULL,
    "roleId"   INT  NOT NULL,
    PRIMARY KEY ("userId", "moduleId", "roleId")
);

INSERT INTO protostellar."UserToModuleRole"("userId", "moduleId", "roleId")
SELECT "ID", 1 as moduleId, 1 as roleId
from protostellar."1LUser";