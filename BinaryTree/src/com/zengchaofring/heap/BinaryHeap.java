package com.zengchaofring.heap;

import com.zengchaofring.tools.printer.BinaryTreeInfo;

import java.util.Comparator;

/**
 * @ClassName BinaryHeap
 * @Description TODO
 * @Author carl
 * @Date 2020/9/26 23:23
 * @Version 1.0
 **/
public class BinaryHeap<E> extends AbstractHeap<E> implements BinaryTreeInfo {
    private E[] elements;
    private static final int DEFAULT_CAPACITY = 10;

    public BinaryHeap(E[] elements, Comparator<E> comparator) {
        super(comparator);

        if (elements == null || elements.length == 0) {
            this.elements = (E[]) new Object[DEFAULT_CAPACITY];
        } else {
            size = elements.length;
            int capacity = Math.max(elements.length, DEFAULT_CAPACITY);
            this.elements = (E[]) new Object[capacity];
            for (int i = 0; i < elements.length; i++) {
                this.elements[i] = elements[i];
            }
            heapify();
        }
    }

    public BinaryHeap(E[] elements) {
        this(elements, null);
    }

    public BinaryHeap(Comparator<E> comparator) {
        this(null, comparator);
    }

    public BinaryHeap() {
        this(null, null);
    }

    @Override
    public void clear() {
        for (int i = 0; i < size; i++) {
            elements[i] = null;
        }
        size = 0;
    }

    @Override
    public void add(E element) {
        elementNotNullCheck(element);
        ensureCapacity(size + 1);
        elements[size++] = element;
        shiftUp(size - 1);
    }

    @Override
    public E get() {
        emptyCheck();
        return elements[0];
    }

    @Override
    public E remove() {
        emptyCheck();

        int lastIndex = --size;
        E root = elements[0];
        elements[0] = elements[lastIndex];
        elements[lastIndex] = null;

        shiftDown(0);
        return root;
    }


    @Override
    public E replace(E element) {
        elementNotNullCheck(element);

        E root = null;
        if (size == 0) {
            elements[0] = element;
            size++;
        } else {
            root = elements[0];
            elements[0] = element;
            shiftDown(0);
        }
        return root;
    }

    /**********************
     *  批量建堆
     ***********************/
    private void heapify() {
        //自下而上的下滤
        for (int i = (size >> 1) - 1; i >= 0; i--) {    //从第一个叶子节点的索引开始，该索引从二叉堆性质来说是二叉堆节点数量的一半减 1
            shiftDown(i);
        }
    }

    /**********************
     *  下滤
     ***********************/
    private void shiftDown(int index) {
        E element = elements[index];
        int half = size >> 1;

        while (index < half) {
            //index的节点有2种情况
            //1. 只有左字节点
            //2. 同时有左右子节点
            int childIndex = (index << 1) + 1;   //某个节点的左子节点的索引，二叉堆性质和公式得出
            E child = elements[childIndex];

            int rightIndex = childIndex + 1;

            if (rightIndex < size && compare(elements[rightIndex], child) > 0) {
                child = elements[childIndex = rightIndex];
            }

            if (compare(element, child) >= 0) break;

            elements[index] = child;

            index = childIndex;
        }
        elements[index] = element;
    }

    /**********************
     *  上滤
     ***********************/
    private void shiftUp(int index) {
        E element = elements[index];

        while (index > 0) {
            int parentIndex = (index - 1) >> 1;
            E parent = elements[parentIndex];
            if (compare(element, parent) <= 0) break;

            elements[index] = parent;

            index = parentIndex;
        }
        elements[index] = element;
    }

    /**********************
     *  辅助方法
     ***********************/
    private void elementNotNullCheck(E element) {
        if (element == null)
            throw new IllegalArgumentException("element must not be null");
    }

    private void emptyCheck() {
        if (size == 0) {
            throw new IndexOutOfBoundsException("Heap is empty");
        }
    }

    private void ensureCapacity(int capacity) {
        int oldCapacity = elements.length;
        if (oldCapacity >= capacity) return;

        // 新容量为旧容量的1.5倍
        int newCapacity = oldCapacity + (oldCapacity >> 1);
        E[] newElements = (E[]) new Object[newCapacity];
        for (int i = 0; i < size; i++) {
            newElements[i] = elements[i];
        }
        elements = newElements;
    }

    /**********************
     *  打印二叉树所需的辅助信息
     ***********************/
    @Override
    public Object root() {
        return 0;
    }

    @Override
    public Object left(Object node) {
        int index = ((int)node << 1) + 1;
        return index >= size ? null : index;
    }

    @Override
    public Object right(Object node) {
        int index = ((int)node << 1) + 2;
        return index >= size ? null : index;
    }

    @Override
    public Object string(Object node) {
        return elements[(int)node];
    }
}
