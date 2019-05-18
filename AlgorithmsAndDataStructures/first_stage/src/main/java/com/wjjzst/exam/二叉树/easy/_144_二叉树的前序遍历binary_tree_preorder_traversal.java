package com.wjjzst.exam.二叉树.easy;

import com.wjjzst.exam.二叉树.TreeNode;

import java.util.*;

/**
 * https://leetcode-cn.com/problems/binary-tree-preorder-traversal/
 *
 * @Author: Wjj
 * @Date: 2019/5/4 17:01
 */
public class _144_二叉树的前序遍历binary_tree_preorder_traversal {
    class Solution {
        public List<Integer> preorderTraversal(TreeNode root) {
            List<Integer> list = new ArrayList<>();
            if (root != null) {
                Stack<TreeNode> stack = new Stack<>();
                stack.push(root);
                while (!stack.empty()) {
                    TreeNode node = stack.pop();
                    list.add(node.val);
                    if (node.right != null) {
                        stack.push(node.right);
                    }
                    if (node.left != null) {
                        stack.push(node.left);
                    }
                }
            }
            return list;
        }

        // 递归
        public List<Integer> preorderTraversal1(TreeNode root) {
            List<Integer> list = new ArrayList<>();
            preorderTraversal1(root, list);
            return list;
        }

        private void preorderTraversal1(TreeNode node, List<Integer> list) {
            if (node == null) {
                return;
            }
            list.add(node.val);
            preorderTraversal1(node.left, list);
            preorderTraversal1(node.right, list);
        }
    }
}
