-- MySQL dump 10.13  Distrib 5.5.34, for debian-linux-gnu (x86_64)
--
-- Host: localhost    Database: pac_ressys
-- ------------------------------------------------------
-- Server version	5.5.34-0ubuntu0.13.04.1

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
-- Table structure for table `Registrant`
--

DROP TABLE IF EXISTS `Registrant`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Registrant` (
  `id` bigint(20) NOT NULL,
  `email` varchar(255) NOT NULL,
  `name` varchar(25) NOT NULL,
  `phone_number` varchar(12) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_it677o4jaydleb048j9oub9mk` (`email`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Registrant`
--

LOCK TABLES `Registrant` WRITE;
/*!40000 ALTER TABLE `Registrant` DISABLE KEYS */;
/*!40000 ALTER TABLE `Registrant` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `aircraft`
--

DROP TABLE IF EXISTS `aircraft`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `aircraft` (
  `id` bigint(20) NOT NULL,
  `aircraft_name` varchar(50) NOT NULL,
  `aircraft_type_id` bigint(20) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_3ex3pkag9fc38w26pngdmyx6n` (`aircraft_name`),
  KEY `FK_4exacsx0nmyxpybp5lubbq239` (`aircraft_type_id`),
  CONSTRAINT `FK_4exacsx0nmyxpybp5lubbq239` FOREIGN KEY (`aircraft_type_id`) REFERENCES `aircraft_type` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `aircraft`
--

LOCK TABLES `aircraft` WRITE;
/*!40000 ALTER TABLE `aircraft` DISABLE KEYS */;
INSERT INTO `aircraft` VALUES (660,'D-KPRT',652),(661,'D-KZYO',652),(662,'D-ASFC',653),(663,'D-APYS',653),(664,'D-CPRO',654),(665,'D-CCLA',655),(666,'D-CREY',656),(667,'D-CMDH',657),(668,'D-IFSH',658),(863,'D-LHLW',862);
/*!40000 ALTER TABLE `aircraft` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `aircraft_type`
--

DROP TABLE IF EXISTS `aircraft_type`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `aircraft_type` (
  `id` bigint(20) NOT NULL,
  `typeName` varchar(100) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `aircraft_type`
--

LOCK TABLES `aircraft_type` WRITE;
/*!40000 ALTER TABLE `aircraft_type` DISABLE KEYS */;
INSERT INTO `aircraft_type` VALUES (652,'B735'),(653,'A320'),(654,'Bombardier Learjet 31A'),(655,'Cessna Citation XLS+'),(656,'Cessna Citation III'),(657,'Cessna Citation Sovereign'),(658,'Piper Cheyenne IIIA'),(680,'B737'),(688,'B737'),(696,'B737'),(704,'B737'),(718,'B737'),(726,'B737'),(734,'B737'),(742,'B737'),(750,'B737'),(758,'B737'),(766,'B737'),(774,'B737'),(782,'B737'),(790,'B737'),(798,'B737'),(806,'B737'),(814,'B737'),(822,'B737'),(830,'B737'),(838,'B737'),(846,'B737'),(854,'B737'),(862,'B737'),(869,'B737');
/*!40000 ALTER TABLE `aircraft_type` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `aircraft_type_to_user`
--

DROP TABLE IF EXISTS `aircraft_type_to_user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `aircraft_type_to_user` (
  `aircraft_type_id` bigint(20) NOT NULL DEFAULT '0',
  `user_id` bigint(20) NOT NULL DEFAULT '0',
  PRIMARY KEY (`aircraft_type_id`,`user_id`),
  KEY `FK_6avdlsiqmn8ytmx6b7g3l0kcg` (`user_id`),
  CONSTRAINT `FK_6avdlsiqmn8ytmx6b7g3l0kcg` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`),
  CONSTRAINT `FK_ou3ncy35hmubfn1ct1gm5dtfg` FOREIGN KEY (`aircraft_type_id`) REFERENCES `aircraft_type` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `aircraft_type_to_user`
--

LOCK TABLES `aircraft_type_to_user` WRITE;
/*!40000 ALTER TABLE `aircraft_type_to_user` DISABLE KEYS */;
INSERT INTO `aircraft_type_to_user` VALUES (652,673),(653,673),(654,673),(655,673),(656,673),(657,673),(658,673),(652,674),(653,674),(656,674);
/*!40000 ALTER TABLE `aircraft_type_to_user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `charter`
--

DROP TABLE IF EXISTS `charter`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `charter` (
  `id` bigint(20) NOT NULL,
  `charter_state` varchar(255) NOT NULL,
  `end_date` datetime NOT NULL,
  `start_date` datetime NOT NULL,
  `aircraft_id` bigint(20) NOT NULL,
  `pilot_id` bigint(20) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_ky9o29cldv73u1toc53cx3ay6` (`aircraft_id`),
  KEY `FK_gpx796y3l1rkhq2msv1e5qtga` (`pilot_id`),
  CONSTRAINT `FK_gpx796y3l1rkhq2msv1e5qtga` FOREIGN KEY (`pilot_id`) REFERENCES `user` (`id`),
  CONSTRAINT `FK_ky9o29cldv73u1toc53cx3ay6` FOREIGN KEY (`aircraft_id`) REFERENCES `aircraft` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `charter`
--

LOCK TABLES `charter` WRITE;
/*!40000 ALTER TABLE `charter` DISABLE KEYS */;
/*!40000 ALTER TABLE `charter` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `hibernate_sequence`
--

DROP TABLE IF EXISTS `hibernate_sequence`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `hibernate_sequence` (
  `next_val` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `hibernate_sequence`
--

LOCK TABLES `hibernate_sequence` WRITE;
/*!40000 ALTER TABLE `hibernate_sequence` DISABLE KEYS */;
INSERT INTO `hibernate_sequence` VALUES (1154);
/*!40000 ALTER TABLE `hibernate_sequence` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `role`
--

DROP TABLE IF EXISTS `role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `role` (
  `id` bigint(20) NOT NULL,
  `role` varchar(255) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_ktrknxxdgpracvg9x4h9cqt4m` (`id`,`role`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `role`
--

LOCK TABLES `role` WRITE;
/*!40000 ALTER TABLE `role` DISABLE KEYS */;
INSERT INTO `role` VALUES (1,'Admin'),(2,'User'),(3,'Guest');
/*!40000 ALTER TABLE `role` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user` (
  `id` bigint(20) NOT NULL,
  `email` varchar(255) NOT NULL,
  `licence_id` varchar(255) DEFAULT NULL,
  `licence_valid_until_date` datetime DEFAULT NULL,
  `login_name` varchar(20) NOT NULL,
  `password` varchar(255) NOT NULL,
  `user_name` varchar(20) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `user_key` (`login_name`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (672,'guest@example.com',NULL,NULL,'guest','69528d52425c3e804ff5a07a1905b128874b3b79fea0b872abdb5800c8fb8f3b','Guest'),(673,'aheizenreder@prodyna.com','AH270578','2024-05-28 00:00:00','andreas','09425a89da54c45a0a54061c68614a8e4382a1ac6658f1784fa1d3fa88e74894','Andreas'),(674,'pilot@example.com','PL011181','2020-11-30 00:00:00','pilot','8bb01e6882140773b84c871b1b854a0b472e47ffd1a080304ccd013763969a56','Pilot');
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_to_role`
--

DROP TABLE IF EXISTS `user_to_role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user_to_role` (
  `role_id` bigint(20) NOT NULL DEFAULT '0',
  `user_id` bigint(20) NOT NULL DEFAULT '0',
  PRIMARY KEY (`role_id`,`user_id`),
  KEY `FK_1elo9khbp42su1qbe33fjgtrj` (`user_id`),
  CONSTRAINT `FK_1elo9khbp42su1qbe33fjgtrj` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`),
  CONSTRAINT `FK_tnqjyhve4aamxaicifkjkbnx6` FOREIGN KEY (`role_id`) REFERENCES `role` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_to_role`
--

LOCK TABLES `user_to_role` WRITE;
/*!40000 ALTER TABLE `user_to_role` DISABLE KEYS */;
INSERT INTO `user_to_role` VALUES (3,672),(1,673),(2,674);
/*!40000 ALTER TABLE `user_to_role` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2014-08-11  3:04:21
