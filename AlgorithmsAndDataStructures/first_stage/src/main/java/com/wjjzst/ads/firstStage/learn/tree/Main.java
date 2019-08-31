package com.wjjzst.ads.firstStage.learn.tree;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.wjjzst.ads.firstStage.learn.common.printer.BinaryTrees;
import com.wjjzst.ads.firstStage.learn.map.Asserts;

public class Main {


    static void test1() {
        BSTree<Integer> bst = new BSTree<>();
        List<Integer> list = generateList();
        bst.add(list);
        BinaryTrees.print(bst);
        System.out.println("\n");
        //bst.preorderTraversal();
        //bst.inorderTraversal();
        //bst.postorderTraversal();
        //bst.levelOrderTraversal();
        /*bst.levelOrder(new BSTree.Visitor<Integer>() {
            @Override
            public void visit(Integer element) {
                System.out.println(element);
            }
        });*/
        //System.out.println(bst);
        // System.out.println(bst.height());
        // System.out.println(bst.height2());
        // System.out.println(bst.isComplete());
        //list.clear();
        bst.preorder(new BinaryTree.Visitor<Integer>() {
            @Override
            public boolean visit(Integer element) {
                System.out.println(element);
                return 43 == element;
            }
        });
        //System.out.println("前序:"+list);
        //list.clear();
        /*bst.inorder(new BSTree.Visitor<Integer>() {
            @Override
            public boolean visit(Integer element) {
                System.out.println(element);
                return 43 == element;
            }
        });*/
        //System.out.println("中序:"+list);
        /* list.clear();
        bst.postorder(new BSTree.Visitor<Integer>() {
            @Override
            public boolean visit(Integer element) {
                // list.add(element);
                System.out.println(element);
                return 43 == element;
            }
        });*/
        /*List<Integer> newList = new ArrayList<>();
        bst.levelOrder(new BSTree.Visitor<Integer>() {
            @Override
            public boolean visit(Integer element) {
                newList.add(element);
                return 54 == element;
            }
        });
        System.out.println("层序:" + newList);*/
//        bst.remove(13);
//        BinaryTrees.print(bst);
//        System.out.println("\n");
//        bst.remove(68);
//        BinaryTrees.print(bst);
//        System.out.println("\n");

    }

    static List<Integer> generateList() {
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            list.add((int) (Math.random() * 100));
        }
//        list = Arrays.asList(7, 4, 2, 1, 3, 5, 9, 8, 11, 10, 12);
//        list = Arrays.asList(7, 4, 9, 2, 5);
        list = Arrays.asList(13, 68, 89, 54, 78, 93, 84, 43, 16, 11, 71, 61, 55, 43, 91, 18, 32, 68, 90, 51);
        //list = Arrays.asList(13, 11, 68, 54, 89, 43, 61, 78, 93, 16, 51, 55, 71, 84, 91, 18, 90, 32);
        System.out.println(list);
        return list;
    }

    static void testAVL(List<Integer> list) {
        AVLTree<Integer> avl = new AVLTree<>();
        avl.add(list);
        BinaryTrees.print(avl);
        for (Integer i : list) {
            System.out.println("\n");
            System.out.println("【" + i + "】");
            avl.remove(i);
            BinaryTrees.print(avl);
            System.out.println("\n");
        }
    }

    static void testRB(List<Integer> list) {
        RBTree<Integer> rb = new RBTree<>();
        // rb.add(list);
        for (Integer i : list) {
            System.out.println("\n");
            System.out.println("【" + "+" + i + "】");
            rb.add(i);
            BinaryTrees.print(rb);
            System.out.println("\n");
        }
        for (Integer i : list) {
            System.out.println("\n");
            System.out.println("【" + "-" + i + "】");
            rb.remove(i);
            BinaryTrees.print(rb);
            System.out.println("\n");
        }
    }

    static void testTrie() {
        Trie<Integer> trie = new Trie<>();
        trie.add("cat", 1);
        trie.add("dog", 2);
        trie.add("catalog", 3);
        trie.add("cast", 4);
        trie.add("小码哥", 5);
        Asserts.test(trie.size() == 5);
        Asserts.test(trie.startsWith("do"));
        Asserts.test(trie.startsWith("c"));
        Asserts.test(trie.startsWith("ca"));
        Asserts.test(trie.startsWith("cat"));
        Asserts.test(trie.startsWith("cata"));
        Asserts.test(!trie.startsWith("hehe"));
        Asserts.test(trie.get("小码哥") == 5);
        Asserts.test(trie.remove("cat") == 1);
        Asserts.test(trie.remove("catalog") == 3);
        Asserts.test(trie.remove("cast") == 4);
        Asserts.test(trie.size() == 2);
        Asserts.test(trie.startsWith("do"));
        Asserts.test(!trie.startsWith("c"));
    }

    static void testOrder() {
        // 创建BST
        Integer data[] = new Integer[]{
                7, 4, 9, 2, 5, 8, 11
        };
        BSTree<Integer> bst = new BSTree<>();
        for (int i = 0; i < data.length; i++) {
            bst.add(data[i]);
        }
        // 树状打印
        BinaryTrees.println(bst);
        // 遍历器
        StringBuilder sb = new StringBuilder();
        BSTree.Visitor<Integer> visitor = new BSTree.Visitor<Integer>() {
            @Override
            public boolean visit(Integer element) {
                sb.append(element).append(" ");
                //return false;
                return element == 8;
            }
        };
        // 遍历
        sb.delete(0, sb.length());
        bst.preorder(visitor);
        //Asserts.test(sb.toString().equals("7 4 2 5 9 8 11 "));
        Asserts.test(sb.toString().equals("7 4 2 5 9 8 "));

        sb.delete(0, sb.length());
        bst.inorder(visitor);
        //Asserts.test(sb.toString().equals("2 4 5 7 8 9 11 "));
        Asserts.test(sb.toString().equals("2 4 5 7 8 "));

        sb.delete(0, sb.length());
        bst.postorder(visitor);
        //Asserts.test(sb.toString().equals("2 5 4 8 11 9 7 "));
        Asserts.test(sb.toString().equals("2 5 4 8 "));

    }

    public static void main(String[] args) {
        //test1();
        //List<Integer> list = generateList();
        //testAVL(list);
        //testRB(list);
        //testTrie();
        testOrder();


    }
}
