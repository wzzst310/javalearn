package com.wjjzst.learn._04threadProblem;

/**
 * @Author: Wjj
 * @Date: 2019/5/29 8:38
 * @desc:
 */
public class Sequence {
    private int value;

    /*public synchronized int getNext() {
        return value++;
    }*/
    public int getNext() {
        return value++;
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
