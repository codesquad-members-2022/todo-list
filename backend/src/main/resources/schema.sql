drop table if exists card;
drop table if exists log;

CREATE TABLE card (
    id int PRIMARY KEY AUTO_INCREMENT,
    order_index int NOT NULL,
    delete_yn tinyint(1) NOT NULL,
    title varchar(255) NOT NULL,
    content varchar(255) NOT NULL,
    section enum('todo', 'doing', 'done') NOT NULL
);



CREATE TABLE log (
     id int PRIMARY KEY AUTO_INCREMENT,
     event enum('create', 'update', 'move', 'delete') NOT NULL,
     title varchar(255) NOT NULL,
     log_time dateTime NOT NULL,
     prev_section varchar(255),
     section varchar(255) NOT NULL
);
