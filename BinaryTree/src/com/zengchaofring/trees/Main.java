package com.zengchaofring.trees;

import com.zengchaofring.classes.*;
import com.zengchaofring.tools.filePrinter.Files;
import com.zengchaofring.tools.printer.BinaryTrees;

import java.text.SimpleDateFormat;
import java.util.Comparator;
import java.util.Date;

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
        String filePath = "/Users/carl/Desktop/Temp/数据结构测试结果/" + fileName + ".txt";
        Files.writeToFile(filePath, result, true);
    }

    public static void main(String[] args) {

        testAVLTreeRemove();
//        testAVLTreeAdd();
//        testRemoveMethod();
//        testBSTHeight();
//        testIfBSTIsComplete();
//        testForBSTPreOrder();
//        testForBSTUsingPerson();
//        testForBinaryTree1();
    }

    public static void testAVLTreeRemove() {
        Integer[] data = new Integer[] {
                6, 8, 14, 18, 23, 49, 50, 52, 76, 79, 86, 88, 100
        };
        AVLTree<Integer> avlTree = new AVLTree<>();
        for (int i = 0; i < data.length; i++) {
            avlTree.add(data[i]);
        }
        avlTree.remove(76);
        avlTree.remove(86);
        avlTree.remove(79);
        avlTree.remove(100);

        BinaryTrees.println(avlTree);
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
        BinarySearchTree<Integer> bst = new BinarySearchTree<>();
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
        BinarySearchTree<Integer> bst = new BinarySearchTree<>();
        for (int i = 0; i < 20; i++) {
            bst.add((int) (Math.random() * 100));
        }

        BinaryTrees.println(bst);
        System.out.println("BST height is(using recursion): " + bst.heightRecursion());
        System.out.println("BST height is(using iteration): " + bst.heightIteration());
    }

    public static void testIfBSTIsComplete() {
        BinarySearchTree<Integer> bst = new BinarySearchTree<>();
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
        BinarySearchTree<Integer> bst = new BinarySearchTree<>();
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
        bst.preOrder(new BinarySearchTree.Visitor<Integer>() {
            @Override
            public void visit(Integer element) {
                sb.append("_").append(element).append("_");
            }
        });
        sb.append("\n");
        sb.append("Inorder result: ");
        bst.inOrder(new BinarySearchTree.Visitor<Integer>() {
            @Override
            public void visit(Integer element) {
                sb.append("_").append(element).append("_");
            }
        });
        sb.append("\n");
        sb.append("Postorder result: ");
        bst.postOrder(new BinarySearchTree.Visitor<Integer>() {
            @Override
            public void visit(Integer element) {
                sb.append("_").append(element).append("_");
            }
        });
        sb.append("\n");
        sb.append("LevelOrder result: ");
        bst.levelOrder(new BinarySearchTree.Visitor<Integer>() {
            @Override
            public void visit(Integer element) {
                sb.append("_").append(element).append("_");
            }
        });
        sb.append("\n");

        System.out.println(sb.toString());
        System.out.println(bst);
    }

    public static void testForBinaryTree1() {
        BinarySearchTree<Integer> bst1 = new BinarySearchTree<>();
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
        BinarySearchTree<Person> bst = new BinarySearchTree<>(new Comparator<Person>() {
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
