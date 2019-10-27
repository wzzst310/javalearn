package com.wjjzst.juc.learn._02newThread;

import java.util.Timer;
import java.util.TimerTask;

/**
 * @Author: Wjj
 * @Date: 2019/5/28 23:09
 * @desc: 定时任务实现多线程
 */
public class Demo5 {
    public static void main(String[] args) {
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                // 实现定时任务
                System.out.println("timetask is run + " + System.currentTimeMillis());

            }
        }, 0, 1000); //延迟0秒 每隔1秒执行一次
    }
}
