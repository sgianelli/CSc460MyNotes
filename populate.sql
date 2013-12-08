INSERT INTO Users (UserEmail, UserName) VALUES ('kate@email', 'Kate');
INSERT INTO Users (UserEmail, UserName) VALUES ('shane@email', 'Shane');
INSERT INTO Users (UserEmail, UserName) VALUES ('kelly@email', 'Kelly');

INSERT INTO Creation (UserEmail, CreationID) VALUES ('kate@email', 1);
INSERT INTO Creation (UserEmail, CreationID) VALUES ('shane@email', 2);
INSERT INTO Creation (UserEmail, CreationID) VALUES ('kate@email', 3);

INSERT INTO Board (BoardName, CreationID) VALUES ('Christmas List', 1);
INSERT INTO Board (BoardName, CreationID) VALUES ('Elegant Thought', 2);
INSERT INTO Board (BoardName, CreationID) VALUES ('Vacation Plans', 3);

INSERT INTO Card (BoardName, TaskName, CreationID, Description, DeadlineDay, DeadlineMonth, DeadlineYear) VALUES ('Christmas List', 'Buy Kelly gift', 1, 'Ideas - dress? new phone?', 25, 12, 2013);
INSERT INTO Card (BoardName, TaskName, CreationID, Description, DeadlineDay, DeadlineMonth, DeadlineYear) VALUES ('Vacation Plans', 'Buy tickets to Seattle', 1, 'Need to decide on dates', 19, 12, 2013);
INSERT INTO Card (BoardName, TaskName, CreationID, Description, DeadlineDay, DeadlineMonth, DeadlineYear) VALUES ('Elegant Thought', 'Write notes on Florida trip', 1, 'Patricia needs the notes on expenses in Floriday', 10, 12, 2013);

INSERT INTO Subscribes (UserEmail, BoardName) VALUES ('shane@email', 'Christmas List');
INSERT INTO Subscribes (UserEmail, BoardName) VALUES ('shane@email', 'Vacation Plans');
INSERT INTO Subscribes (UserEmail, BoardName) VALUES ('kelly@email', 'Vacation Plans');

INSERT INTO AssignedTo(BoardName, TaskName, UserEmail) VALUES ('Christmas List', 'Buy Kelly gift', kate@email);
INSERT INTO AssignedTo(BoardName, TaskName, UserEmail) VALUES ('Vacation Plans', 'Buy tickets to Seattle', shane@email);
INSERT INTO AssignedTo(BoardName, TaskName, UserEmail) VALUES ('Elegant Thought', 'Write notes on Florida trip', shane@email);

