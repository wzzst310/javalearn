package com.wjjzst.jvm.clazz;

/**
 * @Author: Wjj
 * @Date: 2020/3/31 10:43 下午
 * @desc:
 */
public class VolatileTest2 {
    volatile boolean shutdownRequested;

    // 只要通过修改shutdownRequested 可以让并发条件下的多线程同时结束
    public void shutdown(){
        shutdownRequested = true;
    }
    public void doWork(){
        while (!shutdownRequested){
            // 代码的业务逻辑
        }
    }
}
