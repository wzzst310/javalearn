package com.wjjzst.juc.learn._02newThread.spring;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

/**
 * @Author: Wjj
 * @Date: 2019/5/28 23:43
 * @desc: spring线程池
 */
@Service
public class DemoService {
    @Async
    public void a() {
        while (true){
            System.out.println("a线程执行");
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    @Async
    public void b() {
        while (true){
            System.out.println("b线程执行");
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
