package com.wjjzst.jvm.gc;

/**
 * @Author: Wjj
 * @Date: 2020/4/13 12:30 上午
 * @desc: 如果Survivor空间中相同年龄所有对象总和大于Survivor空间的一半，年龄大于或等于该年龄的对象就可以直接进入老年代 不用考虑threshold
 * VM Args:
 * -verbose:gc  在控制台输出GC情况
 * -XX:+PrintGCDetails    //在控制台输出详细的GC情况
 * -Xms20M    //JVM最小可用内存
 * -Xmx20M    //JVM最大可用内存
 * -Xmn10M    //JVM新生代内存大小
 * -XX:SurvivorRatio=8    //新生代中Eden区与一个Survivor区空间比例8:1
 * -XX:+UseSerialGC 使用ParNew+Serial Old收集器
 * -XX:MaxTenuringThreshold=15 对象进入老年代年龄阈值参数
 * -verbose:gc -XX:+UseSerialGC -Xms20M -Xmx20M -Xmn10M -XX:+PrintGCDetails -XX:SurvivorRatio=8 -XX:MaxTenuringThreshold=15 -XX:+PrintTenuringDistribution
 */
public class _07TenuringThresholdUseless {
    private static final int _1MB = 1024 * 1024;

    public static void testPretenureSizeThreshold() {
        byte[] allocation1, allocation2, allocation3, allocation4;
        allocation1 = new byte[_1MB / 4];
        allocation2 = new byte[_1MB / 4];
        allocation3 = new byte[4 * _1MB];
        allocation4 = new byte[4 * _1MB];
        allocation4 = null;
        allocation4 = new byte[4 * _1MB];
    }
    public static void main(String[] args) {
        testPretenureSizeThreshold();
    }
}
