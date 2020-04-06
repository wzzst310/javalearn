package com.wjjzst.jvm.outOfMemoryError;

/**
 * @Author: Wjj
 * @Date: 2020/4/6 11:14 下午
 * @desc: intern()
 */
public class _5RuntimeConstantPoolInternOOM {
    public static void main(String[] args) {
        String str1 = new StringBuilder("计算机").append("软件").toString();
        System.out.println(str1.intern() == str1);
        String str2 = new StringBuilder("ja").append("va").toString();
        System.out.println(str2.intern() == str2);
    }
}
