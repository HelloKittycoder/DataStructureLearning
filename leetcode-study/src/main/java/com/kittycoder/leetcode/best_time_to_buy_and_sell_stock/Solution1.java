package com.kittycoder.leetcode.best_time_to_buy_and_sell_stock;

/**
 * Created by shucheng on 2021/12/2 7:48
 */
public class Solution1 implements Solution {
    /**
     * 方法一：暴力法
     * 不过这种方法会超时，过不了leetcode官方的时间限制
     *
     * 复杂度分析：
     * 时间复杂度：O(n^2)，循环运行n*(n-2)/2次
     * 空间复杂度：O(1)，只使用了常数个变量
     */
    @Override
    public int maxProfit(int[] prices) {
        int maxprofit = 0;
        for (int i = 0; i < prices.length - 1; i++) {
            for (int j = i + 1; j < prices.length; j++) {
                int profit = prices[j] - prices[i];
                if (profit > maxprofit) {
                    maxprofit = profit;
                }
            }
        }
        return maxprofit;
    }

    // 自己想的写法
    // 上面是leetcode官方给的写法
    /*@Override
    public int maxProfit(int[] prices) {
        int max = 0;
        for (int i = 0; i < prices.length - 1; i++) {
            for (int j = i + 1; j < prices.length; j++) {
                max = Math.max(prices[j] - prices[i], max);
            }
        }
        return max;
    }*/
}
