package com.wjjzst.ads.firststage.learn.map;

import com.wjjzst.ads.firststage.learn.common.Times;
import com.wjjzst.ads.firststage.learn.common.file.FileInfo;
import com.wjjzst.ads.firststage.learn.common.file.Files;
import com.wjjzst.ads.firststage.learn.common.model.Key;
import com.wjjzst.ads.firststage.learn.common.model.SubKey1;
import com.wjjzst.ads.firststage.learn.common.model.SubKey2;

public class Main {

//	static void test1() {
//		String string = "jack"; // 3254239
//		System.out.println(string.hashCode());
////		int len = string.length();
////		int hashCode = 0;
////		for (int i = 0; i < len; i++) {
////			char c = string.charAt(i);
////			hashCode = hashCode * 31 + c;
////			// hashCode = (hashCode << 5) - hashCode + c;
////		}
////		System.out.println(hashCode);
//		// hashCode = ((j * 31 + a) * 31 + c) * 31 + k
//	}
//	
//	static void test2() {
//		Integer a = 110;
//		Float b = 10.6f;
//		Long c = 156l;
//		Double d = 10.9;
//		String e = "rose";
//		
//		System.out.println(a.hashCode());
//		System.out.println(b.hashCode());
//		System.out.println(c.hashCode());
//		System.out.println(d.hashCode());
//		System.out.println(e.hashCode());
//	}
//
//	static void test3() {
//		Person p1 = new Person(10, 1.67f, "jack");
//		Person p2 = new Person(10, 1.67f, "jack");
//		
//		Map<Object, Object> map = new HashMap<>();
//		map.put(p1, "abc");
//		map.put("test", "ccc");
//		map.put(p2, "bcd");
//		System.out.println(map.size()); 
//	}
//	
//	static void test4() {
//		Person p1 = new Person(10, 1.67f, "jack");
//		Person p2 = new Person(10, 1.67f, "jack");
//		
//		Map<Object, Integer> map = new HashMap<>();
//		map.put(p1, 1);
//		map.put(p2, 2);
//		map.put("jack", 3);
//		map.put("rose", 4);
//		map.put("jack", 5);
//		map.put(null, 6);
//
////		System.out.println(map.size());
////		System.out.println(map.remove("jack"));
////		System.out.println(map.get("jack"));
////		System.out.println(map.size());
//		
//		System.out.println(map.containsKey(p1));
//		System.out.println(map.containsKey(null));
//		System.out.println(map.containsValue(6));
//		System.out.println(map.containsValue(1));
//		
////		map.traversal(new Visitor<Object, Integer>() {
////			public boolean visit(Object key, Integer value) {
////				System.out.println(key + "_" + value);
////				return false;
////			}
////		});
//
////		System.out.println(map.get("jack"));
////		System.out.println(map.get("rose"));
////		System.out.println(map.get(null));
////		System.out.println(map.get(p1));
//	}
//	
//	static void test5() {
//		Person p1 = new Person(10, 1.67f, "jack");
//		Person p2 = new Person(10, 1.67f, "jack");
//		
//		Map<Object, Integer> map = new HashMap<>();
//		map.put(p1, 1);
//		map.put(p2, 2);
//		map.put("jack", 3);
//		map.put("rose", 4);
//		map.put("jack", 5);
//		map.put(null, 6);
//
////		System.out.println(map.size());
////		System.out.println(map.remove("jack"));
////		System.out.println(map.get("jack"));
////		System.out.println(map.size());
//		
//		System.out.println(map.containsKey(p1));
//		System.out.println(map.containsKey(null));
//		System.out.println(map.containsValue(6));
//		System.out.println(map.containsValue(1));
//		
////		map.traversal(new Visitor<Object, Integer>() {
////			public boolean visit(Object key, Integer value) {
////				System.out.println(key + "_" + value);
////				return false;
////			}
////		});
//
////		System.out.println(map.get("jack"));
////		System.out.println(map.get("rose"));
////		System.out.println(map.get(null));
////		System.out.println(map.get(p1));
//	}
//	
//	static void test6() {
//		HashMap<Object, Integer> map = new HashMap<>();
//		for (int i = 1; i <= 19; i++) {
//			map.put(new Key(i), i);
//		}
//		
//		map.put(new Key(4), 100);
//		Asserts.test(map.size() == 19);
//		Asserts.test(map.get(new Key(4)) == 100);
//		Asserts.test(map.get(new Key(18)) == 18);
//	}
//	

    static void test2(HashMap<Object, Integer> map) {
        for (int i = 1; i <= 20; i++) {
            map.put(new Key(i), i);
        }
        for (int i = 5; i <= 7; i++) {
            map.put(new Key(i), i + 5);
        }
        Asserts.test(map.size() == 20);
        Asserts.test(map.get(new Key(4)) == 4);
        Asserts.test(map.get(new Key(5)) == 10);
        Asserts.test(map.get(new Key(6)) == 11);
        Asserts.test(map.get(new Key(7)) == 12);
        Asserts.test(map.get(new Key(8)) == 8);
    }

    static void test3(HashMap<Object, Integer> map) {
        map.put(null, 1); // 1
        map.put(new Object(), 2); // 2
        map.put("jack", 3); // 3
        map.put(10, 4); // 4
        map.put(new Object(), 5); // 5
        map.put("jack", 6);
        map.put(10, 7);
        map.put(null, 8);
        map.put(10, null);
        Asserts.test(map.size() == 5);
        Asserts.test(map.get(null) == 8);
        Asserts.test(map.get("jack") == 6);
        Asserts.test(map.get(10) == null);
        Asserts.test(map.get(new Object()) == null);
        Asserts.test(map.containsKey(10));
        Asserts.test(map.containsKey(null));
        Asserts.test(map.containsValue(null));
        Asserts.test(map.containsValue(1) == false);
    }

    static void test4(HashMap<Object, Integer> map) {
        map.put("jack", 1);
        map.put("rose", 2);
        map.put("jim", 3);
        map.put("jake", 4);
        for (int i = 1; i <= 10; i++) {
            map.put("test" + i, i);
            map.put(new Key(i), i);
        }
        for (int i = 5; i <= 7; i++) {
            Asserts.test(map.remove(new Key(i)) == i);
        }
        for (int i = 1; i <= 3; i++) {
            map.put(new Key(i), i + 5);
        }
        Asserts.test(map.size() == 21);
        Asserts.test(map.get(new Key(1)) == 6);
        Asserts.test(map.get(new Key(2)) == 7);
        Asserts.test(map.get(new Key(3)) == 8);
        Asserts.test(map.get(new Key(4)) == 4);
        Asserts.test(map.get(new Key(5)) == null);
        Asserts.test(map.get(new Key(6)) == null);
        Asserts.test(map.get(new Key(7)) == null);
        Asserts.test(map.get(new Key(8)) == 8);
        map.traversal(new Map.Visitor<Object, Integer>() {
            @Override
            public boolean visit(Object key, Integer value) {
                System.out.println(key + "_" + value);
                return false;
            }
        });
    }

    static void test5(HashMap<Object, Integer> map) {
        for (int i = 1; i <= 20; i++) {
            map.put(new SubKey1(i), i);
        }
        map.put(new SubKey2(1), 5);
        Asserts.test(map.get(new SubKey1(1)) == 5);
        Asserts.test(map.get(new SubKey2(1)) == 5);
        Asserts.test(map.size() == 20);
    }

    static void test1() {
        String filepath = "C:/Program Files/Java/jdk1.8.0_201/src/java/util";
        FileInfo fileInfo = Files.read(filepath, null);
        String[] words = fileInfo.words();

        System.out.println("总行数：" + fileInfo.getLines());
        System.out.println("单词总数：" + words.length);
        System.out.println("-------------------------------------");

//		java.util.HashMap<String, Integer> map = new java.util.HashMap<>();
//
//		for (String word : words) {
//			Integer count = map.get(word);
//			count = count == null ? 0 : count;
//			map.put(word, count + 1);
//		}
//		System.out.println(map.size()); // 17188

        LinkedHashMap<String, Integer> map = new LinkedHashMap<>();
        Times.test(map.getClass().getName(), new Times.Task() {
            @Override
            public void execute() {
                for (String word : words) {
                    Integer count = map.get(word);
                    count = count == null ? 0 : count;
                    map.put(word, count + 1);
                }
                System.out.println(map.size()); // 17188

                int count = 0;
                for (String word : words) {
                    Integer i = map.get(word);
                    count += i == null ? 0 : i;
                    map.remove(word);
                }
                Asserts.test(count == words.length);
                Asserts.test(map.size() == 0);
            }
        });
    }

    public static void main(String[] args) {
//		test1();
//		test2(new HashMap<>());
//		test3(new HashMap<>());
//      test4(new HashMap<>());
//		test5(new HashMap<>());
        test1();
		test2(new LinkedHashMap<>());
		test3(new LinkedHashMap<>());
        test4(new LinkedHashMap<>());
		test5(new LinkedHashMap<>());
        /*java.util.HashMap<Long, Long> map = new java.util.HashMap<>();
        System.out.println(1 << 2);*/
    }

}
