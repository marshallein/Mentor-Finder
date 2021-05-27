CREATE DATABASE MentorFinder

CREATE TABLE [User] (
	ID int identity(1,1) NOT NULL,
	Email nvarchar(256) NOT NULL,
	Password nvarchar(256) NOT NULL,
	Name nvarchar(256) NOT NULL,
	DOB date NOT NULL,
	Gender bit NOT NULL,
	Role nvarchar(32) NOT NULL,
	PhoneNumber nvarchar(32),
	Address nvarchar(256),
	Image nvarchar(256),
	Description nvarchar(256),
	Certificate nvarchar (256),
	Education nvarchar (256),
	Status bit NOT NULL DEFAULT 1,
	--0 for deactivate, 1 for activate
	PRIMARY KEY (ID),
)

CREATE TABLE [Subject] (
	ID int identity(1,1) NOT NULL,
	Name nvarchar(256) NOT NULL,
	PRIMARY KEY (ID)
)

CREATE TABLE [Request] (
	ID int identity(1,1) NOT NULL,
	MenteeID int NOT NULL,
	SubjectID int NOT NULL,
	RequestDescription nvarchar(256),
	Status int NOT NULL DEFAULT 0,
	-- 0 for NEW, 1 for DONE, 2 for CANCEL
	MentorID int default NULL,
	PRIMARY KEY (ID),
	CONSTRAINT FK_myUserRequest FOREIGN KEY (MenteeID) REFERENCES [User](ID),
	CONSTRAINT FK_myUserSubject FOREIGN KEY (SubjectID) REFERENCES Subject(ID)
)

CREATE TABLE [MentorSubject] (
	MentorID int,
	SubjectID int,
	CONSTRAINT FK_myUserMentorSubject FOREIGN KEY (MentorID) REFERENCES [User](ID),
	CONSTRAINT FK_myUserMentorSubject2 FOREIGN KEY (SubjectID) REFERENCES Subject(ID),
)

CREATE TABLE [EnrolledMentor] (
	RequestID int,
	MentorID int,
	Status bit NOT NULL,
	CONSTRAINT FK_myUserEnrolledMentor FOREIGN KEY(MentorID) REFERENCES [User](ID),
	CONSTRAINT FK_RequestEnrolledMentor FOREIGN KEY(RequestID) REFERENCES Request(ID),
)
