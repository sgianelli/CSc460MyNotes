CREATE TABLE Users (
UserEmail varchar(254) NOT NULL, 
UserName varchar(64) NOT NULL,
PRIMARY KEY (UserEmail));

CREATE TABLE Creation (
UserEmail varchar(254) NOT NULL, 
CreationID int  NOT NULL,
PRIMARY KEY (CreationID),
FOREIGN KEY (UserEmail) REFERENCES Users(UserEmail)   );

CREATE TABLE Board (
BoardName varchar(64) NOT NULL,
CreationID int  NOT NULL,
PRIMARY KEY (BoardName),
FOREIGN KEY (CreationID) REFERENCES Creation(CreationID));

CREATE TABLE CARD (
BoardName varchar(64) NOT NULL,
TaskName varchar(64) NOT NULL,
CreationID int NOT NULL,
Description varchar(4000) NOT NULL,
DeadlineDay int NOT NULL,
DeadlineMonth int NOT NULL,
DeadlineYear int NOT NULL,
PRIMARY KEY (BoardName, TaskName),
FOREIGN KEY (BoardName) REFERENCES Board(BoardName),
FOREIGN KEY (CreationID) REFERENCES Creation(CreationID));

CREATE TABLE Subscribes(
UserEmail varchar(254) NOT NULL,
BoardName varchar(64) NOT NULL,
PRIMARY KEY (UserEmail, BoardName),
FOREIGN KEY (UserEmail) REFERENCES Users(UserEmail),
FOREIGN KEY (BoardName) REFERENCES Board(BoardName));

CREATE TABLE AssignedTo(
BoardName varchar(64) NOT NULL,
TaskName varchar(64) NOT NULL,
UserEmail varchar(254) NOT NULL,
PRIMARY KEY (BoardName, TaskName, UserEmail),
FOREIGN KEY (BoardName, TaskName) REFERENCES Card(BoardName, TaskName),
FOREIGN KEY (UserEmail) REFERENCES Users(UserEmail) ); 


