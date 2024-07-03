-- MySQL dump 10.13  Distrib 8.0.36, for Win64 (x86_64)
--
-- Host: localhost    Database: payrollsystem
-- ------------------------------------------------------
-- Server version	8.4.0

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
-- Table structure for table `department`
--

DROP TABLE IF EXISTS `department`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `department` (
  `DptID` int NOT NULL,
  `DptName` varchar(50) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin DEFAULT NULL,
  `DptHead` varchar(50) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin DEFAULT NULL,
  `DptNum` int DEFAULT NULL,
  PRIMARY KEY (`DptID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 COLLATE=utf8mb3_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `department`
--

LOCK TABLES `department` WRITE;
/*!40000 ALTER TABLE `department` DISABLE KEYS */;
INSERT INTO `department` VALUES (1,'人力资源部','小吴',1),(2,'财务部','小孙',1),(3,'产品部','小王',2),(4,'技术部','小傅',2);
/*!40000 ALTER TABLE `department` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `pay`
--

DROP TABLE IF EXISTS `pay`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `pay` (
  `StaffId` int NOT NULL,
  `Name` varchar(100) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin DEFAULT NULL,
  `BasicPay` decimal(10,0) DEFAULT NULL,
  `PositionPay` decimal(10,0) DEFAULT NULL,
  `HouseAllowance` decimal(10,0) DEFAULT NULL,
  `Allowance` decimal(10,0) DEFAULT NULL,
  `UnionFee` decimal(10,0) DEFAULT NULL,
  `WEFee` decimal(10,0) DEFAULT NULL,
  `HouseFund` decimal(10,0) DEFAULT NULL,
  `Pension` decimal(10,0) DEFAULT NULL,
  `BonusPenalty` decimal(10,0) DEFAULT NULL,
  PRIMARY KEY (`StaffId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 COLLATE=utf8mb3_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `pay`
--

LOCK TABLES `pay` WRITE;
/*!40000 ALTER TABLE `pay` DISABLE KEYS */;
INSERT INTO `pay` VALUES (1,'小王',5000,500,100,100,20,900,200,100,100),(2,'小傅',5000,500,100,100,20,900,200,100,100),(3,'小孙',4000,400,100,50,20,800,200,100,0),(4,'小李',4000,400,100,50,20,800,200,100,20),(5,'小张',4000,400,100,80,20,900,200,100,50),(6,'小吴',3000,300,100,60,20,800,200,100,80);
/*!40000 ALTER TABLE `pay` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `staff`
--

DROP TABLE IF EXISTS `staff`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `staff` (
  `StaffId` int NOT NULL,
  `Name` varchar(100) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin DEFAULT NULL,
  `Gender` varchar(10) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin DEFAULT NULL,
  `BirthDate` date DEFAULT NULL,
  `JoinDate` date DEFAULT NULL,
  `DptId` int DEFAULT NULL,
  `DptName` varchar(100) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin DEFAULT NULL,
  `Position` varchar(100) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL,
  `Title` varchar(100) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL,
  `PolStatus` varchar(100) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL,
  `MarStatus` varchar(100) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL,
  PRIMARY KEY (`StaffId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 COLLATE=utf8mb3_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `staff`
--

LOCK TABLES `staff` WRITE;
/*!40000 ALTER TABLE `staff` DISABLE KEYS */;
INSERT INTO `staff` VALUES (1,'小王','女','1981-07-21','2001-09-27',3,'产品部','产品经理','高级产品经理','中国共产党员','已婚'),(2,'小傅','女','1998-06-06','2020-08-16',4,'技术部','软件开发工程师','高级软件开发工程师','中国共产党员','未婚'),(3,'小孙','女','1985-11-12','2008-06-30',2,'财务部','会计','财务经理','群众','已婚'),(4,'小李','男','1995-05-27','2019-04-08',4,'技术部','软件开发工程师','初级软件开发工程师','群众','已婚'),(5,'小张','男','1975-01-12','2000-01-28',3,'产品部','产品经理','初级产品经理','其他','离异'),(6,'小吴','女','1989-09-01','2010-08-15',1,'人力资源部','人力资源专员','人力资源经理','中国共产党员','已婚');
/*!40000 ALTER TABLE `staff` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `users` (
  `UserId` int NOT NULL,
  `UserName` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin DEFAULT NULL,
  `Password` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin DEFAULT NULL,
  `Role` int DEFAULT NULL,
  `Sex` varchar(1) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin DEFAULT NULL,
  `Phone` char(11) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin DEFAULT NULL,
  PRIMARY KEY (`UserId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 COLLATE=utf8mb3_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users`
--

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` VALUES (1,'wxr','wxr0409',1,'女','19564591969'),(2,'admin','123456',2,'男','15246598568'),(3,'小王','www111',1,'女','13195698458'),(4,'小张','zzz111',1,'男','13520109203'),(5,'小李','lll111',1,'男','13195864589'),(6,'小傅','fff111',2,'女','13164649855'),(7,'小孙','sss111',1,'女','13164679782'),(8,'小吴','555555',1,'女','13839756986');
/*!40000 ALTER TABLE `users` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2024-07-03 15:05:32
