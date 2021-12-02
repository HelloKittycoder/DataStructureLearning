package com.kittycoder.leetcode.best_time_to_buy_and_sell_stock;

/**
 * Created by shucheng on 2021/12/2 20:29
 */
public class Solution2 implements Solution {
    /**
     * 方法二：一次遍历
     * 复杂度分析：
     * 时间复杂度：O(n)，只需要遍历一次
     * 空间复杂度：O(1)，只使用了常数个变量
     * @param prices
     * @return
     */
    @Override
    public int maxProfit(int[] prices) {
        int minprice = Integer.MAX_VALUE;
        int maxprofit = 0;
        for (int i = 0; i < prices.length; i++) {
            if (prices[i] < minprice) {
                // 如果第i天的价格比minprice还低，则记录一个新的历史最低价格
                minprice = prices[i];
            } else if (prices[i] - minprice > maxprofit) {
                // 如果第i天的价格>=minprice，则计算第i天的价格减去历史最低价(第0,1,...,i-1天)的值，
                // 如果比maxprofit还大，则记录一个新的maxprofit
                maxprofit = prices[i] - minprice;
            }
        }
        return maxprofit;
    }
}
