# --- First database schema

# --- !Ups
create table user (
  id                        bigint not null primary key auto_increment,
  name                      varchar(255) not null,
  email                     varchar(255),
  company                   varchar(255)
);

insert into user(id,name,email,company) values(1, 'Alice', 'alice@foo.com', 'Foo Inc.');
insert into user(id,name,email,company) values(2, 'Bob', 'bob@foo.com', 'Foo Inc.');
insert into user(id,name,email,company) values(3, 'Charlie', 'charlie@bar.com', 'Bar Inc.');

# --- !Downs
drop table if exists user;
