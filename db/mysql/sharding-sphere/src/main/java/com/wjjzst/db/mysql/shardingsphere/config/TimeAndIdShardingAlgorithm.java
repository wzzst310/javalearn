package com.wjjzst.db.mysql.shardingsphere.config;

import com.alibaba.fastjson.JSON;
import org.apache.shardingsphere.api.sharding.complex.ComplexKeysShardingAlgorithm;
import org.apache.shardingsphere.api.sharding.complex.ComplexKeysShardingValue;

import java.util.Collection;

/**
 * @Author: Wjj
 * @Date: 2020/11/22 13:47
 * @desc:
 */
public class TimeAndIdShardingAlgorithm implements ComplexKeysShardingAlgorithm {


    @Override
    public Collection<String> doSharding(Collection availableTargetNames, ComplexKeysShardingValue shardingValue) {
        System.out.println(JSON.toJSONString(availableTargetNames));
        System.out.println(JSON.toJSONString(shardingValue));
        return null;
    }
}
