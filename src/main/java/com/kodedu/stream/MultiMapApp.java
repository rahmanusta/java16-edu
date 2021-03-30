package com.kodedu.stream;

import java.util.Arrays;
import java.util.List;

public class MultiMapApp {

    public static void main(String[] args) {

        List<Integer> numbers = Arrays.asList(1, 2, 3, null, 4, 5, null);

//        .map(..)
//            N size input -> N size output

//        .mapMulti(..)
//            N size input -> M size output

        numbers.stream()
                .mapMulti((n, consumer) -> {
                    if (n != null) {
                        consumer.accept(n * 2);
                    } else {
//                        consumer.accept(-1);
                    }
                })
                .forEach(System.out::println);
    }
}
