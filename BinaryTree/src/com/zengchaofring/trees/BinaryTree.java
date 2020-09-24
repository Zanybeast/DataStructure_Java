package com.zengchaofring.trees;

import com.zengchaofring.tools.printer.BinaryTreeInfo;

import java.util.Comparator;
import java.util.LinkedList;
import java.util.Queue;

/**
 * @ClassName BinaryTree
 * @Description TODO
 * @Author carl
 * @Date 2020/8/25 09:10
 * @Version 1.0
 **/
public class BinaryTree<E> implements BinaryTreeInfo {
    protected int size;
    protected Node<E> root;

    public interface Visitor<E> {
        void visit(E element);
    }

    protected Node<E> createNode(E element, Node<E> parent) {
        return new Node<>(element, parent);
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public void clear() {
        root = null;
        size = 0;
    }

    /****************************************************************************
     ****************************************************************************
     *遍历相关
     ****************************************************************************
     ****************************************************************************/
    /*
     * @Author carl
     * @Description 前序遍历
     * @Date 14:34 2020/8/23
     * @Param []
     * @Return void
     **/
    public void preOrder(Visitor<E> visitor) {
        if (visitor == null) return;
        preOrder(root, visitor);
    }

    private void preOrder(Node<E> node, Visitor<E> visitor) {
        if (node == null) return;

        visitor.visit(node.element);
        preOrder(node.left, visitor);
        preOrder(node.right, visitor);
    }
    /*
     * @Author carl
     * @Description 中序遍历
     * @Date 14:36 2020/8/23
     * @Param []
     * @Return void
     **/
    public void inOrder(Visitor<E> visitor) {
        if (visitor == null) return;
        inOrder(root, visitor);
    }

    private void inOrder(Node<E> node, Visitor<E> visitor) {
        if (node == null) return;

        inOrder(node.left, visitor);
        visitor.visit(node.element);
        inOrder(node.right, visitor);

    }
    /*
     * @Author carl
     * @Description 后序遍历
     * @Date 14:49 2020/8/23
     * @Param [node, visitor]
     * @Return void
     **/
    private void postOrder(Node<E> node, Visitor<E> visitor) {
        if (node == null) return;

        postOrder(node.left, visitor);
        postOrder(node.right, visitor);
        visitor.visit(node.element);
    }

    public void postOrder(Visitor<E> visitor) {
        if (visitor == null) return;
        postOrder(root, visitor);
    }
    /*
     * @Author carl
     * @Description 层序遍历
     * @Date 14:56 2020/8/23
     * @Param [node, visitor]
     * @Return void
     **/
    private void levelOrder(Node<E> node, Visitor<E> visitor) {
        if (node == null) return;

        Queue<Node<E>> queue = new LinkedList<>();
        queue.offer(node);

        while (!queue.isEmpty()) {
            node = queue.poll();
            visitor.visit(node.element);
            if (node.left != null) queue.offer(node.left);
            if (node.right != null) queue.offer(node.right);
        }

    }

    public void levelOrder(Visitor<E> visitor) {
        if (visitor == null) return;
        levelOrder(root, visitor);
    }

    /*
     * @Author carl
     * @Description 判断是否为完全二叉树
     * @Date 15:47 2020/8/23
     * @Param []
     * @Return boolean
     **/
    public boolean isComplete() {
        if (root == null) return false;

        Queue<Node<E>> queue = new LinkedList<>();
        queue.offer(root);

        boolean leaf = false;

        while (!queue.isEmpty()) {
            Node<E> node = queue.poll();
            if (leaf && !node.isLeaf()) return false;

            if (node.left != null) {
                queue.offer(node.left);
            } else if (node.right != null) {
                return false;
            }

            if (node.right != null) {
                queue.offer(node.right);
            } else {
                leaf = true;
            }
        }
        return true;
    }

    /*
     * @Author carl
     * @Description 计算二叉树的高度
     * @Date 16:06 2020/8/23
     * @Param []
     * @Return int
     **/
    public int heightRecursion() {
        return getHeightRecursion(root);
    }

    public int heightIteration() {
        return getHeightIteration(root);
    }

    /*递归的办法*/
    private int getHeightRecursion(Node<E> node) {
        if (node == null) return 0;
        return 1 + Math.max(getHeightRecursion(node.left), getHeightRecursion(node.right));
    }

    /*迭代的办法*/
    private int getHeightIteration(Node<E> node) {
        if (node == null) return 0;

        Queue<Node<E>> queue = new LinkedList<>();
        queue.offer(node);
        int height = 0;
        int queueSize = 1;

        while (!queue.isEmpty()) {
            node = queue.poll();

            queueSize--;

            if (node.left != null) queue.offer(node.left);
            if (node.right != null) queue.offer(node.right);

            if (queueSize == 0) {
                height++;
                queueSize = queue.size();
            }
        }

        return height;
    }

    /*
     * @Author carl
     * @Description 前驱节点
     * @Date 16:18 2020/8/23
     * @Param [node]
     * @Return com.zengchaofring.trees.BinarySearchTree.Node<E>
     **/
    protected Node<E> predecessor(Node<E> node) {
        if (node == null) return null;

        Node<E> p = node.left;
        if (p.left != null) {
            while (p.right != null) {
                p = p.right;
            }
            return p;
        }

        while (node.parent != null && node == node.parent.left) {
            node = node.parent;
        }

        return node.parent;
    }

    /*
     * @Author carl
     * @Description 后继节点
     * @Date 16:25 2020/8/23
     * @Param [node]
     * @Return com.zengchaofring.trees.BinarySearchTree.Node<E>
     **/
    protected Node<E> successor(Node<E> node) {
        if (node == null) return null;

        Node<E> c = node.right;
        if (c != null) {
            while (c.left != null) {
                c = c.left;
            }
            return c;
        }

        while (node.parent != null && node == node.parent.right) {
            node = node.parent;
        }

        return node.parent;
    }


    @Override
    public Object root() {
        return root;
    }

    @Override
    public Object left(Object node) {
        return ((Node<E>)node).left;
    }

    @Override
    public Object right(Object node) {
        return ((Node<E>)node).right;
    }

    @Override
    public Object string(Object node) {
//        Node<E> myNode = (Node<E>)node;
//        String parentString = "null";
//        if (myNode.parent != null) {
//            parentString = myNode.parent.element.toString();
//        }
//        return myNode.element + "_p(" + parentString + ")";
        return node;
    }

    protected static class Node<E> {
        E element;
        Node<E> parent;
        Node<E> left;
        Node<E> right;

        public Node(E element, Node<E> parent) {
            this.element = element;
            this.parent = parent;
        }

        @Override
        public String toString() {
            String parentString = "null";
            if (parent != null) {
                parentString = parent.element.toString();
            }

            return element + "_p(" + parentString + ")";
        }
        /*是否叶子节点*/
        public boolean isLeaf() {
            return left == null && right == null;
        }

        /*是否有两个节点*/
        public boolean hasTwoChildren() {
            return left != null && right != null;
        }

        public boolean isLeftChild() {
            return parent != null && this == parent.left;
        }

        public boolean isRightChild() {
            return parent != null && this == parent.right;
        }

        public Node<E> sibling() {
            if (isLeftChild())
                return parent.right;
            if (isRightChild())
                return parent.left;

            return null;
        }
    }
}
