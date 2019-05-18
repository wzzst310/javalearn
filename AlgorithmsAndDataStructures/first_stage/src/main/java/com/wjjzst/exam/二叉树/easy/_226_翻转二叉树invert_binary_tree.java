package com.wjjzst.exam.二叉树.easy;

import com.wjjzst.exam.二叉树.TreeNode;

import java.util.LinkedList;
import java.util.Queue;

/**
 * https://leetcode-cn.com/problems/invert-binary-tree/
 *
 * @Author: Wjj
 * @Date: 2019/5/4 9:30
 */
public class _226_翻转二叉树invert_binary_tree {
    class Solution {
        /**
         * 层序遍历
         */
        public TreeNode invertTree(TreeNode root) {
            if (root == null) {
                return root;
            }
            Queue<TreeNode> queue = new LinkedList<>();
            queue.offer(root);
            while (!queue.isEmpty()) {
                TreeNode node = queue.poll();
                TreeNode tmp = node.left;
                node.left = node.right;
                node.right = tmp;
                if (node.left != null) {
                    queue.offer(node.left);
                }
                if (node.right != null) {
                    queue.offer(node.right);
                }
            }
            return root;
        }

        /**
         * 前序遍历
         */
        public TreeNode invertTree2(TreeNode root) {
            if (root == null) {
                return root;
            }
            TreeNode tmp = root.left;
            root.left = root.right;
            root.right = tmp;
            invertTree(root.left);
            invertTree(root.right);
            return root;
        }

        /**
         * 后序遍历
         */
        public TreeNode invertTree3(TreeNode root) {
            if (root == null) {
                return root;
            }
            invertTree(root.left);
            invertTree(root.right);
            TreeNode tmp = root.left;
            root.left = root.right;
            root.right = tmp;
            return root;
        }

        /**
         * 中序遍历
         */
        public TreeNode invertTree4(TreeNode root) {
            if (root == null) {
                return root;
            }
            invertTree(root.left);
            TreeNode tmp = root.left;
            root.left = root.right;
            root.right = tmp;
            invertTree(root.left); // 此时的node.left = 原本的node.right
            return root;
        }
    }
}
