ALTER TABLE track_predict."Checklist"
    ADD COLUMN IF NOT EXISTS "HistoryId" int NOT NULL DEFAULT(0);

ALTER TABLE track_predict."Checklist"
    DROP CONSTRAINT "Checklist_pkey",
    ADD PRIMARY KEY ("Id", "HistoryId");


