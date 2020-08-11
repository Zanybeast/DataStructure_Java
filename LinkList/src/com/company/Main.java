package com.company;

public class Main {

    public static void main(String[] args) {

        testForJosephCircle();

        testForStack();

        List<Integer> circleLinkedList = new CircleLinkedList<>();
        testArrayListByInteger(circleLinkedList);

        List<Integer> circleSingleLinkedList = new CircleSingleLinkedList<>();
        testArrayListByInteger(circleSingleLinkedList);

        List<Integer> linkedList = new LinkedList<>();
        testArrayListByInteger(linkedList);

        List<Integer> virtualHeaderSingleLinkedList = new SingleLinkedListVirtualHeader<>();
        testArrayListByInteger(virtualHeaderSingleLinkedList);

        List<Integer> singleLinkedList = new SingleLinkedList<Integer>();
        List<Integer> dynamicArray = new ArrayList<>();
        testArrayListByInteger(dynamicArray);
        testArrayListByInteger(singleLinkedList);

        List<Person> singleLinkedListPerson = new SingleLinkedList<>();
        List<Person> dynamicArrayPerson = new ArrayList<>();
        testArrayListWithPerson(singleLinkedListPerson);
        testArrayListWithPerson(dynamicArrayPerson);
    }

    public static void testForJosephCircle() {
        CircleLinkedList<Integer> list = new CircleLinkedList<>();
        for (int i = 1; i < 9; i++) {
            list.add(i);
        }

        int JosephFactor = 3;

        list.reset();
        StringBuilder sb = new StringBuilder();
        sb.append("Joseph Circle Problem Result(By ").append(JosephFactor).append(" steps a time): [ ");
        while (!list.isEmpty()) {
            for (int i = 0; i < JosephFactor - 1; i++) {
                list.next();
            }
            sb.append(list.remove()).append(" ");
        }
        sb.append("]");
        System.out.println(sb.toString());
    }

    public static void testForStack() {
        Stack<Integer> stack = new Stack<>();

        stack.push(11);
        stack.push(22);
        stack.push(33);
        stack.push(44);
        stack.push(55);

        StringBuilder sb = new StringBuilder();

        sb.append("Elements popped by stack: [");
        int size = stack.size();
        for (int i = 0; i < size; i++) {
            if (i != 0) {
                sb.append(", ");
            }
            sb.append(stack.pop());
        }
        sb.append("]");

        System.out.println(sb.toString());
    }

    public static void testArrayListByInteger(List<Integer> arrayList) {

        arrayList.add(11);
        arrayList.add(22);
        for (int i = 0; i < 5; i++) {
            arrayList.add(i + 30);
        }
        arrayList.add(55);
        //[11, 22, 30, 31, 32, 33, 34, 55]
        Asserts.test(arrayList.size() == 8);
        Asserts.test(arrayList.get(0) == 11);
        Asserts.test(arrayList.get(4) == 32);
        Asserts.test(arrayList.contains(34));
        Asserts.test(arrayList.indexOf(33) == 5);
        Asserts.test(arrayList.indexOf(180) == List.ELEMENT_NOT_FOUND);
        Asserts.test(arrayList.remove(0) == 11);
        //[22, 30, 31, 32, 33, 34, 55]

        Asserts.test(arrayList.remove(arrayList.size() - 1) == 55);
        //[22, 30, 31, 32, 33, 34]
        Asserts.test(arrayList.size() == 6);

        arrayList.set(4, 120);
        Asserts.test(arrayList.indexOf(120) == 4);
        Asserts.test(arrayList.size() == 6);
        //[22, 30, 31, 32, 120, 34]

        arrayList.add(5,33);
        Asserts.test(arrayList.size() == 7);
        //[22, 30, 31, 32, 120, 33, 34]
        arrayList.add(4,null);
        System.out.println(arrayList);
        arrayList.clear();
        Asserts.test(arrayList.isEmpty());

    }

    public static void testArrayListWithPerson(List<Person> list) {

        list.add(new Person("Jack", 20));
        list.add(new Person("Rose", 32));
        list.add(new Person("Fring", 35));

        System.out.println(list);
    }

//    public static void testArrayByInteger() {
//
//        List<Integer> arrayList = new ArrayList(10);
//        arrayList.add(11);
//        arrayList.add(22);
//        for (int i = 0; i < 5; i++) {
//            arrayList.add(i + 30);
//        }
//        arrayList.add(55);
//        //Should be [11, 22, 30, 31, 32, 33, 34, 55]
//        System.out.println(arrayList);
//        Asserts.test(arrayList.size() == 8);
//        Asserts.test(arrayList.get(0) == 11);
//        Asserts.test(arrayList.get(4) == 32);
//        Asserts.test(arrayList.contains(34));
//        Asserts.test(arrayList.indexOf(33) == 5);
//
//        arrayList.set(4, 120);
//        Asserts.test(arrayList.indexOf(120) == 4);
//
//        //Should be [11, 22, 30, 31, 120, 33, 34, 55]
//        System.out.println(arrayList);
//        arrayList.clear();
//        Asserts.test(arrayList.isEmpty());
//
//    }

//    public static void testArrayListWithPerson() {
//        ArrayList list = new ArrayList();
//
//        list.add(new Person("Jack", 20));
//        list.add(new Person("Rose", 32));
//        list.add(new Person("Fring", 35));
//
//        System.out.println(list);
//    }
}
