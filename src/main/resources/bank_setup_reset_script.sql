drop table if exists "user";
drop table if exists "account";

create table "user"(
    user_id integer primary key AUTOINCREMENT,
	username text,
	password text
	);
insert into "user" values(0, 'admin', 1234);

insert into "user" values(1, 'admin2', 12345);

create table "account"(
    account_id integer primary key AUTOINCREMENT,
    bank_name text,
    account_type text,
    balance double NOT NULL DEFAULT 0,
    account_user integer NOT null references user(user_id),
    joint_user integer default null references user(user_id)
);
	
insert into "account" values(0, "chase", "checkings", 1323.47, 0, 0);

insert into "account" values(1, "chase", "checkings", 2543.97, 1, 0);

