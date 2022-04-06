DROP TABLE IF EXISTS `user`;

CREATE TABLE `user` (
    `userId` VARCHAR(50) NOT NULL,
    `password` VARCHAR(50) NOT NULL,
    `remove` TINYINT(1) NOT NULL DEFAULT 0,
    PRIMARY KEY (`userId`)
);

DROP TABLE IF EXISTS `card`;

CREATE TABLE `card` (
    `cardId` INT NOT NULL AUTO_INCREMENT,
    `userId` VARCHAR(50) NOT NULL,
    `cardTitle` VARCHAR(50) NOT NULL,
    `cardContent` VARCHAR(255) NOT NULL,
    `boardName` VARCHAR(50) NOT NULL,
    `internalOrder` INT NOT NULL,
    `addDateTime` TIMESTAMP NOT NULL,
    `remove` TINYINT(1) NOT NULL DEFAULT 0,
    PRIMARY KEY (`cardId`),
    FOREIGN KEY (`userId`)
        REFERENCES `user` (`userId`)
        ON UPDATE CASCADE
);

DROP TABLE IF EXISTS `history`;

CREATE TABLE `history` (
    `historyId` INT NOT NULL AUTO_INCREMENT,
    `userId` VARCHAR(50) NOT NULL,
    `actionHistory` VARCHAR(100) NOT NULL,
    `actionTime` TIMESTAMP NOT NULL,
    PRIMARY KEY (`historyId`),
    FOREIGN KEY (`userId`)
        REFERENCES `user` (`userId`)
        ON UPDATE CASCADE
);
