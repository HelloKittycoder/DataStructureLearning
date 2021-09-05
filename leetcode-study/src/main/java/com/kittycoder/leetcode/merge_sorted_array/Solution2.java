/**
 * Leetcode - merge_sorted_array
 */
package com.kittycoder.leetcode.merge_sorted_array;

import java.util.Arrays;

/**
 * log instance is defined in Solution interface
 * this is how slf4j will work in this class:
 * =============================================
 *     if (log.isDebugEnabled()) {
 *         log.debug("a + b = {}", sum);
 *     }
 * =============================================
 */
public class Solution2 implements Solution {

    /**
     * leetcode官方提供的思路：直接合并后排序
     * 先将数组nums2放到数组nums1的尾部，然后直接对整个数组进行排序
     *
     * 假设用的是快速排序，序列长度为 m+n
     * 时间复杂度为 o((m+n)log(m+n))，空间复杂度为 o(log(m+n))
     */
    @Override
    public void merge(int[] nums1, int m, int[] nums2, int n) {
        /*int i = m;
        int j = 0;
        while (i < nums1.length) {
            nums1[i] = nums2[j];
            i++;
            j++;
        }*/

        // 下面的写法和上面的其实是一样的
        for (int i = 0; i < n; i++) {
            nums1[m + i] = nums2[i];
        }
        Arrays.sort(nums1);
    }
}
