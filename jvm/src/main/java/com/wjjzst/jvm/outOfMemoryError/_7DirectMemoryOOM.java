package com.wjjzst.jvm.outOfMemoryError;

import sun.misc.Unsafe;

import java.lang.reflect.Field;

/**
 * @Author: Wjj
 * @Date: 2020/4/7 12:34 上午
 * @desc: 通过Unsafe实例进行内存分配
 * VM Args: -Xmx20M -XX:MaxDirectMemorySize=10M
 */
public class _7DirectMemoryOOM {
    private static final int _1MB = 1024 * 1024 * 1024;
    public static void main(String[] args) throws Exception {
        Field unsafeField = Unsafe.class.getDeclaredFields()[0];
        unsafeField.setAccessible(true);
        Unsafe unsafe = (Unsafe) unsafeField.get(null);
        while (true) {
            // 申请堆外内存
            unsafe.allocateMemory(_1MB);
        }
    }
}
