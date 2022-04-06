DROP TABLE IF EXISTS TODO;
CREATE TABLE TODO
(
    id   BIGINT NOT NULL AUTO_INCREMENT,
    title        VARCHAR(255),
    contents     VARCHAR(500),
    user_id       VARCHAR(100),
    status         VARCHAR(10),
    created_time TIMESTAMP,
    updated_time TIMESTAMP,
    PRIMARY KEY (id)
);
