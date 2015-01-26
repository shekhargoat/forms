-- Create script of state table.

DROP TABLE IF EXISTS `state` ;
CREATE TABLE IF NOT EXISTS state (id INT primary key auto_increment,
sid BINARY(32),name VARCHAR(45),state_code VARCHAR(45));

-- Create script of language
DROP TABLE IF EXISTS `language`;
CREATE TABLE IF NOT EXISTS language (id int primary key auto_increment,sid binary(32),
name varchar(45),language_code varchar(45));

-- Create script of security_questions.
DROP TABLE IF EXISTS `security_questions`;
CREATE TABLE IF NOT EXISTS security_questions
 (id int primary key auto_increment,sid binary(32),
name varchar(45));

ALTER TABLE `appuser` 
ADD COLUMN `activation_key` VARCHAR(1000) NULL AFTER `sid`;


ALTER TABLE `appuser_security_questions_answer` 
ADD CONSTRAINT `fk_appuser_security_questions_answer_1`
  FOREIGN KEY (`appuser_id`)
  REFERENCES `appuser` (`id`)
  ON DELETE NO ACTION
  ON UPDATE NO ACTION,
ADD CONSTRAINT `fk_appuser_security_questions_answer_2`
  FOREIGN KEY (`security_question_id`)
  REFERENCES `security_questions` (`id`)
  ON DELETE NO ACTION
  ON UPDATE NO ACTION;

ALTER TABLE `forms_001`.`appuser` 
DROP INDEX `password_UNIQUE` ;


