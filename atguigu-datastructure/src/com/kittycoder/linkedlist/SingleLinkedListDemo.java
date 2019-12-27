package com.kittycoder.linkedlist;

/**
 * Created by shucheng on 2019/12/24 22:14
 * 实现一个单向链表
 */
public class SingleLinkedListDemo {
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

    // 添加英雄时，根据排名将英雄插入指定位置
    // （如果有这个排名，则添加失败，并给出提示）
    // （课程里的实现，比我的更优：1.只用了一个temp变量；2.思路更清晰）
    public void addByOrder2(HeroNode heroNode) {
        // 因为头节点不能动，因此我们仍然通过一个辅助指针（变量）来帮助找到添加的位置
        // 因为是单链表，我们要找的temp是位于添加位置的前一个节点，如果是后一个节点插入不了
        // 说明：其实是后一个节点的话也可以插入addByOrder（用两个变量可以实现）
        HeroNode temp = head;
        // 找到需要从哪个位置放节点
        boolean flag = false; // 记录是否有相同编号的节点，默认为false
        while (true) {
            if (temp.next == null) { // 说明temp已经在链表的最后
                break;
            }
            if (temp.next.no > heroNode.no) { // 位置找到，就在temp的后面插入
                break;
            } else if (temp.next.no == heroNode.no) { // 说明希望添加的heroNode的编号已经存在
                flag = true; // 说明编号存在
                break;
            }
            temp = temp.next; // 后移，遍历当前列表
        }
        // 判断flag的值
        if (flag) { // 说明编号已经存在，不能添加
            System.out.printf("准备插入的英雄的编号%d已经存在了，不能加入\n", heroNode.no);
        } else {
            // 插入到链表中，temp的后面
            heroNode.next = temp.next;
            temp.next = heroNode;
        }
    }

    // 修改节点的信息，根据no编号来修改，即no编号不能改
    // 说明：
    // 1.根据newHeroNode的no来修改即可
    public void update(HeroNode newHeroNode) {
        // 判断是否为空
        if (head.next == null) {
            System.out.println("链表为空，未修改");
            return;
        }
        HeroNode temp = head.next;
        // 通过遍历找到需要修改的节点
        boolean isFound = false;
        while (true) {
            if (temp == null) {
                break; // 已经遍历完链表
            }
            if (temp.no == newHeroNode.no) {
                isFound = true;
                break;
            }
            temp = temp.next;
        }

        if (isFound) {
            temp.name = newHeroNode.name;
            temp.nickname = newHeroNode.nickname;
        } else {
            System.out.printf("没有找到编号%d的节点，不能修改\n", newHeroNode.no);
        }
    }

    // 删除节点
    // 思路
    // 1.head不能动，因此我们需要一个temp辅助节点找到待删除节点的前一个节点
    // 2.我们在比较时，是将temp.next.no和需要删除的节点的no进行比较
    public void del(int no) {
        HeroNode temp = head;
        // 通过遍历找到需要删除的节点
        boolean flag = false;
        while (true) {
            if (temp.next == null) {
                break; // 已经遍历完链表
            }
            if (temp.next.no == no) {
                flag = true;
                break;
            }
            temp = temp.next;
        }
        if (flag) { // 找到
            // 可以删除
            temp.next = temp.next.next;
        } else {
            System.out.printf("没有找到编号%d的节点，不能删除\n", no);
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