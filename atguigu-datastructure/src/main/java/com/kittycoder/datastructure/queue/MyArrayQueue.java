package com.kittycoder.datastructure.queue;

/**
 * Created by shucheng on 2019-10-29 上午 8:51
 * 自己根据课程里的思路写的数组队列
 *
 * 说明：这个数组队列对于size是手动进行维护的，以及入队、出队操作都需要修改size，
 * 判断isFull()和isEmpty()是通过size来做的
 *
 * ArrayQueueDemo的size是通过front和rear算出来的，入队、出队操作不需要改size，
 * 判断isFull()和isEmpty()是通过front与rear的关系来算的
 */
public class MyArrayQueue {

    private int front = -1;
    private int rear = -1;

    // 用来存储队列元素的数组
    private int[] arr;
    // 队列元素的个数
    private int size;

    // 队列的默认容量为10
    public MyArrayQueue() {
        arr = new int[10];
    }

    // 设置队列默认容量为capacity
    public MyArrayQueue(int capacity) {
        arr = new int[capacity];
    }

    // 获取队列容量
    public int getCapacity() {
        return arr.length;
    }

    // 入队
    public void enqueue(int e) {
        // 判断队列是否已满
        if (isFull()) {
            System.out.println("队列满，不能加入数据~");
            return;
        }
        arr[size] = e;
        rear++;
        size++;
    }

    // 出队
    public int dequeue() {
        // 判断队列是否为空
        if (isEmpty()) {
            // 抛出异常
            throw new RuntimeException("队列空，不能取数据");
        }
        int old = arr[front+1];
        arr[front+1] = 0;
        front++;
        size--;
        return old;
    }

    // 获取队列元素个数
    public int size() {
        return size;
    }

    // 判断队列是否满
    public boolean isFull() {
        return size == getCapacity();
    }

    // 判断队列是否为空
    public boolean isEmpty() {
        return size == 0;
    }

    // 打印ArrayQueue
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("size:" + size + "====");
        sb.append("front:");
        for (int i = front + 1; i <= rear; i++) {
            if (i != rear) {
                sb.append(arr[i] + ",");
            } else {
                sb.append(arr[i]);
            }
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        MyArrayQueue m = new MyArrayQueue();
        System.out.println("是否为空：" + m.isEmpty());
        System.out.println("队列默认容量为：" + m.getCapacity());
        System.out.println("执行入队操作");
        m.enqueue(2);
        System.out.println(m);
        m.enqueue(3);
        System.out.println(m);
        m.enqueue(1);
        System.out.println(m);
        System.out.println("执行出队操作");
        m.dequeue();
        System.out.println(m);
        m.dequeue();
        System.out.println(m);
        m.dequeue();
        System.out.println(m);
    }
}
