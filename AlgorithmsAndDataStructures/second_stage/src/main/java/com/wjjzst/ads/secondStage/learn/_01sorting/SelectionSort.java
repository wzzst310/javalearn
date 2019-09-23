package com.wjjzst.ads.secondStage.learn._01sorting;

public class SelectionSort extends AbstractSort {
    @Override
    protected void sort() {
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
}
