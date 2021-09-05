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
public class Solution3 implements Solution {

    /**
     * leetcode官方提供的思路：双指针
     * 每次拿两个数组中较小的一个，放到一个新数组中
     * 时间复杂度 o(m+n)，空间复杂度 o(m+n)
     */
    @Override
    public void merge(int[] nums1, int m, int[] nums2, int n) {
        /*int p1 = 0, p2 = 0, i = 0, cur;
        int[] temp = new int[m + n];
        while (p1 < m || p2 < n) {
            if (p1 == m) {
                cur = nums2[p2];
                p2++;
            } else if (p2 == n) {
                cur = nums1[p1];
                p1++;
            } else if (nums1[p1] < nums2[p2]) {
                cur = nums1[p1];
                p1++;
            } else {
                cur = nums2[p2];
                p2++;
            }
            temp[i++] = cur;
        }

        for (int k = 0; k < m + n; k++) {
            nums1[k] = temp[k];
        }*/

        // leetcode提供的写法（用的变量更少，更简洁）：
        int p1 = 0, p2 = 0;
        int[] sorted = new int[m + n];
        int cur;
        while (p1 < m || p2 < n) {
            if (p1 == m) {
                cur = nums2[p2++];
            } else if (p2 == n) {
                cur = nums1[p1++];
            } else if (nums1[p1] < nums2[p2]) {
                cur = nums1[p1++];
            } else {
                cur = nums2[p2++];
            }
            sorted[p1 + p2 - 1] = cur;
        }
        for (int i = 0; i < m + n; i++) {
            nums1[i] = sorted[i];
        }
    }
}
