drop table if exists "user";

create table "user"(
	username text,
	password text
	);
	
insert into "user" values('admin', 1234);
insert into "user" values('admin2', 12345);