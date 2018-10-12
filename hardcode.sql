-- Customer Register
INSERT INTO Customer
VALUES (1, "Mochuan Wang", "800 Swanston St.", "mochuanw@student.unimelb.edu.au", "0468111111", "mochuan");

-- Customer Login
SELECT Password
FROM Customer
WHERE EmailAddress = "mochuanw@student.unimelb.edu.au";

-- Customer Booking
INSERT INTO Shipping
VALUES (1, 1, 3, "No requirement", "2018-10-09", "2018-10-12", "2018-10-06 15:25:00", 50, null, 1, null, null, null, null, null, null, "800 Swanston St.", "800 Jakarta");

-- Customer view list
SELECT ShippingID
FROM Shipping JOIN Customer 
WHERE Customer.FullName = "Mochuan Wang";

SELECT BookingTime
FROM Shipping JOIN Customer 
WHERE Customer.FullName = "Mochuan Wang";

SELECT Quantity
FROM Shipping JOIN Customer 
WHERE Customer.FullName = "Mochuan Wang";

SELECT Status
FROM Shipping JOIN Customer 
WHERE Customer.FullName = "Mochuan Wang";

SELECT PreferredArrival
FROM Shipping JOIN Customer 
WHERE Customer.FullName = "Mochuan Wang";

SELECT Cost
FROM Shipping JOIN Customer 
WHERE Customer.FullName = "Mochuan Wang";

-- Or if you want a combination of the colomuns
SELECT ShippingID, BookingTime, Quantity, Status, PreferredArrival, Cost
FROM Shipping JOIN Customer 
WHERE Customer.FullName = "Mochuan Wang";


-- Manager view list
SELECT shippingID
FROM Shipping JOIN Customer;

SELECT FullName
FROM Shipping JOIN Customer;

SELECT BookingTime
FROM Shipping JOIN Customer;

SELECT Quantity
FROM Shipping JOIN Customer;

SELECT PreferredDeparture
FROM Shipping JOIN Customer;

SELECT PreferredArrival
FROM Shipping JOIN Customer;

SELECT PickupAddress
FROM Shipping JOIN Customer;

SELECT Status
FROM Shipping JOIN Customer;

SELECT Cost
FROM Shipping JOIN Customer;

-- Or if you want a combination of the colomuns
SELECT ShippingID, Fullname, BookingTime, Quantity, PreferredDeparture, PreferredArrival, PickupAddress, Status, Cost
FROM Shipping NATURAL JOIN Customer;

-- Customer order detail, just a combination, if need individual attributes, just break down
SELECT BookingTime, AcknowledgeTime, Quantity, DeliveryAddress, PickupAddress, PreferredDeparture, PreferredArrival, Cost, Status, CustomerMessage
FROM Shipping JOIN Customer 
WHERE ShippingID = 1;

-- For the tracking information part, database just store the current status
SELECT Status
FROM Shipping JOIN Customer 
WHERE ShippingID = 1;

-- Acknowledge a shipment
UPDATE Shipping SET status = "2", PickupTime = "2018-10-9 16:00:00", Cost = 60, HBL = 12345678
WHERE ShippingID = 1;