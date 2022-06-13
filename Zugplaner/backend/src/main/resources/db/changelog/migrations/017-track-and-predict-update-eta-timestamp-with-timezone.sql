ALTER TABLE track_predict."Tracker"
    ALTER COLUMN "Eta" SET DATA TYPE timestamp with time zone;

    ALTER TABLE digital_checkin."ContainerCheckinForm"
        ALTER COLUMN "CheckinDateTime" SET DATA TYPE timestamp with time zone;
