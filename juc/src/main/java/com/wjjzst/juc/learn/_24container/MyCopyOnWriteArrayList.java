package com.wjjzst.juc.learn._24container;

import java.util.Collections;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * @Author: Wjj
 * @Date: 2019/7/15 0:11
 * @desc:
 * 内部使用了ReentrantLock  //独占排它锁
 * add() lock.lock   先把原来的elements复制一遍 然后修改新的elements 然后再把老的elements指向新的elements 释放锁
 * 类似remove 和  set都类似上述
 * 多线程读的多采用此CopyOnWriteArrayList  写和修改频繁还是Collections.synchronizedList(new List<>);
 */
public class MyCopyOnWriteArrayList {
    public static void main(String[] args) {
        CopyOnWriteArrayList<String> s = new CopyOnWriteArrayList<>();
        s.add("s");


    }
}
