/**
 * Created by shucheng on 2019-6-10 下午 21:35
 */
public interface Set<E> {

    void add(E e);
    void remove(E e);
    boolean contains(E e);
    int getSize();
    boolean isEmpty();
}
