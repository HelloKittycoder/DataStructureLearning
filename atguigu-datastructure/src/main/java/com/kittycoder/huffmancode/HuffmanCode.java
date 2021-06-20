package com.kittycoder.huffmancode;

import java.util.*;

/**
 * Created by shucheng on 2021/6/16 21:50
 * 数据压缩-赫夫曼编码
 */
public class HuffmanCode {
    public static void main(String[] args) {
        String content = "i like like like java do you like a java";
        byte[] contentBytes = content.getBytes();
        // System.out.println(contentBytes.length);

        List<Node> nodes = getNodes(contentBytes);
        System.out.println("nodes=" + nodes);

        // 创建赫夫曼树
        Node huffmanTreeRoot = createHuffmanTree(nodes);
        // huffmanTreeRoot.preOrder();
    }

    // 前序遍历
    private static void preOrder(Node root) {
        if (root != null) {
            root.preOrder();
        } else {
            System.out.println("赫夫曼树为空");
        }
    }

    /**
     * 根据原始字节数组生成节点对象列表
     * @param bytes 接收的字节数组
     * @return 返回的就是List形式 [Node[date=97,weight=5], Node[date=32,weight=9]......],
     */
    private static List<Node> getNodes(byte[] bytes) {
        // 创建一个ArrayList
        List<Node> nodes = new ArrayList<>();
        // 遍历bytes，统计每一个byte出现的次数 key是byte，value是byte出现的次数
        Map<Byte, Integer> counts = new HashMap<>();
        for (byte b : bytes) {
            Integer count = counts.get(b);
            if (count == null) { // Map还没有这个字符数据，第一次
                counts.put(b, 1);
            } else {
                counts.put(b, count + 1);
            }
        }

        // 把每一个键值对转成Node对象，并加到nodes集合
        for (Map.Entry<Byte, Integer> entry : counts.entrySet()) {
            nodes.add(new Node(entry.getKey(), entry.getValue()));
        }
        return nodes;
    }

    // 通过List<Node>创建对应的赫夫曼树
    private static Node createHuffmanTree(List<Node> nodes) {
        while (nodes.size() > 1) {
            // 排序，从小到大
            Collections.sort(nodes);
            // 取出第一棵最小的二叉树
            Node leftNode = nodes.get(0);
            // 取出第二棵最小的二叉树
            Node rightNode = nodes.get(1);
            // 创建一棵新的二叉树，它的根节点 没有data，只有权值
            Node parent = new Node(null, leftNode.weight + rightNode.weight);
            parent.left = leftNode;
            parent.right = rightNode;

            // 将已经处理的两棵二叉树从nodes中删除
            nodes.remove(leftNode);
            nodes.remove(rightNode);
            // 将新的二叉树，加到nodes中
            nodes.add(parent);
        }
        // nodes 最后的节点，就是赫夫曼树的根节点
        return nodes.get(0);
    }
}

// 创建Node，带数据和权值
class Node implements Comparable<Node> {
    Byte data; // 存放数据（字符）本身，比如：'a'->97，' '->32
    int weight; // 权值，表示字符出现的次数
    Node left;
    Node right;

    public Node(Byte data, int weight) {
        this.data = data;
        this.weight = weight;
    }

    @Override
    public int compareTo(Node o) {
        // 从小到大排序
        return this.weight - o.weight;
    }

    @Override
    public String toString() {
        return "Node{" +
                "data=" + data +
                ", weight=" + weight +
                '}';
    }

    // 前序遍历
    public void preOrder() {
        System.out.println(this);
        if (this.left != null) {
            this.left.preOrder();
        }
        if (this.right != null) {
            this.right.preOrder();
        }
    }
}
