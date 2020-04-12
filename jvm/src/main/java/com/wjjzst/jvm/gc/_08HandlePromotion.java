package com.wjjzst.jvm.gc;

/**
 * @Author: Wjj
 * @Date: 2020/4/13 12:36 上午
 * @desc: 空间分配担保
 * VM Args:
 * -verbose:gc  在控制台输出GC情况
 * -XX:+PrintGCDetails    //在控制台输出详细的GC情况
 * -Xms20M    //JVM最小可用内存
 * -Xmx20M    //JVM最大可用内存
 * -Xmn10M    //JVM新生代内存大小
 * -XX:SurvivorRatio=8    //新生代中Eden区与一个Survivor区空间比例8:1
 * -XX:+UseSerialGC 使用ParNew+Serial Old收集器
 * -XX:-HandlePromotionFailure=true
 * -verbose:gc -XX:+UseSerialGC -Xms20M -Xmx20M -Xmn10M -XX:+PrintGCDetails -XX:SurvivorRatio=8 XX:-HandlePromotionFailure=false
 */
public class _08HandlePromotion {
    private static final int _1MB = 1024 * 1024;

    static void testHandlePromotion() {
        byte[] allocation1, allocation2, allocation3, allocation4, allocation5, allocation6, allocation7;
        allocation1 = new byte[2 * _1MB];
        allocation2 = new byte[2 * _1MB];
        allocation3 = new byte[2 * _1MB];
        allocation1 = null;
        allocation4 = new byte[2 * _1MB];
        allocation5 = new byte[2 * _1MB];
        allocation6 = new byte[2 * _1MB];
        allocation4 = null;
        allocation5 = null;
        allocation6 = null;
        allocation7 = new byte[2 * _1MB];
    }

    public static void main(String[] args) {
        testHandlePromotion();
    }
}
