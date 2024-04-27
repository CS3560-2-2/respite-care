/*
Name:	Matthew Tam and Simon Nasser
Group:	2
Class:	CS3560
File:	RespiteCare.sql
Description:
	Initializes database called RespiteCare, preferably on 
    local host. 
*/

DROP DATABASE IF EXISTS RespiteCare;

CREATE DATABASE RespiteCare;
USE RespiteCare;

DROP USER IF EXISTS 'user'@'localhost';
CREATE USER 'user'@'localhost' IDENTIFIED BY 'password';
GRANT ALL ON respitecare.* TO 'user'@'localhost';

CREATE TABLE Person (
    ssn INT,
    firstName VARCHAR(50),
    lastName VARCHAR(50),
    phoneNumber VARCHAR(20),
    emailAddress VARCHAR(320),
    dateOfBirth DATE,
    PRIMARY KEY (ssn)
);

CREATE TABLE Address (
    addressID INT,
    street VARCHAR(100),
    city VARCHAR(50),
    state VARCHAR(30),
    postalCode INT,
    country CHAR(2),
    ssn INT,
    PRIMARY KEY(addressID)
);

CREATE TABLE Caregiver (
    ssn INT,
    certification VARCHAR(100),
    hoursWorked INT,
    PRIMARY KEY (ssn)
);

CREATE TABLE Clients (
    medicalNumber INT,
    ssn INT,
    emergencyContactNumber INT,
    PRIMARY KEY (medicalNumber)
);

CREATE TABLE Manager (
    ssn INT,
    PRIMARY KEY(ssn)
);

CREATE TABLE Service (
    serviceID INT PRIMARY KEY,
    serviceType VARCHAR(50),
    hourlyRate DECIMAL(5, 2)
);

INSERT INTO Service VALUES
	(1, "Housekeeping", 20.00),
    (2, "In Home Medical Care", 24.45),
    (3, "Food", 21.95),
    (4, "Transportation", 23.25),
    (5, "2on1", 48.75);
    
CREATE TABLE ServiceOrder (
    authNumber INT,
    medicalNumber INT,
    serviceID INT,
    startDate DATE,
    endDate DATE,
    totalHoursAllowed INT,
    caseWorkerName VARCHAR(50),
    PRIMARY KEY (authNumber)
);

CREATE TABLE Note (
    author INT,
    target INT,
    text VARCHAR(2048),
    timea TIMESTAMP,
    PRIMARY KEY (timea , author)
);

CREATE TABLE Timesheet (
	timesheetID INT,
    authNumber INT,
    ssn INT,
    startTime TIMESTAMP,
    endTime TIMESTAMP,
    PRIMARY KEY (timesheetID)
);


ALTER TABLE Address
ADD FOREIGN KEY (ssn) REFERENCES Person(ssn);

ALTER TABLE Clients
ADD FOREIGN KEY (ssn) REFERENCES Person(ssn);

ALTER TABLE Caregiver
ADD FOREIGN KEY (ssn) REFERENCES Person(ssn);

ALTER TABLE Manager
ADD FOREIGN KEY (ssn) REFERENCES Person(ssn);

ALTER TABLE Timesheet
ADD FOREIGN KEY (ssn) REFERENCES Caregiver(ssn),
ADD FOREIGN KEY (authNumber) REFERENCES  ServiceOrder(authNumber);

ALTER TABLE Note
ADD FOREIGN KEY (author) REFERENCES Person(ssn),
ADD FOREIGN KEY (target) REFERENCES Person(ssn);

ALTER TABLE ServiceOrder
ADD FOREIGN KEY (medicalNumber)  REFERENCES Clients(medicalNumber),
ADD FOREIGN KEY (serviceID) REFERENCES Service(serviceID);

#Uncomment and run this command if needed.
#DROP DATABASE respitecare;
