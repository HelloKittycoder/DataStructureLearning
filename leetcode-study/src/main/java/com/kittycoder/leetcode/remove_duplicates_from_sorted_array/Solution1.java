/**
 * Leetcode - remove_duplicates_from_sorted_array
 */
package com.kittycoder.leetcode.remove_duplicates_from_sorted_array;

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
     * 双指针
     * 核心思路是对的
     * 优化点：（1）没有必要在内部加一层循环来找第一个和当前索引i位置上的数字
     * 不相同的数字的位置
     * （2）没有必要将索引从(i+1)到(j-1)的数，全部弄成nums[j]
     */
    @Override
    public int removeDuplicates(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        int i, j;
        // j用来记录第一个和当前索引i位置上的数字不相同的数字的位置
        out:
        for (i = 0, j = 0; i < nums.length && j < nums.length;) {
            while (nums[i] == nums[j]) {
                if (j == nums.length - 1) {
                    break out;
                }
                j++;
            }
            // 将索引从(i+1)到(j-1)的数，全部弄成nums[j]
            for (int k = i + 1; k < j; k++) {
                nums[k] = nums[j];
            }
            i++;
        }
        return i + 1;
    }
}
