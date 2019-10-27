package com.wjjzst.juc.learn._10fairLock;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;

/**
 * @Author: Wjj
 * @Date: 2019/6/17 1:17
 * @desc:
 */
public class Main1 {

    public static void main(String[] args) {
//      Lock lock = new ReentrantLock(true);
        CustomLock lock = new CustomLock();
        new Thread(new Producer(lock)).start();
        new Thread(new Consumer(lock)).start();
    }
}

class Producer implements Runnable {
    private Lock lock;
    private CustomLock customLock;

    public Producer(Lock lock) {
        this.lock = lock;
    }

    public Producer(CustomLock lock) {
        this.customLock = lock;
    }

    @Override
    public void run() {
        while (!Thread.currentThread().isInterrupted()) {
//          lock.lock();
            customLock.lock();
            System.out.println("Producer before");
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("Producer after");
//          lock.unlock();
            customLock.unlock();
        }
    }
}

class Consumer implements Runnable {
    private Lock lock;
    private CustomLock customLock;

    public Consumer(Lock lock) {
        this.lock = lock;
    }

    public Consumer(CustomLock lock) {
        this.customLock = lock;
    }

    @Override
    public void run() {
        while (!Thread.currentThread().isInterrupted()) {
//          lock.lock();
            customLock.lock();
            System.out.println("Consumer before");
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("Consumer after");
//          lock.unlock();
            customLock.unlock();
        }
    }
}

class CustomLock {
    private boolean isLocked;

    public synchronized void lock() {
        while (isLocked) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        isLocked = true;
    }

    public synchronized void unlock() {
        if (isLocked) {
            isLocked = false;
            notify();
        }
    }
}