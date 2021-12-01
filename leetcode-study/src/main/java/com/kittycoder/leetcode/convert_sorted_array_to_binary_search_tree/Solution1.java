package com.kittycoder.leetcode.convert_sorted_array_to_binary_search_tree;

import com.kittycoder.leetcode.util.TreeNode;

import java.util.Random;

/**
 * Created by shucheng on 2021/12/1 7:39
 */
public class Solution1 implements Solution {

    Random rand = new Random();

    @Override
    public TreeNode sortedArrayToBST(int[] nums) {
        return helper(nums, 0, nums.length - 1);
    }

    private TreeNode helper(int[] nums, int left, int right) {
        if (left > right) {
            return null;
        }

        /**
         * 下面3种方法的算法复杂度均为：
         * 时间：O(n)，其中n是数组的长度
         * 空间：O(log n)，其中n是数组的长度
         */

        /**
         * 方法1：中序遍历，总是选择中间位置左边的数字作为根节点
         */
        int mid = (left + right) / 2;
        /**
         * 方法2：中序遍历，总是选择中间位置右边的数字作为根节点
         */
        // int mid = (left + right + 1) / 2;
        /**
         * 方法3：中序遍历，选择任意一个中间位置作为根节点
         * 根节点下标为mid=(left+right)/2和mid=(left+right+1)/2两者中随机选择一个
         */
        // int mid = (left + right + rand.nextInt(2)) / 2;

        TreeNode root = new TreeNode(nums[mid]);
        root.left = helper(nums, left, mid - 1);
        root.right = helper(nums, mid + 1, right);
        return root;
    }
}
