drop table if exists card;
drop table if exists activity_log;

create table card (
    id bigint not null auto_increment,
    title varchar(500) not null,
    contents varchar(500),
    card_status varchar(20) not null,
    author varchar(10) not null,
    update_datetime timestamp not null default current_timestamp,
    primary key (id)
);

create table activity_log (
    id bigint not null auto_increment,
    activity_log_action varchar(10) not null,
    title varchar(500) not null,
    now_status varchar(20) not null,
    before_status varchar(20),
    create_date timestamp not null default current_timestamp,
    primary key (id)
);
