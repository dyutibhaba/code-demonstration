ALTER TABLE digital_checkin."ContainerCheckinFormPicture"
    ALTER COLUMN "ID" SET DEFAULT public.uuid_generate_v4();
