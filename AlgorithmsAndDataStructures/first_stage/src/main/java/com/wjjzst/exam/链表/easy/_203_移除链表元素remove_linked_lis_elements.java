package com.wjjzst.exam.链表.easy;

/**
 * https://leetcode-cn.com/problems/remove-linked-list-elements/
 *
 * @Author: Wjj
 * @Date: 2019-04-19 00:41
 */
public class _203_移除链表元素remove_linked_lis_elements {
    static class Solution {
        public ListNode removeElements(ListNode head, int val) {
            // 人为添加头拼在第一个前面
            ListNode node = new ListNode(-1);
            node.next = head;
            ListNode cur = node;
            while (cur.next != null) {
                if (cur.next.val == val) {
                    cur.next = cur.next.next;
                } else {
                    cur = cur.next;
                }
            }
            return node.next;
        }

        public ListNode removeElements1(ListNode head, int val) {
            //ListNode node = new ListNode(-1);
            //node.next = head;
            if (head == null) {
                return null;
            }
            if (head.next == null) {
                if (head.val == val) {
                    return null;
                }
                return head;
            }
            ListNode cur = head;
            while (cur.next != null) {
                if (cur.next.val == val) {
                    cur.next = cur.next.next;
                } else {
                    cur = cur.next;
                }
            }
            return head.val == val ? head.next : head;
        }
        //递归
        public ListNode removeElements2(ListNode head, int val) {
            if (head == null) {
                return null;
            }
            head.next = removeElements2(head.next, val);
            return head.val == val ? head.next : head;
        }

        public static void main(String[] args) {
            ListNode node0 = new ListNode(6);
            ListNode node1 = new ListNode(1);
            ListNode node2 = new ListNode(2);
            ListNode node3 = new ListNode(6);
            ListNode node4 = new ListNode(3);
            ListNode node5 = new ListNode(4);
            ListNode node6 = new ListNode(5);
            ListNode node7 = new ListNode(6);
            node0.next = node1;
            node1.next = node2;
            node2.next = node3;
            node3.next = node4;
            node4.next = node5;
            node5.next = node6;
            node6.next = node7;
            Solution solution = new Solution();
            //ListNode listNode = solution.removeElements2(node0, 6);
            //System.out.println(listNode.val);
            System.out.println(node0.val);
        }
    }
}
