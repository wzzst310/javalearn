package com.wjjzst.ads.secondStage.learn._01sorting;

import com.wjjzst.ads.secondStage.learn._00common.Integers;

import java.util.Arrays;

public class SortMain {

    public static void main(String[] args) {
        Integer[] array = Integers.random(10000, 1, 20000);

        testSorts(array,
                new BubbleSort1(),
                new BubbleSort2(),
                new BubbleSort3(),
                new SelectionSort(),
                new HeapSort()
        );
    }

    static void testSorts(Integer[] array, AbstractSort... sorts) {
        for (AbstractSort sort : sorts) {
            sort.sort(Integers.copy(array));
        }

        Arrays.sort(sorts);

        for (AbstractSort sort : sorts) {
            System.out.println(sort);
        }
    }

}
