package com.company;

public abstract class  AbstractArrayList<E> implements List<E> {
    /*
     * 元素数量
     * */
    protected int size;
    /*
     * Elements count
     * */
    public int size() {
        return size;
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
    public boolean contains(E element) {
        return indexOf(element) != ELEMENT_NOT_FOUND;
    }

    public void add(E element) {
        add(size, element);
    }

    /****************
     * @Description: 当边界条件不满足时抛出异常
     * @Param: [index]
     * @return: void
     * @Author: Carl
     * @Date: 2020/7/2
     ****************/
    protected void outOfBounds(int index) {
        throw new IndexOutOfBoundsException("Index: " + index + ", Size = " + size);
    }
    /****************
     * @Description: 检查边界条件
     * @Param: [index]
     * @return: void
     * @Author: Carl
     * @Date: 2020/7/2
     ****************/
    protected void rangeCheck(int index) {
        if (index < 0 || index >= size) {
            outOfBounds(index);
        }
    }
    /****************
     * @Description: 检查边界条件(给 Add 方法)
     * @Param: [index]
     * @return: void
     * @Author: Carl
     * @Date: 2020/7/2
     ****************/
    protected void rangeCheckForAdd(int index) {
        if (index < 0 || index > size) {
            outOfBounds(index);
        }
    }
}
