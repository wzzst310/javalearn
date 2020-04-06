package com.wjjzst.jvm.clazz;

/**
 * @Author: Wjj
 * @Date: 2020/3/31 11:04 下午
 * @desc: 双锁检测单例
 */
public class VolatileSingleton {
    private volatile static VolatileSingleton instance;

    public static VolatileSingleton getInstance() {
        if (instance == null) {
            synchronized (VolatileSingleton.class) {
                if (instance == null) {
                    instance = new VolatileSingleton();
                }
            }
        }
        return instance;
    }

    public static void main(String[] args) {
        VolatileSingleton.getInstance();
    }
}
