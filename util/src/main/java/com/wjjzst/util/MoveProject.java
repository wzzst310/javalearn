package com.wjjzst.util;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.*;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;

/**
 * @Author: Wjj
 * @Date: 2020/6/17 7:56 下午
 * @desc:
 */

public class MoveProject {
    public static void main(String[] args) {
//        String sourcefilePath = "/Users/wjj/IdeaProjects/wjjjavalearn/springcloud";
//        String sourcePath = "/Users/wjj/IdeaProjects/wjjjavalearn/springcloud/config/";
//        String targetPath = "/Users/wjj/Desktop/changeBaseDir/IdeaProjects/javalearn/springcloud";
//        String sourcefilePath = "/Users/wjj/IdeaProjects/cehome/b-campaigns/b-campaigns-web";
//        String targetFilePath = "/Users/wjj/Desktop/changeBaseDir/cehome/b-campaigns/b-campaigns-web/";
//        String sourcePath = "/com/taiping/";
//        String targetPath = "/cn/wjjzst/";
//        List<String> ignoreFileSuffixs = new ArrayList<>();
//        List<String> ignoreFileNames = new ArrayList<>();
//        ignoreFileSuffixs.add(".iml");
//        ignoreFileSuffixs.add(".project");
//        ignoreFileNames.add("target");
//        List<String> changeFileSuffixs = new ArrayList<>();
//        changeFileSuffixs.add(".java");
//        String sourceStr = "com.taiping";
//        String targetStr = "cn.wjjzst";
        String sourcefilePath = "/Users/wjj/IdeaProjects/wjjjavalearn/AlgorithmsAndDataStructures";
        String targetFilePath = "/Users/wjj/IdeaProjects/javalearn/AlgorithmsAndDataStructures";
        String sourcePath = "/com/wjjzst/";
        String targetPath = "/cn/wolfcode/";
        List<String> ignoreFileSuffixs = new ArrayList<>();
        List<String> ignoreFileNames = new ArrayList<>();
        ignoreFileSuffixs.add(".iml");
        ignoreFileSuffixs.add(".project");
        ignoreFileSuffixs.add(".Ds_Store");
        ignoreFileNames.add("target");
        List<String> changeFileSuffixs = new ArrayList<>();
        changeFileSuffixs.add(".java");
        changeFileSuffixs.add(".xml");
        List<ReplaceStr> replaceStrs = new ArrayList<>();
        String sourceStr1 = "com.wjjzst";
        String targetStr1 = "cn.wolfcode";
        String sourceStr2 = "wjjjavalearn";
        String targetStr2 = "javalearn";
        replaceStrs.add(new ReplaceStr(sourceStr1, targetStr1));
        replaceStrs.add(new ReplaceStr(sourceStr2, targetStr2));
        List<File> allChildrenFiles = getAllChildrenFiles(sourcefilePath, ignoreFileNames, ignoreFileSuffixs);
        List<File> newFiles = changePath(sourcefilePath, targetFilePath, allChildrenFiles, sourcePath, targetPath);
        changeFileContent(newFiles, changeFileSuffixs, replaceStrs);
    }

    private static List<File> getAllChildrenFiles(String sourcefilePath, List<String> ignoreFileNames, List<String> ignoreFileSuffixs) {
        List<File> files = new ArrayList<>();
        Stack<File> fileStack = new Stack<>();
        fileStack.push(new File(sourcefilePath));
        while (!fileStack.empty()) {
            File file = fileStack.pop();
            String name = file.getName();
            int i = name.lastIndexOf(".");
            if (!ignoreFileNames.contains(name)) {
                if (file.isDirectory()) {
                    fileStack.addAll(Arrays.asList(file.listFiles()));
                } else if (i != -1 && !ignoreFileSuffixs.contains(name.substring(i))) {
                    files.add(file);
                } else if (i != -1) {
                    System.out.println("文件没有后缀名异常,没有进行复制: " + file.getAbsolutePath());
                }
            }
        }
        return files;
    }

    private static List<File> changePath(String sourcefilePath, String targetFilePath, List<File> allChildrenFiles, String sourcePath, String targetPath) {
        File targetFilePathFile = new File(targetFilePath);
        deleteFile(targetFilePathFile);
        List<File> newFiles = new ArrayList<>();
        for (File cFile : allChildrenFiles) {
            String filePath = cFile.getAbsolutePath();
            if (sourcePath.indexOf(sourcePath) != -1) {
                String newFilePath = filePath.replace(sourcefilePath, targetFilePath).replace(sourcePath, targetPath);
                File file = copyFile(filePath, newFilePath);
                if (file != null) {
                    newFiles.add(file);
                }
            }
        }
        return newFiles;
    }

    private static File copyFile(String sourceFilePath, String targetFilePath) {
        try {
            FileChannel inChannel = FileChannel.open(Paths.get(sourceFilePath), StandardOpenOption.READ);
            File file = new File(targetFilePath);
            File parentDir = new File(file.getParent());
            if (!parentDir.exists()) {
                parentDir.mkdirs();
            }
            if (file.exists()) {
                file.delete();
            }
            FileChannel outChennel = FileChannel.open(Paths.get(targetFilePath), StandardOpenOption.WRITE, StandardOpenOption.READ, StandardOpenOption.CREATE_NEW);
            //内存映射文件(什么模式 从哪开始 到哪结束)
            MappedByteBuffer inMappeBuf = inChannel.map(FileChannel.MapMode.READ_ONLY, 0, inChannel.size());
            MappedByteBuffer outMappeBuf = outChennel.map(FileChannel.MapMode.READ_WRITE, 0, inChannel.size());
            //直接都缓冲区进行数据的读写操作
            byte[] dst = new byte[inMappeBuf.limit()];
            inMappeBuf.get(dst);
            outMappeBuf.put(dst);
            inChannel.close();
            outChennel.close();
            System.out.println("文件复制成功: 源文件 " + sourceFilePath + "<====>目标文件 " + targetFilePath);
            return file;
        } catch (IOException e) {
            System.out.println("复制文件异常");
            e.printStackTrace();
        }
        return null;
    }

    private static void changeFileContent(List<File> files, List<String> changeFileSuffixs, List<ReplaceStr> replaceStrs) {
        for (File file : files) {
            String name = file.getName();
            int i = name.lastIndexOf(".");
            if (i != -1 && changeFileSuffixs.contains(name.substring(name.lastIndexOf(".")))) {
                try {
                    // 读
                    FileReader in = new FileReader(file);
                    BufferedReader bufIn = new BufferedReader(in);
                    // 内存流, 作为临时流
                    CharArrayWriter tempStream = new CharArrayWriter();
                    // 替换
                    String line = null;
                    while ((line = bufIn.readLine()) != null) {
                        // 替换每行中, 符合条件的字符串
                        for (ReplaceStr replaceStr : replaceStrs) {
                            line = line.replaceAll(replaceStr.getSourceStr(), replaceStr.getTargetStr());
                        }
                        // 将该行写入内存
                        tempStream.write(line);
                        // 添加换行符
                        tempStream.append(System.getProperty("line.separator"));
                    }
                    // 关闭 输入流
                    bufIn.close();
                    // 将内存中的流 写入 文件
                    FileWriter out = new FileWriter(file);
                    tempStream.writeTo(out);
                    out.close();
                } catch (IOException e) {
                    System.out.println("文件内容替换异常: " + file.getAbsolutePath());
                    e.printStackTrace();
                }

            }
        }
    }

    public static void deleteFile(File targetFile) {
        if (targetFile.isFile()) {
            targetFile.delete();
        } else {
            File[] files = targetFile.listFiles();
            if (files != null) {
                for (File file : files) {
                    if (file.isDirectory()) {
                        //递归直到目录下没有文件
                        deleteFile(file);
                    } else {
                        //删除
                        file.delete();
                    }
                }
            }
            //删除
            targetFile.delete();
        }
    }
}

@Data
@AllArgsConstructor
class ReplaceStr {
    private String sourceStr;
    private String targetStr;
}
