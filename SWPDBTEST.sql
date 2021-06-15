DROP DATABASE SWPTEST1
CREATE DATABASE SWPTEST1
USE SWPTEST1


CREATE TABLE LoginInfo(
	lgId int PRIMARY KEY identity(1,1),
	lgUsername nvarchar(15) NOT NULL UNIQUE,
	lgEmail nvarchar(255) NOT NULL UNIQUE,
	lgPassword varchar(60) NOT NULL
)

CREATE TABLE [Authorization](
    aId int PRIMARY KEY,
	aName nvarchar(60) NOT NULL
)

INSERT INTO [Authorization](aId, aName) VALUES (1, 'ROLE_ADMIN')
INSERT INTO [Authorization](aId, aName) VALUES (2, 'ROLE_USER')
SELECT * FROM [Authorization]

CREATE TABLE LoginInfo_Authorization(
    lgId int FOREIGN KEY REFERENCES LoginInfo(lgId),
	aId int FOREIGN KEY REFERENCES [Authorization](aId)
)
 
CREATE TABLE [User](
    uId int PRIMARY KEY REFERENCES LoginInfo(lgId),
	uName nvarchar(256) NOT NULL,
	uDOB date NOT NULL,
	uGender bit NOT NULL,	
	uRole nvarchar(32) NOT NULL,
	uPhoneNumber nvarchar(32),
	uAddress nvarchar(256),
	uImage nvarchar(256),
	uDescription nvarchar(256),
	uStatus bit NOT NULL DEFAULT 1,
)


CREATE TABLE SkillnExperience(
    sneId int PRIMARY KEY identity(1,1),
    userId int FOREIGN KEY references [User](uId),
	sneType int, --1 la Skill, --2 la Experience --3 chung chi
	sneDescription nvarchar(256)
)

CREATE TABLE Subject( -- toan 
    subId int PRIMARY KEY identity(1,1),
	subName nvarchar(32) NOT NULL,
	subDesc nvarchar(256)
)

CREATE TABLE Level( -- 11 12
    levId int PRIMARY KEY identity(1,1),
	levName nvarchar(32) NOT NULL,
	levDesc nvarchar(256)
)

CREATE TABLE Mentor_Subject(
    mnId int REFERENCES [User](uId),
	subjId int REFERENCES Subject(subId)
)

CREATE TABLE Request(
    reqId int PRIMARY KEY identity(1,1),
	menteeId int FOREIGN KEY REFERENCES [User](uId),
	subId int FOREIGN KEY REFERENCES Subject(subId),
	levId int FOREIGN KEY REFERENCES Level(levId),
	reqStatus bit NOT NULL DEFAULT 0,
	reqDesc nvarchar(256),
	reqDate DATE
)

CREATE TABLE Enrolled(
    enrId int PRIMARY KEY identity(1,1),
	reqId int FOREIGN KEY REFERENCES Request(reqId),
	mentorId int FOREIGN KEY REFERENCES [User](uId),
	enrDate DATE
)


CREATE TABLE ChatEndpoints(
    cEpId int PRIMARY KEY identity(1,1) ,
    userFrom int FOREIGN KEY REFERENCES [User](uId),
	userTo int FOREIGN KEY REFERENCES [User](uId)
)

Create Table Chat(
    cId int PRIMARY KEY identity(1,1),
	cUserFrom bit,
	cEndpId int FOREIGN KEY REFERENCES ChatEndpoints(cEpId),
	cContent nvarchar(256),
	cData DATE
)

CREATE TABLE Notify(
    notId int PRIMARY KEY identity(1,1),
	notType int,
	userReceived int  FOREIGN KEY REFERENCES [User](uId),
	userFrom int FOREIGN KEY REFERENCES [User](uId),
	notStatus bit NOT NULL DEFAULT 0,
	notDate DATE,
	notDesc nvarchar(256)

)

CREATE TABLE Comment(
    comId int PRIMARY KEY identity(1,1),
	userFrom int FOREIGN KEY REFERENCES [User](uId),
	userReceived int FOREIGN KEY REFERENCES [User](uId),
	comContent nvarchar(256),
	comDate DATE
)




INSERT INTO LoginInfo(lgUsername, lgEmail,lgPassword) VALUES ('user1', 'user1@gmail.com', '$2a$10$2TaGSaY8KXFZu65ZagDiM.EoZVCLxEXnZimSaBLiJIIgyt2GqX0uW')
INSERT INTO LoginInfo(lgUsername, lgEmail,lgPassword) VALUES ('user2', 'user2@gmail.com', '$2a$10$2TaGSaY8KXFZu65ZagDiM.EoZVCLxEXnZimSaBLiJIIgyt2GqX0uW')
SELECT * FROM LoginInfo

INSERT INTO LoginInfo_Authorization(lgId, aId) VALUES (1,1)
INSERT INTO LoginInfo_Authorization(lgId, aId) VALUES (1,2)
INSERT INTO LoginInfo_Authorization(lgId, aId) VALUES (2,2)

SELECT * FROM LoginInfo_Authorization

INSERT INTO [User](uId, uName,uDOB, uGender, uRole, uPhoneNumber) VALUES (1, 'Nguyen Van A', '12/12/2001', 1, 'Mentor', '123456789')
INSERT INTO [User](uId, uName,uDOB, uGender, uRole, uPhoneNumber) VALUES (2, 'Nguyen Van B', '12/12/2001', 1, 'Mentor', '123456789')
SELECT * From [User] where uId= 1

SELECT * From [User]