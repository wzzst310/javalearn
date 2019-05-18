package com.wjjzst.exam.二叉树.easy;

import java.util.List;

/**
 *
 * https://leetcode-cn.com/problems/n-ary-tree-postorder-traversal/
 * @Author: Wjj
 * @Date: 2019/5/4 18:30
 */
public class _590_N叉树的后序遍历n_ary_tree_postorder_traversal {
    class Solution {
        public List<Integer> postorder(Node root) {
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
