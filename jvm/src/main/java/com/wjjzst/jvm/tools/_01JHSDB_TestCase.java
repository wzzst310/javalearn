package com.wjjzst.jvm.tools;

/**
 * @Author: Wjj
 * @Date: 2020/4/13 10:40 下午
 * @desc: staticObj、instanceObj、localObj存放在哪
 * VM Args:
 * -Xmx10M    //JVM最大可用内存
 * -XX:+UseSerialGC 使用ParNew+Serial Old收集器
 * -XX:-UseCompressedOops
 * -Xmx10M -Xmn10M -XX:+UseSerialGC -XX:-UseCompressedOops
 */
public class _01JHSDB_TestCase {
    static class Test {
        static ObjectHolder staticObj = new ObjectHolder();
        ObjectHolder instanceObj = new ObjectHolder();

        void foo() {
            ObjectHolder localObj = new ObjectHolder();
            System.out.println("done");
        }
    }

    private static class ObjectHolder {
    }

    public static void main(String[] args) {
        Test test = new _01JHSDB_TestCase.Test();
        test.foo();
    }
}
