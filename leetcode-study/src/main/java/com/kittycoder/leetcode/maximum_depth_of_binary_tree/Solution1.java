package com.kittycoder.leetcode.maximum_depth_of_binary_tree;

import com.kittycoder.leetcode.util.TreeNode;

/**
 * Created by shucheng on 2021/11/29 7:41
 */
public class Solution1 implements Solution {

    /**
     * 方法1：深度优先搜索
     * 时间复杂度：O(n)，其中n为二叉树节点的个数
     * 空间复杂度：O(log n)，其中n为二叉树节点的个数（leetcode官方写的是O(height)，其中height表示二叉树的高度，这里感觉有点问题，我认为是O(log n)）
     */
    @Override
    public int maxDepth(TreeNode root) {
        if (root == null) {
            return 0;
        } else {
            int leftHeight = maxDepth(root.left);
            int rightHeight = maxDepth(root.right);
            return Math.max(leftHeight, rightHeight) + 1;
        }
    }

    // 下面的写法是自己想的，引入了额外的变量，占用了额外空间，好的写法见上方leetcode官方提供的
    /*@Override
    public int maxDepth(TreeNode root) {
        List<Integer> depthList = new ArrayList<>();
        maxDepth(root, 1, depthList);
        return Collections.max(depthList);
    }

    private void maxDepth(TreeNode root, int depth, List<Integer> depthList) {
        if (root != null) {
            maxDepth(root.left, depth + 1, depthList);
            maxDepth(root.right, depth + 1, depthList);
            if (root.left == null && root.right == null) {
                depthList.add(depth);
            }
        }
    }*/
}
