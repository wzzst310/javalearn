package com.wjjzst.jvm.outOfMemoryError;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: Wjj
 * @Date: 2020/4/6 1:07 下午
 * @desc: Java堆内存溢出
 * VM Args: -Xms20m -Xmx20m -XX:+HeapDumpOnOutOfMemoryError
 */
public class _1HeapOOM {
    static class OOMObject {}
    public static void main(String[] args) {
        List<OOMObject> list = new ArrayList<>();
        while (true) {
            list.add(new OOMObject());
        }
    }
}
