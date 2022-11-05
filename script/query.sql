create table fauna (
    id serial primary key,
    name text,
    avg_age int,
    discovery_date date
);

insert into fauna(name, avg_age, discovery_date) values ('cat', 3650, '01.01.1745');
insert into fauna(name, avg_age, discovery_date) values ('dog', 5000, null);
insert into fauna(name, avg_age, discovery_date) values ('tarakan', 250, null);
insert into fauna(name, avg_age, discovery_date) values ('fish_shark', 10000, '01.01.1500');

select name, avg_age from fauna where name like '%fish%';
select name, avg_age from fauna where avg_age > 10000 and avg_age <21000;
select name from fauna where discovery_date is null;
select * from fauna where discovery_date < '1950-01-01';