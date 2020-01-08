-- -----------------------------------------------------
-- Schema officialLeague
-- -----------------------------------------------------
DROP SCHEMA IF EXISTS `officialLeague` ;

-- -----------------------------------------------------
-- Schema officialLeague
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `officialLeague` DEFAULT CHARACTER SET utf8 ;
USE `officialLeague` ;

-- -----------------------------------------------------
-- Table `officialLeague`.`Team`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `officialLeague`.`Team` (
  `id` 		INT NOT NULL AUTO_INCREMENT,
  `name` 	VARCHAR(45) NOT NULL,
  `address` VARCHAR(45) NOT NULL,
  `zip` 	VARCHAR(45) NOT NULL,
  `city` 	VARCHAR(45) NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `officialLeague`.`Official`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `officialLeague`.`Official` (
  `id` 			INT NOT NULL AUTO_INCREMENT,
  `level`		INT NOT NULL,
  `idTeam` 		INT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_Official_Team_idx` (`idTeam` ASC),
  CONSTRAINT `fk_Official_Team`
    FOREIGN KEY (`idTeam`)
    REFERENCES `officialLeague`.`Team` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `officialLeague`.`Game`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `officialLeague`.`Game` (
  `id` 				INT NOT NULL AUTO_INCREMENT,
  `timestamp` 		DATETIME NOT NULL,
  `idTeamAway` 		INT NOT NULL,
  `idTeamHome` 		INT NOT NULL,
  `idReferee` 		INT NOT NULL,
  `idUmpire` 		INT NULL,
  `idChainJudge` 	INT NULL,
  `idLineJudge`		INT NULL,
  `idBackJudge` 	INT NULL,
  `idSideJudge` 	INT NULL,
  `idFieldJudge` 	INT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_Game_Team1_idx` (`idTeamAway` ASC),
  INDEX `fk_Game_Team2_idx` (`idTeamHome` ASC),
  INDEX `fk_Game_Referee_idx` (`idReferee` ASC),
  INDEX `fk_Game_Umpire_idx` (`idUmpire` ASC),
  INDEX `fk_Game_ChainJudge_idx` (`idChainJudge` ASC),
  INDEX `fk_Game_LineJudge_idx` (`idLineJudge` ASC),
  INDEX `fk_Game_BackJudge_idx` (`idBackJudge` ASC),
  INDEX `fk_Game_SideJudge_idx` (`idSideJudge` ASC),
  INDEX `fk_Game_FieldJudge_idx` (`idFieldJudge` ASC),
  CONSTRAINT `fk_Game_Team1`
    FOREIGN KEY (`idTeamAway`)
    REFERENCES `officialLeague`.`Team` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Game_Team2`
    FOREIGN KEY (`idTeamHome`)
    REFERENCES `officialLeague`.`Team` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Game_Referee`
    FOREIGN KEY (`idReferee`)
    REFERENCES `officialLeague`.`Official` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Game_Umpire`
    FOREIGN KEY (`idUmpire`)
    REFERENCES `officialLeague`.`Official` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Game_ChainJudge`
    FOREIGN KEY (`idChainJudge`)
    REFERENCES `officialLeague`.`Official` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Game_LineJudge`
    FOREIGN KEY (`idLineJudge`)
    REFERENCES `officialLeague`.`Official` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Game_BackJudge`
    FOREIGN KEY (`idBackJudge`)
    REFERENCES `officialLeague`.`Official` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Game_SideJudge`
    FOREIGN KEY (`idSideJudge`)
    REFERENCES `officialLeague`.`Official` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Game_FieldJudge`
    FOREIGN KEY (`idFieldJudge`)
    REFERENCES `officialLeague`.`Official` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB CHARACTER SET = utf8;