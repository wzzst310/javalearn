package com.wjjzst.exam.堆栈.easy;

import java.util.Arrays;
import java.util.List;
import java.util.Stack;

/**
 * https://leetcode-cn.com/problems/evaluate-reverse-polish-notation/
 *
 * @Author: Wjj
 * @Date: 2019-04-25 01:31
 */
public class _150_逆波兰表达式求值evaluate_reverse_polish_notation {
    class Solution {
        public int evalRPN(String[] tokens) {
            Stack<Integer> stack = new Stack<>();
            List<String> operatorStrings = Arrays.asList("+", "-", "*", "/");
            for (String token : tokens) {
                if (!operatorStrings.contains(token)) {
                    stack.push(new Integer(token));
                } else {
                    Integer val2 = stack.pop();
                    Integer val1 = stack.pop();
                    stack.push(operate(token,val1,val2));
                }
            }
            return stack.pop();
        }

        private int operate(String operateString, int val1, int val2) {
            switch (operateString) {
                case "+":
                    return val1 + val2;
                case "-":
                    return val1 - val2;
                case "*":
                    return val1 * val2;
                case "/":
                    return val1 / val2;
                default:
                    return 0;
            }
        }
    }
    public static void main(String[] args) {
        Stack<Integer> integers = new Stack<>();
        if(integers.isEmpty()){

        }
        integers.pop();
    }
}
