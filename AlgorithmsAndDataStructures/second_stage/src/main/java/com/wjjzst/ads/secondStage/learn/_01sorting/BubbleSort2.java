package com.wjjzst.ads.secondStage.learn._01sorting;

public class BubbleSort2 extends AbstractSort {
    @Override
    protected void sort() {
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
}
