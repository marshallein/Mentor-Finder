DROP DATABASE SWPTEST1
CREATE DATABASE SWPTEST1
USE SWPTEST1


CREATE TABLE LoginInfo(
	lgId int PRIMARY KEY identity(1,1),
	lgUsername nvarchar(15) NOT NULL UNIQUE,
	lgEmail nvarchar(256) NOT NULL UNIQUE,
	lgPassword nvarchar(15) NOT NULL
)

CREATE TABLE [User](
    uId int PRIMARY KEY identity(1,1),
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

CREATE TABLE Chat(
    userFrom int FOREIGN KEY REFERENCES [User](uId),
	userTo int FOREIGN KEY REFERENCES [User](uId),
	cDate DATE
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


INSERT INTO LoginInfo(lgUsername,lgEmail,lgPassword) VALUES ('abc123','abc@gmail.com',12314)
INSERT INTO LoginInfo(lgUsername,lgEmail,lgPassword) VALUES ('vnm123','adsd@gmail.com',12314)
INSERT INTO LoginInfo(lgUsername,lgEmail,lgPassword) VALUES ('ggasd','dqwdqwdqw@gmail.com',12314)
SELECT * FROM LoginInfo
