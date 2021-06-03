package com.wjjzst.ads.firststage.learn.map;


/**
 * @Author: Wjj
 * @Date: 2019/5/20 0:31
 * @desc:
 */
public interface Map<K, V> {
    int size();

    boolean isEmpty();

    void clear();

    V put(K key, V value);

    V get(K key);

    V remove(K key);

    boolean containsKey(K key);

    boolean containsValue(V value);


    void traversal(Visitor<K, V> visitor);

    abstract class Visitor<K, V> {

        boolean stop;

        protected abstract boolean visit(K key, V value);
    }
    /*@FunctionalInterface
    interface Visitor<K, V> {

        boolean stop = false;

        boolean visit(K key, V value);
    }*/

}
