# --- !Ups

create table "posts" ("id" bigint primary key,"title" varchar(200) not null, "content" varchar(2000) not null);

# --- !Downs

drop table "posts";
