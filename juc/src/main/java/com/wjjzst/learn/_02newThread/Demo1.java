package com.wjjzst.learn._02newThread;

/**
 * @Author: Wjj
 * @Date: 2019/5/28 1:54
 * @desc: 继承Thread
 */
public class Demo1 extends Thread {
    public Demo1(String name) {
        super(name);
    }

    @Override
    public void run() {
        /*while (true) {
            System.out.println(getName() + "线程执行了...");
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }*/
        while (!interrupted()) {
            System.out.println(getName() + "线程执行了...");
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        Demo1 d1 = new Demo1("A");
        Demo1 d2 = new Demo1("B");
        // d1.setDaemon(true); // 设置为守护线程
        // d2.setDaemon(true);
        // 先设置再启动才有用  主线程结束之后 守护线程也执行完
        d1.start();
        d2.start();
        /*try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {

        }*/
        d1.interrupt();
        //d1.stop();
    }
}
