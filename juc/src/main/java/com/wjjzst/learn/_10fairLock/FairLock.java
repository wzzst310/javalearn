package com.wjjzst.learn._10fairLock;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: Wjj
 * @Date: 2019/6/17 0:47
 * @desc:
 */
public class FairLock {
    private boolean isLocked = false;
    private Thread lockingThread = null;
    private List<QueueObject> waitingThreads = new ArrayList<>();

    //加锁操作
    //当前线程获得锁的条件：
    //1、当前锁没有人获取 2、当前线程在链表的头部
    public void lock() throws InterruptedException {
        QueueObject queueObject = new QueueObject();//每次调用一次lock就会新建一个临时对象标记当前线程
        synchronized (waitingThreads) {
            waitingThreads.add(queueObject);
            //不管有没有获得FairLock对象锁，先将监视器压入队列。
        }
        while (true) {
            synchronized (this) {
                if (!isLocked && queueObject.equals(waitingThreads.get(0))) {

                    isLocked = true;
                    lockingThread = Thread.currentThread();
                    return;
                }
            }
            //如果FairLock锁已被占，或者当前线程不在队列头，那么调用监视器的doWait()等待
            try {
                queueObject.doWait();
                //如果一个线程从wait()返回，那么它必然在队列头
            } catch (InterruptedException e) {
                synchronized (waitingThreads) {
                    waitingThreads.remove(queueObject);//出现异常，及时收尾
                }
                throw e;
            }

        }

    }

    public synchronized void unlock() {
        if (this.lockingThread != Thread.currentThread()) {
            throw new IllegalMonitorStateException("Calling thread has not locked this lock");
        }
        isLocked = false;
        lockingThread = null;
        if (!waitingThreads.isEmpty()) {
            waitingThreads.get(0).doNotify();
            synchronized (waitingThreads) {
                waitingThreads.remove(0);
            }
        }
    }

    public static void main(String[] args) {
        FairLock fairLock = new FairLock();
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    fairLock.lock();
                    System.out.println("线程1执行...");
                    fairLock.unlock();
                } catch (Exception e) {

                }
            }
        }).start();

        try {
            Thread.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    fairLock.lock();
                    System.out.println("线程2执行...");
                    fairLock.unlock();
                } catch (Exception e) {

                }
            }
        }).start();

        try {
            Thread.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    fairLock.lock();
                    System.out.println("线程3执行...");
                    fairLock.unlock();
                } catch (Exception e) {

                }
            }
        }).start();
    }

    private static class QueueObject {
        private boolean isNotified = false;

        public synchronized void doWait() throws InterruptedException {
        /*
        使用while()形式封装监听器的wait()和notify()的好处多多：
        1、防止wait()错过信号
        2、防止假唤醒(spurious wakeups)
        3、多个线程等待相同的信号
        */
            while (!isNotified) {
                this.wait();
            }
            isNotified = false;
        }

        public synchronized void doNotify() {
            this.isNotified = true;
            this.notify();
        }

        @Override
        public boolean equals(Object o) {
            return this == o;
        }
    }
}
