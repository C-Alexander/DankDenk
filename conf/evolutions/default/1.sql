# --- !Ups

CREATE TABLE `category` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(45) DEFAULT NULL,
  `description` varchar(45) DEFAULT NULL,
  CONSTRAINT pk_cat PRIMARY KEY (`id`)
);

CREATE TABLE `meme` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `url` varchar(255) DEFAULT NULL,
  `price` float DEFAULT NULL,
  `DTYPE` varchar(30) DEFAULT 'Meme',
--   `cat_id` int(11) DEFAULT NULL,
  CONSTRAINT pk_meme PRIMARY KEY (`id`)
);

-- ALTER TABLE `meme` ADD CONSTRAINT fk_meme_cat FOREIGN KEY (`cat_id`) REFERENCES `category` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION;

# --- !Downs

drop table if exists meme;

drop table if exists category;