CREATE TABLE `record` (
                          `cid` bigint(20) NOT NULL,
                          `cname` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
                          `user_id` bigint(20) DEFAULT NULL,
                          `cstatus` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
                          `yyyymm` varchar(6) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
                          `create_time` datetime DEFAULT NULL COMMENT '创建时间',
                          `card_no` bigint(20) DEFAULT NULL COMMENT '卡号',
                          `create_at` datetime DEFAULT NULL COMMENT '卡号创建时间',
                          PRIMARY KEY (`cid`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;