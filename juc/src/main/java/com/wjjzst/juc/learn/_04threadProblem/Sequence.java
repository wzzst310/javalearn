package com.wjjzst.juc.learn._04threadProblem;

/**
 * @Author: Wjj
 * @Date: 2019/5/29 8:38
 * @desc: synchronized就是内置锁(互斥锁)
 * monitorenter
 * monitoroexit
 *
 *
 *
 *
 */
public class Sequence {
    private static int value;

    /**
     * synchronized放在普通方法上,内置锁就是当前类的
     * @return
     */
    /*public synchronized int getNext() {
        return value++;
    }*/
    public int getNext() {
        return value++;
    }

    /**
     * 修饰静态方法,内置锁是当前的class字节码对象
     * Sequence.class
     */
    public static synchronized  int getPrevious(){
        return value--;
    }

    /**
     * 锁住静态代码块
     * @return
     */
    public int xx(){
        // synchronized (this){
        synchronized (Sequence.class){

        }
        return 0;
    }

    public static void main(String[] args) {
        Sequence s = new Sequence();
        /*while (true){
            System.out.println(s.getNext());
        }*/
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    System.out.println(Thread.currentThread().getName() + " " + s.getNext());
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }, "线程一").start();
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    System.out.println(Thread.currentThread().getName() + " " + s.getNext());
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }, "线程二").start();
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    System.out.println(Thread.currentThread().getName() + " " + s.getNext());
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }, "线程三").start();
    }
}
