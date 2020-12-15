CREATE TABLE Customers (
CustomerId int NOT NULL AUTO_INCREMENT,
PRIMARY KEY (CustomerId));

CREATE TABLE AccountStatus (
Id int NOT NULL AUTO_INCREMENT,
Status varchar(50) DEFAULT NULL,
PRIMARY KEY (Id));

create table SpecialtyList (
SpecialtyId int not null auto_increment,
Specialty varchar(50) not null,
primary key (SpecialtyId),
unique (Specialty));

CREATE TABLE Accounts (
Id int NOT NULL AUTO_INCREMENT,
CustomerId int DEFAULT NULL,
Name varchar(60) DEFAULT NULL,
AccountStatus int DEFAULT NULL,
PRIMARY KEY (Id),
UNIQUE KEY CustomerId (CustomerId),
CONSTRAINT Accounts_ibfk_2 FOREIGN KEY (AccountStatus) REFERENCES AccountStatus (Id),
CONSTRAINT fk_customer FOREIGN KEY (CustomerId) REFERENCES Customers (CustomerId) ON DELETE CASCADE);

create table Specialties (
CustomerId int not null,
SpecialtyId int,
primary key (CustomerId, SpecialtyId),
foreign key (CustomerId) references Customers(CustomerId) on delete cascade,
foreign key (SpecialtyId) references SpecialtyList (SpecialtyId) on delete cascade);