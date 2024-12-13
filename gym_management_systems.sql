-- MySQL dump 10.13  Distrib 8.0.38, for Win64 (x86_64)
--
-- Host: localhost    Database: gym_management_system
-- ------------------------------------------------------
-- Server version	9.0.1
use gym_management_system;
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
-- Table structure for table `bill`
--

DROP TABLE IF EXISTS `bill`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `bill` (
  `ID` int NOT NULL AUTO_INCREMENT,
  `ID_customer` int NOT NULL,
  `ID_staff` int NOT NULL,
  `create_date` datetime NOT NULL,
  `total_amount` double NOT NULL,
  `payment_method` varchar(128) NOT NULL,
  `status` varchar(45) NOT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `bill`
--

LOCK TABLES `bill` WRITE;
/*!40000 ALTER TABLE `bill` DISABLE KEYS */;
INSERT INTO `bill` VALUES (1,1,19,'2024-12-01 10:00:00',450,'Credit Card','Completed'),(2,2,10,'2024-12-02 14:30:00',900,'Cash','Pending'),(3,3,12,'2024-12-03 08:15:00',850,'Bank Transfer','Completed'),(4,4,11,'2024-12-04 11:45:00',760,'Debit Card','Cancelled'),(5,5,13,'2024-12-05 09:00:00',250,'Credit Card','Completed'),(6,1,14,'2024-12-06 16:30:00',550,'PayPal','Pending'),(7,2,19,'2024-12-07 07:30:00',350,'Cash','Completed'),(8,3,10,'2024-12-08 12:00:00',740,'Bank Transfer','Cancelled'),(9,6,12,'2024-12-09 13:45:00',770,'Debit Card','Completed'),(10,7,11,'2024-12-10 10:15:00',120,'Credit Card','Pending');
/*!40000 ALTER TABLE `bill` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `calendar`
--

DROP TABLE IF EXISTS `calendar`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `calendar` (
  `date` date NOT NULL,
  `session_one` int DEFAULT NULL,
  `session_two` int DEFAULT NULL,
  `session_three` int DEFAULT NULL,
  `session_four` int DEFAULT NULL,
  `session_five` int DEFAULT NULL,
  `session_six` int DEFAULT NULL,
  `session_seven` int DEFAULT NULL,
  `session_eight` int DEFAULT NULL,
  `session_nine` int DEFAULT NULL,
  `session_ten` int DEFAULT NULL,
  PRIMARY KEY (`date`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `calendar`
--

LOCK TABLES `calendar` WRITE;
/*!40000 ALTER TABLE `calendar` DISABLE KEYS */;
/*!40000 ALTER TABLE `calendar` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `customer`
--

DROP TABLE IF EXISTS `customer`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `customer` (
  `ID` int NOT NULL AUTO_INCREMENT,
  `full_name` varchar(512) NOT NULL,
  `age` int NOT NULL,
  `sex` varchar(8) NOT NULL,
  `phonenumber` varchar(16) NOT NULL,
  `email` varchar(64) DEFAULT NULL,
  `date_of_birth` date DEFAULT NULL,
  `address` varchar(255) NOT NULL,
  `create_date` date NOT NULL,
  `category` int NOT NULL,
  PRIMARY KEY (`ID`),
  UNIQUE KEY `phonenumber_UNIQUE` (`phonenumber`),
  UNIQUE KEY `email_UNIQUE` (`email`)
) ENGINE=InnoDB AUTO_INCREMENT=20 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `customer`
--

LOCK TABLES `customer` WRITE;
/*!40000 ALTER TABLE `customer` DISABLE KEYS */;
INSERT INTO `customer` VALUES (1,'Anthony Edward Stark',54,'Nam','0987654321','tonystark@gmail.com','1970-05-29',' 10880 Malibu Point, Malibu, California, Hoa Kỳ','2024-12-01',5),(2,'Peter Parker',24,'Nam','0978654321','peterparker@gmail.com','2000-08-20','20 Ingram Street, Forest Hills, Queens, New York','2024-12-01',2),(3,'Steve Rogers',30,'Nam','0987456321','steve@gmail.com','1994-07-04','Brooklyn, New York, Hoa Kỳ','2024-12-01',3),(4,'Clint Barton',53,'Nam','0331265478','clintbarrton@gmail.com','1971-06-07','Missouri, Hoa Kỳ','2024-12-01',5),(5,'Natasha Romanoff',40,'Nữ','0156324789','natasha@gmail.com','1984-11-22','23rd Street, Manhattan, New York, Hoa Kỳ','2024-12-01',4),(6,'Stephen Strange',45,'Nam','0564231751','drstephen@gmail.com','1989-11-11','Sanctum Sanctorum, 177A Bleecker Street, Greenwich Village, Manhattan, New York, Hoa Kỳ','2024-12-01',2),(7,'Wanda Maximoff',30,'Nữ','0987836542','wanda@gmail.com','1994-01-01','Westview, New Jersey','2024-12-01',1),(8,'Vũ Đình Cường',20,'Nam','0987836962','vudinhcuong8404@gmail.com','2024-12-07','Quỳnh Hội, Quỳnh Phụ','2024-12-07',1),(13,'VU DINH CUONG',20,'Nam','0986175856','cuongdaynemano@gmail.com','2024-12-10','Quỳnh Hội, Quỳnh Phụ','2024-12-07',2);
/*!40000 ALTER TABLE `customer` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `customer_login`
--

DROP TABLE IF EXISTS `customer_login`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `customer_login` (
  `ID_customer` int NOT NULL,
  `usename` varchar(256) NOT NULL,
  `password` varchar(256) NOT NULL,
  PRIMARY KEY (`ID_customer`),
  UNIQUE KEY `usename_UNIQUE` (`usename`),
  CONSTRAINT `FK_customer_login` FOREIGN KEY (`ID_customer`) REFERENCES `customer` (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `customer_login`
--

LOCK TABLES `customer_login` WRITE;
/*!40000 ALTER TABLE `customer_login` DISABLE KEYS */;
/*!40000 ALTER TABLE `customer_login` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `customer_service`
--

DROP TABLE IF EXISTS `customer_service`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `customer_service` (
  `ID` int NOT NULL AUTO_INCREMENT,
  `ID_customer` int NOT NULL,
  `ID_service` int NOT NULL,
  `ID_bill` int NOT NULL,
  `purchase_price` double NOT NULL,
  `purchase_date` datetime NOT NULL,
  `status` varchar(45) NOT NULL,
  `remaining_sessions` int NOT NULL,
  PRIMARY KEY (`ID`),
  KEY `FK_customer_service_idx` (`ID_customer`),
  KEY `FK_service_customer_idx` (`ID_service`),
  KEY `FK_bill_service_idx` (`ID_bill`),
  CONSTRAINT `FK_bill` FOREIGN KEY (`ID_bill`) REFERENCES `bill` (`ID`),
  CONSTRAINT `FK_customer_service` FOREIGN KEY (`ID_customer`) REFERENCES `customer` (`ID`),
  CONSTRAINT `FK_service_customer` FOREIGN KEY (`ID_service`) REFERENCES `personal_trainer_service` (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `customer_service`
--

LOCK TABLES `customer_service` WRITE;
/*!40000 ALTER TABLE `customer_service` DISABLE KEYS */;
INSERT INTO `customer_service` VALUES (1,1,1,1,100,'2024-12-01 10:00:00','Completed',0),(2,2,3,2,250,'2024-12-01 11:30:00','Completed',0),(3,3,2,3,150,'2024-12-02 09:00:00','Incomplete',3),(4,4,4,4,400,'2024-12-02 14:30:00','Incomplete',4),(5,5,5,5,300,'2024-12-03 08:15:00','Incomplete',5),(6,6,6,9,500,'2024-12-03 17:00:00','Cancelled',4),(7,7,7,10,120,'2024-12-04 13:00:00','Completed',0),(8,1,2,6,200,'2024-12-04 16:00:00','Completed',0),(9,2,5,7,350,'2024-12-05 10:00:00','Completed',0),(10,3,6,8,450,'2024-12-05 12:30:00','Cancelled',8);
/*!40000 ALTER TABLE `customer_service` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `equipment`
--

DROP TABLE IF EXISTS `equipment`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `equipment` (
  `ID` int NOT NULL AUTO_INCREMENT,
  `name` varchar(256) NOT NULL,
  `brand` varchar(128) NOT NULL,
  `position` varchar(256) NOT NULL,
  `buy_date` date NOT NULL,
  `purchase_price` double NOT NULL,
  `warranty` int DEFAULT NULL,
  `status` varchar(128) NOT NULL,
  `quantity` int NOT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `equipment`
--

LOCK TABLES `equipment` WRITE;
/*!40000 ALTER TABLE `equipment` DISABLE KEYS */;
INSERT INTO `equipment` VALUES (1,'Treadmill X1000','Life Fitness','Khu vực Cardio','2023-01-10',2500,24,'Bảo trì',1),(2,'Elliptical Trainer E200','Precor','Khu vực Cardio','2022-11-05',1800,18,'Hoạt động',2),(3,'Spin Bike S300','Schwinn','Khu vực Cardio','2023-06-20',1200,12,'Hoạt động',3),(4,'Leg Press Machine','Technogym','Khu vực Strength','2023-03-15',3200,24,'Bảo trì',4),(5,'Dumbbell Set (5-50kg)','Rogue Fitness','Khu vực Free Weights','2022-09-25',1500,36,'Hoạt động',5),(6,'Smith Machine SM500','Matrix','Khu vực Strength','2023-05-12',4000,24,'Hoạt động',6),(7,'Lat Pulldown Machine','Cybex','Khu vực Strength','2023-02-08',2700,18,'Hoạt động',7),(8,'Rowing Machine R700','Concept2','Khu vực Cardio','2022-08-10',1300,24,'Bảo trì',8),(9,'Chest Press Machine','Hammer Strength','Khu vực Strength','2023-07-18',3500,24,'Hoạt động',9),(10,'Yoga Mats Set','Lululemon','Khu vực Yoga','2023-04-05',500,12,'Hoạt động',19),(11,'Máy chạy bộ','TechFitness','Khu vực Cardio','2024-12-10',2500,12,'Hoạt động',3),(12,'Ghế đẩy ngực','BH Fitness','Khu vực Tập Sức Mạnh','2025-01-02',1800,24,'Hoạt động',2),(13,'Xe đạp tập thể dục','Johnson Health','Khu vực Cardio','2024-12-19',1500,18,'Hoạt động',4);
/*!40000 ALTER TABLE `equipment` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `item_product_bill`
--

DROP TABLE IF EXISTS `item_product_bill`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `item_product_bill` (
  `ID` int NOT NULL AUTO_INCREMENT,
  `ID_bill` int NOT NULL,
  `ID_product` int NOT NULL,
  `purchase_price` double NOT NULL,
  `quantity` int NOT NULL,
  `total` double NOT NULL,
  PRIMARY KEY (`ID`),
  KEY `FK_product_bill_idx` (`ID_product`),
  KEY `FK_bill_product_idx` (`ID_bill`),
  CONSTRAINT `FK_bill_product` FOREIGN KEY (`ID_bill`) REFERENCES `bill` (`ID`),
  CONSTRAINT `FK_product_bill` FOREIGN KEY (`ID_product`) REFERENCES `product` (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `item_product_bill`
--

LOCK TABLES `item_product_bill` WRITE;
/*!40000 ALTER TABLE `item_product_bill` DISABLE KEYS */;
INSERT INTO `item_product_bill` VALUES (1,1,1,100,2,200),(2,1,2,150,1,150),(3,2,3,200,3,600),(4,3,4,120,5,600),(5,4,5,90,4,360),(6,5,1,100,1,100),(7,6,2,150,2,300),(8,7,3,200,1,200),(9,8,4,120,2,240),(10,9,5,90,3,270);
/*!40000 ALTER TABLE `item_product_bill` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `personal_trainer`
--

DROP TABLE IF EXISTS `personal_trainer`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `personal_trainer` (
  `ID` int NOT NULL AUTO_INCREMENT,
  `ID_staff` int NOT NULL,
  `field` varchar(45) DEFAULT NULL,
  `category` int DEFAULT NULL,
  `min` int DEFAULT NULL,
  `max` int DEFAULT NULL,
  `description` longtext,
  PRIMARY KEY (`ID`),
  UNIQUE KEY `ID_staff_UNIQUE` (`ID_staff`),
  CONSTRAINT `FK_staff_PT` FOREIGN KEY (`ID_staff`) REFERENCES `staff` (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `personal_trainer`
--

LOCK TABLES `personal_trainer` WRITE;
/*!40000 ALTER TABLE `personal_trainer` DISABLE KEYS */;
INSERT INTO `personal_trainer` VALUES (1,7,'Dinh dưỡng',5,20,50,'\nHuấn luyện viên cá nhân chuyên về dinh dưỡng giúp khách hàng thiết kế chế độ ăn uống phù hợp với mục tiêu sức khỏe, kết hợp cùng kế hoạch tập luyện để đạt kết quả tối ưu.'),(2,11,'Yoga',4,25,50,'Huấn luyện viên cá nhân chuyên yoga hướng dẫn khách hàng thực hành các động tác yoga, giúp cải thiện sự linh hoạt, sức mạnh, và thư giãn tinh thần. Họ thiết kế các buổi tập phù hợp với nhu cầu và khả năng của từng người.'),(3,12,'Boxing',3,20,40,'Huấn luyện viên cá nhân chuyên boxing dạy các kỹ thuật đấm, phòng thủ và chiến lược trong boxing, giúp cải thiện sức mạnh, sự bền bỉ và kỹ năng chiến đấu của khách hàng. Họ thiết kế các buổi tập để tăng cường thể lực và cải thiện kỹ năng thi đấu.');
/*!40000 ALTER TABLE `personal_trainer` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `personal_trainer_service`
--

DROP TABLE IF EXISTS `personal_trainer_service`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `personal_trainer_service` (
  `ID` int NOT NULL AUTO_INCREMENT,
  `ID_PT` int NOT NULL,
  `ID_service` int NOT NULL,
  PRIMARY KEY (`ID`),
  KEY `FK_PT_idx` (`ID_PT`),
  KEY `FK_service_idx` (`ID_service`),
  CONSTRAINT `FK_PT` FOREIGN KEY (`ID_PT`) REFERENCES `personal_trainer` (`ID`),
  CONSTRAINT `FK_service` FOREIGN KEY (`ID_service`) REFERENCES `service` (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `personal_trainer_service`
--

LOCK TABLES `personal_trainer_service` WRITE;
/*!40000 ALTER TABLE `personal_trainer_service` DISABLE KEYS */;
INSERT INTO `personal_trainer_service` VALUES (1,1,1),(2,1,2),(3,1,5),(4,2,2),(5,2,3),(6,2,6),(7,3,4),(8,3,5),(9,3,7);
/*!40000 ALTER TABLE `personal_trainer_service` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `position_information`
--

DROP TABLE IF EXISTS `position_information`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `position_information` (
  `ID` int NOT NULL AUTO_INCREMENT,
  `role` varchar(45) NOT NULL,
  `department` varchar(256) NOT NULL,
  `basic_salary` double NOT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `position_information`
--

LOCK TABLES `position_information` WRITE;
/*!40000 ALTER TABLE `position_information` DISABLE KEYS */;
INSERT INTO `position_information` VALUES (1,'Personal Trainer','Training & Coaching',1000),(2,'Gym Manager','Management',1500),(3,'Receptionist','Front Desk',800),(4,'Maintenance Staff','Facility Management',900),(5,'Sales Consultant','Sales & Marketing',1000),(6,'Nutritionist','Health & Wellness',1200),(7,'Customer Support','Customer Service',900);
/*!40000 ALTER TABLE `position_information` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `product`
--

DROP TABLE IF EXISTS `product`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `product` (
  `ID` int NOT NULL AUTO_INCREMENT,
  `name` varchar(256) NOT NULL,
  `purchase_price` double NOT NULL,
  `sale_price` double NOT NULL,
  `quantity` int NOT NULL,
  `entry_date` date NOT NULL,
  `expiry_date` date NOT NULL,
  `description` longtext,
  `status` varchar(45) NOT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `product`
--

LOCK TABLES `product` WRITE;
/*!40000 ALTER TABLE `product` DISABLE KEYS */;
INSERT INTO `product` VALUES (1,'Vitamin C 1000mg',15,25,100,'2024-11-01','2025-11-01','Vitamin C giúp tăng cường miễn dịch và cải thiện làn da.','Bán'),(2,'Protein Whey Isolate',30,50,200,'2024-10-15','2025-10-15','Bột protein giúp tăng cường cơ bắp và phục hồi sau tập luyện.','Bán'),(3,'BCAA 5000mg',20,35,150,'2024-09-20','2025-09-20','Amino axit chuỗi nhánh giúp phục hồi cơ và ngăn ngừa mệt mỏi.','Ngừng bán'),(4,'Creatine Monohydrate',25,40,120,'2024-08-05','2025-08-05','Creatine giúp tăng sức mạnh và cải thiện hiệu suất tập luyện.','Bán'),(5,'Pre-Workout Energy',18,30,80,'2024-11-10','2025-11-10','Thực phẩm bổ sung năng lượng giúp cải thiện hiệu suất trong các bài tập cường độ cao.','Ngừng Bán'),(6,'COCA COLA',1,2,100,'2024-12-08','2025-12-08','Co hai cho gymer','Bán'),(7,'COCA COLA',1,2,1000,'2024-12-10','2024-12-30','Hại gymer','Bán');
/*!40000 ALTER TABLE `product` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `service`
--

DROP TABLE IF EXISTS `service`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `service` (
  `ID` int NOT NULL AUTO_INCREMENT,
  `name` varchar(256) NOT NULL,
  `category` varchar(256) NOT NULL,
  `sale_price` double NOT NULL,
  `numer_of_session` int NOT NULL,
  `frequency` int NOT NULL,
  `description` longtext,
  `pt_persentage` double NOT NULL,
  `gym_persentage` double NOT NULL,
  `status` varchar(45) NOT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `service`
--

LOCK TABLES `service` WRITE;
/*!40000 ALTER TABLE `service` DISABLE KEYS */;
INSERT INTO `service` VALUES (1,'Basic Fitness Package','General',150.5,12,3,'Gói tập cơ bản dành cho người mới bắt đầu với lịch tập 3 buổi/tuần.',20,80,'Ngừng hoạt động'),(2,'Weight Loss Program','Weight Loss',250,20,4,'Chương trình giảm cân chuyên sâu với huấn luyện viên cá nhân.',30,70,'Ngừng hoạt động'),(3,'Strength Building Pack','Strength',3000,25,5,'Gói tập luyện tăng cường sức mạnh với thiết bị và bài tập nâng cao.',25,75,'Hoạt động'),(4,'Muscle Gain Program','Bodybuilding',400,30,5,'Chương trình tăng cơ dành cho người muốn phát triển cơ bắp toàn diện.',35,65,''),(5,'Cardio & Endurance','Cardio',150,15,4,'Gói tập cardio cải thiện sức bền và tăng cường hệ tim mạch.',15,85,''),(6,'Premium PT Package','Personal PT',500,40,6,'Gói tập cao cấp với huấn luyện viên cá nhân theo sát mục tiêu cá nhân.',50,50,''),(7,'Yoga & Flexibility','Yoga & Flexibility',120,12,3,'Chương trình yoga tăng cường sự dẻo dai và giảm stress.',10,90,''),(10,'Platinum Personal Training','Personal Training',250,48,3,'Trải nghiệm huấn luyện viên cá nhân đẳng cấp nhất với lộ trình tập luyện được thiết kế riêng biệt, giúp bạn đạt được mục tiêu nhanh chóng và hiệu quả.',40,60,''),(11,'Platinum Personal Training','Personal Training',250,48,3,'Trải nghiệm huấn luyện viên cá nhân đẳng cấp nhất với lộ trình tập luyện được thiết kế riêng biệt, giúp bạn đạt được mục tiêu nhanh chóng và hiệu quả.',40,60,''),(12,'Platinum Personal Training','Personal Training',250,48,3,'Trải nghiệm huấn luyện viên cá nhân đẳng cấp nhất với lộ trình tập luyện được thiết kế riêng biệt, giúp bạn đạt được mục tiêu nhanh chóng và hiệu quả.',40,60,''),(13,'Platinum Personal Training','Personal Training',250,48,3,'Trải nghiệm huấn luyện viên cá nhân đẳng cấp nhất với lộ trình tập luyện được thiết kế riêng biệt, giúp bạn đạt được mục tiêu nhanh chóng và hiệu quả.',40,60,''),(14,'Platinum Personal Training','Personal Training',250,48,3,'Trải nghiệm huấn luyện viên cá nhân đẳng cấp nhất với lộ trình tập luyện được thiết kế riêng biệt, giúp bạn đạt được mục tiêu nhanh chóng và hiệu quả.',40,60,''),(15,'Platinum Personal Training','Personal Training',250,48,3,'Trải nghiệm huấn luyện viên cá nhân đẳng cấp nhất với lộ trình tập luyện được thiết kế riêng biệt, giúp bạn đạt được mục tiêu nhanh chóng và hiệu quả.',40,60,'');
/*!40000 ALTER TABLE `service` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `staff`
--

DROP TABLE IF EXISTS `staff`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `staff` (
  `ID` int NOT NULL AUTO_INCREMENT,
  `full_name` varchar(255) NOT NULL,
  `age` int NOT NULL,
  `sex` varchar(8) NOT NULL,
  `phonenumber` varchar(16) NOT NULL,
  `date_of_birth` date NOT NULL,
  `noID` varchar(16) NOT NULL,
  `email` varchar(255) NOT NULL,
  `bank_account` varchar(32) DEFAULT NULL,
  `image_url` varchar(256) NOT NULL,
  `address` varchar(512) NOT NULL,
  PRIMARY KEY (`ID`),
  UNIQUE KEY `noID_UNIQUE` (`noID`),
  UNIQUE KEY `phonenumber_UNIQUE` (`phonenumber`),
  UNIQUE KEY `email_UNIQUE` (`email`),
  UNIQUE KEY `image_url_UNIQUE` (`image_url`)
) ENGINE=InnoDB AUTO_INCREMENT=25 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `staff`
--

LOCK TABLES `staff` WRITE;
/*!40000 ALTER TABLE `staff` DISABLE KEYS */;
INSERT INTO `staff` VALUES (7,'Bruce Banner',45,'Nam','0321465789','1979-12-18','034204003291','hulk@gmail.com','012345678910','/images/staff/bruce_banner.jpg','New York, Hoa Kỳ'),(8,'Scott Lang',45,'Nam','0654123789','1979-04-17','034204001235','antman@gmail.com','015324698715','/images/staff/scott_lang.jpg','San Francisco, California, Hoa Kỳ'),(9,'Carol Danvers',40,'Nữ','0648979816','1984-07-24','034204001254','captain@gmail.com','016981494899','/images/staff/carol_danvers.jpg','Hà Nội'),(10,'Nguyễn Văn Hùng',30,'Nam','0987654321','1993-05-15','1234567890123456','hung.nguyen@gym.com','1234567890123456','/images/staff/nguyen_van_hung.jpg','123 Nguyễn Trãi, Hà Nội'),(11,'Trần Thị Mai',27,'Nữ','0976543210','1996-07-20','2345678901234567','mai.tran@gym.com','2345678901234567','/images/staff/tran_thi_mai.jpg','45 Lê Lợi, TP Hồ Chí Minh'),(12,'Lê Minh Tuấn',35,'Nam','0965432109','1988-03-10','3456789012345678','tuan.le@gym.com','3456789012345678','/images/staff/le_minh_tuan.jpg','67 Hoàng Diệu, Đà Nẵng'),(13,'Phạm Thùy Dung',29,'Nữ','0954321098','1994-12-05','4567890123456789','dung.pham@gym.com','4567890123456789','/images/staff/pham_thuy_dung.jpg','89 Nguyễn Huệ, Nha Trang'),(14,'Vũ Anh Quân',32,'Nam','0943210987','1991-11-01','5678901234567890','quan.vu@gym.com','5678901234567890','/images/staff/vu_anh_quan.jpg','101 Lý Thường Kiệt, Hải Phòng'),(20,'Tony Stark',54,'Nam','54648149','2024-12-10','54694989499','vbjshbvs@sjdvs.com','684894198498','/images/staff/1733713459622_sneakers.png','Quỳnh Hội, Quỳnh Phụ'),(21,'Vũ Đình Cường',20,'Nam','0987836962','2024-12-11','54646464646','vudinhcuong8404@gmail.com','684894198498','/images/staff/1733713529177_customer-service.png','Quỳnh Hội, Quỳnh Phụ'),(23,'Vũ Đình Cường',20,'Nam','0987836654','2024-12-12','546464641515','vudinhng8404@gmail.com','68489498','/images/staff/1733713607496_sneakers.png','Quỳnh Hội, Quỳnh Phụ'),(24,'Vũ Đình Cường',20,'Nam','09875415654','2024-12-12','5464646541645','vnhng8404@gmail.com','68489454598','/images/staff/1733713880805_instagram.png','Quỳnh Hội, Quỳnh Phụ');
/*!40000 ALTER TABLE `staff` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `staff_login`
--

DROP TABLE IF EXISTS `staff_login`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `staff_login` (
  `ID_staff` int NOT NULL,
  `username` varchar(64) NOT NULL,
  `pasword` varchar(64) NOT NULL,
  PRIMARY KEY (`ID_staff`),
  CONSTRAINT `FK_staff_login` FOREIGN KEY (`ID_staff`) REFERENCES `staff` (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `staff_login`
--

LOCK TABLES `staff_login` WRITE;
/*!40000 ALTER TABLE `staff_login` DISABLE KEYS */;
/*!40000 ALTER TABLE `staff_login` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `staff_role`
--

DROP TABLE IF EXISTS `staff_role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `staff_role` (
  `ID` int NOT NULL AUTO_INCREMENT,
  `ID_staff` int NOT NULL,
  `ID_role` int NOT NULL,
  `create_date` date NOT NULL,
  `status` varchar(45) NOT NULL,
  `note` longtext,
  PRIMARY KEY (`ID`),
  KEY `FK_staff_idx` (`ID_staff`),
  KEY `FK_role_idx` (`ID_role`),
  CONSTRAINT `FK_role` FOREIGN KEY (`ID_role`) REFERENCES `position_information` (`ID`),
  CONSTRAINT `FK_staff` FOREIGN KEY (`ID_staff`) REFERENCES `staff` (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `staff_role`
--

LOCK TABLES `staff_role` WRITE;
/*!40000 ALTER TABLE `staff_role` DISABLE KEYS */;
INSERT INTO `staff_role` VALUES (1,7,1,'2024-12-01','Active',NULL),(2,9,2,'2023-11-15','Active',NULL),(3,9,7,'2023-10-20','Unactive',NULL),(4,10,1,'2023-10-10','Unactive',NULL),(5,10,4,'2023-11-11','Active',NULL),(6,11,1,'2023-09-25','Active',NULL),(7,12,1,'2023-11-01','Active',NULL),(8,13,3,'2023-12-05','Active',NULL),(9,14,5,'2023-09-15','Active',NULL),(14,20,2,'2024-12-09','Active',NULL),(15,21,1,'2024-12-09','Active',NULL),(16,23,4,'2024-12-09','Active',NULL),(17,24,1,'2024-12-09','Active',NULL);
/*!40000 ALTER TABLE `staff_role` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2024-12-11 14:20:01
