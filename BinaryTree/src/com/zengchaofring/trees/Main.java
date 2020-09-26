package com.zengchaofring.trees;

import com.zengchaofring.classes.*;
import com.zengchaofring.setAndMap.*;
import com.zengchaofring.heap.*;
import com.zengchaofring.tools.Asserts;
import com.zengchaofring.tools.filePrinter.FilesTool;
import com.zengchaofring.tools.printer.BinaryTrees;
import com.zengchaofring.tools.time.TimeTools;
import com.zengchaofring.tools.fileInfo.*;

import java.text.SimpleDateFormat;
import java.util.Comparator;
import java.util.Date;
//import java.util.HashMap;

/**
 * @ClassName Main
 * @Description TODO
 * @Author carl
 * @Date 2020/8/23 02:25
 * @Version 1.0
 **/
public class Main {
    public static void writeToFileWithTree(String str, String fileName) {
        String result = "";
        SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd, HH:mm:ss.SSS");
        result += "------------------------------\n";
        result += ("Time: " + fmt.format(new Date()) + "\n");
        result += "------------------------------\n";
        result += str;
        result += "\n\n\n";
        String filePath = "./Data/" + fileName + ".txt";
        FilesTool.writeToFile(filePath, result, true);
    }

    public static void main(String[] args) {

        testForTrie();

//        testForBinaryHeap1();
//        testForBinaryHeap2();
//        testForBinaryHeap3();
//        testForBinaryHeap4();

//        test1();
//		test2(new LinkedHashMap<>());
//		test3(new LinkedHashMap<>());
//		test4(new LinkedHashMap<>());
//		test5(new LinkedHashMap<>());

//        test1();
//		test2(new HashMap<>());
//		test3(new HashMap<>());
//		test4(new HashMap<>());
//		test5(new HashMap<>());

//        testForHash01();
//        testForMap02();
//        testTreeMap();
//        testForSet();
//        testRBTreeRemove();
//        testRBTreeAdd();
//        testAVLTreeRemove();
//        testAVLTreeAdd();
//        testRemoveMethod();
//        testBSTHeight();
//        testIfBSTIsComplete();
//        testForBSTPreOrder();
//        testForBSTUsingPerson();
//        testForBinaryTree1();
    }

    static void testForTrie() {
        Trie<Integer> trie = new Trie<>();
        trie.add("cat", 1);
        trie.add("dog", 2);
        trie.add("catalog", 3);
        trie.add("cast", 4);
        trie.add("小码哥", 5);
        Asserts.test(trie.size() == 5);
        Asserts.test(trie.startsWith("do"));
        Asserts.test(trie.startsWith("c"));
        Asserts.test(trie.startsWith("ca"));
        Asserts.test(trie.startsWith("cat"));
        Asserts.test(trie.startsWith("cata"));
        Asserts.test(!trie.startsWith("hehe"));
        Asserts.test(trie.get("小码哥") == 5);
//		Asserts.test(trie.remove("cat") == 1);
//		Asserts.test(trie.remove("catalog") == 3);
//		Asserts.test(trie.remove("cast") == 4);
//		Asserts.test(trie.size() == 2);
//		Asserts.test(trie.startsWith("do"));
//		Asserts.test(!trie.startsWith("c"));
    }

    static void testForBinaryHeap1() {
        BinaryHeap<Integer> heap = new BinaryHeap<>();
        heap.add(68);
        heap.add(72);
        heap.add(43);
        heap.add(50);
        heap.add(38);
        heap.add(10);
        heap.add(90);
        heap.add(65);
        BinaryTrees.println(heap);
        // heap.remove();
        // BinaryTrees.println(heap);

        System.out.println(heap.replace(70));
        BinaryTrees.println(heap);
    }

    static void testForBinaryHeap2() {
        Integer[] data = {88, 44, 53, 41, 16, 6, 70, 18, 85, 98, 81, 23, 36, 43, 37};
        BinaryHeap<Integer> heap = new BinaryHeap<>(data);
        BinaryTrees.println(heap);

        data[0] = 10;
        data[1] = 20;
        BinaryTrees.println(heap);
    }

    static void testForBinaryHeap3() {
        Integer[] data = {88, 44, 53, 41, 16, 6, 70, 18, 85, 98, 81, 23, 36, 43, 37};
        BinaryHeap<Integer> heap = new BinaryHeap<>(data, new Comparator<Integer>() {
            public int compare(Integer o1, Integer o2) {
                return o2 - o1;
            }
        });
        BinaryTrees.println(heap);
    }

    static void testForBinaryHeap4() {
        // 新建一个小顶堆
        BinaryHeap<Integer> heap = new BinaryHeap<>(new Comparator<Integer>() {
            public int compare(Integer o1, Integer o2) {
                return o2 - o1;
            }
        });

        // 找出最大的前k个数
        int k = 3;
        Integer[] data = {51, 30, 39, 92, 74, 25, 16, 93,
                91, 19, 54, 47, 73, 62, 76, 63, 35, 18,
                90, 6, 65, 49, 3, 26, 61, 21, 48};
        for (int i = 0; i < data.length; i++) {
            if (heap.size() < k) { // 前k个数添加到小顶堆
                heap.add(data[i]); // logk
            } else if (data[i] > heap.get()) { // 如果是第k + 1个数，并且大于堆顶元素
                heap.replace(data[i]); // logk
            }
        }
        // O(nlogk)
        BinaryTrees.println(heap);
    }

    static void test1Map(Map<String, Integer> map, String[] words) {
        TimeTools.test(map.getClass().getName(), new TimeTools.Task() {
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

    static void test1() {
        String filepath = "/Users/carl/Desktop/src/java/util";
        FileInfo fileInfo = Files.read(filepath, null);
        String[] words = fileInfo.words();

        System.out.println("总行数：" + fileInfo.getLines());
        System.out.println("单词总数：" + words.length);
        System.out.println("-------------------------------------");

        test1Map(new TreeMap<>(), words);
        test1Map(new HashMap<>(), words);
//        test1Map(new LinkedHashMap<>(), words);
    }

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
        map.remove("jack");
        map.remove("jim");
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
        Asserts.test(map.size() == 19);
        Asserts.test(map.get(new Key(1)) == 6);
        Asserts.test(map.get(new Key(2)) == 7);
        Asserts.test(map.get(new Key(3)) == 8);
        Asserts.test(map.get(new Key(4)) == 4);
        Asserts.test(map.get(new Key(5)) == null);
        Asserts.test(map.get(new Key(6)) == null);
        Asserts.test(map.get(new Key(7)) == null);
        Asserts.test(map.get(new Key(8)) == 8);
        map.traversal(new Map.Visitor<Object, Integer>() {
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

//    public static void testForHash01() {
//        Person p1 = new Person(13, 1.55f, "jack");
//        Person p2 = new Person(15, 1.74f, "jason");
//        Person p3 = new Person(13, 1.55f, "jack");
//        HashMap<Object, Object> map = new HashMap<>();
//        map.put(p1, "hello");
//        map.put(p2, "good");
//        map.put(p3, "great");
//
//        System.out.println(p1.hashCode());
//        System.out.println(p2.hashCode());
//        System.out.println(p3.hashCode());
//
//        System.out.println(map.size());
//        for (java.util.Map.Entry<Object, Object> entry:
//                map.entrySet()) {
//            System.out.println("key: " + entry.getKey() + " and value: " + entry.getValue());
//        }
//
//        /*String s = "hello";
//        int hashCode = 0;
//        int sz = s.length();
//        for (int i = 0; i < sz; i++) {
//            char c = s.charAt(i);
//            hashCode = hashCode * 31 + c;
//        }
//        System.out.println(hashCode);
//        System.out.println(s.hashCode());*/
//
//        /*int i = 100;
//        System.out.println(31 * i);
//        System.out.println((i << 5) + i);*/
//    }

    public static void testForMap02() {
        FileInfo fileInfo = Files.read("/Users/carl/Desktop/src/java/util", new String[]{"java"});

        System.out.println("文件数量: " + fileInfo.getFiles());
        System.out.println("代码行数: " + fileInfo.getLines());
        String[] words = fileInfo.words();
        System.out.println("单词数量: " + words.length);

        Map<String, Integer> map = new TreeMap<>();
        for (int i = 0; i < words.length; i++) {
            Integer count = map.get(words[i]);
            count = (count == null) ? 1 : (count + 1);
            map.put(words[i], count);
        }

        map.traversal(new TreeMap.Visitor<String, Integer>() {
            @Override
            public boolean visit(String key, Integer value) {
                System.out.println(key + " occurs: " + value + " times");
                return false;
            }
        });
    }

    public static void testTreeMap() {
        TreeMap<String, Integer> map = new TreeMap<>();
        map.put("a", 15);
        map.put("b", 20);
        map.put("c", 22);
        map.put("b", 53);
        map.put("a", 22);
        map.put("d", 100);

        map.traversal(new Map.Visitor<String, Integer>() {
            @Override
            public boolean visit(String key, Integer value) {
                System.out.println(key + "_" + value);
                return false;
            }
        });
    }

    public static void testForSet() {
        FileInfo fileInfo = Files.read("/Users/carl/Desktop/src/java/util", new String[]{"java"});

        System.out.println("文件数量: " + fileInfo.getFiles());
        System.out.println("代码行数: " + fileInfo.getLines());
        String[] words = fileInfo.words();
        System.out.println("单词数量: " + words.length);

        TimeTools.test("LinkSet Test", new TimeTools.Task() {
            @Override
            public void execute() {
                testSet(new LinkSet<>(), words);
            }
        });
        TimeTools.test("TreeSet Test", new TimeTools.Task() {
            @Override
            public void execute() {
                testSet(new TreeSet<>(), words);
            }
        });
    }

    public static void testSet(Set<String> set, String[] words) {
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

    public static void testRBTreeRemove() {
        Integer[] data = new Integer[] {
                6, 8, 14, 18, 23, 49, 50, 52, 76, 79, 86, 88, 100
        };
        RBTree<Integer> rbTree = new RBTree<>();
        for (int i = 0; i < data.length; i++) {
            rbTree.add(data[i]);
        }
        BinaryTrees.println(rbTree);

        rbTree.remove(23);
        System.out.println("-------------【23】Removed -------------");
        BinaryTrees.println(rbTree);

        rbTree.remove(6);
        System.out.println("-------------【6】Removed -------------");
        BinaryTrees.println(rbTree);

        rbTree.remove(18);
        System.out.println("-------------【18】Removed -------------");
        BinaryTrees.println(rbTree);
    }

    public static void testRBTreeAdd() {
        Integer[] data = new Integer[] {
                6, 8, 14, 18, 23, 49, 50, 52, 76, 79, 86, 88, 100
        };
        RBTree<Integer> rbTree = new RBTree<>();
        for (int i = 0; i < data.length; i++) {
            rbTree.add(data[i]);
        }
        BinaryTrees.println(rbTree);
        writeToFileWithTree(BinaryTrees.printString(rbTree), "testForRBTreeAdd");
    }

    public static void testAVLTreeRemove() {
        Integer[] data = new Integer[] {
                6, 8, 14, 18, 23, 49, 50, 52, 76, 79, 86, 88, 100
        };
        AVLTree<Integer> avlTree = new AVLTree<>();
        for (int i = 0; i < data.length; i++) {
            avlTree.add(data[i]);
        }

        BinaryTrees.println(avlTree);

        avlTree.remove(52);
        System.out.println("----------【52】removed----------");
//        writeToFileWithTree("----------【52】removed----------", "testForAVLTreeRemoved");
        BinaryTrees.println(avlTree);
//        writeToFileWithTree(BinaryTrees.printString(avlTree), "testForAVLTreeRemoved");

        avlTree.remove(88);
        System.out.println("----------【88】removed----------");
//        writeToFileWithTree("----------【88】removed----------", "testForAVLTreeRemoved");
        BinaryTrees.println(avlTree);
//        writeToFileWithTree(BinaryTrees.printString(avlTree), "testForAVLTreeRemoved");

        avlTree.remove(86);
        System.out.println("----------【86】removed----------");
//        writeToFileWithTree("----------【86】removed----------", "testForAVLTreeRemoved");
        BinaryTrees.println(avlTree);
//        writeToFileWithTree(BinaryTrees.printString(avlTree), "testForAVLTreeRemoved");

        avlTree.remove(79);
//        writeToFileWithTree("----------【79】removed----------", "testForAVLTreeRemoved");
        BinaryTrees.println(avlTree);
//        writeToFileWithTree(BinaryTrees.printString(avlTree), "testForAVLTreeRemoved");
    }

    public static void testAVLTreeAdd() {
        Integer[] data = new Integer[] {
                6, 8, 14, 18, 23, 49, 50, 52, 76, 79, 86, 88, 100
        };
        AVLTree<Integer> avlTree = new AVLTree<>();
        for (int i = 0; i < data.length; i++) {
            avlTree.add(data[i]);
        }

        BinaryTrees.println(avlTree);
    }

    public static void testRemoveMethod() {
        BST<Integer> bst = new BST<>();
        Integer[] data = new Integer[] {
                7, 4, 9, 2, 5, 8, 11, 3, 12, 1
        };
        for (int i = 0; i < data.length; i++) {
            bst.add(data[i]);
        }

        BinaryTrees.println(bst);
        bst.remove(11);
        bst.remove(7);
        System.out.println(bst.remove(12));
        BinaryTrees.println(bst);
    }

    public static void testBSTHeight() {
        BST<Integer> bst = new BST<>();
        for (int i = 0; i < 20; i++) {
            bst.add((int) (Math.random() * 100));
        }

        BinaryTrees.println(bst);
        System.out.println("BST height is(using recursion): " + bst.heightRecursion());
        System.out.println("BST height is(using iteration): " + bst.heightIteration());
    }

    public static void testIfBSTIsComplete() {
        BST<Integer> bst = new BST<>();
        Integer[] data = new Integer[] {
                56, 48, 95, 32, 51, 83, 112, 16, 34, 50, 52, 74
        };
        for (int i = 0; i < data.length; i++) {
            bst.add(data[i]);
        }
        BinaryTrees.println(bst);
        System.out.println(bst.isComplete());
    }

    public static void testForBSTPreOrder() {
        BST<Integer> bst = new BST<>();
        Integer[] data = new Integer[] {
                7, 4, 9, 2, 5, 8, 11, 3, 12, 1
        };
        for (int i = 0; i < data.length; i++) {
            bst.add(data[i]);
        }
        //7 4 2 1 3 5 9 8 11 12
        BinaryTrees.println(bst);
        StringBuilder sb = new StringBuilder();
        sb.append("PreOrder result: ");
        bst.preOrder(new BST.Visitor<Integer>() {
            @Override
            public boolean visit(Integer element) {
                sb.append("_").append(element).append("_");
                return false;
            }
        });
        sb.append("\n");
        sb.append("Inorder result: ");
        bst.inOrder(new BST.Visitor<Integer>() {
            @Override
            public boolean visit(Integer element) {
                sb.append("_").append(element).append("_");
                return false;
            }
        });
        sb.append("\n");
        sb.append("Postorder result: ");
        bst.postOrder(new BST.Visitor<Integer>() {
            @Override
            public boolean visit(Integer element) {
                sb.append("_").append(element).append("_");
                return false;
            }
        });
        sb.append("\n");
        sb.append("LevelOrder result: ");
        bst.levelOrder(new BST.Visitor<Integer>() {
            @Override
            public boolean visit(Integer element) {
                sb.append("_").append(element).append("_");
                return false;
            }
        });
        sb.append("\n");

        System.out.println(sb.toString());
        System.out.println(bst);
    }

    public static void testForBinaryTree1() {
        BST<Integer> bst1 = new BST<>();
        Integer[] data = new Integer[] {
                7, 4, 9, 2, 5, 8, 11, 3, 12, 1
        };

        for (int i = 0; i < data.length; i++) {
            bst1.add(data[i]);
        }
        String str = BinaryTrees.printString(bst1);
        writeToFileWithTree(str, "testForBinaryTree1");
        BinaryTrees.println(bst1);
    }


    public static void testForBSTUsingPerson() {
        BST<Person> bst = new BST<>(new Comparator<Person>() {
            @Override
            public int compare(Person o1, Person o2) {
                return o1.getAge() - o2.getAge();
            }
        });

        bst.add(new Person(7, "Jack"));
        bst.add(new Person(4, "Jack"));
        bst.add(new Person(9, "Jack"));
        bst.add(new Person(2, "Jack"));
        bst.add(new Person(5, "Jack"));
        bst.add(new Person(8, "Jack"));
        bst.add(new Person(11, "Jack"));
        bst.add(new Person(3, "Jack"));
        bst.add(new Person(12, "Jack"));
        bst.add(new Person(1, "Jack"));

        BinaryTrees.println(bst);
    }
}
