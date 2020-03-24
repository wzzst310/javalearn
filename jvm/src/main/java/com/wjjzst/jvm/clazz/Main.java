package com.wjjzst.jvm.clazz;

/**
 * @Author: Wjj
 * @Date: 2020/3/23 10:16 下午
 * @desc:
 */
public class Main {
    public static void main(String[] args) throws ClassNotFoundException, IllegalAccessException, InstantiationException {
        MyClassLoader myClassLoader =
                new MyClassLoader("/Users/wjj/IdeaProjects/javalearn/jvm/src/main/java/com/wjjzst/jvm/clazz",
                        "MyClassLoader","com.wjjzst.jvm.clazz.");
        Class c = myClassLoader.loadClass("TempleClass2");
        System.out.println("ClassLoader:" + c.getClassLoader());
        Object instance = c.newInstance();
    }
}
