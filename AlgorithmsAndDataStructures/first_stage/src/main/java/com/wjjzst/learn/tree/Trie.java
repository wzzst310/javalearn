package com.wjjzst.learn.tree;

import com.wjjzst.learn.map.HashMap;

/**
 * @Author: Wjj
 * @Date: 2019/6/2 19:00
 * @desc:
 */
public class Trie<V> {

    private int size;
    private Node<V> root = new Node<>();
    public int size(){
        return size;
    }
    public boolean isEmpty(){
        return size == 0 ;
    }
    public void clear(){
        size = 0;
        root.getChildren().clear();
    }
    public V get(String key){
        Node<V> node = node(key);
        return node == null ? null:node.value;
    }

    private Node<V> node(String key) {
        keyCheck(key);
        Node<V> node = root;
        int len = key.length();
        for (int i = 0; i < len; i++) {
            char c = key.charAt(i);
            node = node.getChildren().get(c);
            if(node == null){
                return null;
            }
        }
        return node.word?node:null;
    }
    private void keyCheck(String key){
        if(key == null || key.length()==0){
            throw new IllegalArgumentException("key must not be empty");
        }
    }


    public boolean contains(String key){
        return node(key) != null;
    }
    public V add(String key,V value){
        keyCheck(key);
        Node<V> node = root;
        int len = key.length();
        for (int i = 0; i < len; i++) {
            char c = key.charAt(i);
            Node<V> childNode = node.getChildren().get(c);
            if(childNode == null){
                childNode = new Node<>();
                node.getChildren().put(c,childNode);
            }
            node = childNode;
        }
        if(node.word){
            V oldValue = node.value;
            node.value = value;
            return oldValue;
        }
        // 新增一个单词
        node.word = true;
        node.value = value;
        size++;
        return null;
    }
    public boolean startsWith(String prefix){
        keyCheck(prefix);
        Node<V> node = root;
        int len = prefix.length();
        for (int i = 0; i < len; i++) {
            char c = prefix.charAt(i);
            node = node.getChildren().get(c);
            if(node == null){
                return false;
            }
        }
        return true;
    }
    private static class Node<V>{
        HashMap<Character,Node<V>> children;
        V value;
        boolean word;
        public HashMap<Character,Node<V>> getChildren(){
            return children == null? children= new HashMap<>():children;

        }
    }
}
