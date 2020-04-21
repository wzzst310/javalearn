package com.wjjzst.ads.secondStage.learn._01sorting.cmp;

/**
 * @Author: Wjj
 * @Date: 2020/3/18 12:55 上午
 * @desc: 选择排序
 */
public class C_SelectionSort<E extends Comparable<E>> extends A_AbstractSort<E> {
    @Override
    protected void sort() {
        // mySort();
        teacherSort();
    }

    private void mySort() {
        for (int i = array.length - 1; i > 0; i--) {
            int maxIndex = 0;
            for (int j = 0; j < i; j++) {
                if (cmp(j, j + 1) > 0) {
                    maxIndex = j;
                }
            }
            swap(maxIndex, i);
        }
    }

    protected void teacherSort() {
        for (int end = array.length - 1; end > 0; end--) {
            int maxIndex = 0;
            for (int begin = 1; begin <= end; begin++) {
                // if (array[maxIndex] <= array[begin]) {
                if (cmp(maxIndex, begin) <= 0) {
                    maxIndex = begin;
                }
            }
            swap(maxIndex, end);
        }
    }
}
