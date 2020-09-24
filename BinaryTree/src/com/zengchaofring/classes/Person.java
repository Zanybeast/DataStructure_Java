package com.zengchaofring.classes;

import java.util.Objects;

/**
 * @ClassName Person
 * @Description TODO
 * @Author carl
 * @Date 2020/8/23 02:28
 * @Version 1.0
 **/
public class Person {
    private int age;
    private float height;

    public int getAge() {
        return age;
    }

    private String name;

    public Person(int age, String name) {
        this.age = age;
        this.name = name;
        this.height = 1.5f;
    }

    public Person(int age, float height, String name) {
        this.age = age;
        this.height = height;
        this.name = name;
    }

    @Override
    public String toString() {
        return name + ": age= " + age;
    }

    /*
     * @Author carl
     * @Description 哈希函数
     * @Date 22:11 2020/9/24
     * @Param
     * @Return
     **/

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Person person = (Person) o;
        return age == person.age &&
                Float.compare(person.height, height) == 0 &&
                Objects.equals(name, person.name);
    }

    @Override
    public int hashCode() {

        return Objects.hash(age, height, name);
    }
}
