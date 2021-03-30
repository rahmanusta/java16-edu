package com.kodedu.records;

public record Point(int x, int y) {

    public Point(int x, int y) {
        if (x < 0 || y < 0) {
            throw new IllegalArgumentException("X or Y cannot be negative");
        }
        this.x = x;
        this.y = y;
    }

}