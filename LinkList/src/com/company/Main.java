package com.company;

public class Main {

    public static void main(String[] args) {
        testArrayByInteger();
        testArrayListWithPerson();
    }

    public static void testArrayByInteger() {
        ArrayList<Integer> arrayList = new ArrayList(10);
        arrayList.add(11);
        arrayList.add(22);
        for (int i = 0; i < 5; i++) {
            arrayList.add(i + 30);
        }
        arrayList.add(55);
        //Should be [11, 22, 30, 31, 32, 33, 34, 55]
        System.out.println(arrayList);
        Asserts.test(arrayList.size() == 8);
        Asserts.test(arrayList.get(0) == 11);
        Asserts.test(arrayList.get(4) == 32);
        Asserts.test(arrayList.contains(34));
        Asserts.test(arrayList.indexOf(33) == 5);

        arrayList.set(4, 120);
        Asserts.test(arrayList.indexOf(120) == 4);

        //Should be [11, 22, 30, 31, 120, 33, 34, 55]
        System.out.println(arrayList);
        arrayList.clear();
        Asserts.test(arrayList.isEmpty());

    }

    public static void testArrayListWithPerson() {
        ArrayList list = new ArrayList();

        list.add(new Person("Jack", 20));
        list.add(new Person("Rose", 32));
        list.add(new Person("Fring", 35));

        System.out.println(list);
    }
}
