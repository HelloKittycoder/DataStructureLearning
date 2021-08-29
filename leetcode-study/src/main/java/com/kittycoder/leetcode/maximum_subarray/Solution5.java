/**
 * Leetcode - maximum_subarray
 */
package com.kittycoder.leetcode.maximum_subarray;

/**
 * log instance is defined in Solution interface
 * this is how slf4j will work in this class:
 * =============================================
 *     if (log.isDebugEnabled()) {
 *         log.debug("a + b = {}", sum);
 *     }
 * =============================================
 */
public class Solution5 implements Solution {

    // leetcode官方题解 动态规划 时间复杂度 o(n)
    @Override
    public int maxSubArray(int[] nums) {
        int length;
        if (nums == null || (length = nums.length) == 0) {
            return 0;
        }
        if (length == 1) {
            return nums[0];
        }

        /**
         * 这里为什么要通过max来返回，而不是result来返回呢？
         * 试下 {-2, 1, -3, 4, -1, 2, 1, -5, 4} 就知道了，如果用result返回，这个结果是5，
         * 6对应的是{4, -1, 2, 1}，5对应的是{4, -1, 2, 1, -5, 4}
         * result表示的是第i个数结尾的连续子数组的最大和，而这里要找的是整个数组的连续子数组的最大和，
         * 所以需要对所有的result再求一次最大值
         */
        /*int maxAns = nums[0];
        int result;
        int pre = 0;
        for (int i = 0; i < nums.length; i++) {
            result = Math.max(pre + nums[i], nums[i]);
            pre = result;
            if (result > maxAns) {
                maxAns = result;
            }
        }*/

        // 下面是官方给的写法，更简洁，和上面其实是一样的
        int pre = 0, maxAns = nums[0];
        for (int x : nums) {
            pre = Math.max(pre + x, x);
            maxAns = Math.max(maxAns, pre);
        }
        return maxAns;
    }
}
