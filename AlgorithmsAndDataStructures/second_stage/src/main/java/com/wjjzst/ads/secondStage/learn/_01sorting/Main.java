package com.wjjzst.ads.secondStage.learn._01sorting;

/**
 * @Author: Wjj
 * @Date: 2019/10/13 20:22
 * @desc:
 */
public class Main<E> {
    public  void a(int a){
        System.out.println("a");
    }
    public  void a(E a){
        System.out.println("obejct");
    }

    public static void main(String[] args) {
        Main<Integer> m = new Main<>();
        m.a(100);
    }
}
