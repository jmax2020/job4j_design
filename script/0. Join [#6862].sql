create table departments(
id serial primary key,
name text
);

create table employees(
id serial primary key,
name text,
departments_id int references departments(id)
);

insert into departments (name) values ('HR');
insert into departments (name) values ('IT');
insert into departments (name) values ('FD');
insert into departments (name) values ('PR');

insert into employees (name, departments_id) values ('Max', 2);
insert into employees (name, departments_id) values ('Bob', 2);
insert into employees (name, departments_id) values ('Luiza', 1);
insert into employees (name, departments_id) values ('Gleb', 3);
insert into employees (name, departments_id) values ('Mark', 3);
insert into employees (name, departments_id) values ('Olivia', 1);
insert into employees (name, departments_id) values ('Zoya', null);
insert into employees (name, departments_id) values ('Sasha', null);

select e.name, d.name from employees e left join departments d on e.departments_id = d.id;
select e.name, d.name from employees e right join departments d on e.departments_id = d.id;
select e.name, d.name from employees e full join departments d on e.departments_id = d.id;
select e.name, d.name from employees e cross join departments d;
select d.name from departments d left join employees e on d.id = e.departments_id where e.name is null;

select d.name, e.name from departments d left join employees e on d.id = e.departments_id;
select d.name, e.name from employees e right join departments d on d.id = e.departments_id;

create table teens(
id serial primary key,
name text,
gender bool
);

insert into teens (name, gender) values ('Max', true);
insert into teens (name, gender) values ('Yulya', false);
insert into teens (name, gender) values ('Bob', true);
insert into teens (name, gender) values ('Mark', true);
insert into teens (name, gender) values ('Sonya', false);
insert into teens (name, gender) values ('Zoya', false);
insert into teens (name, gender) values ('Jon', true);
insert into teens (name, gender) values ('Soul', true);

select m.name, f.name from teens m cross join teens f where m.gender and not f.gender;
