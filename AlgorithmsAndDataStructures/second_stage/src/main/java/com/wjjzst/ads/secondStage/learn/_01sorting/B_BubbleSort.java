package com.wjjzst.ads.secondStage.learn._01sorting;

/**
 * @Author: Wjj
 * @Date: 2020/3/18 12:55 上午
 * @desc: 冒泡排序
 */
public class B_BubbleSort<E extends Comparable<E>> extends A_AbstractSort<E> {

    @Override
    protected void sort() {
        teacherSort3();
    }

    private void mySort1() {
        for (int i = array.length - 1; i > 0; i--) {
            for (int j = 0; j < i; j++) {
                if (cmp(j, j + 1) > 0) {
                    swap(j, j + 1);
                }
            }
        }
    }

    protected void teacherSort1() {
        for (int end = array.length - 1; end > 0; end--) {
            for (int begin = 1; begin <= end; begin++) {
                // if (array[begin] < array[begin - 1]) {
                if (cmp(begin, begin - 1) < 0) {
                    swap(begin, begin - 1);
                }
            }
        }
    }

    // 优化一 如果队列原本有序(第一次循环没有交换) 就不需要进行冒泡排序了
    private void mySort2() {
        for (int i = array.length - 1; i > 0; i--) {
            // 如果队列有序了 不用排了
            boolean dontSwap = true;
            for (int j = 0; j < i; j++) {
                if (cmp(j, j + 1) > 0) {
                    swap(j, j + 1);
                    dontSwap = false;
                }
            }
            if (dontSwap) {
                break;
            }
        }
    }

    protected void teacherSort2() {
        for (int end = array.length - 1; end > 0; end--) {
            boolean sorted = true;
            for (int begin = 1; begin <= end; begin++) {
                // if (array[begin] < array[begin - 1]) {
                if (cmp(begin, begin - 1) < 0) {
                    swap(begin, begin - 1);
                    sorted = false;
                }
            }
            if (sorted) break;
        }
    }

    // 优化二 每次冒泡循环结束的位置不是上一次的位置-1 而是最后一次冒泡交换的节点
    private void mySort3() {
        for (int i = array.length - 1; i > 0; i--) {
            int lastSwapIndex = 0;
            for (int j = 0; j < i; j++) {
                if (cmp(j, j + 1) > 0) {
                    swap(j, j + 1);
                    lastSwapIndex = j + 1;
                }
            }
            i = lastSwapIndex;
        }
    }

    protected void teacherSort3() {
        for (int end = array.length - 1; end > 0; end--) {
            boolean sorted = true;
            int sortedIndex = 1;
            for (int begin = 1; begin <= end; begin++) {
                // if (array[begin] < array[begin - 1]) {
                if (cmp(begin, begin - 1) < 0) {
                    swap(begin, begin - 1);
                    sorted = false;
                    sortedIndex = begin;
                }
            }
            if (sorted) break;
            end = sortedIndex;
        }
    }
}
