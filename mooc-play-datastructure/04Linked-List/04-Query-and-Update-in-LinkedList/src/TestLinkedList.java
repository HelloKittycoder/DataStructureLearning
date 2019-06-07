import org.junit.Test;

/**
 * Created by shucheng on 2019-6-7 上午 11:14
 */
public class TestLinkedList {

    @Test
    public void test1() {

        LinkedList<Integer> linkedList = new LinkedList<>();
        for (int i = 0; i < 5; i++) {
            linkedList.addFirst(i);
            System.out.println(linkedList);
        }

        linkedList.add(2, 666);
        System.out.println(linkedList);
    }
}
