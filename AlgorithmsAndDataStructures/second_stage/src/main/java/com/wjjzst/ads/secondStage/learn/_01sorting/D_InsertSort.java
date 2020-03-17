package com.wjjzst.ads.secondStage.learn._01sorting;

/**
 * @Author: Wjj
 * @Date: 2020/3/18 12:55 上午
 * @desc:
 */
public class D_InsertSort<E extends Comparable<E>> extends A_AbstractSort<E> {
    @Override
    protected void sort() {
        for (int begin = 1; begin < array.length; begin++) {
            int cur = begin;
            while (cur > 0 && cmp(cur, cur - 1) < 0) {
                swap(cur, cur - 1);
                cur--;
            }
        }
    }
}
