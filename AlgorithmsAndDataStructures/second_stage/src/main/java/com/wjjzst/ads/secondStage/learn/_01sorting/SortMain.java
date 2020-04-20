package com.wjjzst.ads.secondStage.learn._01sorting;

import com.alibaba.fastjson.JSON;
import com.wjjzst.ads.secondStage.learn._00common.Asserts;
import com.wjjzst.ads.secondStage.learn._00common.Integers;

import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.List;

public class SortMain {

    public static void main(String[] args) throws IOException {
        // Integer[] array = Integers.random(1000000, 1, 2000000);
        Integer[] array = getFileArray();
        testSorts(array,
                // new B_BubbleSort(),
                // new C_SelectionSort(),
                new D_HeapSort(),
                // new E_InsertSort(),
                new F_MergeSort(),
                new G_QuickSort(),
                new H_ShellSort()
        );
    }

    private static Integer[] getFileArray() throws IOException {
        InputStream inputStream = SortMain.class.getClassLoader().getResource("array.json").openStream();
        byte[] bytes = new byte[inputStream.available()];
        inputStream.read(bytes);
        String arrayStr = new String(bytes);
        List<Integer> integers = JSON.parseArray(arrayStr, Integer.class);
        return integers.toArray(new Integer[integers.size()]);
    }

    static void testSorts(Integer[] array, A_AbstractSort... sorts) {
        for (A_AbstractSort sort : sorts) {
            Integer[] newArray = Integers.copy(array);
            sort.sort(newArray);
            System.out.println(JSON.toJSONString(newArray));
            Asserts.test(Integers.isAscOrder(newArray));
        }

        Arrays.sort(sorts);

        for (A_AbstractSort sort : sorts) {
            System.out.println(sort);
        }
    }

}
