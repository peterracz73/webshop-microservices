create table product (id int8 not null, name varchar(255), price float8 not null, category_id int4, primary key (id));
create table product_aud (id int8 not null, rev int4 not null, revtype int2, name varchar(255), price float8, category_id int4, primary key (id, rev));
