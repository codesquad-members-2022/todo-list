CREATE TABLE column
(
    id       INTEGER AUTO_INCREMENT NOT NULL,
    name     VARCHAR(255) NOT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE `user`
(
    id       VARCHAR(255) NOT NULL,
    name     VARCHAR(255) NOT NULL,
    password VARCHAR(255) NOT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE card
(
     id BIGINT AUTO_INCREMENT NOT NULL,
     user_id VARCHAR(255) NOT NULL,
     column_id INT NOT NULL,
     subject VARCHAR(255) NOT NULL,
     contents VARCHAR(255) NOT NULL,
     create_time TIMESTAMP NOT NULL,
     update_time TIMESTAMP,
     deleted boolean DEFAULT FALSE,
     PRIMARY KEY (id),
     FOREIGN KEY (user_id) REFERENCES user (id),
     FOREIGN KEY (column) REFERENCES column (id) DELETE ON CASCADE
);

CREATE TABLE log
(
    id BIGINT AUTO_INCREMENT NOT NULL,
    user_id VARCHAR(255) NOT NULL,
    subject VARCHAR(255) NOT NULL,
    `from` INTEGER,
    `to` INTEGER,
    active_time TIMESTAMP NOT NULL,
    activity VARCHAR(255) NOT NULL,
    PRIMARY KEY (id),
    FOREIGN KEY (user_id) REFERENCES user (id),
    FOREIGN KEY (from) REFERENCES column (id),
    FOREIGN KEY (to) REFERENCES column (id)
);
