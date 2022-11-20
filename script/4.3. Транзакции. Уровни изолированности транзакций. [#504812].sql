create table bank_account(
	id serial primary key,
	name text,
	count int
);

insert into bank_account(name, count) values('max', 300);
insert into bank_account(name, count) values('bob', 200);
insert into bank_account(name, count) values('ron', 1000);

psql --username=postgres
\connect 1
select * from bank_account;
start transaction isolation level read uncommitted;
update bank_account set count = 50 where name = 'max';

psql --username=postgres
\connect 1
start transaction isolation level read committed;
update bank_account set count = 700 where name = 'bob';
commit;

psql --username=postgres
\connect 1
start transaction isolation level repeatable read;
update bank_account set count = 60 where name = 'bob';
show transaction_isolation;
commit;

psql --username=postgres
\connect 1
start transaction isolation level serializable;
update bank_account set count = 60 where name = 'bob';
commit;