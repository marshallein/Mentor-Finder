CREATE DATABASE MentorFinder

CREATE TABLE myUSER (
	UserID int identity NOT NULL,
	Name nvarchar(200) NOT NULL,
	DOB date NOT NULL,
	Gender bit NOT NULL,
	Address nvarchar(200) NOT NULL,
	Image nvarchar(200),
	Description nvarchar(200) NOT NULL,
	Role nvarchar(200) NOT NULL,
	Email nvarchar(200) NOT NULL,
	Password nvarchar(200) NOT NULL,
	Certificate nvarchar (200),
	Education nvarchar (200) NOT NULL,
	Status bit NOT NULL
	PRIMARY KEY (UserID),
)

CREATE TABLE Subject (
	SubjectID int NOT NULL,
	SubjectName nvarchar(200) NOT NULL,
	PRIMARY KEY (SubjectID)
)

CREATE TABLE Request(
	RequestID int identity NOT NULL,
	MenteeID int NOT NULL,
	RequestDescription nvarchar(200),
	SubjectID int NOT NULL,
	Status bit NOT NULL,
	MentorID int default NULL,
	PRIMARY KEY (RequestID),
	CONSTRAINT FK_myUserRequest FOREIGN KEY (MenteeID) REFERENCES myUSER(UserID),
	CONSTRAINT FK_myUserSubject FOREIGN KEY (SubjectID) REFERENCES Subject(SubjectID)
)

CREATE TABLE MentorSubject(
	MentorID int,
	SubjectID int,
	CONSTRAINT FK_myUserMentorSubject FOREIGN KEY (MentorID) REFERENCES myUSER(UserID),
	CONSTRAINT FK_myUserMentorSubject2 FOREIGN KEY (SubjectID) REFERENCES Subject(SubjectID),
)

CREATE TABLE EnrolledMentor(
	RequestID int,
	MentorID int,
	Status bit NOT NULL,
	CONSTRAINT FK_myUserEnrolledMentor FOREIGN KEY(MentorID) REFERENCES myUSER(UserID),
	CONSTRAINT FK_RequestEnrolledMentor FOREIGN KEY(RequestID) REFERENCES Request(RequestID),
)