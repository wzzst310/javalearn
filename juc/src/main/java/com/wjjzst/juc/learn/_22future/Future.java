package com.wjjzst.juc.learn._22future;


public class Future<T> {
    private T t;
    private boolean down;

    public synchronized void setProduct(T t) {
        if (down) {
            return;
        }
        this.t = t;
        this.down = true;
        notifyAll();
    }

    public synchronized T get() {
        while (!down) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        return t;
    }
}
