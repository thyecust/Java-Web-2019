# MySQL-Front 5.0  (Build 1.0)

/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE */;
/*!40101 SET SQL_MODE='STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES */;
/*!40103 SET SQL_NOTES='ON' */;


# Host: localhost    Database: bbs
# ------------------------------------------------------
# Server version 5.0.67-community-nt

#
# Table structure for table replyinfo
#

set names gbk;

DROP TABLE IF EXISTS `replyinfo`;
CREATE TABLE `replyinfo` (
  `rId` int(11) NOT NULL auto_increment,
  `rTId` int(11) NOT NULL default '0',
  `rSId` int(11) NOT NULL default '0',
  `rUId` int(11) NOT NULL default '0',
  `rTopic` varchar(20) default '',
  `rContents` text NOT NULL,
  `rPublishTime` datetime NOT NULL,
  `rModifyTime` datetime default NULL,
  PRIMARY KEY  (`rId`),
  KEY `FK_rUId` (`rUId`),
  KEY `FK_rSId` (`rSId`),
  KEY `FK_rTId` (`rTId`)
) ENGINE=InnoDB AUTO_INCREMENT=79 DEFAULT CHARSET=utf8;
INSERT INTO `replyinfo` VALUES (77,69,71,7,'д�ò�����һ����','д�ò�����һ����','2009-12-01 09:27:30',NULL);
INSERT INTO `replyinfo` VALUES (78,69,71,7,'д�ò�����һ����','д�ò�����һ����','2009-12-01 09:35:50',NULL);

#
# Table structure for table sectioninfo
#

DROP TABLE IF EXISTS `sectioninfo`;
CREATE TABLE `sectioninfo` (
  `sId` int(11) NOT NULL auto_increment,
  `sName` varchar(20) NOT NULL default '',
  `sTopicCount` int(11) NOT NULL default '0',
  `sMasterId` int(11) default '0',
  `sParentId` int(11) NOT NULL default '0',
  PRIMARY KEY  (`sId`)
) ENGINE=InnoDB AUTO_INCREMENT=82 DEFAULT CHARSET=utf8;
INSERT INTO `sectioninfo` VALUES (70,'ְ������',0,1,0);
INSERT INTO `sectioninfo` VALUES (71,'����ְ��',4,1,77);
INSERT INTO `sectioninfo` VALUES (73,'ְ���Ļ�',1,1,70);
INSERT INTO `sectioninfo` VALUES (74,'�������',1,1,70);
INSERT INTO `sectioninfo` VALUES (75,'�Ƹ�����',1,1,70);
INSERT INTO `sectioninfo` VALUES (77,'�����ۺ�',0,1,0);
INSERT INTO `sectioninfo` VALUES (78,'��������',1,1,77);
INSERT INTO `sectioninfo` VALUES (79,'Ѱ�����',1,1,77);
INSERT INTO `sectioninfo` VALUES (80,'�̺�����',1,1,77);
INSERT INTO `sectioninfo` VALUES (81,'��������',1,1,77);

#
# Table structure for table topicinfo
#

DROP TABLE IF EXISTS `topicinfo`;
CREATE TABLE `topicinfo` (
  `tId` int(11) NOT NULL auto_increment,
  `tSId` int(11) NOT NULL default '0',
  `tUId` int(11) NOT NULL default '0',
  `tTopic` varchar(20) NOT NULL default '',
  `tContents` text NOT NULL,
  `tReplyCount` int(11) NOT NULL default '0',
  `tClickCount` int(11) NOT NULL default '0',
  `tPublishTime` datetime NOT NULL,
  `tModifyTime` datetime default NULL,
  PRIMARY KEY  (`tId`),
  KEY `FK_tUId` (`tUId`),
  KEY `FK_tSId` (`tSId`)
) ENGINE=InnoDB AUTO_INCREMENT=70 DEFAULT CHARSET=utf8;
INSERT INTO `topicinfo` VALUES (55,73,1,'Test','Test',0,0,'2009-11-30 15:59:44',NULL);
INSERT INTO `topicinfo` VALUES (56,74,1,'Test','Test',0,0,'2009-11-30 15:59:57',NULL);
INSERT INTO `topicinfo` VALUES (57,75,1,'Test','Test',0,0,'2009-11-30 16:00:05',NULL);
INSERT INTO `topicinfo` VALUES (59,78,1,'Test','Test',0,0,'2009-11-30 16:00:19',NULL);
INSERT INTO `topicinfo` VALUES (60,79,1,'Test','Test',0,0,'2009-11-30 16:00:24',NULL);
INSERT INTO `topicinfo` VALUES (61,80,1,'Test','Test',0,0,'2009-11-30 16:00:37',NULL);
INSERT INTO `topicinfo` VALUES (62,81,1,'Test','Test',0,0,'2009-11-30 16:00:43',NULL);
INSERT INTO `topicinfo` VALUES (68,71,1,'�ܸ��ˣ��ܸ���ҽ�������','�ܸ��ˣ��ܸ���ҽ�������',0,0,'2009-11-30 17:01:18',NULL);
INSERT INTO `topicinfo` VALUES (69,71,1,'���ΪԱ�����췢չ�ռ�','��������һ��Ա����ʱ�����ǿ����ӹ����л��֪ʶ�����������ҵ���Ͽɡ����ǳ���ÿ����ȡ�Լ��Ĺ��ʣ���ϣ���õ��ģ���ְҵ���ķ�չ�Ŀռ�Ͷ�����<br><br>����1.��Ա����ְ��ѵ��<br><br>������Ա����ְ��ѵ�Ƿǳ���Ҫ�Ļ��ڣ������Ƕ���Ӧ���ҵ��ѧ����ѧ����ѧУ��������һ�νӴ�����˾����ʲô�ǹ�˾���߳�ְҵ���ĵĵ�һ������������֪��ʲô��ְҵ���ġ�����Ա����ְ��ѵ����Ӧ��Ϊ���ǽ��⹫˾�ļ�ֵ�ۡ��Ļ��������������ְҵ������ʲô���ġ�<br><br>����2.��Ȳ�����Ŀ�Ļ��ᡣ<br><br>�����ܶ�ʱ����Ա����Ǳ���Ǻܴ�ģ�����Ҫ�����ܸ�������ʩչ�ռ䡣������������㹻�ķ�չ�ռ䣬��Ҷ���û�л�����Ƚ��뵽��Ŀ���У���̸�����ĳɳ��뷢չ?��ˣ���Ҫ�ԡ������С�����û���顱�������ɾܾ����Ƕ���Ŀ�С����Ѷȡ������ĺ����ģ���һ���ţ���ᷢ�ִ����ʵ�������úܺ�!<br><br>����3.רҵ���ܵ���ѵ��<br><br>��������Ա����ְ��һ�����ҵ�ʱ�䣬����Ӧ���Ѿ��ܹ�����ؿ���ÿһ���˵����Ƽ��̰壬������޶ȷ������Ƶ�ͬʱ��Ҳ��Ҫ����Ϊ�����ṩרҵ���������Ļ��ᡪ����ѵ���г��Ϻܶ���ѵ��������4000~5000Ԫ����ѵ���ܻ���������һ���µĹ��ʣ����Ǹ�����һ�ܵ�ʱ��ȥѧϰ����ʹ�����л��������µ�̨�ס�<br><br>����4.���ɷ�չ�Ŀռ䡣<br><br>�����ܶ���ҵ��������Ϊ������ʵ����Ϊ����򵥵�һ�����־����ܹ�����Ա���ĸ��˷�չ��Ը��������Ը����ְҵ���򡢼�����ȡ���ȵȵȡ��ܶ�ʱ����Ա���ڹ����л����������ö��������ǻ��Լ�ȥ�飬�Լ�ȥѧ���������Լ���˼ά��Ҳ������Լ�����Ȥ����ҵ��Ӧ���ǰ�����ȡԱ��ʣ���ֵ�Ļ���������Ӧ����Ϊһ��������εĳе��ߣ��������ǡ��ƶ����ǵ�ְҵ���Ĳ��Ϸ�չ��<br>',2,0,'2009-11-30 17:03:53',NULL);

#
# Table structure for table userinfo
#

DROP TABLE IF EXISTS `userinfo`;
CREATE TABLE `userinfo` (
  `uId` int(11) NOT NULL auto_increment,
  `uName` varchar(10) NOT NULL default '',
  `uPassWord` varchar(20) NOT NULL default '',
  `uSex` tinyint(1) NOT NULL default '0',
  `uFace` varchar(5) NOT NULL default '',
  `uRegTime` datetime NOT NULL,
  `uType` int(11) NOT NULL default '0',
  PRIMARY KEY  (`uId`),
  UNIQUE KEY `UQ_uName` (`uName`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;
INSERT INTO `userinfo` VALUES (1,'admin','admin',1,'1.gif','2009-01-11 22:17:48',2);
INSERT INTO `userinfo` VALUES (7,'test','test',1,'2.gif','2009-11-30 14:52:58',2);
INSERT INTO `userinfo` VALUES (8,'javaweb','javaweb',0,'2.gif','2010-01-29 10:34:37',0);

#
#  Foreign keys for table replyinfo
#

ALTER TABLE `replyinfo`
ADD CONSTRAINT `FK_rUId` FOREIGN KEY (`rUId`) REFERENCES `userinfo` (`uId`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `FK_rSId` FOREIGN KEY (`rSId`) REFERENCES `sectioninfo` (`sId`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `FK_rTId` FOREIGN KEY (`rTId`) REFERENCES `topicinfo` (`tId`) ON DELETE NO ACTION ON UPDATE NO ACTION;

#
#  Foreign keys for table topicinfo
#

ALTER TABLE `topicinfo`
ADD CONSTRAINT `FK_tUId` FOREIGN KEY (`tUId`) REFERENCES `userinfo` (`uId`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `FK_tSId` FOREIGN KEY (`tSId`) REFERENCES `sectioninfo` (`sId`) ON DELETE NO ACTION ON UPDATE NO ACTION;


/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
