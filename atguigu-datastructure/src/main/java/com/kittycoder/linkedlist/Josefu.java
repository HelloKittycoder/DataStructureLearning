package com.kittycoder.linkedlist;

/**
 * Created by shucheng on 2019/12/30 0:06
 * 课程中的约瑟夫问题实现
 * 使用一个不带头节点的环形链表来实现
 */
public class Josefu {
    // 创建一个first节点，当前没有编号
    private Boy first = null;

    // 添加小孩节点，构建成一个环形的链表
    public void addBoy(int nums) {
        // nums做一个数据校验
        if (nums < 1) {
            System.out.println("nums的值不正确");
            return;
        }
        Boy curBoy = null; // 辅助指针，帮助构建环形链表
        // 使用for来创建我们的环形链表
        for (int i = 1; i <= nums; i++) {
            // 根据编号，创建小孩节点
            Boy boy = new Boy(i);
            // 如果是第一个小孩
            if (i == 1) {
                first = boy;
                first.setNext(first); // 构成环
                curBoy = first; // 让curBoy指向第一个小孩
            } else {
                curBoy.setNext(boy);
                boy.setNext(first);
                curBoy =  boy;
            }
        }
    }

    // 遍历当前的环形链表
    public void showBoy() {
        // 判断链表是否为空
        if (first == null) {
            System.out.println("没有任何小孩~~");
            return;
        }
        // 因为first不能当，因此我们仍然使用一个辅助指针完成遍历
        Boy curBoy = first;
        while (true) {
            System.out.printf("小孩的编号 %d \n", curBoy.getNo());
            if (curBoy.getNext() == first) { // 说明已经遍历完毕
                break;
            }
            curBoy = curBoy.getNext(); // curBoy后移
        }
    }

    /**
     * countBoy和countBoy2的差异：
     * 1.多用了一个start变量，为了不影响原有的first指向
     * 2.找startNo对应的节点和它的前一个节点helper
     * （1）countBoy是从first开始找起，直到first是startNo对应的节点，helper自动是first的前一个节点
     * （2）countBoy是先把helper指向最后一个小孩节点，然后再同时把first和helper移动(start-1)次，
     * 让first能指向startNo对应的节点
     */
    /**
     * 根据用户的输入，计算出小孩出圈的顺序（根据课程思路，自己事先写的）
     * @param startNo 表示从第几个小孩开始数数
     * @param countNum 表示数几下
     * @param nums 表示最初有多少个小孩在圈中
     */
    public void countBoy(int startNo, int countNum, int nums) {
        // 先对数据进行校验
        if (first == null || startNo < 1 || startNo > nums) {
            System.out.println("参数输入有误，请重新输入");
            return;
        }
        // 找到startNo对应的节点，以及startNo的前一个节点
        Boy start = first;
        Boy helper = first;
        while (start != null) {
            if (start.getNext().getNo() == startNo) {
                helper = start; // helper是startNo对应的前一个节点
                start = start.getNext(); // startNo对应的节点
                break;
            }
            start = start.getNext();
        }

        while (nums > 1) {
            for (int i = 1; i < countNum; i++) {
                start = start.getNext();
                helper = helper.getNext();
            }
            System.out.printf("小孩 %d 出圈\n", start.getNo());
            nums--;
            start = start.getNext();
            helper.setNext(start);
        }
        System.out.printf("最后留在圈中的小孩编号%d\n", start.getNo());
    }

    // 课程中的写法
    public void countBoy2(int startNo, int countNum, int nums) {
        // 先对数据进行校验
        if (first == null || startNo < 1 || startNo > nums) {
            System.out.println("参数输入有误，请重新输入");
            return;
        }
        // 将helper指向最后一个小孩节点
        Boy helper = first;
        while (true) {
            if (helper.getNext() == first) { // 说明helper指向最后的小孩节点
                break;
            }
            helper = helper.getNext();
        }
        // 小孩报数前，先让first和helper移动(startNo-1)次
        for (int j = 0; j < startNo - 1; j++) {
            first = first.getNext();
            helper = helper.getNext();
        }
        // 当小孩报数时，让first和helper指针同时移动(countNum-1)次，然后出圈
        // 这里是一个循环操作，直到圈中只有一个节点
        while (true) {
            if (helper == first) { // 说明圈中只有一个节点
                break;
            }
            // 让first和helper指针同时移动(countNum-1)
            for (int j = 0; j < countNum - 1; j++) {
                first = first.getNext();
                helper = helper.getNext();
            }
            // 这是first指向的节点，就是要出圈的小孩节点
            System.out.printf("小孩 %d 出圈\n", first.getNo());
            // 这时将first指向的小孩节点出圈
            first = first.getNext();
            helper.setNext(first);
        }
        System.out.printf("最后留在圈中的小孩编号%d\n", first.getNo());
    }
}

// 创建一个Boy类，表示一个节点
class Boy {
    private int no; // 编号
    private Boy next; // 指向下一个节点，默认null

    public Boy(int no) {
        this.no = no;
    }

    public int getNo() {
        return no;
    }

    public void setNo(int no) {
        this.no = no;
    }

    public Boy getNext() {
        return next;
    }

    public void setNext(Boy next) {
        this.next = next;
    }
}
