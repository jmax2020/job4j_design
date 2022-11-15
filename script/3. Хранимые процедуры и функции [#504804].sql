create table products (
    id serial primary key,
    name varchar(50),
    producer varchar(50),
    count integer default 0,
    price integer
);

insert into products (name, producer, count, price) values ('CHOKOLAD', 'Milka', 50, null);
insert into products (name, producer, count, price) values ('MILK', 'Prostokvashino', 1, 130);
insert into products (name, producer, count, price) values ('BATON', 'Moskow', 10, 200);
insert into products (name, producer, count, price) values ('FISH', 'Dary Morya', 2, 300);
insert into products (name, producer, count, price) values ('Arakhis', 'OOO Azer', 500, 121);

create or replace procedure del_price(i_price integer)
language 'plpgsql'
as
$$
    BEGIN
        delete from products
		where price < i_price or price is null;
    END;
$$;

create or replace function del_count(i_count integer)
returns integer
language 'plpgsql'
as
$$
	DECLARE rsl integer;
    BEGIN
		select into rsl count from products
		where count <= i_count;
        delete from products
		where count <= i_count;
		return rsl;
    END;
$$;
call  del_price(150);
select del_count(5);
select * from products;