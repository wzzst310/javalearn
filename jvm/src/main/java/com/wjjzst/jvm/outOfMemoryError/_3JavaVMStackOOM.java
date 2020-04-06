package com.wjjzst.jvm.outOfMemoryError;

/**
 * @Author: Wjj
 * @Date: 2020/4/6 9:42 下午
 * @desc: 虚拟机栈和本地方法栈 创建线程导致内存溢出
 * VM Args: -Xss2M
 */
public class _3JavaVMStackOOM {
    private void dontStop() {
        while (true) {}
    }
    public void stackLeakByThread() {
        while (true) {
            Thread thread = new Thread(new Runnable() {
                @Override
                public void run() {
                    dontStop();
                }
            });
            thread.start();
        }
    }
    public static void main(String[] args) {
        _3JavaVMStackOOM oom = new _3JavaVMStackOOM();
        oom.stackLeakByThread();
    }
}
