insert into role(name) values('admin');
insert into role(name) values('user');

insert into users(name, role_id) values ('Max', 1);
insert into users(name, role_id) values ('Bob', 2);

insert into rules(name) values('read');
insert into rules(name) values('write');
insert into rules(name) values('delete');

insert into role_rules(role_id, rules_id) values(1, 1);
insert into role_rules(role_id, rules_id) values(1, 2);
insert into role_rules(role_id, rules_id) values(1, 3);
insert into role_rules(role_id, rules_id) values(2, 1);

insert into category(name) values('simple');
insert into category(name) values('important');

insert into state(name) values('created');
insert into state(name) values('completed');

insert into item(name, users_id, category_id, state_id)
values('install windows', 1, 1, 2);
insert into item(name, users_id, category_id, state_id)
values('learn java', 2, 2, 1);

insert into comments(name, item_id) values('windows has been installed', 1);
insert into comments(name, item_id) values('one grain at a time', 2);
insert into comments(name, item_id) values('i love java', 2);

insert into attachs(name, item_id) values('windows.jpg', 1);
insert into attachs(name, item_id) values('java.jpg', 2);
insert into attachs(name, item_id) values('Effective Java.pdf', 2);




