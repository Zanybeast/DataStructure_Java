package com.zengchaofring.heap;

import java.util.Comparator;

/**
 * @ClassName AbstractHeap
 * @Description TODO
 * @Author carl
 * @Date 2020/9/26 23:20
 * @Version 1.0
 **/
public abstract class AbstractHeap<E> implements Heap<E> {
    protected int size;
    protected Comparator<E> comparator;

    public AbstractHeap(Comparator<E> comparator) {
        this.comparator = comparator;
    }

    public AbstractHeap() {
        this(null);
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    protected int compare(E e1, E e2) {
        return comparator != null ? comparator.compare(e1, e2)
                : ((Comparable<E>)e1).compareTo(e2);
    }
}
