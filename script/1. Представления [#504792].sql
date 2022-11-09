create table universities(
	id serial primary key,
	name text
);

create table faculties(
	id serial primary key,
	name text,
	university_id int references universities(id)
);

create table departments(
	id serial primary key,
	name text,
	university_id int references universities(id)
);

create table students(
	id serial primary key,
	name text,
	faculty_id int references faculties(id)
);

create table teachers(
	id serial primary key,
	name text,
	department_id int references departments(id)
);

create view show_students
	as select s.name as name, f.name as faculty, u.name as university from students s
	join faculties f on f.id = s.faculty_id
	join universities u on u.id = f.university_id;

create view show_teachers
	as select t.name as name, d.name as department, u.name as university
	from teachers t
	join departments d on d.id = t.department_id
	join universities u on u.id = d.university_id;

insert into universities(name) values ('SibGAU');
insert into universities(name) values ('KGU');

insert into faculties(name, university_id) values ('FISU', 1);
insert into faculties(name, university_id) values ('FGA', 1);
insert into faculties(name, university_id) values ('KPU', 2);

insert into students(name, faculty_id) values ('Max', 1);
insert into students(name, faculty_id) values ('Bob', 1);
insert into students(name, faculty_id) values ('Ross', 2);
insert into students(name, faculty_id) values ('Fibi', 3);

select ss.university, count(ss.university) as numb_stud from show_students ss
group by ss.university;