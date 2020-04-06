package com.wjjzst.jvm.clazz;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author: Wjj
 * @Date: 2020/3/31 10:49 下午
 * @desc:
 */
public class VolatileTest3 {
    Map configOptions;
    char[] configText;
    // 此变量必须定义为volatile
    volatile boolean initialized = false;

    // 假设以下代码在程序A中执行
    // 模拟读取配置信息，当读取完成之后
    // initialized设置为true，通知其他线程配置可用
    void init() {
        configOptions = new HashMap();
        String fileName = "";
        configText = readConfigFile(fileName);
        processConfigOptions(configText, configOptions);
        initialized = true;
    }

    // 假设以下代码在线程B中执行
    // 等待initialized为true，代表线程A已经把配置信息初始化完成
    void mywait(){
        while (!initialized){
            sleep();
        }
    }

    // 使用线程A中初始化好的配置信息
    void doSomething(){
        doSomethingWithConfig();
    }

    private void doSomethingWithConfig() {
    }

    private void sleep() {
    }

    private void processConfigOptions(char[] configText, Map configOptions) {
    }

    private char[] readConfigFile(String fileName) {
        return new char[0];
    }


}
