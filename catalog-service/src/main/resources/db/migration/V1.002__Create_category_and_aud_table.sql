create table category (id int4 not null, name varchar(255), primary key (id));
create table category_aud (id int4 not null, rev int4 not null, revtype int2, name varchar(255), primary key (id, rev));
