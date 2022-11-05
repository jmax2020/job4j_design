create table man(
id serial primary key,
name text
);

create table car(
id serial primary key,
name text,
man_id int references man(id)
);

insert into man(name) values('Max');
insert into man(name) values('Bob');

insert into car(name, man_id) values('mazda', 1);
insert into car(name, man_id) values('audi', 1);
insert into car(name, man_id) values('vaz', 2);

select c.name, c.man_id from car c join man m on c.man_id = m.id;

select * from car as c join man as m on c.man_id = m.id;

select c.name as Mashina, m.name Chelovek from car c inner join man m on c.man_id = m.id;