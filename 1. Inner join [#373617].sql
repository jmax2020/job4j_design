create table car(
id serial primary key,
name text
);

create table man(
id serial primary key,
name text,
car_id int references car(id)
);

insert into car(name) values('mazda');
insert into car(name) values('audi');
insert into car(name) values('vaz');

insert into man(name, car_id) values('Max', 1);
insert into man(name, car_id) values('Max', 2);
insert into man(name, car_id) values('Bob', 3);

select m.name, car_id from man m join car on m.car_id = car.id;

select * from man as m join car as c on m.car_id = c.id;

select m.name as Chelovek, c.name Mashina from man m inner join car c on m.car_id = c.id;