package com.kittycoder.binarysorttree;

import org.junit.Test;

/**
 * Created by shucheng on 2021/6/30 23:00
 */
public class BinarySortTreeDemoTest {

    private BinarySortTree generateBinarySortTree(int[] arr) {
        BinarySortTree binarySortTree = new BinarySortTree();
        // 循环地添加节点到二叉排序树
        for (int i = 0; i < arr.length; i++) {
            binarySortTree.add(new Node(arr[i]));
        }
        return binarySortTree;
    }

    // 测试节点搜索
    @Test
    public void testSearchNode() {
        int[] arr = {7, 3, 10, 12, 5, 1, 9};
        BinarySortTree binarySortTree = generateBinarySortTree(arr);
        // 中序遍历二叉排序树
        System.out.println("中序遍历二叉排序树~");
        binarySortTree.infixOrder(); // 1,3,5,7,9,10,12

        System.out.println("节点搜索");
        // search(12)->12
        Node searchNode = binarySortTree.search(12);
        System.out.println(searchNode);
        // searchParent(12)->10,searchParent(7)->null
        Node searchParentNode = binarySortTree.searchParent(7);
        System.out.println(searchParentNode);
    }

    // 测试删除叶子节点
    @Test
    public void testDelLeafNode() {
        int[] arr = {7, 3, 10, 12, 5, 1, 9, 2};
        BinarySortTree binarySortTree = generateBinarySortTree(arr);
        System.out.println("删除前");
        binarySortTree.infixOrder();

        binarySortTree.delNode(7);
        // binarySortTree.delNode(5);
        // binarySortTree.delNode(9);
        // binarySortTree.delNode(12);
        System.out.println("删除后");
        binarySortTree.infixOrder();
    }

    // 测试删除只有一棵子树的节点
    @Test
    public void testDelHasOneSubTreeNode() {
        // {7, 10, 12} infixOrder{7,10,12}，删掉10，infixOrder{1,12}
        // 上面这种情况，parent.left就为null
        // {7, 3, 10, 12, 5, 1, 9, 0} infixOrder{0,1,3,5,7,9,10,12}，删掉1，infixOrder{0,3,5,7,9,10,12}
        // {7, 3, 10, 12, 5, 1, 9, 2} infixOrder{1,2,3,5,7,9,10,12}，删掉1，infixOrder{2,3,5,7,9,10,12}
        int[] arr = {7, 3, 10, 12, 5, 1, 9, 0};
        BinarySortTree binarySortTree = generateBinarySortTree(arr);
        System.out.println("删除前");
        binarySortTree.infixOrder();

        binarySortTree.delNode(1);
        System.out.println("删除后");
        binarySortTree.infixOrder();
    }
}