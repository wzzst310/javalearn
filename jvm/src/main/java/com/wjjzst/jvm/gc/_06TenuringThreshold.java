package com.wjjzst.jvm.gc;

/**
 * @Author: Wjj
 * @Date: 2020/4/12 3:45 下午
 * @desc: 长期存活对象将进入老年代
 * VM Args:
 * -verbose:gc  在控制台输出GC情况
 * -XX:+PrintGCDetails    //在控制台输出详细的GC情况
 * -Xms20M    //JVM最小可用内存
 * -Xmx20M    //JVM最大可用内存
 * -Xmn10M    //JVM新生代内存大小
 * -XX:SurvivorRatio=8    //新生代中Eden区与一个Survivor区空间比例8:1
 * -XX:+UseSerialGC 使用ParNew+Serial Old收集器
 * -XX:MaxTenuringThreshold=1 对象进入老年代年龄阈值参数
 * -XX:TargetSurvivorRatio=90 设置此值之后能解决高版本jvm allocation3年龄没等15就到直接进老年代问题
 * 一个计算期望存活大小Desired survivor size的参数
 * 计算公式： (survivor_capacity * TargetSurvivorRatio) / 100 * sizeof(a pointer)：survivor_capacity（一个survivor space的大小）乘以TargetSurvivorRatio，
 * 表明所有age的survivor space对象的大小如果超过Desired survivor size，则重新计算threshold，以age和MaxTenuringThreshold的最小值为准，否则以MaxTenuringThreshold为准
 * -verbose:gc -XX:+UseSerialGC -Xms20M -Xmx20M -Xmn10M -XX:+PrintGCDetails -XX:SurvivorRatio=8 -XX:MaxTenuringThreshold=1 -XX:TargetSurvivorRatio=90 -XX:+PrintTenuringDistribution
 */
public class _06TenuringThreshold {
    private static final int _1MB = 1024 * 1024;
    public static void testPretenureSizeThreshold() {
        byte[] allocation1, allocation2, allocation3;
        allocation1 = new byte[_1MB / 4]; //什么时候进老年代取决于-XX:MaxTenuringThreshold
        allocation2 = new byte[4 * _1MB];
        allocation3 = new byte[4 * _1MB];
        allocation3 = null;
        allocation3 = new byte[4 * _1MB];
    }
    public static void main(String[] args) {
        testPretenureSizeThreshold();
    }
}
