package com.kodedu.records;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class RecordsApp3 {
    public static void main(String[] args) {

        int[][] points = new int[][]{{1, 1}, {2, 3}, {4, -1}};

        record Point(int x, int y) {}

        List<Point> pointList = Arrays.stream(points)
                .map(a -> new Point(a[0], a[1]))
                .collect(Collectors.toList());

        System.out.println(pointList);

    }
}
