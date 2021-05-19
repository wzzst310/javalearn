package com.wjjzst.juc.learn.mockgo;

import com.alibaba.fastjson.JSON;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/**
 * @author: wangzhe
 * @create: 2021/5/19 8:21 下午
 * @Description
 */
public class ChanBase<T> {
    private T message;
    private boolean empty = true;
    public synchronized T take() {
        while (empty) {
            try {
                wait();
            } catch (InterruptedException e) {
            }
        }
        try {
            TimeUnit.SECONDS.sleep(1L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        empty = true;
        notifyAll();
        return message;
    }

    public synchronized void put(T message) {
        while (!empty) {
            try {
                wait();
            } catch (InterruptedException e) {
            }
        }
        try {
            TimeUnit.SECONDS.sleep(1L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        empty = false;
        this.message = message;
        notifyAll();
    }

    private static ChanBase<Integer> chan = new ChanBase<>();

    public static void main(String[] args) {
        Thread consumer1 = new Thread(() -> consume("consumer1"));
        Thread consumer2 = new Thread(() -> consume("consumer2"));
        Thread provider1 = new Thread(() -> provide("provider1"));
        Thread provider2 = new Thread(() -> provide("provider2"));
        consumer1.start();
        consumer2.start();
        provider1.start();
        provider2.start();
        CountDownLatch countDownLatch = new CountDownLatch(1);
        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private static void provide(String name) {
        while (true) {
            int data = Double.valueOf(Math.random() * 100).intValue();
            chan.put(data);
            System.out.println(name + ": " + Thread.currentThread().getName() + "当前存储了的数据:" + JSON.toJSONString(data));
        }
    }

    private static void consume(String name) {
        while (true) {
            Integer take = chan.take();
            System.out.println(name + ": " + Thread.currentThread().getName() + "当前获得了的数据:" + JSON.toJSONString(take));
        }
    }


    public static void main1(String[] args) {
        Thread consumer1 = new Thread(() -> {
            while (true) {
                Integer take = chan.take();
                System.out.println("consumer1: " + Thread.currentThread().getName() + "当前获得了的数据:" + JSON.toJSONString(take));
            }
        });
        Thread consumer2 = new Thread(() -> {
            while (true) {
                Integer take = chan.take();
                System.out.println("consumer2: " + Thread.currentThread().getName() + "当前获得了的数据:" + JSON.toJSONString(take));
            }
        });
        Thread provider1 = new Thread(() -> {
            while (true) {
                int data = Double.valueOf(Math.random() * 100).intValue();
                chan.put(data);
                System.out.println("provider1: " + Thread.currentThread().getName() + "当前存储了的数据:" + JSON.toJSONString(data));
            }
        });
        Thread provider2 = new Thread(() -> {
            while (true) {
                int data = Double.valueOf(Math.random() * 100).intValue();
                chan.put(data);
                System.out.println("provider1: " + Thread.currentThread().getName() + "当前存储了的数据:" + JSON.toJSONString(data));
            }
        });
        consumer1.start();
        consumer2.start();
        provider1.start();
        provider2.start();
        CountDownLatch countDownLatch = new CountDownLatch(1);
        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
