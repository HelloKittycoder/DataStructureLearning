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
     * 说明：下面找路线的策略正常来说，应该动态的根据当前点进行调整，目前是所有点都采用某一种策略，
     * 可能在某些起点找起来比较快，在某些起点找起来会很慢
     * 如：起点 row=5,col=4时，
     * 采用s1策略找到的结果：（耗时517562ms）
     * 52	49	54	47	24	3	20	11
     * 55	46	51	36	21	12	23	4
     * 50	53	48	25	2	19	10	13
     * 45	56	35	42	37	22	5	18
     * 60	41	44	1	26	9	14	29
     * 57	34	59	38	43	28	17	6
     * 40	61	32	27	8	63	30	15
     * 33	58	39	62	31	16	7	64
     */
    public static List<Point> getPossiblePoint(Point point) {
        List<Point> list = new ArrayList<>();
        Point p = new Point();
        // s1：我采用的找路线的策略（耗时507ms）
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

        // s2：课程里采用的找路线的策略（耗时27197ms）
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
}
