package com.wjjzst.jvm.gc;

/**
 * @Author: Wjj
 * @Date: 2020/4/12 3:45 下午
 * @desc: 对象优先在Eden分配
 * VM Args:
 * -verbose:gc  在控制台输出GC情况
 * -XX:+PrintGCDetails    //在控制台输出详细的GC情况
 * -Xms20M    //JVM最小可用内存
 * -Xmx20M    //JVM最大可用内存
 * -Xmn10M    //JVM新生代内存大小
 * -XX:SurvivorRatio=8    //新生代中Eden区与一个Survivor区空间比例8:1
 * -XX:+UseConcMarkSweepGC 使用CMS+ParNew+Serial Old收集器
 * -XX:+UseSerialGC 使用Serial+Serial Old收集器
 * -XX:+UseParNewGC 使用ParNew+Serial Old收集器
 * -XX:+UseParallelGC 使用Parallel Scavenge+Serial Old收集器  jdk9之前默认
 * -XX:+UseParallelOldGC 使用Parallel Scavenge+Parallel Old收集器
 * -XX:+UseG1GC 使用G1收集器 JDK9之后的默认值
 * -verbose:gc -XX:+UseConcMarkSweepGC -Xms20M -Xmx20M -Xmn10M -XX:+PrintGCDetails -XX:SurvivorRatio=8
 * -Xloggc:/Users/wjj/IdeaProjects/javalearn/jvm/src/main/resources/gc1.log 打印GC日志到文件
 */
public class _04PriorEden {
    private static final int _1MB = 1024 * 1024;
    public static void testAllocation() {
        byte[] allocation1, allocation2, allocation3, allocation4;
        allocation1 = new byte[2 * _1MB];
        allocation2 = new byte[2 * _1MB];
        allocation3 = new byte[2 * _1MB];
        allocation4 = new byte[5 * _1MB]; //出现一次Minor GC
    }
    public static void main(String[] args) {
        testAllocation();
    }
}
