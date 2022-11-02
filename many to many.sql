create table language(
    id serial primary key,
    name varchar(255)
);

create table man(
    id serial primary key,
    name varchar(255)
);

create table communication(
    id serial primary key,
    language_id int references language(id),
	man_id int references man(id)
);

insert into language(name) values ('Russian');
insert into language(name) values ('China');


insert into man(name) values ('Ivan');
insert into man(name) values ('Max');
insert into man(name) values ('Bob');

insert into communication(language_id, man_id) values (1, 1);
insert into communication(language_id, man_id) values (1, 2);
insert into communication(language_id, man_id) values (2, 2);
insert into communication(language_id, man_id) values (2, 3);

