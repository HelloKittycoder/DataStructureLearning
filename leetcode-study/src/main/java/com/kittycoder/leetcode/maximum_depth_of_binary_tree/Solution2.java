package com.kittycoder.leetcode.maximum_depth_of_binary_tree;

import com.kittycoder.leetcode.util.TreeNode;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Created by shucheng on 2021/11/29 23:29
 */
public class Solution2 implements Solution {

    /**
     * 方法2：广度优先搜索
     * 时间复杂度：O(n)，其中n为二叉树节点的个数
     * 空间复杂度：O(log n)，其中n为二叉树节点的个数（leetcode官方写的是O(n)，我认为这部分写的有问题，这里应该是O(log n)）
     */
    @Override
    public int maxDepth(TreeNode root) {
        if (root == null) {
            return 0;
        }

        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        int ans = 0;
        while (!queue.isEmpty()) {
            int size = queue.size();
            /**
             * size是用来判断当前层（a层）的节点是否给poll出去的标志
             * size>0表示还没结束，size==0表示已经都poll出去了
             */
            while (size > 0) {
                // 把当前层（a层）的下一层（(a+1)层）的所有节点加到queue中
                TreeNode node = queue.poll();
                if (node.left != null) {
                    queue.offer(node.left);
                }
                if (node.right != null) {
                    queue.offer(node.right);
                }
                size--;
            }
            // 已经把a层的节点给poll出去了
            ans++;
        }
        return ans;
    }
}
