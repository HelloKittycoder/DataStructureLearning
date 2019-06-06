/**
 * Created by shucheng on 2019-6-2 下午 23:29
 * 循环队列的实现，不再复用动态数组，完全重写底层
 */
public class LoopQueue<E> implements Queue<E> {

    private E[] data;
    private int front, tail;
    // 这个size也可以不写，直接通过front、tail计算出来；
    // 这里暂且先多维护一个属性size，写起来稍微简单些
    private int size;

    public LoopQueue(int capacity) {
        data = (E[]) new Object[capacity + 1];
        front = 0;
        tail = 0;
        size = 0;
    }

    public LoopQueue() {
        this(10);
    }

    public int getCapacity() {
        return data.length - 1;
    }

    @Override
    public int getSize() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return front == tail;
    }

    @Override
    public void enqueue(E e) {

    }

    @Override
    public E dequeue() {
        return null;
    }

    @Override
    public E getFront() {
        return null;
    }
}
