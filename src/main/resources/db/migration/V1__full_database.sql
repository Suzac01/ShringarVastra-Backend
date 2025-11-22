-- MySQL dump 10.13  Distrib 8.0.41, for Win64 (x86_64)
--
-- Host: localhost    Database: amees
-- ------------------------------------------------------
-- Server version	8.0.41

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `cart_items`
--

DROP TABLE IF EXISTS `cart_items`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `cart_items` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `image_url` varchar(255) DEFAULT NULL,
  `ordered` bit(1) DEFAULT NULL,
  `ordered_at` datetime(6) DEFAULT NULL,
  `price` double NOT NULL,
  `product_name` varchar(255) DEFAULT NULL,
  `quantity` int NOT NULL,
  `product_id` bigint NOT NULL,
  `user_id` bigint NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK1re40cjegsfvw58xrkdp6bac6` (`product_id`),
  KEY `FK709eickf3kc0dujx3ub9i7btf` (`user_id`),
  CONSTRAINT `FK1re40cjegsfvw58xrkdp6bac6` FOREIGN KEY (`product_id`) REFERENCES `products` (`id`),
  CONSTRAINT `FK709eickf3kc0dujx3ub9i7btf` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cart_items`
--

LOCK TABLES `cart_items` WRITE;
/*!40000 ALTER TABLE `cart_items` DISABLE KEYS */;
INSERT INTO `cart_items` VALUES (14,'dress7.png',_binary '\0',NULL,4799,'Chanderi Suit Set',4,7,75),(15,'dress7.png',_binary '\0',NULL,4799,'Chanderi Suit Set',1,7,75);
/*!40000 ALTER TABLE `cart_items` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `categories`
--

DROP TABLE IF EXISTS `categories`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `categories` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `name` varchar(100) NOT NULL,
  `category_name` varchar(255) NOT NULL,
  `description` varchar(500) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `categories`
--

LOCK TABLES `categories` WRITE;
/*!40000 ALTER TABLE `categories` DISABLE KEYS */;
INSERT INTO `categories` VALUES (1,'Kurtha','',NULL),(2,'Saree','',NULL),(3,'Winter Wear','',NULL);
/*!40000 ALTER TABLE `categories` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `flyway_schema_history`
--

DROP TABLE IF EXISTS `flyway_schema_history`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `flyway_schema_history` (
  `installed_rank` int NOT NULL,
  `version` varchar(50) DEFAULT NULL,
  `description` varchar(200) NOT NULL,
  `type` varchar(20) NOT NULL,
  `script` varchar(1000) NOT NULL,
  `checksum` int DEFAULT NULL,
  `installed_by` varchar(100) NOT NULL,
  `installed_on` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `execution_time` int NOT NULL,
  `success` tinyint(1) NOT NULL,
  PRIMARY KEY (`installed_rank`),
  KEY `flyway_schema_history_s_idx` (`success`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `flyway_schema_history`
--

LOCK TABLES `flyway_schema_history` WRITE;
/*!40000 ALTER TABLE `flyway_schema_history` DISABLE KEYS */;
/*!40000 ALTER TABLE `flyway_schema_history` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `materials`
--

DROP TABLE IF EXISTS `materials`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `materials` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `name` varchar(100) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `name` (`name`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `materials`
--

LOCK TABLES `materials` WRITE;
/*!40000 ALTER TABLE `materials` DISABLE KEYS */;
INSERT INTO `materials` VALUES (1,'Cotton'),(4,'Linen'),(3,'Polyester'),(2,'Silk'),(5,'Wool');
/*!40000 ALTER TABLE `materials` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `order_items`
--

DROP TABLE IF EXISTS `order_items`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `order_items` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `price` double NOT NULL,
  `product_id` bigint DEFAULT NULL,
  `product_name` varchar(255) DEFAULT NULL,
  `quantity` int NOT NULL,
  `order_id` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKbioxgbv59vetrxe0ejfubep1w` (`order_id`),
  CONSTRAINT `FKbioxgbv59vetrxe0ejfubep1w` FOREIGN KEY (`order_id`) REFERENCES `orders` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `order_items`
--

LOCK TABLES `order_items` WRITE;
/*!40000 ALTER TABLE `order_items` DISABLE KEYS */;
/*!40000 ALTER TABLE `order_items` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `orders`
--

DROP TABLE IF EXISTS `orders`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `orders` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `client_email` varchar(255) DEFAULT NULL,
  `order_date` datetime(6) DEFAULT NULL,
  `shipping_address` varchar(255) DEFAULT NULL,
  `status` varchar(255) DEFAULT NULL,
  `additional_note` varchar(255) DEFAULT NULL,
  `payment_method` varchar(255) DEFAULT NULL,
  `set_created_at` varchar(255) DEFAULT NULL,
  `user` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `orders`
--

LOCK TABLES `orders` WRITE;
/*!40000 ALTER TABLE `orders` DISABLE KEYS */;
INSERT INTO `orders` VALUES (1,NULL,NULL,'123 Main Street, Kathmandu','PENDING','Please deliver between 9 AM and 12 PM','Cash on Delivery',NULL,'com.example.demo.model.User@6ce129cb');
/*!40000 ALTER TABLE `orders` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `product_materials`
--

DROP TABLE IF EXISTS `product_materials`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `product_materials` (
  `product_id` bigint NOT NULL,
  `material_id` bigint NOT NULL,
  PRIMARY KEY (`product_id`,`material_id`),
  KEY `material_id` (`material_id`),
  CONSTRAINT `product_materials_ibfk_1` FOREIGN KEY (`product_id`) REFERENCES `products` (`id`) ON DELETE CASCADE,
  CONSTRAINT `product_materials_ibfk_2` FOREIGN KEY (`material_id`) REFERENCES `materials` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `product_materials`
--

LOCK TABLES `product_materials` WRITE;
/*!40000 ALTER TABLE `product_materials` DISABLE KEYS */;
/*!40000 ALTER TABLE `product_materials` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `product_sizes`
--

DROP TABLE IF EXISTS `product_sizes`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `product_sizes` (
  `product_id` bigint NOT NULL,
  `size_id` bigint NOT NULL,
  PRIMARY KEY (`product_id`,`size_id`),
  KEY `size_id` (`size_id`),
  CONSTRAINT `product_sizes_ibfk_1` FOREIGN KEY (`product_id`) REFERENCES `products` (`id`),
  CONSTRAINT `product_sizes_ibfk_2` FOREIGN KEY (`size_id`) REFERENCES `sizes` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `product_sizes`
--

LOCK TABLES `product_sizes` WRITE;
/*!40000 ALTER TABLE `product_sizes` DISABLE KEYS */;
/*!40000 ALTER TABLE `product_sizes` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `product_tags`
--

DROP TABLE IF EXISTS `product_tags`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `product_tags` (
  `product_id` bigint NOT NULL,
  `tag` varchar(255) DEFAULT NULL,
  KEY `FK5rk6s19k3risy7q7wqdr41uss` (`product_id`),
  CONSTRAINT `FK5rk6s19k3risy7q7wqdr41uss` FOREIGN KEY (`product_id`) REFERENCES `products` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `product_tags`
--

LOCK TABLES `product_tags` WRITE;
/*!40000 ALTER TABLE `product_tags` DISABLE KEYS */;
INSERT INTO `product_tags` VALUES (13,'SAREE'),(13,'BLOUSE'),(13,'DESIGNER'),(13,'SILK'),(14,'TAGS'),(15,'NEWNEW'),(16,'NEWNEW'),(17,'ghabhd'),(22,'NEWNEW'),(23,'NEWNEW'),(24,'NEWNEW'),(25,'NEWNEW'),(26,'NEWNEW'),(27,'SAREE'),(27,'BLOUSE'),(27,'DESIGNER'),(27,'SILK'),(28,'NEWNEW');
/*!40000 ALTER TABLE `product_tags` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `products`
--

DROP TABLE IF EXISTS `products`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `products` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `brand` varchar(255) DEFAULT NULL,
  `created_at` datetime(6) DEFAULT NULL,
  `description` varchar(1000) DEFAULT NULL,
  `dimensions` varchar(255) DEFAULT NULL,
  `discount` double DEFAULT NULL,
  `is_featured` bit(1) DEFAULT NULL,
  `product_image` varchar(255) DEFAULT NULL,
  `product_name` varchar(255) NOT NULL,
  `product_price` double NOT NULL,
  `rating` double DEFAULT NULL,
  `reviews_count` int DEFAULT NULL,
  `sku` varchar(100) DEFAULT NULL,
  `status` varchar(50) DEFAULT NULL,
  `stock_quantity` int DEFAULT NULL,
  `updated_at` datetime(6) DEFAULT NULL,
  `weight` double DEFAULT NULL,
  `category_id` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_category` (`category_id`),
  CONSTRAINT `fk_category` FOREIGN KEY (`category_id`) REFERENCES `categories` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=29 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `products`
--

LOCK TABLES `products` WRITE;
/*!40000 ALTER TABLE `products` DISABLE KEYS */;
INSERT INTO `products` VALUES (1,'FabIndia','2025-11-04 11:33:20.000000','Elegant cotton kurta with traditional embroidery for festive occasions.','M',10,_binary '','dress1.png','Elegant Kurta',2499.99,4.5,124,'SKU001','Available',30,'2025-11-04 11:33:20.000000',0.35,1),(2,'Biba','2025-11-04 11:33:20.000000','Beautiful silk saree with zari border and matching blouse piece.','Free',15,_binary '','dress2.png','Silk Saree',3599,4.8,212,'SKU002','Available',25,'2025-11-04 11:33:20.000000',0.75,2),(3,'H&M','2025-11-04 11:33:20.000000','Warm woolen sweater perfect for winter. Soft and comfortable.','L',5,_binary '\0','dress3.png','Woolen Sweater',3299,4.4,178,'SKU003','Available',20,'2025-11-04 11:33:20.000000',0.6,3),(4,'W for Woman','2025-11-04 11:33:20.000000','Stylish designer kurtha set with dupatta. Perfect for parties.','XL',12,_binary '','dress4.png','Designer Kurtha Set',2999,4.6,189,'SKU004','Available',40,'2025-11-04 11:33:20.000000',0.55,1),(5,'Aurelia','2025-11-04 11:33:20.000000','Classic kurtha with minimal prints and cotton fabric for daily wear.','M',8,_binary '\0','dress5.png','Classic Kurtha',1699,4.2,97,'SKU005','Available',60,'2025-11-04 11:33:20.000000',0.4,1),(6,'Manyavar','2025-11-04 11:33:20.000000','Wedding saree with heavy embroidery and premium silk fabric.','Free',20,_binary '','dress6.png','Wedding Saree',4999,4.9,300,'SKU006','Available',15,'2025-11-04 11:33:20.000000',0.85,2),(7,'Libas','2025-11-04 11:33:20.000000','Magenta Chanderi round neck complete suit set for women with gota patti work.','L',10,_binary '','dress7.png','Chanderi Suit Set',4799,4.7,254,'SKU007','Available',35,'2025-11-04 11:33:20.000000',0.5,1),(8,'Global Desi','2025-11-04 11:33:20.000000','Casual kurtha with floral print and side pocket, perfect for summer.','S',5,_binary '\0','dress8.png','Casual Kurtha',1899,4.3,88,'SKU008','Available',45,'2025-11-04 11:33:20.000000',0.42,1),(9,'W for Woman','2025-11-04 11:33:20.000000','Elegant chiffon saree with gold print. Lightweight and easy to drape.','Free',18,_binary '','dress9.png','Chiffon Saree',2799,4.6,132,'SKU009','Available',28,'2025-11-04 11:33:20.000000',0.68,2),(10,NULL,'2025-11-04 11:47:21.651166',NULL,NULL,NULL,_binary '\0',NULL,'new waposdpoasmd',1200,NULL,NULL,NULL,NULL,NULL,'2025-11-04 11:47:21.651166',NULL,NULL),(11,NULL,'2025-11-04 18:38:30.472268',NULL,NULL,NULL,_binary '\0',NULL,'pop',1000,NULL,NULL,NULL,NULL,NULL,'2025-11-04 18:38:30.472268',NULL,NULL),(12,NULL,'2025-11-04 18:39:28.689062',NULL,NULL,NULL,_binary '\0',NULL,'pop',1000,NULL,NULL,NULL,NULL,NULL,'2025-11-04 18:39:28.689062',NULL,NULL),(13,NULL,'2025-11-04 21:18:18.655911','<p>again new new 	</p>',NULL,NULL,_binary '\0',NULL,'New product ',200,0.5,2,'456','ACTIVE',NULL,'2025-11-04 21:18:18.655911',0,NULL),(14,NULL,'2025-11-04 23:48:40.398717','<p>zxczxczxc</p>',NULL,NULL,_binary '\0',NULL,'asdasdasd',1000,5,12,'445','ACTIVE',NULL,'2025-11-04 23:48:40.399717',1.5,NULL),(15,NULL,'2025-11-07 21:09:18.748480','<p>zxczxcxzc</p>',NULL,NULL,_binary '\0',NULL,'asdasd',4100,1.5,12,'1125','ACTIVE',0,'2025-11-07 21:09:18.748480',52,NULL),(16,NULL,'2025-11-07 21:09:22.742647','<p><br></p>',NULL,NULL,_binary '\0',NULL,'asdasd',4100,1.5,12,'1125','ACTIVE',0,'2025-11-07 21:09:22.742647',52,NULL),(17,NULL,'2025-11-07 21:10:58.848313','<p>asdasdasd</p>',NULL,NULL,_binary '\0',NULL,'h',1000,1.5,12,'456','ACTIVE',0,'2025-11-07 21:10:58.848313',1.5,NULL),(18,NULL,'2025-11-07 21:40:28.048998','',NULL,NULL,_binary '\0',NULL,'',0,0,0,'','ACTIVE',0,'2025-11-07 21:40:28.048998',0,NULL),(19,NULL,'2025-11-07 21:41:32.539507','',NULL,NULL,_binary '\0',NULL,'shittttt',0,0,0,'','ACTIVE',0,'2025-11-07 21:41:32.539507',0,NULL),(20,NULL,'2025-11-07 21:59:04.116376','',NULL,NULL,_binary '\0',NULL,'adasd',0,0,0,'','ACTIVE',0,'2025-11-07 21:59:04.118411',0,NULL),(21,NULL,'2025-11-07 22:10:20.554368','',NULL,NULL,_binary '\0',NULL,'',0,0,0,'','ACTIVE',0,'2025-11-07 22:10:20.554368',0,NULL),(22,NULL,'2025-11-08 00:00:52.196550','<p>asdasdsad</p>',NULL,NULL,_binary '\0',NULL,'I AM NULL',100,0,0,'','ACTIVE',0,'2025-11-08 00:00:52.196550',0,NULL),(23,NULL,'2025-11-18 16:14:48.587873','<p>ZXCZxZxX</p>',NULL,NULL,_binary '\0',NULL,'dsaS',500,2,12,'1125','ACTIVE',0,'2025-11-18 16:14:48.587873',1.2,NULL),(24,NULL,'2025-11-18 16:26:59.118953','<p>asdsadasd</p>',NULL,NULL,_binary '\0',NULL,'asdsad',1000,2.5,12,'448','ACTIVE',0,'2025-11-18 16:26:59.118953',500,NULL),(25,NULL,'2025-11-18 16:27:49.186656','<p>cvbcvbcvbcvb</p>',NULL,NULL,_binary '\0',NULL,'asdsad',1000,2.5,12,'448','ACTIVE',0,'2025-11-18 16:27:49.186656',500,NULL),(26,NULL,'2025-11-18 16:29:13.326754','<p>zxczxczxc</p>',NULL,NULL,_binary '\0',NULL,'asdsad',1000,2.5,12,'448','ACTIVE',0,'2025-11-18 16:29:13.326754',500,NULL),(27,NULL,'2025-11-18 17:23:33.547541','<p>zdcvdfgdfg</p>',NULL,NULL,_binary '\0',NULL,'asdzxczxc',1000,2,12,'12','ACTIVE',0,'2025-11-18 17:23:33.547541',1.2,NULL),(28,NULL,'2025-11-18 17:32:47.434488','<p>xcvbvjhfghfgh</p>',NULL,NULL,_binary '\0',NULL,'asdasdzxcxzczxc',4000,5,5,'56','ACTIVE',0,'2025-11-18 17:32:47.434488',12,NULL);
/*!40000 ALTER TABLE `products` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sizes`
--

DROP TABLE IF EXISTS `sizes`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `sizes` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `size_label` varchar(10) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sizes`
--

LOCK TABLES `sizes` WRITE;
/*!40000 ALTER TABLE `sizes` DISABLE KEYS */;
INSERT INTO `sizes` VALUES (1,'S'),(2,'M'),(3,'L'),(4,'XL');
/*!40000 ALTER TABLE `sizes` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `users` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `email` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `role` varchar(255) DEFAULT NULL,
  `appwrite_id` varchar(255) DEFAULT NULL,
  `provider` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK73cqrcich7hge8lo2xta30axr` (`appwrite_id`),
  UNIQUE KEY `unique_email` (`email`)
) ENGINE=InnoDB AUTO_INCREMENT=80 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users`
--

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` VALUES (1,'alice@example.com','Alice','password123','ROLE_ADMIN',NULL,NULL),(2,'bob@example.com','Bob','mypassword','ROLE_CLIENT',NULL,NULL),(3,'charlie@example.com','Charlie','charliepass','ROLE_CLIENT',NULL,NULL),(4,'john@example.com','John Doe','secret123','ROLE_CLIENT',NULL,NULL),(5,'newuser@example.com','New User','newpassword123','ROLE_CLIENT',NULL,NULL),(7,'helloworld@example.com','New User','newpasswor','ROLE_ADMIN',NULL,NULL),(11,'asdsad@example.com','nonasd','password123',NULL,NULL,NULL),(41,'thenew@ad.com','John Doace','securasdasd','ROLE_CLIENT',NULL,NULL),(43,'aoka@example.com','Admin Uasdasdser','securePassword123','ROLE_ADMIN',NULL,NULL),(45,'rbruru@example.com','newasdn','myvvvvword123','ROLE_ADMIN',NULL,NULL),(46,'ru@example.com','ndn','llv123','ROLE_CLIENT',NULL,NULL),(47,'mMEexample.com','ndn','llv123','ROLE_CLIENT',NULL,NULL),(48,'Hello@gmail.com',NULL,'ahvbdkllas.d','ROLE_CLIENT',NULL,NULL),(49,'newHello@gmail.com','null null','kjasbsd','ROLE_CLIENT',NULL,NULL),(50,'newmew@gmail.com','null null','newmew','ROLE_CLIENT',NULL,NULL),(53,'okayasdasd@gmail.com','okayo okk','newbhjcad','ROLE_CLIENT',NULL,NULL),(54,'newgame@gmail.com','gamegame newgame','ashdbiopouwer','ROLE_CLIENT',NULL,NULL),(58,'kaushal@gmail.com','kaushal pandey','hello','ROLE_CLIENT',NULL,NULL),(59,'ram@gmail.com','ram sharma','ramram','ROLE_CLIENT',NULL,NULL),(60,'umesh@gmail.com','umesh khatri','umesh','ROLE_CLIENT',NULL,NULL),(61,'okay@gmail.com','okayokay okay','okay','ROLE_CLIENT',NULL,NULL),(62,'kkk@gmail.com','hello newnew','kkk','ROLE_CLIENT',NULL,NULL),(63,'again@gmail.com','again again','again','ROLE_CLIENT',NULL,NULL),(64,'myid@gmail.com','myid myid','$2a$10$ebaASSMjbR06X7N1zbMPqeVgcTFsbVgvXuzi.rO1gBERtG/O48uXy','ROLE_CLIENT',NULL,NULL),(65,'newpeople@gmail.com','newpeople people','$2a$10$iMJxvZFpIRs6fuVR0Ors0emXn22ylFk1BRYL2M3yJ2fwes1y8Bc06','ROLE_CLIENT',NULL,NULL),(66,'h@gmailcom','hhhhh hhh','$2a$10$uVIdlIsECLb6RGVYOaMvge0aFOO4vlittSV3LKsVTsLgECvmbKBKa','ROLE_CLIENT',NULL,NULL),(67,'let@gmaiil.com','letme me','$2a$10$PYWc1II9x1.vjdV7ZTMB2e5gdgZ1CC0Thx4qtd6RLH3QbDVoD3noi','ROLE_CLIENT',NULL,NULL),(68,'aa@gmail.com','aa aa','$2a$10$BVe4R42uIlzYNMrKjleUJeFKhh01J33hOnHVXfoig/qcxiInhWXxq','ROLE_CLIENT',NULL,NULL),(69,'iii@gmail.com','idk idk','$2a$10$8y/EohoJ/hTsfcKjgZk/H.ZY4W7P5Vyz0IF1d113nnbKFoF3wFRdi','ROLE_CLIENT',NULL,NULL),(70,'newasd@gmail.com','newasd newasd','$2a$10$MSABBAtKM3qg6/t825vDwuGuVsnkERsnMSA9SixQFNIEzitrrD2nS','ROLE_CLIENT',NULL,NULL),(71,'business@vastra.com','Gorkhaaa asd','$2a$10$N7OP0YZB/daALDnfV4.qP.rVgEUw0ennY0YsVvx6aE/lYEaA/P1r.','ROLE_CLIENT',NULL,NULL),(72,'rom@gmail.com','rom bhan','$2a$10$CarZ3NgOm9olJU6ysThQGOiQhR423.7sqy8A.QrJy6UFmgx3TxgD.','ROLE_CLIENT',NULL,NULL),(75,'new@yy.com','new@yy.com popup','$2a$10$B2etOzB/Yqv.MJNj9zri9uVfOwvjVO8Om/e7Dz//PWWQVi3EuuZG6','ROLE_CLIENT',NULL,NULL),(76,'jjj@gmail.com','asjhdb jhuja','$2a$10$NOQHDaIbQUpFa4sxs6LEw..91tntDlGary23LH4sh5VFRBwwJbAgi','ROLE_CLIENT',NULL,NULL),(77,'okay','hello okay','$2a$10$QVzISis7q.JBoDbOANPlguvXXCsytn9lXyBI2zFxPJHPKRntzkVU2','ROLE_CLIENT',NULL,NULL),(78,'iamnew','imanew asd','$2a$10$q7zcemf3ufl9ySaaAPKchu20z1jJjKsVnEDLpH3XDpPH/fusKA14q','ROLE_CLIENT',NULL,NULL),(79,'raman@gmail.com','raman pppp','$2a$10$a2nEWQz7welKRrQw9jAlPONEjfXAFtCsga8SkVEJQN7lkMjWEKEFG','ROLE_CLIENT',NULL,NULL);
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

-- Dump completed on 2025-11-22 22:06:14
