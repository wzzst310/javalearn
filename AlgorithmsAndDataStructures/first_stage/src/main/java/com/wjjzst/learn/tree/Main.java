package com.wjjzst.learn.tree;


import com.wjjzst.learn.tree.old.BinarySearchTree;
import com.wjjzst.learn.tree.printer.BinaryTrees;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

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

    public static void main(String[] args) {
        test1();
        //List<Integer> list = generateList();
        //testAVL(list);
        //testRB(list);


    }
}
