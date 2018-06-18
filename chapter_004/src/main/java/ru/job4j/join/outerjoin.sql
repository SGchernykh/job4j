create table transmission (
id serial primary key,
name varchar (50)
);

create table bodies (
id serial primary key,
name varchar (50)
);


create table engines (
id serial primary key,
name varchar (50)
);


create table cars (
car_id serial primary key,
name varchar (50),
transmission_id int not null references transmission (id),
bodie_id int not null references bodies (id),
engine_id  int not null references engines (id)
);

insert into transmission (name) values ('trans1');
insert into transmission (name) values ('trans2');
insert into transmission (name) values ('trans3');
insert into transmission (name) values ('trans4');

insert into bodies (name) values ('bodies1');
insert into bodies (name) values ('bodies2');
insert into bodies (name) values ('bodies3');
insert into bodies (name) values ('bodies4');

insert into engines (name) values ('engines1');
insert into engines (name) values ('engines2');
insert into engines (name) values ('engines3');
insert into engines (name) values ('engines4');

insert into cars (name, transmission_id, bodie_id, engine_id) values ('car1', 1, 1, 1);
insert into cars (name, transmission_id, bodie_id, engine_id) values ('car2', 2, 2, 2);
insert into cars (name, transmission_id, bodie_id, engine_id) values ('car3', 3, 3, 3);

select c.car_id, c.name, t.name, b.name, e.name from cars as c
inner join engines as e on c.engine_id = e.id
inner join bodies as b on c.bodie_id = b.id
inner join transmission as t on c.transmission_id = t.id;

select t.name from cars as c
right outer join transmission t on c.transmission_id = t.id
where c.name is null;

select b.name from cars as c
right outer join bodies b on c.bodie_id = b.id
where c.name is null;

select e.name from cars as c
right outer join engines e on c.engine_id = e.id
where c.name is null;