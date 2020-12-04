-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema dogdb
-- -----------------------------------------------------
DROP SCHEMA IF EXISTS `dogdb` ;

-- -----------------------------------------------------
-- Schema dogdb
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `dogdb` DEFAULT CHARACTER SET utf8 ;
USE `dogdb` ;

-- -----------------------------------------------------
-- Table `address`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `address` ;

CREATE TABLE IF NOT EXISTS `address` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `street` VARCHAR(45) NULL,
  `city` VARCHAR(45) NULL,
  `state_abbrv` CHAR(2) NULL,
  `zipcode` VARCHAR(45) NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `user`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `user` ;

CREATE TABLE IF NOT EXISTS `user` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `first_name` VARCHAR(150) NULL,
  `last_name` VARCHAR(150) NULL,
  `username` VARCHAR(100) NOT NULL,
  `password` VARCHAR(200) NOT NULL,
  `email` VARCHAR(100) NOT NULL,
  `role` VARCHAR(45) NULL DEFAULT 'user',
  `enabled` TINYINT NOT NULL,
  `profile_photo_url` VARCHAR(5000) NULL,
  `bio` TEXT NULL,
  `create_date` DATETIME NULL,
  `date_option` TINYINT NULL,
  `profile_private` TINYINT NULL,
  `location_private` TINYINT NULL,
  `address_id` INT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_user_address1_idx` (`address_id` ASC),
  CONSTRAINT `fk_user_address1`
    FOREIGN KEY (`address_id`)
    REFERENCES `address` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `dog`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `dog` ;

CREATE TABLE IF NOT EXISTS `dog` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NOT NULL,
  `breed` VARCHAR(45) NOT NULL,
  `temperament` VARCHAR(45) NULL,
  `dog_profile_pic_url` VARCHAR(5000) NULL,
  `activity_level` VARCHAR(45) NULL,
  `size` VARCHAR(45) NULL,
  `bio` TEXT NULL,
  `birthday` DATE NULL,
  `rainbow_bridge` DATE NULL,
  `create_date` DATETIME NULL,
  `user_id` INT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_dog_user1_idx` (`user_id` ASC),
  CONSTRAINT `fk_dog_user1`
    FOREIGN KEY (`user_id`)
    REFERENCES `user` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `dog_park`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `dog_park` ;

CREATE TABLE IF NOT EXISTS `dog_park` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `address_id` INT NULL,
  `name` VARCHAR(45) NULL,
  `description` TEXT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_dog_park_address1_idx` (`address_id` ASC),
  CONSTRAINT `fk_dog_park_address1`
    FOREIGN KEY (`address_id`)
    REFERENCES `address` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `review`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `review` ;

CREATE TABLE IF NOT EXISTS `review` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `rating` INT NULL,
  `review` TEXT NULL,
  `img_url` VARCHAR(5000) NULL,
  `user_id` INT NOT NULL,
  `dog_park_id` INT NULL,
  `dog_id` INT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_review_user1_idx` (`user_id` ASC),
  INDEX `fk_review_dog_park1_idx` (`dog_park_id` ASC),
  INDEX `fk_review_dog1_idx` (`dog_id` ASC),
  CONSTRAINT `fk_review_user1`
    FOREIGN KEY (`user_id`)
    REFERENCES `user` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_review_dog_park1`
    FOREIGN KEY (`dog_park_id`)
    REFERENCES `dog_park` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_review_dog1`
    FOREIGN KEY (`dog_id`)
    REFERENCES `dog` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `user_fav_dog_park`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `user_fav_dog_park` ;

CREATE TABLE IF NOT EXISTS `user_fav_dog_park` (
  `user_id` INT NOT NULL,
  `dog_park_id` INT NOT NULL,
  PRIMARY KEY (`user_id`, `dog_park_id`),
  INDEX `fk_user_has_dog_park_dog_park1_idx` (`dog_park_id` ASC),
  INDEX `fk_user_has_dog_park_user1_idx` (`user_id` ASC),
  CONSTRAINT `fk_user_has_dog_park_user1`
    FOREIGN KEY (`user_id`)
    REFERENCES `user` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_user_has_dog_park_dog_park1`
    FOREIGN KEY (`dog_park_id`)
    REFERENCES `dog_park` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `meetup`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `meetup` ;

CREATE TABLE IF NOT EXISTS `meetup` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `title` VARCHAR(100) NULL,
  `description` TEXT NULL,
  `meetup_date` DATETIME NULL,
  `create_date` DATETIME NULL,
  `user_id_creator` INT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_meetup_user1_idx` (`user_id_creator` ASC),
  CONSTRAINT `fk_meetup_user1`
    FOREIGN KEY (`user_id_creator`)
    REFERENCES `user` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `comment`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `comment` ;

CREATE TABLE IF NOT EXISTS `comment` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `comment_date` DATETIME NULL,
  `comment_text` TEXT NULL,
  `title` VARCHAR(100) NULL,
  `comment_id` INT NULL,
  `meetup_id` INT NULL,
  `dog_park_id` INT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_comment_comment1_idx` (`comment_id` ASC),
  INDEX `fk_comment_meetup1_idx` (`meetup_id` ASC),
  INDEX `fk_comment_dog_park1_idx` (`dog_park_id` ASC),
  CONSTRAINT `fk_comment_comment1`
    FOREIGN KEY (`comment_id`)
    REFERENCES `comment` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_comment_meetup1`
    FOREIGN KEY (`meetup_id`)
    REFERENCES `meetup` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_comment_dog_park1`
    FOREIGN KEY (`dog_park_id`)
    REFERENCES `dog_park` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `user_friend_request`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `user_friend_request` ;

CREATE TABLE IF NOT EXISTS `user_friend_request` (
  `user_id0` INT NOT NULL,
  `user_id1` INT NOT NULL,
  PRIMARY KEY (`user_id0`, `user_id1`),
  INDEX `fk_user_has_user_user2_idx` (`user_id1` ASC),
  INDEX `fk_user_has_user_user1_idx` (`user_id0` ASC),
  CONSTRAINT `fk_user_has_user_user1`
    FOREIGN KEY (`user_id0`)
    REFERENCES `user` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_user_has_user_user2`
    FOREIGN KEY (`user_id1`)
    REFERENCES `user` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `route`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `route` ;

CREATE TABLE IF NOT EXISTS `route` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `start_time` DATETIME NULL,
  `end_time` DATETIME NULL,
  `user_id` INT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_route_user1_idx` (`user_id` ASC),
  CONSTRAINT `fk_route_user1`
    FOREIGN KEY (`user_id`)
    REFERENCES `user` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `location`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `location` ;

CREATE TABLE IF NOT EXISTS `location` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `lat` DOUBLE NULL,
  `lng` DOUBLE NULL,
  `point_time` DATETIME NULL,
  `route_id` INT NULL,
  `user_id` INT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_location_route1_idx` (`route_id` ASC),
  INDEX `fk_location_user1_idx` (`user_id` ASC),
  CONSTRAINT `fk_location_route1`
    FOREIGN KEY (`route_id`)
    REFERENCES `route` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_location_user1`
    FOREIGN KEY (`user_id`)
    REFERENCES `user` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `meetup_has_dog_park`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `meetup_has_dog_park` ;

CREATE TABLE IF NOT EXISTS `meetup_has_dog_park` (
  `meetup_id` INT NOT NULL,
  `dog_park_id` INT NOT NULL,
  PRIMARY KEY (`meetup_id`, `dog_park_id`),
  INDEX `fk_meetup_has_dog_park_dog_park1_idx` (`dog_park_id` ASC),
  INDEX `fk_meetup_has_dog_park_meetup1_idx` (`meetup_id` ASC),
  CONSTRAINT `fk_meetup_has_dog_park_meetup1`
    FOREIGN KEY (`meetup_id`)
    REFERENCES `meetup` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_meetup_has_dog_park_dog_park1`
    FOREIGN KEY (`dog_park_id`)
    REFERENCES `dog_park` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `dog_has_meetup`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `dog_has_meetup` ;

CREATE TABLE IF NOT EXISTS `dog_has_meetup` (
  `dog_id` INT NOT NULL,
  `meetup_id` INT NOT NULL,
  PRIMARY KEY (`dog_id`, `meetup_id`),
  INDEX `fk_dog_has_meetup_meetup1_idx` (`meetup_id` ASC),
  INDEX `fk_dog_has_meetup_dog1_idx` (`dog_id` ASC),
  CONSTRAINT `fk_dog_has_meetup_dog1`
    FOREIGN KEY (`dog_id`)
    REFERENCES `dog` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_dog_has_meetup_meetup1`
    FOREIGN KEY (`meetup_id`)
    REFERENCES `meetup` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

SET SQL_MODE = '';
DROP USER IF EXISTS doggieuser@localhost;
SET SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';
CREATE USER 'doggieuser'@'localhost' IDENTIFIED BY 'doggieuser';

GRANT SELECT, INSERT, TRIGGER, UPDATE, DELETE ON TABLE * TO 'doggieuser'@'localhost';

SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;

-- -----------------------------------------------------
-- Data for table `user`
-- -----------------------------------------------------
START TRANSACTION;
USE `dogdb`;
INSERT INTO `user` (`id`, `first_name`, `last_name`, `username`, `password`, `email`, `role`, `enabled`, `profile_photo_url`, `bio`, `create_date`, `date_option`, `profile_private`, `location_private`, `address_id`) VALUES (1, '', '', 'admin', 'admin', 'admin@mail.com', 'admin', 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL);

COMMIT;

