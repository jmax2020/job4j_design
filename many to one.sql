create table oil(
    id serial primary key,
    name varchar(255)
);

create table car(
    id serial primary key,
    name varchar(255),
    oil_id int references oil(id)
);

insert into oil(name) values ('benzin');
insert into car(name, oil_id) values ('Lada', 1);

select * from oil where id in (select oil_id from car);
