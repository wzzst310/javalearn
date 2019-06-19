package com.wjjzst.learn._11readWriteLock;

/**
 * @Author: Wjj
 * @Date: 2019/6/17 1:30
 * @desc:
 */
public class Main {
    public static void main(String[] args) {
        Demo d = new Demo();
        // writeWrite(d);
        // writeRead(d);
        readRead(d);
    }

    // 读读不互斥
    private static void readRead(Demo d) {
        d.put("key1", "value1");
        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println(d.get("key1"));
            }
        }).start();
        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println(d.get("key1"));
            }
        }).start();
        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println(d.get("key1"));
            }
        }).start();
        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println(d.get("key1"));
            }
        }).start();
    }

    //写读互斥
    private static void writeRead(Demo d) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                d.put("key1", "value1");
            }
        }).start();
        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println(d.get("key1"));
            }
        }).start();
    }

    // 写写互斥
    private static void writeWrite(Demo d) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                d.put("key1", "value1");
            }
        }).start();
        new Thread(new Runnable() {
            @Override
            public void run() {
                d.put("key2", "value3");
            }
        }).start();
        new Thread(new Runnable() {
            @Override
            public void run() {
                d.put("key3", "value3");
            }
        }).start();
    }

}
