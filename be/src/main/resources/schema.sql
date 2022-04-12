drop table if exists todos;
drop table if exists events;


create table todos
(
    id      bigint primary key auto_increment,
    section varchar(30) not null,
    title   varchar(60) not null,
    content varchar(60) not null
);



create table events
(
    id             bigint primary key auto_increment,
    cardTitle      varchar(60) not null,
    prevSection    varchar(60) not null,
    currentSection varchar(60) not null,
    action         enum('ADD', 'UPDATE', 'MOVE', 'REMOVE') not null,
    createdAt      timestamp default CURRENT_TIMESTAMP
);
