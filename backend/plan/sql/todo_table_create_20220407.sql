USE `todolist` ;

-- -----------------------------------------------------
-- Table `todolist`.`member`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `todolist`.`member` ;

CREATE TABLE IF NOT EXISTS `todolist`.`member` (
  `id` BIGINT NOT NULL,
  `user_id` VARCHAR(50) NOT NULL,
  `name` VARCHAR(50) NULL,
  `password` VARCHAR(50) NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `todolist`.`card_section_code`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `todolist`.`card_section_code` ;

CREATE TABLE IF NOT EXISTS `todolist`.`card_section_code` (
  `id` TINYINT NOT NULL,
  `flag_description` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `todolist`.`card`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `todolist`.`card` ;

CREATE TABLE IF NOT EXISTS `todolist`.`card` (
  `id` BIGINT NOT NULL,
  `title` VARCHAR(30) NOT NULL,
  `content` VARCHAR(500) NULL,
  `created_at` TIMESTAMP NOT NULL,
  `modified_at` TIMESTAMP NOT NULL,
  `delete_flag` BIT(1) NOT NULL,
  `member_id` BIGINT NOT NULL,
  `card_section_code_id` TINYINT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_card_member_idx` (`member_id` ASC) VISIBLE,
  INDEX `fk_card_card_section_code1_idx` (`card_section_code_id` ASC) VISIBLE,
  CONSTRAINT `fk_card_member`
    FOREIGN KEY (`member_id`)
    REFERENCES `todolist`.`member` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_card_card_section_code1`
    FOREIGN KEY (`card_section_code_id`)
    REFERENCES `todolist`.`card_section_code` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `todolist`.`card_action_code`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `todolist`.`card_action_code` ;

CREATE TABLE IF NOT EXISTS `todolist`.`card_action_code` (
  `id` TINYINT NOT NULL,
  `code_description` VARCHAR(20) NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `todolist`.`card_action_log`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `todolist`.`card_action_log` ;

CREATE TABLE IF NOT EXISTS `todolist`.`card_action_log` (
  `id` BIGINT NOT NULL,
  `created_at` TIMESTAMP NOT NULL,
  `card_id` BIGINT NOT NULL,
  `member_id` BIGINT NOT NULL,
  `card_action_code_id` TINYINT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_card_action_log_card1_idx` (`card_id` ASC) VISIBLE,
  INDEX `fk_card_action_log_member1_idx` (`member_id` ASC) VISIBLE,
  INDEX `fk_card_action_log_card_action_code1_idx` (`card_action_code_id` ASC) VISIBLE,
  CONSTRAINT `fk_card_action_log_card1`
    FOREIGN KEY (`card_id`)
    REFERENCES `todolist`.`card` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_card_action_log_member1`
    FOREIGN KEY (`member_id`)
    REFERENCES `todolist`.`member` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_card_action_log_card_action_code1`
    FOREIGN KEY (`card_action_code_id`)
    REFERENCES `todolist`.`card_action_code` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;
