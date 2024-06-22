set schema PUBLIC;
create table users(
    "USERNAME" varchar(20) PRIMARY KEY ,
    "PASSWORD" varchar(20) not null
)