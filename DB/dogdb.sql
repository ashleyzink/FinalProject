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
  `name` VARCHAR(150) NOT NULL DEFAULT 'dog',
  `breed` VARCHAR(150) NOT NULL DEFAULT 'breed',
  `temperament` VARCHAR(150) NULL,
  `dog_profile_pic_url` VARCHAR(10000) NULL,
  `activity_level` VARCHAR(45) NULL,
  `size` VARCHAR(45) NULL,
  `bio` TEXT NULL,
  `birthday` DATE NULL,
  `rainbow_bridge` DATE NULL,
  `create_date` DATETIME NULL,
  `user_id` INT NOT NULL DEFAULT 1,
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
  `img_url` VARCHAR(15000) NULL,
  `user_id` INT NOT NULL DEFAULT 3,
  `review_date` DATETIME NULL,
  `dog_park_id` INT NOT NULL DEFAULT 2,
  `last_updated` DATETIME NULL,
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
  `user_id` INT NOT NULL DEFAULT 1,
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
INSERT INTO `address` (`id`, `street`, `city`, `state_abbrv`, `zipcode`) VALUES (3, '5500 laramie st', 'chicago', 'IL', '43322');
INSERT INTO `address` (`id`, `street`, `city`, `state_abbrv`, `zipcode`) VALUES (4, '145 boulder rd.', 'denver', 'CO', '24956');
INSERT INTO `address` (`id`, `street`, `city`, `state_abbrv`, `zipcode`) VALUES (5, '1332 alpine blvd.', 'san diego', 'CA', '49245');
INSERT INTO `address` (`id`, `street`, `city`, `state_abbrv`, `zipcode`) VALUES (6, '143 Parks Rd', 'St Johnsbury', 'VT', '05819');
INSERT INTO `address` (`id`, `street`, `city`, `state_abbrv`, `zipcode`) VALUES (7, '7407 Salisbury Rd', 'Jacksonville', 'FL', '32256');
INSERT INTO `address` (`id`, `street`, `city`, `state_abbrv`, `zipcode`) VALUES (8, '3895 Cherry Ln Ave SE', 'Ada', 'MI', '49301');
INSERT INTO `address` (`id`, `street`, `city`, `state_abbrv`, `zipcode`) VALUES (9, '1720 E Second St', 'Whitefish', 'MT', '59937');
INSERT INTO `address` (`id`, `street`, `city`, `state_abbrv`, `zipcode`) VALUES (10, '7400 Sand Point Way NE', 'Seattle', 'WA', '98115');

COMMIT;


-- -----------------------------------------------------
-- Data for table `user`
-- -----------------------------------------------------
START TRANSACTION;
USE `dogdb`;
INSERT INTO `user` (`id`, `first_name`, `last_name`, `username`, `password`, `email`, `role`, `enabled`, `profile_photo_url`, `bio`, `create_date`, `date_option`, `profile_private`, `location_private`, `address_id`) VALUES (1, 'John ', 'hansen', 'admin', '$2a$10$DBRIM1/sqs2BD39xmlxUoOsUj/pnd/yIxasI.eg7J3moSpf1M4Mlm', 'admin@mail.com', 'admin', 1, 'https://pbs.twimg.com/profile_images/1237550450/mstom_400x400.jpg', 'Freelance food expert. Friend of animals everywhere. Hipster-friendly travel guru. Twitteraholic.', '2020-10-10 ', 1, NULL, NULL, 2);
INSERT INTO `user` (`id`, `first_name`, `last_name`, `username`, `password`, `email`, `role`, `enabled`, `profile_photo_url`, `bio`, `create_date`, `date_option`, `profile_private`, `location_private`, `address_id`) VALUES (2, 'Rebecca', 'Nievera', 'tonysdog', 'dog', 'tony@mail.com', NULL, 1, 'https://www.fakepersongenerator.com/Face/male/male1084815624237.jpg', 'Passionate internet lover. Certified twitter ninja. Lifelong entrepreneur. Gamer. Music junkie.', '2020-10-12', 1, NULL, NULL, 1);
INSERT INTO `user` (`id`, `first_name`, `last_name`, `username`, `password`, `email`, `role`, `enabled`, `profile_photo_url`, `bio`, `create_date`, `date_option`, `profile_private`, `location_private`, `address_id`) VALUES (3, 'Han', 'SoloMio', 'ashleysdogpark', 'dog', 'dog@mail.com', NULL, 1, 'https://www.fakepersongenerator.com/Face/female/female20171026352341797.jpg', 'Subtly charming internet aficionado. Zombie lover. Food practitioner. Student. Alcohol enthusiast. Thinker.', '2020-10-12', 2, NULL, NULL, 3);
INSERT INTO `user` (`id`, `first_name`, `last_name`, `username`, `password`, `email`, `role`, `enabled`, `profile_photo_url`, `bio`, `create_date`, `date_option`, `profile_private`, `location_private`, `address_id`) VALUES (4, 'Ariel', 'Mermale', 'danielsfundog', 'dog', 'fundog@mail.com', NULL, 1, 'https://www.fakepersongenerator.com/Face/male/male20141083547721802.jpg', 'Proud social media evangelist. Reader. Friendly twitter trailblazer. Passionate pop culture fan.', '2020-10-12', 2, NULL, NULL, 4);
INSERT INTO `user` (`id`, `first_name`, `last_name`, `username`, `password`, `email`, `role`, `enabled`, `profile_photo_url`, `bio`, `create_date`, `date_option`, `profile_private`, `location_private`, `address_id`) VALUES (5, 'Nicolai', 'Kysteryec', 'jourdansdoglover', 'dog', 'doglover@mail.com', NULL, 1, 'https://www.fakepersongenerator.com/Face/female/female1022294903778.jpg', 'Travel specialist. Evil gamer. Zombie evangelist. Wannabe organizer. Amateur social mediaholic.', '2020-10-12', 1, NULL, NULL, 5);
INSERT INTO `user` (`id`, `first_name`, `last_name`, `username`, `password`, `email`, `role`, `enabled`, `profile_photo_url`, `bio`, `create_date`, `date_option`, `profile_private`, `location_private`, `address_id`) VALUES (6, 'Shannon', 'Julian', 'sjdoggo', 'dog', 'doglover@mail.com', NULL, 1, 'https://www.fakepersongenerator.com/Face/male/male20161086420389465.jpg', 'Creator. Tv evangelist. Hardcore alcohol enthusiast. Avid web advocate. Entrepreneur. Award-winning twitter fanatic.', '2020-10-12', 2, NULL, NULL, 6);
INSERT INTO `user` (`id`, `first_name`, `last_name`, `username`, `password`, `email`, `role`, `enabled`, `profile_photo_url`, `bio`, `create_date`, `date_option`, `profile_private`, `location_private`, `address_id`) VALUES (7, 'Bartlow', 'Sommer', 'sblow', 'dog', 'howell_bechtel@yahoo.com', NULL, 1, 'https://www.fakepersongenerator.com/Face/female/female20161025302709625.jpg', 'Problem solver. Professional twitter trailblazer. Unable to type with boxing gloves on.', '2020-10-12', 1, NULL, NULL, NULL);
INSERT INTO `user` (`id`, `first_name`, `last_name`, `username`, `password`, `email`, `role`, `enabled`, `profile_photo_url`, `bio`, `create_date`, `date_option`, `profile_private`, `location_private`, `address_id`) VALUES (8, 'Robbie', 'Robert', 'Johns', 'dog', 'shaylee_pacoc@gmail.com', NULL, 1, 'https://www.fakepersongenerator.com/Face/male/male1084443761566.jpg', 'Award-winning thinker. Lifelong reader. Friendly internet aficionado. Music fan. Social media guru.\n\n', '2020-10-12', 2, NULL, NULL, NULL);
INSERT INTO `user` (`id`, `first_name`, `last_name`, `username`, `password`, `email`, `role`, `enabled`, `profile_photo_url`, `bio`, `create_date`, `date_option`, `profile_private`, `location_private`, `address_id`) VALUES (9, 'Stephen', 'Owens', 'steveo', 'dog', 'francesco1972@hotmail.com', NULL, 1, 'https://www.fakepersongenerator.com/Face/male/male20161086669801361.jpg', 'Infuriatingly humble zombie trailblazer. Communicator. Typical music expert. Incurable coffee nerd. Subtly charming gamer. Hipster-friendly twitter geek.\n\n', ' 2020-10-12', 1, NULL, NULL, NULL);
INSERT INTO `user` (`id`, `first_name`, `last_name`, `username`, `password`, `email`, `role`, `enabled`, `profile_photo_url`, `bio`, `create_date`, `date_option`, `profile_private`, `location_private`, `address_id`) VALUES (10, 'Deedra', 'Dilday', 'dd', 'dog', 'eve_corker7@gmail.com', NULL, 1, 'https://www.fakepersongenerator.com/Face/female/female1021890741302.jpg', 'Communicator. Beer specialist. Alcohol maven. Professional bacon evangelist. Social media expert. Music aficionado.', '2020-10-12', 2, NULL, NULL, NULL);

COMMIT;


-- -----------------------------------------------------
-- Data for table `dog`
-- -----------------------------------------------------
START TRANSACTION;
USE `dogdb`;
INSERT INTO `dog` (`id`, `name`, `breed`, `temperament`, `dog_profile_pic_url`, `activity_level`, `size`, `bio`, `birthday`, `rainbow_bridge`, `create_date`, `user_id`, `gender`) VALUES (1, 'Kona', 'Australian Kelpie', 'happy and go lucky', 'https://s3.amazonaws.com/cdn-origin-etr.akc.org/wp-content/uploads/2017/11/14153642/Kelpie.4.jpg', 'very energetic', 'medium', 'Kona aka Kona Bear is your typical \"everyone who meets him falls in love with him immediately\" lab. He has a LOT of friends - basically everyone he meets is his best friend and he loudly insists that they pet him and snuggle his face. He is a gentle guy, and just wants belly rubs and behind the ear scratches all day. Kona LOVES toys. He has a special toy that he totes around the house and knows its name. Every morning, he makes a big show of looking for and finding it. But if it\'s not available, he will take any squeaky toy or bone. He\'s a lab that loves carrying things around in his mouth.', '2020-06-26', NULL, '2020-10-10 ', 1, 'male');
INSERT INTO `dog` (`id`, `name`, `breed`, `temperament`, `dog_profile_pic_url`, `activity_level`, `size`, `bio`, `birthday`, `rainbow_bridge`, `create_date`, `user_id`, `gender`) VALUES (2, 'Afa', 'Afador', 'As an athletic mixed breed.', 'https://www.dogtime.com/assets/uploads/2019/08/afador-mixed-dog-breed-pictures-1-1442x1328.jpg', 'medium to high energy levels', 'large', NULL, '2012-03-26', NULL, '2020-10-10 ', 2, 'female');
INSERT INTO `dog` (`id`, `name`, `breed`, `temperament`, `dog_profile_pic_url`, `activity_level`, `size`, `bio`, `birthday`, `rainbow_bridge`, `create_date`, `user_id`, `gender`) VALUES (3, 'Bassie', 'Basset Hound\n', 'The Basset is a scent hound that was originally bred for the purpose of hunting hare.', 'https://www.dogtime.com/assets/uploads/2011/01/file_23010_basset-hound-460x290.jpg', 'Not aggressive, and quite docile.', 'small', NULL, '2019-02-13', NULL, '2020-10-10 ', 3, 'male');
INSERT INTO `dog` (`id`, `name`, `breed`, `temperament`, `dog_profile_pic_url`, `activity_level`, `size`, `bio`, `birthday`, `rainbow_bridge`, `create_date`, `user_id`, `gender`) VALUES (4, 'Boxo', 'Boston Boxer\n', 'upbeat dog with a zest for life', 'https://www.dogtime.com/assets/uploads/2020/01/boston-boxer-mixed-dog-breed-pictures-1.jpg', 'active in short bursts', 'medium', NULL, '2017-12-13', NULL, '2020-10-10 ', 4, 'female');
INSERT INTO `dog` (`id`, `name`, `breed`, `temperament`, `dog_profile_pic_url`, `activity_level`, `size`, `bio`, `birthday`, `rainbow_bridge`, `create_date`, `user_id`, `gender`) VALUES (5, 'Massie', 'Mastiff', 'calm, collected dog, who enjoys relaxing', 'https://d17fnq9dkz9hgj.cloudfront.net/breed-uploads/2018/08/mastiff-card-medium.jpg?bust=1535568273&width=560', 'low energy levels', 'x-large', NULL, '2010-04-09', NULL, '2020-10-10 ', 5, 'male');
INSERT INTO `dog` (`id`, `name`, `breed`, `temperament`, `dog_profile_pic_url`, `activity_level`, `size`, `bio`, `birthday`, `rainbow_bridge`, `create_date`, `user_id`, `gender`) VALUES (6, 'Kyle', 'Siberian Husky', 'chill', 'https://static.boredpanda.com/blog/wp-content/uploads/2017/09/funny-dog-bios-92-59ad3f9cd01a7__700.jpg', 'mellow, unless were talking politics', 'large', 'Documentary film enthusiast, share global warming articles, medical marijuana patient, and drives in a prius', '2015-04-09', NULL, '2020-10-10 ', 1, 'male');
INSERT INTO `dog` (`id`, `name`, `breed`, `temperament`, `dog_profile_pic_url`, `activity_level`, `size`, `bio`, `birthday`, `rainbow_bridge`, `create_date`, `user_id`, `gender`) VALUES (7, 'Sharon', 'Chihuahua', 'loud', 'https://static.boredpanda.com/blog/wp-content/uploads/2017/09/funny-dog-bios-78-59ad1bd7da892__700.jpg', 'sassy', 'small, but acts big', 'Doesn\'t like to work. Has a xanax prescription, LOVES salad, hates other dogs.', NULL, NULL, '2020-10-10 ', 2, 'female');
INSERT INTO `dog` (`id`, `name`, `breed`, `temperament`, `dog_profile_pic_url`, `activity_level`, `size`, `bio`, `birthday`, `rainbow_bridge`, `create_date`, `user_id`, `gender`) VALUES (8, 'Gerrard', 'Pit Bull', 'eccentric', 'https://static.boredpanda.com/blog/wp-content/uploads/2017/09/funny-dog-bios-95-59ad41c70f0ee__700.jpg', 'medium energy level', 'medium', 'Professor of English literature. Invites himself to student parties', '2005-04-09', NULL, '2020-10-10 ', 3, 'male');
INSERT INTO `dog` (`id`, `name`, `breed`, `temperament`, `dog_profile_pic_url`, `activity_level`, `size`, `bio`, `birthday`, `rainbow_bridge`, `create_date`, `user_id`, `gender`) VALUES (9, 'Betty', 'Golden Retriever', 'annoyed', 'https://static.boredpanda.com/blog/wp-content/uploads/2017/09/funny-dog-bios-79-59ad1e7307892__700.jpg', 'mild', 'medium', 'Looks older than she is. Also, convinced that male dogs are walked more often.', '2015-06-09', NULL, '2020-10-10 ', 4, 'female');
INSERT INTO `dog` (`id`, `name`, `breed`, `temperament`, `dog_profile_pic_url`, `activity_level`, `size`, `bio`, `birthday`, `rainbow_bridge`, `create_date`, `user_id`, `gender`) VALUES (10, 'Curtis', 'Terrier', 'active', 'https://static.boredpanda.com/blog/wp-content/uploads/2017/09/funny-dog-bios-90-59ad3ceb40dc3__700.jpg', 'lots of energy', 'medium-large', 'Asipiring audio engineer. Always wears converse. Only listens to \"real\" hip-hop.', '2009-04-09', NULL, '2020-10-10 ', 5, 'male');

COMMIT;


-- -----------------------------------------------------
-- Data for table `dog_park`
-- -----------------------------------------------------
START TRANSACTION;
USE `dogdb`;
INSERT INTO `dog_park` (`id`, `address_id`, `name`, `description`, `off_leash`, `image_url`) VALUES (1, 1, 'Redwood', 'There are separate fenced in areas, one for big dogs that weigh over 26 pounds, and one for small dogs that weigh less that 26 pounds. There is another smaller area which is closed off for maintenance. There are benches scattered around the area for dog owners to sit. There are water fountains with dog level water bowls (people level too, if you care to use them). There are stations of doggy poop bags in each area.', 1, 'https://s3-media0.fl.yelpcdn.com/bphoto/3ySbLEGIIHOXMnzk9Sc_iQ/180s.jpg');
INSERT INTO `dog_park` (`id`, `address_id`, `name`, `description`, `off_leash`, `image_url`) VALUES (2, 2, 'Newtown Dream Dog Park', 'Through the Beneful Dream Dog Park Project, the company built and improved multiple dog parks around the country. Another beneficiary was the dog park in Buchanan Park in Lancaster, Pennsylvania, which was completely revamped in 2013. Fun, high-tech features include splash pads and a tennis ball tree that launches balls for large dogs. It also has turf grass for cleanliness and plenty of structures for dogs to play on.\n\n', 1, 'https://thumbor.thedailymeal.com/9z7PZVszVMYOwjrFw45CtvIl8fQ=/574x366/filters:format(webp)/https://www.theactivetimes.com/sites/default/files/slideshows/104569/106052/bestdogparks1newtown.jpg');
INSERT INTO `dog_park` (`id`, `address_id`, `name`, `description`, `off_leash`, `image_url`) VALUES (3, 3, 'Montrose Dog Beach', 'Chicagoans and their dogs can escape to this dedicated, fenced in dog beach along Lake Michigan. With stunning views of the skyline and generally courteous owners and furry playmates, the park also has amenities like a dog wash station.\n\n', 1, 'https://thumbor.thedailymeal.com/MXsWEJDC3d_6JiCeY6A2jT6m6b8=/574x366/filters:format(webp)/https://www.theactivetimes.com/sites/default/files/slideshows/104569/106052/bestdogparks3.jpg');
INSERT INTO `dog_park` (`id`, `address_id`, `name`, `description`, `off_leash`, `image_url`) VALUES (4, 4, 'Bea Arthur Dog Park', 'Named for \"Golden Girls\" actress Bea Arthur, this free, 24/7 dog park is most notable for its access ramp to the Elizabeth River, where pups can doggie paddle to their heart\'s content. The park also provides room to run, several pools and a collection of toys. The park is located behind PETA\'s headquarters, and the organization provides a live camera feed of the park on its website so you can check if the park is crowded or not before leaving home.\n\n', 1, 'https://thumbor.thedailymeal.com/WGQnq5pTchJlHQvTmsPTGtJkvVc=/574x366/filters:format(webp)/https://www.theactivetimes.com/sites/default/files/slideshows/104569/106052/bestdogparks4.jpg');
INSERT INTO `dog_park` (`id`, `address_id`, `name`, `description`, `off_leash`, `image_url`) VALUES (5, 5, 'Jackass Acres K-9 Korral', 'The members-only Jackass Acres is the nation\'s first green dog park. Its sustainable practices include structures built from recycled materials and fallen trees, solar-powered lights and fountains, biodegradable poop bags and NFL-grade artificial turf. Its attention to dogs, people and the environment have earned it the title of Best Dog Park in America from Dog Fancy magazine twice since its opening.\n\n', 1, 'https://thumbor.thedailymeal.com/HQeGJrlZQia88Qdbn9G88Fz5QIg=/574x366/filters:format(webp)/https://www.theactivetimes.com/sites/default/files/slideshows/104569/106052/bestdogparks5.jpg');
INSERT INTO `dog_park` (`id`, `address_id`, `name`, `description`, `off_leash`, `image_url`) VALUES (6, 6, 'Dog Mountain', 'Four-legged visitors love its hiking trails, agility course and ponds that are perfect for a quick dunk when the sun’s out. But what really sets this place apart is the one-of-a-kind Dog Chapel that commemorates beloved pets who are no longer with us. Park owner and folk artist Stephen Huneck designed the chapel in the style of an 1820s Vermont church and set it atop a hill overlooking the valley below.', 1, 'https://photos.bringfido.com/photo/2019/06/14/Dog_Mountain.jpg?size=entry&density=2x');
INSERT INTO `dog_park` (`id`, `address_id`, `name`, `description`, `off_leash`, `image_url`) VALUES (7, 7, 'Dog Wood Park\nDog Wood Park\n', 'The privately owned, off-leash Dog Wood Park spans an immaculately groomed 25 acres of land. Your pup can wander through pristine fields of trimmed grass and crawl through tunnels and tubes. The centerpiece of the park is a two-acre swimming lake that boasts its very own beach. It also has an agility section and a small dog area.\n\n', 1, 'https://photos.bringfido.com/photo/2019/06/14/Dogwood_Park.jpg?size=entry&density=2x');
INSERT INTO `dog_park` (`id`, `address_id`, `name`, `description`, `off_leash`, `image_url`) VALUES (8, 8, 'Shaggy Pines Dog Park\n', ' This 15-acre, fenced-in paradise is like a spa resort for dogs. Rolling hills, open green spaces and towering pines give it a secluded feeling despite being close to downtown Cascade Township. The park boasts a pristine doggy swimming pond, separate areas for small and large dogs, and an events space that also hosts agility and training classes. And then there\'s Doggy Mountain, a large sand pile specifically designed for dogs who can’t get enough of climbing and digging.', 1, 'https://photos.bringfido.com/photo/2019/06/17/Shaggy_Pines_2.jpg?size=entry&density=2x');
INSERT INTO `dog_park` (`id`, `address_id`, `name`, `description`, `off_leash`, `image_url`) VALUES (9, 9, 'Hugh Rogers WAG Park\n', ' Location isn’t everything but it’s certainly helped this scenic dog park make our list. While your pooch plays and makes new friends, you can enjoy panoramic views of the soaring peaks (dotted with skiers in the snowy months) and watch for the occasional train trundling by on the nearby track. Named after the Whitefish Animal Group, the WAG park contains separate areas for small and large dogs, an agility course and a large pond with an adjacent dog beach. ', 1, 'https://photos.bringfido.com/photo/2019/06/17/Hugh_Rogers_Wag_Park.jpg?size=entry&density=2x');
INSERT INTO `dog_park` (`id`, `address_id`, `name`, `description`, `off_leash`, `image_url`) VALUES (10, 10, 'Warren G. Magnuson Park Off-Leash Dog Area\n', 'At a whopping 8.6 acres, this fully fenced-in area is the only park inside city limits that has water access, and it\'s no mere splash pool. You and your dog get to enjoy Lake Washington, the second largest lake in the state. Stroll the paved trails with your pup, let her burn off some energy in the park\'s open spaces, and take her to the sloped shoreline where she can dip her paws in the water. The park has a separate area for small dogs who prefer their own company to the raucous bunch in the large dog area.', 2, 'https://photos.bringfido.com/photo/2019/06/17/Magnuson_Bark_Park.jpg?size=entry&density=2x');

COMMIT;


-- -----------------------------------------------------
-- Data for table `user_fav_dog_park`
-- -----------------------------------------------------
START TRANSACTION;
USE `dogdb`;
INSERT INTO `user_fav_dog_park` (`user_id`, `dog_park_id`) VALUES (1, 1);
INSERT INTO `user_fav_dog_park` (`user_id`, `dog_park_id`) VALUES (2, 2);
INSERT INTO `user_fav_dog_park` (`user_id`, `dog_park_id`) VALUES (3, 3);
INSERT INTO `user_fav_dog_park` (`user_id`, `dog_park_id`) VALUES (4, 4);
INSERT INTO `user_fav_dog_park` (`user_id`, `dog_park_id`) VALUES (5, 5);
INSERT INTO `user_fav_dog_park` (`user_id`, `dog_park_id`) VALUES (6, 6);
INSERT INTO `user_fav_dog_park` (`user_id`, `dog_park_id`) VALUES (7, 7);
INSERT INTO `user_fav_dog_park` (`user_id`, `dog_park_id`) VALUES (8, 8);
INSERT INTO `user_fav_dog_park` (`user_id`, `dog_park_id`) VALUES (9, 9);
INSERT INTO `user_fav_dog_park` (`user_id`, `dog_park_id`) VALUES (10, 10);

COMMIT;


-- -----------------------------------------------------
-- Data for table `meetup`
-- -----------------------------------------------------
START TRANSACTION;
USE `dogdb`;
INSERT INTO `meetup` (`id`, `title`, `description`, `meetup_date`, `create_date`, `user_id_creator`, `dog_park_id`) VALUES (1, 'dog and chill', 'It is a long established fact that a reader will be distracted by the readable content of a page when looking at its layout. The point of using Lorem Ipsum is that it has a more-or-less normal distribution of letters, as opposed to using \'Content here, content here\', making it look like readable English. Many desktop publishing packages and web page editors now use Lorem Ipsum as their default model text, and a search for \'lorem ipsum\' will uncover many web sites still in their infancy. Various versions have evolved over the years, sometimes by accident, sometimes on purpose (injected humour and the like).\nIt is a long established fact that a reader will be distracted by the readable content of a page when looking at its layout. The point of using Lorem Ipsum is that it has a more-or-less normal distribution of letters, as opposed to using \'Content here, content here\', making it look like readable English. Many desktop publishing packages and web page editors now use Lorem Ipsum as their default model text, and a search for \'lorem ipsum\' will uncover many web sites still in their infancy. Various versions have evolved over the years, sometimes by accident, sometimes on purpose (injected humour and the like).\n\n\n', '2020-10-10 12:30:16', '2020-09-10 12:30:17', 1, 1);
INSERT INTO `meetup` (`id`, `title`, `description`, `meetup_date`, `create_date`, `user_id_creator`, `dog_park_id`) VALUES (2, 'love to run with my dog', 'if you like to run with your dog lets do it together!', '2021-10-10 12:30:16', '2020-12-10 12:30:17', 2, 2);
INSERT INTO `meetup` (`id`, `title`, `description`, `meetup_date`, `create_date`, `user_id_creator`, `dog_park_id`) VALUES (3, 'swimming dogs?', 'looking for companion for my companion', '2021-10-05 12:30:16', '2020-02-10 12:30:17', 3, 3);
INSERT INTO `meetup` (`id`, `title`, `description`, `meetup_date`, `create_date`, `user_id_creator`, `dog_park_id`) VALUES (4, 'looking for a nice respectable human and dog to meetup with', 'love to swim with my dog, r u interested?', '2022-10-10 12:30:16', '2020-12-10 09:30:17', 4, 4);
INSERT INTO `meetup` (`id`, `title`, `description`, `meetup_date`, `create_date`, `user_id_creator`, `dog_park_id`) VALUES (5, 'new to this thing', 'never been to a dog meetup, anyone else out there relate?', '2021-10-04 12:30:16', '2020-06-10 12:30:17', 5, 5);
INSERT INTO `meetup` (`id`, `title`, `description`, `meetup_date`, `create_date`, `user_id_creator`, `dog_park_id`) VALUES (6, 'great scenery', 'ive never seen a place more amazing', '2021-10-04 12:30:16', '2020-06-10 12:30:17', 6, 6);
INSERT INTO `meetup` (`id`, `title`, `description`, `meetup_date`, `create_date`, `user_id_creator`, `dog_park_id`) VALUES (7, 'bring towels!', 'the towel station ran out so we were soaking wet on the walk home', '2021-10-04 12:30:16', '2020-06-10 12:30:17', 7, 7);
INSERT INTO `meetup` (`id`, `title`, `description`, `meetup_date`, `create_date`, `user_id_creator`, `dog_park_id`) VALUES (8, 'great atmosphere', 'everyone is sooo friendly and welcoming', '2021-10-04 12:30:16', '2020-06-10 12:30:17', 8, 8);
INSERT INTO `meetup` (`id`, `title`, `description`, `meetup_date`, `create_date`, `user_id_creator`, `dog_park_id`) VALUES (9, 'what a hike!', 'next time i wont wear flip flops and will bring my hiking shoes', '2021-10-04 12:30:16', '2020-06-10 12:30:17', 9, 9);
INSERT INTO `meetup` (`id`, `title`, `description`, `meetup_date`, `create_date`, `user_id_creator`, `dog_park_id`) VALUES (10, 'my dog is now a hipdog', 'such a great latte and they even had pooch lattes for free!', '2021-10-04 12:30:16', '2020-06-10 12:30:17', 10, 10);

COMMIT;


-- -----------------------------------------------------
-- Data for table `meetup_comment`
-- -----------------------------------------------------
START TRANSACTION;
USE `dogdb`;
INSERT INTO `meetup_comment` (`id`, `comment_date`, `comment_text`, `title`, `reply_to_comment_id`, `meetup_id`, `user_id`) VALUES (1, '2020-10-10 12:30:16', 'The app lets you search for groups or activities or events by key word....or just scroll through all the events that meet-up groups have planned for the week and just click to join and get the info!', 'My Dog has fleas, Help?', NULL, 1, 1);
INSERT INTO `meetup_comment` (`id`, `comment_date`, `comment_text`, `title`, `reply_to_comment_id`, `meetup_id`, `user_id`) VALUES (2, '2020-10-11 12:30:16', 'I am down!', 'Lets go for a run!', 1, 1, 1);
INSERT INTO `meetup_comment` (`id`, `comment_date`, `comment_text`, `title`, `reply_to_comment_id`, `meetup_id`, `user_id`) VALUES (3, '2020-10-09 12:30:16', 'Plenty of great trails in the area, lets go sometime', 'Trails near by?', 1, 1, 1);
INSERT INTO `meetup_comment` (`id`, `comment_date`, `comment_text`, `title`, `reply_to_comment_id`, `meetup_id`, `user_id`) VALUES (4, '2020-10-08 12:30:16', 'id like to join', 'New fun in town', 3, 3, 3);
INSERT INTO `meetup_comment` (`id`, `comment_date`, `comment_text`, `title`, `reply_to_comment_id`, `meetup_id`, `user_id`) VALUES (5, '2020-12-09 12:30:16', 'how is your dog able to do that?', 'doggie tricks', 5, 5, 2);
INSERT INTO `meetup_comment` (`id`, `comment_date`, `comment_text`, `title`, `reply_to_comment_id`, `meetup_id`, `user_id`) VALUES (6, '2020-12-09 12:30:16', 'i agree, best way to meet other dog lovers', 'How else ?', 3, 3, 4);
INSERT INTO `meetup_comment` (`id`, `comment_date`, `comment_text`, `title`, `reply_to_comment_id`, `meetup_id`, `user_id`) VALUES (7, '2020-12-09 12:30:16', 'It was great seeing you as well', 'Best thing ever.', 4, 4, 5);
INSERT INTO `meetup_comment` (`id`, `comment_date`, `comment_text`, `title`, `reply_to_comment_id`, `meetup_id`, `user_id`) VALUES (8, '2020-12-09 12:30:16', 'it was a common misunderstanding about the rules. dont let the title fool you.', 'Don’t dare break the rules or ruffle group leaders skirt:', 6, 6, 6);
INSERT INTO `meetup_comment` (`id`, `comment_date`, `comment_text`, `title`, `reply_to_comment_id`, `meetup_id`, `user_id`) VALUES (9, '2020-12-09 12:30:16', 'i was feeling the same way, glad we could connect!', 'What a god-send for people in situations where they are lonely or want to meet new people!\n', 7, 7, 7);
INSERT INTO `meetup_comment` (`id`, `comment_date`, `comment_text`, `title`, `reply_to_comment_id`, `meetup_id`, `user_id`) VALUES (10, '2020-12-09 12:30:16', 'id talk to an admin about that', 'I\'ve only just made an account. Is this normal?', 8, 8, 8);

COMMIT;


-- -----------------------------------------------------
-- Data for table `user_friend_request`
-- -----------------------------------------------------
START TRANSACTION;
USE `dogdb`;
INSERT INTO `user_friend_request` (`user_id`, `friend_id`) VALUES (1, 3);
INSERT INTO `user_friend_request` (`user_id`, `friend_id`) VALUES (1, 2);
INSERT INTO `user_friend_request` (`user_id`, `friend_id`) VALUES (1, 4);
INSERT INTO `user_friend_request` (`user_id`, `friend_id`) VALUES (4, 5);
INSERT INTO `user_friend_request` (`user_id`, `friend_id`) VALUES (5, 4);
INSERT INTO `user_friend_request` (`user_id`, `friend_id`) VALUES (6, 2);
INSERT INTO `user_friend_request` (`user_id`, `friend_id`) VALUES (7, 3);
INSERT INTO `user_friend_request` (`user_id`, `friend_id`) VALUES (8, 4);
INSERT INTO `user_friend_request` (`user_id`, `friend_id`) VALUES (9, 6);
INSERT INTO `user_friend_request` (`user_id`, `friend_id`) VALUES (10, 2);

COMMIT;


-- -----------------------------------------------------
-- Data for table `route`
-- -----------------------------------------------------
START TRANSACTION;
USE `dogdb`;
INSERT INTO `route` (`id`, `start_time`, `end_time`, `user_id`) VALUES (1, '2020-10-10 12:30:16', '2020-10-11 12:30:40', 1);
INSERT INTO `route` (`id`, `start_time`, `end_time`, `user_id`) VALUES (2, '2020-10-07 12:30:16', '2020-10-07 2:30:16', 2);
INSERT INTO `route` (`id`, `start_time`, `end_time`, `user_id`) VALUES (3, '2020-10-08 12:30:16', '2020-10-08 1:30:16', 3);
INSERT INTO `route` (`id`, `start_time`, `end_time`, `user_id`) VALUES (4, '2020-10-09 12:30:16', '2020-10-09 12:40:16', 4);
INSERT INTO `route` (`id`, `start_time`, `end_time`, `user_id`) VALUES (5, '2020-10-11 12:30:16', '2020-10-11 12:50:16', 5);
INSERT INTO `route` (`id`, `start_time`, `end_time`, `user_id`) VALUES (6, '2020-10-11 12:30:16', '2020-10-11 12:30:17', 6);
INSERT INTO `route` (`id`, `start_time`, `end_time`, `user_id`) VALUES (7, '2020-10-11 12:30:16', '2020-10-11 12:30:17', 7);
INSERT INTO `route` (`id`, `start_time`, `end_time`, `user_id`) VALUES (8, '2020-10-11 12:30:16', '2020-10-11 12:30:17', 8);
INSERT INTO `route` (`id`, `start_time`, `end_time`, `user_id`) VALUES (9, '2020-10-11 12:30:16', '2020-10-11 12:30:17', 9);
INSERT INTO `route` (`id`, `start_time`, `end_time`, `user_id`) VALUES (10, '2020-10-11 12:30:16', '2020-10-11 12:30:17', 10);

COMMIT;


-- -----------------------------------------------------
-- Data for table `location`
-- -----------------------------------------------------
START TRANSACTION;
USE `dogdb`;
INSERT INTO `location` (`id`, `lat`, `lng`, `point_time`, `route_id`, `user_id`) VALUES (1, 33.47532343320456, -117.10128305844954, '2020-10-10 12:30:16', 1, 1);
INSERT INTO `location` (`id`, `lat`, `lng`, `point_time`, `route_id`, `user_id`) VALUES (2,  -15.74015, -115.54582, '2020-12-10 12:30:16', 2, 2);
INSERT INTO `location` (`id`, `lat`, `lng`, `point_time`, `route_id`, `user_id`) VALUES (3, 41.69936, 28.15432, '2020-09-10 12:30:16', 3, 3);
INSERT INTO `location` (`id`, `lat`, `lng`, `point_time`, `route_id`, `user_id`) VALUES (4, 1.54610, 124.56892, '2020-10-02 12:30:16', 4, 4);
INSERT INTO `location` (`id`, `lat`, `lng`, `point_time`, `route_id`, `user_id`) VALUES (5,  -32.48064, 134.23825, '2020-09-10 12:30:16', 5, 5);

COMMIT;


-- -----------------------------------------------------
-- Data for table `dog_has_meetup`
-- -----------------------------------------------------
START TRANSACTION;
USE `dogdb`;
INSERT INTO `dog_has_meetup` (`dog_id`, `meetup_id`) VALUES (1, 1);
INSERT INTO `dog_has_meetup` (`dog_id`, `meetup_id`) VALUES (2, 2);
INSERT INTO `dog_has_meetup` (`dog_id`, `meetup_id`) VALUES (3, 3);
INSERT INTO `dog_has_meetup` (`dog_id`, `meetup_id`) VALUES (4, 4);
INSERT INTO `dog_has_meetup` (`dog_id`, `meetup_id`) VALUES (5, 5);
INSERT INTO `dog_has_meetup` (`dog_id`, `meetup_id`) VALUES (6, 6);
INSERT INTO `dog_has_meetup` (`dog_id`, `meetup_id`) VALUES (7, 7);
INSERT INTO `dog_has_meetup` (`dog_id`, `meetup_id`) VALUES (8, 8);
INSERT INTO `dog_has_meetup` (`dog_id`, `meetup_id`) VALUES (9, 9);
INSERT INTO `dog_has_meetup` (`dog_id`, `meetup_id`) VALUES (10, 10);

COMMIT;


-- -----------------------------------------------------
-- Data for table `dog_park_comment`
-- -----------------------------------------------------
START TRANSACTION;
USE `dogdb`;
INSERT INTO `dog_park_comment` (`id`, `comment_date`, `comment_text`, `title`, `reply_to_comment_id`, `dog_park_id`, `user_id`) VALUES (1, '2020-10-10 ', 'WOAH, big field!', 'Big Field', NULL, 1, 1);
INSERT INTO `dog_park_comment` (`id`, `comment_date`, `comment_text`, `title`, `reply_to_comment_id`, `dog_park_id`, `user_id`) VALUES (2, '2020-10-09 12:30:16', 'this is a great coversation', 'Doggie Stations', 1, 1, 1);
INSERT INTO `dog_park_comment` (`id`, `comment_date`, `comment_text`, `title`, `reply_to_comment_id`, `dog_park_id`, `user_id`) VALUES (3, '2020-10-08 12:30:16', 'i love talking soo much!', 'Date night?', 2, 1, 1);
INSERT INTO `dog_park_comment` (`id`, `comment_date`, `comment_text`, `title`, `reply_to_comment_id`, `dog_park_id`, `user_id`) VALUES (4, '2020-10-09 12:30:16', 'That looks awesome!', 'Fun activities near me', 3, 2, 2);
INSERT INTO `dog_park_comment` (`id`, `comment_date`, `comment_text`, `title`, `reply_to_comment_id`, `dog_park_id`, `user_id`) VALUES (5, '2019-10-08 12:30:16', 'So much fun and space there!', 'Parks around here', 4, 3, 3);
INSERT INTO `dog_park_comment` (`id`, `comment_date`, `comment_text`, `title`, `reply_to_comment_id`, `dog_park_id`, `user_id`) VALUES (6, '2019-10-08 12:30:16', 'I will say that I enjoy taking my dog to off leash parks, but it seems like I have', 'Are dog parks just simply a bad idea? ', 5, 4, 4);
INSERT INTO `dog_park_comment` (`id`, `comment_date`, `comment_text`, `title`, `reply_to_comment_id`, `dog_park_id`, `user_id`) VALUES (7, '2019-10-08 12:30:16', 'so today was my 4th and final time going to a dog park... Why? I took my 4 month old 30 pound american eskimo mix and he was running around having a great time, well some lady some shows up with her 3 year old kid who decides he is going to run and around and play with the dogs..next thing you know my puppy being the playful friendly little dog he is jumps up on the kids back (not aggressively at all) but it must have frightened the kid, or maybe he got scratched and the kid bursts into tears. ', 'dog park rant (lab, bulldog, terrier)', 6, 5, 5);
INSERT INTO `dog_park_comment` (`id`, `comment_date`, `comment_text`, `title`, `reply_to_comment_id`, `dog_park_id`, `user_id`) VALUES (8, '2019-10-08 12:30:16', 'Ok, I\'m not even sure if I should be this cranky or not. I normally LOVE the dog park I take my guys to, but today I was fed up.  ', 'Dog Park Etiquette', 7, 6, 6);
INSERT INTO `dog_park_comment` (`id`, `comment_date`, `comment_text`, `title`, `reply_to_comment_id`, `dog_park_id`, `user_id`) VALUES (9, '2019-10-08 12:30:16', 'So from an earlier post I was thinking of getting rid of my puppy but have decided against it. In an effort to socialize her I took her to a dog park, shes a pit and is 4 months old. At first I put her in the big dog area (since shes gonna get big) but it freaked her out.', 'First time at dog park...is this normal? (bulldog, yorkie, aggressive)', 8, 7, 7);
INSERT INTO `dog_park_comment` (`id`, `comment_date`, `comment_text`, `title`, `reply_to_comment_id`, `dog_park_id`, `user_id`) VALUES (10, '2019-10-08 12:30:16', 'I took Reina, my chihuhua x, to the offleash dog park today. First of all, you can probably guess that it didn\'t go to well, seeing as I\'m posting here.', 'Dog park drama', 9, 8, 8);

COMMIT;


-- -----------------------------------------------------
-- Data for table `dog_park_review`
-- -----------------------------------------------------
START TRANSACTION;
USE `dogdb`;
INSERT INTO `dog_park_review` (`rating`, `review`, `img_url`, `user_id`, `review_date`, `dog_park_id`, `last_updated`) VALUES (5, 'Great park, open spaces for the pups to roam. The staff is extremely friendly and there is always a show for the dogs to enjoy.', 'https://lh5.googleusercontent.com/p/AF1QipNTi44jKHG3HEtKPMv23pV7LupFneqUtx1MTksO=w408-h306-k-no', 1, '2020-10-10 12:30:16', 1, NULL);
INSERT INTO `dog_park_review` (`rating`, `review`, `img_url`, `user_id`, `review_date`, `dog_park_id`, `last_updated`) VALUES (4, 'This park is well-maintained for both dogs and their humans. There\'s water fountains, benches, shade trees and filled doggie bag dispensers with trash cans. The small dog side is very roomy and the large dog side is sectioned off to allow space between the dogs.\n', 'https://s3-media0.fl.yelpcdn.com/bphoto/sb2KT9vHSj6cntKi4eXdtg/180s.jpg', 2, '2019-10-10 12:30:16', 2, NULL);
INSERT INTO `dog_park_review` (`rating`, `review`, `img_url`, `user_id`, `review_date`, `dog_park_id`, `last_updated`) VALUES (5, 'The entrance is a little hidden but when you drive up there\'s lots of parking. Dog park is clean and and very spacious. I like that there are different sections for different sized dogs. My dog is small so it\'s nice that she can run around with dogs around her size and not feel intimidated by the larger dogs. Around the park there also an open field, tables, and basketball courts for the community. It\'s nice to go here in the afternoon where you can see lots of different types of dogs\n\n', 'https://s3-media0.fl.yelpcdn.com/bphoto/veheOOxJc1yMVSvVPEVfwA/180s.jpg', 3, '2020-11-10 12:30:16', 3, NULL);
INSERT INTO `dog_park_review` (`rating`, `review`, `img_url`, `user_id`, `review_date`, `dog_park_id`, `last_updated`) VALUES (4, 'Loved this little spot to take my bully out and let him play with other dogs! Great maintenance on this place making sure the grass is nice and the trees stay healthy while keeping a clean facility!\n\n', 'https://s3-media0.fl.yelpcdn.com/bphoto/cjO0A8PPR1e1sBZ5ZGGm0w/180s.jpg', 4, '2020-10-12 12:30:16', 4, NULL);
INSERT INTO `dog_park_review` (`rating`, `review`, `img_url`, `user_id`, `review_date`, `dog_park_id`, `last_updated`) VALUES (4, 'Of course, the time you go will impact your experience as to how many dogs are there. We went twice. The first time, there were a few dogs, but the. owners were mindful of their own dog\'s personality in relation to the other dogs. The 2nd time, there was only 1 guy thee with 2 huskies. He was sitting on the bench with his iPod and one of his huskies was starting to show some domineering behaviors over my dogs, so we left. It\'s going to happen, so it\'s important to keep an eye on your dogs.\n', 'https://s3-media0.fl.yelpcdn.com/bphoto/BWpUprjuWkCw-KBDzYM1Aw/300s.jpg', 5, '2018-10-10 12:30:16', 5, NULL);
INSERT INTO `dog_park_review` (`rating`, `review`, `img_url`, `user_id`, `review_date`, `dog_park_id`, `last_updated`) VALUES (4, 'Dog Mountain is located at 143 Parks Road in St Johnsbury, VT. The park is open Friday from 11 a.m. to 4 p.m. and on weekends from 10 a.m. to 5 p.m. Admission is free but donations for park upkeep are welcome.', 'https://advancelocal-adapter-image-uploads.s3.amazonaws.com/expo.advance.net/img/1816ebbbb7/width2048/32f_dogmountainmist02.jpeg', 6, '2018-10-10 12:30:16', 6, NULL);
INSERT INTO `dog_park_review` (`rating`, `review`, `img_url`, `user_id`, `review_date`, `dog_park_id`, `last_updated`) VALUES (5, ' There’s a free rinse station on site but if you’re feeling fancy, dunk your dog in the self-service warm bath. Brushes, towels, shampoo and ear cleaner are all provided for a fee of $8-9.', 'https://media-cdn.tripadvisor.com/media/photo-s/0a/ef/02/55/dog-wood-park.jpg', 7, '2018-10-10 12:30:16', 7, NULL);
INSERT INTO `dog_park_review` (`rating`, `review`, `img_url`, `user_id`, `review_date`, `dog_park_id`, `last_updated`) VALUES (5, 'Pups who decide to dig or go for a dip can be cleaned off in the provided self-service dog wash tubs. You can then treat them to a bite at the on-site Coffee and Treat Bar, which serves human goodies, too. ', 'https://adaliving.files.wordpress.com/2008/01/dog.jpg', 8, '2018-10-10 12:30:16', 8, NULL);
INSERT INTO `dog_park_review` (`rating`, `review`, `img_url`, `user_id`, `review_date`, `dog_park_id`, `last_updated`) VALUES (5, 'The clubhouse provides humans and dogs alike with ample shade, and everybody can cool down at the water fountains on hotter days. At the end of the day, dirty dogs can clean off in the doggy wash station provided.', 'data:image/jpeg;base64,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', 9, '2018-10-10 12:30:16', 9, NULL);
INSERT INTO `dog_park_review` (`rating`, `review`, `img_url`, `user_id`, `review_date`, `dog_park_id`, `last_updated`) VALUES (5, 'The Seattle Barkery rolls up Wednesday to Friday from 10 a.m. to 4 p.m. and on weekends from 9 a.m. to 4 p.m.', 'data:image/jpeg;base64,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', 10, '2018-10-10 12:30:16', 10, NULL);
INSERT INTO `dog_park_review` (`rating`, `review`, `img_url`, `user_id`, `review_date`, `dog_park_id`, `last_updated`) VALUES (NULL, NULL, NULL, DEFAULT, NULL, DEFAULT, NULL);

COMMIT;


-- -----------------------------------------------------
-- Data for table `general_comment`
-- -----------------------------------------------------
START TRANSACTION;
USE `dogdb`;
INSERT INTO `general_comment` (`id`, `comment_date`, `comment_text`, `title`, `reply_to_comment_id`, `user_id`) VALUES (1, '2020-10-10 12:30:16', 'Nam semper maximus elit id porta. Phasellus eu velit purus.', 'What shampoo to use?', NULL, 1);
INSERT INTO `general_comment` (`id`, `comment_date`, `comment_text`, `title`, `reply_to_comment_id`, `user_id`) VALUES (2, '2020-10-10 12:50:16', 'Nam semper maximus elit id porta. Phasellus eu velit purus.', 'Dog Food', 1, 1);
INSERT INTO `general_comment` (`id`, `comment_date`, `comment_text`, `title`, `reply_to_comment_id`, `user_id`) VALUES (3, '2020-08-10 12:50:16', 'Hello everyone. First time dig dad. Wonder daily why I didn\'t get a dog sooner.\n', 'New here & dog breeder question', 3, 2);
INSERT INTO `general_comment` (`id`, `comment_date`, `comment_text`, `title`, `reply_to_comment_id`, `user_id`) VALUES (4, '2020-08-12 12:50:16', 'Anyone have any idea what this could be? It looks like a pimple and the top part that looks like it’s scabbing feels hard. It doesn’t seem to be bothering my dog though.', 'Bump inside of my English Bulldog thigh', 4, 3);
INSERT INTO `general_comment` (`id`, `comment_date`, `comment_text`, `title`, `reply_to_comment_id`, `user_id`) VALUES (5, '2020-08-04 12:50:16', 'For some background--I have been volunteering at an animal shelter as a dog walker for over ten years. I\'ve worked with a lot of dogs that are challenging--leash biting, jumping, mouthing--and generally I\'m regarded as doing very well handling these dogs. I\'ve also done some fostering. Back in February I fostered a dog who was a Humane Investigations rescue, he\'d been found in a crate in a backyard, severely emaciated. Nothing else was known about his past history. I fostered him for several months and he was a delight, until one day I put a new martingale collar on him (with his weight gain the old one didn\'t fit him), carefully checked if it was the correct size by gently pulling on it and he growled at me. He became increasingly aggressive, never actually biting. I contacted the shelter and they had me bring him back.\n', 'Sometimes I feel afraid of my dog even though he\'s given me no reason so far', 2, 4);
INSERT INTO `general_comment` (`id`, `comment_date`, `comment_text`, `title`, `reply_to_comment_id`, `user_id`) VALUES (6, '2020-08-04 12:50:16', 'The last 2 days my dog has been licking his front left paw but no other signs of uncomfort. Today he has a very slight limp and I see that his main paw pad is red.\nI made a vet appointment but it’s 4 days away. Any ideas what it can be or how I can treat until then?', 'Help on Paw redness', 5, 5);
INSERT INTO `general_comment` (`id`, `comment_date`, `comment_text`, `title`, `reply_to_comment_id`, `user_id`) VALUES (7, '2020-08-04 12:50:16', 'I was hoping some of you could share some insight on breed recommendations that fit our life style. (Again your options mean a lot to us hearing from people on this sight with all the experience you have!)', 'Your Opinion is greatly appreciated!', 6, 6);
INSERT INTO `general_comment` (`id`, `comment_date`, `comment_text`, `title`, `reply_to_comment_id`, `user_id`) VALUES (8, '2020-08-04 12:50:16', 'I start online school hours at 8 a.m and end at 2 p.m, there are some days where I finish school hours at 3 p.m, but of course I have to do my homework, so I actually finish everything at 4 or 5 p.m, sometimes a bit earlier if I do my homework in breaktime.', 'A puppy schedule for a busy student', 2, 7);
INSERT INTO `general_comment` (`id`, `comment_date`, `comment_text`, `title`, `reply_to_comment_id`, `user_id`) VALUES (9, '2020-08-04 12:50:16', 'I have a 3 year old english bulldog and she is a great dog. Recently she fel down some steps and hurt herself and now she is going to need a hip operation and due to covid I am only working a few days a week and her insurance wont cover it. ', 'English bulldog operation', 4, 8);
INSERT INTO `general_comment` (`id`, `comment_date`, `comment_text`, `title`, `reply_to_comment_id`, `user_id`) VALUES (10, '2020-08-04 12:50:16', 'He\'s only bit one dog living in the complex and there wasn\'t even any damage, a nick on the little dogs back. My condo association allowed him to stay in the complex as long as he was leashed and had a muzzle on. Since then, rumors spread that my BIG DOG is aggressive.', 'Needing Support', 10, 9);
INSERT INTO `general_comment` (`id`, `comment_date`, `comment_text`, `title`, `reply_to_comment_id`, `user_id`) VALUES (DEFAULT, NULL, NULL, '', NULL, DEFAULT);

COMMIT;

