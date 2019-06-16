package com.wjjzst.learn._11ReadWriteLock;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @Author: Wjj
 * @Date: 2019/6/17 1:23
 * @desc:
 */
public class Demo {
    private Map<String, Object> map = new HashMap<>();
    private ReadWriteLock rwl = new ReentrantReadWriteLock();
    private Lock r = rwl.readLock();
    private Lock w = rwl.writeLock();


    public Object get(String key) {
        r.lock();

        System.out.println(Thread.currentThread().getName()+"读操作在执行...");
        try {
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return map.get(key);
        } finally {
            r.unlock();

            System.out.println(Thread.currentThread().getName()+"读操作执行完毕...");
        }
    }

    public Object put(String key, Object value) {
        w.lock();
        System.out.println(Thread.currentThread().getName()+"写操作在执行...");
        try {
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return map.put(key, value);
        } finally {
            w.unlock();
            System.out.println(Thread.currentThread().getName()+"写操作执行完毕...");
        }
    }
}
