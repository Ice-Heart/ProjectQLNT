CREATE DATABASE QLNT
GO

USE QLNT;
GO

CREATE TABLE tbl_Admin(
	ID_Admin INT IDENTITY(1,1),
	Username NVARCHAR(50) NOT NULL UNIQUE,
	Password NVARCHAR(50) NOT NULL,
	Email NVARCHAR(100) NULL UNIQUE,
	Date_Create DATETIME DEFAULT CURRENT_TIMESTAMP,
	Status NVARCHAR(20) NOT NULL,
	CONSTRAINT pk_tbl_Admin_ID_Admin PRIMARY KEY(ID_Admin)
)

CREATE TABLE tbl_Room(
	ID_Room INT IDENTITY(1,1),
	Room_Name NVARCHAR(50) NOT NULL,
	Description NVARCHAR(255) NULL,
	Status INT,
	CONSTRAINT pk_tbl_Electrict_Price_ID_Room PRIMARY KEY(ID_Room)
)

CREATE TABLE tbl_Price_Room(
	ID_Price_Room INT IDENTITY(1,1),
	Price FLOAT NOT NULL DEFAULT 0,
	Add_Price FLOAT NOT NULL DEFAULT 0,
	CONSTRAINT pk_tbl_Price_Room_ID_Price_Room PRIMARY KEY(ID_Price_Room)
)

CREATE TABLE tbl_Room_Price(
	ID_Room INT NOT NULL,
	ID_Price_Room INT NOT NULL,
	CONSTRAINT pk_tbl_Room_Price_ID_Room_ID_Price_Room PRIMARY KEY(ID_Room, ID_Price_Room),
	CONSTRAINT fk_tbl_Room_PriceID_Room FOREIGN KEY(ID_Room) REFERENCES tbl_Room(ID_Room),
	CONSTRAINT fk_tbl_Room_PriceID_Price_Room FOREIGN KEY(ID_Price_Room) REFERENCES tbl_Price_Room(ID_Price_Room)
)

CREATE TABLE tbl_Electric_Price(
	ID_Electric_Price INT IDENTITY(1,1),
	Price FLOAT NOT NULL DEFAULT 0,
	CONSTRAINT pk_tbl_Electric_Price_ID_Electric_Price PRIMARY KEY(ID_Electric_Price)
)

CREATE TABLE tbl_Room_Electric(
	ID_Room INT NOT NULL,
	ID_Electric_Price INT NOT NULL,
	Old_Number INT NOT NULL DEFAULT 0,
	New_Number INT NOT NULL DEFAULT 0,
	Date_Create DATETIME DEFAULT CURRENT_TIMESTAMP,
	CONSTRAINT pk_tbl_Room_Electric_ID_Electric_Price PRIMARY KEY(ID_Room, ID_Electric_Price),
	CONSTRAINT fk_tbl_Room_Electric_ID_Room FOREIGN KEY(ID_Room) REFERENCES tbl_Room(ID_Room),
	CONSTRAINT fk_tbl_Room_Electric_ID_Electric_Price FOREIGN KEY(ID_Electric_Price) REFERENCES tbl_Electric_Price(ID_Electric_Price)
)


CREATE TABLE tbl_Renter(
	ID_Renter INT IDENTITY(1,1),
	Full_Name NVARCHAR(50) NOT NULL,
	Gender BIT NOT NULL DEFAULT 0,
	Birthday DATETIME DEFAULT CURRENT_TIMESTAMP,
	ID_Card_Number INT NOT NULL,
	Phone_Number VARCHAR(12) CHECK(Phone_Number > 8 AND Phone_Number < 12),
	Job NVARCHAR(255) NOT NULL,
	Address NVARCHAR(255) NOT NULL,
	Assets NVARCHAR(500) NULL,
	CONSTRAINT pk_tbl_Renter_ID_Renter PRIMARY KEY(ID_Renter)
)

CREATE TABLE tbl_Room_Renter(
	ID_Room_Renter INT IDENTITY(1,1),
	ID_Renter INT NOT NUll,
	ID_Room INT NOT NULL,
	Deposits FLOAT NOT NULL,
	Date_In DATETIME NOT NULL,
	DATE_out DATETIME NOT NULL,
	CONSTRAINT pk_tbl_Room_Renter_ID_Room_Renter PRIMARY KEY(ID_Room_Renter),
	CONSTRAINT fk_tbl_Room_Renter_ID_Room FOREIGN KEY(ID_Room) REFERENCES tbl_Room(ID_Room),
	CONSTRAINT fk_tbl_Room_Renter_ID_Renter FOREIGN KEY(ID_Renter) REFERENCES tbl_Renter(ID_Renter)
)

CREATE TABLE tbl_Member(
	ID_Member INT IDENTITY(1,1),
	ID_Room_Renter INT,
	Date_In DATETIME NOT NULL,
	Date_Out DATETIME NOT NULL,
	CONSTRAINT pk_tbl_Member_ID_Member PRIMARY KEY(ID_Member),
	CONSTRAINT fk_tbl_Member_ID_Room_Renter FOREIGN KEY(ID_Room_Renter) REFERENCES tbl_Room_Renter(ID_Room_Renter)
)

CREATE TABLE tbl_Bill(
	ID_Bill INT IDENTITY(1,1),
	ID_Room_Renter INT NOT NULL,
	Date_Create DATETIME DEFAULT CURRENT_TIMESTAMP,
	Status INT,
	CONSTRAINT pk_tbl_Bill_ID_Bill PRIMARY KEY(ID_Bill),
	CONSTRAINT fk_tbl_Bill_ID_Room_Renter FOREIGN KEY(ID_Room_Renter) REFERENCES tbl_Room_Renter(ID_Room_Renter)
)

CREATE TABLE tbl_Bill_Detail(
	ID_Bill_Detail INT IDENTITY(1,1),
	ID_Bill INT NOT NULL,
	ID_Electric_Price INT NOT NULL DEFAULT 0,
	Price INT NOT NULL DEFAULT 0,
	CONSTRAINT pk_tbl_Bill_Detail_ID_Bill_Detail PRIMARY KEY(ID_Bill_Detail),
	CONSTRAINT fk_tbl_Bill_Detail_ID_Bill FOREIGN KEY(ID_Bill) REFERENCES tbl_Bill(ID_Bill)
)

CREATE TABLE tbl_Other_Price(
	ID_Other_Price INT IDENTITY(1,1),
	Price_Name NVARCHAR(50) NOT NULL,
	Price FLOAT NOT NULL DEFAULT 0,
	CONSTRAINT pk_tbl_Other_Price_ID_Other_Price PRIMARY KEY(ID_Other_Price)
)

CREATE TABLE tbl_Room_Other_Price(
	ID_Room INT NOT NULL,
	ID_Other_Price INT NOT NULL,
	CONSTRAINT pk_Room_Other_Price_ID_Room_ID_Other_Price PRIMARY KEY(ID_Room, ID_Other_Price),
	CONSTRAINT fk_tbl_Room_Other_Price_ID_Room FOREIGN KEY(ID_Room) REFERENCES tbl_Room(ID_Room),
	CONSTRAINT fk_tbl_Room_Other_Price_ID_Other_Price FOREIGN KEY(ID_Other_Price) REFERENCES tbl_Other_Price(ID_Other_Price)
)