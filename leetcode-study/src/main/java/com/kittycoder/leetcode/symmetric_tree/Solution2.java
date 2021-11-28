package com.kittycoder.leetcode.symmetric_tree;

import com.kittycoder.leetcode.util.TreeNode;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Created by shucheng on 2021/11/28 23:09
 */
public class Solution2 implements Solution {
    /**
     * 迭代
     * @param root
     * @return
     */
    @Override
    public boolean isSymmetric(TreeNode root) {
        return check(root, root);
    }

    private boolean check(TreeNode u, TreeNode v) {
        Queue<TreeNode> q = new LinkedList<>();
        // 入队
        q.offer(u);
        q.offer(v);

        while (!q.isEmpty()) {
            // 出队
            u = q.poll();
            v = q.poll();

            if (u == null && v == null) {
                continue;
            }
            if (u == null || v == null || u.val != v.val) {
                return false;
            }

            q.offer(u.left);
            q.offer(v.right);

            q.offer(u.right);
            q.offer(v.left);
        }
        return true;
    }
}
