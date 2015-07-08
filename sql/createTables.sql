CREATE TABLE `role` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `description` varchar(100) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;


CREATE TABLE `user` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `username` varchar(100) NOT NULL,
  `password` varchar(40) NOT NULL,
  `firstname` varchar(100) NOT NULL,
  `lastname` varchar(100) NOT NULL,
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

CREATE TABLE apprentice_trainer (
 apprenticeId bigint(20) NOT NULL,
 trainerId bigint(20) NOT NULL,
 KEY apprenticeId (apprenticeId),
 KEY trainerId (trainerId),
 CONSTRAINT apprenticeId FOREIGN KEY (apprenticeId) REFERENCES user (id),
 CONSTRAINT trainerId FOREIGN KEY (trainerId) REFERENCES user (id),
 PRIMARY KEY (apprenticeId, trainerId)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;

CREATE TABLE learn_object (
  id bigint(20) NOT NULL AUTO_INCREMENT,
  designation VARCHAR(5),
  description TEXT,
  PRIMARY KEY (id)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;

CREATE TABLE entry_learn_object (
  entryId BIGINT(20) NOT NULL,
  learnObjectId BIGINT(20) NOT NULL,
  CONSTRAINT entryId FOREIGN KEY (entryId) REFERENCES entry (id),
  CONSTRAINT learnObjectId FOREIGN KEY (learnObjectId) REFERENCES learn_object (id),
  PRIMARY KEY (learnObjectId, entryId)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;

CREATE TABLE competence_area (
  id BIGINT(20) NOT NULL AUTO_INCREMENT,
  letter VARCHAR(1) NOT NULL,
  description text NOT NULL,
  PRIMARY KEY (id)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;

CREATE TABLE competence (
  id BIGINT(20) NOT NULL AUTO_INCREMENT,
  description TEXT NOT NULL,
  number INT NOT NULL,
  PRIMARY KEY (id)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;

CREATE TABLE semester (
  id BIGINT(20) NOT NULL AUTO_INCREMENT,
  inter_company_course BOOLEAN,
  vocational_school BOOLEAN,
  planned BOOLEAN,
  start_date DATE,
  end_date DATE,
  PRIMARY KEY (id)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;

CREATE TABLE rating (
  id BIGINT(20) NOT NULL AUTO_INCREMENT,
  semesterId BIGINT(20),
  learnObjectId BIGINT(20),
  rating TINYINT(1),
  PRIMARY KEY (id),
  CONSTRAINT semester_Id FOREIGN KEY (semesterId) REFERENCES semester (id),
  CONSTRAINT learnObject_Id FOREIGN KEY (learnObjectId) REFERENCES learn_object (id)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;

CREATE TABLE learn_object_semester (
  semesterId BIGINT(20) NOT NULL,
  learnObjectId BIGINT(20) NOT NULL,
  CONSTRAINT fk_semesterID FOREIGN KEY (semesterId) REFERENCES semester (id),
  CONSTRAINT fk_learnObjectId FOREIGN KEY (learnObjectId) REFERENCES learn_object (id),
  PRIMARY KEY (semesterId, learnObjectId)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;

CREATE TABLE  competence_area_competence (
  competenceId BIGINT(20) NOT NULL,
  competenceAreaId BIGINT(20) NOT NULL,
  CONSTRAINT fk_competenceId FOREIGN KEY (competenceId) REFERENCES competence (id),
  CONSTRAINT fk_competenceAreaId FOREIGN KEY (competenceAreaId) REFERENCES competence_area (id)
)  ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;

CREATE TABLE  learn_object_competence (
  competenceId BIGINT(20) NOT NULL,
  learnObjectId BIGINT(20) NOT NULL,
  CONSTRAINT fk_competenceId FOREIGN KEY (competenceId) REFERENCES competence (id),
  CONSTRAINT fk_learnObjectId FOREIGN KEY (learnObjectId) REFERENCES learn_object (id)
)  ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;


insert into role(id, description) values(null, "apprentice");
insert into role(id, description) values(null, "trainer");
insert into role(id, description) values(null, "vocation trainer");

INSERT INTO user(id,username, password, firstname, lastname, fk_roleId) VALUES(null,"test_apprentice","12345", "firstApprentice", "lastApprentice", 1);
INSERT INTO user(id,username, password, firstname, lastname, fk_roleId) VALUES(null,"test_apprentice2","12345", "firstApprentice", "lastApprentice", 1);
INSERT INTO user(id,username, password, firstname, lastname, fk_roleId) VALUES(null,"test_apprentice3","12345", "firstApprentice", "lastApprentice", 1);
INSERT INTO user(id,username, password, firstname, lastname, fk_roleId) VALUES(null,"test_apprentice4","12345", "firstApprentice", "lastApprentice", 1);
INSERT INTO user(id,username, password, firstname, lastname, fk_roleId) VALUES(null,"test_trainer","12345", "firstTrainer", "lastTrainer", 2);
INSERT INTO user(id,username, password, firstname, lastname, fk_roleId) VALUES(null,"test_trainer2","12345", "firstTrainer", "lastTrainer", 2);
INSERT INTO user(id,username, password, firstname, lastname, fk_roleId) VALUES(null,"test_vocationTrainer","12345","firstVocationTrainer", "lastVocationTrainer", 3);

INSERT INTO user(id,username, password, fk_roleId) VALUES(null,"hash_user","8cb2237d0679ca88db6464eac60da96345513964", 2);

insert into apprentice_trainer (trainerId, apprenticeId) values(1,3);
insert into apprentice_trainer (trainerId, apprenticeId) values(1,5);
insert into apprentice_trainer (trainerId, apprenticeId) values(8,6);
insert into apprentice_trainer (trainerId, apprenticeId) values(8,7);


select id, username, firstname, lastname, fk_roleId from user u, apprentice_trainer at where at.trainerId=:trainerId AND u.id = at.apprenticeId;

INSERT INTO entry(id, title, body, created, fk_userId) VALUES(null,"Hi i am xliquidzz", "I am the body of xliquidzz's entry.", NOW(), NOW(), 1);
INSERT INTO entry(id, title, body, created, fk_userId) VALUES(null,"Hey its me the test_user", "I am the body of the test_user's entry.", NOW(), NOW(), 2);
