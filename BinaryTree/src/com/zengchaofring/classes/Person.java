package com.zengchaofring.classes;

/**
 * @ClassName Person
 * @Description TODO
 * @Author carl
 * @Date 2020/8/23 02:28
 * @Version 1.0
 **/
public class Person {
    private int age;

    public int getAge() {
        return age;
    }

    private String name;

    public Person(int age, String name) {
        this.age = age;
        this.name = name;
    }

    @Override
    public String toString() {
        return name + ": age= " + age;
    }
}
