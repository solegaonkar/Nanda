create db sdb;
use sdb;
create table module (id int(5) unsigned auto_increment primary key not null, 
					name varchar(40) not null, 
					months int(5) unsigned,
					fees int(5) unsigned,
					notes varchar(1000));
insert into module (name, months, fees, notes) values ('Vocal - level 1', 6, 1000, 'basic info about the module in less than 1000 chars');
insert into module (name, months, fees, notes) values ('Vocal - level 2', 6, 2000, 'basic info about the module in less than 1000 chars');
insert into module (name, months, fees, notes) values ('Vocal - level 3', 6, 3000, 'basic info about the module in less than 1000 chars');
insert into module (name, months, fees, notes) values ('Guitar - level 1', 6, 1000, 'basic info about the module in less than 1000 chars');
insert into module (name, months, fees, notes) values ('Guitar - level 2', 6, 2000, 'basic info about the module in less than 1000 chars');
insert into module (name, months, fees, notes) values ('Guitar - level 3', 6, 3000, 'basic info about the module in less than 1000 chars');
insert into module (name, months, fees, notes) values ('Keyboard - level 1', 6, 1000, 'basic info about the module in less than 1000 chars');
insert into module (name, months, fees, notes) values ('Keyboard - level 2', 6, 2000, 'basic info about the module in less than 1000 chars');
insert into module (name, months, fees, notes) values ('Keyboard - level 3', 6, 3000, 'basic info about the module in less than 1000 chars');


create table batch (id int(5) unsigned auto_increment primary key not null, 
					name varchar(40) not null, 
					module_id int(5) unsigned not null, 
					schedule varchar(10));
insert into batch (name, module_id, schedule) values ('Early Morning', 0, 'ABAABAA');
insert into batch (name, module_id, schedule) values ('Afternoon', 6, 'AFAAFAA');
insert into batch (name, module_id, schedule) values ('Evening', 7, 'AJAAJAA');


create table student (id int(5) unsigned auto_increment primary key not null, 
					name varchar(40) not null, 
					phone varchar(15), 
					email varchar(40), 
					batch_id int(5) not null);

insert into student(name, phone, email, batch_id) values ('Donald Trump', 9876543210, 'donald.trump@gmail.com', 0);
insert into student(name, phone, email, batch_id) values ('Barac Obama', 1234567890, 'barac.obama@gmail.com', 1);
insert into student(name, phone, email, batch_id) values ('George Bush', 9876543210, 'george.bush@gmail.com', 2);
insert into student(name, phone, email, batch_id) values ('Bill Clinton', 1234567890, 'bill.clinton@gmail.com', 3);

