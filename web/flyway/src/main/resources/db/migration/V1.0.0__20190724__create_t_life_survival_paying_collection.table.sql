/*
Navicat MySQL Data Transfer

Source Server         : khthome
Source Server Version : 50718
Source Host           : 10.29.39.253:3306
Source Database       : life

Target Server Type    : MYSQL
Target Server Version : 50718
File Encoding         : 65001

Date: 2019-01-25 09:45:04
*/

SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for t_life_survival_paying_collection
-- ----------------------------
DROP TABLE IF EXISTS `t_life_survival_paying_collection`;
CREATE TABLE `t_life_survival_paying_collection`
(
    `id`             int(11)      NOT NULL AUTO_INCREMENT,
    `policy_code`    varchar(255) NOT NULL COMMENT '保单号码',
    `biz_channel`    varchar(255) NOT NULL COMMENT '保全渠道',
    `bank_code`      varchar(255) NOT NULL COMMENT '账号所属银行',
    `bank_account`   varchar(255) NOT NULL COMMENT '付费账号',
    `account_type`   varchar(255) NOT NULL COMMENT '账号类型',
    `accoOwner_name` varchar(255) NOT NULL COMMENT '账号所有人',
    `organ_id`       varchar(255) NOT NULL COMMENT '账户所属机构',
    `cs_apply_no`    varchar(255) NOT NULL COMMENT '保全申请号',
    `user_id`        varchar(255) NOT NULL COMMENT '用户',
    `create_time`    datetime     NOT NULL COMMENT '创建时间',
    `return_flag`    varchar(255)  DEFAULT NULL COMMENT '结果表示 0：成功；1：失败；2：规则库校验失败',
    `return_message` varchar(3000) DEFAULT NULL COMMENT '返回信息 成功为空，失败后为错误信息',
    `change_id`      varchar(255)  DEFAULT NULL COMMENT '保全id',
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8;
