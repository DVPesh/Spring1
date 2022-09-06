CREATE DATABASE  IF NOT EXISTS `lesson6` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `lesson6`;
-- MySQL dump 10.13  Distrib 8.0.24, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: lesson6
-- ------------------------------------------------------
-- Server version	8.0.24

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
-- Table structure for table `customers`
--

DROP TABLE IF EXISTS `customers`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `customers` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_to73biqfrti0j9f7k12l255w5` (`name`)
) ENGINE=InnoDB AUTO_INCREMENT=20 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `customers`
--

LOCK TABLES `customers` WRITE;
/*!40000 ALTER TABLE `customers` DISABLE KEYS */;
INSERT INTO `customers` VALUES (4,'Анна'),(2,'Виктор'),(5,'Николай'),(6,'Нина'),(12,'Ольга'),(1,'Полина'),(3,'Сергей');
/*!40000 ALTER TABLE `customers` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `line_items`
--

DROP TABLE IF EXISTS `line_items`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `line_items` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `cost` decimal(8,2) NOT NULL,
  `quantity` float NOT NULL,
  `timestamp` datetime(6) NOT NULL,
  `customer_id` bigint DEFAULT NULL,
  `product_id` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK7p8pxctl1aukvfyr6u2envnko` (`customer_id`),
  KEY `FKb3hu7cw8o38248a3pr3o1aety` (`product_id`),
  CONSTRAINT `FK7p8pxctl1aukvfyr6u2envnko` FOREIGN KEY (`customer_id`) REFERENCES `customers` (`id`),
  CONSTRAINT `FKb3hu7cw8o38248a3pr3o1aety` FOREIGN KEY (`product_id`) REFERENCES `products` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=29 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `line_items`
--

LOCK TABLES `line_items` WRITE;
/*!40000 ALTER TABLE `line_items` DISABLE KEYS */;
INSERT INTO `line_items` VALUES (8,30.50,5.6,'2022-08-01 09:26:17.000000',1,3),(9,140.45,0.7,'2022-08-05 16:30:00.000000',1,8),(10,205.30,1.54,'2022-08-28 17:47:00.000000',1,9),(11,27.90,7.5,'2022-08-07 14:21:00.000000',2,4),(12,180.00,3.12,'2022-09-04 13:56:00.000000',2,7),(13,27.50,10.2,'2022-09-01 19:14:19.000000',3,4),(14,180.00,3.12,'2022-09-04 15:03:02.000000',3,9),(16,27.50,10.1,'2022-09-18 17:56:00.000000',3,4),(17,100.70,1.3,'2022-09-18 20:01:00.000000',4,7),(18,120.45,3.23,'2022-07-25 11:09:00.000000',4,8),(19,24.27,6.87,'2022-08-24 15:07:00.000000',5,4),(20,102.00,3.125,'2022-08-26 18:05:00.000000',5,7),(21,95.67,5.2,'2022-08-21 16:02:00.000000',5,6),(22,27.82,4.95,'2022-08-30 11:52:00.000000',1,4),(28,25.65,7.4,'2022-09-01 16:31:49.963860',2,4);
/*!40000 ALTER TABLE `line_items` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `products`
--

DROP TABLE IF EXISTS `products`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `products` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `cost` decimal(8,2) NOT NULL,
  `title` varchar(255) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_8xtpej5iy2w4cte2trlvrlayy` (`title`)
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `products`
--

LOCK TABLES `products` WRITE;
/*!40000 ALTER TABLE `products` DISABLE KEYS */;
INSERT INTO `products` VALUES (3,95.45,'Бананы'),(4,25.65,'Арбуз'),(5,430.50,'Манго'),(6,87.00,'Дыня'),(7,95.35,'Слива'),(8,150.00,'Яблоки'),(9,160.90,'Груши'),(10,45.80,'Картофель'),(11,40.00,'Морковь');
/*!40000 ALTER TABLE `products` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2022-09-01 19:45:41
