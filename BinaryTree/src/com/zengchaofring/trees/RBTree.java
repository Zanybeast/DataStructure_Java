package com.zengchaofring.trees;

import java.util.Comparator;

/**
 * @ClassName RBTree
 * @Description TODO
 * @Author carl
 * @Date 2020/9/23 20:19
 * @Version 1.0
 **/
public class RBTree<E> extends BBST<E> {
    private static final boolean RED = false;
    private static final boolean BLACK = true;

    public RBTree() {
        this(null);
    }

    public RBTree(Comparator<E> comparator) {
        super(comparator);
    }

    @Override
    protected void afterAdd(Node<E> node) {
        Node<E> parent = node.parent;

        if (parent == null) {
            black(node);
            return;
        }

        if (isBlack(parent)) return;

        Node<E> uncle = parent.sibling();
        Node<E> grand = red(parent.parent);

        if (isRed(uncle)) {
            black(parent);
            black(uncle);
            afterAdd(grand);
            return;
        }

        if (parent.isLeftChild()) {
            if (node.isLeftChild()) {
                black(parent);
            } else {
                black(node);
                rotateLeft(parent);
            }
            rotateRight(grand);
        } else {
            if (node.isLeftChild()) {
                black(node);
                rotateRight(parent);
            } else {
                black(parent);
            }
            rotateLeft(grand);
        }

    }

    @Override
    protected void afterRemove(Node<E> node) {
        //如果删除的是红色节点
        if (isRed(node)) {
            black(node);
            return;
        }
        //删除的是根节点
        Node<E> parent  = node.parent;
        if (parent == null) return;

        //删除的是黑色叶子节点【下溢】
        //判断被删除的是左还是右
        boolean left = parent.left == null || node.isLeftChild();
        Node<E> sibling = left ? parent.right : parent.left;

        if (left) {
            if (isRed(sibling)) {
                black(sibling);
                red(parent);
                rotateLeft(parent);
                //更换兄弟, 变为一般状态
                sibling = parent.right;
            }

            if (isBlack(sibling.left) && isBlack(sibling.right)) {  //兄弟节点没有红色节点, 下溢产生, 父节点向下与兄弟节点合并
                boolean parentBlack = isBlack(parent);
                black(parent);
                red(sibling);
                if (parentBlack) {  //递归调用, 父节点下溢有可能导致祖父节点下溢
                    afterRemove(parent);
                }
            } else {
                if (isBlack(sibling.right)) {
                    rotateRight(sibling);
                    sibling = parent.right;
                }

                colored(sibling, colorOf(parent));
                black(sibling.right);
                black(parent);
                rotateLeft(parent);
            }

        } else {
            if (isRed(sibling)) {
                black(sibling);
                red(parent);
                rotateRight(parent);
                //更换兄弟, 变为一般状态
                sibling = parent.left;
            }

            if (isBlack(sibling.left) && isBlack(sibling.right)) {  //兄弟节点没有红色节点, 下溢产生, 父节点向下与兄弟节点合并
                boolean parentBlack = isBlack(parent);
                black(parent);
                red(sibling);
                if (parentBlack) {  //递归调用, 父节点下溢有可能导致祖父节点下溢
                    afterRemove(parent);
                }
            } else {
                if (isBlack(sibling.left)) {
                    rotateLeft(sibling);
                    sibling = parent.left;
                }

                colored(sibling, colorOf(parent));
                black(sibling.left);
                black(parent);
                rotateRight(parent);
            }
        }

    }

    /*
     * @Author carl
     * @Description 关于节点颜色的辅助方法
     * @Date 21:03 2020/9/23
     * @Param [node, color]
     * @Return com.zengchaofring.trees.BinaryTree.Node<E>
     **/
    private Node<E> colored(Node<E> node, boolean color) {
        if (node == null) return node;
        ((RBNode<E>)node).color = color;
        return node;
    }

    private Node<E> red(Node<E> node) {
        return colored(node, RED);
    }

    private Node<E> black(Node<E> node) {
        return colored(node, BLACK);
    }

    private boolean colorOf(Node<E> node) {
        return node == null ? BLACK : ((RBNode<E>)node).color;
    }

    private boolean isBlack(Node<E> node) {
        return colorOf(node) == BLACK;
    }

    private boolean isRed(Node<E> node) {
        return colorOf(node) == RED;
    }

    /*
     * @Author carl
     * @Description 红黑树特有的 Node 类
     * @Date 21:04 2020/9/23
     * @Param
     * @Return
     **/
    //返回该类特有的 Node
    @Override
    protected Node createNode(Object element, Node parent) {
        return new RBNode<>(element, parent);
    }

    private static class RBNode<E> extends Node<E> {
        boolean color = RED;
        public RBNode(E element, Node<E> parent) {
            super(element, parent);
        }

        @Override
        public String toString() {
            String str = "";
//            if (parent != null)
//                str = str + " p(" + parent.element.toString() + ")_";
            if (color == RED) {
                str = "R_";
            }
            return str + element.toString();
        }
    }
}
