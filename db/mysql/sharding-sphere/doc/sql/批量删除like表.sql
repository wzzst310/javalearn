
CREATE PROCEDURE drop_table_like(IN table_prefix varchar(64), IN username varchar(256))
BEGIN
    DECLARE tname varchar(128) default '';
    DECLARE not_found INT DEFAULT 0;
    DECLARE cur_tnames cursor FOR SELECT table_name FROM INFORMATION_SCHEMA.TABLES WHERE table_schema = username and table_name like table_prefix;
    -- concat("'", table_prefix, "%'");
    DECLARE CONTINUE HANDLER FOR NOT FOUND SET not_found = 1;
    DECLARE EXIT HANDLER FOR SQLEXCEPTION ROLLBACK;
    OPEN cur_tnames;
    WHILE not_found = 0 DO
        FETCH cur_tnames INTO tname;
        IF NOT not_found THEN
            -- select tname;
            set @sql = concat('DROP TABLE ', tname);
            prepare stmt from @sql;
            execute stmt;
            deallocate prepare stmt;
        END IF;
    END WHILE;
    CLOSE cur_tnames;
END


# 使用
call drop_table_like('record_%', 'course_db')