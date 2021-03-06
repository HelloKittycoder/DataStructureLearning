import org.junit.Test;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by shucheng on 2019-6-8 下午 14:26
 */
public class TestBST {

    @Test
    public void test1() {
        BST<Integer> bst = new BST<>();
        Random random = new Random();

        int n = 1000;

        // test removeMin
        for (int i = 0; i < n; i ++)
            bst.add(random.nextInt(10000));

        ArrayList<Integer> nums = new ArrayList<>();
        while (!bst.isEmpty())
            nums.add(bst.removeMin());

        System.out.println(nums);
        for (int i = 1; i < nums.size(); i++)
            if (nums.get(i - 1) > nums.get(i))
                throw new IllegalArgumentException("Error");
        System.out.println("removeMin test completed.");

        // test removeMax
        for (int i = 0; i < n; i ++)
            bst.add(random.nextInt(10000));

        nums = new ArrayList<>();
        while (!bst.isEmpty())
            nums.add(bst.removeMax());

        System.out.println(nums);
        for (int i = 1; i < nums.size(); i++)
            if (nums.get(i - 1) < nums.get(i))
                throw new IllegalArgumentException("Error");
        System.out.println("removeMax test completed.");
    }
}
