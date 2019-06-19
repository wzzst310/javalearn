package com.wjjzst.learn._11readWriteLock;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @Author: Wjj
 * @Date: 2019/6/17 1:23
 * @desc: 锁降级  写锁-->读锁   同样还有锁升级 读锁-->写锁
 */
public class Demo2 {
    private Map<String, Object> map = new HashMap<>();
    private ReadWriteLock rwl = new ReentrantReadWriteLock();
    private Lock r = rwl.readLock();
    private Lock w = rwl.writeLock();
    //线程之间可见 并且不需要JVM优化
    private volatile boolean isUpdate;

    public void readWrite() {
        r.lock();
        if (isUpdate) {
            r.unlock();
            w.lock();
            map.put("xxx", "xxx");
            // 写锁释放前先加一个读锁
            // 性能更好 确保改了之后至少让一个读线程访问到
            // 在写锁没有释放的时候获取到读锁，在释放写锁
            r.lock();
            w.unlock();
        }
        Object xxx = map.get("xxx");
        System.out.println(xxx);
        r.unlock();
    }

    public Object get(String key) {
        r.lock();

        System.out.println(Thread.currentThread().getName() + "读操作在执行...");
        try {
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return map.get(key);
        } finally {
            r.unlock();

            System.out.println(Thread.currentThread().getName() + "读操作执行完毕...");
        }
    }

    public Object put(String key, Object value) {
        w.lock();
        System.out.println(Thread.currentThread().getName() + "写操作在执行...");
        try {
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return map.put(key, value);
        } finally {
            w.unlock();
            System.out.println(Thread.currentThread().getName() + "写操作执行完毕...");
        }
    }
}
