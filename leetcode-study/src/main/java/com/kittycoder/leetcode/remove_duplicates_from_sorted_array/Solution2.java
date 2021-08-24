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
public class Solution2 implements Solution {

    // leetcode提供的双指针解法 时间复杂度 o(n)
    @Override
    public int removeDuplicates(int[] nums) {
        /*if (nums == null || nums.length == 0) {
            return 0;
        }
        int fast = 1, slow = 1;
        for (; fast < nums.length; fast++) {
            if (nums[fast] != nums[fast - 1]) {
                nums[slow] = nums[fast];
                slow++;
            }
        }*/

        int n;
        if (nums == null || (n = nums.length) == 0) {
            return 0;
        }

        int fast = 1, slow = 1;
        while (fast < n) {
            if (nums[fast] != nums[fast - 1]) {
                nums[slow] = nums[fast];
                ++slow;
            }
            ++fast;
        }
        return slow;
    }
}
