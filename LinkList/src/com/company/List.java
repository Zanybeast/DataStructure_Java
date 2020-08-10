package com.company;

public interface List<E> {
    public static final int ELEMENT_NOT_FOUND = -1;

    public int size() ;

    /*
     * Judge if is empty
     * */
    public boolean isEmpty();
    /*
     * To see if there's an element
     * */
    public boolean contains(E e);
    /*
    * Clear all elements
    * */
    public void clear();
    /*
     * Find the element's index in the array
     * */
    public int indexOf(E e);

    /****************
     * @Description: 根据索引添加元素
     * @Param: [index, e]
     * @return: void
     * @Author: Carl
     * @Date: 2020/7/2
     ****************/
    void add(int index, E e);
    public void add(E e);
    /****************
     * @Description: Get the element from specific location
     * @Param: [index]
     * @return: E
     * @Author: Carl Zeng
     * @Date: 2020/7/2
     ****************/
    E get(int index) ;
    /****************
     * @Description: Set the element to specific location
     * @Param: [index, e]
     * @return: E
     * @Author: Carl Zeng
     * @Date: 2020/7/2
     ****************/
    E set(int index, E e);
    /****************
     * @Description: Remove the element on the specific location
     * @Param: [index]
     * @return: E
     * @Author: Carl Zeng
     * @Date: 2020/7/2
     ****************/
    E remove(int index) ;
}
