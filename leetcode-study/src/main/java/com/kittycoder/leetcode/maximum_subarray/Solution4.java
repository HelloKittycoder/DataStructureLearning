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
public class Solution4 implements Solution {

    class Status {
        // 对于一个区间[l,r]
        private int
                // [l,r]内以l为左端点的最大子段和
                lSum,
                // [l,r]内以r为右端点的最大子段和
                rSum,
                // [l,r]内的最大子段和
                mSum,
                // [l,r]的区间和
                iSum;

        public Status(int lSum, int rSum, int mSum, int iSum) {
            this.lSum = lSum;
            this.rSum = rSum;
            this.mSum = mSum;
            this.iSum = iSum;
        }
    }

    /**
     * leetcode官方题解 分治算法 渐进时间复杂度 o(n)
     * 这个后续还要再多练习
     */
    @Override
    public int maxSubArray(int[] nums) {
        int length;
        if (nums == null || (length = nums.length) == 0) {
            return 0;
        }

        return getInfo(nums, 0, length - 1).mSum;
    }

    private Status getInfo(int[] a, int l, int r) {
        // 区间长度为1，不能再继续拆了，递归结束
        if (l == r) {
            return new Status(a[l], a[l], a[l], a[l]);
        }
        // 拆分区间信息
        int m = (l + r) >> 1;
        Status lSub = getInfo(a, l, m);
        Status rSub = getInfo(a, m + 1, r);
        // 合并区间信息
        return pushUp(lSub, rSub);
    }

    // 结合leetcode官方提供的合并算法的文字表述，可以写出如下代码
    private Status pushUp(Status l, Status r) {
        int iSum = l.iSum + r.iSum;
        int lSum = Math.max(l.lSum, l.iSum + r.lSum);
        int rSum = Math.max(r.rSum, r.iSum + l.rSum);
        int mSum = Math.max(Math.max(l.mSum, r.mSum), l.rSum + r.lSum);
        return new Status(lSum, rSum, mSum, iSum);
    }
}
