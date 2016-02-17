USE master
GO

DROP DATABASE QLNT
GO

CREATE DATABASE QLNT
GO

USE QLNT
GO

CREATE TABLE tbl_Admin(
	ID_Admin INT IDENTITY(1,1),
	Username NVARCHAR(50) NOT NULL UNIQUE,
	Password NVARCHAR(50) NOT NULL,
	Email NVARCHAR(100) NULL UNIQUE,
	Date_Create DATE DEFAULT CURRENT_TIMESTAMP,
	Status NVARCHAR(20) NOT NULL,
	CONSTRAINT pk_tbl_Admin_ID_Admin PRIMARY KEY(ID_Admin)
)

CREATE TABLE tbl_Room_Type(
	ID_Room_Type INT IDENTITY(1,1),
	Type NVARCHAR(25) NOT NULL,
	Price FLOAT NOT NULL DEFAULT 0,
	Add_Price FLOAT NOT NULL DEFAULT 0,
	CONSTRAINT pk_tbl_Room_Type_ID_Room_Type PRIMARY KEY(ID_Room_Type)
)

CREATE TABLE tbl_Room(
	ID_Room INT IDENTITY(1,1),
	ID_Room_Type INT NOT NULL,
	Room_Name NVARCHAR(50) NOT NULL,
	Description NVARCHAR(255) NULL,
	Status NVARCHAR(25) NOT NULL,
	CONSTRAINT pk_tbl_Room_ID_Room PRIMARY KEY(ID_Room),
	CONSTRAINT fk_tbl_Room_ID_Room_Type FOREIGN KEY(ID_Room_Type) REFERENCES tbl_Room_Type(ID_Room_Type)
)


CREATE TABLE tbl_Renter(
	ID_Renter INT IDENTITY(1,1),
	Full_Name NVARCHAR(50) NOT NULL,
	Gender BIT NOT NULL DEFAULT 0,
	Birthday DATE DEFAULT CURRENT_TIMESTAMP,
	ID_Card_Number VARCHAR(15) NOT NULL,
	Phone_Number VARCHAR(12) CHECK(LEN(Phone_Number) >= 8 AND LEN(Phone_Number) <= 12),
	Address NVARCHAR(255) NOT NULL,
	Job NVARCHAR(255) NOT NULL,
	Assets NVARCHAR(500) NULL,
	CONSTRAINT pk_tbl_Renter_ID_Renter PRIMARY KEY(ID_Renter)
)

CREATE TABLE tbl_Room_Renter(
	ID_Room_Renter INT IDENTITY(1,1),
	ID_Renter INT NOT NUll,
	ID_Room INT NOT NULL,
	Deposits FLOAT NOT NULL,
	Date_In DATE NOT NULL,
	DATE_out DATE NOT NULL,
	CONSTRAINT pk_tbl_Room_Renter_ID_Room_Renter PRIMARY KEY(ID_Room_Renter),
	CONSTRAINT fk_tbl_Room_Renter_ID_Room FOREIGN KEY(ID_Room) REFERENCES tbl_Room(ID_Room),
	CONSTRAINT fk_tbl_Room_Renter_ID_Renter FOREIGN KEY(ID_Renter) REFERENCES tbl_Renter(ID_Renter)
)

CREATE TABLE tbl_Member(
	ID_Member INT IDENTITY(1,1),
	ID_Room_Renter INT,
	Date_In DATE NOT NULL,
	Date_Out DATE NOT NULL,
	CONSTRAINT pk_tbl_Member_ID_Member PRIMARY KEY(ID_Member),
	CONSTRAINT fk_tbl_Member_ID_Room_Renter FOREIGN KEY(ID_Room_Renter) REFERENCES tbl_Room_Renter(ID_Room_Renter)
)

CREATE TABLE tbl_Services(
	ID_Service INT IDENTITY(1,1),
	Service_Name NVARCHAR(50) NOT NULL,
	Service_Price FLOAT NOT NULL DEFAULT 0,
	CONSTRAINT pk_tbl_Service_ID_Service PRIMARY KEY(ID_Service)
)

CREATE TABLE tbl_Electric(
	ID_Electric INT IDENTITY(1,1),
	Electric_Price FLOAT NOT NULL DEFAULT 0,
	CONSTRAINT pk_tbl_Electric_ID_Electric PRIMARY KEY(ID_Electric)
)

CREATE TABLE tbl_Electric_Bill(
	ID_Electric_Bill INT IDENTITY(1,1),
	ID_Room_Renter INT NOT NULL,
	ID_Electric INT NOT NULL,
	Old_Num INT NULL,
	New_Num INT NULL,
	Date_Create DATE DEFAULT CURRENT_TIMESTAMP,
	Total FLOAT NOT NULL DEFAULT 0,
	CONSTRAINT pk_tbl_Electric_Bill_ID_Electric_Bill PRIMARY KEY(ID_Electric_Bill),
	CONSTRAINT fk_tbl_Electric_Bill_ID_Electric FOREIGN KEY(ID_Electric) REFERENCES tbl_Electric(ID_Electric)
)

CREATE TABLE tbl_Bill(
	ID_Bill INT IDENTITY(1,1),
	ID_Electric_Bill INT NOT NULL,
	ID_Room_Renter INT NOT NULL,
	Date_Create DATE DEFAULT CURRENT_TIMESTAMP,
	Status INT,
	CONSTRAINT pk_tbl_Bill_ID_Bill PRIMARY KEY(ID_Bill),
	CONSTRAINT fk_tbl_Bill_ID_Electric_Bill FOREIGN KEY(ID_Electric_Bill) REFERENCES tbl_Electric_Bill(ID_Electric_Bill),
	CONSTRAINT fk_tbl_Bill_ID_Room_Renter FOREIGN KEY(ID_Room_Renter) REFERENCES tbl_Room_Renter(ID_Room_Renter),
)


CREATE TABLE tbl_Bill_Detail(
	ID_Bill_Detail INT IDENTITY(1,1),
	ID_Bill INT NOT NULL,
	ID_Service INT NOT NULL,
	CONSTRAINT pk_tbl_Bill_Detail_ID_Bill_Detail PRIMARY KEY(ID_Bill_Detail),
	CONSTRAINT fk_tbl_Bill_Detail_ID_Bill FOREIGN KEY(ID_Bill) REFERENCES tbl_Bill(ID_Bill),
	CONSTRAINT fk_tbl_Bill_Detail_ID_Service FOREIGN KEY(ID_Service) REFERENCES tbl_Services(ID_Service)
)
