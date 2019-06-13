package com.wjjzst.learn._9lock1ByMyself;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.AbstractQueuedSynchronizer;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

/**
 * @Author: Wjj
 * @Date: 2019/6/13 8:41
 * @desc:
 */
public class MyLock2 implements Lock {

    private Helper helper = new Helper();

    @Override
    public void lock() {
        helper.acquire(1);
    }

    @Override
    public void lockInterruptibly() throws InterruptedException {
        helper.acquireInterruptibly(1);
    }

    @Override
    public boolean tryLock() {
        return helper.tryAcquire(1);
    }

    @Override
    public boolean tryLock(long time, TimeUnit unit) throws InterruptedException {
        helper.tryAcquireNanos(1, unit.toNanos(time));
        return false;
    }

    @Override
    public void unlock() {
        helper.release(1);
    }

    @Override
    public Condition newCondition() {
        return helper.newCondition();
    }

    private class Helper extends AbstractQueuedSynchronizer {
        @Override
        protected boolean tryAcquire(int arg) {
            //如果第一个线程进来,可以拿到锁,因此我们可以返回true
            /*如果第二个线程进来,拿不到锁,因此我们可以返回false
            未必 有种特例
            如果当前进来的线程和当前保存的线程是同一个线程则可以拿到锁 并更新状态值*/
            //如何判断是第一个线程进来还是其他线程进来
            int state = getState();
            Thread t = Thread.currentThread();
            System.out.println(state);
            if (state == 0) {
                // 如果设置成功肯定是第一个进来访问的
                if (compareAndSetState(0, arg)) {
                    //  设置当前线程
                    setExclusiveOwnerThread(Thread.currentThread());
                    return true;
                }
            } else if (getExclusiveOwnerThread() == t) { //  同一个线程重入了
                setState(state + 1);
                return true;

            }
            return false;
        }

        @Override
        protected boolean tryRelease(int arg) {
            // 锁的获取和释放肯定是一一对应的,那么调用此方法的线程一定是当前线程
            if (Thread.currentThread() != getExclusiveOwnerThread()) {
                throw new RuntimeException();
            }
            int state = getState() - arg;
            boolean flag = false;
            if (state == 0) {
                setExclusiveOwnerThread(null);
                flag = true;
            }
            setState(state);
            return flag;
        }

        Condition newCondition() {
            return new ConditionObject();
        }
    }
}
