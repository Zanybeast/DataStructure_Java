package com.zengchaofring.trees;

import com.zengchaofring.classes.*;
import com.zengchaofring.tools.BinaryTrees;

import java.util.Comparator;

/**
 * @ClassName Main
 * @Description TODO
 * @Author carl
 * @Date 2020/8/23 02:25
 * @Version 1.0
 **/
public class Main {
    public static void main(String[] args) {
        
//        testForBSTUsingPerson();
//        testForBinaryTree1();
    }

    public static void testForBinaryTree1() {
        BinarySearchTree<Integer> bst1 = new BinarySearchTree<>();
        Integer[] data = new Integer[] {
                7, 4, 9, 2, 5, 8, 11, 3, 12, 1
        };

        for (int i = 0; i < data.length; i++) {
            bst1.add(data[i]);
        }

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
