package com.wjjzst.exam.链表.easy;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * https://leetcode-cn.com/problems/merge-two-sorted-lists/
 *
 * @Author: Wjj
 * @Date: 2019-04-23 00:33
 */
public class _21_合并两个有序链表merge_two_sorted_lists {
    static class Solution {
        public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
            l1 = arr2Node("[-2,5]");
            l2 = arr2Node("[-9,-6,-3,-1,1,6]");
            if (l1 != null) {
                ListNode cur = l1;
                while (cur.next != null) {
                    cur = cur.next;
                }
                cur.next = l2;
                sortNode(l1);
                return l1;
            }else{
                if(l2 != null){
                    sortNode(l2);
                    return l2;
                }else{
                    return null;
                }
            }
        }

        //链表排序值交换就行了
        private void sortNode(ListNode node) {
            while (node != null) {
                ListNode cur = node;
                while (cur.next != null) {
                    if (cur.val < cur.next.val) {
                        int temp = cur.val;
                        cur.val = cur.next.val;
                        cur.next.val = temp;
                    }
                    cur = cur.next;
                }
                node = node.next;
            }
        }

        private void getNodes(ListNode node, ArrayList<ListNode> nodes) {
            if (node != null) {
                while (node.next != null) {
                    ListNode temp = node;
                    temp.next = null;
                    nodes.add(temp);
                    node = node.next;
                }
            }
        }
        private ListNode  arr2Node(String arrStr){
            String substring = arrStr.substring(1, arrStr.length()-1);
            String[] split = substring.split(",");
            List<Integer> integers = new ArrayList<>();
            for (String s : split) {
                integers.add(new Integer(s.trim()));
            }
            ListNode node = new ListNode(0);
            ListNode cur = node;
            for (Integer integer : integers) {
                cur.next = new ListNode(integer);
                cur = cur.next;
            }
            return node.next;
        }

        public static void main(String[] args) {
            Solution solution = new Solution();
            solution.mergeTwoLists(new ListNode(1),new ListNode(1));

        }
    }
}