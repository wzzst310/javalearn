package com.wjjzst.juc.learn._18countDownLatch;


import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class Demo {
    protected int[] nums;

    public Demo(int line) {
        this.nums = new int[line];
    }

    public void calc(String line, int index) {
        String[] nus = line.split(",");
        int total = 0;
        for (String s : nus) {
            total += Integer.parseInt(s);
        }
        nums[index] = total;
        System.out.println(Thread.currentThread().getName() + " 执行计算任务... " + line + " 结果为: " + total);
    }

    public void sum() {
        System.out.println("汇总线程开始执行...");
        int total = 0;
        for (int i = 0; i < nums.length; i++) {
            total += nums[i];
        }
        System.out.println("最终结果为:" + total);
    }

    public static void main(String[] args) {
        List<String> contents = readFile();
        int lineCount = contents.size();
        Demo d = new Demo(lineCount);
        for (int i = 0; i < lineCount; i++) {
            final int j = i;
            new Thread(new Runnable() {
                @Override
                public void run() {
                    d.calc(contents.get(j), j);
                }
            }).start();
        }
        while (Thread.activeCount() > 2) {
            // System.out.println(Thread.activeCount());
        }
        d.sum();
    }

    protected static List<String> readFile() {

        List<String> contents = new ArrayList<>();
        String line = null;
        try {
            BufferedReader br = new BufferedReader(new FileReader("/home/wjj/IdeaProjects/javalearn/juc/src/main/java/com/wjjzst/juc/learn/_18countDownLatch/nums.txt"));
            while ((line = br.readLine()) != null) {
                contents.add(line);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return contents;
    }
}
