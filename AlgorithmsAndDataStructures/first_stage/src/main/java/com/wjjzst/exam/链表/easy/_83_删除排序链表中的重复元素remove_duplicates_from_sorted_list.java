package com.wjjzst.exam.链表.easy;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * https://leetcode-cn.com/problems/remove-duplicates-from-sorted-list/
 *
 * @Author: Wjj
 * @Date: 2019-04-19 00:45
 */
public class _83_删除排序链表中的重复元素remove_duplicates_from_sorted_list {
    class Solution {
        public ListNode deleteDuplicates(ListNode head) {
            ListNode cur = head;
            if (head == null || head.next == null) {
                return head;
            }
            Map map = new HashMap();
            map.put(head.val,1);
            while (cur.next != null) {
                if (map.get(cur.next.val) == null) {
                    cur = cur.next;
                    map.put(cur.val, 1);
                } else {
                    cur.next = cur.next.next;
                }
            }
            return head;
        }
    }

    public static void main(String[] args) {
        Set set = new HashSet();
        set.add(1);
        set.add(2);
        set.add(1);
        System.out.println(set);
    }
}
