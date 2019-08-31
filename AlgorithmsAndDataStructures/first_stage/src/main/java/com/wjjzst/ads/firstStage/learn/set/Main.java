package com.wjjzst.ads.firstStage.learn.set;

import com.wjjzst.ads.firstStage.learn.common.Times;
import com.wjjzst.ads.firstStage.learn.common.file.FileInfo;
import com.wjjzst.ads.firstStage.learn.common.file.Files;

/**
 * @Author: Wjj
 * @Date: 2019/5/19 22:58
 * @desc:
 */
public class Main {
    static void testSet() {
        //Set<Integer> set = new ListSet<>();
        Set<Integer> set = new OldTreeSet<>();
        for (int i = 0; i < 6; i++) {
            set.add(i);
        }
        set.add(1);
        set.add(2);
        set.add(3);
        set.remove(5);
        System.out.println(set.contains(5));
        System.out.println(set.size());
        set.traversal(new Set.Visitor<Integer>() {
            @Override
            boolean visit(Integer element) {
                System.out.println(element);
                return 5 == element;
            }
        });
    }
    static void testSet(Set<String> set, String[] words) {
        for (int i = 0; i < words.length; i++) {
            set.add(words[i]);
        }
        for (int i = 0; i < words.length; i++) {
            set.contains(words[i]);
        }
        for (int i = 0; i < words.length; i++) {
            set.remove(words[i]);
        }
    }

    static void test2() {
        FileInfo fileInfo = Files.read("C:/Program Files/Java/jdk1.8.0_201/src/java/util/concurrent",
                new String[]{"java"});

        System.out.println("文件数量：" + fileInfo.getFiles());
        System.out.println("代码行数：" + fileInfo.getLines());
        String[] words = fileInfo.words();
        System.out.println("单词数量：" + words.length);

//		Times.test("ListSet", new Task() {
//			public void execute() {
//				testSet(new ListSet<>(), words);
//			}
//		});

        Times.test("OldTreeSet", new Times.Task() {
            public void execute() {
                testSet(new OldTreeSet<>(), words);
                //testSet(new ListSet<>(), words);
            }
        });
    }


    public static void main(String[] args) {
        //testSet();
        test2();
    }
}
