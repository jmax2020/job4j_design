create table type(
id serial primary key,
name text
);

create table product(
id serial primary key,
name text,
type_id int references type(id),
expired_date date,
price float
);

insert into type(name) values ('СЫР');
insert into type(name) values ('МЯСО');
insert into type(name) values ('МОЛОКО');

insert into product(name, type_id, expired_date, price)
values ('Гауда', 1, '2022-11-01', 600);
insert into product(name, type_id, expired_date, price)
values ('мороженое эскимо', 3, '2022-11-30', 50);
insert into product(name, type_id, expired_date, price)
values ('окорок свиной', 2, '2022-11-08', 300);
insert into product(name, type_id, expired_date, price)
values ('Пармезан', 1, '2022-11-20', 1000);
insert into product(name, type_id, expired_date, price)
values ('Хамон', 2, '2022-11-20', 1000);

select * from product p
join type t on p.type_id = t.id
where t.name = 'СЫР';

select * from product p
where p.name like '%мороженое%';

select * from product p
join type t on p.type_id = t.id
where p.expired_date < now();

select p1.name, maxp.mprice from product p1
join
(select max(price) as mprice from product) maxp
on maxp.mprice = p1.price;

select type.name, count(type_id) from product
join type on product.type_id = type.id
group by type.name;

select * from product
join type on product.type_id = type.id
where type.name = 'СЫР' or type.name = 'МОЛОКО';

select type.name, count(type_id) from product
join type on product.type_id = type.id
group by type.name
having count(type_id) < 10;

select * from product
join type on product.type_id = type.id;

