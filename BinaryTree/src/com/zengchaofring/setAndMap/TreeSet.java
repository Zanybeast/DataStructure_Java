package com.zengchaofring.setAndMap;

import com.zengchaofring.trees.BinaryTree;
import com.zengchaofring.trees.RBTree;

import java.util.Comparator;

/**
 * @ClassName TreeSet
 * @Description TODO
 * @Author carl
 * @Date 2020/9/24 15:17
 * @Version 1.0
 **/
public class TreeSet<E> implements Set<E> {
    private RBTree<E> tree;

    public TreeSet() {
        this(null);
    }

    public TreeSet(Comparator<E> comparator) {
        tree = new RBTree<>(comparator);
    }

    @Override
    public int size() {
        return tree.size();
    }

    @Override
    public boolean isEmpty() {
        return tree.isEmpty();
    }

    @Override
    public void clear() {
        tree.clear();
    }

    @Override
    public boolean contains(E element) {
        return tree.contains(element);
    }

    @Override
    public void add(E element) {
        tree.add(element);
    }

    @Override
    public void remove(E element) {
        tree.remove(element);
    }

    @Override
    public void traversal(Visitor<E> visitor) {
        tree.inOrder(new BinaryTree.Visitor<E>() {
            @Override
            public boolean visit(E element) {
                return visitor.visit(element);
            }
        });
    }
}
