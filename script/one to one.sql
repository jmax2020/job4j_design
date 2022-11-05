create table snils(
    id serial primary key,
    number varchar(255)
);

create table woman(
    id serial primary key,
    name varchar(255),
	snils_id int references snils(id) unique 
);

insert into snils(number) values ('111-222-333 45');

insert into woman(name, snils_id) values ('Liza', 1);
