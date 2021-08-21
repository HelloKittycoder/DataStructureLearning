package com.kittycoder.leetcode.reverse_linked_list;

/**
 * Created by shucheng on 2021/8/19 0:05
 */
public class ListNode {

    int val;
    ListNode next;

    public ListNode() {
    }

    public ListNode(int val) {
        this.val = val;
    }

    public ListNode(int val, ListNode next) {
        this.val = val;
        this.next = next;
    }

    public static ListNode buildListNode(int... valArr) {
        if (valArr != null && valArr.length > 0) {
            ListNode head = new ListNode(valArr[0]);
            if (valArr.length >= 2) {
                ListNode iterNode = head;
                for (int i = 1; i < valArr.length; i++) {
                    iterNode.next = new ListNode(valArr[i]);
                    iterNode = iterNode.next;
                }
            }
            return head;
        } else {
            return null;
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass()) return false;

        ListNode listNode = (ListNode) o;

        if (next != null) {
            return val == listNode.val && next.equals(listNode.next);
        } else {
            return val == listNode.val;
        }
    }

    public static void main(String[] args) {
        ListNode listNode = ListNode.buildListNode(1, 2, 3, 4, 5);
        ListNode listNode2 = ListNode.buildListNode(1, 2, 3, 4, 5);
        System.out.println(listNode.equals(listNode2));
    }
}
