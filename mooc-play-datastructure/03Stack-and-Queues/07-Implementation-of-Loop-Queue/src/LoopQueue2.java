/**
 * Created by shucheng on 2019-6-6 下午 13:33
 * 按照自己理解重新写了一次循环队列的实现
 */
public class LoopQueue2<E> implements Queue<E> {

    private E[] data;

    private int front,tail;
    private int size;

    public LoopQueue2(int capacity) {
        data = (E[]) new Object[capacity + 1];
        front = 0;
        tail = 0;
        size = 0;
    }

    public LoopQueue2() {
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
        return size == 0;
    }

    @Override
    public void enqueue(E e) {

        if ((tail + 1) % getCapacity() == front)
            resize(2 * getCapacity());

        data[tail] = e;
        tail = (tail + 1) % getCapacity();
        size++;
    }

    @Override
    public E dequeue() {

        E oldData = data[front];
        data[front] = null;
        front = (front + 1) % getCapacity();
        size--;

        if (size == getCapacity() / 4 && getCapacity() / 2 != 0)
            resize(getCapacity() / 2);

        return oldData;
    }

    public void resize(int capacity) {

        E[] newData = (E[]) new Object[capacity + 1];
        for (int i = 0; i < size; i++) {
            newData[i] = data[(i + front) % getCapacity()];
        }

        data = newData;
        front = 0;
        tail = size;
    }

    @Override
    public E getFront() {
        return data[front];
    }

    @Override
    public String toString() {
        StringBuilder ret = new StringBuilder();
        ret.append(String.format("Queue: size = %d, capacity = %d\n", size, getCapacity()));
        ret.append("front [");
        for (int i = front; i != tail; i = (i + 1) % getCapacity()) {
            ret.append(data[i]);
            if ((i + 1) % getCapacity() != tail)
                ret.append(",");
        }
        ret.append("] tail");
        return ret.toString();
    }

    public static void main(String[] args) {

        Queue<Integer> queue = new LoopQueue2<>(3);
        for (int i = 0; i < 10; i++) {
            queue.enqueue(i);
            System.out.println(queue);
            if (i % 3 == 2) {
                queue.dequeue();
                System.out.println(queue);
            }
        }
    }
}
