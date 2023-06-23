-- MySQL dump 10.13  Distrib 8.0.31, for Win64 (x86_64)
--
-- Host: localhost    Database: world_of_zuul
-- ------------------------------------------------------
-- Server version	8.0.31

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
-- Table structure for table `game_cache`
--

DROP TABLE IF EXISTS `game_cache`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `game_cache` (
  `id` varchar(50) NOT NULL,
  `player_name` varchar(50) NOT NULL,
  `scene_file` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `game_cache`
--

LOCK TABLES `game_cache` WRITE;
/*!40000 ALTER TABLE `game_cache` DISABLE KEYS */;
INSERT INTO `game_cache` VALUES ('0a8fc3ae918943f9bc1364e67a838515','Wu','Gruppe 6 Semester Projekt/files/gamefile/SaveFileWu.txt'),('1051511ac73a4c998e994591f4281528','Wu','Gruppe 6 Semester Projekt/files/gamefile/SaveFileWu.txt'),('17b99d1b3be14e55ba380584d1fced28','Wuaoni','Gruppe 6 Semester Projekt/files/gamefile/SaveFileWuaoni.txt'),('2476f8372a7b4e0b81b710101a3df22d','Wuaoni','Gruppe 6 Semester Projekt/files/gamefile/SaveFileWuaoni.txt'),('2c2365e60d55439faf8437b242ac4830','Yuan','Gruppe 6 Semester Projekt/files/gamefile/SaveFileYuan.txt'),('2e2e9d15c9614f8e80f9309ba08fa516','Wu','Gruppe 6 Semester Projekt/files/gamefile/SaveFileWu.txt'),('2f5db789cd6e4474b5432083bf507687','Mads','Gruppe 6 Semester Projekt/files/gamefile/SaveFileMads.txt'),('3a8aaa5ce00c48d2a0deb775e0ddcf82','Wu','Gruppe 6 Semester Projekt/files/gamefile/SaveFileWu.txt'),('3f1a967128ba4568bb17eb240927de0f','Yuan','Gruppe 6 Semester Projekt/files/gamefile/SaveFileYuan.txt'),('4252afca7f2744fc986eb22f367fe5e3','Mads','Gruppe 6 Semester Projekt/files/gamefile/SaveFileMads.txt'),('4787172b22e84688859cb192e5fe2c22','Mads','Gruppe 6 Semester Projekt/files/gamefile/SaveFileMads.txt'),('4808a02d82564ac7949d14f204989fd9','Yuan','Gruppe 6 Semester Projekt/files/gamefile/SaveFileYuan.txt'),('529a3c0bb3904906bdb87f729c546dd7','Mads','Gruppe 6 Semester Projekt/files/gamefile/SaveFileMads.txt'),('6515578aeb7a44279a7f2c8c90ee345b','Yuan','Gruppe 6 Semester Projekt/files/gamefile/SaveFileYuan.txt'),('659ed6d5777d4c8ca90e3d696cdd0722','wans','Gruppe 6 Semester Projekt/files/gamefile/SaveFilewans.txt'),('7ad1fab66b02405184559a6bf6635567','Wu','Gruppe 6 Semester Projekt/files/gamefile/SaveFileWu.txt'),('7b7b655a039a47d79c06122df302153b','Mads','Gruppe 6 Semester Projekt/files/gamefile/SaveFileMads.txt'),('8624cf7a6ecd4f8bb814e792669c8df5','Wu','Gruppe 6 Semester Projekt/files/gamefile/SaveFileWu.txt'),('9acf63db7f684276869c871b473e817d','Wu','Gruppe 6 Semester Projekt/files/gamefile/SaveFileWu.txt'),('9b89b5c1881f4de8bbe8d2f3c2ae0b2a','Wu','Gruppe 6 Semester Projekt/files/gamefile/SaveFileWu.txt'),('b3cbb7af2c394bfdb46e56e73b767b8a','Wuaoni','Gruppe 6 Semester Projekt/files/gamefile/SaveFileWuaoni.txt'),('b6565ad4b4f6461682dc5f256d955eb1','Wu','Gruppe 6 Semester Projekt/files/gamefile/SaveFileWu.txt'),('bdf6beae92a44048acc985dd7bc435af','Yuan','Gruppe 6 Semester Projekt/files/gamefile/SaveFileYuan.txt'),('c7024c564a3a4c88b533681c68411e5d','Wuaoni','Gruppe 6 Semester Projekt/files/gamefile/SaveFileWuaoni.txt'),('d048797a5ec24e419463d35bc0111ea0','Mads','Gruppe 6 Semester Projekt/files/gamefile/SaveFileMads.txt'),('d44a8313ea2b45b3b5c53eb2c3d53406','wans','Gruppe 6 Semester Projekt/files/gamefile/SaveFilewans.txt'),('dcaf84ee1cdb41f3820f87b4cfc50ff1','Wu','Gruppe 6 Semester Projekt/files/gamefile/SaveFileWu.txt'),('dcf7cae1fb724c81af0d964561335a66','Wu','Gruppe 6 Semester Projekt/files/gamefile/SaveFileWu.txt'),('e25fc403e72840e6b2fbeb8e4d835dff','Yuan','Gruppe 6 Semester Projekt/files/gamefile/SaveFileYuan.txt'),('f2e092bdaab149d7bee567bbd265cd5c','Wu','Gruppe 6 Semester Projekt/files/gamefile/SaveFileWu.txt');
/*!40000 ALTER TABLE `game_cache` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2023-06-23 13:44:36
