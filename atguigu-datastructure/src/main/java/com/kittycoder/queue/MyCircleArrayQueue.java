package com.kittycoder.queue;

/**
 * Created by shucheng on 2019-10-31 下午 22:41
 * 自己根据课程里的思路写的循环数组队列
 */
public class MyCircleArrayQueue {

    private int front;
    private int rear;

    // 用来存储队列元素的数组
    private int[] arr;

    // 队列的默认容量为10
    public MyCircleArrayQueue() {
        arr = new int[10];
    }

    // 设置队列默认容量为capacity
    public MyCircleArrayQueue(int capacity) {
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
        int c = getCapacity();
        // 说明：写成(rear+1)%c是综合了两种情况
        // （1）rear+1没有超出c-1时，就是rear+1
        // （2）rear+1超出c-1时，就是(rear+1)%c
        arr[(rear+1)%c] = e;
        rear = (rear+1)%c;
    }

    // 出队
    public int dequeue() {
        // 判断队列是否为空
        if (isEmpty()) {
            // 抛出异常
            throw new RuntimeException("队列空，不能取数据");
        }
        int c = getCapacity();
        // 说明：写成(front+1)%c是综合了两种情况
        // （1）front+1没有超出c-1时，就是front+1
        // （2）front+1超出c-1时，就是(front+1)%c
        int old = arr[(front+1)%c];
        arr[(front+1)%c] = 0;
        front = (front+1)%c;
        return old;
    }

    // 获取队列元素个数
    // （1）当front<rear时，size=rear-front
    // 因为rear-front<=c，所以(rear-front+c)%c=rear-front
    // 即size=rear-front=(rear-front+c)%c
    // （2）当front>=rear时，size=(rear-0)+(c-front)=rear-front+c
    // 因为rear-front+c<=c，所以(rear-front+c)%c=rear-front+c
    // 即size=rear-front+c=(rear-front+c)%c
    // 综合，得 size=(rear-front+c)%c
    public int size() {
        int c = getCapacity();
        return (rear - front + c) % c;
    }

    // 判断队列是否满（容量还剩一个位置时，就算满了；
    // 不采用rear==front的原因：如果采用rear==front时，算成满的话，因为满足rear==front也有可能队列为空，
    // 所以这个条件无法推断出队列是满的，不能采用rear==front来做判断）
    // （1）当front>=rear时，为 rear+1=front
    // 等同于 (rear+1)%c=front%c=front，即(rear+1)%c=front
    // （2）当front<rear时，为 rear-front+1=c
    // 其实，就是当rear=c-1,front=0时，队列为满的，(c-1+1)%c=0=front
    // 综合，得 队列满的条件为 (rear+1)%c=front
    public boolean isFull() {
        int c = getCapacity();
        return (rear + 1) % c == front;
    }

    // 判断队列是否为空
    public boolean isEmpty() {
        // return size() == 0;
        return rear == front;
    }

    // 打印CircleArrayQueue
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("size:" + size() + "====");
        sb.append("front:");
        int size = size();

        // 遍历方式1：
        // [front+1,front+size]，总共有size个元素
        for (int i = front + 1; i <= front + size; i++) {
            // 根据i%c获取元素实际存放的索引位置
            int index = i % getCapacity();
            sb.append(arr[index]);
            if (index != rear) { // 如果不是最后一个元素
                sb.append(",");
            }
        }

        // 遍历方式2：
        /*for (int i = front + 1; i != rear + 1; i = (i+1) % c) {
            sb.append(arr[i]);
            if (i != rear) { // 如果不是最后一个元素
                sb.append(",");
            }
        }*/
        return sb.toString();
    }

    public static void main(String[] args) {
        MyCircleArrayQueue m = new MyCircleArrayQueue();
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
