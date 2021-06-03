package com.wjjzst.ads.second_stage.learn._01sorting.cmp;

import com.wjjzst.ads.first_stage.learn.heap.BinaryHeap;

import java.util.Comparator;

public class D_HeapSort<E extends Comparable<E>> extends A_AbstractSort<E> {
    private int heapSize;

    @Override
    protected void sort() {
        // mySort();
        teacherSort();
    }

    private void mySort() {
        BinaryHeap<Integer> heap = new BinaryHeap<>((Integer[]) array, new Comparator<Integer>() {
            @Override
            public int compare(Integer i1, Integer i2) {
                return i2 - i1;
            }
        });
    }

    private void teacherSort() {
        // 原地建最大堆
        heapSize = array.length;
        for (int i = (heapSize >> 1) - 1; i >= 0; i--) {
            siftDown(i);
        }
        // 每次取出堆的最大值 即array[0] 放到数组后面 然后剩下的重建堆 拿到数组
        while (heapSize > 1) {
            // 交换堆顶元素和尾部元素
            swap(0, --heapSize);

            // 对0位置进行siftDown（恢复堆的性质）
            siftDown(0);
        }
    }

    private void siftDown(int index) {
        E element = array[index];

        int half = heapSize >> 1;
        while (index < half) { // index必须是非叶子节点
            // 默认是左边跟父节点比
            int childIndex = (index << 1) + 1;
            E child = array[childIndex];

            int rightIndex = childIndex + 1;
            // 右子节点比左子节点大
            if (rightIndex < heapSize &&
                    cmp(array[rightIndex], child) > 0) {
                child = array[childIndex = rightIndex];
            }

            // 大于等于子节点
            if (cmp(element, child) >= 0) break;

            array[index] = child;
            index = childIndex;
        }
        array[index] = element;
    }
}
