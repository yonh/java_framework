create database java_framework;

use java_framework;

create table custom(
	id int primary key auto_increment,
	name varchar(200),
	contact varchar(200),
	mobile varchar(200),
	email varchar(200),
	remark text
);


insert into custom values('1', 'customer1', 'yonh', '110', '110@china.cn', '');
insert into custom values('2', 'customer2', 'yonh', '120', '120@china.cn', '');

