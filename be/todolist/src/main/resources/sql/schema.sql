DROP TABLE IF EXISTS TODO_CARD;

CREATE TABLE TODO_CARD
(
    id             INT          PRIMARY KEY     AUTO_INCREMENT,
    userid         VARCHAR(255) NOT NULL,
    title          VARCHAR(255) NOT NULL,
    content        VARCHAR(500),
    sequence       INT          NOT NULL,
    status         INT          NOT NULL,
    is_deleted     BOOLEAN      NOT NULL        DEFAULT FALSE
);

DROP TABLE IF EXISTS ACTIVITY_LOG;

CREATE TABLE ACTIVITY_LOG
(
    id             INT            PRIMARY KEY     AUTO_INCREMENT,
    userid         VARCHAR(255)   NOT NULL,
    type           INT            NOT NULL,
    previous       INT            NOT NULL,
    status         INT            NOT NULL,
    time           TIMESTAMP      NOT NULL
);
