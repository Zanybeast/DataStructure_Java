package com.zengchaofring.trees;

import com.zengchaofring.tools.printer.BinaryTreeInfo;

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
public class BinarySearchTree<E> implements BinaryTreeInfo {
    private int size;
    private Node<E> root;
    private Comparator<E> comparator;

    public BinarySearchTree() {
        this(null);
    }

    public BinarySearchTree(Comparator<E> comparator) {
        this.comparator = comparator;
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

    public void add(E element) {
        elementNotNullCheck(element);

        if (root == null) {
            root = new Node<>(element, null);
            size++;
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
        Node<E> newNode = new Node<>(element, parent);
        if (cmp > 0) {
            parent.right = newNode;
        } else {
            parent.left = newNode;
        }
        size++;
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
     * @Description 处理遍历元素的对外接口
     * @Date 14:42 2020/8/23
     * @Param 
     * @Return 
     **/
    public interface Visitor<E> {
        void visit(E element);
    }

    /*
     * @Author carl
     * @Description 利用前序遍历打印二叉树
     * @Date 15:06 2020/8/23
     * @Param []
     * @Return java.lang.String
     **/
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        toString(sb, root, "");
        return sb.toString();
    }

    private void toString(StringBuilder sb, Node<E> node, String prefix) {
        if (node == null) return;

        sb.append(prefix).
                append("【").append(node.element).append("】").append("\n");
        toString(sb, node.left, prefix + "〖L〗");
        toString(sb, node.right, prefix + "〖R〗");
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
    private Node<E> predecessor(Node<E> node) {
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
    private Node<E> successor(Node<E> node) {
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
        return ((Node<E>)node).element;
    }

    private static class Node<E> {
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
            return "Node{}";
        }
        /*是否叶子节点*/
        public boolean isLeaf() {
            return left == null && right == null;
        }

        /*是否有两个节点*/
        public boolean hasTwoChildren() {
            return left != null && right != null;
        }
    }
}
