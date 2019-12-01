# SQL start

SQL，Structured Query Language，结构化查询语言。

RDBMS，Relational Database Management System，关系型数据库管理系统，是SQL的基础，也是所有现代数据库系统的基础。

RDBMS的数据存储在名称为表的对象中，表由行和列组成。

一个数据库通常包含多个表。

一些SQL命令：

* SELECT 从数据库中提取数据
* UPDATE 更新数据
* DELETE 删除数据
* INSERT INTO 插入新数据
* CREATE DATABASE 创建数据库
* ALTER DATABASE 修改数据库
* CREATE TABLE 创建表
* ALTER TABLE 修改表
* DROP TABLE 删除表
* CREATE INDEX 创建索引
* DROP INDEX 删除索引

SQL注释：

* -- 表示单行注释（注意-- 之后是一个空格）
* /**/ 表示多行注释
* #表示单行注释（MySQL中）

## 创建数据表

```sql
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
) ENGINE=InnoDB AUTO_INCREMENT=9 
DEFAULT CHARSET=utf8;
```

这段代码使用InnoDB作为存储引擎、使用utf8编码创建了一个表。

表的第一列是uId，是一个自增序列，从9开始；

表的第二列是Uname，