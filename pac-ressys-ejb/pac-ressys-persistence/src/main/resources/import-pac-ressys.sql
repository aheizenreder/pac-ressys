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
  KEY `FK_4exacsx0nmyxpybp5lubbq239` (`aircraft_type_id`),
  CONSTRAINT `FK_4exacsx0nmyxpybp5lubbq239` FOREIGN KEY (`aircraft_type_id`) REFERENCES `aircraft_type` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `aircraft`
--


--
-- Table structure for table `aircraft_type`
--

DROP TABLE IF EXISTS `aircraft_type`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `aircraft_type` (
  `id` bigint(20) NOT NULL,
  `typeName` varchar(20) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `aircraft_type`
--

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
INSERT INTO `hibernate_sequence` VALUES (1);
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
  `password` varchar(20) NOT NULL,
  `user_name` varchar(20) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_tfed28rxswxnid2st9kf1pvi8` (`id`,`login_name`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
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

-- Dump completed on 2014-07-28 22:24:13
