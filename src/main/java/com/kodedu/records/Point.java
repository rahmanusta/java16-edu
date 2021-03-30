package com.kodedu.records;

public record Point(int x, int y) {

    public Point {
        if (x < 0 || y < 0) {
            throw new IllegalArgumentException("X or Y cannot be negative");
        }
    }

}