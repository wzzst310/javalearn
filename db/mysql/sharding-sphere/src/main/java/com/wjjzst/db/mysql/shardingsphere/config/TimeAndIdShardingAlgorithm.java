package com.wjjzst.db.mysql.shardingsphere.config;

import com.alibaba.fastjson.JSON;
import com.wjjzst.common.util.DateUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.shardingsphere.api.sharding.complex.ComplexKeysShardingAlgorithm;
import org.apache.shardingsphere.api.sharding.complex.ComplexKeysShardingValue;

import java.util.*;

/**
 * @Author: Wjj
 * @Date: 2020/11/22 13:47
 * @desc:
 */
@Slf4j
public class TimeAndIdShardingAlgorithm<T extends Comparable<?>> implements ComplexKeysShardingAlgorithm<T> {

    @Override
    public Collection<String> doSharding(Collection<String> availableTargetNames, ComplexKeysShardingValue<T> shardingValues) {
        System.out.println("collection:" + availableTargetNames + ",shardingValues:" + shardingValues);
        Map columnNameAndShardingValuesMap = shardingValues.getColumnNameAndShardingValuesMap();
        Collection<Long> cardNos = (Collection<Long>) columnNameAndShardingValuesMap.get("card_no");
        Collection<Date> createAts = (Collection<Date>) columnNameAndShardingValuesMap.get("create_at");
        List<String> shardingSuffix = new ArrayList<>();
        log.error("cardNos: " + JSON.toJSONString(cardNos));
        log.error("createAts: " + JSON.toJSONString(createAts));
        // user_id，order_id分片键进行分表
//        Long cardNo = cardNos.iterator().next();
//        Date createAt = createAts.iterator().next();
//        long cardNoPrefix = (cardNo % 3) + 1;
//        String createAtPrefix = DateUtils.date2Str(createAt, DateUtils.YEAR_MONTH_NUMBER);
//        log.error("cardNo: " + cardNoPrefix);
//        log.error("createAt: " + createAtPrefix);
//        String suffix = "_" + cardNoPrefix + "_" + createAtPrefix;
//        for (String s : availableTargetNames) {
//            if (s.endsWith(suffix)) {
//                shardingSuffix.add(s);
//            }
//        }
//        return shardingSuffix;
//
        for (Long cardNo : cardNos) {
            for (Date createAt : createAts) {
                long cardNoPrefix = (cardNo % 3) + 1;
                String createAtPrefix = DateUtils.date2Str(createAt, DateUtils.YEAR_MONTH_NUMBER);
                log.error("cardNo: " + cardNoPrefix);
                log.error("createAt: " + createAtPrefix);
                String suffix = "_" + cardNoPrefix + "_" + createAtPrefix;
                for (String s : availableTargetNames) {
                    if (s.endsWith(suffix)) {
                        shardingSuffix.add(s);
                    }
                }
            }
        }
        return shardingSuffix;
    }
}
