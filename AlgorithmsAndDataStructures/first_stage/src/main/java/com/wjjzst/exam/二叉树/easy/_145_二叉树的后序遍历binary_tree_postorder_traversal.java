package com.wjjzst.exam.二叉树.easy;

import com.wjjzst.exam.二叉树.TreeNode;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * https://leetcode-cn.com/problems/binary-tree-postorder-traversal/
 *
 * @Author: Wjj
 * @Date: 2019/5/4 17:03
 */
public class _145_二叉树的后序遍历binary_tree_postorder_traversal {
    public List<Integer> postorderTraversal(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        if (root != null) {
            Stack<TreeNode> stack = new Stack<>();
            TreeNode pre = null;
            stack.push(root);
            while (!stack.isEmpty()) {
                TreeNode node = stack.peek();
                if ((node.left == null && node.right == null) || (pre != null && (pre == node.left || pre == node.right))) {
                    list.add(node.val);
                    pre = node;
                    stack.pop();
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

    public List<Integer> postorderTraversal1(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        postorderTraversal1(root, list);
        return list;
    }

    private void postorderTraversal1(TreeNode node, List<Integer> list) {
        if (node == null) {
            return;
        }
        if (node.left != null) {
            postorderTraversal1(node.left, list);
        }
        if (node.right != null) {
            postorderTraversal1(node.right, list);
        }
        list.add(node.val);
    }
}
