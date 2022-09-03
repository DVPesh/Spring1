CREATE DATABASE  IF NOT EXISTS `lesson7` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `lesson7`;
-- MySQL dump 10.13  Distrib 8.0.24, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: lesson7
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
-- Table structure for table `products`
--

DROP TABLE IF EXISTS `products`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `products` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `cost` decimal(8,2) NOT NULL,
  `expiration_date` date DEFAULT NULL,
  `quantity` bigint NOT NULL,
  `supplier_email` varchar(255) DEFAULT NULL,
  `title` varchar(255) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_8xtpej5iy2w4cte2trlvrlayy` (`title`)
) ENGINE=InnoDB AUTO_INCREMENT=34 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `products`
--

LOCK TABLES `products` WRITE;
/*!40000 ALTER TABLE `products` DISABLE KEYS */;
INSERT INTO `products` VALUES (7,75.00,'2022-09-21',25,'supplier1@yandex.ru','огурцы'),(8,98.00,'2022-10-01',17,'supplier2@yandex.ru','редис'),(13,85.00,NULL,38,'supplier3@gmail.ru','мыло'),(14,15400.00,NULL,12,'','гитара'),(15,2500.00,NULL,47,'omega@mail.ru','наушники'),(16,150.00,'2022-09-30',80,'supplier1@yandex.ru','помидоры'),(17,435.00,'2022-10-01',78,'supplier3@gmail.ru','морская капуста'),(18,110.00,'2022-10-05',45,'supplier1@yandex.ru','слива'),(19,355.00,'2022-10-02',56,'oyster@yandex.ru','устрицы'),(20,312.00,'2022-09-17',12,'chicken@gmail.com','курица'),(21,85.00,'2022-09-09',16,'supplier1@yandex.ru','бананы'),(22,1100.00,'2022-10-01',11,'fish@yandex.ru','форель'),(23,45.00,'2022-09-19',56,'supplier3@gmail.ru','укроп'),(24,830.00,'2022-09-19',23,'chicken@gmail.com','говядина'),(25,39700.00,NULL,2,'supplier3@gmail.ru','лодка надувная'),(26,350.00,NULL,25,'chicken@gmail.com','пластиковый контейнер'),(27,78.00,NULL,54,'supplier1@yandex.ru','зубочистки'),(28,289.00,'2022-12-09',36,'supplier2@yandex.ru','конфеты'),(29,250.00,'2022-11-05',41,'supplier3@gmail.ru','печенье'),(30,4206.00,NULL,6,'chicken@gmail.com','чайник'),(33,43.00,'2022-09-07',29,'supplier3@gmail.ru','хлеб');
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

-- Dump completed on 2022-09-04  1:41:10
