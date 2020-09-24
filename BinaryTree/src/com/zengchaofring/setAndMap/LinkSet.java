package com.zengchaofring.setAndMap;


import com.zengchaofring.trees.BinaryTree;

import java.util.LinkedList;
import java.util.List;

/**
 * @ClassName LinkSet
 * @Description TODO
 * @Author carl
 * @Date 2020/9/24 14:49
 * @Version 1.0
 **/
public class LinkSet<E> implements Set<E> {
    private List<E> list = new LinkedList<>();

    public int size() {
        return list.size();
    }

    public boolean isEmpty() {
        return list.isEmpty();
    }

    public void clear() {
        list.clear();
    }

    public boolean contains(E element) {
        return list.contains(element);
    }

    public void add(E element) {
        int index = list.indexOf(element);
        if (index != -1) {
            list.set(index, element);
        } else {
            list.add(element);
        }
    }

    public void remove(E element) {
        int index = list.indexOf(element);
        if (index != -1) {
            list.remove(index);
        }
    }

    @Override
    public void traversal(Visitor<E> visitor) {
        if (visitor == null) return;

        int size = list.size();
        for (int i = 0; i < size; i++) {
            if (visitor.visit(list.get(i))) return;
        }
    }

}
