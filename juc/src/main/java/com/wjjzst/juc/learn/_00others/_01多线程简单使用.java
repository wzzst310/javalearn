package com.wjjzst.juc.learn._00others;

import com.alibaba.fastjson.JSON;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.*;

/**
 * @Author: Wjj
 * @Date: 2020/4/23 1:12 上午
 * @desc:
 */
public class _01多线程简单使用 {
    public static void main(String[] args) {
        try {
            ExecutorService service = Executors.newFixedThreadPool(5);
            CountDownLatch countDownLatch = new CountDownLatch(3);
            // 每个Future里面包裹的是每个线程的返回值   可以是不一样的
            Future<List<Integer>> task1Future = service.submit(() -> {
                List<Integer> result1 = new ArrayList<>();
                try {
                    System.out.println("任务一开始");
                    result1 = task1();
                } catch (Exception e) {
                    System.out.println("任务一异常" + JSON.toJSONString(e));
                } finally {
                    countDownLatch.countDown();
                }
                return result1;
            });
            Future<List<Integer>> task2Future = service.submit(() -> {
                List<Integer> result2 = new ArrayList<>();
                try {
                    System.out.println("任务二开始");
                    result2 = task2();
                } catch (Exception e) {
                    System.out.println("任务二异常" + JSON.toJSONString(e));
                } finally {
                    countDownLatch.countDown();
                }
                return result2;
            });
            Future<List<Integer>> task3Future = service.submit(() -> {
                List<Integer> result3 = new ArrayList<>();
                try {
                    System.out.println("任务三开始");
                    result3 = task3();
                } catch (Exception e) {
                    System.out.println("任务三异常" + JSON.toJSONString(e));
                } finally {
                    countDownLatch.countDown();
                }
                return result3;
            });
            service.shutdown();
            // 最多阻塞1min  1min内没干完的就认为失败
            countDownLatch.await(1, TimeUnit.MINUTES);
            // 最终结果的包装 上述多线程返回值汇总
            List<List<Integer>> result = new ArrayList<>();
            if (task1Future != null) {
                List<Integer> task1Result = task1Future.get(20, TimeUnit.SECONDS);
                result.add(task1Result);
            }
            if (task2Future != null) {
                List<Integer> task2Result = task2Future.get(20, TimeUnit.SECONDS);
                result.add(task2Result);
            }
            if (task3Future != null) {
                List<Integer> task3Result = task3Future.get(20, TimeUnit.SECONDS);
                result.add(task3Result);
            }
            // result就是最终的结果
            System.out.println(JSON.toJSONString(result));
        } catch (Exception e) {
            System.out.println("线程异常" + JSON.toJSONString(e));
            e.printStackTrace();
        }
    }

    // 模拟每个线程花费的时间
    private static List<Integer> task1() {
        try {
            TimeUnit.SECONDS.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("任务一完成");
        return Arrays.asList(1, 2, 3);
    }

    private static List<Integer> task2() {
        try {
            TimeUnit.SECONDS.sleep(15);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("任务二完成");
        return Arrays.asList(4, 5, 6);
    }

    private static List<Integer> task3() {
        try {
            TimeUnit.SECONDS.sleep(5);
            // TimeUnit.SECONDS.sleep(125); 等待时间125超时
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("任务三完成");
        return Arrays.asList(7, 8, 9);
    }
}
