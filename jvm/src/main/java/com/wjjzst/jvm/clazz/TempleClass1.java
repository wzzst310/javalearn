package com.wjjzst.jvm.clazz;

import java.io.Serializable;

/**
 * @Author: Wjj
 * @Date: 2020/3/21 3:53 下午
 * @desc:
 */
public class TempleClass1 implements Serializable, Comparable<TempleClass1> {
    private int a;
    public static final String b = "abc";
    public TempleClass1(int a) {
        this.a = a;
    }
    void hello() { }
    public String hello2(Integer i) throws Exception {
        return "ddd";
    }
    public String hello3(Integer i) {
        try {
            String d = "ccc";
            if (true) {
                hello();
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            System.out.println("hehe");
        }
        return "";
    }
    protected static void hello4(final float[] arr) { }
    @Override
    public int compareTo(TempleClass1 o) {
        return 0;
    }
}
