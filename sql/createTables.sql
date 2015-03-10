CREATE TABLE `role` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `description` varchar(100) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;


CREATE TABLE `user` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `username` varchar(100) NOT NULL,
  `password` varchar(40) NOT NULL,
  `fk_roleId` bigint(20) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `username` (`username`),
  KEY `fk_roleId` (`fk_roleId`),
  CONSTRAINT `fk_roleId` FOREIGN KEY (`fk_roleId`) REFERENCES `role` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;


CREATE TABLE `entry` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `title` varchar(100) NOT NULL,
  `message` text NOT NULL,
  `created` timestamp,
  `draft` boolean NOT NULL,
  `fk_userId` bigint(20) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_userId` (`fk_userId`),
  CONSTRAINT `fk_userId` FOREIGN KEY (`fk_userId`) REFERENCES `user` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;



____

CREATE TABLE user_categories (
 categoryId bigint(20) NOT NULL,
 userId bigint(20) NOT NULL,
 KEY categoryId (categoryId),
 KEY userId (userId),
 CONSTRAINT categoryId FOREIGN KEY (categoryId) REFERENCES category (id),
 CONSTRAINT userId FOREIGN KEY (userId) REFERENCES user (id),
 PRIMARY KEY (categoryId, userId)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;


insert into role(description) values("apprentice");

INSERT INTO user(id,username, password, fk_roleId) VALUES(null,"xliquidzz","12345", 1);
INSERT INTO user(id,username, password, fk_roleId) VALUES(null,"test_user","12345", 1);
INSERT INTO user(id,username, password, fk_roleId) VALUES(null,"hash_user","8cb2237d0679ca88db6464eac60da96345513964", 2);


INSERT INTO entry(id, title, body, created, fk_userId) VALUES(null,"Hi i am xliquidzz", "I am the body of xliquidzz's entry.", NOW(), NOW(), 1);
INSERT INTO entry(id, title, body, created, fk_userId) VALUES(null,"Hey its me the test_user", "I am the body of the test_user's entry.", NOW(), NOW(), 2);
