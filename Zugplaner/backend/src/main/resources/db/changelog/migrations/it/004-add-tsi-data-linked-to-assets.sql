--- rec-id-000 blue-train-000 (one position, one ETA)
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
    'big-blue-train-000',
    'SP',
    'N/A',
    'EBS-5555',
    'Tango-Charlie-Alpha',
    'Spain',
    'Catalona',
    'Estacio de Barcelona-Sants',
    'Placa dels Paisos Catalans',
    '08014',
    '41.22',
    '2.08',
    '2021-01-01'::timestamp,
    'IT-tests',
    '2021-01-01'::timestamp,
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
    'big-blue-train-000',
    -6,
    '2021-01-13'::timestamp,
    'IT-tests',
    '2021-01-01'::timestamp,
    'IT-tests',
    '2021-01-01'::timestamp,
    'rec-id-000'
);
