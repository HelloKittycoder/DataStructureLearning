package com.kittycoder.recursion;

/**
 * Created by shucheng on 2020/1/6 10:53
 */
public class MiGong {

    public static void main(String[] args) {
        // 地图
        int[][] map = new int[8][7];

        // 设置墙
        // 横向的墙（上下全部置为1）
        for (int i = 0; i < 7; i++) {
            map[0][i] = 1;
            map[7][i] = 1;
        }

        // 纵向的墙（左右全部置为1）
        for (int i = 0; i < 8; i++) {
            map[i][0] = 1;
            map[i][6] = 1;
        }

        // 设置挡板，用1表示
        map[3][1] = 1;
        map[3][2] = 1;
        System.out.println("地图的情况");
        printMap(map);

        // 使用递归给小球找路
        setWay(map, 1, 1);
        // setWay(map, 1, 1, 0);

        System.out.println("修改后的地图的情况");
        printMap(map);
    }

    // 输出地图
    public static void printMap(int[][] map) {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 7; j++) {
                System.out.print(map[i][j] + " ");
            }
            System.out.println();
        }
    }

    // 测试方法
    public static boolean setWay(int[][] map, int i, int j, int depth) {
        System.out.println(RecursionUtil.generateDepthString(depth) + "(" + i + "," +j +")" + "进入");

        if (map[6][5] == 2) { // 已经到达了(6,5)，通路已找到
            System.out.println(RecursionUtil.generateDepthString(depth) + "通路已找到");
            return true;
        } else {
            if (map[i][j] == 0) { // 该点没走过
                map[i][j] = 2; // 假定该点是可以走通的
                System.out.println(RecursionUtil.generateDepthString(depth) + "修改(" + i + "," +j +")为2");
                // 按照
                if (setWay(map, i + 1, j, depth + 1)) { // 下
                    System.out.println(RecursionUtil.generateDepthString(depth) + "(" + i + "," + j + ")能走通[下]");
                    return true;
                } else if (setWay(map, i, j + 1, depth + 1)) { // 右
                    System.out.println(RecursionUtil.generateDepthString(depth) + "(" + i + "," + j + ")能走通[右]");
                    return true;
                } else if (setWay(map, i - 1, j, depth + 1)) { // 上
                    System.out.println(RecursionUtil.generateDepthString(depth) + "(" + i + "," + j + ")能走通[上]");
                    return true;
                } else if (setWay(map, i, j - 1, depth + 1)) { // 左
                    System.out.println(RecursionUtil.generateDepthString(depth) + "(" + i + "," + j + ")能走通[左]");
                    return true;
                } else {
                    // 说明该点走不同，是死路
                    map[i][j] = 3;
                    return false;
                }
            } else { // 取值为1（墙）、2（该点能走通，且走过）、3（该点试了走不通）
                System.out.println(RecursionUtil.generateDepthString(depth) + "(" + i + "," + j + ")为" + map[i][j] + "=失败");
                return false;
            }
        }
    }

    // 寻找路径，如果走通了，返回true
    // map表示地图，(i,j)表示从哪个位置开始找
    public static boolean setWay(int[][] map, int i, int j) {
        if (map[6][5] == 2) { // 已经到达了(6,5)，通路已找到
            return true;
        } else {
            if (map[i][j] == 0) { // 该点没走过
                map[i][j] = 2; // 假定该点是可以走通的
                // 按照
                if (setWay(map, i + 1, j)) { // 下
                    return true;
                } else if (setWay(map, i, j + 1)) { // 右
                    return true;
                } else if (setWay(map, i - 1, j)) { // 上
                    return true;
                } else if (setWay(map, i, j - 1)) { // 左
                    return true;
                } else {
                    // 说明该点走不同，是死路
                    map[i][j] = 3;
                    return false;
                }
            } else { // 取值为1（墙）、2（该点能走通，且走过）、3（该点试了走不通）
                return false;
            }
        }
    }
}
