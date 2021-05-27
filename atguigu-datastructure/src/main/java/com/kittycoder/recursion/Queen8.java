package com.kittycoder.recursion;

import java.util.Arrays;

/**
 * Created by shucheng on 2020/1/7 12:46
 * 求解八皇后问题
 */
public class Queen8 {

    private static int max = 8;
    private static int[] arr = new int[max];
    private static int num = 0;
    private static int traverseTimes = 0;

    public static void main(String[] args) {
        // check(0, 0);
        check(0);
        System.out.printf("总共%d种方案\n", num); // 92
        System.out.printf("总共遍历%d次\n", traverseTimes); // 15720
    }

     // 测试方法
    public static void check(int n, int depth) {
        // 显示当前进入的递归深度，和正准备放置第几个皇后
        System.out.println(RecursionUtil.generateDepthString(depth) + "row=" + n + "进入");
        // 如果准备在走第9个皇后，说明已经找到一种解法
        if (n == max) {
            print();
            return;
        }
        // 通过循环来放置八个皇后
        for (int i = 0; i < 8; i++) {
            traverseTimes++;
            arr[n] = i;
            // 显示当前进入的递归深度，和正在放置第几个皇后，以及当前所尝试的方案，和当前已经尝试次数
            System.out.println(RecursionUtil.generateDepthString(depth)
                    + "row=" + n + "-i=" + i + Arrays.toString(arr) + judge(n) + traverseTimes);
            if (judge(n)) {
                check(n + 1, depth + 1);
            }
        }
        // 显示当前将要离开的递归深度，和已经放置好第几个皇后
        System.out.println(RecursionUtil.generateDepthString(depth) + "row=" + n + "退出");
    }

    public static void check(int n) {
        // 如果准备在走第9个皇后，说明已经找到一种解法
        if (n == max) {
            print();
            return;
        }
        // 通过循环来放置八个皇后
        for (int i = 0; i < 8; i++) {
            traverseTimes++;
            arr[n] = i;
            if (judge(n)) {
                check(n + 1);
            }
        }
    }

    // 判断第(n+1)个皇后和前面n个皇后是否冲突
    public static boolean judge(int n) {
        for (int i = 0; i < n; i++) {
            // 冲突（在同一列或者同一斜线）
            if (arr[n] == arr[i] || Math.abs(n - i) == Math.abs(arr[n] - arr[i])) {
                return false;
            }
        }
        return true;
    }

    public static void print() {
        num++;
        // System.out.println("find-[" + num + "," + traverseTimes + "]"); // 显示当前正在打印第几个找到的方案，以及当前尝试的次数
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();
    }
}
