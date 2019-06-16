package com.wjjzst.learn._10fairLock;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @Author: Wjj
 * @Date: 2019/6/16 19:27
 * @desc: 公平锁 就是按照先后顺序拿到锁
 */
public class FairLock1 {
    private boolean isLocked = false;
    private Thread lockBy = null;
    private Queue<QueueObject> waitingThreads = new LinkedList<>();

    public void lock() {
        QueueObject q = new QueueObject();
        /*boolean isLockForThisThread = true;
        synchronized (waitingThreads) {
            waitingThreads.offer(q);
        }

        while (isLockForThisThread) {
            synchronized (this) {
                isLockForThisThread = isLocked || waitingThreads.peek().equals(q);
                if (!isLockForThisThread) {
                    isLocked = true;
                    waitingThreads.poll();
                    lockBy = Thread.currentThread();
                    return;
                }
            }
        }*/
        synchronized (waitingThreads) {
            waitingThreads.offer(q);
        }
        while (true) {
            synchronized (this) {
                if (!isLocked && q.equals(waitingThreads.peek())) {
                    isLocked = true;
                    waitingThreads.poll();
                    lockBy = Thread.currentThread();
                    return;
                }
            }
            try {
                q.doWait();
            } catch (Exception e) {
                synchronized (this) {
                    waitingThreads.remove(q);
                }
                e.printStackTrace();
            }
        }
    }

    public synchronized void unlock() {
        if (this.lockBy != Thread.currentThread()) {
            throw new IllegalMonitorStateException("Calling thread has not locked this lock");
        }
        isLocked = false;
        lockBy = null;
        if (waitingThreads.size() > 0) {
            waitingThreads.poll().doNotify();
        }

    }

    private static class QueueObject {
        private boolean isNotified = false;

        public synchronized void doWait() {

            while (!isNotified) {
                try {
                    this.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            this.isNotified = false;

        }

        public synchronized void doNotify() {
            this.isNotified = true;
            this.notify();
        }

        public boolean equals(Object o) {
            return this == o;
        }
    }
}

