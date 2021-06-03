package com.wjjzst.ads.second_stage.learn._01sorting;

import com.wjjzst.ads.second_stage.learn._01sorting.cmp.A_AbstractSort;

/**
 * @Author: Wjj
 * @Date: 2020/6/18 12:18 上午
 * @desc: 基数排序
 * 非常适合哟领域整数(特别是非负整数)
 * 依次对个位数/十位数/百位数/千位数/万位数进行排序(从低位到高位)
 * 针对个位数来一次计数排序 针对十位数来一次计数排序
 */
public class J_RadixSort extends A_AbstractSort<Integer> {
    @Override
    protected void sort() {
        sort1();
        // sort2();
        // teacherSort2();
    }

    // 593 / 1 % 10
    // 593 / 10 % 10
    // 593 / 100 % 10
    // 通过对每一位的基数进行计数排序 由低位到高位 进行排序(高位的权重高,越高位应该越后面排)
    // 计算每一位基数 array[i] / divider % 10
    private void sort1() {
        Integer init = array[0];
        Integer max = init;
        // 找到最大最小值
        for (int i = 1; i < array.length; i++) {
            Integer each = array[i];
            if (each > max) {
                max = each;
            }
        }
        // 降低空间复杂度
        int[] counts = new int[10];
        Integer[] newArray = new Integer[array.length];
        for (int i = 1; i <= max; i *= 10) {
            countSort(i, counts, newArray);
        }
    }

    private void countSort(int divider, int[] counts, Integer[] newArray) {
        for (int i = 0; i < counts.length; i++) {
            counts[i] = 0;
        }
        // 把元素当所以构建arrays
        // int[] counts = new int[10];
        for (int i = 0; i < array.length; i++) {
            counts[array[i] / divider % 10]++;
        }
        // arrays每个位置存储的次数应该累加之前所有的次数
        int lastNotZeroIndex = 0;
        for (int i = 1; i < counts.length; i++) {
            if (counts[i] != 0) {
                counts[i] += counts[lastNotZeroIndex];
                lastNotZeroIndex = i;
            }
        }
        // Integer[] newArray = new Integer[array.length];
        for (int i = array.length - 1; i >= 0; i--) {
            Integer each = array[i];
            // array中元素找到arrays中对应的累积次数-1就是新数组中的
            newArray[--counts[array[i] / divider % 10]] = each;
        }
        for (int i = 0; i < array.length; i++) {
            array[i] = newArray[i];
        }
        // 数组类似普通类型 数组是值传递不是引用传递
        // array = newArray;
    }

    private void sort2() {
        Integer init = array[0];
        Integer max = init;
        // 找到最大最小值
        for (int i = 1; i < array.length; i++) {
            Integer each = array[i];
            if (each > max) {
                max = each;
            }
        }
        for (int divider = 1; divider <= max; divider *= 10) {
            arrSort(divider);
        }
    }

    private void arrSort(int divider) {
        Integer[][] buckets = new Integer[10][array.length];
        for (int cursor = 0; cursor < array.length; cursor++) {
            Integer element = array[cursor];
            int radix = element / divider % 10;
            for (int j = 0; j < buckets[radix].length; j++) {
                if (buckets[radix][j] == null) {
                    buckets[radix][j] = element;
                    break;
                }
            }
        }
        int cursor = 0;
        for (int j = 0; j < buckets.length; j++) {
            for (int k = 0; k < buckets[j].length; k++) {
                if (buckets[j][k] == null) {
                    break;
                }
                array[cursor++] = buckets[j][k];
            }
        }
    }

    private void teacherSort2() {
        Integer max = array[0];
        for (int i = 1; i < array.length; i++) {
            if (array[i] > max) {
                max = array[i];
            }
        }
        // 桶数组
        int[][] buckets = new int[10][array.length];
        // 每个桶的元素数量
        // 搞个数组记录每个桶当前位置
        int[] bucketSizes = new int[buckets.length];
        for (int divider = 1; divider <= max; divider *= 10) {
            for (int i = 0; i < array.length; i++) {
                int no = array[i] / divider % 10;
                buckets[no][bucketSizes[no]++] = array[i];
            }
            int index = 0;
            for (int i = 0; i < buckets.length; i++) {
                for (int j = 0; j < bucketSizes[i]; j++) {
                    array[index++] = buckets[i][j];
                }
                bucketSizes[i] = 0;
            }
        }
    }
}
