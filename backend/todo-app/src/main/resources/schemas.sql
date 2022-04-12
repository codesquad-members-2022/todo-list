drop table if exists history;
create table history(
    history_id int primary key auto_increment,
    action_type varchar(6) not null,
    card_title varchar(30) not null,
    past_location varchar(4) default null,
    now_location varchar(4) default null,
    history_date datetime not null
);