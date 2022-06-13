
CREATE TABLE protostellar."1LPaymentPlan"
(
     "ID" INT UNIQUE,
     "Name" character varying(250)
);

CREATE TABLE IF NOT EXISTS protostellar."1LModule"
(    "ID" INT UNIQUE,
     "Name" character varying(250)
);
INSERT INTO protostellar."1LPaymentPlan"
      ("ID", "Name")
VALUES(1, 'FREE'),
      (2, 'PREMIUM');

INSERT INTO protostellar."1LModule"
      ("ID", "Name")
VALUES(0, 'UNASSIGNED'),
      (1, 'TRACK_AND_PREDICT'),
      (2, 'DIGITAL_CHECKIN'),
      (3, 'DELAY_MANAGER'),
      (4, 'MARKETPLACE');

-- protostellar."PayingPlan" foreign keys

ALTER TABLE protostellar."PayingPlan" ADD CONSTRAINT "1LModule_fkey" FOREIGN KEY ("FeatureRef") REFERENCES protostellar."1LModule"("ID");
ALTER TABLE protostellar."PayingPlan" ADD CONSTRAINT "1LPaymentPlan_fkey" FOREIGN KEY ("PlanRef") REFERENCES protostellar."1LPaymentPlan"("ID");
