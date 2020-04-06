package com.wjjzst.jvm.outOfMemoryError;

/**
 * @Author: Wjj
 * @Date: 2020/4/6 9:25 下午
 * @desc: 虚拟机栈和本地方法栈 栈深度太深导致内存溢出
 * VM Args: -Xss256k
 */
public class _2JavaVMStackSOF {
    private int stackLength = 1;
    public void stackLeak() {
        stackLength++;
        stackLeak();
    }
    public static void main(String[] args) {
        _2JavaVMStackSOF oom = new _2JavaVMStackSOF();
        try {
            oom.stackLeak();
        } catch (Throwable e) {
            System.out.println("stack length:" + oom.stackLength);
            throw e;
        }
    }
}
