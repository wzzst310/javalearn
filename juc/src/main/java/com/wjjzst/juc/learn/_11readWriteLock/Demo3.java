package com.wjjzst.juc.learn._11readWriteLock;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import java.util.concurrent.locks.StampedLock;

/**
 * @Author: Wjj
 * @Date: 2019/6/17 1:23
 * @desc: 锁降级  写锁-->读锁   同样还有锁升级 读锁-->写锁
 * 解决ReentrantReadWriteLock 读写锁  解决写锁和读锁互斥的问题 (大部分情况下写锁获取不到锁 写锁饥饿问题)
 * 读锁不会阻塞写锁   读的时候发生了写操作就应该重新去读    乐观锁(不互斥) 悲观锁(互斥)
 */
public class Demo3 {
    private int balance;

    private StampedLock lock = new StampedLock();

    public void conditionReadWrite(int value) {
        //寿险判断balance的值是否符合更新的条件
        long stamp = lock.readLock();
        while (balance > 0) {
            long writeStamp = lock.tryConvertToWriteLock(stamp);
            if (writeStamp != 0) {//成功转换成为写锁
                stamp = writeStamp;
                balance += value;
                break;
            } else {
                // 没有转换成写锁,这里需要首先释放读锁,再拿到写锁
                lock.unlockRead(stamp);
                // 获取写锁
                stamp = lock.writeLock();
            }
        }
        lock.unlock(stamp);
    }

    public int optimisticRead() {
        long stamp = lock.tryOptimisticRead(); //先尝试乐观锁
        int c = balance;
        // 可能会出现写操作,因此要进行判断
        if (!lock.validate(stamp)) {  // 用了乐观锁就需要校验
            //要重新读取
            long readStamp = lock.readLock(); // 乐观锁失败后就尝试普通的读锁(悲观锁)
            c = balance;
            stamp = readStamp;
        }
        ///
        lock.unlockRead(stamp);
        return c;
    }

    public void read() {
        long stamp = lock.readLock();
        int c = balance;
        lock.unlockRead(stamp);
    }

    public void write(int value) {
        long stamp = lock.readLock();
        balance += value;
        lock.unlockWrite(stamp);
    }
}
