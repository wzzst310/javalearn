package com.wjjzst.jvm.gc;

/**
 * @Author: Wjj
 * @Date: 2020/4/12 3:45 下午
 * @desc: 大对象直接进入老年代
 * VM Args:
 * -verbose:gc  在控制台输出GC情况
 * -XX:+PrintGCDetails    //在控制台输出详细的GC情况
 * -Xms20M    //JVM最小可用内存
 * -Xmx20M    //JVM最大可用内存
 * -Xmn10M    //JVM新生代内存大小
 * -XX:SurvivorRatio=8    //新生代中Eden区与一个Survivor区空间比例8:1
 * -XX:+UseParNewGC 使用ParNew+Serial Old收集器
 * -XX:PretenureSizeThreshold参数(仅对Serial和ParNew新生代收集器有效),指定大于此值得对象直接进入老年代分配
 * -verbose:gc -XX:+UseParNewGC -Xms20M -Xmx20M -Xmn10M -XX:+PrintGCDetails -XX:SurvivorRatio=8 -XX:PretenureSizeThreshold=3145728  3MB
 */
public class _05BigObj {
    private static final int _1MB = 1024 * 1024;
    public static void testPretenureSizeThreshold() {
        byte[] allocation;
        allocation = new byte[4 * _1MB]; //直接分配在老年代中
    }
    public static void main(String[] args) {
        testPretenureSizeThreshold();
    }
}
