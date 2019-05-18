package com.wjjzst.exam.链表.easy;

/**
 * https://leetcode-cn.com/problems/delete-node-in-a-linked-list/
 *
 * @Author: Wjj
 * @Date: 2019-04-19 00:25
 */
public class _237_删除链表中的节点delete_node_in_a_linked_list {
    class Solution {
        public void deleteNode(ListNode node) {
            node.val = node.next.val;
            node.next = node.next.next;
        }
    }
}
