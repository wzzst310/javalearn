package com.wjjzst.exam.二叉树.easy;

import com.wjjzst.exam.二叉树.TreeNode;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * https://leetcode-cn.com/problems/binary-tree-inorder-traversal/
 *
 * @Author: Wjj
 * @Date: 2019/5/4 17:02
 */
public class _94_二叉树的中序遍历binary_tree_inorder_traversal {
    class Solution {
        public List<Integer> inorderTraversal(TreeNode root) {
            List<Integer> list = new ArrayList<>();
            if (root != null) {
                Stack<TreeNode> stack = new Stack<>();
                stack.push(root);
                while (!stack.isEmpty()) {
                    TreeNode node = stack.peek();
                    if (node.left == null && node.right == null) {
                        node = stack.pop();
                        TreeNode next = stack.peek();
                        if (next.right == node) {
                            next = stack.pop();
                            list.add(next.val);
                            list.add(node.val);
                        } else {
                            list.add(node.val);
                        }
                    } else {
                        if (node.right != null) {
                            stack.push(node.right);
                        }
                        if (node.left != null) {
                            stack.push(node.left);
                        }
                    }
                }
            }
            return list;
        }
    }

    public List<Integer> inorderTraversal1(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        inorderTraversal1(root, list);
        return list;
    }

    private void inorderTraversal1(TreeNode node, List<Integer> list) {
        if (node == null) {
            return;
        }
        inorderTraversal1(node.left, list);
        list.add(node.val);
        inorderTraversal1(node.right, list);

    }
}

