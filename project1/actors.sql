USE TEST;
CREATE TABLE Actors (Name varchar(40), Movie varchar(80), Year Integer, Role varchar(40));
LOAD DATA LOCAL INFILE '~/data/actors.csv' INTO TABLE Actors FIELDS TERMINATED BY ',' OPTIONALLY ENCLOSED BY '"';
SELECT Name from Actors where Movie="Die Another Day";
Drop table Actors;
