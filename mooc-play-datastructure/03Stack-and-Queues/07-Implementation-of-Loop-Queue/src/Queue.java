/**
 * Created by shucheng on 2019-6-2 下午 22:40
 */
public interface Queue<E> {

    int getSize();
    boolean isEmpty();
    void enqueue(E e);
    E dequeue();
    E getFront();
}
