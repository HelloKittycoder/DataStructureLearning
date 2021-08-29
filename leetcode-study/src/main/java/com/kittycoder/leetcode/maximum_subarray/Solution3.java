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
public class Solution3 implements Solution {

    // 优化前缀和 时间复杂度 o(n)，n是数组长度
    @Override
    public int maxSubArray(int[] nums) {
        int length;
        if (nums == null || (length = nums.length) == 0) {
            return 0;
        }

        int maxSum = nums[0];
        int sum = 0;
        int minSum = 0;
        for (int i = 0; i < length; i++) {
            sum += nums[i];
            maxSum = Math.max(maxSum, sum - minSum);
            minSum = Math.min(minSum, sum);
        }
        return maxSum;
    }
}
