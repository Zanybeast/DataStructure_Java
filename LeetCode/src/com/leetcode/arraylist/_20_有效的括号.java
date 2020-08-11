package com.leetcode.arraylist;


import java.util.HashMap;
import java.util.Stack;

public class _20_有效的括号 {
    public boolean isValid(String s) {
        HashMap<Character, Character> map = new HashMap<>();
        map.put('{', '}');
        map.put('(', ')');
        map.put('[', ']');

        Stack<Character> stack = new Stack<>();

        int length = s.length();
        for (int i = 0; i < length; i++) {
            char c = s.charAt(i);
            if (map.containsKey(c)) {
                stack.push(c);
            } else {
                if (stack.isEmpty()) return false;
                if (c != map.get(stack.pop())) return false;
            }
        }

        return stack.isEmpty();
    }
}
