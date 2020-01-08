-- -----------------------------------------------------
-- Schema user
-- -----------------------------------------------------
DROP SCHEMA IF EXISTS `User` ;

-- -----------------------------------------------------
-- Schema user
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `User` DEFAULT CHARACTER SET utf8 ;
USE `User` ;

-- -----------------------------------------------------
-- Table `officialLeague`.`Official`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `User`.`User` (
  `id` 			INT NOT NULL AUTO_INCREMENT,
  `firstname`	VARCHAR(45) NOT NULL,
  `lastname`	VARCHAR(45) NOT NULL,
  `email`		VARCHAR(45) NOT NULL UNIQUE,
  `password`	VARCHAR(255) NOT NULL,
  PRIMARY KEY (`id`)
)
ENGINE = InnoDB CHARACTER SET = utf8;