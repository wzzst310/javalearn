package com.wjjzst.learn._10fairLock;

/**
 * @Author: Wjj
 * @Date: 2019/6/16 21:49
 * @desc:
 */
public class MyFairLock {
    /**
     * true 表示 ReentrantLock 的公平锁
     */
    // private ReentrantLock lock = new ReentrantLock(true);
    private FairLock1 lock = new FairLock1();

    public void testFail() {
        try {
            try {
                lock.lock();
            } catch (Exception e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + "获得了锁");
        } finally {
            lock.unlock();
        }
    }

    public static void main(String[] args) {
        MyFairLock fairLock = new MyFairLock();
        Runnable runnable = () -> {
            System.out.println(Thread.currentThread().getName() + "启动");
            fairLock.testFail();
        };
        Thread[] threadArray = new Thread[10];
        for (int i = 0; i < 10; i++) {
            threadArray[i] = new Thread(runnable);
        }
        for (int i = 0; i < 10; i++) {
            threadArray[i].start();
        }
    }
}
