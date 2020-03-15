package com.wjjzst.ads.secondStage.learn._01sorting;

public class B_BubbleSort3 extends A_AbstractSort {
    @Override
    protected void sort() {
        mySort();
        // teacherSort();
    }

    private void mySort() {
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

    protected void teacherSort() {
        for (int end = array.length - 1; end > 0; end--) {
            boolean sorted = true;
            int sortedIndex = 1;
            for (int begin = 1; begin <= end; begin++) {
                // if (array[begin] < array[begin - 1]) {
                if (cmp(begin, begin - 1) < 0) {
                    swap(begin, begin - 1);
                    sortedIndex = begin;
                }
            }
            if (sorted) break;
            end = sortedIndex;
        }
    }
}
