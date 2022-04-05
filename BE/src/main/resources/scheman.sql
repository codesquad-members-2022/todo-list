DROP TABLE IF EXISTS category;

CREATE TABLE category (
    id INT NOT NULL AUTO_INCREMENT,
    name VARCHAR(64) NOT NULL,
    PRIMARY KEY (id)
);

DROP TABLE IF EXISTS work;

CREATE TABLE work (
    id INT NOT NULL AUTO_INCREMENT,
    category_id INT NOT NULL,
    title VARCHAR(255),
    content VARCHAR(255),
    user_id VARCHAR(64),
    created_date TIMESTAMP NOT NULL,
    PRIMARY KEY (id),
    FOREIGN KEY (category_id) REFERENCES category(id)
);
