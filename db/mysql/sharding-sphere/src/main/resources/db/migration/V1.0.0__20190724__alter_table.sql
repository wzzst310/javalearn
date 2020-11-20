ALTER TABLE `course`
    ADD COLUMN `begin_date` datetime(0) NULL AFTER `yyyymm`,
    ADD COLUMN `end_date` datetime(0) NULL AFTER `begin_date`;