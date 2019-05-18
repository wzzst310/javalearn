package com.wjjzst.exam.二叉树.easy;

import java.util.List;

/**
 *
 * https://leetcode-cn.com/problems/n-ary-tree-preorder-traversal/
 * @Author: Wjj
 * @Date: 2019/5/4 18:29
 */
public class _589_N叉树的前序遍历n_ary_tree_preorder_traversal {
    class Solution {
        public List<Integer> preorder(Node root) {
            return null;
        }
    }
    class Node {
        public int val;
        public List<Node> children;

        public Node() {}

        public Node(int _val,List<Node> _children) {
            val = _val;
            children = _children;
        }
    }
}
