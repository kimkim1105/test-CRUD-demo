CREATE DATABASE  IF NOT EXISTS `testdemo` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `testdemo`;
-- MySQL dump 10.13  Distrib 8.0.29, for Win64 (x86_64)
--
-- Host: localhost    Database: testdemo
-- ------------------------------------------------------
-- Server version	8.0.30

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `books`
--

DROP TABLE IF EXISTS `books`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `books` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `status` bit(1) NOT NULL,
  `active` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=22 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `books`
--

LOCK TABLES `books` WRITE;
/*!40000 ALTER TABLE `books` DISABLE KEYS */;
INSERT INTO `books` VALUES (7,'erterwet235425789',_binary '','free'),(2,'book100000',_binary '','free'),(3,'book30',_binary '\0','free'),(8,'sdfgsdffgsd',_binary '','free'),(9,'asdfgwe',_binary '','free'),(10,'asdfgwe',_binary '','free'),(11,'hlaihdsaig',_binary '\0','free'),(12,'hlaihdsaigasfgsg',_binary '\0','free'),(13,'xzvxvc',_binary '','free'),(14,'adsfgh',_binary '','free'),(15,'adsfgh',_binary '','free'),(16,'adsfgh',_binary '','free'),(17,'adsfgh',_binary '','free'),(18,'afgh',_binary '','free'),(19,'asdfdsgf',_binary '','free'),(20,'adfsghdj',_binary '','borrowing'),(21,'sdfgfhgfh',_binary '','borrowing');
/*!40000 ALTER TABLE `books` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `borrowbooks`
--

DROP TABLE IF EXISTS `borrowbooks`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `borrowbooks` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `date` date DEFAULT NULL,
  `book_id` bigint DEFAULT NULL,
  `student_id` bigint DEFAULT NULL,
  `note` varchar(255) DEFAULT NULL,
  `status` bit(1) NOT NULL,
  `return_date` date DEFAULT NULL,
  `type` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKf6nd0olp6alp99cooitbpbyi6` (`book_id`),
  KEY `FK27dr5oe56bngcp7w7em4764fa` (`student_id`)
) ENGINE=MyISAM AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `borrowbooks`
--

LOCK TABLES `borrowbooks` WRITE;
/*!40000 ALTER TABLE `borrowbooks` DISABLE KEYS */;
INSERT INTO `borrowbooks` VALUES (6,'2022-09-12',21,65,NULL,_binary '','2022-09-12','completed'),(7,'2022-09-12',20,20,NULL,_binary '',NULL,'processing'),(8,'2022-09-13',21,65,NULL,_binary '',NULL,'processing');
/*!40000 ALTER TABLE `borrowbooks` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `students`
--

DROP TABLE IF EXISTS `students`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `students` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `status` bit(1) NOT NULL,
  `active` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=68 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `students`
--

LOCK TABLES `students` WRITE;
/*!40000 ALTER TABLE `students` DISABLE KEYS */;
INSERT INTO `students` VALUES (14,'1323465',_binary '\0','free'),(15,'1323465gjkgk',_binary '','free'),(16,'1323656',_binary '','free'),(17,'1323656',_binary '','free'),(18,'64686',_binary '','free'),(19,'135464',_binary '','free'),(20,'135464',_binary '','borrowing'),(65,'asdfgh',_binary '','borrowing'),(67,'sadfgh',_binary '','free'),(66,'dfsgdfh',_binary '','free');
/*!40000 ALTER TABLE `students` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping routines for database 'testdemo'
--
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2022-09-13 13:40:15
