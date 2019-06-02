import org.junit.Test;

/**
 * Created by shucheng on 2019-6-2 下午 14:19
 */
public class TestArrayStack {

    @Test
    public void test() {
        ArrayStack<Integer> stack = new ArrayStack<>();
        for (int i = 0; i < 5; i++) {
            stack.push(i);
            System.out.println(stack);
        }

        stack.pop();
        System.out.println(stack);
    }
}
