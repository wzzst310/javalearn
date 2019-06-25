package com.wjjzst.juc.learn._16join;

/**
 * @Author: Wjj
 * @Date: 2019/6/25 20:47
 * @desc: A线程调用B线程的join方法 实际上是A去调用B的wait方法 而A自己进入阻塞状态
 */
public class Demo {
    public void a(Thread joinThread) {
        System.out.println("方法a执行了...");
        joinThread.start();
        try {
            joinThread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("a方法执行完毕...");

    }

    public void b() {
        System.out.println("加塞线程开始...");
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("加塞线程执行完毕...");
    }

    public static void main(String[] args) {
        Demo demo = new Demo();
        Thread joinThread = new Thread(new Runnable() {
            @Override
            public void run() {
                demo.b();
            }
        });
        // joinThread.setDaemon(true);
        new Thread(new Runnable() {
            @Override
            public void run() {
                demo.a(joinThread);
            }
        }).start();
    }
}
