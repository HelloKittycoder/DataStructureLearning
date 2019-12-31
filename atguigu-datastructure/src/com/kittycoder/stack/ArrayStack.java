package com.kittycoder.stack;

import java.util.Scanner;

/**
 * Created by shucheng on 2019/12/30 22:38
 * 用数组来模拟一个栈
 */
public class ArrayStack<E> {

    private E[] stack; // 数组，数组模拟栈，数据就放在该数组
    private int top; // top表示栈顶，初始化为-1

    public ArrayStack() {
        this(10);
    }

    // 构造器
    public ArrayStack(int capacity) {
        stack = (E[]) new Object[capacity];
        top = -1;
    }

     // 栈的容量
    public int getCapacity() {
        return stack.length;
    }

    // 栈满
    public boolean isFull() {
        return top == getCapacity() - 1;
    }

    // 栈空
    public boolean isEmpty() {
        return top == -1;
    }

    // 入栈
    public void push(E e) {
        if (isFull()) {
            System.out.println("栈满，不能放入数据");
            return;
        }
        top++;
        stack[top] = e;
    }

    // 出栈，将栈顶的元素返回
    public E pop() {
        if (isEmpty()) {
            // 抛出异常
            throw new RuntimeException("栈空，不能取出数据");
        }
        E value = stack[top];
        top--;
        return value;
    }

    // 课程中的打印栈内元素
    public void list() {
        if (isEmpty()) {
            System.out.println("栈空，没有数据~");
            return;
        }
        // 需要从栈顶开始显示数据
        for (int i = top; i >= 0; i--) {
            System.out.printf("stack[%d] = %d\n", i, stack[i]);
        }
    }

    // 打印栈内元素（自己的写法，直接利用toString来构造打印格式）
    // 注意：遍历时，需要从栈顶开始显示数据
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("top:");
        for (int i = top; i >= 0; i--) {
            sb.append(stack[i]);
            if (i != 0) {
                sb.append(",");
            }
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        /*ArrayStack arrayStack = new ArrayStack();
        for (int i = 1; i <= 5; i++) {
            arrayStack.push(i);
            System.out.print("放入元素" + i + " ");
            System.out.println(arrayStack);
        }
        for (int i = 1; i <= 5; i++) {
            System.out.print("取出元素" + arrayStack.pop() + " ");
            System.out.println(arrayStack);
        }*/

        // 测试一下ArrayStack是否正确
        // 先创建一个ArrayStack对象->表示栈
        ArrayStack<Integer> stack = new ArrayStack(4);
        String key = "";
        boolean loop = true; // 控制是否退出菜单
        Scanner scanner = new Scanner(System.in);

        while (loop) {
            System.out.println("show:表示显示栈");
            System.out.println("exit:退出程序");
            System.out.println("push:表示添加数据到栈（入栈）");
            System.out.println("pop:表示从栈取出数据（出栈）");
            System.out.println("请输入你的选择");
            key = scanner.next();
            switch (key) {
                case "show":
                    stack.list();
                    break;
                case "push":
                    System.out.println("请输入一个数");
                    int value = scanner.nextInt();
                    stack.push(value);
                    break;
                case "pop":
                    try {
                        int res = stack.pop();
                        System.out.printf("出栈的数据是 %d\n", res);
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case "exit":
                    scanner.close();
                    loop = false;
                    break;
                default:
                    break;
            }
        }
        System.out.println("程序退出~~~");
    }
}
