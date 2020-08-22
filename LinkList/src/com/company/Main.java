package com.company;

public class Main {

    public static void main(String[] args) {

        testForCircleDeque();

        testForCircleQueue();

        testForDeque();
        testForQueue();

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

    public static void testForCircleDeque() {
        CircleDeque<Integer> queue = new CircleDeque<>();
        for (int i = 0; i < 5; i++) {
            queue.enQueueFront(i);
            queue.enQueueRear(i + 100);
        }

        int size = queue.size();
        StringBuilder sb = new StringBuilder();
        sb.append("Queue::size = ").append(size).append(" Deque from front straight Look: [");
        for (int i = 0; i < size; i++) {
            if (i != 0) {
                sb.append(", ");
            }
            sb.append(queue.deQueueFront());
        }
        sb.append("]\n");
        System.out.println(sb.toString());
        for (int i = 0; i < 5; i++) {
            queue.enQueueFront(i);
            queue.enQueueRear(i + 100);
        }

        System.out.println(queue);
        for (int i = 0; i < 2; i++) {
            queue.deQueueFront();
            queue.deQueueRear();
        }
        System.out.println("Deque 2 elements on both front and rear end, now the queue is: ");
        System.out.println(queue);
        for (int i = 0; i < 3; i++) {
            queue.enQueueRear(i + 200);
        }
        System.out.println("Enque 3 elements on rear, now the queue is: ");
        System.out.println(queue);
        queue.deQueueRear();
        queue.deQueueRear();
        System.out.println("Deque 2 elements on rear, now the queue is: ");
        System.out.println(queue);
        for (int i = 0; i < 10; i++) {
            queue.enQueueFront(i + 300);
        }
        System.out.println("Enqueue 10 elements on front, now the queue is: ");
        System.out.println(queue);

        System.out.println("--------------------------------------------------");
        System.out.println("--------------------------------------------------");
    }

    public static void testForCircleQueue() {
        CircleQueue<Integer> queue = new CircleQueue<>();

        for (int i = 0; i < 10; i++) {
            queue.enQueue(i);
        }

        System.out.println(queue);
        int count = 3;
        for (int i = 0; i < count; i++) {
            queue.deQueue();
        }
        System.out.println("Deque 3 elements from front, now the queue is: ");
        System.out.println(queue);
        queue.enQueue(100);
        queue.enQueue(105);
        System.out.println("First enqueue 100 and then 105, now the queue is: ");
        System.out.println(queue);
        for (int i = 0; i < 5; i++) {
            queue.enQueue(i + 200);
        }
        System.out.println("Enqueue more 5 elements, now the queue is: ");
        System.out.println(queue);
        System.out.println("--------------------------------------------------");
        System.out.println("--------------------------------------------------");
    }

    public static void testForDeque() {
        Deque<Integer> queue = new Deque<>();
        for (int i = 0; i < 5; i++) {
            queue.enQueueFront(i);
            queue.enQueueRear(i + 100);
        }
        //should be: Front[4, 3, 2, 1, 0, 100, 101, 102, 103, 104]End
        int size = queue.size();
        StringBuilder sb = new StringBuilder();
        sb.append("Queue::size = ").append(size).append(" Deque from front straight Look: [");
        for (int i = 0; i < size; i++) {
            if (i != 0) {
                sb.append(", ");
            }
            sb.append(queue.deQueueFront());
        }
        sb.append("]\n");
        System.out.println(sb.toString());

        for (int i = 0; i < 5; i++) {
            queue.enQueueFront(i);
            queue.enQueueRear(i + 100);
        }
        sb.append("Queue::size = ").append(size).append(" Deque from end straight Look: [");
        for (int i = 0; i < size; i++) {
            if (i != 0) {
                sb.append(", ");
            }
            sb.append(queue.deQueueRear());
        }
        sb.append("]");
        //should be: [104, 103, 102, 101, 100, 0, 1, 2, 3, 4]
        System.out.println(sb.toString());
        System.out.println("--------------------------------------------------");
        System.out.println("--------------------------------------------------");
    }

    public static void testForQueue() {
        Queue<Integer> queue = new Queue<>();
        for (int i = 0; i < 5; i++) {
            queue.enQueue(i + 100);
        }
        int size = queue.size();
        StringBuilder sb = new StringBuilder();
        sb.append("Queue::size = ").append(size).append(" Deque from front straight Look: [");
        for (int i = 0; i < size; i++) {
            if (i != 0) {
                sb.append(", ");
            }
            sb.append(queue.deQueue());
        }
        sb.append("]");
        System.out.println(sb.toString());
        System.out.println("--------------------------------------------------");
        System.out.println("--------------------------------------------------");
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
        System.out.println("--------------------------------------------------");
        System.out.println("--------------------------------------------------");
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
        System.out.println("--------------------------------------------------");
        System.out.println("--------------------------------------------------");
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
        System.out.println("--------------------------------------------------");
        System.out.println("--------------------------------------------------");
    }

    public static void testArrayListWithPerson(List<Person> list) {

        list.add(new Person("Jack", 20));
        list.add(new Person("Rose", 32));
        list.add(new Person("Fring", 35));

        System.out.println(list);
        System.out.println("--------------------------------------------------");
        System.out.println("--------------------------------------------------");
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
