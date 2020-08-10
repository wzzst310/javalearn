package com.wjjzst.base.hashmap;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * @author: Wjj
 * @create: 2020/7/8 2:50 下午
 * @Description
 */
public class ModCount {
    public static void main(String[] args) {
        Map map = new HashMap<>();
        map.put("1", "1");
        map.put("2", "2");
        map.put("3", "3");
        /*for (Object o : map.keySet()) {
            if ("3 ".equals(o)) {
                map.remove(o);
            }
        }*/
        Iterator iterator = map.keySet().iterator();
        while(iterator.hasNext()){
            Object o = iterator.next();
            if ("1".equals(o)) {
                map.remove(o);
            }
        }
    }

}
