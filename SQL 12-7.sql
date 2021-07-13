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
 
CREATE TABLE UserInfo(
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
    userId int FOREIGN KEY references UserInfo(uId),
	sneType int, --1 la Skill, --2 la Experience --3 chung chi
	sneDescription nvarchar(256)
)

CREATE TABLE Subject( -- toan 
    subId int PRIMARY KEY identity(1,1),
	subName nvarchar(32) NOT NULL,
	subDesc nvarchar(256),
	subImage nvarchar(256)
)

CREATE TABLE Level( -- 11 12
    levId int PRIMARY KEY identity(1,1),
	levName nvarchar(32) NOT NULL,
	levDesc nvarchar(256)
)

CREATE TABLE Mentor_Subject(
    mnId int REFERENCES UserInfo(uId),
	subjId int REFERENCES Subject(subId)
)

---CREATE TABLE Request(
    --reqId int PRIMARY KEY identity(1,1),
	--menteeId int FOREIGN KEY REFERENCES [User](uId),
	--subId int FOREIGN KEY REFERENCES Subject(subId),
	--levId int FOREIGN KEY REFERENCES Level(levId),
	--reqStatus bit NOT NULL DEFAULT 0,
	--reqDesc nvarchar(256),
	--reqDate DATE
--)

CREATE TABLE Request(
     reqId int PRIMARY KEY identity(1,1),
	 menteeIdFrom int FOREIGN KEY REFERENCES UserInfo(uId),
	 subId int FOREIGN KEY REFERENCES Subject(subId),
	 levId int FOREIGN KEY REFERENCES Level(levId),
	 reqTitle nvarchar(64),
	 reqDesc nvarchar(512),
	 reqAvaiTime nvarchar(256),
	 reqStatus bit NOT NULL DEFAULT 0,
	 reqDateTime Date
)


CREATE TABLE Enrolled(
    enrId int PRIMARY KEY identity(1,1),
	reqId int FOREIGN KEY REFERENCES Request(reqId),
	mentorId int FOREIGN KEY REFERENCES UserInfo(uId),
	enrDate DATE,
	status nvarchar(32) DEFAULT N'NEW'
)


CREATE TABLE ChatEndpoints(
    cEpId int PRIMARY KEY identity(1,1) ,
    userFrom int FOREIGN KEY REFERENCES UserInfo(uId),
	userTo int FOREIGN KEY REFERENCES UserInfo(uId)
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
	userReceived int  FOREIGN KEY REFERENCES UserInfo(uId),
	userFrom int FOREIGN KEY REFERENCES UserInfo(uId),
	notStatus bit NOT NULL DEFAULT 0,
	notDate DATE,
	notDesc nvarchar(256)

)

CREATE TABLE Comment(
    comId int PRIMARY KEY identity(1,1),
	userFrom int FOREIGN KEY REFERENCES UserInfo(uId),
	userReceived int FOREIGN KEY REFERENCES UserInfo(uId),
	comContent nvarchar(256),
	comDate DATE
)




INSERT INTO LoginInfo(lgUsername, lgEmail,lgPassword) VALUES ('user1', 'user1@gmail.com', '$2a$10$2TaGSaY8KXFZu65ZagDiM.EoZVCLxEXnZimSaBLiJIIgyt2GqX0uW')
INSERT INTO LoginInfo(lgUsername, lgEmail,lgPassword) VALUES ('user2', 'user2@gmail.com', '$2a$10$2TaGSaY8KXFZu65ZagDiM.EoZVCLxEXnZimSaBLiJIIgyt2GqX0uW')
INSERT INTO LoginInfo(lgUsername, lgEmail,lgPassword) VALUES ('mentee1', 'mentee1@gmail.com', '$2a$10$2TaGSaY8KXFZu65ZagDiM.EoZVCLxEXnZimSaBLiJIIgyt2GqX0uW')
INSERT INTO LoginInfo(lgUsername, lgEmail,lgPassword) VALUES ('mentee2', 'mentee2@gmail.com', '$2a$10$2TaGSaY8KXFZu65ZagDiM.EoZVCLxEXnZimSaBLiJIIgyt2GqX0uW')
INSERT INTO LoginInfo(lgUsername, lgEmail,lgPassword) VALUES ('mentee3', 'mentee3@gmail.com', '$2a$10$2TaGSaY8KXFZu65ZagDiM.EoZVCLxEXnZimSaBLiJIIgyt2GqX0uW')
INSERT INTO LoginInfo(lgUsername, lgEmail,lgPassword) VALUES ('mentor1', 'mentor1@gmail.com', '$2a$10$2TaGSaY8KXFZu65ZagDiM.EoZVCLxEXnZimSaBLiJIIgyt2GqX0uW')
INSERT INTO LoginInfo(lgUsername, lgEmail,lgPassword) VALUES ('mentor2', 'mentor2@gmail.com', '$2a$10$2TaGSaY8KXFZu65ZagDiM.EoZVCLxEXnZimSaBLiJIIgyt2GqX0uW')
INSERT INTO LoginInfo(lgUsername, lgEmail,lgPassword) VALUES ('mentor3', 'mentor3@gmail.com', '$2a$10$2TaGSaY8KXFZu65ZagDiM.EoZVCLxEXnZimSaBLiJIIgyt2GqX0uW')
SELECT * FROM LoginInfo

INSERT INTO LoginInfo_Authorization(lgId, aId) VALUES (1,1)
INSERT INTO LoginInfo_Authorization(lgId, aId) VALUES (1,2)
INSERT INTO LoginInfo_Authorization(lgId, aId) VALUES (2,2)
INSERT INTO LoginInfo_Authorization(lgId, aId) VALUES (3,2)
INSERT INTO LoginInfo_Authorization(lgId, aId) VALUES (4,2)
INSERT INTO LoginInfo_Authorization(lgId, aId) VALUES (5,2)
INSERT INTO LoginInfo_Authorization(lgId, aId) VALUES (6,2)
INSERT INTO LoginInfo_Authorization(lgId, aId) VALUES (7,2)
INSERT INTO LoginInfo_Authorization(lgId, aId) VALUES (8,2)

SELECT * FROM LoginInfo_Authorization

INSERT INTO UserInfo(uId, uName,uDOB, uGender, uRole, uPhoneNumber) VALUES (1, 'Nguyen Van A', '12/12/2001', 1, 'Mentor', '123456789')
INSERT INTO UserInfo(uId, uName,uDOB, uGender, uRole, uPhoneNumber) VALUES (2, 'Nguyen Van B', '12/12/2001', 1, 'Mentor', '123456789')

INSERT INTO UserInfo(uId, uName,uDOB, uGender, uRole, uPhoneNumber) VALUES (3, 'Nguyen Minh Hanh', '12/12/2001', 0, 'Mentee', '123456789')
INSERT INTO UserInfo(uId, uName,uDOB, uGender, uRole, uPhoneNumber) VALUES (4, 'Nguyen Thu An', '12/12/2001', 0, 'Mentee', '123456789')
INSERT INTO UserInfo(uId, uName,uDOB, uGender, uRole, uPhoneNumber) VALUES (5, 'Nguyen Tran Hoang', '12/12/2001', 0, 'Mentee', '123456789')

INSERT INTO UserInfo(uId, uName,uDOB, uGender, uRole, uPhoneNumber) VALUES (6, 'Nguyen Tran A', '12/12/2001', 1, 'Mentor', '123456789')
INSERT INTO UserInfo(uId, uName,uDOB, uGender, uRole, uPhoneNumber, uStatus) VALUES (7, 'Nguyen Tran B', '12/12/2001', 1, 'Mentor', '123456789', 0)
INSERT INTO UserInfo(uId, uName,uDOB, uGender, uRole, uPhoneNumber, uStatus) VALUES (8, 'Nguyen Tran Thai An', '12/12/2001', 1, 'Mentor', '0923456789', 0)

SELECT * From UserInfo where uId= 1
SELECT * From UserInfo

INSERT INTO [Subject](subName, subDesc, subImage) VALUES ('Math','Mathematics', N'/image/subjectImage/math.jpg')
INSERT INTO [Subject](subName, subDesc, subImage) VALUES ('Literature','Literature', N'/image/subjectImage/literature.jpg')
INSERT INTO [Subject](subName, subDesc, subImage) VALUES ('English','English', N'/image/subjectImage/english.jpg')
INSERT INTO [Subject](subName, subDesc, subImage) VALUES ('History','History', N'/image/subjectImage/history.jpg')
INSERT INTO [Subject](subName, subDesc, subImage) VALUES ('Physics','Physics', N'/image/subjectImage/physics.jpg')
INSERT INTO [Subject](subName, subDesc, subImage) VALUES ('Chemistry','Chemistry', N'/image/subjectImage/chemistry.jpg')
INSERT INTO [Subject](subName, subDesc, subImage) VALUES ('Geography','Geography', N'/image/subjectImage/geography.jpg')
INSERT INTO [Subject](subName, subDesc, subImage) VALUES ('Art','Art', N'/image/subjectImage/art.jpg')
INSERT INTO [Subject](subName, subDesc, subImage) VALUES ('Calculus','Calculus', N'/image/subjectImage/math.jpg')
INSERT INTO [Subject](subName, subDesc, subImage) VALUES ('Biology','Biology', N'/image/subjectImage/biology.jpg')
INSERT INTO [Subject](subName, subDesc, subImage) VALUES ('Java','Java', N'/image/subjectImage/IT.jpg')
INSERT INTO [Subject](subName, subDesc, subImage) VALUES ('Python','Python', N'/image/subjectImage/IT.jpg')
INSERT INTO [Subject](subName, subDesc, subImage) VALUES ('C++','C++', N'/image/subjectImage/IT.jpg')
INSERT INTO [Subject](subName, subDesc, subImage) VALUES ('Spring MVC','Spring MVC', N'/image/subjectImage/IT.jpg')
INSERT INTO [Subject](subName, subDesc) VALUES ('Other','Other')

INSERT INTO [Level](levName, levDesc) VALUES ('Grade 1', 'Primary School')
INSERT INTO [Level](levName, levDesc) VALUES ('Grade 2', 'Primary School')
INSERT INTO [Level](levName, levDesc) VALUES ('Grade 3', 'Primary School')
INSERT INTO [Level](levName, levDesc) VALUES ('Grade 4', 'Primary School')
INSERT INTO [Level](levName, levDesc) VALUES ('Grade 5', 'Primary School')
INSERT INTO [Level](levName, levDesc) VALUES ('Grade 6', 'Secondary School')
INSERT INTO [Level](levName, levDesc) VALUES ('Grade 7', 'Secondary School')
INSERT INTO [Level](levName, levDesc) VALUES ('Grade 8', 'Secondary School')
INSERT INTO [Level](levName, levDesc) VALUES ('Grade 9', 'Secondary School')
INSERT INTO [Level](levName, levDesc) VALUES ('Grade 10', 'High School')
INSERT INTO [Level](levName, levDesc) VALUES ('Grade 11', 'High School')
INSERT INTO [Level](levName, levDesc) VALUES ('Grade 12', 'High School')
INSERT INTO [Level](levName, levDesc) VALUES ('Freshman', 'University')
INSERT INTO [Level](levName, levDesc) VALUES ('Sophomore', 'University')
INSERT INTO [Level](levName, levDesc) VALUES ('Senior', 'University')
INSERT INTO [Level](levName, levDesc) VALUES ('Other', 'Other')
Select * from Subject
Select * From Level

Select* From Request
