package com.wjjzst.ads.secondStage.learn._01sorting;

import com.wjjzst.ads.secondStage.learn._01sorting.cmp.A_AbstractSort;

/**
 * @Author: Wjj
 * @Date: 2020/4/21 10:24 下午
 * @desc: 计数排序
 */
public class I_CountSort extends A_AbstractSort<Integer> {

    @Override
    protected void sort() {
//        array = new Integer[]{
//            7, 3, 5, 8, 6, 7, 4, 5
//        } ;
        teacherSort();
    }

    private void mySort() {
        Integer init = array[0];
        Integer min = init, max = init;
        // 找到最大最小值
        for (int i = 1; i < array.length; i++) {
            Integer each = array[i];
            if (each > max) {
                max = each;
                continue;
            }
            if (each < min) {
                min = each;
            }
        }
        // 把元素当所以构建arrays
        int[] counts = new int[max - min + 1];
        for (int i = 0; i < array.length; i++) {
            Integer each = array[i] - min;
            counts[each]++;
        }
        // arrays每个位置存储的次数应该累加之前所有的次数
        int lastNotZeroIndex = 0;
        for (int i = 1; i < counts.length; i++) {
            if (counts[i] != 0) {
                counts[i] += counts[lastNotZeroIndex];
                lastNotZeroIndex = i;
            }
        }
        Integer[] newArray = new Integer[array.length];
        for (int i = array.length - 1; i >= 0; i--) {
            Integer each = array[i];
            int countIndex = each - min;
            // array中元素找到arrays中对应的累积次数-1就是新数组中的
            newArray[--counts[countIndex]] = each;
        }
        for (int i = 0; i < array.length; i++) {
            array[i] = newArray[i];
        }
        // 数组类似普通类型 数组是值传递不是引用传递
        // array = newArray;
    }

    private void teacherSort() {
        // 找出最值
        int max = array[0];
        int min = array[0];
        for (int i = 1; i < array.length; i++) {
            if (array[i] > max) {
                max = array[i];
            }
            if (array[i] < min) {
                min = array[i];
            }
        }

        // 开辟内存空间，存储次数
        int[] counts = new int[max - min + 1];
        // 统计每个整数出现的次数
        for (int i = 0; i < array.length; i++) {
            counts[array[i] - min]++;
        }
        // 累加次数
        for (int i = 1; i < counts.length; i++) {
            counts[i] += counts[i - 1];
        }

        // 从后往前遍历元素，将它放到有序数组中的合适位置
        int[] newArray = new int[array.length];
        for (int i = array.length - 1; i >= 0; i--) {
            newArray[--counts[array[i] - min]] = array[i];
        }

        // 将有序数组赋值到array
        for (int i = 0; i < newArray.length; i++) {
            array[i] = newArray[i];
        }
    }
}
