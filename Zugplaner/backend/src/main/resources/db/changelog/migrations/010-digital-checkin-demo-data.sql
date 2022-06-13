INSERT INTO digital_checkin."Terminal" ("ID", "Name", "SysInsertTS", "SysInsertPrg")
VALUES ('39cebff1-7fe6-48c7-b974-b31aca6dead9', 'Edinburgh', NOW(), 'digital_checkin');

INSERT INTO digital_checkin."UserToTerminal"
("UserID", "TerminalID")
SELECT "ID", '39cebff1-7fe6-48c7-b974-b31aca6dead9' FROM protostellar."1LUser";

INSERT INTO digital_checkin."ContainerCheckinForm"
("ContainerNumber", "VehiclePlateNumber", "CheckinDateTime", "TerminalID", "Status", "SysInsertTS", "SysInsertPrg")
VALUES
('1234', 'AZ-524-DS', NOW(), '39cebff1-7fe6-48c7-b974-b31aca6dead9', 0, NOW(), 'digital_checkin'),
('5678', 'BD-302-VW', NOW(), '39cebff1-7fe6-48c7-b974-b31aca6dead9', 1, NOW(), 'digital_checkin'),
('2323', 'LA-972-SF', NOW(), '39cebff1-7fe6-48c7-b974-b31aca6dead9', 2, NOW(), 'digital_checkin');
