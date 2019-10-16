# Mysql start

## Installation for Windows

1. Download ZIP package from Internet.
2. Unzip to `C:\web\mysql-8.0.17-winx64`.
3. Add `my.ini` to `C:\web\mysql-8.0.17-winx64` with content:
```
[client]
# 设置mysql客户端默认字符集
default-character-set=utf8
 
[mysqld]
# 设置3306端口
port = 3306
# 设置mysql的安装目录
basedir=C:\\web\\mysql-8.0.17-winx64
# 设置 mysql数据库的数据的存放目录，MySQL 8+ 不需要以下配置，系统自己生成即可，否则有可能报错
# datadir=C:\\web\\sqldata
# 允许最大连接数
max_connections=20
# 服务端使用的字符集默认为8比特编码的latin1字符集
character-set-server=utf8
# 创建新表时将使用的默认存储引擎
default-storage-engine=INNODB
```
4. Run cmd as Administrator:
```
Microsoft Windows [版本 10.0.18362.418]
(c) 2019 Microsoft Corporation。保留所有权利。

C:\WINDOWS\system32>cd C:\web\mysql-8.0.17-winx64\bin

C:\web\mysql-8.0.17-winx64\bin>mysqld --initialize --console
2019-10-16T05:36:40.755285Z 0 [System] [MY-013169] [Server] C:\web\mysql-8.0.17-winx64\bin\mysqld.exe (mysqld 8.0.17) initializing of server in progress as process 27068
2019-10-16T05:36:40.757307Z 0 [Warning] [MY-013242] [Server] --character-set-server: 'utf8' is currently an alias for the character set UTF8MB3, but will be an alias for UTF8MB4 in a future release. Please consider using UTF8MB4 in order to be unambiguous.
2019-10-16T05:36:46.405372Z 5 [Note] [MY-010454] [Server] A temporary password is generated for root@localhost: qI8.*LNJQUE(
2019-10-16T05:36:48.616725Z 0 [System] [MY-013170] [Server] C:\web\mysql-8.0.17-winx64\bin\mysqld.exe (mysqld 8.0.17) initializing of server has completed

C:\web\mysql-8.0.17-winx64\bin>mysqld install
Service successfully installed.

C:\web\mysql-8.0.17-winx64\bin>net start mysql
MySQL 服务正在启动 ..
MySQL 服务已经启动成功。
```
You have successfully installed MySQL.

## Log in

In cmd:
```
C:\web\mysql-8.0.17-winx64\bin>mysql -u root -p
Enter password: ************
Welcome to the MySQL monitor.  Commands end with ; or \g.
Your MySQL connection id is 9
Server version: 8.0.17

Copyright (c) 2000, 2019, Oracle and/or its affiliates. All rights reserved.

Oracle is a registered trademark of Oracle Corporation and/or its
affiliates. Other names may be trademarks of their respective
owners.

Type 'help;' or '\h' for help. Type '\c' to clear the current input statement.

mysql>
```

The `mysql>` reminds you have logged in.

You can change the password:
```
mysql> use mysql;
ERROR 1820 (HY000): You must reset your password using ALTER USER statement before executing this statement.
mysql> ALTER USER
    -> USER()
    -> IDENTIFIED BY '123456';
Query OK, 0 rows affected (0.04 sec)
```


