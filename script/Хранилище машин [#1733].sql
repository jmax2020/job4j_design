create table car_bodies(
id serial primary key,
name text
);

create table car_engines(
id serial primary key,
name text);

create table car_transmissions(
id serial primary key,
name text);

create table cars(
id serial primary key,
name text,
body_id int references car_bodies(id),
engine_id int references car_engines(id),
transmission_id int references car_transmissions(id)
);

insert into car_bodies(name) values ('купе');
insert into car_bodies(name) values ('универсал');
insert into car_bodies(name) values ('седан');
insert into car_bodies(name) values ('джип');

insert into car_engines(name) values ('100 л.с.');
insert into car_engines(name) values ('150 л.с.');
insert into car_engines(name) values ('200 л.с.');
insert into car_engines(name) values ('250 л.с.');

insert into car_transmissions(name) values ('автомат');
insert into car_transmissions(name) values ('механика');
insert into car_transmissions(name) values ('вариатор');
insert into car_transmissions(name) values ('робот');

insert into cars(name, body_id, engine_id, transmission_id)
values ('mazda 6', 2, 2, 1);
insert into cars(name, body_id, engine_id, transmission_id)
values ('peugeot 4007', 3, 2, 3);
insert into cars(name, body_id, engine_id, transmission_id)
values ('VAZ 2109', 2, 1, 2);
insert into cars(name, body_id, engine_id, transmission_id)
values ('mazda rx-8', 1, 3, null);

select c.id, c.name as car_name, cb.name as body_name, ce.name as engine_name,
ct.name as transmission_name from cars c
left join car_bodies cb on c.body_id = cb.id
left join car_engines ce on c.engine_id = ce.id
left join car_transmissions ct on c.transmission_id = ct.id;

select cb.name from car_bodies cb left join cars c on cb.id = c.body_id
where c.name is null;

select ce.name from car_engines ce left join cars c on ce.id = c.engine_id
where c.name is null;

select ct.name from car_transmissions ct left join cars c on ct.id = c.transmission_id
where c.name is null;