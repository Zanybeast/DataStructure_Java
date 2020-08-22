package com.company;

public class CircleDeque<E> {
    private int front;
    private int size;
    private E[] elements;
    private static final int DEFAULT_CAPACITY = 10;

    public CircleDeque() {
        elements = (E[]) new Object[DEFAULT_CAPACITY];
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    /*
     * @Author carl
     * @Description 清楚所有元素
     * @Date 18:47 2020/8/22
     * @Param []
     * @Return void
     **/
    public void clear() {
        for (int i = 0; i < size; i++) {
            elements[index(i)] = null;
        }
        front = 0;
        size = 0;
    }
    /*
     * @Author carl
     * @Description 从头部插入元素
     * @Date 18:50 2020/8/22
     * @Param [element]
     * @Return void
     **/
    public void enQueueFront(E element) {
        ensureCapacity(size + 1);

        front = index(-1);
        elements[front] = element;
        size++;
    }

    /*
     * @Author carl
     * @Description 从尾部插入元素
     * @Date 18:48 2020/8/22
     * @Param [element]
     * @Return void
     **/
    public void enQueueRear(E element) {
        ensureCapacity(size + 1);

        elements[index(size)] = element;
        size++;
    }

    /*
     * @Author carl
     * @Description 头部出队
     * @Date 18:52 2020/8/22
     * @Param []
     * @Return 未出队前的头部元素
     **/
    public E deQueueFront() {
        E oldElement = elements[front];

        elements[front] = null;
        front = index(1);
        size--;
        return oldElement;
    }

    /*
     * @Author carl
     * @Description 尾部出队
     * @Date 18:54 2020/8/22
     * @Param []
     * @Return 未出队前尾部元素
     **/
    public E deQueueRear() {
        E oldElement = elements[index(size - 1)];

        elements[index(size - 1)] = null;
        size--;
        return oldElement;
    }

    private int index(int index) {
        index += front;
        if (index < 0) {
            return index + elements.length;
        }
        return index - (index >= elements.length ? elements.length : 0);
    }

    /*
     * 保证容量
     * */
    private void ensureCapacity(int capacity) {
        int oldCapacity = elements.length;
        if (oldCapacity >= capacity) return;

        int newCapacity = oldCapacity + (oldCapacity >> 1);
        E[] newElements = (E[]) new Object[newCapacity];
        for (int i = 0; i < size; i++) {
            newElements[i] = elements[index(i)];
        }
        elements = newElements;

        front = 0;
    }

    @Override
    public String toString() {
        StringBuilder string = new StringBuilder();
        string.append("CircleDeque::Capacity=").append(elements.length)
                .append(" size=").append(size)
                .append(" front=").append(front)
                .append(", [");
        for (int i = 0; i < elements.length; i++) {
            if (i != 0) {
                string.append(", ");
            }

            string.append(elements[i]);
        }
        string.append("]");
        return string.toString();
    }
}
