package com.wjjzst.ads.second_stage.learn._01sorting.cmp;

import java.util.LinkedList;
import java.util.List;

/**
 * @Author: Wjj
 * @Date: 2020/4/21 12:04 上午
 * @desc: 希尔排序 可看做是对插入排序的优化 主要就是每次排序之后 逆序对变少了
 */
public class H_ShellSort<E extends Comparable<E>> extends A_AbstractSort<E> {
    private List<Integer> stepSequence;

    @Override
    protected void sort() {
        stepSequence = shellStepSequence();
        for (Integer step : stepSequence) {
            sort(step);
        }
    }

    // 步长就说明有多少列 step列
    private void sort(Integer step) {
        // 对每列进行排序  每次对列进行排序之后逆序对减少了
        for (int col = 0; col < step; col++) {
            // 依赖插入排序  每次间隔step比较判断 交换
            for (int begin = col + step; begin < array.length; begin += step) {
                int cur = begin;
                while (cur > col && cmp(cur, cur - step) < 0) {
                    swap(cur, cur - step);
                    cur -= step;
                }
            }
        }
    }

    // Shell本人给出的步长队列
    private List<Integer> shellStepSequence() {
        List<Integer> stepSequence = new LinkedList<>();
        int step = array.length;
        while ((step >>= 1) > 0) {
            stepSequence.add(step);
        }
        return stepSequence;
    }
}
