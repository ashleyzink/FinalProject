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
  `street` VARCHAR(150) NULL,
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
  `gender` VARCHAR(10) NULL,
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
  `name` VARCHAR(150) NULL,
  `description` TEXT NULL,
  `off_leash` TINYINT NULL,
  `image_url` VARCHAR(5000) NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_dog_park_address1_idx` (`address_id` ASC),
  CONSTRAINT `fk_dog_park_address1`
    FOREIGN KEY (`address_id`)
    REFERENCES `address` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `dog_review`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `dog_review` ;

CREATE TABLE IF NOT EXISTS `dog_review` (
  `rating` INT NULL,
  `review` TEXT NULL,
  `img_url` VARCHAR(5000) NULL,
  `user_id` INT NOT NULL,
  `dog_id` INT NOT NULL,
  `review_date` DATETIME NULL,
  PRIMARY KEY (`dog_id`, `user_id`),
  INDEX `fk_review_user1_idx` (`user_id` ASC),
  INDEX `fk_review_dog1_idx` (`dog_id` ASC),
  CONSTRAINT `fk_review_user1`
    FOREIGN KEY (`user_id`)
    REFERENCES `user` (`id`)
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
  `user_id_creator` INT NOT NULL,
  `dog_park_id` INT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_meetup_user1_idx` (`user_id_creator` ASC),
  INDEX `fk_meetup_dog_park1_idx` (`dog_park_id` ASC),
  CONSTRAINT `fk_meetup_user1`
    FOREIGN KEY (`user_id_creator`)
    REFERENCES `user` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_meetup_dog_park1`
    FOREIGN KEY (`dog_park_id`)
    REFERENCES `dog_park` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `meetup_comment`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `meetup_comment` ;

CREATE TABLE IF NOT EXISTS `meetup_comment` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `comment_date` DATETIME NULL,
  `comment_text` TEXT NULL,
  `title` VARCHAR(100) NULL,
  `reply_to_comment_id` INT NULL,
  `meetup_id` INT NOT NULL,
  `user_id` INT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_comment_comment1_idx` (`reply_to_comment_id` ASC),
  INDEX `fk_comment_meetup1_idx` (`meetup_id` ASC),
  INDEX `fk_meetup_comment_user1_idx` (`user_id` ASC),
  CONSTRAINT `fk_comment_comment1`
    FOREIGN KEY (`reply_to_comment_id`)
    REFERENCES `meetup_comment` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_comment_meetup1`
    FOREIGN KEY (`meetup_id`)
    REFERENCES `meetup` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_meetup_comment_user1`
    FOREIGN KEY (`user_id`)
    REFERENCES `user` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `user_friend_request`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `user_friend_request` ;

CREATE TABLE IF NOT EXISTS `user_friend_request` (
  `user_id` INT NOT NULL,
  `friend_id` INT NOT NULL,
  PRIMARY KEY (`user_id`, `friend_id`),
  INDEX `fk_user_has_user_user2_idx` (`friend_id` ASC),
  INDEX `fk_user_has_user_user1_idx` (`user_id` ASC),
  CONSTRAINT `fk_user_has_user_user1`
    FOREIGN KEY (`user_id`)
    REFERENCES `user` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_user_has_user_user2`
    FOREIGN KEY (`friend_id`)
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


-- -----------------------------------------------------
-- Table `dog_park_comment`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `dog_park_comment` ;

CREATE TABLE IF NOT EXISTS `dog_park_comment` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `comment_date` DATETIME NULL,
  `comment_text` TEXT NULL,
  `title` VARCHAR(100) NULL,
  `reply_to_comment_id` INT NULL,
  `dog_park_id` INT NOT NULL,
  `user_id` INT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_comment_comment1_idx` (`reply_to_comment_id` ASC),
  INDEX `fk_dog_park_comment_dog_park1_idx` (`dog_park_id` ASC),
  INDEX `fk_dog_park_comment_user1_idx` (`user_id` ASC),
  CONSTRAINT `fk_comment_comment10`
    FOREIGN KEY (`reply_to_comment_id`)
    REFERENCES `dog_park_comment` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_dog_park_comment_dog_park1`
    FOREIGN KEY (`dog_park_id`)
    REFERENCES `dog_park` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_dog_park_comment_user1`
    FOREIGN KEY (`user_id`)
    REFERENCES `user` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `dog_park_review`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `dog_park_review` ;

CREATE TABLE IF NOT EXISTS `dog_park_review` (
  `rating` INT NULL,
  `review` TEXT NULL,
  `img_url` VARCHAR(5000) NULL,
  `user_id` INT NOT NULL,
  `review_date` DATETIME NULL,
  `dog_park_id` INT NOT NULL,
  PRIMARY KEY (`user_id`, `dog_park_id`),
  INDEX `fk_review_user1_idx` (`user_id` ASC),
  INDEX `fk_dog_park_review_dog_park1_idx` (`dog_park_id` ASC),
  CONSTRAINT `fk_review_user10`
    FOREIGN KEY (`user_id`)
    REFERENCES `user` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_dog_park_review_dog_park1`
    FOREIGN KEY (`dog_park_id`)
    REFERENCES `dog_park` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `general_comment`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `general_comment` ;

CREATE TABLE IF NOT EXISTS `general_comment` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `comment_date` DATETIME NULL,
  `comment_text` TEXT NULL,
  `title` VARCHAR(100) NULL,
  `reply_to_comment_id` INT NULL,
  `user_id` INT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_comment_comment1_idx` (`reply_to_comment_id` ASC),
  INDEX `fk_meetup_comment_user1_idx` (`user_id` ASC),
  CONSTRAINT `fk_comment_comment11`
    FOREIGN KEY (`reply_to_comment_id`)
    REFERENCES `general_comment` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_meetup_comment_user10`
    FOREIGN KEY (`user_id`)
    REFERENCES `user` (`id`)
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
-- Data for table `address`
-- -----------------------------------------------------
START TRANSACTION;
USE `dogdb`;
INSERT INTO `address` (`id`, `street`, `city`, `state_abbrv`, `zipcode`) VALUES (1, '1234 dog st.', 'dogwood', 'CA', '12345');
INSERT INTO `address` (`id`, `street`, `city`, `state_abbrv`, `zipcode`) VALUES (2, '123 happy st', 'dogwood', 'CA', '12345');

COMMIT;


-- -----------------------------------------------------
-- Data for table `user`
-- -----------------------------------------------------
START TRANSACTION;
USE `dogdb`;
INSERT INTO `user` (`id`, `first_name`, `last_name`, `username`, `password`, `email`, `role`, `enabled`, `profile_photo_url`, `bio`, `create_date`, `date_option`, `profile_private`, `location_private`, `address_id`) VALUES (1, '', '', 'admin', 'admin', 'admin@mail.com', 'admin', 1, NULL, NULL, '2020-10-10 ', NULL, NULL, NULL, 2);

COMMIT;


-- -----------------------------------------------------
-- Data for table `dog`
-- -----------------------------------------------------
START TRANSACTION;
USE `dogdb`;
INSERT INTO `dog` (`id`, `name`, `breed`, `temperament`, `dog_profile_pic_url`, `activity_level`, `size`, `bio`, `birthday`, `rainbow_bridge`, `create_date`, `user_id`, `gender`) VALUES (1, 'Kona', 'Australian Kelpie', NULL, 'https://s3.amazonaws.com/cdn-origin-etr.akc.org/wp-content/uploads/2017/11/14153642/Kelpie.4.jpg', NULL, NULL, NULL, NULL, NULL, '2020-10-10 ', 1, NULL);

COMMIT;


-- -----------------------------------------------------
-- Data for table `dog_park`
-- -----------------------------------------------------
START TRANSACTION;
USE `dogdb`;
INSERT INTO `dog_park` (`id`, `address_id`, `name`, `description`, `off_leash`, `image_url`) VALUES (1, 1, 'Redwood', 'Nam semper maximus elit id porta. Phasellus eu velit purus.', NULL, NULL);

COMMIT;


-- -----------------------------------------------------
-- Data for table `dog_review`
-- -----------------------------------------------------
START TRANSACTION;
USE `dogdb`;
INSERT INTO `dog_review` (`rating`, `review`, `img_url`, `user_id`, `dog_id`, `review_date`) VALUES (5, 'Nam semper maximus elit id porta. Phasellus eu velit purus.', 'https://images2.minutemediacdn.com/image/upload/c_fill,g_auto,h_1248,w_2220/v1555439209/shape/mentalfloss/345eyrhfj.png?itok=WBbnAek5', 1, 1, '2020-10-10 12:30:16');

COMMIT;


-- -----------------------------------------------------
-- Data for table `user_fav_dog_park`
-- -----------------------------------------------------
START TRANSACTION;
USE `dogdb`;
INSERT INTO `user_fav_dog_park` (`user_id`, `dog_park_id`) VALUES (1, 1);

COMMIT;


-- -----------------------------------------------------
-- Data for table `meetup`
-- -----------------------------------------------------
START TRANSACTION;
USE `dogdb`;
INSERT INTO `meetup` (`id`, `title`, `description`, `meetup_date`, `create_date`, `user_id_creator`, `dog_park_id`) VALUES (1, 'dog and chill', 'It is a long established fact that a reader will be distracted by the readable content of a page when looking at its layout. The point of using Lorem Ipsum is that it has a more-or-less normal distribution of letters, as opposed to using \'Content here, content here\', making it look like readable English. Many desktop publishing packages and web page editors now use Lorem Ipsum as their default model text, and a search for \'lorem ipsum\' will uncover many web sites still in their infancy. Various versions have evolved over the years, sometimes by accident, sometimes on purpose (injected humour and the like).\nIt is a long established fact that a reader will be distracted by the readable content of a page when looking at its layout. The point of using Lorem Ipsum is that it has a more-or-less normal distribution of letters, as opposed to using \'Content here, content here\', making it look like readable English. Many desktop publishing packages and web page editors now use Lorem Ipsum as their default model text, and a search for \'lorem ipsum\' will uncover many web sites still in their infancy. Various versions have evolved over the years, sometimes by accident, sometimes on purpose (injected humour and the like).\n\n\n', '2020-10-10 12:30:16', '2020-09-10 12:30:17', 1, 1);

COMMIT;


-- -----------------------------------------------------
-- Data for table `meetup_comment`
-- -----------------------------------------------------
START TRANSACTION;
USE `dogdb`;
INSERT INTO `meetup_comment` (`id`, `comment_date`, `comment_text`, `title`, `reply_to_comment_id`, `meetup_id`, `user_id`) VALUES (1, '2020-10-10 12:30:16', 'Lorem ipsum dolor', 'My Dog has fleas, Help?', NULL, 1, 1);
INSERT INTO `meetup_comment` (`id`, `comment_date`, `comment_text`, `title`, `reply_to_comment_id`, `meetup_id`, `user_id`) VALUES (2, '2020-10-11 12:30:16', 'Lorem ipsum dolor', 'Lets go for a run!', 1, 1, 1);
INSERT INTO `meetup_comment` (`id`, `comment_date`, `comment_text`, `title`, `reply_to_comment_id`, `meetup_id`, `user_id`) VALUES (3, '2020-10-09 12:30:16', 'Lorem ipsum dolor', 'Trails near by?', 2, 1, 1);

COMMIT;


-- -----------------------------------------------------
-- Data for table `user_friend_request`
-- -----------------------------------------------------
START TRANSACTION;
USE `dogdb`;
INSERT INTO `user_friend_request` (`user_id`, `friend_id`) VALUES (1, 1);

COMMIT;


-- -----------------------------------------------------
-- Data for table `route`
-- -----------------------------------------------------
START TRANSACTION;
USE `dogdb`;
INSERT INTO `route` (`id`, `start_time`, `end_time`, `user_id`) VALUES (1, '2020-10-10 12:30:16', '2020-10-11 12:30:40', 1);

COMMIT;


-- -----------------------------------------------------
-- Data for table `location`
-- -----------------------------------------------------
START TRANSACTION;
USE `dogdb`;
INSERT INTO `location` (`id`, `lat`, `lng`, `point_time`, `route_id`, `user_id`) VALUES (1, 33.47532343320456, -117.10128305844954, '2020-10-10 12:30:16', 1, 1);

COMMIT;


-- -----------------------------------------------------
-- Data for table `dog_has_meetup`
-- -----------------------------------------------------
START TRANSACTION;
USE `dogdb`;
INSERT INTO `dog_has_meetup` (`dog_id`, `meetup_id`) VALUES (1, 1);

COMMIT;


-- -----------------------------------------------------
-- Data for table `dog_park_comment`
-- -----------------------------------------------------
START TRANSACTION;
USE `dogdb`;
INSERT INTO `dog_park_comment` (`id`, `comment_date`, `comment_text`, `title`, `reply_to_comment_id`, `dog_park_id`, `user_id`) VALUES (1, '2020-10-10 ', 'Nam semper maximus elit id porta. Phasellus eu velit purus.', 'Big Field', NULL, 1, 1);
INSERT INTO `dog_park_comment` (`id`, `comment_date`, `comment_text`, `title`, `reply_to_comment_id`, `dog_park_id`, `user_id`) VALUES (2, '2020-10-09 12:30:16', 'this is a great coversation', 'Doggie Stations', 1, 1, 1);
INSERT INTO `dog_park_comment` (`id`, `comment_date`, `comment_text`, `title`, `reply_to_comment_id`, `dog_park_id`, `user_id`) VALUES (3, '2020-10-08 12:30:16', 'i love talking soo much!', 'Date night?', 2, 1, 1);

COMMIT;


-- -----------------------------------------------------
-- Data for table `dog_park_review`
-- -----------------------------------------------------
START TRANSACTION;
USE `dogdb`;
INSERT INTO `dog_park_review` (`rating`, `review`, `img_url`, `user_id`, `review_date`, `dog_park_id`) VALUES (5, 'Nam semper maximus elit id porta. Phasellus eu velit purus.', 'https://lh5.googleusercontent.com/p/AF1QipNTi44jKHG3HEtKPMv23pV7LupFneqUtx1MTksO=w408-h306-k-no', 1, '2020-10-10 12:30:16', 1);

COMMIT;


-- -----------------------------------------------------
-- Data for table `general_comment`
-- -----------------------------------------------------
START TRANSACTION;
USE `dogdb`;
INSERT INTO `general_comment` (`id`, `comment_date`, `comment_text`, `title`, `reply_to_comment_id`, `user_id`) VALUES (1, '2020-10-10 12:30:16', 'Nam semper maximus elit id porta. Phasellus eu velit purus.', 'What shampoo to use?', NULL, 1);
INSERT INTO `general_comment` (`id`, `comment_date`, `comment_text`, `title`, `reply_to_comment_id`, `user_id`) VALUES (2, '2020-10-10 12:50:16', 'Nam semper maximus elit id porta. Phasellus eu velit purus.', 'Dog Food', 1, 1);

COMMIT;

