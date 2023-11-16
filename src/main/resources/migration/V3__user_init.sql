create table users(
    id int auto_increment primary key,
    username varchar(50) not null unique,
    password varchar(50) not null,
    role varchar(10) not null,
    enabled BOOL DEFAULT true
);
