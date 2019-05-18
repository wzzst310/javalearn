package com.wjjzst.exam.二叉树.easy;

import java.util.List;

/**
 *
 * https://leetcode-cn.com/problems/maximum-depth-of-n-ary-tree/
 * @Author: Wjj
 * @Date: 2019/5/4 18:31
 */
public class _559_N叉树的最大深度maximum_depth_of_n_ary_tree {
    class Solution {
        public int maxDepth(Node root) {
            return 0;
        }

        class Node {
            public int val;
            public List<Node> children;

            public Node() {
            }

            public Node(int _val, List<Node> _children) {
                val = _val;
                children = _children;
            }
        }
    }
}
