package com.wjjzst.db.mysql.shardingsphere.config;

import com.wjjzst.common.util.DateUtils;
import org.apache.shardingsphere.api.sharding.complex.ComplexKeysShardingAlgorithm;
import org.apache.shardingsphere.api.sharding.complex.ComplexKeysShardingValue;

import java.util.*;

/**
 * @Author: Wjj
 * @Date: 2020/11/22 13:47
 * @desc:
 */
public class TimeAndIdShardingAlgorithm<T extends Comparable<?>> implements ComplexKeysShardingAlgorithm<T> {

    @Override
    public Collection<String> doSharding(Collection<String> availableTargetNames, ComplexKeysShardingValue<T> shardingValues) {
        System.out.println("collection:" + availableTargetNames + ",shardingValues:" + shardingValues);
        Map columnNameAndShardingValuesMap = shardingValues.getColumnNameAndShardingValuesMap();
        Collection<Integer> cardNos = (Collection<Integer>) columnNameAndShardingValuesMap.get("cardNo");
        Collection<Date> createAts = (Collection<Date>) columnNameAndShardingValuesMap.get("createAt");
        List<String> shardingSuffix = new ArrayList<>();
        System.out.println(cardNos);
        System.out.println(createAts);
        // user_id，order_id分片键进行分表
        for (Integer cardNo : cardNos) {
            for (Date createAt : createAts) {
                int cardNoPrefix = (cardNo % 3) + 1;
                String createAtPrefix = DateUtils.date2Str(createAt, DateUtils.YEAR_MONTH_NUMBER);
                System.out.println("cardNo: " + cardNoPrefix);
                System.out.println("createAt: " + createAtPrefix);
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
