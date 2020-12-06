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
  `name` VARCHAR(45) NOT NULL DEFAULT 'dog',
  `breed` VARCHAR(45) NOT NULL DEFAULT 'breed',
  `temperament` VARCHAR(45) NULL,
  `dog_profile_pic_url` VARCHAR(5000) NULL,
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
INSERT INTO `address` (`id`, `street`, `city`, `state_abbrv`, `zipcode`) VALUES (3, '5500 laramie st', 'chicago', 'IL', '43322');
INSERT INTO `address` (`id`, `street`, `city`, `state_abbrv`, `zipcode`) VALUES (4, '145 boulder rd.', 'denver', 'CO', '24956');
INSERT INTO `address` (`id`, `street`, `city`, `state_abbrv`, `zipcode`) VALUES (5, '1332 alpine blvd.', 'san diego', 'CA', '49245');

COMMIT;


-- -----------------------------------------------------
-- Data for table `user`
-- -----------------------------------------------------
START TRANSACTION;
USE `dogdb`;
INSERT INTO `user` (`id`, `first_name`, `last_name`, `username`, `password`, `email`, `role`, `enabled`, `profile_photo_url`, `bio`, `create_date`, `date_option`, `profile_private`, `location_private`, `address_id`) VALUES (1, '', '', 'admin', '$2a$10$gZVyrDbxj9HMZqWEhxMBXemjsVmKJX.LbWqD9Oz96wvCIA099ipUe\n', 'admin@mail.com', 'admin', 1, NULL, NULL, '2020-10-10 ', NULL, NULL, NULL, 2);
INSERT INTO `user` (`id`, `first_name`, `last_name`, `username`, `password`, `email`, `role`, `enabled`, `profile_photo_url`, `bio`, `create_date`, `date_option`, `profile_private`, `location_private`, `address_id`) VALUES (2, NULL, NULL, 'tonysdog', 'dog', 'tony@mail.com', NULL, 1, NULL, NULL, '2020-10-12', NULL, NULL, NULL, NULL);
INSERT INTO `user` (`id`, `first_name`, `last_name`, `username`, `password`, `email`, `role`, `enabled`, `profile_photo_url`, `bio`, `create_date`, `date_option`, `profile_private`, `location_private`, `address_id`) VALUES (3, NULL, NULL, 'ashleysdogpark', 'dog', 'dog@mail.com', NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `user` (`id`, `first_name`, `last_name`, `username`, `password`, `email`, `role`, `enabled`, `profile_photo_url`, `bio`, `create_date`, `date_option`, `profile_private`, `location_private`, `address_id`) VALUES (4, NULL, NULL, 'danielsfundog', 'dog', 'fundog@mail.com', NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `user` (`id`, `first_name`, `last_name`, `username`, `password`, `email`, `role`, `enabled`, `profile_photo_url`, `bio`, `create_date`, `date_option`, `profile_private`, `location_private`, `address_id`) VALUES (5, NULL, NULL, 'jourdansdoglover', 'dog', 'doglover@mail.com', NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL);

COMMIT;


-- -----------------------------------------------------
-- Data for table `dog`
-- -----------------------------------------------------
START TRANSACTION;
USE `dogdb`;
INSERT INTO `dog` (`id`, `name`, `breed`, `temperament`, `dog_profile_pic_url`, `activity_level`, `size`, `bio`, `birthday`, `rainbow_bridge`, `create_date`, `user_id`, `gender`) VALUES (1, 'Kona', 'Australian Kelpie', 'happy and go lucky', 'https://s3.amazonaws.com/cdn-origin-etr.akc.org/wp-content/uploads/2017/11/14153642/Kelpie.4.jpg', NULL, NULL, NULL, NULL, NULL, '2020-10-10 ', 1, NULL);
INSERT INTO `dog` (`id`, `name`, `breed`, `temperament`, `dog_profile_pic_url`, `activity_level`, `size`, `bio`, `birthday`, `rainbow_bridge`, `create_date`, `user_id`, `gender`) VALUES (2, 'Afa', 'Afador', 'As an athletic mixed breed.', 'https://www.dogtime.com/assets/uploads/2019/08/afador-mixed-dog-breed-pictures-1-1442x1328.jpg', NULL, NULL, NULL, NULL, NULL, '2020-10-10 ', 2, NULL);
INSERT INTO `dog` (`id`, `name`, `breed`, `temperament`, `dog_profile_pic_url`, `activity_level`, `size`, `bio`, `birthday`, `rainbow_bridge`, `create_date`, `user_id`, `gender`) VALUES (3, 'Bassie', 'Basset Hound\n', NULL, 'https://www.dogtime.com/assets/uploads/2011/01/file_23010_basset-hound-460x290.jpg', NULL, NULL, NULL, NULL, NULL, '2020-10-10 ', 3, NULL);
INSERT INTO `dog` (`id`, `name`, `breed`, `temperament`, `dog_profile_pic_url`, `activity_level`, `size`, `bio`, `birthday`, `rainbow_bridge`, `create_date`, `user_id`, `gender`) VALUES (4, 'Boxo', 'Boston Boxer\n', NULL, 'https://www.dogtime.com/assets/uploads/2020/01/boston-boxer-mixed-dog-breed-pictures-1.jpg', NULL, NULL, NULL, NULL, NULL, '2020-10-10 ', 4, NULL);
INSERT INTO `dog` (`id`, `name`, `breed`, `temperament`, `dog_profile_pic_url`, `activity_level`, `size`, `bio`, `birthday`, `rainbow_bridge`, `create_date`, `user_id`, `gender`) VALUES (5, 'Massie', 'Mastiff', NULL, 'https://d17fnq9dkz9hgj.cloudfront.net/breed-uploads/2018/08/mastiff-card-medium.jpg?bust=1535568273&width=560', NULL, NULL, NULL, NULL, NULL, '2020-10-10 ', 5, NULL);
INSERT INTO `dog` (`id`, `name`, `breed`, `temperament`, `dog_profile_pic_url`, `activity_level`, `size`, `bio`, `birthday`, `rainbow_bridge`, `create_date`, `user_id`, `gender`) VALUES (DEFAULT, DEFAULT, DEFAULT, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '2020-10-10 ', DEFAULT, NULL);

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

COMMIT;


-- -----------------------------------------------------
-- Data for table `dog_review`
-- -----------------------------------------------------
START TRANSACTION;
USE `dogdb`;
INSERT INTO `dog_review` (`rating`, `review`, `img_url`, `user_id`, `dog_id`, `review_date`) VALUES (5, 'Nam semper maximus elit id porta. Phasellus eu velit purus.', 'https://images2.minutemediacdn.com/image/upload/c_fill,g_auto,h_1248,w_2220/v1555439209/shape/mentalfloss/345eyrhfj.png?itok=WBbnAek5', 1, 1, '2020-10-10 12:30:16');
INSERT INTO `dog_review` (`rating`, `review`, `img_url`, `user_id`, `dog_id`, `review_date`) VALUES (3, 'dogs get along just fine', NULL, 2, 2, '2020-12-10 12:30:16');
INSERT INTO `dog_review` (`rating`, `review`, `img_url`, `user_id`, `dog_id`, `review_date`) VALUES (1, 'steer clear of this animal!', NULL, 3, 3, '2020-01-10 12:30:16');
INSERT INTO `dog_review` (`rating`, `review`, `img_url`, `user_id`, `dog_id`, `review_date`) VALUES (2, 'dog is not friendly to other cats', NULL, 4, 4, '2020-08-10 12:30:16');
INSERT INTO `dog_review` (`rating`, `review`, `img_url`, `user_id`, `dog_id`, `review_date`) VALUES (4, 'would love to hang out with this dog again', NULL, 5, 5, '2020-08-14 12:30:16');

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

COMMIT;


-- -----------------------------------------------------
-- Data for table `meetup_comment`
-- -----------------------------------------------------
START TRANSACTION;
USE `dogdb`;
INSERT INTO `meetup_comment` (`id`, `comment_date`, `comment_text`, `title`, `reply_to_comment_id`, `meetup_id`, `user_id`) VALUES (1, '2020-10-10 12:30:16', 'Lorem ipsum dolor', 'My Dog has fleas, Help?', NULL, 1, 1);
INSERT INTO `meetup_comment` (`id`, `comment_date`, `comment_text`, `title`, `reply_to_comment_id`, `meetup_id`, `user_id`) VALUES (2, '2020-10-11 12:30:16', 'Lorem ipsum dolor', 'Lets go for a run!', 1, 1, 1);
INSERT INTO `meetup_comment` (`id`, `comment_date`, `comment_text`, `title`, `reply_to_comment_id`, `meetup_id`, `user_id`) VALUES (3, '2020-10-09 12:30:16', 'Lorem ipsum dolor', 'Trails near by?', 2, 1, 1);
INSERT INTO `meetup_comment` (`id`, `comment_date`, `comment_text`, `title`, `reply_to_comment_id`, `meetup_id`, `user_id`) VALUES (4, '2020-10-08 12:30:16', 'id like to join', 'New fun in town', 3, 2, 3);
INSERT INTO `meetup_comment` (`id`, `comment_date`, `comment_text`, `title`, `reply_to_comment_id`, `meetup_id`, `user_id`) VALUES (5, '2020-12-09 12:30:16', 'how is your dog able to do that?', 'doggie tricks', 4, 5, 2);

COMMIT;


-- -----------------------------------------------------
-- Data for table `user_friend_request`
-- -----------------------------------------------------
START TRANSACTION;
USE `dogdb`;
INSERT INTO `user_friend_request` (`user_id`, `friend_id`) VALUES (1, 1);
INSERT INTO `user_friend_request` (`user_id`, `friend_id`) VALUES (2, 3);
INSERT INTO `user_friend_request` (`user_id`, `friend_id`) VALUES (3, 2);
INSERT INTO `user_friend_request` (`user_id`, `friend_id`) VALUES (4, 5);
INSERT INTO `user_friend_request` (`user_id`, `friend_id`) VALUES (5, 4);

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

COMMIT;


-- -----------------------------------------------------
-- Data for table `dog_park_comment`
-- -----------------------------------------------------
START TRANSACTION;
USE `dogdb`;
INSERT INTO `dog_park_comment` (`id`, `comment_date`, `comment_text`, `title`, `reply_to_comment_id`, `dog_park_id`, `user_id`) VALUES (1, '2020-10-10 ', 'Nam semper maximus elit id porta. Phasellus eu velit purus.', 'Big Field', NULL, 1, 1);
INSERT INTO `dog_park_comment` (`id`, `comment_date`, `comment_text`, `title`, `reply_to_comment_id`, `dog_park_id`, `user_id`) VALUES (2, '2020-10-09 12:30:16', 'this is a great coversation', 'Doggie Stations', 1, 1, 1);
INSERT INTO `dog_park_comment` (`id`, `comment_date`, `comment_text`, `title`, `reply_to_comment_id`, `dog_park_id`, `user_id`) VALUES (3, '2020-10-08 12:30:16', 'i love talking soo much!', 'Date night?', 2, 1, 1);
INSERT INTO `dog_park_comment` (`id`, `comment_date`, `comment_text`, `title`, `reply_to_comment_id`, `dog_park_id`, `user_id`) VALUES (4, '2020-10-09 12:30:16', 'That looks awesome!', 'Fun activities near me', 3, 2, 2);
INSERT INTO `dog_park_comment` (`id`, `comment_date`, `comment_text`, `title`, `reply_to_comment_id`, `dog_park_id`, `user_id`) VALUES (5, '2019-10-08 12:30:16', 'So much fun and space there!', 'Parks around here', 4, 3, 3);

COMMIT;


-- -----------------------------------------------------
-- Data for table `dog_park_review`
-- -----------------------------------------------------
START TRANSACTION;
USE `dogdb`;
INSERT INTO `dog_park_review` (`rating`, `review`, `img_url`, `user_id`, `review_date`, `dog_park_id`) VALUES (5, 'Nam semper maximus elit id porta. Phasellus eu velit purus.', 'https://lh5.googleusercontent.com/p/AF1QipNTi44jKHG3HEtKPMv23pV7LupFneqUtx1MTksO=w408-h306-k-no', 1, '2020-10-10 12:30:16', 1);
INSERT INTO `dog_park_review` (`rating`, `review`, `img_url`, `user_id`, `review_date`, `dog_park_id`) VALUES (4, 'This park is well-maintained for both dogs and their humans. There\'s water fountains, benches, shade trees and filled doggie bag dispensers with trash cans. The small dog side is very roomy and the large dog side is sectioned off to allow space between the dogs.\n', 'https://s3-media0.fl.yelpcdn.com/bphoto/sb2KT9vHSj6cntKi4eXdtg/180s.jpg', 2, '2019-10-10 12:30:16', 2);
INSERT INTO `dog_park_review` (`rating`, `review`, `img_url`, `user_id`, `review_date`, `dog_park_id`) VALUES (5, 'The entrance is a little hidden but when you drive up there\'s lots of parking. Dog park is clean and and very spacious. I like that there are different sections for different sized dogs. My dog is small so it\'s nice that she can run around with dogs around her size and not feel intimidated by the larger dogs. Around the park there also an open field, tables, and basketball courts for the community. It\'s nice to go here in the afternoon where you can see lots of different types of dogs\n\n', 'https://s3-media0.fl.yelpcdn.com/bphoto/veheOOxJc1yMVSvVPEVfwA/180s.jpg', 3, '2020-11-10 12:30:16', 3);
INSERT INTO `dog_park_review` (`rating`, `review`, `img_url`, `user_id`, `review_date`, `dog_park_id`) VALUES (4, 'Loved this little spot to take my bully out and let him play with other dogs! Great maintenance on this place making sure the grass is nice and the trees stay healthy while keeping a clean facility!\n\n', 'https://s3-media0.fl.yelpcdn.com/bphoto/cjO0A8PPR1e1sBZ5ZGGm0w/180s.jpg', 4, '2020-10-12 12:30:16', 4);
INSERT INTO `dog_park_review` (`rating`, `review`, `img_url`, `user_id`, `review_date`, `dog_park_id`) VALUES (4, 'Of course, the time you go will impact your experience as to how many dogs are there. We went twice. The first time, there were a few dogs, but the. owners were mindful of their own dog\'s personality in relation to the other dogs. The 2nd time, there was only 1 guy thee with 2 huskies. He was sitting on the bench with his iPod and one of his huskies was starting to show some domineering behaviors over my dogs, so we left. It\'s going to happen, so it\'s important to keep an eye on your dogs.\n', 'https://s3-media0.fl.yelpcdn.com/bphoto/BWpUprjuWkCw-KBDzYM1Aw/300s.jpg', 5, '2018-10-10 12:30:16', 5);

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

COMMIT;

