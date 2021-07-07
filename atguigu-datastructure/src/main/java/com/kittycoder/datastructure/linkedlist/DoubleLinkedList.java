package com.kittycoder.datastructure.linkedlist;

/**
 * Created by shucheng on 2019/12/29 0:12
 * 实现一个双向链表
 */
public class DoubleLinkedList {

    // 先初始化一个头节点，头节点不要动，不存放具体的数据
    private HeroNode2 head = new HeroNode2(0, "", "");

    // 添加节点到双向链表，直接添加到链表的尾部
    public void add(HeroNode2 heroNode) {
        // 找到最后一个节点
        HeroNode2 temp = head;
        while (temp.next != null) {
            temp = temp.next;
        }
        // 形成一个双向链表
        temp.next = heroNode;
        heroNode.pre = temp;
    }

    // 添加节点到双向链表，根据排名添加到指定位置
    public void addByOrder(HeroNode2 heroNode) {
        HeroNode2 temp = head;
        boolean flag = false;
        while (true) {
            if (temp.next == null) { // 说明temp已经在链表的最后
                break;
            }
            if (temp.next.no > heroNode.no) { // 位置找到，就在temp的后面插入
                break;
            } else if (temp.next.no > heroNode.no) { // 说明希望添加的heroNode编号已经存在
                flag = true; // 说明编号存在
                break;
            }
            temp = temp.next; // 后移，遍历当前链表
        }
        // 判断flag的值
        if (flag) { // 说明编号已经存在，不能添加
            System.out.printf("准备插入的英雄的编号%d已经存在了，不能加入\n", heroNode.no);
        } else {
            // 插入到链表中，temp的后面（因为temp是temp.next的前一个节点，需要插到temp和temp.next之间，
            // 1和2的顺序不能调换；如果先执行2的话，hero和原来的temp.next就无法建立连接）
            // 1.建立heroNode和temp.next的连接
            if (temp.next != null) {
                temp.next.pre = heroNode;
                heroNode.next = temp.next;
            }
            // 2.建立temp和heroNode的连接
            temp.next = heroNode;
            heroNode.pre = temp;
        }
    }

    // 修改节点的信息，根据no编号来修改，即no编号不能改
    // 可以看到双向链表的节点内容修改和单向链表一样
    // 只是节点类型改成HeroNode2
    public void update(HeroNode2 newHeroNode) {
        if (head.next == null) {
            System.out.println("链表为空，未修改");
            return;
        }
        // 找到no编号相同的节点
        HeroNode2 temp = head.next;
        boolean isFound = false;
        while (temp != null) {
            if (temp.no == newHeroNode.no) {
                isFound = true;
                break;
            }
            temp = temp.next;
        }

        if (isFound) {
            // 形成一个双向链表
            temp.name = newHeroNode.name;
            temp.nickname = newHeroNode.nickname;
        } else {
            System.out.printf("没有找到编号%d的节点，不能修改\n", newHeroNode.no);
        }
    }

    // 删除节点
    // 1.对于双向链表，我们可以直接找到要删除的这个节点
    // 2.找到后自我删除即可
    public void del(int no) {
        if (head.next == null) {
            System.out.println("链表为空，未删除");
            return;
        }
        // 找到no编号相同的节点
        HeroNode2 temp = head.next;
        boolean isFound = false;
        while (temp != null) {
            if (temp.no == no) {
                isFound = true;
                break;
            }
            temp = temp.next;
        }

        if (isFound) {
            temp.pre.next = temp.next;
            // 这里不能直接写 temp.next.pre = temp.pre;
            // 如果是最后一个节点，就不需要执行下面这句话，否则会出现空指针
            if (temp.next != null) {
                temp.next.pre = temp.pre;
            }
        } else {
            System.out.printf("没有找到编号%d的节点，不能删除\n", no);
        }
    }

    // 打印所有节点数据
    public void list() {
        if (head.next == null) {
            System.out.println("链表为空");
            return;
        }
        HeroNode2 temp = head.next;
        while (temp != null) {
            System.out.println(temp);
            temp = temp.next;
        }
    }
}

// 定义HeroNode2
class HeroNode2 {
    public int no;
    public String name;
    public String nickname;
    public HeroNode2 next; // 指向下一个节点
    public HeroNode2 pre; // 指向上一个节点

    public HeroNode2(int no, String name, String nickname) {
        this.no = no;
        this.name = name;
        this.nickname = nickname;
    }

    @Override
    public String toString() {
        return "HeroNode2{" +
                "no=" + no +
                ", name='" + name + '\'' +
                ", nickname='" + nickname + '\'' +
                '}';
    }
}