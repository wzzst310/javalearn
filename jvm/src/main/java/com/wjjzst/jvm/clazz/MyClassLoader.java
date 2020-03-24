package com.wjjzst.jvm.clazz;

import java.io.FileInputStream;

/**
 * @Author: Wjj
 * @Date: 2020/3/23 1:00 上午
 * @desc:
 */
public class MyClassLoader extends ClassLoader {
    private String classPath;
    private String classLoaderName;
    private String referenceName;

    public MyClassLoader(String classPath, String classLoaderName, String referenceName) {
        this.classPath = classPath;
        this.classLoaderName = classLoaderName;
        this.referenceName = referenceName;
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
            // return defineClass(data, 0, data.length);
            return defineClass(referenceName + name, data, 0, data.length);
        } catch (Exception e) {
            e.printStackTrace();
            throw new ClassNotFoundException();
        }
    }
}
