package com.wjjzst.jvm.classLoader;

import java.io.FileInputStream;

/**
 * @Author: Wjj
 * @Date: 2020/3/23 1:00 上午
 * @desc:
 */
public class MyClassLoader extends ClassLoader {
    private String classPath;

    public MyClassLoader(ClassLoader parent, String classPath) {
        super(parent);
        this.classPath = classPath;
    }
    private byte[] loadByte(String name) throws Exception {
        name = name.replaceAll("\\.", "/");
        FileInputStream fis = new FileInputStream(classPath + "/" + name
                + ".class");
        int len = fis.available();
        byte[] data = new byte[len];
        fis.read(data);
        fis.close();
        return data;

    }

    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        try {
            byte[] data = loadByte(name);
            return defineClass(name, data, 0, data.length);
        } catch (Exception e) {
            e.printStackTrace();
            throw new ClassNotFoundException();
        }
    }
}
