CREATE DEFINER=`root`@`%` PROCEDURE `batchTableCreate`(IN tableBaseName varchar(30), IN batchType int(1), IN beginDate date,
                                                  IN endDate date)
BEGIN
DECLARE table_name VARCHAR(39);
WHILE beginDate <= endDate DO
	IF batchType = 1 THEN
        SET table_name = CONCAT(tableBaseName,'_',date_format(beginDate,'%Y%m'));
        set beginDate = DATE_ADD(beginDate, INTERVAL 1 MONTH);
	ELSEIF batchType = 2 THEN
		SET table_name = CONCAT(tableBaseName,'_',date_format(beginDate,'%Y%m%d'));
        set beginDate = DATE_ADD(beginDate, INTERVAL 1 DAY);
	ELSE
		SET table_name = CONCAT(tableBaseName,'_',date_format(beginDate,'%Y'));
        set beginDate = DATE_ADD(beginDate, INTERVAL 1 YEAR);
	END IF;
SET @csql = CONCAT(
'CREATE TABLE ',table_name,' (
`cid` bigint(20) NOT NULL,
`cname` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
`user_id` bigint(20) DEFAULT NULL,
`cstatus` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
`create_time` datetime DEFAULT NULL,
PRIMARY KEY (`cid`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;'
);
PREPARE create_stmt FROM @csql;
EXECUTE create_stmt;
END WHILE;
END

# 使用
call drop_table_like('record_%', 'course_db');
call batchTableCreate('record', 1, '2019-01-01','2025-12-31');