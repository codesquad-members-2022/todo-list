DROP TABLE IF EXISTS card, log;
CREATE TABLE card (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    card_index INT NOT NULL,
    title VARCHAR(50) NOT NULL,
    contents VARCHAR(500),
    writer VARCHAR(20) NOT NULL,
    card_status ENUM('TODO', 'PROGRESS', 'DONE'),
    created_date DATETIME DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE log
(
    id                  BIGINT AUTO_INCREMENT PRIMARY KEY,
    title               varchar(50) NOT NULL,
    current_status      ENUM('TODO', 'PROGRESS', 'DONE'),
    prev_status         ENUM('TODO', 'PROGRESS', 'DONE') default NULL,
    action_status       ENUM('ADD', 'REMOVE', 'UPDATE', 'MOVE'),
    action_time        DATETIME DEFAULT CURRENT_TIMESTAMP
);

