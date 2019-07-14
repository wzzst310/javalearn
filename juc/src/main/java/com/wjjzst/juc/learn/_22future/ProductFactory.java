package com.wjjzst.juc.learn._22future;

import java.util.Random;

public class ProductFactory {
    public Future createProduct(String name) {
        Future f = new Future();
        System.out.println("下单成功,你可以去上班了");
        // 生产产品
        new Thread(new Runnable() {
            @Override
            public void run() {
                Product p = new Product(new Random().nextInt(), name);
                f.setProduct(p);
            }
        }).start();
        return f;
    }
}
