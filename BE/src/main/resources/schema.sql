DROP TABLE IF EXISTS section;
DROP TABLE IF EXISTS member;
DROP TABLE IF EXISTS card;
DROP TABLE IF EXISTS log;

CREATE TABLE section
(
    id       INTEGER AUTO_INCREMENT NOT NULL,
    name     VARCHAR(255) NOT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE member
(
    id       VARCHAR(255) NOT NULL,
    name     VARCHAR(255) NOT NULL,
    password VARCHAR(255) NOT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE card
(
     id          BIGINT AUTO_INCREMENT NOT NULL,
     member_id   VARCHAR(255) NOT NULL,
     section_id  INTEGER NOT NULL,
     subject     VARCHAR(255) NOT NULL,
     contents    VARCHAR(255) NOT NULL,
     order_index BIGINT NOT NULL,
     created_at  TIMESTAMP NOT NULL,
     updated_at  TIMESTAMP,
     deleted     boolean DEFAULT FALSE,
     PRIMARY KEY (id),
     FOREIGN KEY (member_id)    REFERENCES member (id),
     FOREIGN KEY (section_id) REFERENCES section (id)
);

CREATE TABLE log
(
     id                  BIGINT AUTO_INCREMENT NOT NULL,
     member_id           VARCHAR(255) NOT NULL,
     previous_subject    VARCHAR(255) NOT NULL,
     current_subject     VARCHAR(255),
     previous_section_id INTEGER,
     current_section_id  INTEGER,
     activated_at        TIMESTAMP NOT NULL,
     activity            VARCHAR(255) NOT NULL,
     PRIMARY KEY (id),
     FOREIGN KEY (member_id)            REFERENCES member (id),
     FOREIGN KEY (previous_section_id)   REFERENCES section (id),
     FOREIGN KEY (current_section_id)    REFERENCES section (id)
);
