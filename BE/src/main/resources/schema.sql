SET FOREIGN_KEY_CHECKS = 0;

DROP table if EXISTS users CASCADE;

CREATE TABLE users
(
    id   BIGINT AUTO_INCREMENT,
    name VARCHAR(16),
    PRIMARY KEY (id)
);


DROP table if EXISTS works CASCADE;

CREATE TABLE works
(
    id   BIGINT AUTO_INCREMENT,
    title VARCHAR(64) NOT NULL,
    content TEXT NOT NULL,
    author_id BIGINT,
    work_status VARCHAR(32),
    status_index INT,
    create_date_time TIMESTAMP,
    last_modified_date_time TIMESTAMP,
    deleted BOOLEAN DEFAULT false,
    CONSTRAINT users_pk FOREIGN KEY (author_id) REFERENCES users (id) ON DELETE CASCADE,
    PRIMARY KEY (id)
);

SET FOREIGN_KEY_CHECKS = 1;
