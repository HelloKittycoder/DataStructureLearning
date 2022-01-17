package com.kittycoder.leetcode.best_time_to_buy_and_sell_stock_ii;

/**
 * Created by shucheng on 2022/1/17 8:01
 */
public class Solution1 implements Solution {

    /**
     * 方法一：动态规划法
     * 时间复杂度 O(2n) = O(n)，n为数组的长度。
     * 一共有2n个状态，每次状态转移的时间复杂度为O(1)
     *
     * 空间复杂度：
     * 写法1 O(n) 这里需要存储动态规划中的所有状态
     * 写法2 O(1)
     */
    @Override
    public int maxProfit(int[] prices) {
        int n = prices.length;
        // 写法1：
        /*int[][] dp = new int[n][2];
        dp[0][0] = 0;
        dp[0][1] = -prices[0];
        for (int i = 1; i < n; i++) {
            dp[i][0] = Math.max(dp[i - 1][0], dp[i - 1][1] + prices[i]);
            dp[i][1] = Math.max(dp[i - 1][1], dp[i - 1][0] - prices[i]);
        }
        return dp[n - 1][0];*/

        // 写法2：
        int dp0 = 0, dp1 = -prices[0];
        for (int i = 1; i < n; i++) {
            int newDp0 = Math.max(dp0, dp1 + prices[i]);
            int newDp1 = Math.max(dp1, dp0 - prices[i]);
            dp0 = newDp0;
            dp1 = newDp1;
        }
        return dp0;
    }
}
