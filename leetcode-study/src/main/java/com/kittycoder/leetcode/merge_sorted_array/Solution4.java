/**
 * Leetcode - merge_sorted_array
 */
package com.kittycoder.leetcode.merge_sorted_array;

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

    /**
     * leetcode官方提供的思路：逆向双指针
     * 每次取两个数组中较大的一个，放到nums1的最后面
     * 时间复杂度 o(m+n) 空间复杂度 o(1)
     */
    @Override
    public void merge(int[] nums1, int m, int[] nums2, int n) {
        // p1 + p2 + 2的起始值就是 m+n-1
        /*int p1 = m - 1, p2 = n - 1;
        int cur;
        while (p1 > -1 || p2 > -1) {
            if (p1 == -1) {
                cur = nums2[p2--];
            } else if (p2 == -1) {
                cur = nums1[p1--];
            } else if (nums1[p1] > nums2[p2]) {
                cur = nums1[p1--];
            } else {
                cur = nums2[p2--];
            }
            nums1[p1 + p2 + 2] = cur;
        }*/

        // leetcode提供的写法，基本一样
        int p1 = m - 1, p2 = n - 1;
        int tail = m + n - 1;
        int cur;
        while (p1 >= 0 || p2 >= 0) {
            if (p1 == -1) {
                cur = nums2[p2--];
            } else if (p2 == -1) {
                cur = nums1[p1--];
            } else if (nums1[p1] > nums2[p2]) {
                cur = nums1[p1--];
            } else {
                cur = nums2[p2--];
            }
            nums1[tail--] = cur;
        }
    }
}
