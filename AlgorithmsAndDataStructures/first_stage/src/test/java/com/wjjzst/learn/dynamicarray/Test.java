package com.wjjzst.learn.dynamicarray;

import com.wjjzst.learn.common.model.Person;

import java.util.Map;
import java.util.TreeMap;

public class Test {
    public static void main(String[] args) {
        Map map = new TreeMap();
        map.put(new Person("wjj",15,true),"nihao");
        map.put(11,23);
        map.put("haha",25);
        System.out.println(map.size());
    }
    private static void  test1(){
        System.out.println(5>>1);
    }
}
