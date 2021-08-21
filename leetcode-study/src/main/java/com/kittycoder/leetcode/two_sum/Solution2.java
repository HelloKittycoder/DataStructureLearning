/**
 * Leetcode - two_sum
 */
package com.kittycoder.leetcode.two_sum;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by shucheng on 2021/8/21 22:52
 */
public class Solution2 implements Solution {

    // 哈希表 时间复杂度o(n)
    @Override
    public int[] twoSum(int[] nums, int target) {
        Map<Integer, Integer> hashTable = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            if (hashTable.containsKey(target - nums[i])) {
                return new int[]{hashTable.get(target - nums[i]), i};
            }
            hashTable.put(nums[i], i);
        }
        return new int[0];
    }
}
