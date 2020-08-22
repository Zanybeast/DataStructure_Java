package com.leetcode.arraylist;

import java.util.LinkedList;

/**
 * @ClassName _225_用队列实现栈
 * @Description TODO
 * @Author carl
 * @Date 2020/8/22 21:24
 * @Version 1.0
 **/
public class _225_用队列实现栈 {
    private LinkedList<Integer> q1;

    /** Initialize your data structure here. */
    public _225_用队列实现栈() {
        q1 = new LinkedList<>();
    }

    /** Push element x onto stack. */
    public void push(int x) {
        q1.add(x);
        int size = q1.size();
        while (size > 1) {
            q1.add(q1.remove());
            size--;
        }
    }

    /** Removes the element on top of the stack and returns that element. */
    public int pop() {
        return q1.remove(0);
    }

    /** Get the top element. */
    public int top() {
        return q1.peek();
    }

    /** Returns whether the stack is empty. */
    public boolean empty() {
        return q1.isEmpty();
    }
}
