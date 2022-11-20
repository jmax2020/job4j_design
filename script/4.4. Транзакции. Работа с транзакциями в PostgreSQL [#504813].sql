create table bank_account(
	id serial primary key,
	name text,
	count int
);

insert into bank_account(name, count) values('max', 300);
insert into bank_account(name, count) values('bob', 200);
insert into bank_account(name, count) values('ron', 1000);


set session characteristics as transaction isolation level serializable;
start transaction;
insert into bank_account(name, count) values('mag', 123);
savepoint p1;
update bank_account set count = 50 where name = 'max';
savepoint p2;
delete from bank_account where count = 1000;
release savepoint p2;

savepoint p3;
insert into bank_account(name, count) values('Jack', 999);

select * from bank_account;
commit;