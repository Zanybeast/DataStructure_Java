package com.company;

@SuppressWarnings("unchecked")
public class ArrayList<E> extends AbstractArrayList<E> {

    /*
    * 元素容器
    * */
    private E[] elements;
    /*
    * Constants
    * */
    private static final int DEFAULT_CAPACITY = 10;


    /*
    * Constructor
    * */
    public ArrayList(int capacity) {
        capacity = Math.max(capacity, DEFAULT_CAPACITY);
        elements = (E[]) new Object[capacity];
    }

    public ArrayList() {
        this(DEFAULT_CAPACITY);
    }

    /*
     * Clear all elements
     * */
    public void clear() {
        for (int i = 0; i < size; i++) {
            elements[i] = null;
        }
        size = 0;
    }
    /*
     * Find the element's index in the array
     * */
    public int indexOf(E e) {
        if(e == null) {
            for (int i = 0; i < size; i++) {
                if(elements[i] == null) return i;
            }
        } else {
            for (int i = 0; i < size; i++) {
                if (e.equals(elements[i])) return i;
            }
        }
        return ELEMENT_NOT_FOUND;
    }
    /****************
    * @Description: 增加元素
    * @Param: [e]
    * @return: void
    * @Author: Carl
    * @Date: 2020/7/2
    ****************/

    /****************
    * @Description: 根据索引添加元素
    * @Param: [index, e]
    * @return: void
    * @Author: Carl
    * @Date: 2020/7/2
    ****************/
    public void add(int index, E e) {
        rangeCheckForAdd(index);

        ensureCapacity(size + 1);

        for (int i = size; i > index; i--) {
            elements[i] = elements[i - 1];
        }
        elements[index] = e;

        size++;
    }
    /****************
    * @Description: Get the element from specific location
    * @Param: [index]
    * @return: E
    * @Author: Carl Zeng
    * @Date: 2020/7/2
    ****************/
    public E get(int index) {
        rangeCheck(index);

        return elements[index];
    }
    /****************
    * @Description: Set the element to specific location
    * @Param: [index, e]
    * @return: E
    * @Author: Carl Zeng
    * @Date: 2020/7/2
    ****************/
    public E set(int index, E e) {
        rangeCheck(index);
        E oldElement = elements[index];
        elements[index] = e;
        return  oldElement;
    }
    /****************
    * @Description: Remove the element on the specific location
    * @Param: [index]
    * @return: E
    * @Author: Carl Zeng
    * @Date: 2020/7/2
    ****************/
    public E remove(int index) {
        rangeCheck(index);

        E old = elements[index];
        for (int i = index + 1; i < size; i++) {
            elements[i - 1] = elements[i];
        }
        elements[--size] = null;
        return old;
    }
    /****************
    * @Description: Ensure array's capacity to add elements
    * @Param: [capacity]
    * @return: void
    * @Author: Carl
    * @Date: 2020/7/2
    ****************/
    private void ensureCapacity(int capacity) {
        int oldCapacity = elements.length;
        if (oldCapacity >= capacity) return;

        int newCapacity = oldCapacity + (oldCapacity >> 1);
        E[] newElements = (E[]) new Object[newCapacity];

        for (int i = 0; i < size; i++) {
            newElements[i] = elements[i];
        }

        elements = newElements;

        System.out.println("Old Capacity " + oldCapacity + "has been expanded to " + newCapacity);
    }



    /****************
    * @Description: 打印对象
    * @Param: []
    * @return: java.lang.String
    * @Author: Carl
    * @Date: 2020/7/2
    ****************/
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("DynamicArray::Size = ").append(size).append(", [");
        for (int i = 0; i < size; i++) {
            if (i != 0) {
                sb.append(", ");
            }
            sb.append(elements[i]);
        }
        sb.append("]");

        return sb.toString();
    }
}
