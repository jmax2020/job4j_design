create table products (
    id serial primary key,
    name varchar(50),
    producer varchar(50),
    count integer default 0,
    price integer
);

create or replace function nalog()
    returns trigger as
$$
    BEGIN
        update products
        set price = price * 1.2
        where id = (select id from inserted);
        return NEW;
    END;
$$
LANGUAGE 'plpgsql';

create trigger nalog_trigger_after
    after insert on products
	referencing new table as inserted
    for each statement
    execute procedure nalog();

create or replace function nalog_row()
	returns trigger as
$$
	BEGIN
		new.price = new.price * 1.2;
		return NEW;
	END;
$$
LANGUAGE 'plpgsql';

create trigger nalog_trigger_before
	before insert on products
	for each row
	execute procedure nalog_row();

create table history_of_price (
    id serial primary key,
    name varchar(50),
    price integer,
    date timestamp
);

create or replace function insert_history()
	returns trigger as
$$
	BEGIN
		insert into history_of_price select new.id, new.name, new.price, now();
		return NEW;
	END;
$$
LANGUAGE 'plpgsql';

create trigger history_price
	after insert on products
	for each row
	execute procedure insert_history();
