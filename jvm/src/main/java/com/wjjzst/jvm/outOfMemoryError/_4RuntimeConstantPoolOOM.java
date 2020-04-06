package com.wjjzst.jvm.outOfMemoryError;

import java.util.HashSet;
import java.util.Set;

/**
 * @Author: Wjj
 * @Date: 2020/4/6 10:56 下午
 * @desc: 虚拟机栈和本地方法栈 创建线程导致内存溢出
 * VM Args: -XX:PermSize=6M -XX:MaxPermSize=6M 或者 -XX:MaxMeta-spaceSize=6M  java7以上 字符串常量池在堆中了 这里限制没用
 */
public class _4RuntimeConstantPoolOOM {
    public static void main(String[] args) {
        // 使用Set保持着常量池引用，避免Full GC回收常量池行为
        Set<String> set = new HashSet<>();
        // 在short范围内足以让6MB的PermSize产生OOM了
        short i = 0;
        while (true) {
            set.add(String.valueOf(i++).intern());
        }
    }
}
