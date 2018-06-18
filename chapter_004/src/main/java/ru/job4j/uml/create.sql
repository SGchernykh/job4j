create database uml;

create table rules(
 id serial primary key,
 rules varchar (50),
 role_id integer not null references rules(id)
);

create table role (
id serial primary key,
role varchar (50)
);

create table users (
  id serial primary key,
  name varchar(50),
  role_id integer not null references role(id)
);

create table category (
id serial primary key,
category varchar (50)
);

create table state (
id serial primary key,
state varchar (50)
);

create table item (
id serial primary key,
name varchar (50),
user_id integer not null references users(id),
category_id integer not null references category(id),
item_status integer not null references state(id)
);

create table comments (
id serial primary key,
commentary varchar (255),
create_date timestamp ,
item_id integer not null references item(id)
);

create table files(
id serial primary key,
file_path varchar (1000),
item_id integer not null references item(id)
);

insert into  role (role) values ('User');
insert into role (role) values ('Admin');

insert into rules (rules, role_id) values ('read', 1);
insert into rules (rules, role_id) values ('write', 2);
insert into rules (rules, role_id) values ('read', 2);

insert into users (name, role_id) values ('Alisa', 1);
insert into users (name, role_id) values ('Bob', 2);

insert into category (category) values ('read');
insert into category (category) values ('write');

insert into state (state) values ('is pending');
insert into state (state) values ('is fulfilled');
insert into state (state) values ('is finished');

INSERT INTO item (name, user_id, category_id, item_status) VALUES ('The text item', 1, 1, 1);
INSERT INTO item (name, user_id, category_id, item_status) VALUES ('The text item', 2, 2, 1);

INSERT INTO comments (commentary, create_date, item_id) VALUES ('The text comments.', NOW(), 1);
INSERT INTO comments (commentary, create_date, item_id) VALUES ('The text comments.', NOW(), 1);

INSERT INTO files (file_path, item_id) VALUES ('file/1', 1);
INSERT INTO files (file_path, item_id) VALUES ('file/2', 2);






