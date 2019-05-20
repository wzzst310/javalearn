package com.wjjzst.learn.map;

import com.wjjzst.learn.common.model.Person;

/**
 * @Author: Wjj
 * @Date: 2019/5/20 22:13
 * @desc:
 */
public class Main {
    public static void main(String[] args) {
        Map<String, Object> map = new TreeMap<>();
        map.put("1", "hello");
        map.put("2", "3");
        map.put("1", "5");
        map.put("4", "111");
        map.put("5", "222");
        //map.put(new Person("wjj", 25, true), null);
        map.traversal(new Map.Visitor() {
            @Override
            boolean visit(Object key, Object value) {
                System.out.println("key:" + key + " value:" + value);
                return false;
            }
        });
        System.out.println(map.size());
    }
}
