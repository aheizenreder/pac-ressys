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
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `aircraft_type` (
  `id` bigint(20) NOT NULL,
  `typeName` varchar(20) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;
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
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `hibernate_sequence` (
  `next_val` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;
INSERT INTO `hibernate_sequence` VALUES (1);
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `role` (
  `id` bigint(20) NOT NULL,
  `role` varchar(255) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_ktrknxxdgpracvg9x4h9cqt4m` (`id`,`role`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;
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
