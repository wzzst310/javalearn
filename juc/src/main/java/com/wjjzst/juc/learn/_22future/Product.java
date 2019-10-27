package com.wjjzst.juc.learn._22future;

import lombok.Data;

@Data
public class Product {
    private int id;
    private String name;

    public Product(int id, String name) {
        System.out.println("开始生产 " + name);
        try {
            Thread.sleep(4000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        this.id = id;
        this.name = name;
        System.out.println(name + "生产关闭");
    }
}
