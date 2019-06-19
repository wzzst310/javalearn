package com.wjjzst.juc.learn._02newThread;

import java.util.Arrays;
import java.util.List;

/**
 * @Author: Wjj
 * @Date: 2019/5/28 23:50
 * @desc: 函数式创建线程池
 */
public class Demo7 {
    public int add(List<Integer> values) {
        return values.parallelStream().mapToInt(a -> a).sum();
    }

    public int add2(List<Integer> values) {
        values.parallelStream().forEach(System.out::println);
        /*   打印结果来看是并行的
        1
        4
        96
        5
        4
        10
        11
        0
        */
        // values.stream().forEach(System.out::println); 非并行
        return 0;
    }

    public static void main(String[] args) {
        List<Integer> values = Arrays.asList(4, 5, 11, 4, 1, 10, 96);
        int res = new Demo7().add2(values);
        System.out.println(res);
    }
}
