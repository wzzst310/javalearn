package com.wjjzst.learn.dynamicarray;


public class ArrayListTest {

    public static void main(String[] args) {
        ArrayList<Object> list = new ArrayList<>();
        for (int i = 0; i <= 10; i++) {
            list.add(i);
        }
        System.out.println(list);
        //System.out.println(list.get(9));
        //System.out.println(list.remove(9));
        //list.clear();
        list.add(null);
        System.out.println(list.indexOf(null));
        java.util.ArrayList list1 = new java.util.ArrayList();
        list1.add(0);
        System.out.println(list.indexOf(0));
    }
}
