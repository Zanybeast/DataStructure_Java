package com.zengchaofring.trees;

import com.zengchaofring.tools.printer.*;

import java.util.Comparator;
import java.util.LinkedList;
import java.util.Queue;

/**
 * @ClassName BinarySearchTree
 * @Description TODO
 * @Author carl
 * @Date 2020/8/23 02:31
 * @Version 1.0
 **/
@SuppressWarnings("unchecked")
public class BinarySearchTree<E> extends BinaryTree {

    private Comparator<E> comparator;

    public BinarySearchTree() {
        this(null);
    }

    public BinarySearchTree(Comparator<E> comparator) {
        this.comparator = comparator;
    }

    public boolean contains(E element) {
        return node(element) != null;
    }
    /*
     * @Author carl
     * @Description 添加元素
     * @Date 08:40 2020/8/24
     * @Param [element]
     * @Return void
     **/
    protected void afterAdd(Node<E> node) { }

    public void add(E element) {
        elementNotNullCheck(element);

        if (root == null) {
            root = createNode(element, null);
            size++;
            afterAdd(root);
            return;
        }

        Node<E> parent = root;
        Node<E> node = root;
        int cmp = 0;
        while (node != null) {
            cmp = compare(element, node.element);
            parent = node;
            if (cmp > 0) {
                node = node.right;
            } else if (cmp < 0) {
                node = node.left;
            } else {
                return;
            }
        }
        Node<E> newNode = createNode(element, parent);
        if (cmp > 0) {
            parent.right = newNode;
        } else {
            parent.left = newNode;
        }
        size++;

        afterAdd(newNode);
    }
    /*
     * @Author carl
     * @Description 删除元素
     * @Date 08:41 2020/8/24
     * @Param [element]
     * @Return E
     **/
    protected void afterRemove(Node<E> node) { }

    public E remove(E element) {
        return remove(node(element));
    }

    private E remove(Node<E> node) {
        if (node == null) return null;

        if (node.hasTwoChildren()) {
            Node<E> s = successor(node);

            node.element = s.element;

            node = s;
        }

        Node<E> replaceNode = node.left != null ? node.left : node.right;

        if (replaceNode != null) {
            replaceNode.parent = node.parent;

            if (node.parent == null) {
                root = replaceNode;
            } else if (node == node.parent.left) {
                node.parent.left = replaceNode;
            } else {
                node.parent.right = replaceNode;
            }
        } else if (node.parent == null) {
             root = null;

             afterRemove(node);
        } else {
            if (node == node.parent.left) {
                node.parent.left = null;
            } else {
                node.parent.right = null;
            }

            afterRemove(node);
        }

        size--;

        return node.element;
    }

    private Node<E> node(E element) {
        if (element == null) return null;

        Node<E> node = root;

        while (node != null) {
            int cmp = compare(element, node.element);
            if (cmp > 0) {
                node = node.right;
            } else if (cmp < 0) {
                node = node.left;
            } else {
                return node;
            }
        }

        return null;
    }

    /*
     * @Author carl
     * @Description 利用前序遍历打印二叉树
     * @Date 15:06 2020/8/23
     * @Param []
     * @Return java.lang.String
     **/
//    @Override
//    public String toString() {
//        StringBuilder sb = new StringBuilder();
//        toString(sb, root, "");
//        return sb.toString();
//    }
//
//    private void toString(StringBuilder sb, Node<E> node, String prefix) {
//        if (node == null) return;
//
//        sb.append(prefix).
//                append("【").append(node.element).append("】").append("\n");
//        toString(sb, node.left, prefix + "〖L〗");
//        toString(sb, node.right, prefix + "〖R〗");
//    }



    /*
     * @Author carl
     * @Description 比较器
     * @Date 14:25 2020/8/23
     * @Param [e1, e2]
     * @Return int
     **/
    private int compare(E e1, E e2) {
        if (comparator != null) {
            return comparator.compare(e1, e2);
        }
        return ((Comparable<E>)e1).compareTo(e2);
    }

    /*
     * @Author carl
     * @Description 检测元素是否为空
     * @Date 14:25 2020/8/23
     * @Param [element]
     * @Return void
     **/
    private void elementNotNullCheck(E element) {
        if (element == null) {
            throw new IllegalArgumentException("Element can not be null.");
        }
    }


}
