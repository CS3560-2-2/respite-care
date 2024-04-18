DROP DATABASE IF EXISTS RespiteCare;

CREATE DATABASE RespiteCare;
USE RespiteCare;

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
    caregiverID INT,
    ssn INT,
    PRIMARY KEY (caregiverID)
);

CREATE TABLE Clients (
    medicalNumber INT,
    ssn INT,
    emergencyContactNumber INT,
    PRIMARY KEY (medicalNumber)
);

CREATE TABLE Manager (
	managerID INT, 
    PRIMARY KEY(managerID)
);

CREATE TABLE Service (
    serviceID INT PRIMARY KEY,
    serviceType VARCHAR(50)
);

INSERT INTO Service VALUES
	(01, "Housekeeping"),
    (02, "In Home Medical Care"),
    (03, "Food"),
    (04, "Transportation"),
    (05, "Other");
    
CREATE TABLE ServiceOrder (
    authNumber INT,
    medicalNumber INT,
    serviceID INT,
    startDate DATE,
    endDate DATE,
    totalHoursAllowed INT,
    maxBillAmt INT,
    caseWorkerName VARCHAR(50),
    PRIMARY KEY (authNumber)
);

CREATE TABLE ClientFeedback (
    feedbackID INT,
    authNumber INT,
    medicalNumber INT,
    text VARCHAR(2048),
    PRIMARY KEY (feedbackID , authNumber , medicalNumber)
);

CREATE TABLE CareNote (
    noteID INT,
    authNumber INT,
    caregiverID INT,
    text VARCHAR(2048),
    PRIMARY KEY (noteID , authNumber , caregiverID)
);

CREATE TABLE Timesheet (
    authNumber INT,
    caregiverID INT,
    startDate INT,
    endDate INT,
    dayTime VARCHAR(50),
    PRIMARY KEY (authNumber , caregiverID)
);


ALTER TABLE Address
ADD FOREIGN KEY (ssn) REFERENCES Person(ssn);

ALTER TABLE Clients
ADD FOREIGN KEY (ssn) REFERENCES Person(ssn);

ALTER TABLE Caregiver
ADD FOREIGN KEY (ssn) REFERENCES Person(ssn);

ALTER TABLE Timesheet
ADD FOREIGN KEY (caregiverID) REFERENCES Caregiver(caregiverID),
ADD FOREIGN KEY (authNumber) REFERENCES  ServiceOrder(authNumber);

ALTER TABLE CareNote
ADD FOREIGN KEY (authNumber)  REFERENCES ServiceOrder(authNumber),
ADD FOREIGN KEY (caregiverID) REFERENCES Caregiver(caregiverID);

ALTER TABLE ClientFeedback
ADD FOREIGN KEY (authNumber) REFERENCES ServiceOrder(authNumber),
ADD FOREIGN KEY (medicalNumber)	 REFERENCES Clients(medicalNumber);

ALTER TABLE ServiceOrder
ADD FOREIGN KEY (medicalNumber)  REFERENCES Clients(medicalNumber),
ADD FOREIGN KEY (serviceID) REFERENCES Service(serviceID);



#Uncomment and run this command if needed.
#DROP DATABASE respitecare;
