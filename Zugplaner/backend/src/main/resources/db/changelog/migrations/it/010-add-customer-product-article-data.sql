INSERT INTO protostellar."Product"
VALUES (
         '49cebff1-7fe6-48c7-b974-b31aca6dea10'::uuid,
         'Point Machine',
         'L700H',
         'Point Machine L700H',
         CURRENT_TIMESTAMP,
         '',
         'be527f12-0a80-4f72-a5ce-9320f7220176',
         CURRENT_TIMESTAMP,
         '',
         'be527f12-0a80-4f72-a5ce-9320f7220176'
       ) ON CONFLICT("ID") DO NOTHING;

INSERT INTO protostellar."Customer"
(
  "ID",
  "Name",
  "Email",
  "Description",
  "SysInsertTS",
  "SysInsertUserID",
  "SysUpdateTS",
  "SysUpdatePrg",
  "SysUpdateUserID"
)
VALUES (
         '55cebff1-7fe6-48c7-b974-b31aca6dea22'::uuid,
         'HitachiTest',
         'test@hitachi.com',
         'HitachiTest',
         CURRENT_TIMESTAMP,
         'be527f12-0a80-4f72-a5ce-9320f7220176',
         CURRENT_TIMESTAMP,
         '',
         'be527f12-0a80-4f72-a5ce-9320f7220176'
       ) ON CONFLICT("ID") DO NOTHING;

INSERT INTO protostellar."Article" (
  "ThalesNr",
  "DetectorBar",
  "Trailable",
  "ConnectorPointDetector",
  "ThrowingForce",
  "ThrowingStroke",
  "MaxThrowingTime",
  "PhasePlug",
  "AdditionalContact",
  "StandardApplication",
  "SysInsertTS"
)
VALUES ('82001 01030',  TRUE, TRUE, NULL, 5000, 220, 6.5, NULL, NULL, 'Spitzenverschluss', '2021-02-15 06:48:35' ),
       ('82001 01032',  NULL, TRUE, TRUE, 5000, 220, 6.5, NULL, NULL, 'Gleissperre', '2021-02-15 06:48:35' ),
       ('82001 01037',  TRUE, TRUE, TRUE, 5000, 220, 6.5, NULL, NULL, 'Spitzenverschluss', '2021-02-15 06:48:35' ),
       ('3JA 15534 AAAA',  TRUE, TRUE, TRUE, 5000, 220, 6.5, NULL, FALSE, 'Spitzenverschluss', '2021-02-15 06:48:35' ),
       ('3JA 15535 AAAA',  TRUE, TRUE, TRUE, 5000, 220, 6.5, FALSE, NULL, 'Spitzenverschluss', '2021-02-15 06:48:35' ),
       ('3JA 15537 AAAA',  TRUE, TRUE, TRUE, 5000, 220, 6.5, FALSE, FALSE, 'Spitzenverschluss', '2021-02-15 06:48:35' ),
       ('82001 01045',  NULL, TRUE, NULL, 3450, 150, 1, NULL, NULL, 'Schnellläufer', '2021-02-15 06:48:35' ),
       ('82001 01055',  TRUE, TRUE, NULL, 5000, 150, 5, NULL, NULL, 'Flachkreuzung', '2021-02-15 06:48:35' ),
       ('82001 01063',  NULL, TRUE, NULL, 3450, 220, 2.7, NULL, NULL, 'Schnellläufer', '2021-02-15 06:48:35' ),
       ('82001 01064',  TRUE, TRUE, NULL, 3450, 220, 2.7, FALSE, NULL, 'Schnellläufer', '2021-02-15 06:48:35' ),
       ('82001 01065',  TRUE, NULL, TRUE, 5000, 220, 6.5, NULL, NULL, 'Spitzenverschluss', '2021-02-15 06:48:35' ),
       ('3JA 15536 AAAA',  TRUE, NULL, TRUE, 5000, 220, 6.5, FALSE, NULL, 'Spitzenverschluss', '2021-02-15 06:48:35' ),
       ('3JA 15538 AAAA',  TRUE, NULL, TRUE, 5000, 220, 6.5, TRUE, TRUE, 'Spitzenverschluss', '2021-02-15 06:48:35' ),
       ('82001 01066',  TRUE, NULL, TRUE, 8000, 220, 8, NULL, NULL, 'Spitzenverschluss', '2021-02-15 06:48:35' ),
       ('3JA 15536 BAAA',  TRUE, NULL, TRUE, 8000, 220, 8, TRUE, NULL, 'Spitzenverschluss', '2021-02-15 06:48:35' ),
       ('82001 01067',  TRUE, NULL, TRUE, 5000, 220, 6.5, NULL, NULL, 'Herzstück', '2021-02-15 06:48:35' ),
       ('3JA 15536 JAAA',  TRUE, NULL, TRUE, 5000, 220, 6, TRUE, NULL, 'Herzstück ', '2021-02-15 06:48:35' ),
       ('82001 01074',  TRUE, NULL, TRUE, 4500, 220, 6, NULL, NULL, 'HRS Weiche', '2021-02-15 06:48:35' ),
       ('82001 01079',  TRUE, NULL, TRUE, 5900, 220, 6, NULL, NULL, 'HRS Weiche', '2021-02-15 06:48:35' ),
       ('3JA 15536 CAAA',  TRUE, NULL, TRUE, 5900, 220, 6, TRUE, NULL, 'HRS Weiche', '2021-02-15 06:48:35' ),
       ('82001 01080',  TRUE, NULL, TRUE, 4500, 220, 6, NULL, NULL, 'HRS Weiche', '2021-02-15 06:48:35' ),
       ('3JA 15536 DAAA',  TRUE, NULL, TRUE, 4500, 220, 6, TRUE, NULL, 'HRS Weiche', '2021-02-15 06:48:35' ),
       ('82001 01081',  TRUE, NULL, TRUE, 4500, 150, 6, NULL, NULL, 'HRS Weiche', '2021-02-15 06:48:35' ),
       ('3JA 15536 EAAA',  TRUE, NULL, TRUE, 4500, 150, 6, TRUE, NULL, 'HRS Weiche', '2021-02-15 06:48:35' ),
       ('82001 01082',  TRUE, NULL, TRUE, 6500, 150, 6, NULL, NULL, 'HRS Weiche', '2021-02-15 06:48:35' ),
       ('3JA 15536 FAAA',  TRUE, NULL, TRUE, 6500, 150, 6, TRUE, NULL, 'HRS Weiche', '2021-02-15 06:48:35' ),
       ('3JA 15531 AAAA',  TRUE, NULL, TRUE, 8000, 220, 8, NULL, NULL, 'HRS Weiche', '2021-02-15 06:48:35' ),
       ('3JA 15536 GAAA',  TRUE, NULL, TRUE, 8000, 220, 8, TRUE, NULL, 'HRS Weiche', '2021-02-15 06:48:35' ),
       ('3JA 15532 AAAA',  NULL, NULL, TRUE, 8000, 150, 8, NULL, NULL, 'Mittelverschluss', '2021-02-15 06:48:35' ),
       ('3JA 15536 HAAA',  NULL, NULL, TRUE, 8000, 150, 8, TRUE, NULL, 'Mittelverschluss', '2021-02-15 06:48:35' );
