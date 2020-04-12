package com.wjjzst.jvm.gc;

/**
 * @Author: Wjj
 * @Date: 2020/4/7 11:03 下午
 * @desc:
 */
public class _1ReferenceCountingGC {
    public Object instance = null;
    private static final int _1MB = 1024 * 1024;
    /**
     * 这个成员属性的唯一意义就是占点内存，以便能在GC日志中看清楚是否有回收过
     */
    private byte[] bigSize = new byte[2 * _1MB];

    public static void testGC() {
        _1ReferenceCountingGC objA = new _1ReferenceCountingGC();
        _1ReferenceCountingGC objB = new _1ReferenceCountingGC();
        objA.instance = objB;
        objB.instance = objA;
        objA = null;
        objB = null;
        // 假设在这行发生GC，objA和objB是否能被回收
        System.gc();
    }

    public static void main(String[] args) {
        while (true) {
            testGC();
        }
    }
}
