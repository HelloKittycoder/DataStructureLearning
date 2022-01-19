package com.kittycoder.leetcode.single_number;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * Created by shucheng on 2022/1/19 12:16
 */
public class Solution2 implements Solution {

    @Override
    public int singleNumber(int[] nums) {
        // 其他方法
        /**
         * 方法1：使用集合
         * 遍历数组中的每个数字，如果集合中包含这个数字，则从集合中移除这个数字，
         * 如果集合中不包含这个数字，则向集合中添加这个数字，最后剩下的数字就是只出现一次的数字
         *
         * 算法复杂度：
         * 时间：O(n)
         * 空间：O(n)
         */
        /*Set<Integer> set = new HashSet<>();
        for (int num : nums) {
            if (set.contains(num)) {
                set.remove(num);
            } else {
                set.add(num);
            }
        }
        return set.iterator().next();*/

        /**
         * 方法2：使用哈希表
         * 遍历数组中的每个数字，统计不同数字出现的次数
         * 遍历哈希表，找到只出现1次的数字
         */
        /*Map<Integer, Integer> map = new HashMap<>();
        for (int num : nums) {
            Integer times = map.get(num);
            if (times == null) {
                times = 0;
            }
            map.put(num, times + 1);
        }

        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            if (entry.getValue() == 1) {
                return entry.getKey();
            }
        }
        return -1;*/

        /**
         * 方法3：使用集合
         * 使用集合存储数组中的所有数字，并求出数组中所有元素之和
         * 然后用集合中所有素之和的2倍减去数组元素之和，得到的就是只出现1次的数字
         */
        Set<Integer> set = new HashSet<>();
        int arrSum = 0;
        for (int num : nums) {
            set.add(num);
            arrSum += num;
        }

        int setSum = 0;
        for (int sEle : set) {
            setSum += sEle;
        }
        return 2 * setSum - arrSum;
    }
}
