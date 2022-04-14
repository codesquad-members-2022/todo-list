DROP table if EXISTS users CASCADE;

CREATE TABLE users (
    id BIGINT AUTO_INCREMENT,
    name VARCHAR(16),
    PRIMARY KEY (id)
);
