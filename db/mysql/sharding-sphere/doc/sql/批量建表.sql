DELIMITER $$

        USE `DBName`$$

        DROP PROCEDURE IF EXISTS `pro_TableCreate`$$

        CREATE DEFINER=`root`@`%` PROCEDURE `pro_TableCreate`(
        )
        BEGIN
        DECLARE i INT;
        DECLARE table_name VARCHAR(20);
        SET i = 0;

        WHILE i<100 DO
        #为了使表名成为xxx00这样的格式加的条件判断
        IF i<10 THEN
        SET table_name = CONCAT('t_UserLog0',i);
        ELSE
        SET table_name = CONCAT('t_UserLog',i);
        END IF;

        SET @csql = CONCAT(
        'CREATE TABLE ',table_name,'(
        ID bigint(18) UNSIGNED NOT NULL auto_increment COMMENT"注释",
        UserID int(10) comment"注释",
        ModularID int(4) comment"注释",
        BillTypeID int(4) comment"注释",
        BillerID bigint(18) comment"注释",
        LogTypeID smallint(2) default "0" comment"注释",
        Log varchar(1000) comment"注释",
        LogDate datetime comment"注释",
        Deleted tinyint(1) DEFAULT "0" comment"注释",
        DeletedID int(10) comment"注释",
        Deletedate datetime comment"注释",
        PRIMARY KEY(FInterID)
        )ENGINE=Innodb default charset=utf8;'
);

PREPARE create_stmt FROM @csql;
EXECUTE create_stmt;
SET i = i+1;
END WHILE;

END$$

DELIMITER ;

CALL pro_TableCreate();



DELIMITER $$
DROP FUNCTION IF EXISTS genPerson$$
CREATE FUNCTION genPerson(name varchar(20)) RETURNS varchar(50)
BEGIN
  DECLARE str VARCHAR(50) DEFAULT '';
        SET @tableName=name;
        SET str=CONCAT('create table ', @tableName,'(id int, name varchar(20));');
        return str;
        END $$
        DELIMITER ;

select genPerson('student');

（1）DELIMITER $$  定义结束符。MySQL默认的结束符是分号，但是函数体中可能用到分号。为了避免冲突，需要另外定义结束符。

（2）DROP FUNCTION IF EXISTS genPerson$$  如果函数genPerson已经存在了，就删除掉。

（3）CREATE FUNCTION 创建函数genPerson，函数的参数是name，返回值是varchar(50)。

（4）函数体放在BEGIN 与 END之间。

（5）DECLARE 声明变量，str类型是varchar(50)，默认值是空。

（6）CONCAT连接多个字符串。

（7）RETURN 返回拼接后的字符串str。