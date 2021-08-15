package com.kittycoder.algorithm.horse;

import java.awt.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * 马踏棋盘算法（贪心算法版，听完课程里写的代码自己写的）
 * 要点：对当前点接下来的可能走的所有点（记作集合s）进行排序，
 * 排序规则是集合s中的下一步可能节点数量最小的放前面
 * Created by shucheng on 2021/8/15 22:48
 */
public class HorseAlgorithm3 {

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
        int row = 5;
        // 马儿初始位置的列，对应数组里的第0列
        int column = 4;
        long start = System.currentTimeMillis();
        travelChessboard(chessboard, 1, new Point(row - 1, column - 1));
        System.out.println("耗时" + (System.currentTimeMillis() - start) + "ms");

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
        sort(possiblePoint);
        for (Point p : possiblePoint) {
            if (!visited[p.x*column+p.y]) {
                travelChessboard(chessboard, step + 1, p);
            }
        }

        if (step == row * column) {
            finished = true;
            System.out.println("游戏结束");
        } else if (!finished) {
            chessboard[x][y] = 0;
            visited[x*column+y] = false;
        }

        // 下面的判断是课程里的写法：跟上面我的写法其实是一样的
        /*if(step < row * column && !finished ) {
            chessboard[x][y] = 0;
            visited[x*column+y] = false;
        } else {
            finished = true;
            System.out.println("游戏结束");
        }*/
    }

    /**
     * 说明：
     * 起点 row=5,col=4时，采用s1作为初始策略，耗时6ms
     * 起点 row=5,col=4时，采用s2作为初始策略，耗时75ms
     */
    public static List<Point> getPossiblePoint(Point point) {
        List<Point> list = new ArrayList<>();
        Point p = new Point();
        // s1：我采用的找路线的策略（耗时12ms）
        int[][] possible_pos_arr = {
                {-2, 1},
                {-1, 2},
                {1, 2},
                {2, 1},
                {2, -1},
                {1, -2},
                {-1, -2},
                {-2, -1}
        };

        // s2：课程里采用的找路线的策略（耗时28ms）
        /*int[][] possible_pos_arr = {
                {-2, -1},
                {-1, -2},
                {1, -2},
                {2, -1},
                {2, 1},
                {1, 2},
                {-1, 2},
                {-2, 1}
        };*/

        int delta_x, delta_y;
        for (int[] possible_pos : possible_pos_arr) {
            delta_x = possible_pos[0];
            delta_y = possible_pos[1];
            p.x = point.x + delta_x;
            p.y = point.y + delta_y;

            if ((p.x >= 0 && p.x < column) && (p.y >= 0 && p.y < column)) {
                list.add(new Point(p));
            }
        }
        return list;
    }

    // 根据当前这个一步的所有的下一步的选择位置的数量，进行非递减排序, 减少回溯的次数
    public static void sort(List<Point> possiblePointList) {
        possiblePointList.sort(new Comparator<Point>() {
            @Override
            public int compare(Point o1, Point o2) {
                // 获取o1的下一步的所有位置个数
                int count1 = getPossiblePoint(o1).size();
                // 获取o2的下一步的所有位置个数
                int count2 = getPossiblePoint(o2).size();
                if (count1 < count2) {
                    return -1;
                } else if (count1 == count2) {
                    return 0;
                } else {
                    return 1;
                }
            }
        });
    }
}
