package com.company;

public class SingleLinkedListVirtualHeader<E> extends AbstractArrayList<E>{
    private Node<E> first;
    /*
    * 带虚拟头结点的构造函数
    * */

    public SingleLinkedListVirtualHeader() {
        first = new Node<>(null, null);
    }

    /*
    * 内部结点类
    * */
    private static class Node<E> {
        E element;
        Node<E> next;

        public Node(E element, Node<E> next) {
            this.element = element;
            this.next = next;
        }

        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder();
            sb.append("(").append(element).append(")").append("->");
            if (next != null) {
                sb.append(next.element);
            } else {
                sb.append("null");
            }

            return sb.toString();
        }
    }

    @Override
    public void clear() {
        first = null;
        size = 0;
    }

    @Override
    public int indexOf(E element) {
        if (element == null) {
             Node<E> node = first.next;
            for (int i = 0; i < size; i++) {
                if (node.element == null) return i;

                node = node.next;
            }
        } else {
            Node<E> node = first.next;
            for (int i = 0; i < size; i++) {
                if (element.equals(node.element)) return i;

                node = node.next;
            }
        }

        return ELEMENT_NOT_FOUND;
    }

    private Node<E> node(int index) {
        rangeCheck(index);

        Node<E> node = first.next;
        for (int i = 0; i < index; i++) {
            node = node.next;
        }
        return node;
    }

    @Override
    public void add(int index, E element) {
        rangeCheckForAdd(index);

        Node<E> prev = index == 0 ? first : node(index - 1);
        prev.next = new Node<>(element, prev.next);

        size++;
    }

    @Override
    public E get(int index) {
        return node(index).element;
    }

    @Override
    public E set(int index, E element) {
        Node<E> node = node(index);
        E oldElement = node.element;
        node.element = element;

        return oldElement;
    }

    @Override
    public E remove(int index) {
        rangeCheck(index);

        Node<E> prev = index == 0? first : node(index - 1);
        Node<E> node = prev.next;
        prev.next = node.next;

        size--;

        return node.element;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("SingleLinkedListVirtualHeader::Size = ").append(size);
        sb.append(" Straight look: ").append("[");
        for (int i = 0; i < size; i++) {
            if (i != 0) {
                sb.append(", ");
            }
            sb.append(node(i).element);
        }
        sb.append("]").append("\n               More Specific: ");
        sb.append(", [");
        for (int i = 0; i < size; i++) {
            if (i != 0) {
                sb.append(", ");
            }
            sb.append(node(i));
        }
        sb.append("]");

        return sb.toString();
    }
}
