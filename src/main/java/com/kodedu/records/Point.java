package com.kodedu.records;

public record Point(int x, int y) {

//    you can declare constructor or other methods
//    if you want to override the default behaviour
    public Point {
        if (x < 0 || y < 0) {
            throw new IllegalArgumentException("X or Y cannot be negative");
        }
    }

}