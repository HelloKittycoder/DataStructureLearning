package com.kittycoder.algorithm.dynamic;

/**
 * 动态规划求解背包问题（结合课程思路写的，课程代码写的更好点）
 * Created by shucheng on 2021/7/12 23:55
 */
public class MyKnapsackProblem {
    // 记录每次的求解得到的最大值
    private int[][] v;
    // 记录每次求解到最大值时对应的i、j（如果在i、j求到最大值，则记为1）
    private int[][] path;
    // 物品的重量数组
    private int[] weightArr;
    // 物品的价格数组
    private int[] priceArr;

    public static void main(String[] args) {
        int[] weightArr = {1, 4, 3};
        int[] priceArr = {1500, 3000, 2000};
        MyKnapsackProblem da = new MyKnapsackProblem(3, 0, 4, weightArr, priceArr);
        da.solve();
        System.out.println(da);
    }

    public MyKnapsackProblem(int itemNum, int weightStart, int weightEnd, int[] weightArr, int[] priceArr) {
        int rows = itemNum + 1;
        int columns = weightEnd - weightStart + 1;
        v = new int[rows][columns];
        path = new int[rows][columns];
        this.weightArr = weightArr;
        this.priceArr = priceArr;
    }

    public void solve() {
        for (int i = 1; i < v.length; i++) {
            int[] row = v[i];
            for (int j = 1; j < row.length; j++) {
                if (weightArr[i - 1] > j) {
                    v[i][j] = v[i - 1][j];
                } else {
                    // v[i][j] = Math.max(v[i - 1][j], priceArr[i - 1] + v[i - 1][j - weightArr[i - 1]]);
                    if (v[i - 1][j] > priceArr[i - 1] + v[i - 1][j - weightArr[i - 1]]) {
                        v[i][j] = v[i - 1][j];
                    } else {
                        v[i][j] = priceArr[i - 1] + v[i - 1][j - weightArr[i - 1]];
                        path[i][j] = 1;
                    }
                }
            }
        }
    }
}
