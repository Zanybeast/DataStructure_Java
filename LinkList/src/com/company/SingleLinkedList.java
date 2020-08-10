package com.company;

public class SingleLinkedList<E> extends AbstractArrayList<E> {
    private Node<E> first;

    private static class Node<E> {
        E element;
        Node<E> next;

        public Node(E element, Node<E> next) {
            this.element = element;
            this.next = next;
        }
    }

    @Override
    public void clear() {
        first = null;
        size = 0;
    }

    @Override
    public int indexOf(E e) {
        if (e == null) {
            Node<E> node = first;
            for (int i = 0; i < size; i++) {
                if (node.element == null) return i;

                node = node.next;
            }
        } else {
            Node<E> node = first;
            for (int i = 0; i < size; i++) {
                if (e.equals(node.element)) return i;

                node = node.next;
            }
        }

        return ELEMENT_NOT_FOUND;
    }

    private Node<E> node(int index) {
        rangeCheck(index);

        Node<E> node = first;
        for (int i = 0; i < index; i++) {
            node = node.next;
        }
        return node;
    }

    @Override
    public void add(int index, E e) {
        rangeCheckForAdd(index);

        if (index == 0) {
            first = new Node<>(e, first);
        } else {
            Node<E> prev = node(index - 1);
            prev.next = new Node<>(e, prev.next);
        }

        size++;
    }

    @Override
    public E get(int index) {
        return node(index).element;
    }

    @Override
    public E set(int index, E e) {
        Node<E> node = node(index);
        E oldElement = node.element;
        node.element = e;

        return oldElement;
    }

    @Override
    public E remove(int index) {
        rangeCheck(index);

        Node<E> node = first;
        if (index == 0) {
            first = first.next;
        } else {
            Node<E> prev = node(index - 1);
            node = prev.next;
            prev.next = node.next;
        }

        size--;

        return node.element;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("SingleLinkedList::Size = ").append(size).append(", [");
        for (int i = 0; i < size; i++) {
            if (i != 0) {
                sb.append(", ");
            }
            if (i < size - 1) {
                sb.append(node(i).element).append("->").append(node(i + 1).element);
            } else {
                sb.append(node(i).element).append("->").append("null");
            }

        }
        sb.append("]");

        return sb.toString();
    }
}
