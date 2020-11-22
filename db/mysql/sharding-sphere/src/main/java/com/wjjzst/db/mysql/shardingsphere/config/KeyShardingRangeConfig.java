/*
package com.wjjzst.db.mysql.shardingsphere.config;

*/
/**
 * @Author: Wjj
 * @Date: 2020/11/22 14:08
 * @desc:
 *//*

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

*/
/**
 * 分片键分布配置
 *//*

public class KeyShardingRangeConfig {

    private static Map<String, List<KeyShardingRange>> keyRangeMap = new LinkedHashMap<String, List<KeyShardingRange>>();

    public static final String SHARDING_ID_KEY = "id";

    public static final String SHARDING_CONTENT_ID_KEY = "adminId";

    public static final String SHARDING_DATE_KEY = "createTime";

    static {
        List<KeyShardingRange> idRanges = new ArrayList<KeyShardingRange>();
        idRanges.add(new KeyShardingRange(0, "_0", 0L, 4000000L));
        idRanges.add(new KeyShardingRange(1, "_1", 4000001L, 8000000L));
        idRanges.add(new KeyShardingRange(2, "_2", 8000001L, 12000000L));
        idRanges.add(new KeyShardingRange(3, "_3", 12000001L, 16000000L));
        idRanges.add(new KeyShardingRange(4, "_4", 16000001L, 2000000L));
        keyRangeMap.put(SHARDING_ID_KEY, idRanges);

        List<KeyShardingRange> contentIdRanges = new ArrayList<KeyShardingRange>();
        contentIdRanges.add(new KeyShardingRange(0, "_0", 0L, 4000000L));
        contentIdRanges.add(new KeyShardingRange(1, "_1", 4000001L, 8000000L));
        contentIdRanges.add(new KeyShardingRange(2, "_2", 8000001L, 12000000L));
        contentIdRanges.add(new KeyShardingRange(3, "_3", 12000001L, 16000000L));
        contentIdRanges.add(new KeyShardingRange(4, "_4", 16000001L, 2000000L));
        keyRangeMap.put(SHARDING_CONTENT_ID_KEY, contentIdRanges);

        List<KeyShardingRange> timeRanges = new ArrayList<KeyShardingRange>();
        timeRanges.add(new KeyShardingRange("_0", 20170701L, 20171231L));
        timeRanges.add(new KeyShardingRange("_1", 20180101L, 20180630L));
        timeRanges.add(new KeyShardingRange("_2", 20180701L, 20181231L));
        timeRanges.add(new KeyShardingRange("_3", 20190101L, 20190630L));
        timeRanges.add(new KeyShardingRange("_4", 20190701L, 20191231L));
        keyRangeMap.put(SHARDING_DATE_KEY, timeRanges);
    }

    public static Map<String, List<KeyShardingRange>> getKeyRangeMap() {
        return keyRangeMap;
    }



}*/
