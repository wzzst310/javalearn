package com.wjjzst.juc.learn._05singleton;


public class LazySingleton {
    private LazySingleton() {

    }

    private static volatile LazySingleton instance; //volatile减少虚拟机的优化不会出现指令重排序

    /**
     * 偏向锁    不会提升性能
     * 轻量级锁  对象头标记 (自旋 一直等待 浪费cpu资源)
     *
     * @return
     */
    public static LazySingleton getInstance() {
        /*if (instance == null) { //自旋while(true)
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            instance = new LazySingleton();
        }*/
        if (instance == null) {
            synchronized (LazySingleton.class) { //仅仅锁住它不够
                if (instance == null) {  //双重检查加锁
                    instance = new LazySingleton(); // 指令重排序
                    // 申请一块内存空间 //1
                    // 在这块空间里实例化对象 //2
                    // instance的引用指向这块空间地址 //3
                }
            }
        }
        return instance;
    }
}
