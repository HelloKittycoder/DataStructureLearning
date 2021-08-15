package com.kittycoder.algorithm.horse;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

/**
 * 马踏棋盘算法（回溯版，听完课程里写的代码自己写的）
 * Created by shucheng on 2021/8/11 8:13
 */
public class HorseAlgorithm2 {
    // 棋盘行数
    public static int row;
    // 棋盘列数
    public static int column;
    // 记录已访问的点
    public static boolean[] visited;
    // 游戏结束
    public static boolean finished = false;

    public static void main(String[] args) {
        row = 8;
        column = 8;
        int[][] chessboard = new int[row][column];
        visited = new boolean[row * column];

        // 马儿初始位置的行，对应数组里的第0行
        int row = 1;
        // 马儿初始位置的列，对应数组里的第0列
        int column = 1;
        travelChessboard(chessboard, 1, new Point(row - 1, column - 1));

        showChessboard(chessboard);
    }

    // 显示在棋盘上的行走路线
    public static void showChessboard(int[][] chessboard) {
        for (int[] rows : chessboard) {
            for (int step : rows) {
                System.out.print(step + "\t");
            }
            System.out.println();
        }
    }

    public static void travelChessboard(int[][] chessboard, int step, Point point) {
        int x = point.x;
        int y = point.y;
        chessboard[x][y] = step;
        visited[point.x*column+point.y] = true;

        List<Point> possiblePoint = getPossiblePoint(point);
        for (Point p : possiblePoint) {
            if (!visited[p.x*column+p.y]) {
                travelChessboard(chessboard, step + 1, p);
            }
        }

        /*if (step == row * column) {
            finished = true;
            System.out.println("游戏结束");
        } else if (!finished) {
            chessboard[x][y] = 0;
            visited[x*column+y] = false;
        }*/

        if(step < row * column && !finished ) {
            chessboard[x][y] = 0;
            visited[x*column+y] = false;
        } else {
            finished = true;
        }
    }

    public static List<Point> getPossiblePoint(Point point) {
        List<Point> list = new ArrayList<>();
        Point p = new Point();
        if ((p.x = point.x - 2) >= 0 && (p.y = point.y + 1) < column) {
            list.add(new Point(p));
        }
        if ((p.x = point.x - 1) >= 0 && (p.y = point.y + 2) < column) {
            list.add(new Point(p));
        }
        if ((p.x = point.x + 1) < row && (p.y = point.y + 2) < column) {
            list.add(new Point(p));
        }
        if ((p.x = point.x + 2) < row && (p.y = point.y + 1) < column) {
            list.add(new Point(p));
        }
        if ((p.x = point.x + 2) < row && (p.y = point.y - 1) >= 0) {
            list.add(new Point(p));
        }
        if ((p.x = point.x + 1) < row && (p.y = point.y - 2) >= 0) {
            list.add(new Point(p));
        }
        if ((p.x = point.x - 1) >= 0 && (p.y = point.y - 2) >= 0) {
            list.add(new Point(p));
        }
        if ((p.x = point.x - 2) >= 0 && (p.y = point.y - 1) >= 0) {
            list.add(new Point(p));
        }
        return list;
    }
}
