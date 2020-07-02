package com.company;

import java.util.Objects;

public class Person {
    private int age;
    private String name;

    public Person(String name, int age) {
        this.age = age;
        this.name = name;
    }

    @Override
    public String toString() {
        return "{" + name + ", age = " + age + "}";
    }

}
