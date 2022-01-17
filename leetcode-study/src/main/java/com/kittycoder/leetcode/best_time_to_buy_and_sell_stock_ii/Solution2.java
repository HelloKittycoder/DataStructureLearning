package com.kittycoder.leetcode.best_time_to_buy_and_sell_stock_ii;

/**
 * Created by shucheng on 2022/1/17 8:11
 */
public class Solution2 implements Solution {

    /**
     * 方法二：贪心算法
     * 时间复杂度：O(n)，n为数组的长度
     * 空间复杂度：O(1)，只需要常数空间存放若干变量
     */
    @Override
    public int maxProfit(int[] prices) {
        int ans = 0;
        int n = prices.length;
        for (int i = 1; i < n; i++) {
            ans += Math.max(0, prices[i] - prices[i - 1]);
        }
        return ans;
    }
}
