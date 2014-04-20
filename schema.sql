create table if not exists tasks (
	id int auto_increment,
	title varchar(30) not null,
	description varchar(255) not null,
	primary key(id)
);