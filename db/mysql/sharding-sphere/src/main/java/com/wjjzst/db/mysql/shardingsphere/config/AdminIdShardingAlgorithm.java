/*
package com.wjjzst.db.mysql.shardingsphere.config;
*/
/**
 * 分片键分布配置
 *//*


import org.apache.shardingsphere.api.sharding.ShardingValue;
import org.apache.shardingsphere.api.sharding.complex.ComplexKeysShardingAlgorithm;
import org.apache.shardingsphere.api.sharding.complex.ComplexKeysShardingValue;

import java.util.*;
import java.util.logging.Logger;

*/
/**
 *//*

public class adminIdIdShardingAlgorithm implements ComplexKeysShardingAlgorithm {


    @Override
    public Collection<String> doSharding(Collection availableTargetNames, ComplexKeysShardingValue shardingValue) {
        return null;
    }

    @Override
    public Collection<String> doSharding(Collection<String> availableTargetNames, Collection<ShardingValue> shardingValues) {
        Collection<String> routTables = new HashSet<String>();
        if (shardingValues != null) {
            for (ShardingValue shardingValue : shardingValues) {

                // eq in 条件
                if (shardingValue instanceof ListShardingValuedingValue) {
                    ListShardingValue listShardingValue = (ListShardingValue) shardingValue;
                    Collection<Comparable> values = listShardingValue.getValues();
                    if (values != null) {
                        Iterator<Comparable> it = values.iterator();
                        while (it.hasNext()) {
                            Comparable value = it.next();
                            String routTable = getRoutTable(shardingValue.getLogicTableName(), value);
                            if (StringUtils.isNotBlank(routTable)) {
                                routTables.add(routTable);
                            }
                        }
                    }

                    // eq 条件
                } else if (shardingValue instanceof PreciseShardingValue) {
                    PreciseShardingValue preciseShardingValue = (PreciseShardingValue) shardingValue;

                    Comparable value = preciseShardingValue.getValue();
                    String routTable = getRoutTable(shardingValue.getLogicTableName(), value);
                    if (StringUtils.isNotBlank(routTable)) {
                        routTables.add(routTable);
                    }
                    // between 条件
                } else if (shardingValue instanceof RangeShardingValue) {
                    RangeShardingValue rangeShardingValue = (RangeShardingValue) shardingValue;
                    Range<Comparable> valueRange = rangeShardingValue.getValueRange();
                    Comparable lowerEnd = valueRange.lowerEndpoint();
                    Comparable upperEnd = valueRange.upperEndpoint();

                    Collection<String> tables = getRoutTables(shardingValue.getLogicTableName(), lowerEnd, upperEnd);
                    if (tables != null && tables.size() > 0) {
                        routTables.addAll(tables);
                    }
                }

                if (routTables != null && routTables.size() > 0) {
                    return routTables;
                }
            }
        }

        throw new UnsupportedOperationException();

    }

    private String getRoutTable(String logicTable, Comparable keyValue) {
        Map<String, List<KeyShardingRange>> keyRangeMap = KeyShardingRangeConfig.getKeyRangeMap();

        List<KeyShardingRange> keyShardingRanges = keyRangeMap.get(KeyShardingRangeConfig.SHARDING_ID_KEY);

        if (keyValue != null && keyShardingRanges != null) {
            if (keyValue instanceof Integer) {
                keyValue = Long.valueOf(((Integer) keyValue).intValue());
            }
            for (KeyShardingRange range : keyShardingRanges) {
                if (keyValue.compareTo(range.getMin()) >= 0 && keyValue.compareTo(range.getMax()) <= 0) {
                    return logicTable + range.getTableKey();
                }
            }
        }
        return null;
    }
    private Collection<String> getRoutTables(String logicTable, Comparable lowerEnd, Comparable upperEnd) {
        Map<String, List<KeyShardingRange>> keyRangeMap = KeyShardingRangeConfig.getKeyRangeMap();

        List<KeyShardingRange> keyShardingRanges = keyRangeMap.get(KeyShardingRangeConfig.SHARDING_CONTENT_ID_KEY);
        Set<String> routTables = new HashSet<String>();
        if (lowerEnd != null && upperEnd != null && keyShardingRanges != null) {
            if (lowerEnd instanceof Integer) {
                lowerEnd = Long.valueOf(((Integer) lowerEnd).intValue());
            }

            if (upperEnd instanceof Integer) {
                upperEnd = Long.valueOf(((Integer) upperEnd).intValue());
            }
            boolean start = false;
            for (KeyShardingRange range : keyShardingRanges) {
                if (lowerEnd.compareTo(range.getMin()) >= 0 && lowerEnd.compareTo(range.getMax()) <= 0) {
                    start = true;
                }
                if (start) {
                    routTables.add(logicTable + range.getTableKey());
                }
                if (upperEnd.compareTo(range.getMin()) >= 0 && upperEnd.compareTo(range.getMax()) <= 0) {
                    break;
                }
            }
        }
        return routTables;
    }


}
*/
