CREATE TABLE card
(
     id BIGINT AUTO_INCREMENT NOT NULL,
     user_name VARCHAR(255) NOT NULL,
     column INT NOT NULL,
     subject VARCHAR(255) NOT NULL,
     contents VARCHAR(255) NOT NULL,
     create_time TIMESTAMP NOT NULL,
     update_time TIMESTAMP,
     deleted boolean DEFAULT FALSE,
     PRIMARY KEY (id)
);

CREATE TABLE `user`
(
    id       VARCHAR(255) NOT NULL,
    name     VARCHAR(255) NOT NULL,
    password VARCHAR(255) NOT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE log
(
    id BIGINT AUTO_INCREMENT NOT NULL,
    user_name VARCHAR(255) NOT NULL,
    subject VARCHAR(255) NOT NULL,
    `from` INT,
    `to` INT,
    active_time TIMESTAMP NOT NULL,
    activity VARCHAR(255) NOT NULL,
    PRIMARY KEY (id)
);
