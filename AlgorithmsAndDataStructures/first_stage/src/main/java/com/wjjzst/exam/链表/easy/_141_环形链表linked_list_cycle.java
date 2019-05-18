package com.wjjzst.exam.链表.easy;

/**
 * https://leetcode-cn.com/problems/linked-list-cycle/
 *
 * @Author: Wjj
 * @Date: 2019-04-19 00:37
 */
public class _141_环形链表linked_list_cycle {
    public class Solution {
        public boolean hasCycle(ListNode head) {
            if (head != null && head.next != null) {
                ListNode fast = head.next.next;
                ListNode slow = head.next;
                while (fast != null && fast.next != null) {
                    if (fast.equals(slow)) {
                        return true;
                    }
                    fast = fast.next.next;
                    slow = slow.next;
                }
            }
            return false;
        }
    }
}
