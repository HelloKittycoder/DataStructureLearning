/**
 * Created by shucheng on 2019-5-31 下午 22:52
 */
public interface Stack<E> {

    int getSize();
    boolean isEmpty();
    void push(E e);
    E pop();
    E peek();
}
