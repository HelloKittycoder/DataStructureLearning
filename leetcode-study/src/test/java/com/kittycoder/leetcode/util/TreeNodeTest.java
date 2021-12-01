package com.kittycoder.leetcode.util;

import org.junit.Test;

import java.util.Arrays;

/**
 * Created by shucheng on 2021/12/1 21:08
 */
public class TreeNodeTest {

    @Test
    public void inOrderStr() {
        int[] arr = {-10,-3,0,5,9};
        System.out.println(Arrays.toString(arr));

        /**
         *      0
         *     / \
         *  -10  5
         *    \   \
         *   -3   9
         */
        TreeNode t1 = new TreeNode(0,
                new TreeNode(-10,
                        null, new TreeNode(-3)),
                new TreeNode(5,
                        null, new TreeNode(9))
        );
        System.out.println(t1.inOrderStr());
        System.out.println("====================================");

        /**
         *      0
         *     / \
         *   -3  5
         *    \   \
         *   -3   9
         */
        /**
         *      0
         *     / \
         *   -3   9
         *   /   /
         * -10  5
         */
        TreeNode t2 = new TreeNode(0,
                new TreeNode(-3,
                        new TreeNode(-10), null),
                new TreeNode(9,
                        new TreeNode(5), null)
        );
        System.out.println(t2.inOrderStr());

        // 上面两棵树的中序遍历结果都是 -10,-3,0,5,9,
    }

    @Test
    public void testEquals() {
        /**
         *      0
         *     / \
         *  -10  5
         *    \   \
         *   -3   9
         */
        TreeNode t1 = new TreeNode(0,
                new TreeNode(-10,
                        null, new TreeNode(-3)),
                new TreeNode(5,
                        null, new TreeNode(9))
        );

        /**
         *      0
         *     / \
         *  -10  5
         *    \   \
         *   -3   9
         */
        TreeNode t2 = new TreeNode(0,
                new TreeNode(-10,
                        null, new TreeNode(-3)),
                new TreeNode(5,
                        null, new TreeNode(9))
        );

        // 上面两棵树虽然是不同的对象，但是两棵树的整体结构及各节点值是一样的
        System.out.println(t1.equals(t2)); // true
    }
}
