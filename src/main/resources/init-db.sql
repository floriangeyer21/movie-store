CREATE DATABASE movie_store DEFAULT CHARACTER SET utf8 COLLATE utf8_unicode_ci;

CREATE TABLE `movie_store`.`movies` (
  `movie_id` BIGINT(11) NOT NULL AUTO_INCREMENT,
  `title` VARCHAR(255) NOT NULL,
  `description` VARCHAR(255) NOT NULL,
 `deleted` BOOLEAN NOT NULL DEFAULT false,
 PRIMARY KEY (`movie_id`));