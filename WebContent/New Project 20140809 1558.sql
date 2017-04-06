-- MySQL Administrator dump 1.4
--
-- ------------------------------------------------------
-- Server version	5.1.50-community


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;


--
-- Create schema ins
--

CREATE DATABASE IF NOT EXISTS ins;
USE ins;

--
-- Temporary table structure for view `workuser`
--
DROP TABLE IF EXISTS `workuser`;
DROP VIEW IF EXISTS `workuser`;
CREATE TABLE `workuser` (
  `wid` int(11),
  `uid` int(11),
  `workname` varchar(50),
  `workdescription` text,
  `publishtime` datetime,
  `sharecount` int(11),
  `visitcount` int(11),
  `replycount` int(11),
  `praisecount` int(11),
  `collectcount` int(11),
  `rid` int(11),
  `category` varchar(45),
  `account` int(11),
  `username` varchar(30),
  `email` varchar(50),
  `registertime` datetime,
  `level` int(11),
  `honestylevel` int(11),
  `status` tinyint(1),
  `accstatus` int(11),
  `fanscount` int(11),
  `attentioncount` int(11),
  `mark` text,
  `photo` varchar(50)
);

--
-- Definition of table `attention`
--

DROP TABLE IF EXISTS `attention`;
CREATE TABLE `attention` (
  `aid` int(11) NOT NULL AUTO_INCREMENT,
  `uid` int(11) NOT NULL,
  `buid` int(11) NOT NULL,
  PRIMARY KEY (`aid`),
  KEY `FK_attention` (`buid`),
  KEY `FK_attentiongetter` (`uid`),
  CONSTRAINT `FK_attention` FOREIGN KEY (`buid`) REFERENCES `user` (`uid`),
  CONSTRAINT `FK_attentiongetter` FOREIGN KEY (`uid`) REFERENCES `user` (`uid`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;

--
-- Dumping data for table `attention`
--

/*!40000 ALTER TABLE `attention` DISABLE KEYS */;
INSERT INTO `attention` (`aid`,`uid`,`buid`) VALUES 
 (1,1,2),
 (2,2,1),
 (3,1,3),
 (4,3,1),
 (5,2,3),
 (6,3,2);
/*!40000 ALTER TABLE `attention` ENABLE KEYS */;


--
-- Definition of table `auction`
--

DROP TABLE IF EXISTS `auction`;
CREATE TABLE `auction` (
  `aid` int(11) NOT NULL AUTO_INCREMENT,
  `wid` int(11) NOT NULL,
  `uid` int(11) NOT NULL,
  `baseprice` decimal(8,2) NOT NULL,
  `applicationtime` datetime NOT NULL,
  `begintime` datetime NOT NULL,
  `endtime` datetime NOT NULL,
  `status` int(11) NOT NULL DEFAULT '1',
  `topprice` decimal(8,2) NOT NULL,
  `mark` text,
  PRIMARY KEY (`aid`),
  KEY `FK_auction_work` (`wid`),
  KEY `FK_seller` (`uid`),
  CONSTRAINT `FK_auction_work` FOREIGN KEY (`wid`) REFERENCES `work` (`wid`),
  CONSTRAINT `FK_seller` FOREIGN KEY (`uid`) REFERENCES `user` (`uid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `auction`
--

/*!40000 ALTER TABLE `auction` DISABLE KEYS */;
/*!40000 ALTER TABLE `auction` ENABLE KEYS */;


--
-- Definition of table `bid`
--

DROP TABLE IF EXISTS `bid`;
CREATE TABLE `bid` (
  `bid` int(11) NOT NULL AUTO_INCREMENT,
  `uid` int(11) NOT NULL,
  `auctionid` int(11) NOT NULL,
  `price` decimal(8,2) NOT NULL,
  `bidtime` datetime NOT NULL,
  PRIMARY KEY (`bid`),
  KEY `FK_bidder` (`uid`),
  KEY `FK_bids` (`auctionid`),
  CONSTRAINT `FK_bidder` FOREIGN KEY (`uid`) REFERENCES `user` (`uid`),
  CONSTRAINT `FK_bids` FOREIGN KEY (`auctionid`) REFERENCES `auction` (`aid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `bid`
--

/*!40000 ALTER TABLE `bid` DISABLE KEYS */;
/*!40000 ALTER TABLE `bid` ENABLE KEYS */;


--
-- Definition of table `collection`
--

DROP TABLE IF EXISTS `collection`;
CREATE TABLE `collection` (
  `cid` int(11) NOT NULL AUTO_INCREMENT,
  `wid` int(11) NOT NULL,
  `uid` int(11) NOT NULL,
  PRIMARY KEY (`cid`),
  KEY `FK_collector` (`uid`),
  KEY `FK_collectworks` (`wid`),
  CONSTRAINT `FK_collector` FOREIGN KEY (`uid`) REFERENCES `user` (`uid`),
  CONSTRAINT `FK_collectworks` FOREIGN KEY (`wid`) REFERENCES `work` (`wid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `collection`
--

/*!40000 ALTER TABLE `collection` DISABLE KEYS */;
/*!40000 ALTER TABLE `collection` ENABLE KEYS */;


--
-- Definition of table `comment`
--

DROP TABLE IF EXISTS `comment`;
CREATE TABLE `comment` (
  `cid` int(11) NOT NULL AUTO_INCREMENT,
  `wid` int(11) NOT NULL,
  `uid` int(11) NOT NULL,
  `content` text NOT NULL,
  `time` datetime NOT NULL,
  `replycid` int(11) NOT NULL,
  PRIMARY KEY (`cid`),
  KEY `FK_user_comments` (`uid`),
  KEY `FK_work_comments` (`wid`),
  CONSTRAINT `FK_user_comments` FOREIGN KEY (`uid`) REFERENCES `user` (`uid`),
  CONSTRAINT `FK_work_comments` FOREIGN KEY (`wid`) REFERENCES `work` (`wid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `comment`
--

/*!40000 ALTER TABLE `comment` DISABLE KEYS */;
/*!40000 ALTER TABLE `comment` ENABLE KEYS */;


--
-- Definition of table `messages`
--

DROP TABLE IF EXISTS `messages`;
CREATE TABLE `messages` (
  `mid` int(11) NOT NULL AUTO_INCREMENT,
  `sid` int(11) NOT NULL,
  `rid` int(11) NOT NULL,
  `msgcontent` text NOT NULL,
  `msgtime` datetime NOT NULL,
  `msgstatus` tinyint(1) NOT NULL,
  PRIMARY KEY (`mid`),
  KEY `FK_receiver_messages` (`sid`),
  KEY `FK_sender_messages` (`rid`),
  CONSTRAINT `FK_receiver_messages` FOREIGN KEY (`sid`) REFERENCES `user` (`uid`),
  CONSTRAINT `FK_sender_messages` FOREIGN KEY (`rid`) REFERENCES `user` (`uid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `messages`
--

/*!40000 ALTER TABLE `messages` DISABLE KEYS */;
/*!40000 ALTER TABLE `messages` ENABLE KEYS */;


--
-- Definition of table `praise`
--

DROP TABLE IF EXISTS `praise`;
CREATE TABLE `praise` (
  `pid` int(11) NOT NULL AUTO_INCREMENT,
  `uid` int(11) NOT NULL,
  `wid` int(11) NOT NULL,
  PRIMARY KEY (`pid`),
  KEY `FK_user_praise` (`uid`),
  KEY `FK_works_praise` (`wid`),
  CONSTRAINT `FK_user_praise` FOREIGN KEY (`uid`) REFERENCES `user` (`uid`),
  CONSTRAINT `FK_works_praise` FOREIGN KEY (`wid`) REFERENCES `work` (`wid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `praise`
--

/*!40000 ALTER TABLE `praise` DISABLE KEYS */;
/*!40000 ALTER TABLE `praise` ENABLE KEYS */;


--
-- Definition of table `resource`
--

DROP TABLE IF EXISTS `resource`;
CREATE TABLE `resource` (
  `rid` int(11) NOT NULL AUTO_INCREMENT,
  `type` varchar(10) NOT NULL,
  `path` varchar(100) NOT NULL,
  PRIMARY KEY (`rid`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8;

--
-- Dumping data for table `resource`
--

/*!40000 ALTER TABLE `resource` DISABLE KEYS */;
INSERT INTO `resource` (`rid`,`type`,`path`) VALUES 
 (1,'.jpg','430208458760950.jpg'),
 (2,'.jpg','432669957246715.jpg'),
 (3,'.jpg','434579236161999.jpg'),
 (4,'.jpg','434711891186481.jpg'),
 (5,'.jpg','435243195563990.jpg'),
 (6,'.jpg','436279955390304.jpg'),
 (7,'.jpg','13489602865651.jpg'),
 (8,'.jpg','13660991062389.jpg'),
 (9,'.jpg','48992753954413.jpg'),
 (10,'.jpg','49395052098497.jpg'),
 (11,'.jpg','68594182299171.jpg');
/*!40000 ALTER TABLE `resource` ENABLE KEYS */;


--
-- Definition of table `trade`
--

DROP TABLE IF EXISTS `trade`;
CREATE TABLE `trade` (
  `tid` int(11) NOT NULL AUTO_INCREMENT,
  `bid` int(11) NOT NULL,
  PRIMARY KEY (`tid`),
  KEY `FK_winner` (`bid`),
  CONSTRAINT `FK_winner` FOREIGN KEY (`bid`) REFERENCES `bid` (`bid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `trade`
--

/*!40000 ALTER TABLE `trade` DISABLE KEYS */;
/*!40000 ALTER TABLE `trade` ENABLE KEYS */;


--
-- Definition of table `user`
--

DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `uid` int(11) NOT NULL AUTO_INCREMENT,
  `account` int(11) NOT NULL,
  `username` varchar(30) NOT NULL,
  `password` varchar(30) NOT NULL,
  `email` varchar(50) NOT NULL,
  `registertime` datetime NOT NULL,
  `level` int(11) NOT NULL,
  `honestylevel` int(11) NOT NULL,
  `status` tinyint(1) NOT NULL,
  `accstatus` int(11) NOT NULL,
  `fanscount` int(11) NOT NULL,
  `attentioncount` int(11) NOT NULL,
  `mark` text,
  `photo` varchar(50) NOT NULL,
  PRIMARY KEY (`uid`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

--
-- Dumping data for table `user`
--

/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` (`uid`,`account`,`username`,`password`,`email`,`registertime`,`level`,`honestylevel`,`status`,`accstatus`,`fanscount`,`attentioncount`,`mark`,`photo`) VALUES 
 (1,0,'123','123','123@qq.com','2014-08-07 16:33:06',0,5,1,1,0,0,'1234567890','369255906843226.jpg'),
 (2,0,'abc','abc','123@qq.com','2014-08-07 16:35:21',0,5,1,1,0,0,'1234567890','369391453708983.jpg'),
 (3,0,'xgy','xgy','xgy@gmail.com','2014-08-07 18:28:57',0,5,1,1,0,0,'xgy','376207781026428.jpg');
/*!40000 ALTER TABLE `user` ENABLE KEYS */;


--
-- Definition of table `work`
--

DROP TABLE IF EXISTS `work`;
CREATE TABLE `work` (
  `wid` int(11) NOT NULL AUTO_INCREMENT,
  `uid` int(11) NOT NULL,
  `workname` varchar(50) NOT NULL,
  `workdescription` text NOT NULL,
  `publishtime` datetime NOT NULL,
  `sharecount` int(11) NOT NULL DEFAULT '0',
  `visitcount` int(11) NOT NULL DEFAULT '0',
  `replycount` int(11) NOT NULL DEFAULT '0',
  `praisecount` int(11) NOT NULL DEFAULT '0',
  `collectcount` int(11) NOT NULL DEFAULT '0',
  `rid` int(11) DEFAULT NULL,
  `category` varchar(45) NOT NULL,
  PRIMARY KEY (`wid`),
  KEY `FK_user_works` (`uid`),
  CONSTRAINT `FK_user_works` FOREIGN KEY (`uid`) REFERENCES `user` (`uid`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8;

--
-- Dumping data for table `work`
--

/*!40000 ALTER TABLE `work` DISABLE KEYS */;
INSERT INTO `work` (`wid`,`uid`,`workname`,`workdescription`,`publishtime`,`sharecount`,`visitcount`,`replycount`,`praisecount`,`collectcount`,`rid`,`category`) VALUES 
 (1,1,'权威','而','2014-08-08 10:09:47',0,0,0,0,0,2,'摄影'),
 (2,1,'哈哈','呵呵','2014-08-08 10:43:49',0,0,0,0,0,4,'雕刻'),
 (3,1,'让我太温柔','发的所发生的','2014-08-08 10:52:40',0,0,0,0,0,5,'摄影'),
 (4,1,'请问我认为','请问请问我去','2014-08-08 11:09:57',0,0,0,0,0,6,'摄影'),
 (5,2,'回忆的沙漏','回忆是一道伤','2014-08-08 23:22:13',0,0,0,0,0,7,'其他'),
 (6,2,'夜空中最亮的星','夜空中最亮的星，是否记得','2014-08-08 23:25:04',0,0,0,0,0,8,'绘画'),
 (7,3,'时间','时间都去哪儿了','2014-08-09 09:13:53',0,0,0,0,0,9,'绘画'),
 (8,3,'愿得一人心','愿得一人心，白首不分离','2014-08-09 09:20:35',0,0,0,0,0,10,'摄影'),
 (9,2,'酒酒酒','酒逢知己','2014-08-09 14:40:34',0,0,0,0,0,11,'摄影');
/*!40000 ALTER TABLE `work` ENABLE KEYS */;


--
-- Definition of view `workuser`
--

DROP TABLE IF EXISTS `workuser`;
DROP VIEW IF EXISTS `workuser`;
CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`%` SQL SECURITY DEFINER VIEW `workuser` AS select `work`.`wid` AS `wid`,`work`.`uid` AS `uid`,`work`.`workname` AS `workname`,`work`.`workdescription` AS `workdescription`,`work`.`publishtime` AS `publishtime`,`work`.`sharecount` AS `sharecount`,`work`.`visitcount` AS `visitcount`,`work`.`replycount` AS `replycount`,`work`.`praisecount` AS `praisecount`,`work`.`collectcount` AS `collectcount`,`work`.`rid` AS `rid`,`work`.`category` AS `category`,`user`.`account` AS `account`,`user`.`username` AS `username`,`user`.`email` AS `email`,`user`.`registertime` AS `registertime`,`user`.`level` AS `level`,`user`.`honestylevel` AS `honestylevel`,`user`.`status` AS `status`,`user`.`accstatus` AS `accstatus`,`user`.`fanscount` AS `fanscount`,`user`.`attentioncount` AS `attentioncount`,`user`.`mark` AS `mark`,`user`.`photo` AS `photo` from (`work` join `user`) where ((`work`.`uid` in (select `attention`.`buid` from `attention` where (`attention`.`uid` = 1)) or (`work`.`uid` = 1)) and (`work`.`uid` = `user`.`uid`)) order by `work`.`publishtime` desc;



/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
