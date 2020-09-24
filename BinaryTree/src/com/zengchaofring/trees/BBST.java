package com.zengchaofring.trees;

import java.util.Comparator;

/**
 * @ClassName BBST
 * @Description TODO
 * @Author carl
 * @Date 2020/9/23 20:10
 * @Version 1.0
 **/
public class BBST<E> extends BST<E> {
    public BBST() {
        this(null);
    }

    public BBST(Comparator<E> comparator) {
        super(comparator);
    }

    protected void rotateRight(Node<E> grand) {
        Node<E> parent = grand.left;
        Node<E> child = parent.right;

        grand.left = child;
        parent.right = grand;
        afterRotate(grand, parent, child);
    }

    protected void rotateLeft(Node<E> grand) {
        Node<E> parent = grand.right;
        Node<E> child = parent.left;

        grand.right = child;
        parent.left = grand;
        afterRotate(grand, parent, child);
    }

    protected void afterRotate(Node<E> grand, Node<E> parent, Node<E> child) {
        parent.parent = grand.parent;
        if (grand.isLeftChild()) {
            grand.parent.left = parent;
        } else if (grand.isRightChild()) {
            grand.parent.right = parent;
        } else {
            root = parent;
        }

        if (child != null) {
            child.parent = grand;
        }

        grand.parent = parent;
    }
    /*
     * @Author carl
     * @Description 通用统一情况的旋转
     * @Date 20:15 2020/9/23
     * @Param [r, b, c, d, e, f]
     * @Return void
     **/
    protected void rotate(
            Node<E> r,
            Node<E> b, Node<E> c,
            Node<E> d,
            Node<E> e, Node<E> f
    ) {
        d.parent = r.parent;
        if (r.isLeftChild()) {
            r.parent.left = d;
        } else if (r.isRightChild()) {
            r.parent.right = d;
        } else {
            root = d;
        }

        b.right = c;
        if (c != null) {
            c.parent = b;
        }

        f.left = e;
        if (e != null) {
            e.parent = f;
        }

        d.left = b;
        d.right = f;
        b.parent = d;
        f.parent = d;

    }
}
