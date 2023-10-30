package org.example;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
    public static void main(String[] args) {

        TripletDeque<Integer> deque = new TripletDeque<>();
        deque.addFirst(1);

        deque.addLast(2);
        deque.addLast(3);
        deque.addLast(4);
        deque.addLast(5);
        deque.addLast(6);
        System.out.println(deque);
        deque.pollLast();
        System.out.println(deque);
    }
}