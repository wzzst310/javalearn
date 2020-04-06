package com.wjjzst.jvm.outOfMemoryError;

import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * @Author: Wjj
 * @Date: 2020/4/6 11:43 下午
 * @desc: 通过CGLib使得某个类的信息变得很大很大 从而占满方法区(java8 元空间)
 * VM Args: -XX:PermSize=10M -XX:MaxPermSize=10M 或者 -XX:MaxMetaspaceSize=10M
 */
public class _6JavaMethodAreaOOM {
    static class OOMObject {}
    public static void main(String[] args) {
        while (true) {
            Enhancer enhancer = new Enhancer();
            enhancer.setSuperclass(OOMObject.class);
            enhancer.setUseCache(false);
            enhancer.setCallback(new MethodInterceptor() {
                @Override
                public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy)
                        throws Throwable {
                    return methodProxy.invokeSuper(o, objects);
                }
            });
            enhancer.create();
        }
    }
}
