DROP TABLE IF EXISTS card;

CREATE TABLE card
(
    id          BIGINT PRIMARY KEY NOT NULL,
    title       varchar(50)        NOT NULL,
    contents    varchar(500)       NOT NULL,
    user_id      varchar(20)        NOT NULL,
    card_status varchar(10)               NOT NULL,
    isDeleted   BOOLEAN            NOT NULL,
    created_at   TIMESTAMP          NOT NULL
);
