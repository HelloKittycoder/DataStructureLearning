package com.kittycoder.linkedlist;

import java.util.Stack;

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

    public SingleLinkedList() {}

    // 通过头节点来创建一个单链表
    public SingleLinkedList(HeroNode head) {
        this.head = head;
    }

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

    // 获取头节点
    public HeroNode getHead() {
        return head;
    }

    // 说明：这里的链表都是有头节点的，如果是空链表，则只有一个头节点
    // 根据头节点获取整个链表的节点数量（排除头节点）
    public static int getLength(HeroNode head) {
        if (head.next == null) {
            return 0;
        }
        int length = 0;
        // 定义一个辅助变量，这里我们没有统计头节点
        HeroNode temp = head.next;
        while (temp != null) {
            length++;
            temp = temp.next;
        }
        return length;
    }

    // 查找单链表的倒数第k个节点
    // 思路
    // 1.编写一个方法，接收head节点，同时接收一个k
    // 2.k 表示倒数第k个节点
    // 3.先把链表从头到尾遍历，得到链表的总长度 getLength
    // 4.得到size后，我们从链表的第一个开始遍历（length-k）个，就可以得到
    // 5.如果找到了，则返回该节点，否则返回null
    public static HeroNode findLastIndexNode(HeroNode head, int k) {
        // 如果链表为空，返回null
        if (head.next == null) {
            return null; // 没有找到
        }
        // 第一次遍历得到链表的长度（节点个数）
        int length = getLength(head);
        // 第二次遍历 length-k位置，就是我们倒数的第k个节点
        // 先对k校验
        if (k <= 0 || k > length) {
            System.out.printf("倒数第%d个节点不存在", k);
            return null;
        }
        // 定义一个辅助遍历，for循环定位到倒数的k
        HeroNode temp = head.next;
        for (int i = 0; i < length - k; i++) {
            temp = temp.next;
        }
        return temp;
    }

    // 单链表反转（自己的思路，效率比较低）
    public static SingleLinkedList reverseLinkedList(HeroNode head) {
        int length = getLength(head);
        SingleLinkedList s = new SingleLinkedList();
        for (int i = 1; i <= length; i++) {
            HeroNode node = cloneNode(findLastIndexNode(head, i));
            s.add(node);
        }
        return s;
    }

    // 避免在单链表反转过程中在原来的node中生成循环引用，取不到节点数量
    public static HeroNode cloneNode(HeroNode oldNode) {
        HeroNode heroNode = new HeroNode(oldNode.no, oldNode.name, oldNode.nickname);
        return heroNode;
    }

    // 课程中的思路（使用头插法）
    public static SingleLinkedList reverseLinkedList2(HeroNode head) {
        SingleLinkedList s = new SingleLinkedList(head);
        // 如果当前链表为空，或者只有一个节点，无需反转，直接返回
        if (head.next == null || head.next.next == null) {
            return s;
        }
        // 定义一个辅助的指针（变量），帮助我们遍历原来的链表
        HeroNode temp = head.next;
        HeroNode oldNext = null;
        HeroNode reverseHead = new HeroNode(0, "", "");
        while (temp != null) {
            oldNext = temp.next; // 先暂时保存当前节点的下一个节点
            // 以下两行代码是关键：
            temp.next = reverseHead.next; // 断开节点原来的链接，然后指向新的head的下一个节点
            reverseHead.next = temp; // 将新的head的下一个节点指向temp
            temp = oldNext;
        }
        head.next = reverseHead.next;
        return s;
    }

    // 从尾到头打印单链表（使用数组存放节点）
    public static void reversePrint(HeroNode head) {
        /*将原有单链表反转，再打印（这种确实能逆序打印，但是影响了原有的数据结构）
        SingleLinkedList singleLinkedList = reverseLinkedList2(head);
        singleLinkedList.list();*/

        HeroNode temp = head.next;
        if (temp == null) { // 当前链表为空
            return;
        }
        // 将链表元素倒序存放到数组中
        int lenth = getLength(head);
        HeroNode[] nodeArr = new HeroNode[lenth];
        for (int i = 0; i < lenth; i++) {
            if (temp == null) {
                break;
            }
            nodeArr[lenth - i - 1] = temp;
            temp = temp.next;
        }
        // 打印数组元素
        for (int i = 0; i < lenth; i++) {
            System.out.println(nodeArr[i]);
        }
    }

    // 从尾到头打印单链表（使用栈存放节点）
    public static void reversePrint2(HeroNode head) {
        HeroNode temp = head.next;
        if (temp == null) { // 当前链表为空
            return;
        }
        // 创建一个栈，将所有节点压入栈中
        Stack<HeroNode> stack = new Stack<>();
        while (temp != null) {
            stack.push(temp);
            temp = temp.next;
        }
        // 弹出栈中的所有节点
        while (stack.size() > 0) { // 或写成 !stack.isEmpty()
            System.out.println(stack.pop());
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