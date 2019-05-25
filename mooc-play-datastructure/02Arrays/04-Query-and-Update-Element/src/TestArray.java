import org.junit.Test;

/**
 * Created by shucheng on 2019-5-25 下午 22:52
 */
public class TestArray {

    @Test
    public void test1() {
        Array arr = new Array(20);
        for (int i = 0; i < 10; i++)
            arr.addLast(i);
        System.out.println(arr);

        arr.add(1, 100);
        System.out.println(arr);

        arr.addFirst(-1);
        System.out.println(arr);
    }
}
