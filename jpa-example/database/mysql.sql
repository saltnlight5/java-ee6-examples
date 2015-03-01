create table users (
    username varchar(64) primary key,
    password varchar(256) not null,
    createdon datetime not null  default current_timestamp
);
insert into users(username, password) 
    values ('admin', 'admin123');
