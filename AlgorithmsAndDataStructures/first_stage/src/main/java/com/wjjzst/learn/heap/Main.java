package com.wjjzst.learn.heap;

import com.wjjzst.learn.common.printer.BinaryTrees;

/**
 * @Author: Wjj
 * @Date: 2019/5/28 0:25
 * @desc:
 */
public class Main {
    public static void main(String[] args) {
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
}
