package com.wjjzst.ads.firstStage.learn.heap;

import com.wjjzst.ads.firstStage.learn.common.printer.BinaryTrees;

import java.util.Comparator;

/**
 * @Author: Wjj
 * @Date: 2019/5/28 0:25
 * @desc:
 */
public class Main {
    public static void main(String[] args) {
        test1();
        //test2();
    }

    private static void test2() {
        BinaryHeap<Integer> heap = new BinaryHeap<>();
        heap.add(9);
        heap.add(79);
        heap.add(2);
        heap.add(16);
        heap.add(27);
        heap.add(33);
        heap.add(6);
        BinaryTrees.print(heap);
        System.out.println();
        heap.remove();
        BinaryTrees.print(heap);
        System.out.println();
        heap.remove();
        BinaryTrees.print(heap);
        System.out.println();
        heap.replace(1);
        BinaryTrees.print(heap);
    }

    public static void test1() {
        // 新建一个最小堆
        BinaryHeap<Integer> heap = new BinaryHeap<>(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o2 - o1;
                // return o1 - o2;
            }
        });

        // 找出前top个元素
        int top = 5;
        int[] elements = new int[]{68, 83, 97, 89, 50, 62, 9, 15, 44, 38, 57, 98, 21, 64, 85, 63, 87, 13, 42, 90, 4, 40, 2, 36, 17, 48, 5, 19, 99, 12};
        // int top = elements.length;
        for (int i = 0; i < elements.length; i++) {
            if (i < top) {
                heap.add(elements[i]);//先将前top个元素放到最小堆里面
            } else if (elements[i] - heap.get() > 0) {  // 新添加的元素必须要大于最小的元素才能添加进堆
                heap.replace(elements[i]);
            }
        }
        BinaryTrees.print(heap);
    }
}
