ALTER TABLE `record`
    CHANGE COLUMN `yyyymm` `yyyymmm` bigint(20) NULL DEFAULT NULL AFTER `cstatus`,
    ADD COLUMN `end_date` datetime(0) NULL AFTER `yyyymmm`;