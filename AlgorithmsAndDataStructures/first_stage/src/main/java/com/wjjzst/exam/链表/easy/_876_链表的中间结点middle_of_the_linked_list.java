package com.wjjzst.exam.链表.easy;

/**
 * https://leetcode-cn.com/problems/middle-of-the-linked-list/
 *
 * @Author: Wjj
 * @Date: 2019-04-19 00:50
 */
public class _876_链表的中间结点middle_of_the_linked_list {
    static class Solution {
        public ListNode middleNode(ListNode head) {
            ListNode temp = head;
            int i = 1;
            while (head.next != null) {
                i++;
                head = head.next;
            }
            for (int j = 0; j < i / 2; j++) {
                temp = temp.next;
            }
            return temp;
        }
        public static void main(String[] args) {
            ListNode node1 = new ListNode(1);
            ListNode node2 = new ListNode(2);
            ListNode node3 = new ListNode(3);
            ListNode node4 = new ListNode(4);
            //ListNode node5 = new ListNode(5);
            node1.next = node2;
            node2.next = node3;
            node3.next = node4;
            //node4.next = node5;
            _876_链表的中间结点middle_of_the_linked_list.Solution solution = new _876_链表的中间结点middle_of_the_linked_list.Solution();
            ListNode listNode = solution.middleNode(node1);
            System.out.println(listNode);
        }
    }
}
