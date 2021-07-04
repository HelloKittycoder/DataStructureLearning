package com.kittycoder.avl;

import org.junit.Test;

/**
 * Created by shucheng on 2021/7/4 22:13
 */
public class AVLTreeDemoTest {

    @Test
    public void testLeftRotate() {
        int[] arr = {4, 3, 6, 5, 7, 8};
        // 创建一个AVLTree对象
        AVLTree avlTree = new AVLTree();
        // 添加节点
        for (int i = 0; i < arr.length; i++) {
            avlTree.add(new Node(arr[i]));
        }

        // 遍历
        System.out.println("中序遍历");
        avlTree.infixOrder(); // 3,4,5,6,7,8

        System.out.println("在平衡处理后~~");
        System.out.println("树的高度=" + avlTree.getRoot().height()); // 3
        System.out.println("树的左子树高度=" + avlTree.getRoot().leftHeight()); // 2
        System.out.println("树的右子树高度=" + avlTree.getRoot().rightHeight()); // 2
    }

    @Test
    public void testRightRotate() {
        int[] arr = {10, 12, 8, 9, 7, 6};
        // 创建一个AVLTree对象
        AVLTree avlTree = new AVLTree();
        // 添加节点
        for (int i = 0; i < arr.length; i++) {
            avlTree.add(new Node(arr[i]));
        }

        // 遍历
        System.out.println("中序遍历");
        avlTree.infixOrder(); // 6,7,8,9,10,12

        System.out.println("在平衡处理后~~");
        System.out.println("树的高度=" + avlTree.getRoot().height()); // 3
        System.out.println("树的左子树高度=" + avlTree.getRoot().leftHeight()); // 2
        System.out.println("树的右子树高度=" + avlTree.getRoot().rightHeight()); // 2
    }
}
