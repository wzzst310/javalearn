package com.wjjzst.learn._01threadStatus;

/**
 * @Author: Wjj
 * @Date: 2019/5/28 1:09
 * @desc:
 */
public class NewThread implements Runnable {
    @Override
    public synchronized void run() {
        while (true) {
            try {
                // Thread.sleep(1000);  // 进入阻塞状态
                wait();   // 进入就绪状态
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("自定义线程启动线程...");
        }
    }

    public static void main(String[] args) {
        NewThread n = new NewThread();
        Thread thread = new Thread(n); // 初始化状态 被new出来的时候
        thread.start();
        while (true) {
            synchronized (n) {
                System.out.println("主线程启动线程...");
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                n.notifyAll(); //唤醒子线程
            }
        }
    }
}
