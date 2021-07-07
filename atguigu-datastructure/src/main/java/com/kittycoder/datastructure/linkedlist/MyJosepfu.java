package com.kittycoder.datastructure.linkedlist;

/**
 * Created by shucheng on 2019/12/29 21:10
 * 约瑟夫问题-根据课程思路简单写的（比较粗糙，基本功能有了）
 */
public class MyJosepfu {
    private JsNode first;
    private JsNode cur;

    public MyJosepfu() {
        this(1);
    }

    public MyJosepfu(int firstNo) {
        first = new JsNode(firstNo, first);
        cur = first;
    }

    public void add(JsNode node) {
        cur.next = node;
        node.next = first;
        cur = node;
    }

    public void printDequeueNum() {
        for (JsNode temp = first; temp != null;) {
            temp = temp.next;
            System.out.println(temp);
            del(temp.no);

            if (first == null && cur == null) {
                break;
            }
            temp = temp.next;
        }
    }

    public void del(int no) {
        JsNode temp = first;
        // 通过遍历找到要删除的节点
        boolean isFound = false;
        while (temp != null) {
            if (temp == first && temp.no == no) {
                isFound = true;
                break;
            }
            if (temp.next == first) {
                break; // 已经遍历完链表
            }
            if (temp.next.no == no) {
                isFound = true;
                break;
            }
            temp = temp.next;
        }
        if (isFound) { // 找到
            // 可以删除
            if (temp == first && temp.no == no) { // 要删除的节点刚好是first
                if (temp.next != first) { // 此时不止一个节点
                    first = temp.next;
                    cur.next = first;
                } else { // 此时只有一个节点
                    first = null;
                    cur = null;
                }
            } else if (temp == cur) { // 要删除的节点是最后一个节点
                temp.next = temp.next.next;
                cur = temp.next.next;
            } else { // 要删除的节点是中间的节点
                if (temp == temp.next.next) { // 如果删除后只剩下一个节点
                    cur = temp;
                }
                temp.next = temp.next.next;
            }
        } else {
            System.out.printf("没有找到编号%d的节点，不能删除\n", no);
        }
    }

    public void list() {
        JsNode temp = first;
        while (temp != null) {
            System.out.println(temp);
            if (temp == cur) {
                break;
            }
            temp = temp.next;
        }
    }
}

class JsNode {
    int no;
    JsNode next;

    public JsNode(int no) {
        this.no = no;
    }

    public JsNode(int no, JsNode next) {
        this.no = no;
        this.next = next;
    }

    public void setNext(JsNode next) {
        this.next = next;
    }

    @Override
    public String toString() {
        return "JsNode{" +
                "no=" + no +
                '}';
    }
}
