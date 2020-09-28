package com.wjjzst.queue.rabbitmq.rabbitmqspringcloud.config.myself;

import lombok.Getter;

/**
 * @author: wangzhe
 * @create: 2020/9/23 11:25 上午
 * @Description
 */
public enum NormalQueueEnum {
    MC_OPERATE_CARD("operate_card_exchange", "operate_card", "operate_card_key"),
    MC_MEMBER_TIER("member_tier_exchange", "member_tier", "member_tier_key"),
    MC_MEMBER_TIER_CALLBACK("member_tier_callback_exchange", "member_tier_callback", "member_tier_callback_key"),
    MC_MEMBER_TRANSACTION("member_transaction_exchange", "member_transaction", "member_transaction_key"),
    MC_CAMPAIGN("mc_campaign_exchange", "mc_campaign", "mc_campaign_key"),
    MC_CASHBACK_SYNC("cashback_sync_exchange", "cashback_sync", "cashback_sync_key"),
    MC_CASHBACK_CONSUME("cashback_consume_exchange", "cashback_consume", "cashback_consume_key"),
    MC_SEGMENT_ACCOUNT_BATCH_OPERATE("segment_account_batch_operate_exchange", "segment_account_batch_operate", "segment_account_batch_operate_key"),
    MC_SEGMENT_ACCOUNT_BATCH_OPERATE_CALLBACK("segment_account_batch_operate_callback_exchange", "segment_account_batch_operate_callback", "segment_account_batch_operate_callback_key"),
    MC_CONDITION_SEGMENT_OPERATE("condition_segment_operate_exchange", "condition_segment_operate", "condition_segment_operate_key"),
    ;
    /**
     * 交换名称
     */
    @Getter
    private String exchange;

    /**
     * 队列名称
     */
    @Getter
    private String name;

    /**
     * 路由键
     */
    @Getter
    private String routeKey;

    NormalQueueEnum(String exchange, String name, String routeKey) {
        this.exchange = exchange;
        this.name = name;
        this.routeKey = routeKey;
    }
}