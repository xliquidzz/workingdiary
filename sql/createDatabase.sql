-- MySQL dump 10.13  Distrib 5.6.22, for Win64 (x86_64)
--
-- Host: localhost    Database: workingdiary
-- ------------------------------------------------------
-- Server version	5.6.22-log

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `apprentice_trainer`
--

DROP TABLE IF EXISTS `apprentice_trainer`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `apprentice_trainer` (
  `apprenticeId` bigint(20) NOT NULL,
  `trainerId` bigint(20) NOT NULL,
  PRIMARY KEY (`apprenticeId`,`trainerId`),
  KEY `apprenticeId` (`apprenticeId`),
  KEY `trainerId` (`trainerId`),
  CONSTRAINT `apprenticeId` FOREIGN KEY (`apprenticeId`) REFERENCES `user` (`id`),
  CONSTRAINT `trainerId` FOREIGN KEY (`trainerId`) REFERENCES `user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `apprentice_trainer`
--

LOCK TABLES `apprentice_trainer` WRITE;
/*!40000 ALTER TABLE `apprentice_trainer` DISABLE KEYS */;
INSERT INTO `apprentice_trainer` VALUES (3,1),(5,1);
/*!40000 ALTER TABLE `apprentice_trainer` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `entry`
--

DROP TABLE IF EXISTS `entry`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `entry` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `title` varchar(100) NOT NULL,
  `message` text NOT NULL,
  `created` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `draft` tinyint(1) NOT NULL,
  `fk_userId` bigint(20) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_userId` (`fk_userId`),
  CONSTRAINT `fk_userId` FOREIGN KEY (`fk_userId`) REFERENCES `user` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=31 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `entry`
--

LOCK TABLES `entry` WRITE;
/*!40000 ALTER TABLE `entry` DISABLE KEYS */;
INSERT INTO `entry` VALUES (3,'test','test','2015-03-10 14:13:41',0,2),(10,'test update','<p>test update again</p><p>hhh<b>hhhh</b></p><p>hhhhh</p>','2015-03-16 12:45:30',0,3),(11,'test update','test updated','2015-03-13 15:37:57',0,3),(12,'Lorem Ipsum fpioasdoipsaoksadpo','Lorem ipsum dolor sit amet, consetetur sadipscing elitr, sed diam nonumy eirmod tempor invidunt ut labore et dolore magna aliquyam erat, sed diam voluptua. At vero eos et accusam et justo duo dolores et ea rebum. Stet clita kasd gubergren, no sea takimata sanctus est Lorem ipsum dolor sit amet. Lorem ipsum dolor sit amet, consetetur sadipscing elitr, sed diam nonumy eirmod tempor invidunt ut labore et dolore magna aliquyam erat, sed diam voluptua. At vero eos et accusam et justo duo dolores et ea rebum. Stet clita kasd gubergren, no sea takimata sanctus est Lorem ipsum dolor sit amet.\n\nLorem ipsum dolor sit amet, consetetur sadipscing elitr, sed diam nonumy eirmod tempor invidunt ut labore et dolore magna aliquyam erat, sed diam voluptldféfslésadékladsua. At vero eos et accusam et justo duo dolores et ea rebum. Stet clita kasd gubergren, no sea takimata sanctus est Lorem ipsum dolor sit amet. Lorem ipsum dolor sit amet, consetetur sadipscing elitr, sed diam nonumy eirmod tempor invidunt ut labore et dolore magna aliquyam erat, sed diam voluptua. At vero eos et accusam et justo duo dolores et ea rebum. Stet clita kasd gubergren, no sea takimata sanctus est Lorem ipsum dolor sit amet.','2015-03-13 18:33:11',0,3),(13,'this is my first entry im test_apprentice 3','this is my first entry im test_apprentice 3','2015-03-13 14:53:43',0,5),(14,'this is my second entry im test_apprentice 3','this is my second entry im test_apprentice 3','2015-03-13 14:53:59',0,5),(19,'TEST','<p><b>fasfffas <i>fasdfa<u>fasdasd</u></i></b></p><p><b><i><u><br/></u></i></b></p><p><i><b>rq</b>f</i>ffD</p><p><br/></p><p>FASDASDSAD</p>','2015-03-16 10:30:54',0,3),(20,'Lorem Ipsum','<h1>Lorem Ipsum</h1><h2>Lorem Ipsum</h2><h3>Lorem Ipsum</h3><p><span style=\"color: rgb(51, 51, 51);float: none;background-color: rgb(255, 255, 255);\"><b>Lorem ipsum dolor sit amet, consetetur sadipscing elitr, sed diam nonumy eirmod tempor invidunt ut labore et dolore magna aliquyam erat, sed diam voluptua. At vero eos et accusam et justo duo dolores et ea rebum. Stet clita kasd gubergren, no sea takimata sanctus est Lorem ipsum dolor sit amet. Lorem ipsum dolor sit amet, consetetur sadipscing elitr, sed diam nonumy eirmod tempor invidunt ut labore et dolore magna aliquyam erat, sed diam </b></span></p><p><span style=\"color: rgb(51, 51, 51);float: none;background-color: rgb(255, 255, 255);\"><br/></span></p><p><span style=\"color: rgb(51, 51, 51);float: none;background-color: rgb(255, 255, 255);\"><i>voluptua. At vero eos et accusam et justo duo dolores et ea rebum. Stet clita kasd gubergren, no sea takimata sanctus est Lorem ipsum dolor sit amet. Lorem ipsum dolor sit amet, consetetur sadipscing elitr, sed diam nonumy eirmod tempor invidunt ut labore et dolore magna aliquyam erat, sed diam voluptldféfslésadékladsua. At vero eos et accusam et justo duo dolores et ea rebum. Stet clita kasd gubergren, no sea takimata sanctus est Lorem ipsum dolor sit amet. Lorem ipsum dolor sit amet, consetetur sadipscing elitr, sed diam </i></span></p><p><span style=\"color: rgb(51, 51, 51);float: none;background-color: rgb(255, 255, 255);\"><br/></span></p><p><span style=\"color: rgb(51, 51, 51);float: none;background-color: rgb(255, 255, 255);\"><u>nonumy eirmod tempor invidunt ut labore et dolore magna aliquyam erat, sed diam voluptua. At vero eos et accusam et justo duo dolores et ea rebum. Stet clita kasd gubergren, no sea takimata sanctus est Lorem ipsum dolor sit amet.</u></span><br/></p><p><span style=\"color: rgb(51, 51, 51);float: none;background-color: rgb(255, 255, 255);\"><u><br/></u></span></p><p><span style=\"color: rgb(51, 51, 51);float: none;background-color: rgb(255, 255, 255);\"><u><span style=\"color: rgb(51, 51, 51);float: none;background-color: rgb(255, 255, 255);\"><b>Lorem ipsum dolor sit amet, consetetur sadipscing elitr, sed diam nonumy eirmod tempor invidunt ut labore et dolore magna aliquyam erat, sed diam voluptua. At vero eos et accusam et justo duo dolores et ea rebum. Stet clita kasd gubergren, no sea takimata sanctus est Lorem ipsum dolor sit amet. Lorem ipsum dolor sit amet, consetetur sadipscing elitr, sed diam nonumy eirmod tempor invidunt ut labore et dolore magna aliquyam erat, sed diam </b></span></u></span></p><p><span style=\"color: rgb(51, 51, 51);float: none;background-color: rgb(255, 255, 255);\"><u><span style=\"color: rgb(51, 51, 51);float: none;background-color: rgb(255, 255, 255);\"><br/></span></u></span></p><p><span style=\"color: rgb(51, 51, 51);float: none;background-color: rgb(255, 255, 255);\"><u><span style=\"color: rgb(51, 51, 51);float: none;background-color: rgb(255, 255, 255);\"><i>voluptua. At vero eos et accusam et justo duo dolores et ea rebum. Stet clita kasd gubergren, no sea takimata sanctus est Lorem ipsum dolor sit amet. Lorem ipsum dolor sit amet, consetetur sadipscing elitr, sed diam nonumy eirmod tempor invidunt ut labore et dolore magna aliquyam erat, sed diam </i></span></u></span></p><h1><span style=\"color: rgb(51, 51, 51);float: none;background-color: rgb(255, 255, 255);\"><u><span style=\"color: rgb(51, 51, 51);float: none;background-color: rgb(255, 255, 255);\">voluptldféfslésadékladsua. At vero eos et accusam et justo duo dolores et ea rebum. Stet clita kasd gubergren, no sea takimata sanctus est Lorem ipsum dolor sit amet. Lorem ipsum dolor sit amet, consetetur sadipscing elitr, sed diam nonumy eirmod tempor invidunt ut labore et dolore magna aliquyam erat, sed diam voluptua. At vero eos et accusam et justo duo dolores et ea rebum. Stet clita kasd gubergren, no sea takimata sanctus est Lorem ipsum dolor sit amet.</span><br/></u></span></h1>','2015-03-16 10:43:37',0,3),(21,'Example','<h1>H1 Header</h1><h2>H2 Header</h2><h3>H3 Header</h3><p><br/></p><p><b>Bold</b></p><p><i>krusiv</i></p><p><u>underlined</u></p>','2015-03-16 11:14:57',0,3),(22,'Have done something','<h3>this is a test</h3>','2015-03-16 12:47:56',0,3),(23,'$location','<pre style=\"background-color: #2b2b2b;color: #a9b7c6;\"><span style=\"color: #6a8759;\">$location</span></pre>','2015-03-16 12:48:10',0,3),(24,'test','<h1>1212</h1><h2>2<i>hhh</i></h2><h3>3</h3><p><br/></p><p><b>dsafdsafadsfasfasf jjfjjf</b></p><p><b><br/></b></p><p><b><br/></b></p>','2015-03-17 08:32:57',0,3),(25,'Test','<pre class=\"default prettyprint prettyprinted\" style=\"color: rgb(57, 51, 24);text-align: left;background-color: rgb(238, 238, 238);\"><code style=\"background-color: rgb(238, 238, 238);\"><span class=\"pun\" style=\"color: rgb(0, 0, 0);\">{</span><span class=\"pln\" style=\"color: rgb(0, 0, 0);\">&#10;    height</span><span class=\"pun\" style=\"color: rgb(0, 0, 0);\">:</span><span class=\"pln\" style=\"color: rgb(0, 0, 0);\"> </span><span class=\"lit\" style=\"color: rgb(128, 0, 0);\">100px</span><span class=\"pun\" style=\"color: rgb(0, 0, 0);\">;</span><span class=\"pln\" style=\"color: rgb(0, 0, 0);\">&#10;    max</span><span class=\"pun\" style=\"color: rgb(0, 0, 0);\">-</span><span class=\"pln\" style=\"color: rgb(0, 0, 0);\">height</span><span class=\"pun\" style=\"color: rgb(0, 0, 0);\">:</span><span class=\"pln\" style=\"color: rgb(0, 0, 0);\"> </span><span class=\"lit\" style=\"color: rgb(128, 0, 0);\">100px</span><span class=\"pun\" style=\"color: rgb(0, 0, 0);\">;</span><span class=\"pln\" style=\"color: rgb(0, 0, 0);\">  &#10;</span><span class=\"pun\" style=\"color: rgb(0, 0, 0);\">}</span></code></pre><pre class=\"default prettyprint prettyprinted\" style=\"color: rgb(57, 51, 24);text-align: left;background-color: rgb(238, 238, 238);\"><code style=\"background-color: rgb(238, 238, 238);\"><span class=\"pun\" style=\"color: rgb(0, 0, 0);\">{</span><span class=\"pln\" style=\"color: rgb(0, 0, 0);\">&#10;    height</span><span class=\"pun\" style=\"color: rgb(0, 0, 0);\">:</span><span class=\"pln\" style=\"color: rgb(0, 0, 0);\"> </span><span class=\"lit\" style=\"color: rgb(128, 0, 0);\">100px</span><span class=\"pun\" style=\"color: rgb(0, 0, 0);\">;</span><span class=\"pln\" style=\"color: rgb(0, 0, 0);\">&#10;    max</span><span class=\"pun\" style=\"color: rgb(0, 0, 0);\">-</span><span class=\"pln\" style=\"color: rgb(0, 0, 0);\">height</span><span class=\"pun\" style=\"color: rgb(0, 0, 0);\">:</span><span class=\"pln\" style=\"color: rgb(0, 0, 0);\"> </span><span class=\"lit\" style=\"color: rgb(128, 0, 0);\">100px</span><span class=\"pun\" style=\"color: rgb(0, 0, 0);\">;</span><span class=\"pln\" style=\"color: rgb(0, 0, 0);\">  &#10;</span><span class=\"pun\" style=\"color: rgb(0, 0, 0);\">}</span></code></pre><pre class=\"default prettyprint prettyprinted\" style=\"color: rgb(57, 51, 24);text-align: left;background-color: rgb(238, 238, 238);\"><code style=\"background-color: rgb(238, 238, 238);\"><span class=\"pun\" style=\"color: rgb(0, 0, 0);\">{</span><span class=\"pln\" style=\"color: rgb(0, 0, 0);\">&#10;    height</span><span class=\"pun\" style=\"color: rgb(0, 0, 0);\">:</span><span class=\"pln\" style=\"color: rgb(0, 0, 0);\"> </span><span class=\"lit\" style=\"color: rgb(128, 0, 0);\">100px</span><span class=\"pun\" style=\"color: rgb(0, 0, 0);\">;</span><span class=\"pln\" style=\"color: rgb(0, 0, 0);\">&#10;    max</span><span class=\"pun\" style=\"color: rgb(0, 0, 0);\">-</span><span class=\"pln\" style=\"color: rgb(0, 0, 0);\">height</span><span class=\"pun\" style=\"color: rgb(0, 0, 0);\">:</span><span class=\"pln\" style=\"color: rgb(0, 0, 0);\"> </span><span class=\"lit\" style=\"color: rgb(128, 0, 0);\">100px</span><span class=\"pun\" style=\"color: rgb(0, 0, 0);\">;</span><span class=\"pln\" style=\"color: rgb(0, 0, 0);\">  &#10;</span><span class=\"pun\" style=\"color: rgb(0, 0, 0);\">}</span></code></pre><pre class=\"default prettyprint prettyprinted\" style=\"color: rgb(57, 51, 24);text-align: left;background-color: rgb(238, 238, 238);\"><code style=\"background-color: rgb(238, 238, 238);\"><span class=\"pun\" style=\"color: rgb(0, 0, 0);\">{</span><span class=\"pln\" style=\"color: rgb(0, 0, 0);\">&#10;    height</span><span class=\"pun\" style=\"color: rgb(0, 0, 0);\">:</span><span class=\"pln\" style=\"color: rgb(0, 0, 0);\"> </span><span class=\"lit\" style=\"color: rgb(128, 0, 0);\">100px</span><span class=\"pun\" style=\"color: rgb(0, 0, 0);\">;</span><span class=\"pln\" style=\"color: rgb(0, 0, 0);\">&#10;    max</span><span class=\"pun\" style=\"color: rgb(0, 0, 0);\">-</span><span class=\"pln\" style=\"color: rgb(0, 0, 0);\">height</span><span class=\"pun\" style=\"color: rgb(0, 0, 0);\">:</span><span class=\"pln\" style=\"color: rgb(0, 0, 0);\"> </span><span class=\"lit\" style=\"color: rgb(128, 0, 0);\">100px</span><span class=\"pun\" style=\"color: rgb(0, 0, 0);\">;</span><span class=\"pln\" style=\"color: rgb(0, 0, 0);\">  &#10;</span><span class=\"pun\" style=\"color: rgb(0, 0, 0);\">}</span></code></pre><pre class=\"default prettyprint prettyprinted\" style=\"color: rgb(57, 51, 24);text-align: left;background-color: rgb(238, 238, 238);\"><code style=\"background-color: rgb(238, 238, 238);\"><span class=\"pun\" style=\"color: rgb(0, 0, 0);\">{</span><span class=\"pln\" style=\"color: rgb(0, 0, 0);\">&#10;    height</span><span class=\"pun\" style=\"color: rgb(0, 0, 0);\">:</span><span class=\"pln\" style=\"color: rgb(0, 0, 0);\"> </span><span class=\"lit\" style=\"color: rgb(128, 0, 0);\">100px</span><span class=\"pun\" style=\"color: rgb(0, 0, 0);\">;</span><span class=\"pln\" style=\"color: rgb(0, 0, 0);\">&#10;    max</span><span class=\"pun\" style=\"color: rgb(0, 0, 0);\">-</span><span class=\"pln\" style=\"color: rgb(0, 0, 0);\">height</span><span class=\"pun\" style=\"color: rgb(0, 0, 0);\">:</span><span class=\"pln\" style=\"color: rgb(0, 0, 0);\"> </span><span class=\"lit\" style=\"color: rgb(128, 0, 0);\">100px</span><span class=\"pun\" style=\"color: rgb(0, 0, 0);\">;</span><span class=\"pln\" style=\"color: rgb(0, 0, 0);\">  &#10;</span><span class=\"pun\" style=\"color: rgb(0, 0, 0);\">}</span></code></pre><pre class=\"default prettyprint prettyprinted\" style=\"color: rgb(57, 51, 24);text-align: left;background-color: rgb(238, 238, 238);\"><code style=\"background-color: rgb(238, 238, 238);\"><span class=\"pun\" style=\"color: rgb(0, 0, 0);\">{</span><span class=\"pln\" style=\"color: rgb(0, 0, 0);\">&#10;    height</span><span class=\"pun\" style=\"color: rgb(0, 0, 0);\">:</span><span class=\"pln\" style=\"color: rgb(0, 0, 0);\"> </span><span class=\"lit\" style=\"color: rgb(128, 0, 0);\">100px</span><span class=\"pun\" style=\"color: rgb(0, 0, 0);\">;</span><span class=\"pln\" style=\"color: rgb(0, 0, 0);\">&#10;    max</span><span class=\"pun\" style=\"color: rgb(0, 0, 0);\">-</span><span class=\"pln\" style=\"color: rgb(0, 0, 0);\">height</span><span class=\"pun\" style=\"color: rgb(0, 0, 0);\">:</span><span class=\"pln\" style=\"color: rgb(0, 0, 0);\"> </span><span class=\"lit\" style=\"color: rgb(128, 0, 0);\">100px</span><span class=\"pun\" style=\"color: rgb(0, 0, 0);\">;</span><span class=\"pln\" style=\"color: rgb(0, 0, 0);\">  &#10;</span><span class=\"pun\" style=\"color: rgb(0, 0, 0);\">}</span></code></pre><pre class=\"default prettyprint prettyprinted\" style=\"color: rgb(57, 51, 24);text-align: left;background-color: rgb(238, 238, 238);\"><code style=\"background-color: rgb(238, 238, 238);\"><span class=\"pun\" style=\"color: rgb(0, 0, 0);\">{</span><span class=\"pln\" style=\"color: rgb(0, 0, 0);\">&#10;    height</span><span class=\"pun\" style=\"color: rgb(0, 0, 0);\">:</span><span class=\"pln\" style=\"color: rgb(0, 0, 0);\"> </span><span class=\"lit\" style=\"color: rgb(128, 0, 0);\">100px</span><span class=\"pun\" style=\"color: rgb(0, 0, 0);\">;</span><span class=\"pln\" style=\"color: rgb(0, 0, 0);\">&#10;    max</span><span class=\"pun\" style=\"color: rgb(0, 0, 0);\">-</span><span class=\"pln\" style=\"color: rgb(0, 0, 0);\">height</span><span class=\"pun\" style=\"color: rgb(0, 0, 0);\">:</span><span class=\"pln\" style=\"color: rgb(0, 0, 0);\"> </span><span class=\"lit\" style=\"color: rgb(128, 0, 0);\">100px</span><span class=\"pun\" style=\"color: rgb(0, 0, 0);\">;</span><span class=\"pln\" style=\"color: rgb(0, 0, 0);\">  &#10;</span><span class=\"pun\" style=\"color: rgb(0, 0, 0);\">}</span></code></pre>','2015-03-17 09:13:29',0,3),(26,'List test','<ul><li><b>Test</b></li><li><b>Te</b>st</li><li><b>Test</b></li></ul><span>Test</span><br/><div><span><br/></span></div><div><h1><ol><li>One</li><li>Two</li><li>Three</li></ol></h1></div>','2015-03-17 11:43:48',0,3),(27,'dasf','<p>sdfsadfasf</p>','2015-03-17 11:45:51',0,3),(28,'test','<h1>12345</h1><h2>23456</h2><h3>12345</h3><p><b>fett</b></p><p><i>kurisv</i></p><p><u>underlined</u></p><p><ul><li><u>item</u></li><li><u>item 2</u></li><li><u>item3</u></li></ul><div><ol><li><u>1</u></li><li><u>2</u></li><li><u>3</u></li></ol><div><u><br/></u></div></div></p><p><b><br/></b></p>','2015-03-17 15:22:03',0,3),(29,'test','<p>test</p>','2015-03-17 15:24:53',0,11),(30,'test','<h1>h1</h1><h2>h2</h2><h3>h3</h3><p><b>bold</b></p><p><i>italic</i></p><p><u>underline</u></p><p><ul><li><u>f</u></li><li><u>f</u></li><li><u>f</u></li><li><u>f</u></li></ul><div><ol><li><u>f</u></li><li><u>f</u></li><li><u>f</u></li><li><u>f</u></li><li><u><br/></u></li></ol></div></p>','2015-03-17 15:42:21',0,3);
/*!40000 ALTER TABLE `entry` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `role`
--

DROP TABLE IF EXISTS `role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `role` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `description` varchar(100) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `role`
--

LOCK TABLES `role` WRITE;
/*!40000 ALTER TABLE `role` DISABLE KEYS */;
INSERT INTO `role` VALUES (1,'apprentice'),(2,'trainer'),(3,'vocation trainer');
/*!40000 ALTER TABLE `role` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
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
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (1,'test_trainer','827ccb0eea8a706c4c34a16891f84e7b','firstTrainer','lastTrainer',2),(2,'test_vocationTrainer','827ccb0eea8a706c4c34a16891f84e7b','firstVocationTrainer','lastVocationTrainer',3),(3,'test_apprentice','827ccb0eea8a706c4c34a16891f84e7b','firstApprentice','lastApprentice',1),(5,'test_apprentice2','827ccb0eea8a706c4c34a16891f84e7b','firstApprentice','lastApprentice',1),(8,'test_trainer2','827ccb0eea8a706c4c34a16891f84e7b','firstTrainer','lastTrainer',2),(11,'pxy','neela','Neela','Kazi',1),(12,'testpraxis','827ccb0eea8a706c4c34a16891f84e7b','test','tste',2);
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;


--
-- Table structure for table `schoolmodule`
--

DROP TABLE IF EXISTS `schoolmodule`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `schoolmodule` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `moduleId` varchar(10) NOT NULL,
  `description` varchar(100) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `schoolmodule`
--

LOCK TABLES `schoolmodule` WRITE;
/*!40000 ALTER TABLE `schoolmodule` DISABLE KEYS */;
INSERT INTO `schoolmodule` VALUES (1,'M316', 'Objektorientiert Programmieren'),(2,'M223', 'Entwurfsmustern verstehen und einsetzen'),(3,'M226', 'Datenbankentwurf');
/*!40000 ALTER TABLE `schoolmodule` ENABLE KEYS */;
UNLOCK TABLES;


--
-- Table structure for table `entry_schoolmodule`
--

DROP TABLE IF EXISTS `entry_schoolmodule`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `entry_schoolmodule` (
  `entryId` bigint(20) NOT NULL AUTO_INCREMENT,
  `schoolmoduleId` varchar(10) NOT NULL,
  PRIMARY KEY (`entryId`, `schoolmoduleId`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `entry_schoolmodule`
--

LOCK TABLES `entry_schoolmodule` WRITE;
/*!40000 ALTER TABLE `entry_schoolmodule` DISABLE KEYS */;
INSERT INTO `entry_schoolmodule` VALUES (1,'M316', 'Objektorientiert Programmieren'),(2,'M223', 'Entwurfsmustern verstehen und einsetzen'),(3,'M226', 'Datenbankentwurf');
/*!40000 ALTER TABLE `entry_schoolmodule` ENABLE KEYS */;
UNLOCK TABLES;


/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;


-- Dump completed on 2015-03-20 16:41:45
