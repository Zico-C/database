CREATE TABLE `memberlist` (
  `ID` int NOT NULL AUTO_INCREMENT,
  `account` varchar(30) NOT NULL,
  `memberName` char(10) NOT NULL,
  `password` varchar(20) NOT NULL,
  `tel` varchar(12) DEFAULT NULL,
  `address` char(100) DEFAULT NULL,
  PRIMARY KEY (`ID`,`account`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci