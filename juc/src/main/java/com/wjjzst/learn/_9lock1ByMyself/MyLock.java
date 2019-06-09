package com.wjjzst.learn._9lock1ByMyself;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

public class MyLock implements Lock {
    private boolean isLocked;
    private Thread lockBy;
    private int lockCount;

    @Override
    public synchronized void lock() {
        Thread currentThread = Thread.currentThread();
        while (isLocked && currentThread != lockBy) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        isLocked = true;
        lockBy = currentThread;
        lockCount++;
    }

    @Override
    public void lockInterruptibly() throws InterruptedException {

    }

    @Override
    public boolean tryLock() {
        return false;
    }

    @Override
    public boolean tryLock(long time, TimeUnit unit) throws InterruptedException {
        return false;
    }

    @Override
    public synchronized void unlock() {
        if (lockBy == Thread.currentThread()) {
            System.out.println("localCount: " + lockCount);
            lockCount--;
            if (lockCount == 0) {
                notify();
                isLocked = false;
            }
        }
        isLocked = false;
        notify();
    }

    @Override
    public Condition newCondition() {
        return null;
    }
}
