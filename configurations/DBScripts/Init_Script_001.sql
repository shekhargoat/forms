CREATE SCHEMA IF NOT EXISTS `forms_001` DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci ;
USE `forms_001` ;

-- -----------------------------------------------------
-- Table `forms_001`.`appuser`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `forms_001`.`appuser` ;

CREATE TABLE IF NOT EXISTS `forms_001`.`appuser` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `username` VARCHAR(100) NOT NULL,
  `password` VARCHAR(100) NULL,
  `first_name` VARCHAR(200) NULL,
  `last_name` VARCHAR(200) NULL,
  `contact_phone_numer` VARCHAR(10) NULL,
  `preferred_language` ENUM('MAR','KAN') NOT NULL,
  `is_enabled` TINYINT(1) NOT NULL,
  `account_created_on` DATETIME NOT NULL,
  `account_last_accessed` DATETIME NOT NULL,
  `sid` BINARY(32) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `username_UNIQUE` (`username` ASC),
  UNIQUE INDEX `password_UNIQUE` (`password` ASC),
  UNIQUE INDEX `sid_UNIQUE` (`sid` ASC))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `forms_001`.`districts`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `forms_001`.`districts` ;

CREATE TABLE IF NOT EXISTS `forms_001`.`districts` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NOT NULL,
  `state_name` ENUM('KAR','MAH') NOT NULL,
  `sid` BINARY(32) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `sid_UNIQUE` (`sid` ASC))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `forms_001`.`society_name`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `forms_001`.`society_name` ;

CREATE TABLE IF NOT EXISTS `forms_001`.`society_name` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `society_name` TEXT NOT NULL,
  `society_code` VARCHAR(45) NOT NULL,
  `address` TEXT NULL,
  `district_id` INT NULL,
  `sid` BINARY(32) NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `index2` (`society_code` ASC),
  UNIQUE INDEX `sid_UNIQUE` (`sid` ASC),
  CONSTRAINT `fk_society_name_1`
    FOREIGN KEY (`id`)
    REFERENCES `forms_001`.`districts` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `forms_001`.`financial_year`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `forms_001`.`financial_year` ;

CREATE TABLE IF NOT EXISTS `forms_001`.`financial_year` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(15) NOT NULL,
  `financial_year_duration` VARCHAR(45) NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `forms_001`.`forms`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `forms_001`.`forms` ;

CREATE TABLE IF NOT EXISTS `forms_001`.`forms` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `form_name` VARCHAR(45) NOT NULL,
  `appuser_id` INT NOT NULL COMMENT '				',
  `society_id` INT NOT NULL,
  `financial_year_id` INT NOT NULL,
  `date_created` DATETIME NOT NULL,
  `last_accessed_on` DATETIME NULL,
  `completed_on` DATETIME NULL,
  `status` ENUM('CREATED','PENDING','COMPLETED') NOT NULL,
  `form_language` ENUM('KAR','MAR') NOT NULL,
  `sid` BINARY(32) NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_appuser_has_forms_1_idx` (`appuser_id` ASC),
  INDEX `fk_appuser_has_forms_2_idx` (`financial_year_id` ASC),
  INDEX `fk_appuser_has_forms_3_idx` (`society_id` ASC),
  UNIQUE INDEX `sid_UNIQUE` (`sid` ASC),
  CONSTRAINT `fk_appuser_has_forms_1`
    FOREIGN KEY (`appuser_id`)
    REFERENCES `forms_001`.`appuser` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_appuser_has_forms_2`
    FOREIGN KEY (`financial_year_id`)
    REFERENCES `forms_001`.`financial_year` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_appuser_has_forms_3`
    FOREIGN KEY (`society_id`)
    REFERENCES `forms_001`.`society_name` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `forms_001`.`question`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `forms_001`.`question` ;

CREATE TABLE IF NOT EXISTS `forms_001`.`question` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `sid` BINARY(32) NOT NULL,
  `question_text` TEXT NOT NULL,
  `question_default_answer` VARCHAR(45) NULL,
  `is_mandatory` TINYINT(1) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `sid_UNIQUE` (`sid` ASC))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `forms_001`.`section`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `forms_001`.`section` ;

CREATE TABLE IF NOT EXISTS `forms_001`.`section` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `section_name` VARCHAR(100) NOT NULL,
  `sid` BINARY(32) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `sid_UNIQUE` (`sid` ASC))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `forms_001`.`form_has_sections`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `forms_001`.`form_has_sections` ;

CREATE TABLE IF NOT EXISTS `forms_001`.`form_has_sections` (
  `society_id` INT NOT NULL,
  `section_id` INT NOT NULL,
  PRIMARY KEY (`society_id`, `section_id`),
  INDEX `fk_form_has_sections_2_idx` (`section_id` ASC),
  CONSTRAINT `fk_form_has_sections_1`
    FOREIGN KEY (`society_id`)
    REFERENCES `forms_001`.`society_name` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_form_has_sections_2`
    FOREIGN KEY (`section_id`)
    REFERENCES `forms_001`.`section` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `forms_001`.`section_has_questions`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `forms_001`.`section_has_questions` ;

CREATE TABLE IF NOT EXISTS `forms_001`.`section_has_questions` (
  `section_id` INT NOT NULL,
  `question_id` INT NOT NULL,
  `is_mandatory` TINYINT(1) NULL,
  `default_answer` VARCHAR(45) NULL,
  PRIMARY KEY (`section_id`, `question_id`))
ENGINE = InnoDB;
