package com.company;
//计算斐波那契数列
public class Main {

    public static int fib1(int n) {
        if (n <= 1) return n;
        return fib1(n - 1) + fib1(n - 2);
    }

    public static int fib2(int n) {
        if (n <= 1) return n;

        int first = 0;
        int second = 1;

        for (int i = 0; i < n - 1; ++i) {
            int sum = first + second;
            first = second;
            second = sum;
        }

        return second;
    }

    public static void main(String[] args) {
        CompareFib(45);
    }

    public static void CompareFib(int n) {
        TimeTools.test("Fib1", new TimeTools.Task() {
            @Override
            public void execute() {
                System.out.println(fib1(n));
            }
        });

        TimeTools.test("Fib2", new TimeTools.Task() {
            @Override
            public void execute() {
                System.out.println(fib2(n));
            }
        });

    }
}
