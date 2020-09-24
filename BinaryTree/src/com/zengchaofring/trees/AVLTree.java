package com.zengchaofring.trees;

import java.util.Comparator;

/**
 * @ClassName AVLTree
 * @Description TODO
 * @Author carl
 * @Date 2020/8/25 11:19
 * @Version 1.0
 **/
public class AVLTree<E> extends BBST<E> {
    private Object element;
    private Node parent;

    /*
     * @Author carl
     * @Description 初始化方法
     * @Date 11:21 2020/8/25
     * @Param []
     * @Return 
     **/
    public AVLTree() {
        this(null);
    }

    public AVLTree(Comparator<E> comparator) {
        super(comparator);
    }

    @Override
    protected Node createNode(Object element, Node parent) {
        return new AVLNode(element, parent);
    }
    /*
     * @Author carl
     * @Description 增加节点后的处理, 恢复平衡
     * @Date 23:34 2020/9/1
     * @Param [node]
     * @Return void
     **/
    @Override
    protected void afterAdd(Node<E> node) {
        super.afterAdd(node);
        //不停往上找, 找到最近的失衡节点
        //所以当找到的时候, 它已经是祖父节点了(父节点不可能失衡)
        while ((node = node.parent) != null) {
            if (isBalanced(node)) {
                updateHeight(node);
            } else {
                rebalanced(node);
                break;
            }
        }
    }
    /*
     * @Author carl
     * @Description 删除节点后的处理
     * @Date 23:34 2020/9/1
     * @Param [node]
     * @Return void
     **/
    @Override
    protected void afterRemove(Node<E> node) {
        super.afterRemove(node);
        /*删除节点有可能会导致所有的父节点失衡, 所以循环不能停, 直到遍历完所有父节点*/
        while ((node = node.parent) != null) {
            if (isBalanced(node)) {
                updateHeight(node);
            } else {
                rebalanced(node);
            }
        }
    }

    /*
     * @Author carl
     * @Description 恢复平衡方法, 这里提供两种方法
     * @Date 20:32 2020/9/1
     * @Param [node]
     * @Return void
     **/
    private void rebalanced(Node<E> node) {
        rebalanced2(node);
    }
    /*
     * @Author carl
     * @Description 第一种恢复平衡的方法, 分开处理四种旋转情况
     * @Date 22:37 2020/9/1
     * @Param [grand]
     * @Return void
     **/
    private void rebalanced1(Node<E> grand) {
        Node<E> parent = ((AVLNode<E>)grand).tallerChild();
        Node<E> node = ((AVLNode<E>)parent).tallerChild();

        if (parent.isLeftChild()) {
            if (node.isLeftChild()) {   //LL
                rotateRight(grand);
            } else {        //LR
                rotateLeft(parent);
                rotateRight(grand);
            }
        } else {
            if (node.isLeftChild()) {   //RL
                rotateRight(parent);
                rotateLeft(grand);
            } else {        //RR
                rotateLeft(grand);
            }
        }
    }

    /*
     * @Author carl
     * @Description AVLTree特有的恢复平衡的处理
     * @Date 20:16 2020/9/23
     * @Param
     * @Return
     **/
    @Override
    protected void afterRotate(Node<E> grand, Node<E> parent, Node<E> child) {
        super.afterRotate(grand, parent, child);

        //更新高度, 顺序不能乱
        updateHeight(grand);
        updateHeight(parent);
    }

    @Override
    protected void rotate(Node<E> r, Node<E> b, Node<E> c, Node<E> d, Node<E> e, Node<E> f) {
        super.rotate(r, b, c, d, e, f);

        updateHeight(b);
        updateHeight(f);
        updateHeight(d);
    }

    /*
     * @Author carl
     * @Description 另一种恢复平衡的方法, 将四种方法归纳通用
     * @Date 23:16 2020/9/1
     * @Param [node]
     * @Return void
     **/
    private void rebalanced2(Node<E> grand) {
        Node<E> parent = ((AVLNode<E>)grand).tallerChild();
        Node<E> node = ((AVLNode<E>)parent).tallerChild();

        if (parent.isLeftChild()) {
            if (node.isLeftChild()) {   //LL
                rotate(grand, node, node.right, parent, parent.right, grand);
            } else {        //LR
                rotate(grand, parent, node.left, node, node.right, grand);
            }
        } else {
            if (node.isLeftChild()) {   //RL
                rotate(grand, grand, node.left, node, node.right, parent);
            } else {        //RR
                rotate(grand, grand, parent.left, parent, node.left, node);
            }
        }
    }


    /*
     * @Author carl
     * @Description 判断是否平衡
     * @Date 20:26 2020/9/1
     * @Param [node]
     * @Return boolean
     **/
    private boolean isBalanced(Node<E> node) {
        return Math.abs(((AVLNode<E>)node).balanceFactor()) <= 1;
    }
    /*
     * @Author carl
     * @Description 更新节点高度
     * @Date 20:26 2020/9/1
     * @Param [node]
     * @Return void
     **/
    private void updateHeight(Node<E> node) {
        ((AVLNode<E>)node).updateHeight();
    }

    /*
     * @Author carl
     * @Description 特有 Node 内部类, 继承自 Node
     * @Date 20:22 2020/9/1
     * @Param
     * @Return
     **/
    private static class AVLNode<E> extends Node<E> {
        int height = 1;

        public AVLNode(E element, Node<E> parent) {
            super(element, parent);
        }

        public int balanceFactor() {
            int leftHeight = left == null ? 0 : ((AVLNode<E>)left).height;
            int rightHeight = right == null ? 0 : ((AVLNode<E>)right).height;
            return leftHeight - rightHeight;
        }

        public void updateHeight() {
            int leftHeight = left == null ? 0 : ((AVLNode<E>)left).height;
            int rightHeight = right == null ? 0 : ((AVLNode<E>)right).height;
            height = 1 + Math.max(leftHeight, rightHeight);
        }

        public Node<E> tallerChild() {
            int leftHeight = left == null ? 0 : ((AVLNode<E>)left).height;
            int rightHeight = right == null ? 0 : ((AVLNode<E>)right).height;
            if (leftHeight > rightHeight) return left;
            if (leftHeight < rightHeight) return right;
            return isLeftChild() ? left : right;
        }

        @Override
        public String toString() {
            String parentString = "null";
            if (parent != null) {
                parentString = parent.element.toString();
            }

            return element + "_p(" + parentString + ")_h(" + height + ")";
        }
    }
}
