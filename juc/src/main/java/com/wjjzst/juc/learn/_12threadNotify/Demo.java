package com.wjjzst.juc.learn._12threadNotify;

/**
 * @Author: Wjj
 * @Date: 2019/6/19 12:11
 * @desc:
 */
public class Demo {
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
        Demo d = new Demo();
        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("修改状态的线程执行...");
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                d.setSingal(1);
                System.out.println("状态值修改成功");
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                // 等待signal为1开始执行,否则不执行
                while (d.getSingal() != 1) {
                    // 当信号为1的时候,执行代码
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                System.out.println("模拟代码的执行...");
            }
        }).start();
    }
}
