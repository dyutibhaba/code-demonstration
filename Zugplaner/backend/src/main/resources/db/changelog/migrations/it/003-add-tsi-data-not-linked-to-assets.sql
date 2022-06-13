--- rec-id-000 train-number-000
INSERT INTO track_predict."TrainLastPosition"(
    "TrainNumber",
    "CountryCode",
    "State",
    "RailstationNumber",
    "RailstationCode",
    "CountryName",
    "OperatingRegionName",
    "RailstationName",
    "Street",
    "ZIPCode",
    "GPSLatitude",
    "GPSLongitude",
    "SYS_InsertTS",
    "SYS_InsertPrg",
    "SYS_UpdateTS",
    "SYS_UpdatePrg",
    "Recipient"
) VALUES (
    'train-number-000',
    'FR',
    'N/A',
    'STLZR-1234',
    'Alpha-Tango-Zoulou',
    'France',
    'Ile-de-France',
    'Saint Lazare',
    'Rue dAmsterdam',
    '75008',
    '48.52',
    '2.19',
    '2021-02-24'::timestamp,
    'IT-tests',
    '2021-02-24'::timestamp,
    'IT-tests',
    'rec-id-000'
 );

INSERT INTO track_predict."TrainETADelay"(
    "TrainNumber",
    "DeviationInMinutes",
    "ETA",
    "SYS_InsertPrg",
    "SYS_InsertTS",
    "SYS_UpdatePrg",
    "SYS_UpdateTS",
    "Recipient"
) VALUES (
    'train-number-000',
    3,
    '2021-02-25'::timestamp,
    'IT-tests',
    '2021-02-24'::timestamp,
    'IT-tests',
    '2021-02-24'::timestamp,
    'rec-id-000'
);

--- rec-id-000 train-number-001
INSERT INTO track_predict."TrainLastPosition"(
    "TrainNumber",
    "CountryCode",
    "State",
    "RailstationNumber",
    "RailstationCode",
    "CountryName",
    "OperatingRegionName",
    "RailstationName",
    "Street",
    "ZIPCode",
    "GPSLatitude",
    "GPSLongitude",
    "SYS_InsertTS",
    "SYS_InsertPrg",
    "SYS_UpdateTS",
    "SYS_UpdatePrg",
    "Recipient"
) VALUES (
    'train-number-001',
    'BE',
    'N/A',
    'LG-4321',
    'Delta-Foxtrot-Golf',
    'Belgium',
    'Belgique',
    'Liège-Guillemins',
    'Place des Guillemins',
    '4000',
    '50.37',
    '5.34',
    '2021-02-22'::timestamp,
    'IT-tests',
    '2021-02-22'::timestamp,
    'IT-tests',
    'rec-id-000'
);

INSERT INTO track_predict."TrainETADelay"(
    "TrainNumber",
    "DeviationInMinutes",
    "ETA",
    "SYS_InsertPrg",
    "SYS_InsertTS",
    "SYS_UpdatePrg",
    "SYS_UpdateTS",
    "Recipient"
) VALUES (
    'train-number-001',
    -2,
    '2021-02-28'::timestamp,
    'IT-tests',
    '2021-02-22'::timestamp,
    'IT-tests',
    '2021-02-22'::timestamp,
    'rec-id-000'
);

--- rec-id-000 train-number-002
INSERT INTO track_predict."TrainLastPosition"(
    "TrainNumber",
    "CountryCode",
    "State",
    "RailstationNumber",
    "RailstationCode",
    "CountryName",
    "OperatingRegionName",
    "RailstationName",
    "Street",
    "ZIPCode",
    "GPSLatitude",
    "GPSLongitude",
    "SYS_InsertTS",
    "SYS_InsertPrg",
    "SYS_UpdateTS",
    "SYS_UpdatePrg",
    "Recipient"
) VALUES (
    'train-number-002',
    'DE',
    'N/A',
    'SHB-9876',
    'Juliet-November-Sierra',
    'Germany',
    'Bade-Wurtemberg',
    'Stuttgart Hauptbahnhof',
    'Arnulf-Klett-Platz',
    '70173',
    '48.47',
    '9.10',
    '2021-03-01'::timestamp,
    'IT-tests',
    '2021-03-01'::timestamp,
    'IT-tests',
    'rec-id-000'
);

INSERT INTO track_predict."TrainETADelay"(
    "TrainNumber",
    "DeviationInMinutes",
    "ETA",
    "SYS_InsertPrg",
    "SYS_InsertTS",
    "SYS_UpdatePrg",
    "SYS_UpdateTS",
    "Recipient"
) VALUES (
    'train-number-002',
    6,
    '2021-04-01'::timestamp,
    'IT-tests',
    '2021-03-01'::timestamp,
    'IT-tests',
    '2021-03-01'::timestamp,
    'rec-id-000'
);

--- recipient-id-001 train-number-003
INSERT INTO track_predict."TrainLastPosition"(
    "TrainNumber",
    "CountryCode",
    "State",
    "RailstationNumber",
    "RailstationCode",
    "CountryName",
    "OperatingRegionName",
    "RailstationName",
    "Street",
    "ZIPCode",
    "GPSLatitude",
    "GPSLongitude",
    "SYS_InsertTS",
    "SYS_InsertPrg",
    "SYS_UpdateTS",
    "SYS_UpdatePrg",
    "Recipient"
) VALUES (
     'train-number-003',
     'DE',
     'N/A',
     'P-1111',
     'Tango-Alpha-Bravo',
     'France',
     'Pyrenees-Orientales',
     'Gare de Perpignan',
     'Galerie Salvador Dali',
     '66000',
     '42.41',
     '2.52',
     '2021-03-03'::timestamp,
     'IT-tests',
     '2021-03-03'::timestamp,
     'IT-tests',
     'recipient-id-001'
 );

INSERT INTO track_predict."TrainETADelay"(
    "TrainNumber",
    "DeviationInMinutes",
    "ETA",
    "SYS_InsertPrg",
    "SYS_InsertTS",
    "SYS_UpdatePrg",
    "SYS_UpdateTS",
    "Recipient"
) VALUES (
     'train-number-003',
     -4,
     '2021-07-01'::timestamp,
     'IT-tests',
     '2021-06-01'::timestamp,
     'IT-tests',
     '2021-06-01'::timestamp,
     'rec-id-000'
 );
