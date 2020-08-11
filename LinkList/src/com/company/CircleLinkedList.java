package com.company;

public class CircleLinkedList<E> extends AbstractArrayList<E> {
    private Node<E> first;
    private Node<E> last;

    /*
    * 为约瑟夫环问题而维护的东西
    * 也是最大程度发挥循环链表的功能
    * */
    private Node<E> current;

    public void reset() {
        current = first;
    }

    public E next() {
        if (current == null) return null;
        current = current.next;
        return current.element;
    }

    public E remove() {
        if (current == null) return null;

        Node<E> next = current.next;
        E element = remove(current);
        if (size == 0) {
            current = null;
        } else {
            current = next;
        }
        return element;
    }

    /*
     * 内部类*/
    private static class Node<E> {
        E element;
        Node<E> next;
        Node<E> prev;

        public Node(Node<E> prev, E element, Node<E> next) {
            this.element = element;
            this.next = next;
            this.prev = prev;
        }

        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder();
            if (prev != null) {
                sb.append(prev.element);
            } else {
                sb.append("null");
            }

            sb.append("<-").append("(").append(element).append(")").append("->");

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
        size = 0;
        first = null;
        last = null;
    }

    @Override
    public int indexOf(E element) {
        if (element == null) {
            Node<E> node = first;
            for (int i = 0; i < size; i++) {
                if (node.element == null) return i;
                node = node.next;
            }
        } else {
            Node<E> node = first;
            for (int i = 0; i < size; i++) {
                if (node.element.equals(element)) return i;
                node = node.next;
            }
        }
        return ELEMENT_NOT_FOUND;
    }

    @Override
    public E get(int index) {
        return node(index).element;
    }

    @Override
    public E set(int index, E element) {
        Node<E> old = node(index);
        E oldElement = old.element;
        old.element = element;
        return oldElement;
    }

    @Override
    public void add(int index, E element) {
        rangeCheckForAdd(index);

        if (index == size) {
            Node<E> oldLast = last;
            last = new Node<>(oldLast, element, first);
            if (oldLast == null) {
                first = last;
                last.next = last;
                last.prev = last;
            } else {
                oldLast.next = last;
                first.prev = last;
            }
        } else {
            Node<E> next = node(index);
            Node<E> prev = next.prev;
            Node<E> node = new Node<>(prev, element, next);
            next.prev = node;
            prev.next = node;

            if (next == first) {
                first = node;
            }
        }

        size++;
    }

    @Override
    public E remove(int index) {
        rangeCheck(index);

        Node<E> node = node(index);

        Node<E> prev = node.prev;
        Node<E> next = node.next;
        prev.next = next;
        next.prev = prev;

        if (node == first) {
            first = next;
        }

        if (node == last) {
            last = prev;
        }

        size--;
        return node.element;
    }

    /*
    * 传入一个结点来删除*/
    private E remove(Node<E> node) {
        if (size == 1) {
            first = null;
            last = null;
        } else {
            Node<E> prev = node.prev;
            Node<E> next = node.next;
            prev.next = next;
            next.prev = prev;

            if (node == first) {
                first = next;
            }

            if (node == last) {
                last = prev;
            }
        }



        size--;
        return node.element;
    }

    private Node<E> node(int index) {
        rangeCheck(index);

        if (index > (size >> 1)) {
            Node<E> node = last;
            for (int i = size - 1; i > index; i--) {
                node = node.prev;
            }

            return node;
        } else {
            Node<E> node = first;
            for (int i = 0; i < index; i++) {
                node = node.next;
            }

            return node;
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("CircleLinkedList::Size = ").append(size);
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
