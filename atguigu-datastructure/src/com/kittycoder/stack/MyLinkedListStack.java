package com.kittycoder.stack;

/**
 * Created by shucheng on 2019/12/31 10:53
 * 用链表来模拟一个栈
 */
public class MyLinkedListStack<E> {

    private MyLinkedList<E> list;

    public MyLinkedListStack() {
        this.list = new MyLinkedList<>();
    }

    // 栈是否为空
    public boolean isEmpty() {
        return list.isEmpty();
    }

    // 入栈
    public void push(E e) {
        list.addFirst(e);
    }

    // 出栈
    public E pop() {
        return list.removeFirst();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Stack: top ");
        sb.append(list);
        return sb.toString();
    }

    public static void main(String[] args) {
        MyLinkedListStack<String> m = new MyLinkedListStack<>();
        m.push("1");
        m.push("2");
        m.push("3");
        System.out.println(m);
        m.pop();
        System.out.println(m);
        m.pop();
        System.out.println(m);
        m.pop();
        System.out.println(m);
        /*m.pop();
        System.out.println(m);*/
    }
}
