CREATE TABLE if not exists customers (
customer_id int NOT NULL AUTO_INCREMENT,
PRIMARY KEY (customer_id));

create table if not exists specialty_list (
specialty_id int not null auto_increment,
specialty varchar(50) not null,
primary key (specialty_id),
unique (specialty));

CREATE TABLE if not exists accounts (
id int NOT NULL AUTO_INCREMENT,
customer_id int DEFAULT NULL,
name varchar(60) DEFAULT NULL,
account_status varchar (50) DEFAULT NULL,
PRIMARY KEY (id),
UNIQUE KEY customer_id (customer_id),
CONSTRAINT fk_customer FOREIGN KEY (customer_id) REFERENCES customers (customer_id) ON DELETE CASCADE);

create table if not exists specialties (
customer_id int,
specialty_id int,
primary key (customer_id, specialty_id),
foreign key (customer_id) references customers(customer_id) on delete cascade,
foreign key (specialty_id) references specialty_list (specialty_id) on delete cascade);
