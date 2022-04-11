DROP TABLE IF EXISTS CARD, ACTION_LOG;
CREATE TABLE CARD (
    id              int AUTO_INCREMENT PRIMARY KEY,
    title           varchar(50) NOT NULL,
    content         varchar(500),
    author_system   varchar(20) NOT NULL,
    column_name     ENUM('to_do', 'in_progress', 'done'),
    deleted         tinyint DEFAULT false,
    order_index     int         NOT NULL
);

CREATE INDEX order_index ON CARD(order_index);
CREATE INDEX column_deleted_comp ON CARD(column_name, deleted);

CREATE TABLE ACTION_LOG
(
    id                  int AUTO_INCREMENT PRIMARY KEY,
    title               varchar(50) NOT NULL,
    cur_column_name     ENUM('to_do', 'in_progress', 'done'),
    prev_column_name    ENUM('to_do', 'in_progress', 'done') default NULL,
    action_type         ENUM('add', 'remove', 'update', 'move'),
    created_date        DATETIME DEFAULT CURRENT_TIMESTAMP
);
