package com.kittycoder.linkedlist;

/**
 * Created by shucheng on 2019/12/24 22:14
 * 实现一个单向链表
 */
public class SingleLinkedListDemo {
    public static void main(String[] args) {
        // 进行测试
        // 先创建节点
        /*HeroNode heroNode1 = new HeroNode(1, "宋江", "及时雨");
        HeroNode heroNode2 = new HeroNode(2, "卢俊义", "玉麒麟");
        HeroNode heroNode3 = new HeroNode(3, "吴用", "智多星");
        HeroNode heroNode4 = new HeroNode(4, "林冲", "豹子头");*/

        // 创建链表
        SingleLinkedList singleLinkedList = new SingleLinkedList();

        // 向链表添加数据
        /*singleLinkedList.add(heroNode1);
        singleLinkedList.add(heroNode2);
        singleLinkedList.add(heroNode3);
        singleLinkedList.add(heroNode4);*/

        /*singleLinkedList.addByOrder(heroNode4);
        singleLinkedList.addByOrder(heroNode1);
        singleLinkedList.addByOrder(heroNode3);
        singleLinkedList.addByOrder(heroNode2);*/

        for (int i = 0; i < 1000; i++) {
            int  no = (int) (Math.random() * i);
            HeroNode h = new HeroNode(no, "张三" + no, "昵称" + no);
            singleLinkedList.addByOrder(h);
        }

        // 显示链表数据
        singleLinkedList.list();
    }
}

// 定义SingleLinkedList管理我们的英雄
class SingleLinkedList {
    // 先初始化一个头节点，头节点不要动，不存放具体的数据
    private HeroNode head = new HeroNode(0, "", "");

    // 添加节点到单向链表
    // 添加英雄时，直接添加到链表的尾部
    // 思路，当不考虑编号顺序时
    // 1.找到当前链表的最后节点
    // 2.将最后这个节点的next指向新的节点
    public void add(HeroNode heroNode) {
        // 因为head节点不能动，因此我们需要一个辅助变量temp
        HeroNode temp = head;
        // 遍历链表，找到最后
        while (true) {
            if (temp.next == null) {
                break;
            }
            // 如果没有找到最后，则将temp后移
            temp = temp.next;
        }
        // 当退出while循环时，temp就指向了链表的最后
        // 将最后这个节点的next指向新的节点
        temp.next = heroNode;
    }

    // 添加英雄时，根据排名将英雄插入指定位置
    // （如果有这个排名，则添加失败，并给出提示）
    public void addByOrder(HeroNode heroNode) {
        // 判断链表是否为空
        if (head.next == null) {
            head.next = heroNode;
            return;
        }
        // 找到需要从哪个位置放节点
        HeroNode currentNode = head.next;
        HeroNode beforeNode = head;
        while (true) {
            // 判断是否到链表最后
            if (currentNode == null) {
                beforeNode.next = heroNode;
                break;
            }
            // 如果节点编号小于待放入节点编号，说明待放入节点应该放到当前节点后面
            if (currentNode.no < heroNode.no) {
                beforeNode = currentNode;
                currentNode = currentNode.next;
            } else if (currentNode.no > heroNode.no) {
                // 如果节点编号大于待放入节点编号，说明待放入节点应该放到当前节点之前
                beforeNode.next = heroNode;
                heroNode.next = currentNode;
                break;
            } else {
                System.out.println(heroNode + "和节点" + currentNode +"的no重复");
                break;
            }
        }
    }

    // 显示链表[遍历]
    public void list() {
        // 判断链表是否为空
        if (head.next == null) {
            System.out.println("链表为空");
            return;
        }
        // 因为头节点不能动，因此我们需要一个辅助变量来遍历
        HeroNode temp = head.next;
        while (true) {
            // 判断是否到链表最后
            if (temp == null) {
                break;
            }
            // 输出节点的信息
            System.out.println(temp);
            // 将temp后移
            temp = temp.next;
        }
    }
}

// 定义HeroNode
class HeroNode {
    public int no;
    public String name;
    public String nickname;
    public HeroNode next; // 指向下一个节点
    public HeroNode(int no, String name, String nickname) {
        this.no = no;
        this.name = name;
        this.nickname = nickname;
    }
    // 为了显示方便，我们重写toString方法
    @Override
    public String toString() {
        return "HeroNode{" +
                "no=" + no +
                ", name='" + name + '\'' +
                ", nickname='" + nickname + '\'' +
                '}';
    }
}