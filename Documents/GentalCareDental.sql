CREATE DATABASE GentalCareDentals
GO

USE GentalCareDentals
GO

CREATE TABLE Account
(
	UserID VARCHAR(10) PRIMARY KEY,
	FullName NVARCHAR(100) NOT NULL,
	UserPassword VARCHAR(50) NOT NULL,
	UserEmail NVARCHAR(50) NOT NULL,
	DateOfBirth DATE,
	UserAddress NVARCHAR(150),
	UserPhone CHAR(10),
	Gender CHAR(1),
	ImageAvatar VARBINARY(MAX),
	ColorAvatar VARCHAR(10),
	DefaultAvatar CHAR(1),
	UserRole INT DEFAULT '0',
	UserStatus INT DEFAULT '0',
	AccountCreated DATETIME DEFAULT CURRENT_TIMESTAMP
)

CREATE TABLE Notify
(
	NotifyID VARCHAR(10) PRIMARY KEY,
	UserID VARCHAR(10) FOREIGN KEY REFERENCES Account(UserID),
	NotifyType NVARCHAR(10) NOT NULL,
	NotifyStatus INT DEFAULT '0',
	NotifyCreated DATETIME DEFAULT CURRENT_TIMESTAMP
)

CREATE TABLE Employee
(
	EmployeeID VARCHAR(10) PRIMARY KEY,
	Salary INT,
	Insurance INT,
	UserID VARCHAR(10) UNIQUE FOREIGN KEY REFERENCES Account(UserID)
)

CREATE TABLE RoomChat
(
	RoomID VARCHAR(10) PRIMARY KEY,
	EmployeeID VARCHAR(10) FOREIGN KEY REFERENCES Employee(EmployeeID),
	UserID VARCHAR(10) FOREIGN KEY REFERENCES Account(UserID),
	RoomStatus INT DEFAULT '0'
)

CREATE TABLE MessageChat
(
	RoomID VARCHAR(10) FOREIGN KEY REFERENCES RoomChat(RoomID),
	ChatContent NVARCHAR(MAX),
	ChatDate DATETIME DEFAULT CURRENT_TIMESTAMP
)

CREATE TABLE Hospital
(
	HospitalID VARCHAR(10) PRIMARY KEY,
	HospitalName NVARCHAR(150) NOT NULL,
	HospitalPhone CHAR(10) NOT NULL,
	HospitalAddress NVARCHAR(150) NOT NULL,
	HospitalStatus INT DEFAULT '0'
)

CREATE TABLE ServiceType
(
	ServiceTypeID VARCHAR(10) PRIMARY KEY,
	ServiceTypeName NVARCHAR(150) NOT NULL,
)

CREATE TABLE Slot
(
	SlotID VARCHAR(10) PRIMARY KEY,
	SlotStart TIME,
	SlotEnd TIME,
	SlotStatus INT DEFAULT '0',
)

CREATE TABLE [Services]
(
	ServiceID VARCHAR(10) PRIMARY KEY,
	ServiceName NVARCHAR(150) NOT NULL,
	ServicePrice INT DEFAULT '0',
	ImageService VARBINARY(MAX),
	DescriptionService NTEXT,
	ServiceStatus INT DEFAULT '0',
	ServiceTypeID VARCHAR(10) FOREIGN KEY REFERENCES ServiceType(ServiceTypeID)
)

CREATE TABLE ServiceSlot
(
	SlotServiceID VARCHAR(10) PRIMARY KEY,
	SlotStart TIME,
	SlotEnd TIME,
	SSlotStatus INT DEFAULT '0',
	ServiceID VARCHAR(10) FOREIGN KEY REFERENCES [Services](ServiceID),
	SlotID VARCHAR(10) FOREIGN KEY REFERENCES Slot(SlotID)
)

CREATE TABLE Booking
(
	BookingID VARCHAR(10) PRIMARY KEY,
	UserID VARCHAR(10) FOREIGN KEY REFERENCES Account(UserID),
	HospitalID VARCHAR(10) FOREIGN KEY REFERENCES Hospital(HospitalID),
	BookingNote NVARCHAR(Max),
	BookingStatus INT DEFAULT '0',
	BookingCreated DATETIME DEFAULT CURRENT_TIMESTAMP
)

CREATE TABLE BookingService
(
	BookingServiceID VARCHAR(10) PRIMARY KEY,
	ServiceID VARCHAR(10) FOREIGN KEY REFERENCES [Services](ServiceID),
	BookingID VARCHAR(10) FOREIGN KEY REFERENCES Booking(BookingID),
	SlotServiceID VARCHAR(10) FOREIGN KEY REFERENCES ServiceSlot(SlotServiceID),
	BookingDate DATE, 
	BSStatus INT DEFAULT '0',
)

CREATE TABLE FeedBack
(
	FeedBackID VARCHAR(10) PRIMARY KEY,
	BookingServiceID VARCHAR(10) FOREIGN KEY REFERENCES BookingService(BookingServiceID),
	NumberRating INT DEFAULT '0',
	FeedBackContent NVARCHAR(MAX) NOT NULL,
	FeedBackStatus INT DEFAULT '0',
	FeedBackCreated DATETIME DEFAULT CURRENT_TIMESTAMP
)