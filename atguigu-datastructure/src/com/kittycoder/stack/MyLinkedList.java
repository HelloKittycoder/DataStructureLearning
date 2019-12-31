package com.kittycoder.stack;

/**
 * Created by shucheng on 2019/12/31 11:14
 */
public class MyLinkedList<E> {

    private class Node {
        public E e;
        public Node next;

        public Node(E e, Node next) {
            this.e = e;
            this.next = next;
        }

        public Node() {
            this(null, null);
        }

        public Node(E e) {
            this(e, null);
        }

        @Override
        public String toString() {
            return e.toString();
        }
    }

    private Node dummyHead;
    private int size; // 链表中的元素个数

    // 判断链表是否为空
    public boolean isEmpty() {
        return size == 0;
    }

    public MyLinkedList() {
        dummyHead = new Node();
        size = 0;
    }

    // 将元素添加到链表开始
    public void addFirst(E e) {
        /*Node newNode = new Node(e, dummyHead.next);
        dummyHead.next = newNode;
        size++;*/

        add(0, e);
    }

    // 将元素添加到链表末尾
    public void addLast(E e) {
        /*Node cur = dummyHead;
        // 遍历至链表的最后一个节点
        for (int i = 0; i < size; i++) {
            cur = cur.next;
        }

        Node newNode = new Node(e, null);
        cur.next = newNode;
        size++;*/

        add(size, e);
    }

    // 在链表的index位置添加元素
    public void add(int index, E e) {
        if (index < 0 || index > size) {
            throw new RuntimeException("添加失败，index位置无效");
        }
        Node prev = dummyHead;
        // 遍历至索引为index的前一个节点
        for (int i = 0; i < index; i++) {
            prev = prev.next;
        }

        Node newNode = new Node(e, prev.next);
        prev.next = newNode;
        size++;
    }

    // 移除链表中指定索引的元素
    public E remove(int index) {
        if (index < 0 || index >= size) {
            throw new RuntimeException("移除失败，index位置无效");
        }
        Node prev = dummyHead;
        // 遍历至索引为index的前一个节点
        for (int i = 0; i < index; i++) {
            prev = prev.next;
        }

        Node retNode = prev.next;
        prev.next = retNode.next;
        retNode.next = null;
        size--;

        return retNode.e;
    }

    // 移除链表中第一个元素
    public E removeFirst() {
        return remove(0);
    }

    // 移除链表中最后一个元素
    public E removeLast() {
        return remove(size - 1);
    }

    @Override
    public String toString() {
        StringBuilder res = new StringBuilder();
        for (Node cur = dummyHead.next; cur != null; cur = cur.next) {
            res.append(cur + "->");
        }
        res.append("NULL");
        return res.toString();
    }

    public static void main(String[] args) {
        MyLinkedList<Integer> m = new MyLinkedList<>();
        m.addLast(1);
        m.addLast(2);
        m.addLast(3);
        m.addLast(4);
        m.addLast(5);
        m.add(2, 2222);
        m.add(4, 4444);
        m.addFirst(1111);
        m.addFirst(1112);
        System.out.println(m); // 1112->1111->1->2->2222->3->4444->4->5->NULL
        m.remove(8);
        System.out.println(m);
        m.remove(0);
        System.out.println(m);
        m.removeFirst();
        System.out.println(m);
    }
}
