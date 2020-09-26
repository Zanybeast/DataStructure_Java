package com.zengchaofring.setAndMap;

import java.util.Objects;

/**
 * @ClassName LinkedHashMap
 * @Description TODO
 * @Author carl
 * @Date 2020/9/26 22:33
 * @Version 1.0
 **/
public class LinkedHashMap<K, V> extends HashMap<K, V> {
    private LinkedNode<K, V> first;
    private LinkedNode<K, V> last;

    @Override
    public void clear() {
        super.clear();
        first = null;
        last = null;
    }

    @Override
    public boolean containsValue(V value) {
        LinkedNode<K, V> node = first;
        while (node != null) {
            if (Objects.equals(node.value, value)) return true;
            node = node.next;
        }
        return false;
    }

    @Override
    public void traversal(Visitor<K, V> visitor) {
        if (visitor == null) return;
        LinkedNode<K, V> node = first;
        while (node != null) {
            if (visitor.visit(node.key, node.value)) return;
            node = node.next;
        }
    }

    @Override
    protected void afterRemove(Node<K, V> willNode, Node<K, V> removedNode) {
        //删除节点后，链表结构会改变，需要修复
        LinkedNode<K, V> node1 = (LinkedNode<K, V>) willNode;
        LinkedNode<K, V> node2 = (LinkedNode<K, V>) removedNode;

        if (node1 != node2) {   //如果两节点不一样，说明删除的是度为2的节点，需要交换被删除节点和该节点的前驱或者后继节点
            //交换prev指针
            LinkedNode<K, V> tmp = node1.prev;
            node1.prev = node2.prev;
            node2.prev = tmp;
            if (node1.prev == null) {   //指针交换之后，如果该指针为空，说明之前node2是头节点，需要将first指向node1
                first = node1;
            } else {
                node1.prev.next = node1;
            }
            if (node2.prev == null) {
                first = node2;
            } else {
                node2.prev.next = node2;
            }

            //交换next
            tmp = node1.next;
            node1.next = node2.next;
            node2.next = tmp;
            if (node1.next == null) {   //此处说明next指针交换后如果有一个是空，说明交换前node2指针是最后节点，其next指向last
                last = node1;
            } else {
                node1.next.prev = node1;
            }
            if (node2.next == null) {
                last = node2;
            } else {
                node2.next.prev = node2;
            }
        }

        //来到这里，被删除的是度为 1 或者叶子节点
        LinkedNode<K, V> prev = node2.prev;
        LinkedNode<K, V> next = node2.next;
        if (prev == null) {
            first = next;
        } else {
            prev.next = next;
        }

        if (next == null) {
            last = prev;
        } else {
            next.prev = prev;
        }
    }

    @Override
    protected Node<K, V> createNode(K key, V value, Node<K, V> parent) {
        LinkedNode<K, V> node = new LinkedNode<>(key, value, parent);
        if (first == null) {
            first = node;
            last = node;
        } else {
            last.next = node;
            node.prev = last;
            last = node;
        }

        return node;
    }

    private static class LinkedNode<K, V> extends Node<K, V> {
        LinkedNode<K, V> prev;
        LinkedNode<K, V> next;
        public LinkedNode(K key, V value, Node<K, V> parent) {
            super(key, value, parent);
        }
    }
}
