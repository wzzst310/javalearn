package com.wjjzst.learn._12threadNotify;

/**
 * @Author: Wjj
 * @Date: 2019/6/19 12:11
 * @desc: wait和notify一定你得在synchronized同步代码块或者同步方法中
 */
public class Demo2 {
    private volatile int singal; // 线程之间可见

    public int getSingal() {
        return singal;
    }

    public void setSingal(int singal) {
        this.singal = singal;
    }

    public static void main(String[] args) {
        // singal = 1  线程一执行
        // singal = 2  线程二执行
        Demo2 d = new Demo2();
        new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized (d) {
                    System.out.println("修改状态的线程执行...");
                    try {
                        Thread.sleep(2000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    d.setSingal(1);
                    System.out.println("状态值修改成功");
                    d.notify(); // 一定要放到同步代码块中
                    // notify(); // 不加d. 就是当前Thread类的notify和wait 不是被锁资源的wait和notify
                }
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized (d) {
                    // 等待signal为1开始执行,否则不执行
                    while (d.getSingal() != 1) {
                        try {
                            d.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    System.out.println("模拟代码的执行...");
                }
            }
        }).start();
    }
}
