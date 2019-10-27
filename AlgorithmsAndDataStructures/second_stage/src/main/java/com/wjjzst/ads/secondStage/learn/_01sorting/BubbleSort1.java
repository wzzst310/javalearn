package com.wjjzst.ads.secondStage.learn._01sorting;

public class BubbleSort1 extends AbstractSort {

    @Override
    protected void sort() {
        mySort();
    }

    private void mySort() {
        for (int i = array.length - 1; i > 0; i--) {
            for (int j = 0; j < i; j++) {
                if (cmp(j, j + 1) > 0) {
                    swap(j, j + 1);
                }
            }
        }
    }

    protected void teacherSort() {
        for (int end = array.length - 1; end > 0; end--) {
            for (int begin = 1; begin <= end; begin++) {
                // if (array[begin] < array[begin - 1]) {
                if (cmp(begin, begin - 1) < 0) {
                    swap(begin, begin - 1);
                }
            }
        }
    }
}
