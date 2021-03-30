package com.kodedu.stream;

import java.util.List;
import java.util.stream.Collectors;

public class ToListApp {

    public static void main(String[] args) {

        List<Integer> numbers = List.of(1, 2, 3, 4, 5);

        List<Integer> list1 = numbers.stream().map(n -> n * 2).collect(Collectors.toList());
        System.out.println("List1: " + list1);
        list1.add(99);
        System.out.println("List1: " + list1);

        List<Integer> list2 = numbers.stream().map(n -> n * 2).toList();
        System.out.println("List2: " + list2);
        list2.add(99);
        System.out.println("List2: " + list2);

    }
}
