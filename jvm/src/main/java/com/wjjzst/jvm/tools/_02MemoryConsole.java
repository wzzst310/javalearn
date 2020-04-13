package com.wjjzst.jvm.tools;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: Wjj
 * @Date: 2020/4/13 11:04 下午
 * @desc: 内存监控
 * VM Args:
 * -Xms100M    //JVM最小可用内存
 * -Xmx100M    //JVM最大可用内存
 * -XX:+UseSerialGC 使用ParNew+Serial Old收集器
 * -Xmx100M -Xms100M -XX:+UseSerialGC
 */
public class _02MemoryConsole {
    /**
     * 内存占位符对象，一个OOMObject大约占64kb
     */
    static class OOMObject {
        public byte[] placeHolder = new byte[64 * 1024];
    }
    public static void fillHeap(int num) throws InterruptedException {
        List<OOMObject> list = new ArrayList<>();
        for (int i = 0; i < num; i++) {
            // 稍作延迟，令监视曲线的变化更加明显
            Thread.sleep(50);
            list.add(new OOMObject());
        }
        System.gc();
    }
    public static void main(String[] args) throws InterruptedException {
        fillHeap(1000);
    }
}
