package com.kittycoder.leetcode.util;

import java.util.Arrays;
import java.util.Objects;

/**
 * Created by shucheng on 2021/9/5 21:25
 */
public class TreeNode {
    public int val;
    public TreeNode left;
    public TreeNode right;

    public TreeNode() {
    }

    public TreeNode(int val) {
        this.val = val;
    }

    public TreeNode(int val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }

    // 返回二叉树的中序遍历结果，以字符串形式返回
    public String inOrderStr() {
        StringBuilder sb = new StringBuilder();
        inOrder(sb);
        return sb.toString();
    }

    public void inOrder(StringBuilder sb) {
        if (left != null) {
            left.inOrder(sb);
        }
        // System.out.println(val);
        sb.append(val).append(",");
        if (right != null) {
            right.inOrder(sb);
        }
    }

    // 判断两棵树的整体结构及各节点值是否相同
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass()) return false;

        TreeNode treeNode = (TreeNode) o;
        return this.val == treeNode.val && Objects.equals(this.left, treeNode.left) && Objects.equals(this.right, treeNode.right);
    }
}
