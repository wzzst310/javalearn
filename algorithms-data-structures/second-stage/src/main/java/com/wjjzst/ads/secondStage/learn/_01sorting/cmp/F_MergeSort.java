package com.wjjzst.ads.secondstage.learn._01sorting.cmp;

/**
 * @Author: Wjj
 * @Date: 2020/4/19 2:44 上午
 * @desc: 合并排序
 */
public class F_MergeSort<E extends Comparable<E>> extends A_AbstractSort<E> {
    private E[] leftArray;

    @Override
    protected void sort() {
        leftArray = (E[]) new Comparable[array.length >> 1];
        sort(0, array.length);
    }

    private void sort(int begin, int end) {
        if (end - begin < 2) {
            return;
        }
        int mid = (end + begin) >> 1;
        sort(begin, mid);
        sort(mid, end);
        merge(begin, mid, end);
    }

    private void merge(int begin, int mid, int end) {
        int li = 0, le = mid - begin;
        int ri = mid, re = end;
        int ai = begin;
        for (int i = 0; i < le; i++) {
            leftArray[i] = array[begin + i];
        }
        while (li < le) {
            if (ri < re && (cmp(array[ri], leftArray[li])) < 0) {
                array[ai++] = array[ri++];
                // swap(ai++, ri++);
            } else {
                array[ai++] = leftArray[li++];
            }
        }
    }
}
