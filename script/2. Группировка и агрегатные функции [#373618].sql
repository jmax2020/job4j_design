create table devices(
    id serial primary key,
    name varchar(255),
    price float
);

create table people(
    id serial primary key,
    name varchar(255)
);

create table devices_people(
    id serial primary key,
    device_id int references devices(id),
    people_id int references people(id)
);

insert into devices(name, price) values ('phone', 10000.5);
insert into devices(name, price) values ('notebook', 70000.8);
insert into devices(name, price) values ('watch', 8000.1);

insert into people(name) values ('Max');
insert into people(name) values ('Bob');
insert into people(name) values ('Zag');

insert into devices_people (device_id, people_id) values (1, 1);
insert into devices_people (device_id, people_id) values (2, 1);
insert into devices_people (device_id, people_id) values (1, 2);
insert into devices_people (device_id, people_id) values (1, 3);
insert into devices_people (device_id, people_id) values (2, 3);
insert into devices_people (device_id, people_id) values (3, 3);

select avg(price) from devices;

select avg(d.price), p.name
from devices_people as dp
join devices d
on dp.device_id = d.id
join people p
on dp.people_id = p.id
group by p.name
having avg(d.price) > 5000;


