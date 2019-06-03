package com.wjjzst.learn._05singleton;

public class LazySingleton {
    private LazySingleton() {

    }

    private static LazySingleton instance;

    public static LazySingleton getInstance() {
        if (instance == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            instance = new LazySingleton();
        }
        return instance;
    }
}
