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
public class Solution1 implements Solution {

    /**
     * 暴力求解，时间复杂度 o(n^3)
     * 自己想的解法 时间太长了，运行超时
     */
    @Override
    public int maxSubArray(int[] nums) {
        int length;
        if (nums == null || (length = nums.length) == 0) {
            return 0;
        }

        int maxSum = Integer.MIN_VALUE;
        int sum;
        // int t = 0; // 计算过的子数组的个数
        for (int i = 0; i < length; i++) {
            for (int k = 0; k < length - i; k++) {
                sum = 0;
                // t++;
                // log.info("求索引范围为【{}, {}】的子数组的和", i, i + k);
                for (int m = i; m <= i + k; m++) {
                    sum += nums[m];
                    if (sum > maxSum) {
                        maxSum = sum;
                    }
                }
            }
        }
        // System.out.println(t);
        return maxSum;
    }

    /**
     * 思路说明：
     * 只要能把该数组的所有子数组情况都列出来，然后都分别求和，再比较下大小就可以了
     * n个元素的数组，所有子数组的情况为：1+2+3+...+n=n*(n+1)/2
     *
     * 以{-2, 1, -3, 4}为例，所有子数组的情况有10个，记成 {A,B,C,D}
     * 所有情况如下：
     * 单元素- {A}，{B}，{C}，{D}
     * 2个元素- {A,B}，{B,C}，{C,D}
     * 3个元素- {A,B,C}，{B,C,D}
     * 4个元素- {A,B,C,D}
     *
     * 我实际遍历的时候，次序如下：
     * {A}，{A,B}，{A,B,C}，{A,B,C,D}
     * {B}，{B,C}，{B,C,D}
     * {C}，{C,D}
     * {D}
     * A开头的时候，不停的进行累加，每累加1次，看下是否比max大，如果大的话，则替换max
     * B开头的时候，先将sum重置为0，然后不停的进行累加，每累加1次，看下是否比max大，如果大的话，则替换max
     * 后面C、D开头都是同样的操作
     */

    public static void main(String[] args) {
        // int[] arr = {-2, 1, -3, 4, -1, 2, 1, -5, 4}; // 6
        // int[] arr = {-2, 1, -3, 4}; // 4
        int[] arr = {-10000}; // -10000
        System.out.println(arr.length);
        Solution solution = new Solution1();
        System.out.println(solution.maxSubArray(arr));
        /*for (int i = 0; i < arr.length; i++) {
            if (i < arr.length) { // 单元素数组
                System.out.println(i + "," + (i + 0));
            }
            if (i + 1 < arr.length) { // 两个元素的数组
                System.out.println(i + "," + (i + 1));
            }
            if (i + 2 < arr.length) { // 三个元素的数组
                System.out.println(i + "," + (i + 2));
            }
            if (i + 3 < arr.length) { // 四个元素的数组
                System.out.println(i + "," + (i + 3));
            }
        }*/
    }
}
