create table types (
id serial primary key,
name varchar (50)
);

create table product (
id serial primary key,
name varchar (50),
type_id integer not null references types (id),
expired_date timestamp,
price integer
);

insert into types (name) values ('мясо');
insert into types (name) values ('молочка');
insert into types (name) values ('сыр');

insert into product (name, type_id, expired_date, price) values ('Ракфор', 3, '1999-01-08 04:05:06', 500);
insert into product (name, type_id, expired_date, price) values ('Филодельфия', 3, '1999-01-08 04:05:06', 1500);
insert into product (name, type_id, expired_date, price) values ('Фруктовое мороженное', 2, '1999-01-08 04:05:06', 3);
insert into product (name, type_id, expired_date, price) values ('Магнат мороженное', 2, '1999-01-08 04:05:06', 3);
insert into product (name, type_id, expired_date, price) values ('Курица', 1, '1999-01-08 04:05:06', 3);
insert into product (name, type_id, expired_date, price) values ('Сладко мороженное аленка', 2, '1999-03-08 04:05:06', 3);

select p.id, p.name, t.name, p.expired_date, p.price from product as p
inner join types as t on t.id = p.type_id
where t.name = 'сыр';

select p.name from product
where name like '%мороженное%';

select * from product
where expired_date between '1999-03-01 00:00:00' and '1999-03-31 23:59:59';

select * from product
where price = (select max(price) from product);

select count (*) from product
where type_id = 3;

select p.id, p.name, t.name, p.expired_date, p.price from product as p
inner join types as t on t.id = p.type_id
where t.name = 'сыр' or t.name = 'молочка';

select t.name, (count (p.type_id)) from product as p
inner join types as t on t.id = p.type_id
group by t.name
having count (p.type_id) < 10;

select p.id, p.name, t.name, p.expired_date, p.price from product as p
inner join types as t on t.id = p.type_id;