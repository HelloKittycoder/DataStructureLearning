package com.kittycoder.algorithm.horse;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import java.util.ArrayList;
import java.util.List;

/**
 * 马踏棋盘算法（回溯版，结合课程思路自己想的）
 * Created by shucheng on 2021/8/8 22:33
 */
public class HorseAlgorithm {

    static List<Point> visitedPoint = new ArrayList<>();
    static int len = 8;
    static boolean finished = false;

    public static void main(String[] args) {
        int[][] arr = new int[len][len];
        // arr中0表示该点没被访问过，1表示该点被访问过
        // System.out.println(arr);
        long start = System.currentTimeMillis();
        horse(arr, 1, new Point(0, 0));
        System.out.println(System.currentTimeMillis() - start + "ms");
        /*List<Point> list = new ArrayList<>();
        Point p1 = new Point(3, 5);
        list.add(p1);
        Point p2 = new Point(3, 5);
        list.remove(p2);
        System.out.println(list);*/
    }

    public static void horse(int[][] arr, int step, Point point) {


        int x = point.x;
        int y = point.y;

        visitedPoint.add(point);
        arr[x][y] = 1;

        List<Point> possiblePoint = new ArrayList<>();
        // 东北
        addPossiblePoint(possiblePoint, x - 2, y + 1, arr);
        addPossiblePoint(possiblePoint, x - 1, y + 2, arr);

        // 东南
        addPossiblePoint(possiblePoint, x + 1, y + 2, arr);
        addPossiblePoint(possiblePoint, x + 2, y + 1, arr);

        // 西南
        addPossiblePoint(possiblePoint, x + 2, y - 1, arr);
        addPossiblePoint(possiblePoint, x + 1, y - 2, arr);

        // 西北
        addPossiblePoint(possiblePoint, x - 1, y - 2, arr);
        addPossiblePoint(possiblePoint, x - 2, y - 1, arr);

        /*if (possiblePoint.size() == 0 && visitedPoint.size() < 64) {
            visitedPoint.remove(point);
            arr[x][y] = 0;
            return;
        }*/

        for (Point pp : possiblePoint) {
            horse(arr, step + 1, pp);
        }
        if (step == len * len) {
            finished = true;
            System.out.println("游戏结束");
        } else if (!finished) {
            visitedPoint.remove(point);
            arr[x][y] = 0;
        }

        /*
        // 下面这种写法是有问题的，递归结束不了；这里要结束递归，需要再另外引入变量finished
        // 如果用step == len * len作为递归结束条件，那么在回到上层递归时又会执行一遍else里的逻辑
        // （else的逻辑应该是马没走完棋盘时要做的，一旦马已经走完else里的逻辑就不用再执行了）
        if (step == len * len) {
            System.out.println("游戏结束");
        } else {
            visitedPoint.remove(point);
            arr[x][y] = 0;
        }*/
    }

    private static void addPossiblePoint(List<Point> list, int x, int y, int[][] arr) {
        if ((x >= 0 && x < len) && (y >= 0 && y < len) && arr[x][y] == 0) {
            list.add(new Point(x, y));
        }
    }

    static class Point {
        private int x;
        private int y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;

            if (o == null || getClass() != o.getClass()) return false;

            Point point = (Point) o;

            return new EqualsBuilder()
                    .append(x, point.x)
                    .append(y, point.y)
                    .isEquals();
        }

        @Override
        public int hashCode() {
            return new HashCodeBuilder(17, 37)
                    .append(x)
                    .append(y)
                    .toHashCode();
        }

        @Override
        public String toString() {
            return "Point{" +
                    "x=" + x +
                    ", y=" + y +
                    '}';
        }
    }
}
