package com.wjjzst.juc.learn._21exchanger;

import java.util.concurrent.Exchanger;

public class Demo {
    public void a(Exchanger<String> exchanger) {
        System.out.println("a方法执行...");
        try {
            System.out.println("a线程正在抓取数据...");
            Thread.sleep(2000);
            System.out.println("a线程抓取数据结束...");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        String res = "12345";
        try {
            System.out.println("等待对比结果...");
            exchanger.exchange(res);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void b(Exchanger<String> exchanger) {
        System.out.println("b方法执行...");
        try {
            System.out.println("b线程正在抓取数据...");
            Thread.sleep(4000);
            System.out.println("b线程抓取数据结束...");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        String res = "12345";
        try {
            String value = exchanger.exchange(res);
            System.out.println("开始进行比对");
            System.out.println("比对结果为：" + value.equals(res));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        Demo d = new Demo();
         Exchanger<String> exchanger = new Exchanger<>();
         new Thread(new Runnable() {
             @Override
             public void run() {
                 d.a(exchanger);
             }
         }).start();
        new Thread(new Runnable() {
            @Override
            public void run() {
                d.b(exchanger);
            }
        }).start();
    }
}
