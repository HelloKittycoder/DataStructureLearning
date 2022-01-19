package com.kittycoder.leetcode.single_number;

/**
 * Created by shucheng on 2022/1/19 12:07
 */
public class Solution1 implements Solution {

    /**
     * 方法1：位运算（使用异或）
     *
     * 算法复杂度：
     * 时间：O(n)，其中n是数组长度，只需要对数组遍历一次
     * 空间：O(1)
     * @param nums
     * @return
     */
    @Override
    public int singleNumber(int[] nums) {
        int single = 0;
        for (int num : nums) {
            single ^= num;
        }
        return single;
    }
}
