package com.company;

public class ArrayList {
    /*
    * 元素数量
    * */
    private int size;
    /*
    * 元素容器
    * */
    private int[] elements;
    /*
    * Constants
    * */
    private static final int DEFAULT_CAPACITY = 10;
    private static final int ELEMENT_NOT_FOUND = -1;

    /*
    * Constructor
    * */
    public ArrayList(int capacity) {
        capacity = Math.max(capacity, DEFAULT_CAPACITY);
        elements = new int[capacity];
    }

    public ArrayList() {
        this(DEFAULT_CAPACITY);
    }
    /*
    * Elements count
    * */
    public int size() {
        return size;
    }
    /*
    * Clear all elements
    * */
    public void clear() {
        size = 0;
    }
    /*
    * Judge if is empty
    * */
    public boolean isEmpty() {
        return size == 0;
    }
    /*
    * To see if there's an element
    * */
    public boolean contains(int e) {
        for (int i = 0; i < size; i++) {
            if (elements[i] == e) {
                return true;
            }
        }
        return false;
    }
    /*
    * Add element
    * */
    public void add(int e) {
        elements[size] = e;
        size++;
    }

    public void add(int index, int e) {

    }
    /*
    * Get an element from index
    * */
    public int get(int index) {
        if (index >= size || index < 0) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size：" + size);
        }
        return elements[index];
    }
    /*
    * Set the element to the specific index
    * */
    public int set(int index, int e) {
        int oldElement = elements[index];
        elements[index] = e;
        return  oldElement;
    }
    /*
    * Remove the specific position element
    * */
    public int remove(int index) {
        return -1;
    }
    /*
    * Find the element's index in the array
    * */
    public int indexOf(int e) {
        for (int i = 0; i < size; i++) {
            if (elements[i] == e)
                return i;
        }
        return ELEMENT_NOT_FOUND;
    }


}
